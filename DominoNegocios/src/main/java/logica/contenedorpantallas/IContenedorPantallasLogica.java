/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.contenedorpantallas;

import listeners.IContenedorListener;

/**
 *
 * @author asielapodaca
 */
public interface IContenedorPantallasLogica {
    public void iniciarContenedorDePantallas();
    public void abrirPantalla(IContenedorListener pantalla);
}
