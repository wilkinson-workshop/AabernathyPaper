package adt.aabernathypaper;

import org.bukkit.Material;

public class TradableGood {
    private Material     good;
    private TradableType type;
    private Double       primeWeight;

    // This offset value is used in calculation
    // of trade values to offset the value of the
    // root item weight.
    private Double primeOffset;

    protected static Double globalBuyOffset  = 0.0;
    protected static Double globalSellOffset = 0.0;
    protected static Double globalSellWeight = 0.0;

    public TradableGood(Material good, TradableType type, Double weight) {
        this(good, type, weight, 0.0);
    }

    public TradableGood(
        Material good,
        TradableType type,
        Double weight,
        Double offset) {

        this.good        = good;
        this.type        = type;
        this.primeWeight = weight;
        this.primeOffset = offset;
    }

    /*
     * Calculates the buy price of an individual
     * item.
     */
    public Double buyPrice(TradableGood root) {
        Double rootWeight          = root.getWeight();
        Double rootWeightInverse   = (1 / root.getWeight());
        Double weightWithBuyOffset = (this.getWeight() + globalBuyOffset);

        return rootWeight * Math.pow(weightWithBuyOffset, rootWeightInverse);
    }

    /*
     * Calculates the buy price of a stack of
     * this item.
     */
    public Double buyPriceStack(TradableGood root) {
        return this.buyPrice(root) * this.getGood().getMaxStackSize();
    }

    protected Material getGood() {
        return this.good;
    }

    protected TradableType getType() {
        return this.type;
    }

    protected Double getWeight() {
        Double weightWithOffset = (this.primeWeight + this.primeOffset);
        return this.primeWeight + (weightWithOffset * this.type.getWeight());
    }

    /*
     * Calculates the sell price of an individual
     * item.
     */
    public Double sellPrice(TradableGood root) {
        Double buyPrice   = this.buyPrice(root);
        Double sellWeight = (globalSellWeight * root.getWeight());

        return buyPrice * (sellWeight + globalSellOffset);
    }

    /*
     * Calculates the sell price of a stack of
     * this item.
     */
    public Double sellPriceStack(TradableGood root) {
        return this.sellPrice(root) * this.getGood().getMaxStackSize();
    }

    /*
     * Set the global buy offset used to offset
     * trabable buy weights.
     */
    public static void setGlobalBuyOffset(Double offset) {
        globalBuyOffset = offset;
    }

    /*
     * Set the global sell offset used to offset
     * tradable sell weights.
     */
    public static void setGlobalSellOffset(Double offset) {
        globalSellOffset = offset;
    }

    /*
     * Set the global sell weight. This value is
     * used to modifiy root item weights before
     * calculating against a tradable's weight.
     */
    public static void setGlobalSellWeight(Double weight) {
        globalSellWeight = weight;
    }
}
