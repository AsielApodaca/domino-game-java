/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import contenedorView.FormContenedorModel;
import dominio.FichaDominoEntity;
import dominio.PozoEntity;
import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;
import mediadorNavegacion.MediadorNavegacionPantallas;
import presentacion.partidadomino.PartidaDominoController;
import presentacion.partidadomino.PartidaDominoModel;
import presentacion.partidadomino.PartidaDominoView;
//import presentacion.tablerodomino.*;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica {

//    TableroDominoModel model;
//    TableroDominoView view;
//    TableroDominoController controller;
    private PartidaDominoModel model;
    private PartidaDominoView view;
    private PartidaDominoController controller;
    
    FormContenedorModel proveedorDeEscala;

    public TableroDominoLogica(FormContenedorModel proveedorDeEscala) {
        this.proveedorDeEscala = proveedorDeEscala;
    }

    
    
    @Override
    public void iniciar() {
        crearPresentacionPartida();
        // simularListaFichasDTO() // Se colocará este metodo cuando el mvc ya tenga listeners
    }
    
    private void crearPresentacionPartida() {
        this.model = new PartidaDominoModel();
        this.model.setEscala(proveedorDeEscala.getScale());
        simularListaFichasDTO(); // Asigna lista simulada de fichas de domino a modelo
        this.view = new PartidaDominoView(model);
        this.controller = new PartidaDominoController(model, view, proveedorDeEscala);
        //this.model.addListener(this);
        MediadorNavegacionPantallas.getInstance().navegarA(view);
    }
    
    private void crearPozo() {
        PozoEntity pozo = new PozoEntity();
    }
    public void simularListaFichasDTO() { // temporal
        List<FichaDominoDTO> listaFichas = new ArrayList<>();
        
        listaFichas.add(new FichaDominoDTO(6, 6));
        listaFichas.add(new FichaDominoDTO(1, 6));
        listaFichas.add(new FichaDominoDTO(1, 4));
        listaFichas.add(new FichaDominoDTO(4, 4));
        listaFichas.add(new FichaDominoDTO(4, 5));
        listaFichas.add(new FichaDominoDTO(3, 4));
        listaFichas.add(new FichaDominoDTO(3, 6));
        
        model.setListaFichasJugadorLocal(listaFichas);
        
    }

   
    }


