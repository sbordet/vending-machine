package it.jugmi.rng;

public class SimpleRNG implements RNG {
    private final long seed;

    public SimpleRNG(long seed) {
        this.seed = seed;
    }

    @Override
    public Result<Integer> nextInt() {
        long newSeed = (seed * 0x5DEECE66DL + 0xB) & ((1L << 48) - 1);
        int newValue = (int)(newSeed >>> 16);
        return new Result<>(newValue, new SimpleRNG(newSeed));
    }
}
