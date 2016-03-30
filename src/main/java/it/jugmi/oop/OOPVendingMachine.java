package it.jugmi.oop;

import java.util.ArrayList;
import java.util.List;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;

public class OOPVendingMachine {
    private final OOPModel model;

    public OOPVendingMachine(List<ProductInfo> productInfos) {
        model = new OOPModel(productInfos);
    }

    public void cash(Cash cash) {
        model.credit(cash);
    }

    public void choose(Product product) throws Exception {
        ProductInfo productInfo = find(product);
        if (productInfo == null) {
            throw new Exception("Invalid Product");
        }
        if (!buy(productInfo)) {
            throw new Exception("Insufficient Cash");
        }
    }

    protected ProductInfo find(Product product) {
        return model.find(product);
    }

    protected boolean buy(ProductInfo productInfo) {
        return model.debit(productInfo.cost);
    }

    public static void main(String[] args) throws Exception {
        List<ProductInfo> infos = new ArrayList<>();
        infos.add(new ProductInfo(new Product("Coffee"), new Cash(2)));

        OOPVendingMachine machine = new OOPVendingMachine(infos);
        machine.cash(new Cash(10));
        machine.choose(infos.get(0).product);
    }
}
