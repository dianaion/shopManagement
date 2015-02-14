
package sistemff;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

public class Statistici extends JPanel {
    
    JLabel jl;
    Gestiune g;
    JLabel jl1, jl2, jl3, jl4;
    String magazin;
    Double totalTaxe = 0.0 , totalFaraTaxe, totalTaxeScutite; 
    
    public Statistici(){
    }
    
    public void initStatistici(){
        g = Gestiune.getInstance();
        this.removeAll();
        jl = new JLabel();
        jl.setBounds(5, 0, 700, 20);
        for(Magazin m : g.magazine)
            if(m.getTotalCuTaxe() > totalTaxe){
                totalTaxe = m.getTotalCuTaxe();
                totalFaraTaxe = m.getTotalFaraTaxe();
                totalTaxeScutite = m.getTotalCuTaxeScutite();
                magazin = m.nume;
            }
        DecimalFormat df = new DecimalFormat("#0.###");
        
        
        //magazinul cu cele mai mari vanzari
        jl1 = new JLabel();
        jl2 = new JLabel();
        jl3 = new JLabel();
        jl4 = new JLabel();
        String ss = "";
        ss = ss + "Magazinul cu cele mai mari vanzari: " + magazin;
        jl1.setText(ss);
        String ss1 = "";
        ss1 = ss1 + "Total fara taxe: " + df.format(totalFaraTaxe);
        jl2.setText(ss1);
        String ss2 = "";
        ss2 = ss2 + "Total cu taxe: " + df.format(totalTaxe);
        jl3.setText(ss2);
        String ss3 = "";
        ss3 = ss3 + "Total cu taxe scutite: " + df.format(totalTaxeScutite);
        jl4.setText(ss3);
        jl1.setBounds(5, 25, 500, 15);
        jl2.setBounds(25, 40, 500, 15);
        jl3.setBounds(25, 55, 500, 15);
        jl4.setBounds(25, 70, 500, 15);
        this.add(jl);
        this.add(jl1);
        this.add(jl2);
        this.add(jl3);
        this.add(jl4);
       
        //magazinul cu cele mai mari vanzari pt fiecare tara in parte
        Vector<String> tari = g.getTari();
        Collections.sort(tari);
        int i = 1;
        for(String t : tari){
            totalTaxe = 0.0;
            for(Magazin m : g.magazine)
                if(m.getTotalTaraCuTaxe(t) > totalTaxe){
                    magazin = m.nume;
                    totalTaxe = m.getTotalTaraCuTaxe(t);
                    totalFaraTaxe = m.getTotalTaraFaraTaxe(t);
                    totalTaxeScutite = m.getTotalTaraCuTaxeScutite(t);
                }
            if(totalTaxe != 0){
                JLabel jl5 = new JLabel();
                JLabel jl6 = new JLabel();
                JLabel jl7 = new JLabel();
                JLabel jl8 = new JLabel();
                ss = "";
                ss = ss + "Magazinul cu cele mai mari vanzari pentru tara " + t + " : " + magazin;
                jl5.setText(ss);
                ss1 = "";
                ss1 = ss1 + "Total fara taxe: " + df.format(totalFaraTaxe);
                jl6.setText(ss1);
                ss2 = "";
                ss2 = ss2 + "Total cu taxe: " + df.format(totalTaxe);
                jl7.setText(ss2);
                ss3 = "";
                ss3 = ss3 + "Total cu taxe scutite: " + df.format(totalTaxeScutite);
                jl8.setText(ss3);
                jl5.setBounds(5, 70 + 15 * i, 500, 15);
                jl6.setBounds(25, 70 + 15 * (i + 1), 500, 15);
                jl7.setBounds(25, 70 + 15 * (i + 2), 500, 15);
                jl8.setBounds(25, 70 + 15 * (i + 3), 500, 15);
                this.add(jl5);
                this.add(jl6);
                this.add(jl7);
                this.add(jl8);
                i += 4;    
            }
           
        } 
        
        //magazinul cu vanzarile cele mai mari din fiecare categorie
        int nr = (i - 1) * 15;
        Vector<String> categorii = g.getCategorii();
        Collections.sort(categorii);
        i = 1;
        for(String c : categorii){
            totalTaxe = 0.0;
            for(Magazin m : g.magazine)
                if(m.getTotalCategorieCuTaxe(c) > totalTaxe){
                    magazin = m.nume;
                    totalTaxe = m.getTotalCategorieCuTaxe(c);
                    totalFaraTaxe = m.getTotalCategorieFaraTaxe(c);
                    totalTaxeScutite = m.getTotalCategorieCuTaxeScutite(c);
                }
            if(totalTaxe != 0){
                JLabel jl5 = new JLabel();
                JLabel jl6 = new JLabel();
                JLabel jl7 = new JLabel();
                JLabel jl8 = new JLabel();
                ss = "";
                ss = ss + "Magazinul cu cele mai mari vanzari pentru categoria " + c + " : " + magazin;
                jl5.setText(ss);
                ss1 = "";
                ss1 = ss1 + "Total fara taxe: " + df.format(totalFaraTaxe);
                jl6.setText(ss1);
                ss2 = "";
                ss2 = ss2 + "Total cu taxe: " + df.format(totalTaxe);
                jl7.setText(ss2);
                ss3 = "";
                ss3 = ss3 + "Total cu taxe scutite: " + df.format(totalTaxeScutite);
                jl8.setText(ss3);
                jl5.setBounds(5, 70 + nr + 15 * i, 500, 15);
                jl6.setBounds(25, 70 + nr + 15 * (i + 1), 500, 15);
                jl7.setBounds(25, 70 + nr + 15 * (i + 2), 500, 15);
                jl8.setBounds(25, 70 + nr + 15 * (i + 3), 500, 15);
                this.add(jl5);
                this.add(jl6);
                this.add(jl7);
                this.add(jl8);
                i += 4;
            }
               
        } 
        
        nr += (i - 1) * 15;
        double total = 0.0;
        String nume = "";
        for(Magazin m : g.magazine)
            for(Factura fa : m.facturi)
                if(fa.getTotalFaraTaxe() > total){
                    total = fa.getTotalFaraTaxe();
                    nume = fa.denumire;
                    magazin = m.nume;
                }
        JLabel jl5 = new JLabel();
        ss = "";
        ss = ss + "Factura cu suma total cea mai mare este " + nume + " din magazinul " + magazin + " : " + df.format(total);
        jl5.setText(ss);
        jl5.setBounds(5, 70 + nr + 15, 500, 15);
        this.add(jl5);
        this.setLayout(null);
        this.setBounds(200,0,900,600);
        this.setVisible(true);
    }
    
    public void setText(String text){
        jl.setText(text);
        this.repaint();
        this.revalidate();
    }
}
