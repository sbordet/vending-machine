package it.jugmi.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;

public class FPVendingMachine {
    public static Model cash(Model model, Cash cash) {
        return new Model(model.infos, model.cash.plus(cash), "Thanks!");
    }

    public static Model choose(Model model, Product product) {
        Optional<ProductInfo> productInfoOpt = model.infos.stream()
                .filter(info -> info.product.equals(product))
                .findAny();
        return productInfoOpt.map(productInfo -> {
            Optional<Cash> cashOpt = model.cash.minus(productInfo.cost);
            return cashOpt.map(newCash -> new Model(model.infos, newCash, "Enjoy!"))
                    .orElseGet(() -> new Model(model.infos, model.cash, "Insufficient Cash"));
        }).orElseGet(() -> new Model(model.infos, model.cash, "Invalid Product"));
    }

    public static void main(String[] args) {
        List<ProductInfo> infos = new ArrayList<>();
        infos.add(new ProductInfo(new Product("Coffee"), new Cash(2)));

        Model init = new Model(infos, new Cash(0), "Ready");
        Model result = choose(cash(init, new Cash(10)), infos.get(0).product);
        System.err.println(result);
    }
}

