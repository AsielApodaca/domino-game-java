/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorusuario;

import dominio.UsuarioEntity;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class GestorUsuario implements IGestorUsuario {

    private UsuarioEntity usuarioActual;

    @Override
    public UsuarioEntity crearUsuario(String nombre, String rutaIcono) {
        usuarioActual = new UsuarioEntity(nombre);
        usuarioActual.setFuenteIcono(rutaIcono);
        return usuarioActual;
    }

    @Override
    public UsuarioEntity getUsuario() {
        return usuarioActual;
    }

}
