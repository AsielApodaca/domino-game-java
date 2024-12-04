/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import listeners.IContenedorListener;
import listeners.IPresentacionCrearUsuarioListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IFachadaCrearUsuario {

    IContenedorListener iniciarPantalla();

    void suscribirPresentacionListener(IPresentacionCrearUsuarioListener listener);
}
