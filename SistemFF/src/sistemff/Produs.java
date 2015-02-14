
package sistemff;

public class Produs {
    
    private String denumire;
    private String categorie;
    private String taraOrigine;
    private double pret;
    
    public void setDenumire(String s) {
        denumire = s;
    }
    
    public String getDenumire(){
        return this.denumire;
    }
    
    public void setCategorie(String s){
        categorie = s;
    }
    
    public String getCategorie() {
        return this.categorie;
    }
    
    public void setTaraOrigine(String s){
        taraOrigine = s;
    }
    
    public String getTaraOrigine(){
        return this.taraOrigine;
    }
    
    public void setPret(double val){
        pret = val;
    }
    
    public double getPret(){
        return this.pret;
    }
    
    @Override
    public String toString(){
        String s = this.getDenumire() + " " + this.getCategorie() + " " + this.getTaraOrigine()+ " " + this.getPret();
        return s;
    }
    
}