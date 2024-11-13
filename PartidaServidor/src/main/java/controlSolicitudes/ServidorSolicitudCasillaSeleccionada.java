/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlSolicitudes;

import adapterEntidades.AdapterCasilla;
import adapterEntidades.AdapterFichaDomino;
import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominio.TableroDominoEntity;
import domino.respuestas.RespuestaAgregarFichaTablero;
import domino.respuestas.RespuestaQuitarFichaUsuario;
import dominodto.CasillaDTO;
import partidaservidor.PartidaServidor;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ServidorSolicitudCasillaSeleccionada {

    private TableroDominoEntity tableroEntity;
    private PartidaServidor partidaServidor;
    private AdapterCasilla adpaterCasilla;
    private AdapterFichaDomino adapterFichaDomino;

    /**
     * Constructor de ServidorSolicitudCasillaSeleccionada. Inicializa los
     * adaptadores y el tablero de dominó necesario para procesar las
     * solicitudes.
     */
    public ServidorSolicitudCasillaSeleccionada() {
        tableroEntity = new TableroDominoEntity();
        partidaServidor = new PartidaServidor();
        adapterFichaDomino = new AdapterFichaDomino();
        adpaterCasilla = new AdapterCasilla(adapterFichaDomino);
    }

    /**
     * Coloca una ficha en el tablero en función de la posición extrema
     * especificada en el objeto CasillaDTO.
     *
     * @param casillaDTO Objeto que contiene la información de la casilla y el
     * extremo en el cual colocar la ficha.
     */
    public void colocarFichaSeleccionadaEnTableroEntity(CasillaDTO casillaDTO) {
        CasillaEntity casilla;
        CasillaDTO casillaDTORespuesta;

        // Determina el extremo del tablero en el que se colocará la ficha según la información de casillaDTO
        switch (casillaDTO.getExtremo()) {
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

        // Actualiza la ficha seleccionada en el tablero y adapta la entidad a DTO para enviar una respuesta
        tableroEntity.setFichaSeleccionada(
                null);
        casillaDTORespuesta = adpaterCasilla.adaptToDTO(
                casilla);

        // Envía respuestas para actualizar el estado de las fichas en el tablero y en la interfaz del usuario
        enviarRespuestaQuitarFichaUsuario(new RespuestaQuitarFichaUsuario(
                casillaDTORespuesta.getFichaDominoDTO()));
        enviarRespuestaAgregarFichaTablero(new RespuestaAgregarFichaTablero(casillaDTORespuesta));

    }

    /**
     * Coloca una ficha de tipo "mula" en el centro del tablero.
     *
     * @return CasillaEntity de la casilla donde se coloca la mula.
     */
    private CasillaEntity colocarMula() {
        FichaDominoEntity mula = tableroEntity.getFichaSeleccionada();
        tableroEntity.colocarMula(mula);
        CasillaEntity casillaMula = tableroEntity.getCasillaMula();
        return casillaMula;
    }

    /**
     * Coloca una ficha en el extremo 1 del tablero.
     *
     * @return CasillaEntity de la casilla donde se coloca la ficha en el
     * extremo 1.
     */
    private CasillaEntity colocarFichaExtremo1() {
        FichaDominoEntity ficha = tableroEntity.getFichaSeleccionada();
        tableroEntity.colocarFichaExtremo1(ficha);
        CasillaEntity casillaExtremo = tableroEntity.getCasillaExtremo1();
        return casillaExtremo;
    }

    /**
     * Coloca una ficha en el extremo 2 del tablero.
     *
     * @return CasillaEntity de la casilla donde se coloca la ficha en el
     * extremo 2.
     */
    private CasillaEntity colocarFichaExtremo2() {
        FichaDominoEntity ficha = tableroEntity.getFichaSeleccionada();
        tableroEntity.colocarFichaExtremo2(ficha);
        CasillaEntity casillaExtremo = tableroEntity.getCasillaExtremo2();
        return casillaExtremo;
    }

    /**
     * Envía una respuesta para notificar al usuario de la eliminación de la
     * ficha seleccionada.
     *
     * @param respuesta Objeto RespuestaQuitarFichaUsuario con la información de
     * la ficha que se quita.
     */
    private void enviarRespuestaQuitarFichaUsuario(RespuestaQuitarFichaUsuario respuesta) {
        partidaServidor.sendResponse(respuesta);
    }

    /**
     * Envía una respuesta para notificar la colocación de una ficha en el
     * tablero.
     *
     * @param respuesta Objeto RespuestaAgregarFichaTablero con la información
     * de la casilla donde se coloca la ficha.
     */
    private void enviarRespuestaAgregarFichaTablero(RespuestaAgregarFichaTablero respuesta) {
        partidaServidor.sendResponse(respuesta);
    }

}
