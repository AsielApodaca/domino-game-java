/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import listeners.IContenedorListener;

/**
 *
 * @author asielapodaca
 */
public interface IFachadaContenedorPantallas {
    public void iniciarContenedorDePantallas();
    public void establecerPantalla(IContenedorListener pantallaController);
}
