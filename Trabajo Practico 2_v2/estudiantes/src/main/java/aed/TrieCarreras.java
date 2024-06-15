package aed;

import java.util.ArrayList;

public class TrieCarreras<T> {

    private nodoCarreras    raiz;
    private int cantidadNodos;

    private class nodoCarreras{
        ArrayList<nodoCarreras> hijos;
        TrieMaterias            materiasDeCarrera;
    }

    public ArrayList<nodoCarreras> nodoCarreras(){
        return new ArrayList<>(256);
    }

    public TrieCarreras(){
        this.raiz           = new nodoCarreras();
        this.cantidadNodos  = 0;
    }

    public boolean buscar (String palabra) {
        nodoCarreras nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {                            //recorre la palabra por caracteres
            char caracter   = palabra.charAt(i);                                //charAT(i) devuelve el char en la posición i
            int indice      = (int) caracter;                                   //(int) char devuelve el código ASCII del char

            if(nodoActual.hijos.get(indice) == null){                           //get(indice) devuelve el hijo en la posición indice
                return false;                                                   //si el array en el indice es null, la letra no existe
            }
            nodoActual = nodoActual.hijos.get(indice);                          //caso contrario, nodo actual pasa a ser el nodo en 
        }                                                                       //la posición "indice" del array (la siguiente letra)
        return nodoActual != null && (nodoActual.materiasDeCarrera != null);    //materiasDeCarrera hace las de fin de palabra
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
        if (nodoActual.materiasDeCarrera == null){
            TrieMaterias nuevoTrie = new TrieMaterias();
            nodoActual.materiasDeCarrera = nuevoTrie;
        }
         
    }
}

