package it.jugmi.rng;

public interface RNG {
    public Result<Integer> nextInt();

    public static class Result<V> {
        public final V value;
        public final RNG rng;

        public Result(V value, RNG rng) {
            this.value = value;
            this.rng = rng;
        }
    }
}
