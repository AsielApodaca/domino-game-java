/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.gestorusuario;

import dominio.UsuarioEntity;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IGestorUsuario {

    /**
     * Crea un nuevo usuario con los datos especificados.
     *
     * @param nombre Nombre del usuario
     * @param rutaIcono Ruta del ícono del usuario
     */
    void crearUsuario(String nombre, String rutaIcono);

    /**
     * Obtiene el usuario actual del sistema.
     *
     * @return El usuario actual como UsuarioEntity
     */
    UsuarioEntity getUsuario();

}
