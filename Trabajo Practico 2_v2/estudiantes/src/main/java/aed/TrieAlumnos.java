package aed;

import java.util.*;

public class TrieAlumnos {

    private nodoAlumnos raiz;
    private int         cantidadNodos;

    private class nodoAlumnos{
        private ArrayList<nodoAlumnos>  hijos;
        private boolean                 fin;
        private int                     cantidadMateriasInscripto;
    }

    public ArrayList<nodoAlumnos> nodoAlumnos(){
        return new ArrayList<>(256);
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
}
