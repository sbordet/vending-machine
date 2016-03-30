package it.jugmi.fp;

import java.util.List;

import it.jugmi.Cash;
import it.jugmi.ProductInfo;

public class Model {
    public final List<ProductInfo> infos;
    public final Cash cash;
    public final String status;

    public Model(List<ProductInfo> infos, Cash cash, String status) {
        this.infos = infos;
        this.cash = cash;
        this.status = status;
        System.err.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%s | %s]", cash, status);
    }
}
