
package sistemff;

import java.io.*;
import java.util.*;

public class SistemFF {
    
    public static void main(String[] args) throws IOException{
        Gestiune g = Gestiune.getInstance();
        Vector<String> tari = g.readProduse("produse.txt");
        g.readTaxe("taxe.txt");
        g.readFacturi("facturi.txt");
        g.afisare("out.txt", tari);

        PaginaPrincipala pp = new PaginaPrincipala();
        pp.open();
    }
    
}
