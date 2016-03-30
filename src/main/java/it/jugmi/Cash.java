package it.jugmi;

import java.util.Optional;

public class Cash {
    public static final Cash ZERO = new Cash(0);

    private final int value;

    public Cash(int value) {
        this.value = value;
    }

    public Cash plus(Cash cash) {
        return new Cash(value + cash.value);
    }

    public Optional<Cash> minus(Cash cash) {
        int newValue = value - cash.value;
        if (newValue < 0) {
            return Optional.empty();
        }
        return Optional.of(new Cash(newValue));
    }
}
