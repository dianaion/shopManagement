
package sistemff;

public class ProdusComandat {
    
    private Produs produs;
    private int taxa;
    private int cantitate;
    
    public void setProdus(Produs p){
        produs = p;
    }
    
    public Produs getProdus(){
        return this.produs;
    }
    
    public void setTaxa(int t){
        taxa = t;
    }
    
    public int getTaxa(){
        return this.taxa;
    }
    
    public void setCantitate(int c){
        cantitate = c;
    }
    
    public int getCantitate(){
        return this.cantitate;
    }
    
    @Override
    public String toString(){
        String s = this.getProdus().toString() + " " + this.getTaxa() + " " + this.getCantitate();
        return s;
    }
    
}