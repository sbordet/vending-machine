package it.jugmi.oop;

import java.util.List;
import java.util.Optional;

import it.jugmi.Cash;
import it.jugmi.Product;
import it.jugmi.ProductInfo;

public class OOPModel {
    private final List<ProductInfo> productInfos;
    private Cash cash = Cash.ZERO;

    public OOPModel(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public ProductInfo find(Product product) {
        return productInfos.stream()
                .filter(info -> info.product.equals(product))
                .findAny()
                .orElse(null);
    }

    public void credit(Cash cash) {
        this.cash = this.cash.plus(cash);
    }

    public boolean debit(Cash cash) {
        Optional<Cash> result = this.cash.minus(cash);
        if (!result.isPresent()) {
            return false;
        }
        this.cash = result.get();
        return true;
    }
}
