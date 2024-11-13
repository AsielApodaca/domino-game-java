/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partidaservidor;

import adapterEntidades.AdapterCasilla;
import adapterEntidades.AdapterFichaDomino;
import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominio.TableroDominoEntity;
import domino.respuestas.RespuestaQuitarFichaUsuario;
import dominodto.CasillaDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PartidaServidorSolicitudCasillaSeleccionada {

    private TableroDominoEntity tableroEntity;
    private PartidaServidor partidaServidor;
    private AdapterCasilla adpaterCasilla;
    private AdapterFichaDomino adapterFichaDomino;
    
    public PartidaServidorSolicitudCasillaSeleccionada() {
        tableroEntity = new TableroDominoEntity();
        partidaServidor = new PartidaServidor();
        adapterFichaDomino = new AdapterFichaDomino();
        adpaterCasilla = new AdapterCasilla(adapterFichaDomino);
    }
    
     public void colocarFichaSeleccionadaEnTableroEntity(CasillaDTO casillaDTO) {
        CasillaEntity casilla;
        RespuestaQuitarFichaUsuario quitarFichaUsuario;
        CasillaDTO casillaDTRespuesta;
        
        switch(casillaDTO.getExtremo()) {
            case CasillaDTO.MULA:
                casilla = colocarMula();
                break;
            case CasillaDTO.EXTREMO1:
                casilla = colocarFichaExtremo1();
                break;
            case CasillaDTO.EXTREMO2:
                casilla = colocarFichaExtremo2();
                break;
            default:
                casilla = null;
                System.out.println("La casilla seleccionada no pertenece a ningun extremo");
        }
        tableroEntity.setFichaSeleccionada(
                null);
        casillaDTRespuesta = adpaterCasilla.adaptToDTO(
                casilla);
        quitarFichaUsuario = new RespuestaQuitarFichaUsuario(
                casillaDTRespuesta.getFichaDominoDTO());
        
        
        partidaServidor.sendResponse(quitarFichaUsuario);
        
    }
     
    private CasillaEntity colocarMula() {
        FichaDominoEntity mula = tableroEntity.getFichaSeleccionada();
        tableroEntity.colocarMula(mula);
        CasillaEntity casillaMula = tableroEntity.getCasillaMula();
        return casillaMula;
    }
    
    private CasillaEntity colocarFichaExtremo1() {
        FichaDominoEntity ficha = tableroEntity.getFichaSeleccionada();
        tableroEntity.colocarFichaExtremo1(ficha);
        CasillaEntity casillaExtremo = tableroEntity.getCasillaExtremo1();
        return casillaExtremo;
    }
    
    private CasillaEntity colocarFichaExtremo2() {
        FichaDominoEntity ficha = tableroEntity.getFichaSeleccionada();
        tableroEntity.colocarFichaExtremo2(ficha);
        CasillaEntity casillaExtremo = tableroEntity.getCasillaExtremo2();
        return casillaExtremo;
    }
    
    
}
