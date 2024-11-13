/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domino.solicitudes.EventoSolicitud;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import dominodto.CasillaDTO;

/**
 *
 * @author castr
 */
public class Serializador {

    private final Gson gson;

    public Serializador() {
        this.gson = new Gson();
    }

    public String convertirEventoAJSON(EventoSolicitud solicitud) {
//        JsonObject jsonRequest = new JsonObject();
//
//        if (solicitud instanceof SolicitudCasillaSeleccionada) {
//            SolicitudCasillaSeleccionada solicitudCasilla = (SolicitudCasillaSeleccionada) solicitud;
//            CasillaDTO casilla = solicitudCasilla.getCasillaDTO();
//
//            JsonObject fichaJson = new JsonObject();
//            fichaJson.addProperty("locacionx", casilla.getLocacionX());
//            fichaJson.addProperty("locaciony", casilla.getLocacionY());
//            fichaJson.addProperty("rotacion", casilla.getRotacion());
//
//            if (casilla.getFichaDominoDTO() != null) {
//                JsonObject domino = new JsonObject();
//                domino.addProperty("extremo1", casilla.getFichaDominoDTO().getValorExtremo1());
//                domino.addProperty("extremo2", casilla.getFichaDominoDTO().getValorExtremo2());
//                fichaJson.add("ficha", domino);
//            }
//
//            jsonRequest.add("datos", fichaJson);
//        }

        return gson.toJson(solicitud);
    }
}
