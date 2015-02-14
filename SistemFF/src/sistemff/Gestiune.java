
package sistemff;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Gestiune {
    
    public ArrayList<Produs> produse;
    public ArrayList<Magazin> magazine;
    public HashMap<String, HashMap<String, Integer>> taxe;
   
    private static Gestiune gestiune = new Gestiune();
    
    private Gestiune(){
    }
    
    public static Gestiune getInstance(){
        return gestiune;
    }
     
    public Vector<String> readProduse(String numeFisier) throws IOException{
        BufferedReader br1 = new BufferedReader(new FileReader(numeFisier)); 
        Vector<String> tari = new Vector<String>();
        ArrayList<Produs> produse = new ArrayList<Produs>();
        int count1 = 0, count2;
        String line; 
        while((line = br1.readLine()) != null ){
            StringTokenizer st1 = new StringTokenizer(line);
            count1++;
            count2 = 0;
            if(count1 == 1){
                while(st1.hasMoreTokens()) {
                    String token = st1.nextToken();
                    count2++;
                    if(count2 >= 3)
                        tari.add(token);
                }
            }
            if(count1 > 1){
                Produs p = new Produs();
                while(st1.hasMoreElements()){
                    String token = st1.nextToken();
                    count2++;
                    if(count2 == 1)
                        p.setDenumire(token);
                    if(count2 == 2)
                        p.setCategorie(token);
                    if(count2 >= 3){
                        Produs a = new Produs();
                        Double pret = new Double(token);
                        a.setDenumire(p.getDenumire());
                        a.setCategorie(p.getCategorie());
                        a.setPret(pret);
                        a.setTaraOrigine(tari.get(count2-3));
                   
                        produse.add(a);
                    }
                }
            }
        }
        gestiune.produse = produse;
        return tari;
    }
    
    public void readTaxe(String numeFisier) throws IOException{
        BufferedReader br2 = new BufferedReader(new FileReader(numeFisier));
        Vector<String> tari2 = new Vector<String>();
        HashMap<String, HashMap<String, Integer>> taxe = new HashMap<String, HashMap<String, Integer>>();
        Vector<String> categorii = new Vector<String>();
        Vector<Integer> listaTaxe = new Vector<Integer>();
        int count1 = 0, count2;
        String line;
        while((line = br2.readLine()) != null){
            StringTokenizer st2 = new StringTokenizer(line); 
            count1++;
            count2 = 0;
            if(count1 == 1){
                while(st2.hasMoreTokens()){
                    String token = st2.nextToken();
                    count2++;
                    if(count2 > 1)
                        tari2.add(token);
                }
            }
            if(count1 > 1){
                while(st2.hasMoreTokens()){
                    String token = st2.nextToken();
                    count2++;
                    Integer taxa;
                    if(count2 == 1)
                        categorii.add(token);
                    if(count2 >= 2){
                        taxa = new Integer(token);
                        listaTaxe.add(taxa);
                    }
                }
            }
        }
        
        for(int i = 0; i < tari2.size(); i++){
            HashMap<String, Integer> pair = new HashMap<String, Integer>();
            for(int j = 0; j < categorii.size(); j++){
                pair.put(categorii.get(j), listaTaxe.get(j * tari2.size() + i));
            }
            taxe.put(tari2.get(i), pair);
        }
        gestiune.taxe = taxe;
    }
    
    public void readFacturi(String numeFisier) throws IOException{
        BufferedReader br3 = new BufferedReader(new FileReader(numeFisier));
        String line; 
        int count1 = 0, count2;
        FactoryMagazin fm = new FactoryMagazin();
        Vector<Factura> f = new Vector<Factura>();
        ArrayList<Magazin> m = new ArrayList<Magazin>();
        Vector<Integer> nr = new Vector<Integer>();
        
        count1 = 0;
        count2 = 0;
        int index = -1;
        int count3 = 0;
        while((line = br3.readLine()) != null){
            StringTokenizer st3 = new StringTokenizer(line," :");
            if(st3.hasMoreTokens()){
                
                String token = st3.nextToken();
             
                if(token.equals("Magazin")){
                    count1++;
                    count2 = 0;
                    token = st3.nextToken();
                    Magazin m1 = fm.createMagazin(token);
                    m1.nume = st3.nextToken();
                    m1.facturi = new Vector<Factura>();
                    m.add(m1);
                    line = br3.readLine();
                    nr.add(count1-1,count2);
                }
                else {
                    if(token.contains("Factura")){
                        count3 = 0;
                        count2++;
                        index++;
                        Factura f1 = new Factura();
                        f1.denumire = token;
                        f1.produse = new Vector<ProdusComandat>(); 
                        f.add(f1);
                    }
                    else {
                        count3++;
                        if(count3 > 1){
                        String s = st3.nextToken();
                        for(int i = 0; i < produse.size(); i++)
                            if(produse.get(i).getDenumire().equals(token) && produse.get(i).getTaraOrigine().equals(s)){
                                ProdusComandat p = new ProdusComandat();
                                p.setProdus(produse.get(i));
                                p.setCantitate(new Integer(st3.nextToken()));
                                p.setTaxa(taxe.get(s).get(produse.get(i).getCategorie()));
                                Factura f2 = f.get(index);
                                f2.produse.add(p);
                                nr.set(count1-1,count2);
                            }
                        }
                    }
                }         
            }
        }
        int init = 0;
        for(int i = 0; i < m.size(); i++){
            Magazin m1 = m.get(i);
            for(int j = init; j < init + nr.get(i); j++)
            {
                Factura f1 = f.get(j);
                m1.facturi.add(f1);
            }
            init += nr.get(i);
        }
        gestiune.magazine = m;
    }
    
    public Vector<String> getTari(){
        Vector<String> tari = new Vector<String>();
        for(Produs p : produse)
            if(!tari.contains(p.getTaraOrigine()))
                tari.add(p.getTaraOrigine());
        return tari;
    }
    
    public Vector<String> getCategorii(){
        Vector<String> categorii = new Vector<String>();
        for(Produs p : produse)
            if(!categorii.contains(p.getCategorie()))
                categorii.add(p.getCategorie());
        return categorii;
    }
   
    public void afisare(String numeFisier, Vector<String> tari) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
        Collections.sort(tari);
        Collections.sort(gestiune.magazine, new ComparatorMagazin());
        DecimalFormat df = new DecimalFormat("#0.###");
        bw.write("MiniMarket");
        bw.newLine();
        for(Magazin m3 : gestiune.magazine)
            if(m3 instanceof MiniMarket){
                bw.write(m3.nume);
                bw.newLine();
                bw.newLine();
                bw.write("Total " + df.format(m3.getTotalFaraTaxe())+ " " + df.format(m3.getTotalCuTaxe()) + " " + df.format(m3.getTotalCuTaxeScutite()));
                bw.newLine();
                bw.newLine();
                bw.write("Tara");
                bw.newLine();
                for(String t : tari)
                    if(m3.getTotalTaraFaraTaxe(t) != 0){
                        bw.write(t + " " + df.format(m3.getTotalTaraFaraTaxe(t)) + " " + df.format(m3.getTotalTaraCuTaxe(t))+ " " + df.format(m3.getTotalTaraCuTaxeScutite(t)));
                        bw.newLine();
                    }
                    else{
                        bw.write(t + " " + 0);
                        bw.newLine();
                    }
                bw.newLine();
                Collections.sort(m3.facturi, new ComparatorFactura());
                for(Factura fg : m3.facturi){
                    bw.write(fg.denumire);
                    bw.newLine();
                    bw.newLine();
                    bw.write("Total " + df.format(fg.getTotalFaraTaxe()) + " " + df.format(fg.getTotalCuTaxe()));
                    bw.newLine();
                    bw.newLine();
                    for(String t : tari)
                        if(fg.getTotalTaraFaraTaxe(t) != 0){
                            bw.write(t + " " + df.format(fg.getTotalTaraFaraTaxe(t)) + " " + df.format(fg.getTotalTaraCuTaxe(t)));
                            bw.newLine();
                        }
                        else{
                            bw.write(t + " " + 0);
                            bw.newLine();
                        }
                    bw.newLine();
                }
            }
        
        //MediumMarket
        bw.write("MediumMarket");
        bw.newLine();
        for(Magazin m3 : gestiune.magazine)
            if(m3 instanceof MediumMarket){
                bw.write(m3.nume);
                bw.newLine();
                bw.newLine();
                bw.write("Total " + df.format(m3.getTotalFaraTaxe())+ " " + df.format(m3.getTotalCuTaxe()) + " " + df.format(m3.getTotalCuTaxeScutite()));
                bw.newLine();
                bw.newLine();
                bw.write("Tara");
                bw.newLine();
                for(String t : tari)
                    if(m3.getTotalTaraFaraTaxe(t) != 0){
                        bw.write(t + " " + df.format(m3.getTotalTaraFaraTaxe(t)) + " " + df.format(m3.getTotalTaraCuTaxe(t))+ " " + df.format(m3.getTotalTaraCuTaxeScutite(t)));
                        bw.newLine();
                    }
                    else{
                        bw.write(t + " " + 0);
                        bw.newLine();
                    }
                bw.newLine();
                Collections.sort(m3.facturi, new ComparatorFactura());
                for(Factura fg : m3.facturi){
                    bw.write(fg.denumire);
                    bw.newLine();
                    bw.newLine();
                    bw.write("Total " + df.format(fg.getTotalFaraTaxe()) + " " + df.format(fg.getTotalCuTaxe()));
                    bw.newLine();
                    bw.newLine();
                    for(String t : tari)
                        if(fg.getTotalTaraFaraTaxe(t) != 0){
                            bw.write(t + " " + df.format(fg.getTotalTaraFaraTaxe(t)) + " " + df.format(fg.getTotalTaraCuTaxe(t)));
                            bw.newLine();
                        }
                        else{
                            bw.write(t + " " + 0);
                            bw.newLine();
                        }
                    bw.newLine();
                }
            }
        
        //HyperMarket
        bw.write("HyperMarket");
        bw.newLine();
        for(Magazin m3 : gestiune.magazine)
            if(m3 instanceof HyperMarket){
                bw.write(m3.nume);
                bw.newLine();
                bw.newLine();
                bw.write("Total " + df.format(m3.getTotalFaraTaxe())+ " " + df.format(m3.getTotalCuTaxe()) + " " + df.format(m3.getTotalCuTaxeScutite()));
                bw.newLine();
                bw.newLine();
                bw.write("Tara");
                bw.newLine();
                for(String t : tari)
                    if(m3.getTotalTaraFaraTaxe(t) != 0){
                        bw.write(t + " " + df.format(m3.getTotalTaraFaraTaxe(t)) + " " + df.format(m3.getTotalTaraCuTaxe(t))+ " " + df.format(m3.getTotalTaraCuTaxeScutite(t)));
                        bw.newLine();
                    }
                    else{
                        bw.write(t + " " + 0);
                        bw.newLine();
                    }
                bw.newLine();
                Collections.sort(m3.facturi, new ComparatorFactura());
                for(Factura fg : m3.facturi){
                    bw.write(fg.denumire);
                    bw.newLine();
                    bw.newLine();
                    bw.write("Total " + df.format(fg.getTotalFaraTaxe()) + " " + df.format(fg.getTotalCuTaxe()));
                    bw.newLine();
                    bw.newLine();
                    for(String t : tari)
                        if(fg.getTotalTaraFaraTaxe(t) != 0){
                            bw.write(t + " " + df.format(fg.getTotalTaraFaraTaxe(t)) + " " + df.format(fg.getTotalTaraCuTaxe(t)));
                            bw.newLine();
                        }
                        else{
                            bw.write(t + " " + 0);
                            bw.newLine();
                        }
                    bw.newLine();
                }
            }
                
          bw.close(); 
    }
    
    public void scriereProduse(String numeFisier) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(numeFisier));
        bw.write("Produs Categorie");
        Vector<String> tari = this.getTari();
        Collections.sort(tari);
        DecimalFormat df = new DecimalFormat("#0.###");
        for(String s : tari)
            bw.write(" " + s);
        bw.newLine();
        Vector<Integer> scris = new Vector<Integer>();
        for(int i = 0; i < produse.size(); i++)
            scris.add(0);
        for(Produs p : produse){
            if(scris.get(produse.indexOf(p)) == 0){
                bw.write(p.getDenumire() + " " + p.getCategorie());
                
                for(String s : tari){
                    int ok = 0;
                    for(Produs p1 : produse){
                        if(p1.getTaraOrigine().equals(s) && p1.getDenumire().equals(p.getDenumire()) && scris.get(produse.indexOf(p1)) == 0 ){
                            bw.write(" " + df.format(p1.getPret()));
                            scris.set(produse.indexOf(p1), 1);
                            ok = 1;
                        }
                        
                    }
                    if(ok == 0)
                        bw.write(" " + 0);
                }
                
                bw.newLine();
            }
            
        }
        bw.close();
    }
    
    @Override
    public String toString(){
        String s = "";
        for(Produs p : produse)
            s = s + p.toString() + "\n";
        for(Magazin m : magazine)
            s = s + m.toString() + "\n";
        s = s + taxe.toString();
        return s;
    }
    
}
