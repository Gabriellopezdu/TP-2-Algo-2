package aed;

import java.util.ArrayList;

import aed.TrieMaterias.NodoMaterias;

public class TrieCarreras<T> {

    private nodoCarreras    raiz;
    private int             cantidadNodos;

    public class nodoCarreras{
        ArrayList<nodoCarreras> hijos;
        Character valorActual;
        TrieMaterias            materiasDeCarrera;  //También indica el fin de la palabra si es distinto de null
    
        public nodoCarreras(){
            this.hijos  =  new ArrayList<nodoCarreras> (256);
        }
    }

    public TrieCarreras(){
        this.raiz           = new nodoCarreras();
        this.cantidadNodos  = 0;
    }

    public boolean pertenece (String palabra) {
        nodoCarreras nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {                            //Recorre la palabra por caracteres
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

        for (int i = 0; i < palabra.length(); i++){
            char caracter   = palabra.charAt(i);
            int indice      = (int) caracter;               
            
            if (nodoActual.hijos.get(indice) != null){          //Si la letra ya está definida, continua por ahí
                nodoActual = nodoActual.hijos.get(indice);
            }else{
                nodoCarreras nuevoNodo  = new nodoCarreras();   //Si no está definida, la define
                nodoActual.hijos.set(indice, nuevoNodo);
                nodoActual              = nuevoNodo;
                cantidadNodos++;
            }
        }
        if (nodoActual.materiasDeCarrera == null){              //Si termina la palabra y no hay un
            TrieMaterias nuevoTrie = new TrieMaterias();        //trieMaterias asociado, lo genera
            nodoActual.materiasDeCarrera = nuevoTrie;
        }
    }
    //recibe el string a buscar y el trie de carreras 
    public nodoCarreras buscarUltimo(TrieCarreras trieDeCarrera, String palabra){
        nodoCarreras actual = trieDeCarrera.raiz;     
            for (int i = 0; i < palabra.length(); i++){ 
                char caracter   = palabra.charAt(i);    
                int indice      = (int) caracter;
                while(i != (palabra.length() -1)){  // i llega hasta el anteultimo y actualiza actual a el ultimo
                    actual = actual.hijos.get(indice);  
                  //actualiza para tener la instancia de carrera
                }
            }
        return actual;
    }

     public class iteradorLexiDeCarreras{
        private nodoCarreras _actual;

        public iteradorLexiDeCarreras(){
            nodoCarreras _actual;
        }

        public boolean haySiguiente(){
            return (_actual.hijos!= null);
        }

        public Character siguiente(){
            Character adevolver = _actual.valorActual;
            int i = 0;
            while (i < 256 && _actual.hijos.get(i) == null){
                //  if(_actual.hijosmaterias.get(i) != null){
                     _actual = _actual.hijos.get(i) ;
                //  }
            }
            return adevolver; 
        }


    public int tamañoTrie(){
        return cantidadNodos;
    }
   }
}

