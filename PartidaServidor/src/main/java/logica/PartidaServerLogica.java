/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.UsuarioDTO;
import generadorrespuestas.IGeneradorRespuestas;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public class PartidaServerLogica implements IPartidaServerLogica{
    
    private IGeneradorRespuestas generadorRespuestas;

    public PartidaServerLogica(IGeneradorRespuestas generadorRespuestas) {
        this.generadorRespuestas = generadorRespuestas;
    }

    @Override
    public void procesarIniciarPartida(List<UsuarioDTO> usuariosDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
