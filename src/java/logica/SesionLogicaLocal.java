/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.Local;
import modelo.Docente;
import modelo.Estudiante;

/**
 *
 * @author NOREÑA
 */
@Local
public interface SesionLogicaLocal {
    public Estudiante iniciarSesionEstudiante(Long documento, String clave) throws Exception;
    public Docente iniciarSesionDocente(Long documeto, String clave) throws Exception;
}
