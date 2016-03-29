/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import logica.CarreraLogicaLocal;
import logica.MateriaLogicaLocal;
import modelo.Carrera;
import modelo.Materia;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author NOREÑA
 */
@Named(value = "materiaVista")
@RequestScoped
public class MateriaVista {

    private InputText txtNumero;
    private InputText txtNombre;
    
    private SelectOneMenu cmbCarreras;
    private ArrayList<SelectItem> itemsCarreras;
    
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    
    private List<Materia> listaMaterias;
    private Materia selectedMateria;
    
    @EJB
    private MateriaLogicaLocal materiaLogica;
    private CarreraLogicaLocal carreraLogica;

    public SelectOneMenu getCmbCarreras() {
        return cmbCarreras;
    }

    public void setCmbCarreras(SelectOneMenu cmbCarreras) {
        this.cmbCarreras = cmbCarreras;
    }

    public ArrayList<SelectItem> getItemsCarreras() {
        try {
            List<Carrera> listaC = carreraLogica.consultarTodas();
            itemsCarreras = new ArrayList<>();
            
            for(Carrera c: listaC){
                itemsCarreras.add(new SelectItem(c.getNumerocarrera(), c.getNombrecarrera()));
            }
        } catch (Exception ex) {
            Logger.getLogger(CarreraVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemsCarreras;
    }

    public void setItemsCarreras(ArrayList<SelectItem> itemsCarreras) {
        this.itemsCarreras = itemsCarreras;
    }
    
    public InputText getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(InputText txtNumero) {
        this.txtNumero = txtNumero;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
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

    public List<Materia> getListaMaterias() {
        if(listaMaterias == null) {
            try {
                listaMaterias = materiaLogica.consultarTodas();
            } catch (Exception ex) {
                Logger.getLogger(CarreraVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public Materia getSelectedMateria() {
        return selectedMateria;
    }

    public void setSelectedMateria(Materia selectedMateria) {
        this.selectedMateria = selectedMateria;
    }    
    
    /**
     * Creates a new instance of MateriaVista
     */
    public MateriaVista() {
    }
    
}
