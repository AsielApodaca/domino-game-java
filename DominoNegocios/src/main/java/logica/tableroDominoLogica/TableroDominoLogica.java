/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import dominio.PozoEntity;
import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;
import presentacion.partidadomino.fachada.FachadaPartidaDomino;
import presentacion.partidadomino.fachada.IFachadaPartidaDomino;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica {
    
    private IFachadaPartidaDomino fachadaPartidaDomino;

    public TableroDominoLogica() { 
        this.fachadaPartidaDomino = new  FachadaPartidaDomino();
    }

    @Override
    public void iniciar() {
        crearPresentacionPartida();
        simularListaFichasDTO(); // Se colocará este metodo cuando el mvc ya tenga listeners
    }
    
    private void crearPresentacionPartida() {
        fachadaPartidaDomino.iniciarPantalla();
    }
    
    private void crearPozo() {
        PozoEntity pozo = new PozoEntity();
    }
    private void simularListaFichasDTO() { // temporal
        List<FichaDominoDTO> listaFichas = new ArrayList<>();
        
        listaFichas.add(new FichaDominoDTO(6, 6));
        listaFichas.add(new FichaDominoDTO(1, 6));
        listaFichas.add(new FichaDominoDTO(1, 4));
        listaFichas.add(new FichaDominoDTO(4, 4));
        listaFichas.add(new FichaDominoDTO(4, 5));
        listaFichas.add(new FichaDominoDTO(3, 4));
        listaFichas.add(new FichaDominoDTO(3, 6));
        
        fachadaPartidaDomino.actualizarFichasJugadorLocal(listaFichas);
        
    }

   
    }


