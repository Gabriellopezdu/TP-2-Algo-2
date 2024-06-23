package aed;
import aed.TrieCarreras.nodoCarreras;

/* 
 * El invariante de representación de la clase Tupla es que tanto v1 como v2 no pueden ser 
 * null, y que v2 debe pertenecer al TrieMaterias asociado al atributo materiasDeCarrera
 * del nodoCarrera en v1.
 */

public class Tupla<T1, T2> { // creamos el tipo tupla para poder 2 meter cosas en
    // la lista enlazada
    private nodoCarreras v1;
    private String v2;

    public Tupla(nodoCarreras elem, String elem2) {
        v1 = elem;
        v2 = elem2;
    }

    public nodoCarreras t1() {
        return v1;
    }

    public String t2() {
        return v2;
    }
}
