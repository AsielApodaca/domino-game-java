/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import mediadorNavegacion.MediadorNavegacionPantallas;
import contenedorView.FormContenedorView;
import javax.swing.JPanel;
import logica.tableroDominoLogica.ITableroDominoLogica;
import logica.tableroDominoLogica.TableroDominoLogica;

/**
 *
 * @author asielapodaca
 */
public class AppRunner {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Se instancia y muestra el form que contendrá las pantallas
        FormContenedorView formContenedorView = new FormContenedorView();
        formContenedorView.setVisible(true);
        
        // Obtiene el asistente de navegación de pantallas
        MediadorNavegacionPantallas asistenteNavegacionPantallas =
                MediadorNavegacionPantallas.getInstance();
        
        // Obtiene el contenedor del FormContenedorView y se asigna al asistente
        JPanel contenedor = formContenedorView.getContenedor();
        asistenteNavegacionPantallas.setContenedor(contenedor);
        
        ITableroDominoLogica tableroDominoLogica = new TableroDominoLogica();
        tableroDominoLogica.crearYMostrarPantalla();
        
    }
    
}
