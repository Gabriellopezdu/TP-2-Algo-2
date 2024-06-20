package aed;

import java.util.ArrayList;
import aed.TrieCarreras.*;
import aed.ListaEnlazada.*;
import aed.Tupla.*;

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


    public void agregar(String palabra, Materia NuevaMateria){ // la llamamos ya con la instancia de materia del siu 
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
        // nuevaMateria va a ser la que va a apuntar creada en el siu 
        actual.instanciademateria = NuevaMateria; 
    }

    public void eliminar(String palabra){
        NodoMaterias actual = raiz;     
            for (int i = 0; i < palabra.length(); i++){ 
                char caracter   = palabra.charAt(i);    
                int indice      = (int) caracter;
                while(i != (palabra.length() -1)){  // i llega hasta el anteultimo y actualiza actual a el ultimo
                    actual = actual.hijosmaterias.get(indice);  
                  //actualiza para tener la instancia de carrera
                }
            }
            Materia materiaActual = actual.instanciademateria;
            actual.instanciademateria = null;
            // ListaEnlazada<Tupla<nodoCarreras, String>> conjABorrar = materiaActual.infoMateria;
            // nodo actual1 = conjABorrar.primero();}
            // nodoCarreras primerValor = actual1.t1();
         //   Tupla<nodoCarreras,String> conlaInfo = new Tupla(actual1.valor.t1(),actual1.valor.t2());
            // while (actual1.siguiente != null) { 
            //     if (actual1.valor.t2() != palabra) {
            //         nodoCarreras aBorrar = actual1.t1();
            //         NodoMaterias del = buscarUltimo(aBorrar.materiasDeCarrera,actual1.t2());
            //         del.instanciademateria = null; 
            //     }
            //     actual1 = actual1.siguiente;
            // }
    }

    //aca recibe el trie de carreras que le pasa trieCarreras y el nombre de la materia que va a buscar
    public NodoMaterias buscarUltimo(TrieMaterias trieDeLaCarrera, String palabra){
        NodoMaterias actual = trieDeLaCarrera.raiz;     
            for (int i = 0; i < palabra.length(); i++){ 
                char caracter   = palabra.charAt(i);    
                int indice      = (int) caracter;
                while(i != (palabra.length() -1)){  // i llega hasta el anteultimo y actualiza actual a el ultimo
                    actual = actual.hijosmaterias.get(indice);  
                  //actualiza para tener la instancia de carrera
                }
            }
        return actual;
    }

    public class iteradorLexiDeMaterias{
        private NodoMaterias _actual;

        public iteradorLexiDeMaterias(){
            NodoMaterias _actual;
        }

        public boolean haySiguiente(){
            return (_actual.hijosmaterias!= null);
        }

        public Character siguiente(){
            Character adevolver = _actual.valorActual;
            int i = 0;
            while (i < 256 && _actual.hijosmaterias.get(i) == null){
                //  if(_actual.hijosmaterias.get(i) != null){
                     _actual = _actual.hijosmaterias.get(i) ;
                //  }
            }
            return adevolver; 
        }
        /*aca si bien no es lo mas eficiente ya que va a ser una constante grande (256 iteraciones de operaciones O(1)) sigue siendo eficiente contra el nombre de carrera que va a ser algo lineal y potencialmente mucho mas largo*/

        // public ArrayList<String> Inorder(NodoMaterias RaizdetrieActual){
        //     String palabra = "";
        //     ArrayList<String> res = new ArrayList<>();
        //     for(int i = 0;i < 256;i++){
        //         if(RaizdetrieActual.hijosmaterias.get(i) != null){
        //             Character char = RaizdetrieActual.hijosmaterias.get(i);
        //             palabra.concat(char);

        //         }
        //         // 

        //     }
            
        // } 
    }    
}