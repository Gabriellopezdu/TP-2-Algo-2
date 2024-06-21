package aed;

import aed.Tupla.*;
import aed.TrieCarreras.nodoCarreras;
import aed.ListaEnlazada.*;
import aed.TrieMaterias.*;

public class SistemaSIU {

    private TrieCarreras trieDeCarreras;
    private TrieAlumnos trieAlumnos;

    enum CargoDocente {
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {

        trieDeCarreras = new TrieCarreras(); // creamos los tries vacios
        trieAlumnos = new TrieAlumnos();

        for (int i = 0; i < infoMaterias.length; i++) { // iteramos sobre cada infoMateria (osea entre cada materia
                                                        // general)

            Materia nuevaMateria = new Materia(); // creamos el objeto materia para el aliasing

            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++) { // iteramos sobre cada (carrera,
                                                                                        // materia) de una misma materia
                                                                                        // general
                String nombreCarrera = infoMaterias[i].getParesCarreraMateria()[j].getCarrera(); // accedemos al
                                                                                                 // nombreCarrera
                String nombreMateria = infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria(); // accedemos al
                                                                                                       // nombreMateria

                trieDeCarreras.agregar(nombreCarrera); // enchufamos la carrera
                nodoCarreras ultNodoCarreras = trieDeCarreras.buscarCarrera(nombreCarrera); // guardamos el ultimo nodo
                                                                                            // de la carrera

                if (ultNodoCarreras.materiasDeCarrera != null) { // le enchufamos la materia al trieMaterias de la
                                                                 // carrera
                    ultNodoCarreras.materiasDeCarrera.agregar(nombreMateria);
                } else {
                    ultNodoCarreras.materiasDeCarrera = new TrieMaterias(); // o creamos el trieMaterias y luego
                                                                            // enchufamos la materia
                    ultNodoCarreras.materiasDeCarrera.agregar(nombreMateria);
                }

                nuevaMateria.agregarCarMat(ultNodoCarreras, nombreMateria); // agregamos la mausquerramienta misteriosa
                                                                            // que nos servira para mas adelante
                                                                            // al obj materia le metemos un puntero al
                                                                            // ultimo nodo de carrera y el nombreMateria

                nodoMaterias ultNodoMateria = ultNodoCarreras.materiasDeCarrera.buscarMateria(nombreMateria);
                ultNodoMateria.materia = nuevaMateria; // apuntamos el ultimo nodo de la materia al onjeto materia
            }
        }

        for (int k = 0; k < libretasUniversitarias.length; k++) { // enchufamos en el trieAlumnos a todas las LU
            trieAlumnos.agregar(libretasUniversitarias[k]);
        }
    }

    /*
     * COMPLEJIDAD:
     * Crear los tries vacíos es O(1) (generar el nodo raíz)
     * 
     */

    public void inscribir(String estudiante, String carrera, String materia) {

        nodoCarreras nodoCarreras = trieDeCarreras.buscarCarrera(carrera);

        nodoMaterias nodoMateria = nodoCarreras.materiasDeCarrera.buscarMateria(materia);

        nodoMateria.materia.agregarAlumno(estudiante);

        trieAlumnos.inscribir(estudiante);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia) {

        nodoCarreras nodoCarreras = trieDeCarreras.buscarCarrera(carrera);
        nodoMaterias nodoMateria = nodoCarreras.materiasDeCarrera.buscarMateria(materia);
        if (CargoDocente.AY1 == cargo) {
            nodoMateria.materia.agregarAy1();
        } else if (CargoDocente.PROF == cargo) {
            nodoMateria.materia.agregarProfe();
        }

        else if (CargoDocente.AY2 == cargo) {
            nodoMateria.materia.agregarAy2();
        } else if (CargoDocente.JTP == cargo) {
            nodoMateria.materia.agregarJTP();
        }
    }

    public int[] plantelDocente(String materia, String carrera) {

        return trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(materia).materia.profes();

        // recorremos el nombre de la carrera y el de la materia, luego solamente
        // devolvemos un atributo del objeto materia
        // O(largo de carrera + largo de materia)
    }

    public void cerrarMateria(String materia, String carrera) {

        nodoCarreras ultNodoCarreras = trieDeCarreras.buscarCarrera(carrera); // buscamos la materia en el trieCarreras

        nodoMaterias ultNodoMateria = ultNodoCarreras.materiasDeCarrera.buscarMateria(materia); // buscamos el objeto de
                                                                                                // materia en el
                                                                                                // trieMaterias

        Materia materiaABorrar = ultNodoMateria.materia;

        ListaEnlazada<Tupla>.nodo carMat = materiaABorrar.infoMateria.primerNodo;

        for (int i = 0; i < materiaABorrar.infoMateria.longitud() - 1; i++) {
            carMat.valor.t1().materiasDeCarrera.eliminar(carMat.valor.t2());
            carMat = carMat.siguiente;
        }

        ListaEnlazada<String>.nodo nodoAlumno = materiaABorrar.inscriptos().primerNodo;

        for (int j = 0; j < materiaABorrar.inscriptos().longitud() - 1; j++) {
            trieAlumnos.buscarAlumno(nodoAlumno.valor).cantidadMateriasInscripto--;
            nodoAlumno = nodoAlumno.siguiente;
        }

        // TrieMaterias trieDeMateriaActual =
        // this.trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera;// seteamos el
        // // trie para
        // // usarlo
        // Materia objetoDeMateria = trieDeMateriaActual.buscarMateria(materia).materia;
        // // seteamos el objeto materia
        // nodoT actual = objetoDeMateria.infoMateria.primeroT(); // consultamos el
        // primer nodo de la lista con los nombres
        // // de la materia

        // while (actual.siguiente != null) {
        // if (actual.valor.t2() != palabra) {
        // nodoCarreras aBorrar = actual.t1();
        // nodoMaterias del = buscarUltimo(aBorrar.materiasDeCarrera, actual.t2());
        // del.instanciademateria = null;
        // }
        // actual = actual.siguiente;
        // }
        // return;
    }

    public int inscriptos(String materia, String carrera) {

        return trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(materia).materia
                .inscriptos().tamaño;

        // recorremos el nombre de la carrera y el de la materia, luego preguntamos el
        // tamano de la lista enlazada que es un atributo de lista enlazada
        // O(largo de carrera + largo de materia)
    }

    public boolean excedeCupo(String materia, String carrera) {
        Materia laMateria = trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(materia).materia;
        int cupo = laMateria.cupo();
        int inscriptos = laMateria.inscriptos().longitud();
        return (inscriptos > cupo);
    }

    public String[] carreras() {
        String[] res[];
        for (int i = 0; i < 256;i++)
            while (trieDeCarreras.iterador().haySiguiente() != false){
                
            }
            
        res.
            
    }

    public String[] materias(String carrera) {
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int materiasInscriptas(String estudiante) {
        return trieAlumnos.buscarAlumno(estudiante).cantidadMateriasInscripto;
    }

}
