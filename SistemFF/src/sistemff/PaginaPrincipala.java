
package sistemff;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class PaginaPrincipala{
    
    static JFrame jf;
    Buton b1, b2, b3, b4;
    JOptionPane logare;
    boolean logedIn = false;
    PaginaPrincipala p = this;
    String utilizator = "";
    PrimaPagina primaPagina;
    IncarcareFisiere incarcareFisiere;
    AdministrareProduse administrareProduse;
    JPanel imagine;
    Statistici statistici;
    boolean fisiereCitite = false;
    
    public PaginaPrincipala() throws IOException{
        jf = new JFrame("Sistem Facturi Fiscale");
        b1 = new Buton("Logare", 0, 0, 200, 60);
        b2 = new Buton("Incarcare fisiere", 0, 60, 200, 60);
        b3 = new Buton("Administrare produse", 0 , 120, 200, 60);
        b4 = new Buton("Statistici", 0, 180, 200, 60);
        imagine = new PanelImagine();
        
        jf.setSize(900,600);
        jf.setLayout(null);
        
        primaPagina = new PrimaPagina();
        primaPagina.setText("Bine ati venit! Pentru a continua, va rugam sa va logati!");
        incarcareFisiere = new IncarcareFisiere();
        incarcareFisiere.setVisible(false);
        statistici = new Statistici();
        statistici.setVisible(false);
        administrareProduse = new AdministrareProduse();
        administrareProduse.setVisible(false);
        
        jf.add(imagine);
        jf.add(primaPagina);
        jf.add(incarcareFisiere);
        jf.add(statistici);
        jf.add(administrareProduse);
        jf.add(b1);
        jf.add(b2);
        jf.add(b3);
        jf.add(b4);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void open(){
        this.newActionListenerB1();
        this.newActionListenerB2();
        this.newActionListenerB3();
        this.newActionListenerB4();
        jf.setVisible(true);
    }
    
    public void newActionListenerB1(){
        ActionListener al1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                p.initLogare();
                
            }
        };
        b1.addActionListener(al1);
    } 
    
    public void newActionListenerB2(){
        ActionListener al2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                if(!logedIn)
                    JOptionPane.showMessageDialog(jf, "Nu sunteti logat! Pentru a continua, va rugam sa va autentificati!");
                else{
                    incarcareFisiere.setVisible(true);
                    primaPagina.setVisible(false);
                    statistici.setVisible(false);
                    administrareProduse.setVisible(false);
                    String ss = "";
                    ss = ss + "Sunteti autentificat ca: " + utilizator;
                    incarcareFisiere.setText(ss);
                    jf.repaint();
                    jf.revalidate();
                }
                
            }
        };
        b2.addActionListener(al2);
    }
    
    public void newActionListenerB3(){
        ActionListener al3 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                if(!logedIn)
                    JOptionPane.showMessageDialog(jf, "Nu sunteti logat! Pentru a continua va rugam sa va autentificati!");
                else{
                    if(incarcareFisiere.produseCitite()){
                        administrareProduse.initAdminProd();
                        incarcareFisiere.setVisible(false);
                        primaPagina.setVisible(false);
                        statistici.setVisible(false);
                        administrareProduse.setVisible(true);
                        String ss = "";
                        ss = ss + "Sunteti autentificat ca: " + utilizator;
                        administrareProduse.setText(ss);
                        jf.repaint();
                        jf.revalidate();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Nu se poate accesa pagina! Nu a fost incarcat fisierul cu produse!");
                }
                    
            }
            
        };
        b3.addActionListener(al3);
    }
    
    public void newActionListenerB4(){
        ActionListener al4 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev){
                if(!logedIn)
                    JOptionPane.showMessageDialog(jf, "Nu sunteti logat! Pentru a continua va rugam sa va autentificati!");
                else{
                    if(incarcareFisiere.citite()){
                        statistici.initStatistici();
                        incarcareFisiere.setVisible(false);
                        primaPagina.setVisible(false);
                        statistici.setVisible(true);
                        administrareProduse.setVisible(false);
                        String ss = "";
                        ss = ss + "Sunteti autentificat ca: " + utilizator;
                        statistici.setText(ss);
                        jf.repaint();
                        jf.revalidate();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Nu se pot afisa statisticile! Nu au fost incarcate toate fisierele!");
                }
            }
        };
        b4.addActionListener(al4);
    }
    
    public  HashMap<String, String> readLogare ( String numeFisier) throws IOException {
        HashMap<String,String> logare = new HashMap<String, String>();
        BufferedReader br1 = new BufferedReader(new FileReader(numeFisier)); 
        String line; 
        while((line = br1.readLine()) != null ){
            StringTokenizer st1 = new StringTokenizer(line);
                while(st1.hasMoreTokens()) {
                    String nume = st1.nextToken();
                    String parola = st1.nextToken();
                    logare.put(nume, parola);
                }
        }
        return logare;
    }
     
    public void initLogare(){
        JTextField nume = new JTextField(10);
        JPasswordField parola = new JPasswordField(10);
        HashMap<String, String> np = new HashMap<String, String>();
        try {
            np = p.readLogare("logare.txt");
        }
        catch(IOException io){
            System.out.println("IOException");
        }

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Nume de utilizator:"));
        myPanel.add(nume);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Parola:"));
        myPanel.add(parola);
        int ok = 0;
        if(!logedIn)
            ok = 1;
        else{
            int result = JOptionPane.showConfirmDialog(null,"Doriti sa va delogati?", null, JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                logedIn = false;
                b1.changeText("Logare");
                primaPagina.setVisible(true);
                primaPagina.setText("Bine ati venit! Pentru a continua, va rugam sa va logati!");
                incarcareFisiere.setVisible(false);
                statistici.setVisible(false);
                administrareProduse.setVisible(false);
                jf.repaint();
                jf.revalidate();
            }
        }
        while(ok == 1){
            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                "Logare", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String n = nume.getText();
                String p = parola.getText();
                if(np.get(n) != null){
                    if(np.get(n).equals(p)){
                        logedIn = true;
                        b1.changeText("Delogare");
                        ok = 0;
                        utilizator = n;
                        String ss = "";
                        ss = ss + "Sunteti autentificat ca: " + utilizator;
                        primaPagina.setText(ss);
                        JOptionPane.showMessageDialog(jf, "Logare reusita!");
                        primaPagina.setVisible(true);
                        incarcareFisiere.setVisible(false);
                        statistici.setVisible(false);
                        administrareProduse.setVisible(false);
                        jf.repaint();
                        jf.revalidate();
                    }
                    else
                        JOptionPane.showMessageDialog(jf, "Nume de utilizator sau parola incorecte!");
                }
                else
                    JOptionPane.showMessageDialog(jf, "Nume de utilizator sau parola incorecte!");
            }
            if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION)
                ok = 0;
        }
    }
}
