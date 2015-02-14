
package sistemff;

public class FactoryMagazin {
    
    public Magazin createMagazin(String tip){
        if(tip.equals("MiniMarket"))
            return new MiniMarket();
        if(tip.equals("MediumMarket"))
            return new MediumMarket();
        if(tip.equals("HyperMarket"))
            return new HyperMarket();
        return null;
    }
    
}
