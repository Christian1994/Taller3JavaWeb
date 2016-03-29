/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Matricula;
import persistencia.MatriculaFacadeLocal;

/**
 *
 * @author NOREÑA
 */
@Stateless
public class MatriculaLogica implements MatriculaLogicaLocal {

    @EJB
    MatriculaFacadeLocal matriculaDAO;
    
    @Override
    public void registrarMatricula(Matricula matricula) throws Exception {
        if(matricula == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(matricula.getEstudiante().getNombreestudiante().equals("") || matricula.getEstudiante().getNombreestudiante() == null){
                throw new Exception("No se ha seleccionado ningún Estudiante.");
            }
            if(matricula.getMateria().getNombremateria().equals("") || matricula.getMateria().getNombremateria() == null){
                throw new Exception("No se ha seleccionado ninguna Materia.");
            }
            if(matricula.getNota() == 0.0 || matricula.getNota() == null){
                throw new Exception("Campo Nota Obligatorio.");
            }
            if(matricula.getEstado().equals("") || matricula.getEstado() == null){
                throw new Exception("Campo Estado Obligatorio.");
            }
        }
        
        Matricula objMatricula = matriculaDAO.find(matricula.getMatriculaPK());
        if(objMatricula != null){
            throw new Exception("Matrícula ya existe.");
        }
        else{
            matriculaDAO.create(matricula);
        }
    }

    @Override
    public void modificarMatricula(Matricula matricula) throws Exception {
                if(matricula == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(matricula.getEstudiante() == null){
                throw new Exception("No se ha seleccionado ningún Estudiante.");
            }
            if(matricula.getMateria() == null){
                throw new Exception("No se ha seleccionado ninguna Materia.");
            }
            if(matricula.getNota() == null){
                throw new Exception("Campo Nota Obligatorio.");
            }
            if(matricula.getEstado().equals("") || matricula.getEstado() == null){
                throw new Exception("Campo Estado Obligatorio.");
            }
        }
        
        Matricula objMatricula = matriculaDAO.find(matricula.getMatriculaPK());
        if(objMatricula == null){
            throw new Exception("La Matrícula a modificar no existe.");
        }
        else{
            objMatricula.setNota(matricula.getNota());
            objMatricula.setEstado(matricula.getEstado());
            matriculaDAO.edit(matricula);
        }
    }

    @Override
    public void eliminarMatricula(Matricula matricula) throws Exception {
        Matricula objMatricula = matriculaDAO.find(matricula.getMatriculaPK());
        
        if(objMatricula == null){
            throw new Exception("La Matrícula a eliminar no existe.");
        }
        else{
            matriculaDAO.remove(matricula);
        }
    }

    @Override
    public Matricula consultarxCodigo(Integer codigo) throws Exception {
        if(codigo == 0 || codigo == null){
            throw new Exception("El código es Obligatorio.");
        }
        else{
            return matriculaDAO.find(codigo);
        }
    }

    @Override
    public List<Matricula> consultarTodas() throws Exception {
        return matriculaDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
