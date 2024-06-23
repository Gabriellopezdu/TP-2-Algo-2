package aed;

import aed.TrieCarreras.nodoCarreras;
import aed.TrieMaterias.nodoMaterias;

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
        // ambos son O(1) pues esos metodos son O(1) y siempre se ejecutan
        // sin importar la entrada

        for (int i = 0; i < infoMaterias.length; i++) { // iteramos sobre cada infoMateria (osea entre cada materia
                                                        // general)
            // esta operacion es una sumatoria desde i=1 hasta el largo de infoMaterias

            Materia nuevaMateria = new Materia(); // creamos el objeto materia para el aliasing
            // O(1) nuevamente, solo es asignacion

            for (int j = 0; j < infoMaterias[i].getParesCarreraMateria().length; j++) { // iteramos sobre cada (carrera,
                                                                                        // materia) de una misma materia
                                                                                        // general
                // sumatoria desde j=1 hasta el largo de la lista paresCarreraMateria

                String nombreCarrera = infoMaterias[i].getParesCarreraMateria()[j].getCarrera(); // accedemos al
                                                                                                 // nombreCarrera
                // esta complejidad es O(1) solo accedemos a la posicion de un array

                String nombreMateria = infoMaterias[i].getParesCarreraMateria()[j].getNombreMateria(); // accedemos al
                                                                                                       // nombreMateria
                // esta complejidad es O(1) solo accedemos a la posicion de un array

                nodoCarreras ultNodoCarreras = trieDeCarreras.agregar(nombreCarrera); // enchufamos la carrera y
                                                                                      // guardamos el ultimo nodo
                // O(largo carrera)

                if (ultNodoCarreras.materiasDeCarrera == null) {
                    ultNodoCarreras.materiasDeCarrera = new TrieMaterias(); // si no lo tiene creamos el trieMaterias
                    // O(1)
                }

                nodoMaterias ultNodoMateria = ultNodoCarreras.materiasDeCarrera.agregar(nombreMateria);
                ultNodoCarreras.materiasDeCarrera.cantMaterias++;
                // agregar es O(nombre materia) y la asignacion es O(1)

                nuevaMateria.agregarCarMat(ultNodoCarreras, nombreMateria); // agregamos la mausquerramienta misteriosa
                                                                            // que nos servira para mas adelante
                                                                            // al obj materia le metemos un puntero al
                                                                            // ultimo nodo de carrera y el nombreMateria
                // O(1) pues es agregarAtras a una lista enlazada

                ultNodoMateria.materia = nuevaMateria; // apuntamos el ultimo nodo de la materia al objeto materia
            }
        }

        for (int k = 0; k < libretasUniversitarias.length; k++) { // enchufamos en el trieAlumnos a todas las LU
            trieAlumnos.agregar(libretasUniversitarias[k]);
        }
        // esto sera O(cantidad de estudiantes), pues al ser acotadas las LU
        // todas las operaciones son O(1), y quedaria sumatoria{desde (k=1 hasta
        // cantidad de LU) de O(1)}

        /*
         * finalmente la complejidad nos quedaria sumatoria{(desde i=1 hasta el largo de
         * infoMaterias)
         * de sumatoria{(j=1 hasta el largo de la lista paresCarreraMateria) de
         * O(largo carrera) + O(largo materia)}} +
         * sumatoria{(desde k=1 hasta cantidad de LU) de O(1) }
         */
    }

    public void inscribir(String estudiante, String carrera, String materia) {

        nodoCarreras nodoCarreras = trieDeCarreras.buscarCarrera(carrera); // buscamos el ultimo nodo de la carrera
        // recorremos la carrera, entonces es O(largo carrera)

        nodoMaterias nodoMateria = nodoCarreras.materiasDeCarrera.buscarMateria(materia); // lo mismo pero con materia
        // O(largo materia)

        nodoMateria.materia.agregarAlumno(estudiante); // agregamos la LU a la lista enlazada
        // agregarAlumno usa agregarAtras, que es O(1)

        trieAlumnos.inscribir(estudiante); // le sumamos uno a las materias inscriptas del alumno
        // como es trie con LU acotadas, las operaciones son O(1)

        // entonces la complejidad quedaria O(largo carrera + largo materia)
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia) {

        nodoCarreras nodoCarreras = trieDeCarreras.buscarCarrera(carrera); // buscamos el ultimo nodo de la carrera
        // recorremos la carrera, entonces es O(largo carrera)

        nodoMaterias nodoMateria = nodoCarreras.materiasDeCarrera.buscarMateria(materia); // lo mismo pero con materia
        // O(largo materia)

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
        // estas solo son comparaciones y no dependen de la entrada, osea es todo O(1)

        // nos queda complejidad O(largo carrera + largo materia)
    }

    public int[] plantelDocente(String materia, String carrera) {

        return trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(materia).materia.profes();

        // recorremos el nombre de la carrera y el de la materia, luego solamente
        // devolvemos un atributo del objeto materia
        // O(largo de carrera + largo de materia)
    }

    public void cerrarMateria(String materia, String carrera) {

        nodoCarreras ultNodoCarreras = trieDeCarreras.buscarCarrera(carrera);// buscamos el ultimo nodo de la carrera
        // recorremos la carrera, entonces es O(largo carrera)

        nodoMaterias ultNodoMateria = ultNodoCarreras.materiasDeCarrera.buscarMateria(materia); // buscamos el ultimo
                                                                                                // nodo de la materia
        // para eso recorremos la materia, O(largo materia)

        Materia materiaABorrar = ultNodoMateria.materia; // le asignamos el obj materia
        // O(1)

        ListaEnlazada<Tupla>.nodo carMat = materiaABorrar.infoMateria.primerNodo;
        int i = 0;
        // asignaciones O(1)

        while (i < materiaABorrar.infoMateria.longitud()) {
            carMat.valor.t1().materiasDeCarrera.eliminar(carMat.valor.t2());
            carMat = carMat.siguiente;
            i++;
        }
        // como t1() devuelve un puntero (mausquerramienta misteriosa) no recorremos la
        // carrera denuevo
        // solamente recorremos el nombre de la materia
        // sumatoria{(desde i=1 hasta cantidadNombresMateria) de O(largo materia)}

        ListaEnlazada<String>.nodo nodoAlumno = materiaABorrar.inscriptos().primerNodo;
        int j = 0;
        // asignaciones O(1)

        while (j < materiaABorrar.inscriptos().longitud()) {
            trieAlumnos.buscarAlumno(nodoAlumno.valor).cantidadMateriasInscripto--;
            nodoAlumno = nodoAlumno.siguiente;
            j++;
        }
        // por cada alumno inscripto a esa materia, en el trie le sacamos una materia
        // como es trie acotado es O(1) por cada alumno inscripto

        // entonces la complejidad queda O(largo carrera + largo materia) + O(largos de
        // nombresMateria) + O(cant Inscriptos a materia)
    }

    public int inscriptos(String materia, String carrera) {

        return trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(materia).materia
                .inscriptos().tamaño;

        // recorremos el nombre de la carrera y el de la materia, luego preguntamos el
        // tamano de la lista enlazada que es un atributo de lista enlazada
        // O(largo de carrera + largo de materia)
    }

    public boolean excedeCupo(String nombreMateria, String carrera) {
        Materia mat = trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(nombreMateria).materia;
        // buscamos el nodo de la carrera y luego el de la materia para llamar al objeto
        // O(largo carrera + materia)

        int cantProfes = mat.profes()[0];
        int cantJtps = mat.profes()[1];
        int cantAY1 = mat.profes()[2];
        int cantAY2 = mat.profes()[3];
        int tamañoDeInscriptos = mat.inscriptos.longitud();

        if ((cantAY2 * 30) < tamañoDeInscriptos) {
            return true;
        }

        if (cantAY1 * 20 < mat.inscriptos.tamaño) {
            return true;
        }

        if (cantJtps * 100 < mat.inscriptos.tamaño) {
            return true;
        }

        if (cantProfes * 250 < mat.inscriptos.tamaño) {
            return true;
        }
        return false;
        // el resto son todas asignaciones o comparaciones

        // complejidad queda O(largo carrera + materia)
    }

    public String[] carreras() {
        return trieDeCarreras.inOrderCarreras();
        // recorremos el arbol, visitando cada nodo, osea recorriendo cada caracter de
        // todas las carreras sin pasar 2 veces x el mismo
        // entonces es lo mismo decir O(cant nodos) que O(cant caracteres sin repetir)
        // osea es O(largo de cada nombre de carrera)
    }

    public String[] materias(String carrera) {
        return trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.inOrderMaterias();

        // es lo mismo que con carreras, solo que cambiamos
        // los nombres de las carreras por los nombres de las materias de la carrera
        // y sumamos el largo de la carrera, pues la recorrermos para acceder al trie

        // osea que quedaria O(largo carrera) + O(nombres de las materias)
    }

    public int materiasInscriptas(String estudiante) {
        return trieAlumnos.buscarAlumno(estudiante).cantidadMateriasInscripto;

        // como el trieAlumnos tiene sus elementos acotados, toda operacion es
        // O(1)
    }

}