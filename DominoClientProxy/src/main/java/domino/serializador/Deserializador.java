
package domino.serializador;

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
import java.util.List;

/**
 *
 * @author castr
 */
public class Deserializador {

    private final Gson gson;
    private final List<Class<? extends EventoRespuesta>> clasesPermitidas = List.of(
            RespuestaActualizarCantidadFichas.class,
            RespuestaAgregarFichaJugador.class,
            RespuestaColocarFichaTablero.class,
            RespuestaMostrarCasillasDisponibles.class,
            RespuestaMostrarFichasActualizadasDeJugador.class,
            RespuestaMostrarPantallaPartida.class,
            RespuestaOcultarCasillasDisponibles.class,
            RespuestaOtorgarTurno.class
    );
    

    public Deserializador() {
        this.gson = new Gson();
    }

    public EventoRespuesta convertirJSONAEvento(String jsonObject) {
        for(Class<? extends EventoRespuesta> clase: clasesPermitidas) {
            if(isJsonInstanceOf(jsonObject, clase)) {
                return gson.fromJson(jsonObject, clase);
            }
        }
        return null; // Si no coincide con ninguna clase tipo EventoRespuesta
    }

    public <T> boolean isJsonInstanceOf(String json, Class<T> clase) {
        try {
            gson.fromJson(json, clase);
            return true;
        } catch (JsonSyntaxException | NullPointerException e) {
            return false;
        }
    }

}
