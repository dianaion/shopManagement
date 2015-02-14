
package sistemff;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class IncarcareFisiere extends JPanel {
    
    JLabel jl;
    Buton b1, b2, b3, b4;
    Gestiune g;
    JFileChooser jfc1, jfc2, jfc3, jfc4;
    String fisierProduse, fisierTaxe, fisierFacturi;
    int ok1 = 0, ok2 = 0, ok3 = 0;
    Vector<String> tari;
    
    public IncarcareFisiere() throws IOException{
        g = Gestiune.getInstance();
        jl = new JLabel();
        jl.setBounds(5, 0, 500, 20);
        b1 = new Buton("Incarcare fisier produse", 5, 80, 200, 80 );
        b2 = new Buton("Incarcare fisier taxe", 5, 165, 200, 80);
        b3 = new Buton("Incarcare fisier facturi", 5, 250, 200, 80);
        b4 = new Buton("Creare fisier de iesire", 5, 335, 200, 80);
        b1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ev){
                jfc1 = new JFileChooser();
                int result = jfc1.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    fisierProduse = jfc1.getSelectedFile().getName();
                    int ok = 0;
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(fisierProduse));
                        String linie = br.readLine();
                        StringTokenizer st = new StringTokenizer(linie, " ");
                        String token = st.nextToken();
                        if(token.equals("Produs"))
                            ok = 1;
                        br.close();
                    }
                    catch(IOException ex){
                        System.out.println("IOException1");
                    }
                    if(ok == 1){
                        try{
                            tari = g.readProduse(fisierProduse);
                            ok1 = 1;
                        }
                        catch(IOException ex){
                            System.out.println("IOException");
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Fisierul nu are formatul dorit! Incarcati alt fisier!");
                }
            }
        });
        
        b2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ev){
                jfc1 = new JFileChooser();
                int result = jfc1.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    fisierTaxe = jfc1.getSelectedFile().getName();
                    int ok = 0;
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(fisierTaxe));
                        String linie = br.readLine();
                        StringTokenizer st = new StringTokenizer(linie, " ");
                        String token = st.nextToken();
                        if(token.contains("Categorie"))
                            ok = 1;
                        br.close();
                    }
                    catch(IOException ex){
                        System.out.println("IOException1");
                    }
                    if(ok == 1){
                        try{
                            g.readTaxe(fisierTaxe);
                            ok2 = 1;
                        }
                        catch(IOException ex){
                            System.out.println("IOException");
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Fisirul nu are formatul dorit! Incarcati alt fisier!");
                }
            }
        });
        
        b3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ev){
                if(ok1 == 1 && ok2 == 1){
                jfc1 = new JFileChooser();
                int result = jfc1.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    fisierFacturi = jfc1.getSelectedFile().getName();
                    
                    int ok = 0;
                    try{
                        BufferedReader br = new BufferedReader(new FileReader(fisierFacturi));
                        String linie = br.readLine();
                        StringTokenizer st = new StringTokenizer(linie, " :");
                        String token = st.nextToken();
                        if(token.equals("Magazin"))
                            ok = 1;
                        br.close();
                    }
                    catch(IOException ex){
                        System.out.println("IOException1");
                    }
                    if(ok == 1){
                        try{
                            g.readFacturi(fisierFacturi);
                            ok3 = 1;
                        }
                        catch(IOException ex){
                            System.out.println("IOException");
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Fisierul nu are formatul dorit! Incarcati alt fisier!");
                }
                }
                else
                    JOptionPane.showMessageDialog(null, "Pentru a incarca facturile, incarcati mai intai produsele si taxele!");    
            }
        });
        
        b4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ev){
                if ( ok1 == 1 && ok2 == 1 && ok3 == 1){
                    try{
                        g.afisare("out.txt", tari);
                        JOptionPane.showMessageDialog(null, "Fisier creat cu succes!");
                    }
                    catch(IOException ex){
                        System.out.println("IOException");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nu ati selectat toate fisierele!");
                }
            }
        });

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(jl);
        this.setLayout(null);
        this.setBounds(200,0,600,900);
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }
    
     public void setText(String text){
        jl.setText(text);
        this.repaint();
        this.revalidate();
    }
     
    public boolean citite(){
        if(ok1 == 1 && ok2 == 1 && ok3 == 1)
            return true;
        return false;
    }
    
    public boolean produseCitite(){
        if(ok1 == 1)
            return true;
        return false;
    }
    
}
