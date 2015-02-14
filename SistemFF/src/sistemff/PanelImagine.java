
package sistemff;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class PanelImagine extends JPanel {
    
    public PanelImagine() throws IOException{
        this.setLayout(new FlowLayout());
        BufferedImage bi = ImageIO.read(new File("supermarket.jpg"));
        ImageIcon image = new ImageIcon(bi);
        JLabel j1 = new JLabel(image);
        this.add(j1);
        this.setBounds(0,240,200,900);
    }
    
}
