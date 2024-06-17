package aed;

public class Tupla<T1, T2> { // creamos el tipo tupla para poder 2 meter cosas en
    // la lista enlazada
    private T1 v1;
    private T2 v2;

    public Tupla(T1 elem, T2 elem2) {
        v1 = elem;
        v2 = elem2;
    }

    public T1 t1() {
        return v1;
    }

    public T2 t2() {
        return v2;
    }
}
