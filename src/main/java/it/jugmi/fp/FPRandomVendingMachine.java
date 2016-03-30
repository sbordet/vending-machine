package it.jugmi.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;
import it.jugmi.rng.RNG;
import it.jugmi.rng.SimpleRNG;

/**
 * Same as {@link FPVendingMachine}, but with randomness in calculating the cost.
 * <p>
 * It shows how the randomness causes the model to change, and the ripple effect
 * that now we must change all the places where we create a model passing in the
 * random state.
 */
public class FPRandomVendingMachine {
    public static RandomModel cash(RandomModel model, Cash cash) {
        return new RandomModel(model.infos, model.cash.plus(cash), "Thanks!", model.rng);
    }

    public static RandomModel choose(RandomModel model, Product product) {
        Optional<ProductInfo> productInfoOpt = model.infos.stream()
                .filter(info -> info.product.equals(product))
                .findAny();
        return productInfoOpt.map(productInfo ->
        {
            Cash cost = productInfo.cost;

            // This is the additional random code.
            RNG.Result<Integer> randomResult = model.rng.nextInt();
            if (randomResult.value >= 0) {
                cost = Cash.ZERO;
            }

            return model.cash.minus(cost).map(newCash -> new RandomModel(model.infos, newCash, "Enjoy!", randomResult.rng))
                    .orElseGet(() -> new RandomModel(model.infos, model.cash, "Insufficient Cash", randomResult.rng));
        }).orElseGet(() -> new RandomModel(model.infos, model.cash, "Invalid Product", model.rng));
    }

    public static void main(String[] args) {
        List<ProductInfo> infos = new ArrayList<>();
        infos.add(new ProductInfo(new Product("Coffee"), new Cash(2)));

        RandomModel init = new RandomModel(infos, new Cash(0), "Ready", new SimpleRNG(System.nanoTime()));
        RandomModel result = choose(cash(init, new Cash(10)), infos.get(0).product);
        System.err.println(result);
    }
}
