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
import dominio.PozoEntity;
import java.util.List;
import javax.swing.JPanel;
import logica.Pozo.PozoLogica;
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
        tableroDominoLogica.crearYMostrarPantalla();

        
        
        
        
        
        
        //PRUEBAS LOGICA POZO (FUNCIONA)
//        PozoEntity pozo = new PozoEntity(null); //pozo vacio
//        PozoLogica pozoLogica = new PozoLogica(pozo);
//
//        ControladorFichasLogica controladorFichas = new ControladorFichasLogica(pozoLogica);
//        System.out.println("Entrooo!");
//        
//        // paso 1: Generar todas las fichas posibles
//        pozoLogica.generarFichas();
//        System.out.println("Fichas generadas en el pozo:");
//        imprimirFichas(pozo.getListaPiezas());  // Imprimir todas las fichas generadas
//
//        // paso2: repartir fichas
//        System.out.println("\nRepartiendo fichas a un jugador...");
//        List<FichaDomino> fichasJugador = controladorFichas.repartirFichas(7);
//        System.out.println("Fichas del jugador:");
//        imprimirFichas(fichasJugador);
//        
//        // paso 3: verifica fichas despues de repartir
//        System.out.println("\nFichas restantes en el pozo:");
//        imprimirFichas(pozo.getListaPiezas());
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
//        imprimirFichas(pozo.getListaPiezas());
//    }
//
//    // metodo auxiliar solo para imprimir 
//    public static void imprimirFichas(List<FichaDomino> fichas) {
//        if (fichas == null || fichas.isEmpty()) {
//            System.out.println("No hay fichas.");
//        } else {
//            for (FichaDominoEntity ficha : fichas) {
//                System.out.println(ficha); 
//            }
//
//        }
//    }
    }
}
