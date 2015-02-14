
package sistemff;

import java.util.*;

public class MediumMarket extends Magazin {
    
    private String tip;
    
    public MediumMarket(){
        tip = "MediumMarket";
    }
    
    @Override
    public int calculScutiriTaxe(){
         Vector<String> categorii = new Vector<String>();
        for(Factura f : this.facturi){
            for(ProdusComandat pc: f.produse){
                String s = pc.getProdus().getCategorie();
                categorii.add(s);
            }
        }
        for(String s : categorii)
            if(this.getTotalCategorieCuTaxe(s) > 0.5 * this.getTotalCuTaxe())
                return 5;
        return 0;
    }
    
    @Override
    public String toString(){
        return(tip + "\n" + super.toString());
    }
    
}
