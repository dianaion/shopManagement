
package sistemff;

import java.util.*;

abstract public class Magazin implements IMagazin{
    
    public String nume;
    public Vector<Factura> facturi;
    
    @Override
    public double getTotalFaraTaxe(){
        double total = 0;
        for(Factura f : facturi)
            total += f.getTotalFaraTaxe();
        return total;
    }
    
    @Override
    public double getTotalCuTaxe(){
        double total = 0;
        for(Factura f : facturi)
            total += f.getTotalCuTaxe();
        return total;
    }
    
    @Override
    public double getTotalCuTaxeScutite(){
        return this.getTotalCuTaxe() * ( 100 - this.calculScutiriTaxe()) / 100;
    }
    
    @Override
    public double getTotalTaraFaraTaxe(String tara){
        double total = 0;
        for(Factura f : facturi)
            total += f.getTotalTaraFaraTaxe(tara);
        return total;
    }
    
    @Override
    public double getTotalTaraCuTaxe(String tara){
        double total = 0;
        for(Factura f : facturi)
            total += f.getTotalTaraCuTaxe(tara);
        return total;
    }
       
    @Override
    public double getTotalTaraCuTaxeScutite(String tara){
        return this.getTotalTaraCuTaxe(tara) * (100 - this.calculScutiriTaxe()) / 100;
    }
    
    public double getTotalCategorieFaraTaxe(String categorie){
        double total = 0;
        for(Factura f : facturi)
            total += f.getTotalCategorieFaraTaxe(categorie);
        return total;
    }
    
    public double getTotalCategorieCuTaxe(String categorie){
        double total = 0;
        for(Factura f : facturi)
            total += f.getTotalCategorieCuTaxe(categorie);
        return total;
    }
    
    public double getTotalCategorieCuTaxeScutite(String categorie){
        return this.getTotalCategorieCuTaxe(categorie) * (100 - this.calculScutiriTaxe()) / 100;
    }
    
    @Override
    abstract public int calculScutiriTaxe();
    
    @Override
    public String toString(){
        String s = "";
        s = s + nume + "\n";
        for(Factura f : facturi)
            s = s + f.toString() + "\n";
        return s;
    }
    
}
