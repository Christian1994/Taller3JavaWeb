/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Estudiante;
import persistencia.EstudianteFacadeLocal;

/**
 *
 * @author NOREÑA
 */
@Stateless
public class EstudianteLogica implements EstudianteLogicaLocal {

    @EJB
    EstudianteFacadeLocal estudianteDAO;
    
    @Override
    public void registrarEstudiante(Estudiante estudiante) throws Exception {
        if(estudiante == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(estudiante.getDocumentoestudiante() == 0 || estudiante.getDocumentoestudiante() == null){
                throw new Exception("Campo Documento Estudiante Obligatorio.");
            }
            if(estudiante.getNombreestudiante().equals("") || estudiante.getNombreestudiante() == null){
                throw new Exception("Campo Nombre Estudiante Obligatorio.");
            }
            if(estudiante.getApellidoestudiante().equals("") || estudiante.getApellidoestudiante() == null){
                throw new Exception("Campo Apellido Estudiante Obligatorio.");
            }
            if(estudiante.getCorreoestudiante().equals("") || estudiante.getCorreoestudiante() == null){
                throw new Exception("Campo E-mail Estudiante Obligatorio.");
            }
            if(!estudiante.getCorreoestudiante().contains("@") && 
                    (!estudiante.getCorreoestudiante().endsWith(".com") || !estudiante.getCorreoestudiante().endsWith(".es"))){
                throw new Exception("E-mail inválído. Ejemplos válidos: \"example@something.com\" o \"example@something.es\"");
            }
            if(estudiante.getSemestreestudiante() == 0 || estudiante.getSemestreestudiante() == null){
                throw new Exception("Campo Semestre Estudiante Obligatorio.");
            }
            if(estudiante.getClaveestudiante().equals("") || estudiante.getClaveestudiante() == null){
                throw new Exception("Campo Clave Estudiante Obligatorio.");
            }
        }
        
        Estudiante objEstudiante = estudianteDAO.find(estudiante.getDocumentoestudiante());
        if(objEstudiante != null){
            throw new Exception("Estudiante ya existe.");
        }
        else{
            estudianteDAO.create(estudiante);
        }
    }

    @Override
    public void modificarEstudiante(Estudiante estudiante) throws Exception {
        if(estudiante == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(estudiante.getDocumentoestudiante() == 0 || estudiante.getDocumentoestudiante() == null){
                throw new Exception("Campo Documento Estudiante Obligatorio.");
            }
            if(estudiante.getNombreestudiante().equals("") || estudiante.getNombreestudiante() == null){
                throw new Exception("Campo Nombre Estudiante Obligatorio.");
            }
            if(estudiante.getApellidoestudiante().equals("") || estudiante.getApellidoestudiante() == null){
                throw new Exception("Campo Apellido Estudiante Obligatorio.");
            }
            if(estudiante.getCorreoestudiante().equals("") || estudiante.getCorreoestudiante() == null){
                throw new Exception("Campo E-mail Estudiante Obligatorio.");
            }
            if(!estudiante.getCorreoestudiante().contains("@") && 
                    (!estudiante.getCorreoestudiante().endsWith(".com") || !estudiante.getCorreoestudiante().endsWith(".es"))){
                throw new Exception("E-mail inválído. Ejemplos válidos: \"example@something.com\" o \"example@something.es\"");
            }
            if(estudiante.getSemestreestudiante() == 0 || estudiante.getSemestreestudiante() == null){
                throw new Exception("Campo Semestre Estudiante Obligatorio.");
            }
            if(estudiante.getClaveestudiante().equals("") || estudiante.getClaveestudiante() == null){
                throw new Exception("Campo Clave Estudiante Obligatorio.");
            }
        }
        
        Estudiante objEstudiante = estudianteDAO.find(estudiante.getDocumentoestudiante());
        if(objEstudiante == null){
            throw new Exception("Estudiante a modificar no existe.");
        }
        else{
            objEstudiante.setNombreestudiante(estudiante.getNombreestudiante());
            objEstudiante.setApellidoestudiante(estudiante.getApellidoestudiante());
            objEstudiante.setCorreoestudiante(estudiante.getCorreoestudiante());
            objEstudiante.setSemestreestudiante(estudiante.getSemestreestudiante());
            objEstudiante.setClaveestudiante(estudiante.getClaveestudiante());
            estudianteDAO.edit(objEstudiante);
        }
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) throws Exception {
        if(estudiante == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(estudiante.getDocumentoestudiante() == 0 || estudiante.getDocumentoestudiante() == null){
                throw new Exception("El Documento de Estudiante es Obligatorio.");
            }
        }
        
        Estudiante objEstudiante = estudianteDAO.find(estudiante.getDocumentoestudiante());
        if(objEstudiante == null){
            throw new Exception("El Estudiante a eliminar no existe.");
        }
        else{
            if(objEstudiante.getMatriculaList().size() > 0){
                throw new Exception("El Estudiante tiene matrículas asociadas.");
            }
            estudianteDAO.remove(estudiante);
        }
    }

    @Override
    public Estudiante consultarxcodigo(Integer codigo) throws Exception {
        if(codigo==null || codigo==0){
            throw new Exception("El código es Obligatorio.");
        }else{
            return estudianteDAO.find(codigo);
        }
    }

    @Override
    public List<Estudiante> consultarTodos() throws Exception {
        return estudianteDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
