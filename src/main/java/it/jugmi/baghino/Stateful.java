package it.jugmi.baghino;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;
import it.jugmi.fp.FPVendingMachine;
import it.jugmi.fp.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
interface Stateful<T> {
    <U> Function<U, Stateful<T>> transition(BiFunction<T, U, T> step);
}

final class StatefulModel implements Stateful<Model> {

    final Model model;

    public StatefulModel(final Model model) {
        this.model = model;
    }

    @Override
    public <U> Function<U, Stateful<Model>> transition(BiFunction<Model, U, Model> step) {
        return u -> new StatefulModel(step.apply(model, u));
    }

    @Override
    public String toString() {
        return model.toString();
    }

    public static void main(String[] args) {
        List<ProductInfo> availableProducts = new ArrayList<>();
        availableProducts.add(new ProductInfo(new Product("Coffee"), new Cash(2)));

        Stateful<Model> startingSituation = new StatefulModel(new Model(availableProducts, new Cash(0), "Ready"));

        Stateful<Model> finalSituation =
                startingSituation.
                        transition(FPVendingMachine::cash).apply(new Cash(1)).
                        transition(FPVendingMachine::choose).apply(availableProducts.get(0).product).
                        transition(FPVendingMachine::cash).apply(new Cash(1)).
                        transition(FPVendingMachine::choose).apply(availableProducts.get(0).product);

        System.err.println(finalSituation);
    }
}
