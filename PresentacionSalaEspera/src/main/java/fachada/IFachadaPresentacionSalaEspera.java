/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import dominodto.UsuarioDTO;
import listeners.IContenedorListener;
import listeners.IPresentacionSalaEsperaListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IFachadaPresentacionSalaEspera {

    public IContenedorListener iniciarPantalla(Boolean esAnfitrion);

    public void agregarUsuario(UsuarioDTO usuarioDTO);

    public void removerUsuario(UsuarioDTO usuarioDTO);

    public void subscribirPresentacionListener(IPresentacionSalaEsperaListener listener);

}
