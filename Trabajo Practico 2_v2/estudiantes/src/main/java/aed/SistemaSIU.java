package aed;

import aed.TrieCarreras.nodoCarreras;

public class SistemaSIU {

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        TrieCarreras trieNuevo = new TrieCarreras(); //creamos el trieDeCarreras nuevo
        TrieAlumnos triedeAlumnos = new TrieAlumnos(); //creamos el trieDeAlumnos nuevo
        for(int i = 0; i < infoMaterias.length; i++){
            Materia NuevaMateria = new Materia(); //la materia a la que van a apuntar las hojas del TrieMaterias
            for(int k = 0; k < libretasUniversitarias.length;k++ ){ //aca cargamos las Lu
                String palabra = libretasUniversitarias[k];
                NuevaMateria.agregarAlumno(palabra); 
            }  
            ParCarreraMateria[] materiaActual = infoMaterias[i].getParesCarreraMateria(); //no estoy seguro de esto de [i] pero no me deja accederlo con get(i)
            for(int j = 0; j < materiaActual.length;j++){ // aca vamos a cargar la materia y carreras
                String nombreCarrera = materiaActual[j].getCarrera() ;
                String nombreMateria = materiaActual[j].getNombreMateria();
                if(!trieNuevo.pertenece(nombreCarrera)){ // chequea si la carrera esta, si no la agrego 
                    trieNuevo.agregar(nombreCarrera);
                }
                nodoCarreras primerElem = trieNuevo.buscarUltimo(trieNuevo, nombreCarrera); // consigo la hoja de la carrera 
                NuevaMateria.agregarCarMat(primerElem, nombreMateria); 
            }
        }	    
    }
    /*Cuando generamos el sistemaSiu, por cada infoMateria que entre:
    1)Generamos un objeto de clase Materia, llamado "nuevaMateria"
        2)Hacemos un ciclo que recorra los elementos(Duplas <String carrera, String materia>) de infoMateria,
            creando los tries correspondientes
            3)Por cada nuevo nombre que reciba la materia, hacemos que el último nodo del trieMateria de 
                esa carrera apunte a nuevaMateria
            4)Por cada carrera de trieCarrera, hay que incluir el último nodo de la carrera al objeto materias
                en el atributo infoMateria
        5)Pasamos al siguiente infoMateria y repite
    */
   
    public void inscribir(String estudiante, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
