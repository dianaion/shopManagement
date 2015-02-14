
package sistemff;

import java.util.*;

public class ComparatorMagazin implements Comparator<Magazin> {
    
    @Override
    public int compare(Magazin m1, Magazin m2){
        if(m1.getTotalCuTaxe() < m2.getTotalCuTaxe())
            return -1;
        else
            if(m1.getTotalCuTaxe() > m2.getTotalCuTaxe())
                return 1;
        return 0;
    }
    
}
