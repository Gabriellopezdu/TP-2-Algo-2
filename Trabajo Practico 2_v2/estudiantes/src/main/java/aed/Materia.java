package aed;

import aed.TrieCarreras.*;

/* 
 * El invariante de representación de la clase Materia es que los valores de cada posicion 
 * del atributo profes seran mayores o iguales a 0, que o bien infoMateria es una lista enlazada
 * vacia, o bien el v1 de cada tupla dentro de la lista enlazada del atributo infoMateria
 * apuntara al ultimo nodo de Carreras que corresponda al nombre de la materia en esa carrera 
 * (que es el v2), y que o bien inscriptos es una lista enlazada vacia, o bien cada nodo dentro
 * de la lista enlazada del atributo inscriptos tendra un string de una longitud de exactamente
 * 5 elementos.
 */

public class Materia {

    public ListaEnlazada<Tupla> infoMateria; // lista enlazada de tuplas tipo (puntero carrera,
                                                                    // nombreMateria)

    private int[] profes; // lista de cantidad de profes (Profe, jtp, Ay1, Ay2) y cant alumnos es (250,
                          // 100, 20, 30)

    public ListaEnlazada<String> inscriptos; // lista enlazada con las LU (esto lo hacemos para facilitar el metodo
                                              // borrar materia)

    public Materia() {

        ListaEnlazada<Tupla> nombres = new ListaEnlazada();

        infoMateria = nombres;

        profes = new int[4];

        ListaEnlazada<String> alumnos = new ListaEnlazada();

        inscriptos = alumnos;
    }

    public void agregarProfe() {
        profes[0]++;
    }

    public void agregarJTP() {
        profes[1]++;
    }

    public void agregarAy1() {
        profes[2]++;
    }

    public void agregarAy2() {
        profes[3]++;
    }

    public int[] profes() {
        return this.profes;
    }

    public ListaEnlazada<String> inscriptos() {
        return this.inscriptos;
    }

    public void agregarCarMat(nodoCarreras ultimoNodoCarrera, String nombreMateria) {
        Tupla<nodoCarreras, String> nueva = new Tupla<nodoCarreras, String>(ultimoNodoCarrera, nombreMateria);

        infoMateria.agregarAtras(nueva);
    }

    public void agregarAlumno(String alumno) {
        inscriptos.agregarAtras(alumno);
    }

    public int cupo() {
        return profes[0] * 250 + profes[1] * 100 + profes[2] * 20 + profes[3] * 30;
    }
}
