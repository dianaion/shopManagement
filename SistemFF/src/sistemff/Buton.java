
package sistemff;

import javax.swing.*;

public class Buton extends JButton{
    
    public Buton(String nume, int a, int b, int c, int d){
        super(nume);
        this.setBounds(a,b,c,d);
    }
    
    public void changeText(String s){
        this.setText(s);
        this.repaint();
        this.revalidate();
    }
    
}
