package adt.aabernathypaper;

import org.bukkit.Material;

public interface TradableData {
    public void         addTradable(TradableGood tradable);
    public TradableGood getTradable(Material good);
    public TradableGood getRootTradable();
    public void         update(Material good);
    public void         updateAll();
}
