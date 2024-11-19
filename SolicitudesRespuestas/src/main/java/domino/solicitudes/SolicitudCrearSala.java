/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.solicitudes;

import dominodto.UsuarioDTO;

/**
 *
 * @author castr
 */
public class SolicitudCrearSala extends EventoSolicitud{
    //config partida

    int size ;
    
    public SolicitudCrearSala(UsuarioDTO usuarioDTO) {
        super(usuarioDTO);
        this.size = 3 ; //TEMPORAL PARA PRUEBA
    }
    //config partida
}
