
package domino.serializador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import domino.respuestas.EventoRespuesta;
import domino.solicitudes.EventoSolicitud;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Clase para deserializar objetos JSON basándose en el atributo "tipo".
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Deserializador {

    private static final Gson gson = new Gson();
    private static final Map<String, Class<?>> mapaDeClases = new HashMap<>();

    static {
        inicializarMapaDeClases();
    }

    private static void inicializarMapaDeClases() {
        // Escanear los paquetes y agregar subclases de EventoSolicitud y EventoRespuesta
        agregarClasesAlMapa("domino.solicitudes", EventoSolicitud.class);
        agregarClasesAlMapa("domino.respuestas", EventoRespuesta.class);
    }

    private static void agregarClasesAlMapa(String paquete, Class<?> claseBase) {
        Reflections reflections = new Reflections(paquete);
        Set<? extends Class<?>> subclases = reflections.getSubTypesOf(claseBase);
        for (Class<?> subclase : subclases) {
            mapaDeClases.put(subclase.getSimpleName(), subclase);
        }
    }

    /**
     * Deserializa un JSON a la clase correspondiente según el atributo "tipo".
     * 
     * @param json el JSON a deserializar
     * @param <T> el tipo esperado
     * @return una instancia de la clase correspondiente, o null si no coincide
     */
    public static <T> T convertirJSONAEvento(String json) {
        try {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String tipo = jsonObject.get("nombreEvento").getAsString();
            Class<?> clase = mapaDeClases.get(tipo);
            if (clase != null) {
                return (T) gson.fromJson(json, clase);
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            // Manejar el error si el JSON está mal formado o falta el atributo "tipo"
        }
        return null; // Si no coincide con ninguna clase permitida
    }

    /**
     * Comprueba si un JSON es instancia de una clase específica.
     * 
     * @param json el JSON a comprobar
     * @param clase la clase a verificar
     * @param <T> el tipo de la clase
     * @return true si el JSON corresponde a la clase, false en caso contrario
     */
    public static <T> boolean esJsonInstanciaDe(String json, Class<T> clase) {
        try {
            T objetoDeserializado = convertirJSONAEvento(json);
            // Comparar el tipo del objeto deserializado con la clase esperada
            return objetoDeserializado != null && clase.isInstance(objetoDeserializado);
        } catch (JsonSyntaxException | NullPointerException e) {
            return false;
        }
    }
}