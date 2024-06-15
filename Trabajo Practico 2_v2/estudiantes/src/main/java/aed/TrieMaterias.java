package aed;

import java.util.ArrayList;

import sun.net.www.protocol.http.spnego.NegotiatorImpl;

public class TrieMaterias{
    private NodoMaterias raiz;
    private NodoMaterias siguiente;
    private Materias materiasDeCarrera;

    private class NodoMaterias{
        ArrayList<NodoMaterias> hijosmaterias; // Array que va a tener los hijos que diferencian materias
        ArrayList<NodoMaterias> valorActual;
        public NodoMaterias(){ // constructor del nodo 
            this.valorActual = new ArrayList<NodoMaterias>(256);
            this.hijosmaterias = new ArrayList<NodoMaterias>(256); //le declaramos el array con hijos
        }

    }
    // Constructor del trie
    public TrieMaterias(){
        this.raiz = new NodoMaterias();
        this.siguiente = new NodoMaterias();
        this.materiasDeCarrera = new Materias();
    }
    
    //nos fijamos si existe la palabra 
    public boolean pertenece (String palabra) {
        NodoMaterias actual = raiz; //accedemos a la raiz del trie
        for (int i = 0; i < palabra.length(); i++) { // 
            char caracter   = palabra.charAt(i);
            int indice = (int) caracter;
            
            if(actual.hijosmaterias.get(indice) == null){
                return false;
            }
            actual = actual.hijosmaterias.get(indice);
        }
        return actual != null && (materiasDeCarrera != null);
    }


    public void agregar(String palabra){
        NodoMaterias actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter   = palabra.charAt(i);
            int indice      = (int) caracter;
            
            if (actual.hijosmaterias.get(indice) != null){
                actual = actual.hijosmaterias.get(indice);
            }else{
                NodoMaterias nuevoNodo  = new NodoMaterias();
                actual.hijosmaterias.set(indice, nuevoNodo);
                actual = nuevoNodo;
            }
        }
    }

    public void eliminar(String palabra){
        NodoMaterias actual = raiz;
        if (pertenece(palabra)){
            for (int i = 0; i < palabra.length(); i++){
                
            }
        }
    }

}