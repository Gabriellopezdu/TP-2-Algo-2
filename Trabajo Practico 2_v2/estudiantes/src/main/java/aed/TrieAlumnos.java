package aed;

import java.util.*;

public class TrieAlumnos {

    private nodoAlumnos raiz;
    private int         cantidadNodos;

    private class nodoAlumnos{
        private ArrayList<nodoAlumnos>  hijos;
        private boolean                 fin;
        private int                     cantidadMateriasInscripto;

        public nodoAlumnos(){
            this.hijos                      = new ArrayList<nodoAlumnos> (256);
            this.fin                        = false;
            this.cantidadMateriasInscripto  = 0;
        }
    }


    public TrieAlumnos(){
        this.cantidadNodos = 0;
        this.raiz = new nodoAlumnos();
    }

    public boolean buscar (String palabra) {
        nodoAlumnos nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter   = palabra.charAt(i);
            int indice      = (int) caracter;

            if(nodoActual.hijos.get(indice) == null){
                return false;
            }
            nodoActual = nodoActual.hijos.get(indice);
        }
        return (nodoActual != null && (nodoActual.fin != false));
    }

    public void agregar(String palabra){
        nodoAlumnos actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter   = palabra.charAt(i); 
            int indice      = (int) caracter;
            
            if (actual.hijos.get(indice) != null){
                actual = actual.hijos.get(indice);
            }else{
                nodoAlumnos nuevoNodo  = new nodoAlumnos();
                actual.hijos.set(indice, nuevoNodo);
                actual = nuevoNodo;
                cantidadNodos++;
            }
        }
        actual.fin = true;
    }

    public int tamañoTrie(){
        return cantidadNodos;
    }
}
