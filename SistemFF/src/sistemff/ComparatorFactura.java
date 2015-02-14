
package sistemff;

import java.util.*;

public class ComparatorFactura implements Comparator<Factura>{
    
    @Override
    public int compare(Factura f1, Factura f2){
        if(f1.getTotalCuTaxe() < f2.getTotalCuTaxe())
            return -1;
        else
            if(f1.getTotalCuTaxe() > f2.getTotalCuTaxe())
                return 1;
        return 0;
    }
    
}
