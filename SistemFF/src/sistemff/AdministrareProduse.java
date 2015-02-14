
package sistemff;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.*;

public class AdministrareProduse extends JPanel{
    
    JTable tabel, tabel1;
    Gestiune g;
    Buton b1, b2, b3, b4, b5, b6, b7;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5;
    JLabel jl1, jl2, jl3, jl4;
    JLabel jl, jl5;
    int row = -1;
    AdministrareProduse ap = this;
    int size = 0;
    
    public AdministrareProduse(){
        g = Gestiune.getInstance();
    }
    
    public void initAdminProd(){

        this.removeAll();
        jl = new JLabel();
        jl.setBounds(5, 0, 700, 20);
        b1 = new Buton("Ordonare dupa denumire",400, 20, 200, 40);
        b2 = new Buton("Ordonare dupa tara", 400, 65, 200, 40);
        b3 = new Buton("Sterge", 400, 110, 200, 40);
        b4 = new Buton("Adaugare/editare produs", 400, 155, 200, 40);
        jl5 = new JLabel("Cautare produse");
        jl5.setBounds(400, 240, 200, 40);
        jl5.setHorizontalAlignment(JLabel.CENTER);
        jtf5 = new JTextField();
        jtf5.setBounds(400, 270, 200, 20);
        b5 = new Buton("Denumire",310, 300, 120, 20);
        b6 = new Buton("Tara origine", 430, 300, 120, 20);
        b7 = new Buton("Categorie", 550, 300, 120, 20);
        
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("Denumire");
        model1.addColumn("Tara origine");
        model1.addColumn("Categorie");
        model1.addColumn("Pret");
        tabel1 = new JTable(model1);
        JScrollPane sp1 = new JScrollPane(tabel1);
        tabel1.setColumnSelectionAllowed( true );
        tabel1.setCellSelectionEnabled( true );
        sp1.setBounds(310,330,370,100);
        
        b5.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                Object produse[][] = new Object[4][100];
                int i = 0;
                for(Produs p : g.produse)
                    if(p.getDenumire().equals(jtf5.getText()) && p.getPret() != 0){
                        produse[0][i] = p.getDenumire();
                        produse[1][i] = p.getTaraOrigine();
                        produse[2][i] = p.getCategorie();
                        produse[3][i] = p.getPret();
                        i++;
                    }
                if(i == 0)
                    JOptionPane.showMessageDialog(null, "Nu a fost gasit nici un produs!");
                int nrRanduri = model1.getRowCount();
                for (int k = nrRanduri - 1; k >= 0; k--) {
                    model1.removeRow(k);
                }
                Object prod[]=new Object[4];
                for (int k = 0; k < i; k++){
                    for (int j = 0; j < 4; j++){
                        prod[j] = produse[j][k];
                }
                model1.addRow(prod);
                }
            }
        });
        
        b6.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                Object produse[][] = new Object[4][100];
                int i = 0;
                for(Produs p : g.produse)
                    if(p.getTaraOrigine().equals(jtf5.getText()) && p.getPret() != 0){
                        produse[0][i] = p.getDenumire();
                        produse[1][i] = p.getTaraOrigine();
                        produse[2][i] = p.getCategorie();
                        produse[3][i] = p.getPret();
                        i++;
                    }
                if(i == 0)
                    JOptionPane.showMessageDialog(null, "Nu a fost gasit nici un produs!");
                int nrRanduri = model1.getRowCount();
                for (int k = nrRanduri - 1; k >= 0; k--) {
                    model1.removeRow(k);
                }
                Object prod[]=new Object[4];
                for (int k = 0; k < i; k++){
                    for (int j = 0; j < 4; j++){
                        prod[j] = produse[j][k];
                }
                model1.addRow(prod);
                }
            }
        });
        
        b7.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                Object produse[][] = new Object[4][100];
                int i = 0;
                for(Produs p : g.produse)
                    if(p.getCategorie().equals(jtf5.getText()) && p.getPret() != 0){
                        produse[0][i] = p.getDenumire();
                        produse[1][i] = p.getTaraOrigine();
                        produse[2][i] = p.getCategorie();
                        produse[3][i] = p.getPret();
                        i++;
                    }
                if(i == 0)
                    JOptionPane.showMessageDialog(null, "Nu a fost gasit nici un produs!");
                int nrRanduri = model1.getRowCount();
                for (int k = nrRanduri - 1; k >= 0; k--) {
                    model1.removeRow(k);
                }
                Object prod[]=new Object[4];
                for (int k = 0; k < i; k++){
                    for (int j = 0; j < 4; j++){
                        prod[j] = produse[j][k];
                }
                model1.addRow(prod);
                }
            }
        });
        
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(jtf5);
        this.add(jl5);
        this.add(jl);
        
        jl1 = new JLabel();
        jl1.setText("Denumire");
        jl1.setBounds(310, 200, 90, 20);
        jl2 = new JLabel();
        jl2.setText("Tara origine");
        jl2.setBounds(400, 200, 90, 20);
        jl3 = new JLabel();
        jl3.setText("Categorie");
        jl3.setBounds(490, 200, 90, 20);
        jl4 = new JLabel();
        jl4.setText("Pret");
        jl4.setBounds(580, 200, 90, 20);
        
        jtf1 = new JTextField();
        jtf1.setBounds(310, 220, 90, 20);
        jtf2 = new JTextField();
        jtf2.setBounds(400, 220, 90, 20);
        jtf3 = new JTextField();
        jtf3.setBounds(490, 220, 90, 20);
        jtf4 = new JTextField();
        jtf4.setBounds(580, 220, 90, 20);
        
        this.add(jl1);
        this.add(jl2);
        this.add(jl3);
        this.add(jl4);
        this.add(jtf1);
        this.add(jtf2);
        this.add(jtf3);
        this.add(jtf4);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        
        DefaultTableModel model = new DefaultTableModel();
	model.addColumn("Denumire");
	model.addColumn("Tara origine");
	model.addColumn("Categorie");
        model.addColumn("Pret");
        tabel = new JTable(model);
        JScrollPane sp = new JScrollPane(tabel);
        tabel.setColumnSelectionAllowed( true );
        tabel.setCellSelectionEnabled( true );
        sp.setBounds(5,20,300,300);
        Object produse[][] = new Object[4][100];
        int i = 0;
        for(Produs p : g.produse){
            if(p.getPret() != 0){
                produse[0][i] = p.getDenumire();
                produse[1][i] = p.getTaraOrigine();
                produse[2][i] = p.getCategorie();
                produse[3][i] = p.getPret();
                i++;
            }
        }
        Object prod[]=new Object[4];
        size = 0;
	for (int k = 0; k < i; k++){
            for (int j = 0; j < 4; j++){
		prod[j] = produse[j][k];
            }
            model.addRow(prod);
            size++;
	}
        
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                Object produse1[][] = new Object[4][100];
                int k = 0;
                Collections.sort(g.produse, new ComparatorProduseAlfabetic());
                for(Produs p : g.produse){
                    if(p.getPret() != 0){
                        produse1[0][k] = p.getDenumire();
                        produse1[1][k] = p.getTaraOrigine();
                        produse1[2][k] = p.getCategorie();
                        produse1[3][k] = p.getPret();
                        k++;
                    }
                }
                int nrRanduri = model.getRowCount();
                for (int p = nrRanduri - 1; p >= 0; p--) {
                    model.removeRow(p);
                }
                size = 0;
                Object prod1[] = new Object[4];
                for (int j = 0; j < k; j++){
                    for (int p = 0; p < 4; p++){
                        prod1[p]=produse1[p][j];
                    }
                    size++;
                    model.addRow(prod1);
                }
            }
        });
        
        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                Object produse1[][] = new Object[4][100];
                int k = 0;
                Collections.sort(g.produse, new ComparatorProduseAlfabeticTara());
                for(Produs p : g.produse){
                    if(p.getPret() != 0){
                        produse1[0][k] = p.getDenumire();
                        produse1[1][k] = p.getTaraOrigine();
                        produse1[2][k] = p.getCategorie();
                        produse1[3][k] = p.getPret();
                        k++;
                    }
                }
                int nrRanduri = model.getRowCount();
                for (int p = nrRanduri - 1; p >= 0; p--) {
                    model.removeRow(p);
                }
                size = 0;
                Object prod1[] = new Object[4];
                for (int j = 0; j < k; j++){
                    for (int p = 0; p < 4; p++){
                        prod1[p] = produse1[p][j];
                    }
                    model.addRow(prod1);
                    size++;
                }
            }
        });
        
        b3.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ev){
                if(row == -1)
                    JOptionPane.showMessageDialog(null, "Nu a fost selectat nici un produs!");
                else{
                    Produs p = new Produs();
                    p.setDenumire((String)model.getValueAt(row, 0));
                    p.setTaraOrigine((String)model.getValueAt(row, 1));
                    p.setCategorie((String)model.getValueAt(row, 2));
                    p.setPret((Double)model.getValueAt(row, 3));
                    int count = -1;
                    for(Produs p1 : g.produse){
                        count++;
                        if(p1.getCategorie().equalsIgnoreCase(p.getCategorie()) && p1.getDenumire().equals(p.getDenumire()) && p1.getTaraOrigine().equals(p.getTaraOrigine()))
                            break;
                    }
                    g.produse.remove(count);
                    model.removeRow(row);
                    size--;
                    JOptionPane.showMessageDialog(null, "Atentie! Reincarcati fisierul cu facturile pentru a reactualiza statisticile!");
                    try{
                        g.scriereProduse("produse2.txt");
                    }
                    catch(IOException ex){
                        System.out.println("Exceptie la scriere lui produse2");
                    }
                }
            }
        });
        
        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                row = tabel.rowAtPoint(ev.getPoint());
            }
        });
        
        b4.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ev){
                Produs p = new Produs();
                Object produse1[] = new Object[4];
                p.setDenumire(jtf1.getText());
                produse1[0] = jtf1.getText();
                p.setTaraOrigine(jtf2.getText());
                produse1[1] = jtf2.getText();
                p.setCategorie(jtf3.getText());
                produse1[2] = jtf3.getText();
                p.setPret(new Double(jtf4.getText()));
                produse1[3] = jtf4.getText();
                int ok = 1;
                int count = -1;
                for(Produs p1 : g.produse ){
                    count++;
                    if(p.getDenumire().equals(p1.getDenumire()) && p.getTaraOrigine().equals(p1.getTaraOrigine()) && p.getCategorie().equals(p1.getCategorie())){
                        ok = 0;
                        int result = JOptionPane.showConfirmDialog(null,"Produsul exista! Doriti sa il editati?", null, JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION){
                            model.removeRow(count);
                            model.insertRow(count, produse1);
                            g.produse.remove(count);
                            g.produse.add(count, p);
                        }
                        break;
                    }
                }
                if(ok == 1){
                    g.produse.add(p);
                    model.addRow(produse1);
                    size++;
                }
                try{
                    g.scriereProduse("produse2.txt");
                }
                catch(IOException ex){
                    System.out.println("Exceptie la scriere lui produse2");
                }
           }
        });
        
        this.setLayout(null);
        this.setBounds(200,0,900,600);
        this.setVisible(true);
        this.add(sp);
        this.add(sp1);
    }
    
    public void setText(String text){
        jl.setText(text);
        this.repaint();
        this.revalidate();
    }
   
}
