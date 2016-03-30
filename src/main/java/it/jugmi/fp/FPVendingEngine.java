package it.jugmi.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;

@FunctionalInterface
public interface FPVendingEngine {
    Model apply(Model model);

    default FPVendingEngine map(Function<Model, Model> f) {
        return m -> f.apply(apply(m));
    }

    default FPVendingEngine cash(Cash cash) {
        return map(model -> FPVendingMachine.cash(model, cash));
    }

    default FPVendingEngine choose(Product product) {
        return map(model -> FPVendingMachine.choose(model, product));
    }
}

class FPVendingEngineImpl implements FPVendingEngine {
    @Override
    public Model apply(Model model) {
        return model;
    }

    public static void main(String[] args) {
        List<ProductInfo> infos = new ArrayList<>();
        infos.add(new ProductInfo(new Product("Coffee"), new Cash(2)));

        FPVendingEngine engine = new FPVendingEngineImpl();
        Model init = new Model(infos, new Cash(0), "Ready");
        Model result = engine.cash(new Cash(10)).choose(infos.get(0).product).apply(init);
        System.err.println(result);
    }
}
