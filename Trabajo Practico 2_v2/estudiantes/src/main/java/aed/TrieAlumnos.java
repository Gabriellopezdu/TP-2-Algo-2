package aed;

/*
 * El invariante de representación es que no habra mas de una manera de recorrer una palabra, 
 * que cada rama del trie tendra una longitud de 5 elementos, por lo que el atributo fin solo
 * sera true en el quinto nodo de cada rama y el mismo quinto nodo tendra como atributo hijos
 * null, y que si el atributo fin es true representa que la palabra termina en ese punto, y 
 * que la raiz no tendra un caracter implicitamente definido. 
 */

public class TrieAlumnos {

    private nodoAlumnos raiz;
    private int cantidadNodos;

    /*
     * El invariante de representación de la clase nodoAlumnos es que los atributos hijos y fin
     * no pueden ser simultaneamente false y null respectivamente, que cada atributo hijos sera 
     * un array de 256 nodoMaterias, y que el atributo cantidadMateriasInscripto 
     * sera mayor o igual a 0. Tambien, el valor del caracter del nodo estara 
     * representada implicitamente en la posicion del array asociada al numero entero del 
     * caracter en el codigo Ascii.
     */

    public class nodoAlumnos {
        private nodoAlumnos[] hijos;
        private boolean fin;
        public int cantidadMateriasInscripto;

        public nodoAlumnos() {
            this.hijos = new nodoAlumnos[256];
            this.fin = false;
            this.cantidadMateriasInscripto = 0;
        }

        public int cantMateriasInscriptas() {
            return this.cantidadMateriasInscripto;
        }
    }

    public TrieAlumnos() {
        this.cantidadNodos = 0;
        this.raiz = new nodoAlumnos();
    }

    public boolean pertenece(String palabra) {
        nodoAlumnos nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = (int) caracter;

            if (nodoActual.hijos[indice] == null) {
                return false;
            }
            nodoActual = nodoActual.hijos[indice];
        }
        return (nodoActual != null && (nodoActual.fin != false));
    }

    public void agregar(String palabra) { // inscribir a la facu
        nodoAlumnos actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = (int) caracter;

            if (actual.hijos[indice] != null) {
                actual = actual.hijos[indice];
            } else {
                nodoAlumnos nuevoNodo = new nodoAlumnos();
                actual.hijos[indice] = nuevoNodo;
                actual = nuevoNodo;
                cantidadNodos++;
            }
        }
        actual.fin = true;
    }

    public void inscribir(String palabra) { // inscribir a materias
        nodoAlumnos alumno = buscarAlumno(palabra);
        alumno.cantidadMateriasInscripto++;

    }

    public void desinscribir(String alumno) {
        nodoAlumnos actual = buscarAlumno(alumno);
        actual.cantidadMateriasInscripto--;
    }

    public nodoAlumnos buscarAlumno(String palabra) {

        nodoAlumnos nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = (int) caracter;

            if (nodoActual.hijos[indice] != null) {
                nodoActual = nodoActual.hijos[indice];
            }
        }
        return nodoActual;
    }

    public int tamañoTrie() {
        return cantidadNodos;
    }
}
