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
                           

                nodoCarreras ultNodoCarreras = trieDeCarreras.agregar(nombreCarrera); // enchufamos la carrera y guardamos el ultimo nodo
                // O(largo carrera)

                if (ultNodoCarreras.materiasDeCarrera == null) {
                    ultNodoCarreras.materiasDeCarrera = new TrieMaterias(); // si no lo tiene creamos el trieMaterias
                    // O(1)
                }                                                           
                
                nodoMaterias ultNodoMateria = ultNodoCarreras.materiasDeCarrera.agregar(nombreMateria);
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
        // todas las operaciones son O(1), y quedaria sumatoria{desde (k=1 hasta cantidad de LU) de O(1)}  

        /* finalmente nos quedaria sumatoria{(desde i=1 hasta el largo de infoMaterias) 
                                   de sumatoria{(j=1 hasta el largo de la lista paresCarreraMateria) de 
                                   O(largo carrera) + O(largo materia)}} + 
                                   sumatoria{(desde k=1 hasta cantidad de LU) de O(1)   } */
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

    public boolean excedeCupo(String nombreMateria, String carrera) {
        Materia mat = trieDeCarreras.buscarCarrera(carrera).materiasDeCarrera.buscarMateria(nombreMateria).materia;
        int cantProfes =  mat.profes()[0];
        int cantJtps = mat.profes()[1];
        int cantAY1 = mat.profes()[2];
        int cantAY2 = mat.profes()[3];
        int tamañoDeInscriptos = mat.inscriptos.longitud();
        
        if ((cantAY2*30) < tamañoDeInscriptos){
            return true;
        }

        if(cantAY1*20 < mat.inscriptos.tamaño){ 
            return true;
        }
        
        if(cantJtps*100 < mat.inscriptos.tamaño){ 
            return true;
        }

        if(cantProfes*250 < mat.inscriptos.tamaño){ 
            return true;
        }
        return false;
    }
    
    // public String[] carreras() {
    //     String[] res[];
    //     for (int i = 0; i < 256;i++)
    //         while (trieDeCarreras.iterador().haySiguiente() != false){
                
    //         }
            
    //     res.
            
    // }

    // public String[] materias(String carrera) {
    //     throw new UnsupportedOperationException("Método no implementado aún");
    // }

    public int materiasInscriptas(String estudiante) {
        return trieAlumnos.buscarAlumno(estudiante).cantidadMateriasInscripto;
    }

}
