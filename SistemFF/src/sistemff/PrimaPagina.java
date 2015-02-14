
package sistemff;

import java.awt.*;
import javax.swing.*;

public class PrimaPagina extends JPanel {
    
    JLabel jl;
    
    public PrimaPagina(){
        jl = new JLabel();
        jl.setBounds(5, 0, 500, 20);
        this.setLayout(null);
        this.setBounds(200,0,600,900);
        this.add(jl);
        this.setVisible(true);
    }
    
    public void setText(String text){
        jl.setText(text);
        this.repaint();
        this.revalidate();
    }

}
