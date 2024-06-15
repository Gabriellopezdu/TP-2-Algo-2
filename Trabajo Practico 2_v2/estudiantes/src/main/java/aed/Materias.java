package aed;

import java.util.ArrayList;

public class Materias {
    
    private ListaEnlazada<ArrayList<ArrayList<String>>> infoMateria; // lista enlazada de tuplas tipo (carrera, nombreMateria)
    private ArrayList<Integer> profes; // lista de cantidad de profes (Profe, jtp, Ay1, Ay2) y cant alumnos es (250, 100, 20, 30)
    private ListaEnlazada<String> inscriptos; // lista enlazada con las LU (esto lo hacemos para facilitar el metodo borrar materia)

    public Materias nuevaMateria(ParCarreraMateria[] carrerasMateria, String[] alumnos) {
        ListaEnlazada<T> nueva = new ListaEnlazada();
        int i = 0;

        while (i < carrerasMateria.length) {
            nueva.agregarAdelante(carrerasMateria[0]);
            i++;
        }
        infoMateria = nueva;

        profes = new ArrayList<>(4);
        
        nueva = new ListaEnlazada();
        while (i < alumnos.length) {
            nueva.agregarAdelante(alumnos[i]);
            i++;
        }
        inscriptos = nueva;
    }

}