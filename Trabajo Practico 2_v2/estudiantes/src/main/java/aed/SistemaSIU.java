package aed;

import aed.TrieCarreras.nodoCarreras;
import aed.ListaEnlazada.*;
import aed.TrieMaterias.*;
import java.util.*;

public class SistemaSIU {

    private TrieCarreras    trieDeCarreras;
    private TrieAlumnos     trieAlumnos;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        this.trieDeCarreras = new TrieCarreras(); //creamos el trieDeCarreras nuevo
        this.trieAlumnos = new TrieAlumnos(); //creamos el trieDeAlumnos nuevo
        for(int i = 0; i < infoMaterias.length; i++){
            Materia NuevaMateria = new Materia(); //la materia a la que van a apuntar las hojas del TrieMaterias
            for(int k = 0; k < libretasUniversitarias.length;k++ ){ //aca cargamos las Lu
                String palabra = libretasUniversitarias[k];
                NuevaMateria.agregarAlumno(palabra);
                this.trieAlumnos.agregar(libretasUniversitarias[i]);
            } 
            ParCarreraMateria[] materiaActual = infoMaterias[i].getParesCarreraMateria();
            for(int j = 0; j < materiaActual.length;j++){ // aca vamos a cargar la materia y carreras
                String nombreCarrera = materiaActual[j].getCarrera() ;
                String nombreMateria = materiaActual[j].getNombreMateria();
                if(!trieDeCarreras.pertenece(nombreCarrera)){ // chequea si la carrera esta, si no la agrego 
                    trieDeCarreras.agregar(nombreCarrera);
                }
                nodoCarreras primerElem = trieDeCarreras.buscarUltimo(trieDeCarreras, nombreCarrera); // consigo la hoja de la carrera 
                NuevaMateria.agregarCarMat(primerElem, nombreMateria);
            }
        }
    }
    
    public void inscribir(String estudiante, String carrera, String materia){
        TrieMaterias trieDelaMActual = this.trieDeCarreras.buscarUltimo(this.trieDeCarreras,carrera).materiasDeCarrera;
        Materia objetoDeMateria = trieDelaMActual.buscarUltimo(trieDelaMActual, materia).instanciademateria;
        objetoDeMateria.agregarAlumno(estudiante);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        TrieMaterias trieDelaMActual = this.trieDeCarreras.buscarUltimo(this.trieDeCarreras,carrera).materiasDeCarrera;
        Materia objetoDeMateria = trieDelaMActual.buscarUltimo(trieDelaMActual, materia).instanciademateria;
        if(CargoDocente.AY1 == cargo){
            objetoDeMateria.agregarAy1();
        }
        else if (CargoDocente.AY2 == cargo) {
            objetoDeMateria.agregarAy2();
        }    
        else if(cargo == CargoDocente.JTP){
            objetoDeMateria.agregarJTP();
        } 
        else if (cargo == CargoDocente.PROF){
            objetoDeMateria.agregarProfe();
        }
    }

    public int[] plantelDocente(String materia, String carrera){
        TrieMaterias trieDelaMActual = this.trieDeCarreras.buscarUltimo(this.trieDeCarreras,carrera).materiasDeCarrera;
        Materia objetoDeMateria = trieDelaMActual.buscarUltimo(trieDelaMActual, materia).instanciademateria;
        return objetoDeMateria.profes();
    }

    public void cerrarMateria(String materia, String carrera){
       /* TrieMaterias trieDelaMActual = this.trieDeCarreras.buscarUltimo(this.trieDeCarreras,carrera).materiasDeCarrera;
        Materia objetoDeMateria = trieDelaMActual.buscarUltimo(trieDelaMActual, materia).instanciademateria;
        trieDelaMActual.eliminar(materia);
        nodo actual = objetoDeMateria.infoMateria.primero(); 
        while(actual.siguiente != null){
            if(actual.valor.t2() != palabra){
                nodoCarreras aBorrar = actual.t1();
                NodoMaterias del = buscarUltimo(aBorrar.materiasDeCarrera,actual1.t2());
                del.instanciademateria = null; 
            }
            actual = actual.siguiente;
        }*/
    return;
    }

    public int inscriptos(String materia, String carrera){
        TrieMaterias trieDelaMActual = this.trieDeCarreras.buscarUltimo(this.trieDeCarreras,carrera).materiasDeCarrera;
        Materia objetoDeMateria = trieDelaMActual.buscarUltimo(trieDelaMActual, materia).instanciademateria;
        return objetoDeMateria.inscriptos().tamaño;    
    }

    public boolean excedeCupo(String materia, String carrera){
        TrieMaterias trieDelaMActual = this.trieDeCarreras.buscarUltimo(this.trieDeCarreras,carrera).materiasDeCarrera;
        Materia objetoDeMateria = trieDelaMActual.buscarUltimo(trieDelaMActual, materia).instanciademateria;
        return objetoDeMateria.cupo() >= objetoDeMateria.inscriptos().tamaño;	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        return this.trieAlumnos.buscarUltimo(this.trieAlumnos,estudiante).cantidadMateriasInscripto ; // hay que implementar buscarAlumno en el trieAlumnos	    
    }
    
}
