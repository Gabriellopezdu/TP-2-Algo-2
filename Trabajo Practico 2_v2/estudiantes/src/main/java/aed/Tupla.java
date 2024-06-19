package aed;
import aed.TrieCarreras.nodoCarreras;

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
