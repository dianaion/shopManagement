
package sistemff;

import java.util.*;

public class MiniMarket extends Magazin{
    
    private String tip;
    
    public MiniMarket(){
        tip = "MiniMarket";
    }
    
    @Override
    public int calculScutiriTaxe(){
        Vector<String> tari = new Vector<String>();
        for(Factura f : this.facturi){
            for(ProdusComandat pc: f.produse){
                String s = pc.getProdus().getTaraOrigine();
                tari.add(s);
            }
        }
        for(String s : tari)
            if(this.getTotalTaraCuTaxe(s) > 0.5 * this.getTotalCuTaxe())
                return 10;
        return 0;
    }
    
    @Override
    public String toString(){
        return(tip + "\n" + super.toString());
    }
    
}
