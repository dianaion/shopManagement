
package sistemff;

import java.util.*;

public class ComparatorProduseAlfabetic implements Comparator<Produs>{
    
    @Override
    public int compare(Produs p1, Produs p2){
        if(p1.getDenumire().compareTo(p2.getDenumire()) > 0)
            return 1;
        if(p1.getDenumire().compareTo(p2.getDenumire()) < 0)
            return -1;
        if(p1.getDenumire().equals(p2.getDenumire())){
            if(p1.getTaraOrigine().compareTo(p2.getTaraOrigine()) > 0)
                return 1;
            if(p1.getTaraOrigine().compareTo(p2.getTaraOrigine()) < 0)
                return -1;
            if(p1.getTaraOrigine().equals(p2.getTaraOrigine())){
                if(p1.getCategorie().compareTo(p2.getCategorie()) > 0)
                    return 1;
                if(p1.getCategorie().compareTo(p2.getCategorie()) < 0)
                    return -1;
                if(p1.getCategorie().equals(p2.getCategorie())){
                    if(p1.getPret() > p2.getPret())
                        return 1;
                    if(p1.getPret() < p2.getPret())
                        return -1;
                }
            }
        }
        return 0;
    }
    
}
