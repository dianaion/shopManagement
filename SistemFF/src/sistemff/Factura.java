
package sistemff;

import java.util.*;

public class Factura {
    
    public String denumire;
    public Vector<ProdusComandat> produse;
    
    public double getTotalFaraTaxe(){
        double total = 0;
        Produs p;
        for(ProdusComandat pc : produse){
            p = pc.getProdus();
            total += p.getPret() * pc.getCantitate();
        }
        return total;
    }
    
    public double getTotalCuTaxe(){
        double total = 0;
        Produs p;
        for(ProdusComandat pc : produse){
            p = pc.getProdus();
            total += p.getPret() * pc.getCantitate() * (100 + pc.getTaxa()) / 100;
        }
        return total;
    }
    
    public double getTaxe(){
        return this.getTotalCuTaxe() - this.getTotalFaraTaxe();
    }
    
    public double getTotalTaraFaraTaxe(String tara){
        double total = 0;
        Produs p;
        for(ProdusComandat pc : produse){
            p = pc.getProdus();
            if(p.getTaraOrigine().equals(tara))
                total += p.getPret() * pc.getCantitate();
        }
        return total;
    }
    
    public double getTotalTaraCuTaxe(String tara){
        double total = 0;
        Produs p;
        for(ProdusComandat pc : produse){
            p = pc.getProdus();
            if(p.getTaraOrigine().equals(tara))
                total += p.getPret() * pc.getCantitate() * (100 + pc.getTaxa()) / 100;
        }
        return total;
    }
    
    public double getTaxeTara(String tara){
        return this.getTotalTaraCuTaxe(tara) - this.getTotalTaraFaraTaxe(tara);
    }
    
    public double getTotalCategorieFaraTaxe(String categorie){
        double total = 0;
        Produs p;
        for(ProdusComandat pc : produse){
            p = pc.getProdus();
            if(p.getCategorie().equals(categorie))
                total += p.getPret() * pc.getCantitate();
        }
        return total;
    }
    
    public double getTotalCategorieCuTaxe(String categorie){
        double total = 0;
        Produs p;
        for(ProdusComandat pc : produse){
            p = pc.getProdus();
            if(p.getCategorie().equals(categorie))
                total += p.getPret() * pc.getCantitate() * (100 + pc.getTaxa()) / 100;
        }
        return total;
    }
    
    @Override
    public String toString(){
        String s = "";
        s = s + denumire + "\n";
        for(ProdusComandat pc : produse)
            s = s + pc.toString() + "\n";
        return s;
    }
    
}