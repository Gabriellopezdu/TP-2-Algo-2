package aed;

import java.util.*;

public class TrieCarreras<T> {

    private nodoCarreras    raiz;
    private int             cantidadNodos;

    private class nodoCarreras{
        ArrayList<nodoCarreras> hijos;
        Materia                 materiaDeCarrera;
    }

    public ArrayList<nodoCarreras> nodoCarreras(int espacio){
        return new ArrayList<>(256);
    }

    public TrieCarreras(){
        this.raiz           = new nodoCarreras();
        this.cantidadNodos  = 0;
    }
}
