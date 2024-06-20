package aed;

import java.util.ArrayList;
import aed.TrieCarreras.*;

public class Materia {

    public ListaEnlazada<Tupla<nodoCarreras, String>> infoMateria; // lista enlazada de tuplas tipo (puntero carrera,
                                                                    // nombreMateria)

    private ArrayList<Integer> profes; // lista de cantidad de profes (Profe, jtp, Ay1, Ay2) y cant alumnos es (250,
                                       // 100, 20, 30)

    private ListaEnlazada<String> inscriptos; // lista enlazada con las LU (esto lo hacemos para facilitar el metodo
                                              // borrar materia)

    public Materia() {

        ListaEnlazada<Tupla<nodoCarreras, String>> nombres = new ListaEnlazada();

        infoMateria = nombres;

        profes = new ArrayList<>(4);

        ListaEnlazada<String> alumnos = new ListaEnlazada();

        inscriptos = alumnos;
    }

    public void agregarProfe() {
        profes.set(0, profes.get(0) + 1);
    }

    public void agregarJTP() {
        profes.set(1, profes.get(1) + 1);
    }

    public void agregarAy1() {
        profes.set(2, profes.get(2) + 1);
    }

    public void agregarAy2() {
        profes.set(3, profes.get(3) + 1);
    }

    public ArrayList<Integer> profes(){
        return this.profes;
    }

    public ListaEnlazada<String> inscriptos(){
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
        return profes.get(0) * 250 + profes.get(1) * 100 + profes.get(2) * 20 + profes.get(3) * 30;
    }
}
