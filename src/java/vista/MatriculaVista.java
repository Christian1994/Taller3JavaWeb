/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.MatriculaLogicaLocal;
import modelo.Estudiante;
import modelo.Materia;
import modelo.Matricula;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author jsnar
 */
@Named(value = "matriculaVista")
@RequestScoped
public class MatriculaVista {

    private InputText txtDocumentoEstudiante;
    private InputText txtNumeroMateria;
    private InputText txtNota;
    private InputText txtEstado;
    
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    
    private List<Matricula> listaMatriculas;
    private Matricula selectedMatricula;
    
    @EJB
    private MatriculaLogicaLocal matriculaLogica;

    public InputText getTxtDocumentoEstudiante() {
        return txtDocumentoEstudiante;
    }

    public void setTxtDocumentoEstudiante(InputText txtDocumentoEstudiante) {
        this.txtDocumentoEstudiante = txtDocumentoEstudiante;
    }

    public InputText getTxtNumeroMateria() {
        return txtNumeroMateria;
    }

    public void setTxtNumeroMateria(InputText txtNumeroMateria) {
        this.txtNumeroMateria = txtNumeroMateria;
    }

    public InputText getTxtNota() {
        return txtNota;
    }

    public void setTxtNota(InputText txtNota) {
        this.txtNota = txtNota;
    }

    public InputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(InputText txtEstado) {
        this.txtEstado = txtEstado;
    }

    public List<Matricula> getListaMatriculas() {
        if(listaMatriculas == null) {
            try {
                listaMatriculas = matriculaLogica.consultarTodas();
            } catch (Exception ex) {
                Logger.getLogger(MatriculaVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    public Matricula getSelectedMatricula() {
        return selectedMatricula;
    }

    public void setSelectedMatricula(Matricula selectedMatricula) {
        this.selectedMatricula = selectedMatricula;
    }

    public CommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(CommandButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public CommandButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(CommandButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public CommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }
    
    
    //Mostrar por interfaz la matrícula seleccionada
    public void onRowSelect(SelectEvent event) {
        this.selectedMatricula = (Matricula) event.getObject();
        
        this.txtDocumentoEstudiante.setValue(selectedMatricula.getEstudiante().getDocumentoestudiante());
        this.txtNumeroMateria.setValue(selectedMatricula.getMateria().getNumeromateria());
        this.txtNota.setValue(selectedMatricula.getNota());
        this.txtEstado.setValue(selectedMatricula.getEstado());
        
        // Se deshabilita el botón registrar para permitir que la matricula se puede modificar o eliminar       
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);
        this.txtDocumentoEstudiante.setDisabled(true);
    }
    
    //Limpia los campos y reinicia los valores
    public void limpiar(){
        this.txtDocumentoEstudiante.setValue("");
        this.txtNumeroMateria.setValue("");
        this.txtNota.setValue("");
        this.txtEstado.setValue("");
        
        this.txtDocumentoEstudiante.setDisabled(false);
        this.btnRegistrar.setDisabled(false);
        this.btnModificar.setDisabled(true);
        this.btnEliminar.setDisabled(true);
    }
    
    // Método registrar
    public void action_registrar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            objEstudiante.setDocumentoestudiante(Long.parseLong(this.txtDocumentoEstudiante.getValue().toString()));
            Materia objMateria = new Materia();
            objMateria.setNumeromateria(Integer.parseInt(this.txtNumeroMateria.getValue().toString()));
            
            Matricula objMatricula = new Matricula();
            objMatricula.setEstudiante(objEstudiante);
            objMatricula.setMateria(objMateria);
            objMatricula.setNota(Double.parseDouble(this.txtNota.getValue().toString()));
            objMatricula.setEstado(this.txtEstado.getValue().toString());
            
            matriculaLogica.registrarMatricula(objMatricula);
            listaMatriculas = null;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de creación de matrícula", "La matrícula fue hecha con éxito."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }
    
    public void action_modificar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            objEstudiante.setDocumentoestudiante(Long.parseLong(this.txtDocumentoEstudiante.getValue().toString()));
            Materia objMateria = new Materia();
            objMateria.setNumeromateria(Integer.parseInt(this.txtNumeroMateria.getValue().toString()));
            
            Matricula objMatricula = new Matricula();
            objMatricula.setEstudiante(objEstudiante);
            objMatricula.setMateria(objMateria);
            objMatricula.setNota(Double.parseDouble(this.txtNota.getValue().toString()));
            objMatricula.setEstado(this.txtEstado.getValue().toString());
            
            matriculaLogica.modificarMatricula(objMatricula);
            listaMatriculas = null;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de modificación de matrícula", "La matrícula fue modificada con éxito."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }
    
    public void action_eliminar(){
        try {
            Estudiante objEstudiante = new Estudiante();
            objEstudiante.setDocumentoestudiante(Long.parseLong(this.txtDocumentoEstudiante.getValue().toString()));
            Materia objMateria = new Materia();
            objMateria.setNumeromateria(Integer.parseInt(this.txtNumeroMateria.getValue().toString()));
            
            Matricula objMatricula = new Matricula();
            objMatricula.setEstudiante(objEstudiante);
            objMatricula.setMateria(objMateria);
            objMatricula.setNota(Double.parseDouble(this.txtNota.getValue().toString()));
            objMatricula.setEstado(this.txtEstado.getValue().toString());
            
            matriculaLogica.eliminarMatricula(objMatricula);
            listaMatriculas = null;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de eliminación de matrícula", "La matrícula fue eliminada con éxito."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }
    
    /**
     * Creates a new instance of MatriculaVista
     */
    public MatriculaVista() {
    }
    
}
