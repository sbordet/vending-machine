package it.jugmi.oop;

import java.util.List;
import java.util.Random;

import it.jugmi.ProductInfo;

public class OOPRandomVendingMachine extends OOPVendingMachine {
    private final Random random = new Random();

    public OOPRandomVendingMachine(List<ProductInfo> productInfos) {
        super(productInfos);
    }

    @Override
    protected boolean buy(ProductInfo productInfo) {
        return random.nextBoolean() || super.buy(productInfo);
    }
}
