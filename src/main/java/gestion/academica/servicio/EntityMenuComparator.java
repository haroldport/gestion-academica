package gestion.academica.servicio;

import gestion.academica.modelo.AccesoRol;

import java.util.Comparator;

/**
 *
 * @author manu
 */
public class EntityMenuComparator implements Comparator<AccesoRol> {

    @Override
    public int compare(AccesoRol o1, AccesoRol o2) {
        return o1.getAcceso().getOrden().compareTo(o2.getAcceso().getOrden());
    }
    
}
