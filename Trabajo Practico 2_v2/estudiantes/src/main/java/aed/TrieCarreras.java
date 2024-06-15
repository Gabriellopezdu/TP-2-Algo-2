package aed;

import java.util.ArrayList;

public class TrieCarreras<T> {

    private nodoCarreras    raiz;
    private int             cantidadNodos;

    private class nodoCarreras{
        ArrayList<nodoCarreras> hijos;
        Materias                materiasDeCarrera;
    }

    public ArrayList<nodoCarreras> nodoCarreras(int espacio){
        return new ArrayList<>(256);
    }

    public TrieCarreras(){
        this.raiz           = new nodoCarreras();
        this.cantidadNodos  = 0;
    }

    public boolean buscar (String palabra) {
        nodoCarreras nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter   = palabra.charAt(i);            //charAT(i) devuelve el char en la posición i
            int indice      = (int) caracter;
            
            if(nodoActual.hijos.get(indice) == null){
                return false;
            }
            nodoActual = nodoActual.hijos.get(indice);      //get(indice) devuelve el hijo en la posición indice
        }                                                   //en este caso, es el nodo en la posición indice del array
        return nodoActual != null && (nodoActual.materiasDeCarrera != null);
    }

    public void agregar (String palabra) {
        nodoCarreras nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter   = palabra.charAt(i);
            int indice      = (int) caracter;
            
            if (nodoActual.hijos.get(indice) != null){
                nodoActual = nodoActual.hijos.get(indice);
            }else{
                nodoCarreras nuevoNodo  = new nodoCarreras();
                nodoActual.hijos.set(indice, nuevoNodo);
                nodoActual              = nuevoNodo;
            }
        
        }
    }
}

