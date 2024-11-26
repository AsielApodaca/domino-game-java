/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domino.respuestas.EventoRespuesta;
import domino.respuestas.RespuestaActualizarCantidadFichas;
import domino.respuestas.RespuestaAgregarFichaJugador;
import domino.respuestas.RespuestaColocarFichaTablero;
import domino.respuestas.RespuestaMostrarCasillasDisponibles;
import domino.respuestas.RespuestaMostrarFichasActualizadasDeJugador;
import domino.respuestas.RespuestaMostrarPantallaPartida;
import domino.respuestas.RespuestaOcultarCasillasDisponibles;
import domino.respuestas.RespuestaOtorgarTurno;
import domino.solicitudes.SolicitudAbandonarSala;
import domino.solicitudes.SolicitudCasillaSeleccionada;
import domino.solicitudes.SolicitudCrearSala;
import domino.solicitudes.SolicitudFichaSeleccionada;
import domino.solicitudes.SolicitudIniciarPartida;
import domino.solicitudes.SolicitudUnirseSala;
import java.util.List;

/**
 *
 * @author olive
 */
public class Deserializador {

    private static Gson gson = new Gson();
    private static final List<Class<?>> clasesPermitidas = List.of(
            // Respuestas
            RespuestaActualizarCantidadFichas.class,
            RespuestaAgregarFichaJugador.class,
            RespuestaColocarFichaTablero.class,
            RespuestaMostrarCasillasDisponibles.class,
            RespuestaMostrarFichasActualizadasDeJugador.class,
            RespuestaMostrarPantallaPartida.class,
            RespuestaOcultarCasillasDisponibles.class,
            RespuestaOtorgarTurno.class,
            // Solicitudes
            SolicitudAbandonarSala.class,
            SolicitudCasillaSeleccionada.class,
            SolicitudCrearSala.class,
            SolicitudFichaSeleccionada.class,
            SolicitudIniciarPartida.class,
            SolicitudUnirseSala.class
    );

    public static <T> T convertirJSONAEvento(String jsonObject) {
        for (Class<?> clase : clasesPermitidas) {
            if (esJsonInstanciaDe(jsonObject, clase)) {
                return (T) gson.fromJson(jsonObject, clase);
            }
        }
        return null; // Si no coincide con ninguna clase permitida
    }

    public static <T> boolean esJsonInstanciaDe(String json, Class<T> clase) {
        try {
            gson.fromJson(json, clase);
            return true;
        } catch (JsonSyntaxException | NullPointerException e) {
            return false;
        }
    }

}
