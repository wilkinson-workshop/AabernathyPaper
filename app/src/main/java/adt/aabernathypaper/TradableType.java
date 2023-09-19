package adt.aabernathypaper;

public enum TradableType {
    ROOT_ITEM(0.0, 0.0),
    COMMON(0.02),
    UNCOMMON(0.05),
    VERY_UNCOMMON(0.10),
    RARE(0.18),
    VERY_RARE(0.31),
    LEGENDARY(0.52);

    // Multiplier used for calculating trade 
    // values.
    private double weight;

    // Offset applied to weight.
    private double offset;

    private TradableType() {
        this(0.0);
    }

    private TradableType(double weight) {
        this(weight, -0.08);
    }

    private TradableType(double weight, double offset) {
        this.weight = weight;
        this.weight = offset;
    }

    public Double getWeight() {
        return this.weight + this.offset;
    }
}
