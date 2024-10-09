/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import contenedorView.FormContenedorController;
import contenedorView.FormContenedorModel;
import contenedorView.FormContenedorView;
import mediadorNavegacion.MediadorNavegacionPantallas;
import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import dominio.PozoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import logica.controladorFichas.ControladorFichasLogica;
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

        // Se instancia MVC que presentará el contenedor de las pantallas del juego
        FormContenedorModel formContenedorModel = new FormContenedorModel();
        FormContenedorView formContenedorView = new FormContenedorView(formContenedorModel);
        FormContenedorController formContenedorController = new FormContenedorController(formContenedorModel, formContenedorView);
        formContenedorController.showView();
        
        // Obtiene el asistente de navegación de pantallas
        MediadorNavegacionPantallas asistenteNavegacionPantallas
                = MediadorNavegacionPantallas.getInstance();

        // Obtiene el contenedor del FormContenedor y se establece al asistente
        JPanel contenedor = formContenedorModel.getContenedorPanel();
        asistenteNavegacionPantallas.setContenedor(contenedor);

        ITableroDominoLogica tableroDominoLogica = new TableroDominoLogica(formContenedorModel);
        tableroDominoLogica.iniciar();

//        //PRUEBAS LOGICA POZO (FUNCIONA)
//        PozoEntity pozo = new PozoEntity();
//        ControladorFichasLogica controladorFichas = new ControladorFichasLogica(pozo);
//        System.out.println("Entrooo!");
//
//        // paso 1: Generar todas las fichas posibles
//        System.out.println("Fichas generadas en el pozo:");
//        imprimirFichas(pozo.getListaFichas());  // Imprimir todas las fichas generadas
//
//        // paso2: repartir fichas
//        int NUMERO_JUGADORES = 4;
//        int FICHAS_POR_JUGADOR = 5;
//        List<JugadorDominoEntity> jugadores = new ArrayList<>();
//        for (int i = 0; i < NUMERO_JUGADORES; i++) {
//            String nombre = "Jugador " + (i + 1);
//            Icon icon = null; // Puedes agregar un ícono si lo deseas
//            List<FichaDominoEntity> fichasJugador = controladorFichas.repartirFichas(FICHAS_POR_JUGADOR);
//            JugadorDominoEntity jugador = new JugadorDominoEntity(nombre, icon, fichasJugador);
//            jugadores.add(jugador);
//
//            System.out.println("\nFichas del " + nombre + ":");
//            imprimirFichas(fichasJugador);
//        }
//
//        // paso 3: verifica fichas despues de repartir
//        System.out.println("\nFichas restantes en el pozo:");
//        imprimirFichas(pozo.getListaFichas());
//
//        // paso 4: sacar ficha
//        try {
//            FichaDominoEntity fichaSacada = controladorFichas.sacarFicha();
//            System.out.println("\nFicha sacada del pozo: " + fichaSacada);
//        } catch (IllegalStateException e) {
//            System.out.println(e.getMessage());
//        }
//
//        // paso 5: volver a verificar ficahs despues de haber sacado una fiocha
//        System.out.println("\nFichas restantes en el pozo después de sacar una ficha:");
//        imprimirFichas(pozo.getListaFichas());
//    }
//
//    // metodo auxiliar solo para imprimir 
//    public static void imprimirFichas(List<FichaDominoEntity> fichas) {
//        if (fichas == null || fichas.isEmpty()) {
//            System.out.println("No hay fichas.");
//        } else {
//            for (FichaDominoEntity ficha : fichas) {
//                System.out.println(ficha);
//            }
//
//        }

    }
}
