package aed;

import java.util.ArrayList;

import sun.net.www.protocol.http.spnego.NegotiatorImpl;

public class TrieMaterias{
    public NodoMaterias raiz;
    private NodoMaterias siguiente;
    private Materias materiasDeCarrera;

    public class NodoMaterias{
        ArrayList<NodoMaterias> hijosmaterias; // Array que va a tener los hijos que diferencian materias
        Character valorActual;
        public NodoMaterias(){ // constructor del nodo 
            //agregar un atributo de tipo materia para poder apuntar con el ultimo nodo
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
        actual.materiasDeCarrera = new Materias(); //tengo que hacer que el ultimo nodo apunte a la instancia de materia que esta haciendo fran  
    }

    public void eliminar(String palabra){
        NodoMaterias actual = raiz;
        if (pertenece(palabra)){
            for (int i = 0; i < palabra.length(); i++){ 
                char caracter   = palabra.charAt(i);    //luego, 
                int indice      = (int) caracter;
                while(i != (palabra.length() -1)){  // i llega hasta el anteultimo y actualiza actual a el ultimo
                    actual = actual.hijosmaterias.get(indice);  
                actual.hijosmaterias = null; //luego, convierte la lista que buscaria el siguiente en null 
            }
            }
        }
    }
}