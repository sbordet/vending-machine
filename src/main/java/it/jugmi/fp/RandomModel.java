package it.jugmi.fp;

import java.util.List;

import it.jugmi.Cash;
import it.jugmi.ProductInfo;
import it.jugmi.rng.RNG;

public class RandomModel extends Model {
    public final RNG rng;

    public RandomModel(List<ProductInfo> infos, Cash cash, String status, RNG rng) {
        super(infos, cash, status);
        this.rng = rng;
    }
}
