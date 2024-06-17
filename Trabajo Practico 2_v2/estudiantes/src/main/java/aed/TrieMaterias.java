package aed;

import java.util.ArrayList;

public class TrieMaterias{
    public NodoMaterias raiz;

    public class NodoMaterias{
        ArrayList<NodoMaterias> hijosmaterias; // Array que va a tener los hijos que diferencian materias
        Character valorActual;
        Materia instanciademateria;

        public NodoMaterias(){ // constructor del nodo 
            this.hijosmaterias = new ArrayList<NodoMaterias>(256); //le declaramos el array con hijos
        }

    }
    // Constructor del trie
    public TrieMaterias(){
        this.raiz = new NodoMaterias();
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
        return actual != null && (actual.instanciademateria != null);// vemos que el ultimo nodo no sea null y apunte a una intancia de materia
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
        // nuevaMateria va a ser la que va a apuntar 
        actual.instanciademateria = NuevaMateria; // el ultimo nodo apunta a 
    }

    public void eliminar(String palabra){
        NodoMaterias actual = raiz;
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