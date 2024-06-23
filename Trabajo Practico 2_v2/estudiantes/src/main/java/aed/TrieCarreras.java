package aed;

public class TrieCarreras {

    private nodoCarreras raiz;
    private int cantidadNodos;
    public int cantidadDeCarreras = 0;

    // El invariante de representación de la clase nodoCarreras es que no existe
    // nodo tal que los atributos
    // hijos y materiasDeCarrera sean null simultaneamente.

    public class nodoCarreras {
        nodoCarreras[] hijos;
        Character valorActual;
        TrieMaterias materiasDeCarrera; // indica el fin de la palabra si es distinto de null

        public nodoCarreras() {
            this.hijos = new nodoCarreras[256];
        }
    }

    public TrieCarreras() {
        this.raiz = new nodoCarreras();
        this.cantidadNodos = 0;
    }

    public boolean pertenece(String palabra) {

        if (palabra == null || palabra.length() == 0) {
            return false;
        }

        nodoCarreras nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) { // Recorre la palabra por caracteres
            char caracter = palabra.charAt(i); // charAT(i) devuelve el char en la posición i
            int indice = (int) caracter; // (int) char devuelve el código ASCII del char

            if (nodoActual.hijos[indice] == null) { // get(indice) devuelve el hijo en la posición indice
                return false; // si el array en el indice es null, la letra no existe
            }
            nodoActual = nodoActual.hijos[indice]; // caso contrario, nodo actual pasa a ser el nodo en
        } // la posición "indice" del array (la siguiente letra)
        return nodoActual != null && (nodoActual.materiasDeCarrera != null);
        // materiasDeCarrera hace las de fin de palabra
    }

    public nodoCarreras agregar(String palabra) {
        nodoCarreras nodoActual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = (int) caracter;

            if (nodoActual.hijos[indice] != null) { // Si la letra ya está definida, continua por ahí
                nodoActual = nodoActual.hijos[indice];
            } else {
                nodoCarreras nuevoNodo = new nodoCarreras(); // Si no está definida, la define
                nodoActual.hijos[indice] = nuevoNodo;
                nodoActual = nuevoNodo;
                cantidadNodos++;
            }
        }
        if (nodoActual.materiasDeCarrera == null) { // Si termina la palabra y no hay un // trieMaterias asociado, lo
                                                    // genera un nuevo trie de materias vacío
            nodoActual.materiasDeCarrera = new TrieMaterias();
            cantidadDeCarreras++;
        }

        return nodoActual;
    }

    // recibe el string a buscar y el trie de carreras
    public nodoCarreras buscarCarrera(String palabra) {

        nodoCarreras nodoActual = raiz;

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

    public String[] inOrderCarreras() {
        String[] arrayCarreras = new String[cantidadNodos]; // Acá desperdiciamos un poco de espacio, pidiendo el array
                                                            // con el tamaño máximo posible
        String[] res = new String[cantidadDeCarreras];
        int[] indice = { 0 }; // Array con un elemento para mantener el indice para añadir carreras

        StringBuilder carreraActual = new StringBuilder();
        inOrder(raiz, carreraActual, arrayCarreras, indice);

        // Copia los elementos != null de arrayCarreras al resultado
        int resultadoDelIndice = 0;
        for (String carrera : arrayCarreras) {
            if (carrera != null) {
                res[resultadoDelIndice++] = carrera;
            }
        }

        return res;
    }

    private void inOrder(nodoCarreras nodoActual, StringBuilder carreraActual, String[] arrayCarreras,
            int[] indice) {
        if (nodoActual == null) {
            return;
        }

        if (nodoActual.materiasDeCarrera != null) {
            // Busco el fin de carrera, añade carreraActual a arrayCarreras
            arrayCarreras[indice[0]++] = carreraActual.toString();
        }

        for (int i = 0; i < 256; i++) {
            if (nodoActual.hijos[i] != null) {
                carreraActual.append((char) i);
                inOrder(nodoActual.hijos[i], carreraActual, arrayCarreras, indice);
                carreraActual.deleteCharAt(carreraActual.length() - 1);
            }
        }
    }
}
