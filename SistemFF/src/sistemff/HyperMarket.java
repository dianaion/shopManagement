
package sistemff;

public class HyperMarket extends Magazin{
    
    private String tip;
    
    public HyperMarket(){
        tip = "HyperMarket";
    }
    
    @Override
    public int calculScutiriTaxe(){
        for(Factura f : this.facturi){
            if(f.getTotalCuTaxe() > 0.1 * this.getTotalCuTaxe())
                return 1;
        }
        return 0;
    }
    
    @Override
    public String toString(){
        return(tip + "\n" + super.toString());
    }
    
}
