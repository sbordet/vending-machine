package it.jugmi.baghino;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;
import it.jugmi.fp.FPVendingMachine;
import it.jugmi.fp.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
interface MStateful<T> {
    <U> MStateful<U> transition(Function<T, MStateful<U>> step);
}

final class MStatefulModel implements MStateful<Model> {

    final Model model;

    public MStatefulModel(final Model model) {
        this.model = model;
    }

    @Override
    public <U> MStateful<U> transition(Function<Model, MStateful<U>> step) {
        return step.apply(model);
    }

    @Override
    public String toString() {
        return model.toString();
    }

    public static void main(String[] args) {
        List<ProductInfo> availableProducts = new ArrayList<>();
        availableProducts.add(new ProductInfo(new Product("Coffee"), new Cash(2)));

        MStateful<Model> startingSituation = new MStatefulModel(new Model(availableProducts, new Cash(0), "Ready"));

        MStateful<Model> finalSituation =
                startingSituation.
                        transition(model -> new MStatefulModel(FPVendingMachine.cash(model, new Cash(1)))).
                        transition(model -> new MStatefulModel(FPVendingMachine.choose(model, availableProducts.get(0).product))).
                        transition(model -> new MStatefulModel(FPVendingMachine.cash(model, new Cash(1)))).
                        transition(model -> new MStatefulModel(FPVendingMachine.choose(model, availableProducts.get(0).product)));;

        System.err.println(finalSituation);
    }
}
