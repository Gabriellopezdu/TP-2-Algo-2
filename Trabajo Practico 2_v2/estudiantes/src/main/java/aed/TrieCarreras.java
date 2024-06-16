package aed;

import java.util.ArrayList;

public class TrieCarreras<T> {

    private nodoCarreras    raiz;
    private int             cantidadNodos;

    private class nodoCarreras{
        ArrayList<nodoCarreras> hijos;
        TrieMaterias            materiasDeCarrera;                              //También indica el fin de la palabra
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

    public void agregar (String palabra) {  //(ParCarreraMateria carreraYMaterias)
        nodoCarreras nodoActual = raiz;
//        String nombreCarrera = carreraYMaterias.getCarrera();

//        for (int i = 0; i < nombreCarrera.length(); i++) {      //Toma el nombre de la carrera, y recorre igual que "buscar"
        for (int i = 0; i < palabra.length(); i++){
            //char caracter   = nombreCarrera.charAt(i);
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
        if (nodoActual.materiasDeCarrera == null){          //Si termina la palabra y no hay un
            TrieMaterias nuevoTrie = new TrieMaterias();    //trieMaterias asociado, lo genera
            nodoActual.materiasDeCarrera = nuevoTrie;       //Podría completar el trie con las materias
        }else{
            return;
        }
    }

    public int tamañoTrie(){
        return cantidadNodos;
    }
}

