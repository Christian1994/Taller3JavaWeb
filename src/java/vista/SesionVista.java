/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import logica.SesionLogicaLocal;
import modelo.Docente;
import modelo.Estudiante;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

/**
 *
 * @author NOREÑA
 */
@Named(value = "sesionVista")
@RequestScoped
public class SesionVista {

    @EJB
    private SesionLogicaLocal sesionLogica;
    
    private InputText txtUsuario;
    private Password txtClave;
    private CommandButton btnIngresar;
    private CommandButton btnCerrar;

    public InputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(InputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public Password getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(Password txtClave) {
        this.txtClave = txtClave;
    }

    public CommandButton getBtnIngresar() {
        return btnIngresar;
    }

    public void setBtnIngresar(CommandButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }

    public CommandButton getBtnCerrar() {
        return btnCerrar;
    }

    public void setBtnCerrar(CommandButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }
    
    public void funcion_ingresar(){
        try {
            String urlDocente, urlEstudiante;
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            urlDocente = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/docente/gestionDocentes.xhtml"));
            urlEstudiante = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/estudiante/gestionEstudiantes.xhtml"));
            Long documento = Long.parseLong(txtUsuario.getValue().toString());
            String clave = txtClave.getValue().toString();
            Estudiante e = sesionLogica.iniciarSesionEstudiante(documento, clave);
            
            if(e != null){
                // Se loguea un estudiante
                extContext.getSessionMap().put("tipo", "estudiante");
                extContext.getSessionMap().put("usuario", e);
                extContext.redirect(urlEstudiante);                
            }
            else{
               Docente d = sesionLogica.iniciarSesionDocente(documento, clave);
               if(d != null){
                    // Se loguea un docente
                    extContext.getSessionMap().put("tipo", "docente");
                    extContext.getSessionMap().put("usuario", d);
                    extContext.redirect(urlDocente);                   
               }
               else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario NO existe."));                   
               }
            }        
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
        }
    }
    
    public void cerrarSesion_action()
    {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext= context.getExternalContext();
            extContext.getSessionMap().remove("tipo");
            extContext.getSessionMap().remove("usuario");
            String url=extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,"/index.xhtml"));
            extContext.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(SesionVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates a new instance of SesionVista
     */
    public SesionVista() {
    }
    
}
