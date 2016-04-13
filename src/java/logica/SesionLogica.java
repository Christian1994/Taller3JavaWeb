/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Docente;
import modelo.Estudiante;
import persistencia.DocenteFacadeLocal;
import persistencia.EstudianteFacadeLocal;

/**
 *
 * @author NOREÑA
 */
@Stateless
public class SesionLogica implements SesionLogicaLocal {

    @EJB
    private EstudianteFacadeLocal estudianteDAO;
    
    @EJB
    private DocenteFacadeLocal docenteDAO;
    
    @Override
    public Estudiante iniciarSesionEstudiante(Long documento, String clave) throws Exception {
        if(documento == null || clave == null || clave.equals("")){
            throw new Exception("Los datos de ingreso son Obligatorios.");
        }
        
        Estudiante e = estudianteDAO.find(documento);
        
        if(e != null){
            if(!e.getClaveestudiante().equals(clave)){
                throw new Exception("Clave incorrecta.");
            }
        }
        
        return e;
    }

    @Override
    public Docente iniciarSesionDocente(Long documento, String clave) throws Exception {
        if(documento == null || clave == null || clave.equals("")){
            throw new Exception("Los datos de ingreso son Obligatorios.");
        }
        
        Docente d = docenteDAO.find(documento);
        
        if(d != null){
            if(!d.getClavedocente().equals(clave)){
                throw new Exception("Clave incorrecta.");
            }
        }
        
        return d;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
