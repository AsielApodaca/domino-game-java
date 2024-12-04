/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.menudominologica;

import listeners.IContenedorListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IMenuDominoLogica {
    /**
     * Inicia el menú y establece los listeners necesarios.
     * 
     * @return Un contenedor de listeners configurado para manejar eventos en la sala.
     */
    public IContenedorListener iniciar();
    
    public void cerrar();
    
    public boolean estaOperando();
}
