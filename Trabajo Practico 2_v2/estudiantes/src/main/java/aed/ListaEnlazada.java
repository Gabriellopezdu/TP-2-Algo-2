package aed;

public class ListaEnlazada<T> {

    private nodo primerNodo;
    private nodo ultimoNodo;
    private int tamaño;

    private class nodo{
        T       valor;
        nodo    siguiente;
    }

    public ListaEnlazada(){
        this.tamaño     = 0;
        this.primerNodo = null;
        this.ultimoNodo = null;
    }

    public int longitud(){
        return this.tamaño;
    }

    public void agregarAdelante(T elemento){
        nodo nuevoNodo      = new nodo();
        T nuevoElemento     = elemento;
        nuevoNodo.valor     = nuevoElemento;
        nuevoNodo.siguiente = null;

        if (primerNodo != null){
            nuevoNodo.siguiente = primerNodo;
        }else{
            ultimoNodo = nuevoNodo;
        }

        primerNodo = nuevoNodo;
        tamaño++;
    }

    public void agregarAtras(T elemento){
        nodo nuevoNodo      = new nodo();
        T nuevoElemento     = elemento;
        nuevoNodo.valor     = nuevoElemento;
        nuevoNodo.siguiente = null;

        if (ultimoNodo != null){
            ultimoNodo.siguiente = nuevoNodo;
        }else{
            primerNodo = nuevoNodo;
        }

        ultimoNodo = nuevoNodo;
        tamaño++;
    }   
    
    public T obtener(int i) {
        nodo actual = primerNodo;

        for (int j = 0; j < i; j++){
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        nodo actual     = primerNodo;
        nodo anterior   = primerNodo;

        for (int j = 0; j < i; j++){
            anterior    = actual;
            actual      = actual.siguiente;
        }

        if (actual == ultimoNodo){
            anterior.siguiente  = null;
            ultimoNodo          = anterior;
        }else{
            anterior.siguiente = actual.siguiente;
        }
    }
}
