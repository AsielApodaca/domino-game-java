/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica;

import dominodto.CasillaDTO;
import dominodto.FichaDominoDTO;
import dominodto.UsuarioDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public interface IPartidaServerLogica {
    public void procesarIniciarPartida(List<UsuarioDTO> usuariosDTO);
    public void procesarFichaSeleccionada(FichaDominoDTO ficha, UsuarioDTO usuario);
    public void procesarCasillaSeleccionada(CasillaDTO casilla, UsuarioDTO usuario);
}
