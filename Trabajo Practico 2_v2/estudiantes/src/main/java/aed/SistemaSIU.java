package aed;

public class SistemaSIU {

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        throw new UnsupportedOperationException("Método no implementado aún");	    
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
