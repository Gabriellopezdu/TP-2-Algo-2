package aed;

/* INVREP listaEnlazada:
 *  El invariante de representación es que el primer nodo y el ultimo nodo son null si y solo si no hay elementos en la lista
 * El tamaño es equivalente a la cantidad de nodos que tiene la lista, que no puede ser menor a 0
 * Si la lista tiene un solo elemento, éste es el primerNodo y el último nodo
 * Si hacemos primerNodo.siguiente (tamaño-1) veces, llegaremos al último nodo
 */


public class ListaEnlazada<T> {

    public nodo primerNodo;
    public nodo ultimoNodo;
    public int tamaño;

    public class nodo{
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

    public nodo primero(){
        return this.primerNodo;
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

    public nodo siguiente(nodo nodito) {
       return nodito = nodito.siguiente;
    }
}
