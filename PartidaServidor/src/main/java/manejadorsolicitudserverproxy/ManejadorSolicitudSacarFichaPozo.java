/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadorsolicitudserverproxy;

import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudSacarFichaPozo;
import logica.IPartidaServerLogica;

public class ManejadorSolicitudSacarFichaPozo extends ManejadorSolicitudServerProxy {

    /**
     * Constructor del manejador con la lógica del servidor de partidas.
     *
     * @param partidaServerLogica La lógica del servidor de la partida.
     */
    public ManejadorSolicitudSacarFichaPozo(IPartidaServerLogica partidaServerLogica) {
        super(partidaServerLogica);
    }

    /**
     * Verifica si el evento es una instancia de
     * {@link SolicitudSacarFichaPozo}.
     *
     * @param evento El evento que se quiere verificar.
     * @return {@code true} si el evento es una solicitud de sacar ficha del
     * pozo.
     */
    @Override
    protected boolean puedeManejar(EventoSolicitud evento) {
        return evento instanceof SolicitudSacarFichaPozo;
    }

    /**
     * Procesa el evento {@link SolicitudSacarFichaPozo}.
     *
     * Este método llama a la lógica del servidor para procesar la acción de
     * sacar una ficha del pozo.
     *
     * @param evento El evento que contiene la información de la solicitud.
     */
    @Override
    protected void procesar(EventoSolicitud evento) {
        String idCliente = evento.getIdCliente();
        partidaServerLogica.procesarSacarFichaPozo(idCliente);
    }

}
