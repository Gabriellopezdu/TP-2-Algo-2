package aed;

// El inv. de representacion de la clase TrieMaterias es que tendra un nodoMaterias raiz en el cual
// no tendra valor de Character definido, luego respeta que los nodos que conforman el trie cumplen
// el inv. de representacion de la clase nodoMaterias. Luego no habra dos(o mas) formas distintas de 
// recorrer el trie para obtener la misma palabra. 
// Al final de cada palabra, el ultimo nodo tendra un atributo de clase Materia. 
// Luego, el trie de materias siempre estara ligado a una carrera existente en el trie de carreras.  

public class TrieMaterias {

    private nodoMaterias raiz;
    private int cantidadNodos;
    public int cantMaterias = 0;

    // El inv. de representación de la clase nodoMaterias es que, si no es la raiz
    // el valor del
    // nodo sera un Character y tendra como atributo un Array de 256 nodoMaterias en
    // las que, si no es
    // null tendra su valor predefinido en el Ascii. Luego, si estamos en el nodo
    // que representa el fin
    // de la palabra este tendra el atributo fin en true y el atributo materia
    // distinto de null
    // apuntando a la instancia de materia que representa.

    public class nodoMaterias {
        nodoMaterias[] hijos;
        Character valorActual;
        Boolean fin;
        Materia materia;

        public nodoMaterias() {
            this.hijos = new nodoMaterias[256]; // creamos el arreglo de 256 posiciones
            fin = null;
            // ambas operaciones son O(1), ya que solo son asignaciones
            // y siempre se ejecutaran solo esas 2.
        }
    }

    public TrieMaterias() {
        this.raiz = new nodoMaterias();
        this.cantidadNodos = 0;
    }

    public boolean pertenece(String palabra) {

        if (palabra == null || palabra.length() == 0) {
            return false;
        }

        nodoMaterias nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) { // Recorre la palabra por caracteres
            char caracter = palabra.charAt(i); // charAT(i) devuelve el char en la posición i
            int indice = (int) caracter; // (int) char devuelve el código ASCII del char

            if (nodoActual.hijos[indice] == null) { // get(indice) devuelve el hijo en la posición indice
                return false; // si el array en el indice es null, la letra no existe
            }
            nodoActual = nodoActual.hijos[indice]; // caso contrario, nodo actual pasa a ser el nodo en
        } // la posición "indice" del array (la siguiente letra)
        return nodoActual != null && (nodoActual.materia != null);
        // materiasDeCarrera hace las de fin de palabra
    }

    public nodoMaterias agregar(String palabra) {
        nodoMaterias nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = (int) caracter;

            if (nodoActual.hijos[indice] != null) { // Si la letra ya está definida, continua por ahí
                nodoActual = nodoActual.hijos[indice];
            } else {
                nodoMaterias nuevoNodo = new nodoMaterias(); // Si no está definida, la define
                nodoActual.hijos[indice] = nuevoNodo;
                nodoActual = nuevoNodo;
                cantidadNodos++;
            }
        }
        nodoActual.fin = true; 
        return nodoActual;
    }

    // recibe la materia a buscar y devolvera el ultimo nodo que apunta a la instancia de materia  
    public nodoMaterias buscarMateria(String palabra) {

        nodoMaterias nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = (int) caracter;

            if (nodoActual.hijos[indice] != null) {
                nodoActual = nodoActual.hijos[indice];
            }
        }
        return nodoActual;
    }
    // recibe una materia(string), la busca en el trie y elimina la referencia al objeto materia del ultimo nodo 
    public void eliminar(String palabra) {
        nodoMaterias actual = this.buscarMateria(palabra);
        actual.materia = null;
        actual.fin = false;
        cantMaterias--;
    }

    public int tamañoTrie() {
        return cantidadNodos;
    }
    // aqui manejamos los datos con los que llamamos a recorridoLexico, 
    public String[] inOrderMaterias() {
        String[] arrayMaterias = new String[cantMaterias]; //aca vamos a guardar las materias
        int[] indice = { 0 }; //indice con el cual nos desplazaremos sobre arrayMaterias

        StringBuffer materiaActual = new StringBuffer();
        recorridoLexico(raiz, materiaActual, arrayMaterias, indice);
        return arrayMaterias;
    }

    private void recorridoLexico(nodoMaterias nodoActual, StringBuffer materiaActual,
            String[] arrayMaterias, int[] indice) {
        //caso base no queda nada mas por explorar        
        if (nodoActual == null) {
            return;
        }
        //detecta que llegamos al fin del nombre de una materia y la guarda en el arrayMaterias
        if (nodoActual.materia != null) {
            arrayMaterias[indice[0]++] = materiaActual.toString();
        }
        //iteramos para encontrar el sig nodo no null en sus hijos
        for (int i = 0; i < 256; i++) {
            if (nodoActual.hijos[i] != null) {
                materiaActual.append((char) i); //ponemos el char encontrado en la palabra que estamos armando
                recorridoLexico(nodoActual.hijos[i], materiaActual, arrayMaterias, indice);//lamada recursiva  
                materiaActual.deleteCharAt(materiaActual.length() - 1);
            }
        }
    }

}