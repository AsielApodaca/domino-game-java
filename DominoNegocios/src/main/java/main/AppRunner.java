package main;

import dominio.ConfiguracionJuegoEntity;
import dominio.JugadorDominoEntity;
import dominio.SalaEntity;
import java.util.ArrayList;
import java.util.List;
import listeners.IContenedorListener;
import logica.contenedorpantallas.ContenedorPantallasLogica;
import logica.contenedorpantallas.IContenedorPantallasLogica;
import logica.tableroDominoLogica.ITableroDominoLogica;
import logica.tableroDominoLogica.TableroDominoLogica;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class AppRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Inicia los procesos del contenedor de pantallas
        IContenedorPantallasLogica contenedorPantallasLogica = new ContenedorPantallasLogica();
        contenedorPantallasLogica.iniciarContenedorDePantallas();
       
        // Sala simulada
        SalaEntity salaEntity = new SalaEntity();
        ConfiguracionJuegoEntity configuracionDeSala = new ConfiguracionJuegoEntity();
        configuracionDeSala.setFichasPorJugador(7);
        salaEntity.setConfiguracionPartida(configuracionDeSala);
        
        // Inicia los procesos del tablero de domino
        ITableroDominoLogica tableroDominoLogica = new TableroDominoLogica(salaEntity);
        IContenedorListener pantalla =
                tableroDominoLogica.iniciar();
        
        contenedorPantallasLogica.abrirPantalla(pantalla);
        
//        // Paso 1: Crear el pozo y el controlador de fichas
//        PozoEntity pozo = new PozoEntity();
//        ControladorFichasLogica controladorFichas = new ControladorFichasLogica(pozo);
//        System.out.println("Fichas generadas en el pozo:");
//        imprimirFichas(pozo.getListaFichas());
//
//        // Paso 2: Repartir fichas a los jugadores
//        int NUMERO_JUGADORES = 4;
//        int FICHAS_POR_JUGADOR = 5;
//        List<JugadorDominoEntity> jugadores = new ArrayList<>();
//        for (int i = 0; i < NUMERO_JUGADORES; i++) {
//            String nombre = "Jugador " + (i + 1);
//            List<FichaDominoEntity> fichasJugador = controladorFichas.repartirFichas(FICHAS_POR_JUGADOR);
//            JugadorDominoEntity jugador = new JugadorDominoEntity(nombre, null, fichasJugador);
//            jugadores.add(jugador);
//            System.out.println("\nFichas del " + nombre + ":");
//            imprimirFichas(fichasJugador);
//        }
//
//        // Paso 3: Verificar fichas restantes en el pozo
//        System.out.println("\nFichas restantes en el pozo:");
//        imprimirFichas(pozo.getListaFichas());
//
////        // Paso 4: Sacar una ficha del pozo
////        try {
////            FichaDominoEntity fichaSacada = controladorFichas.sacarFicha();
////            System.out.println("\nFicha sacada del pozo: " + fichaSacada);
////        } catch (IllegalStateException e) {
////            System.out.println(e.getMessage());
////        }
////
////        // Paso 5: Verificar fichas restantes en el pozo después de sacar una
////        System.out.println("\nFichas restantes en el pozo después de sacar una ficha:");
////        imprimirFichas(pozo.getListaFichas());
//
//        // Paso 6: Crear el controlador de turnos y asignar turnos
//        ControladorTurno controladorTurno = new ControladorTurno(jugadores);
//        controladorTurno.asignarTurno();
//
//        // Paso 7: Obtener y mostrar el jugador con la mula más alta
//        JugadorDominoEntity jugadorConMulaMasAlta = controladorTurno.encontrarJugadorConMulaMasAlta();
//        FichaDominoEntity fichaMulaMasAlta = controladorTurno.obtenerFichaMulaMasAlta();
//
//        if (jugadorConMulaMasAlta != null) {
//            System.out.println("\nJugador con la mula mas alta: " + jugadorConMulaMasAlta.getNombre());
//            System.out.println("Ficha mula mas alta: " + fichaMulaMasAlta);
//        } else {
//            System.out.println("\nNo se encontro ninguna mula entre los jugadores");
//        }
//
//        // Paso 8: Mostrar el orden de los turnos
//        System.out.println("\nOrden de los turnos:");
//        for (int i = 0; i < NUMERO_JUGADORES; i++) {
//            JugadorDominoEntity jugadorActual = controladorTurno.obtenerJugadorActual();
//            System.out.println("Turno " + (i + 1) + ": " + jugadorActual.getNombre());
//            controladorTurno.avanzarTurno();
//        }
//    }
//
//    public static void imprimirFichas(List<FichaDominoEntity> fichas) {
//        if (fichas == null || fichas.isEmpty()) {
//            System.out.println("No hay fichas.");
//        } else {
//            for (FichaDominoEntity ficha : fichas) {
//                System.out.println(ficha);
//            }
//        }
//    }
    }
}
