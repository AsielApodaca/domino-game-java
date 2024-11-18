package mapeodto;

import dominio.FichaDominoEntity;
import dominio.CasillaEntity;
import dominio.TableroDominoEntity;
import dominio.UsuarioEntity;
import dominodto.FichaDominoDTO;
import dominodto.CasillaDTO;
import dominodto.TableroDominoDTO;
import dominodto.UsuarioDTO;

/**
 * Clase encargada de mapear las entidades del dominio a sus respectivos 
 * Data Transfer Objects (DTO). Este mapeador facilita la transferencia de datos
 * entre capas, asegurando que las estructuras de dominio no se expongan
 * directamente al resto del sistema.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MapeadorDTO {

    /**
     * Mapea una entidad de ficha de dominó a su correspondiente DTO.
     *
     * @param ficha La entidad de la ficha de dominó a mapear.
     * @return Un objeto {@link FichaDominoDTO} que representa la ficha de dominó.
     */
    public static FichaDominoDTO fichaEntityADTO(FichaDominoEntity ficha) {
        int extremo1 = ficha.getExtremo1();
        int extremo2 = ficha.getExtremo2();
        return new FichaDominoDTO(extremo1, extremo2);
    }

    /**
     * Mapea una entidad de casilla a su correspondiente DTO.
     *
     * @param casilla La entidad de la casilla a mapear.
     * @return Un objeto {@link CasillaDTO} que representa la casilla.
     */
    public static CasillaDTO casillaEntityADTO(CasillaEntity casilla) {
        CasillaDTO casillaDTO = new CasillaDTO();
        casillaDTO.setLocacionX(casilla.getLocacionX());
        casillaDTO.setLocacionY(casilla.getLocacionY());
        casillaDTO.setRotacion(casilla.getRotacion());

        if (casilla.getFichaDomino() != null) {
            FichaDominoDTO fichaDominoDTO = fichaEntityADTO(casilla.getFichaDomino());
            casillaDTO.setFichaDominoDTO(fichaDominoDTO);
        }

        return casillaDTO;
    }

    /**
     * Mapea una entidad de tablero de dominó a su correspondiente DTO.
     *
     * @param tableroDomino La entidad del tablero de dominó a mapear.
     * @return Un objeto {@link TableroDominoDTO} que representa el tablero de dominó.
     */
    public static TableroDominoDTO tableroDominoEntityADTO(TableroDominoEntity tableroDomino) {
        int anchoTablero = tableroDomino.getAnchoTablero();
        int altoTablero = tableroDomino.getAltoTablero();
        TableroDominoDTO tableroDominoDTO = new TableroDominoDTO(anchoTablero, altoTablero);

        CasillaEntity casilla = tableroDomino.obtenerPrimerElemento();
        if (casilla != null) { // El tablero tiene al menos una ficha
            do { 
                tableroDominoDTO.addPosicion(casillaEntityADTO(casilla));
            } while ((casilla = casilla.getSiguienteCasilla()) != null);
        }

        return tableroDominoDTO;
    }

    /**
     * Mapea una entidad de usuario a su correspondiente DTO.
     *
     * @param usuarioEntity La entidad de usuario a mapear.
     * @return Un objeto {@link UsuarioDTO} que representa al usuario.
     */
    public static UsuarioDTO UsuarioEntityADTO(UsuarioEntity usuarioEntity) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIcon(usuarioEntity.getIcon());
        usuarioDTO.setIdCliente(usuarioEntity.getIdCliente());
        usuarioDTO.setNombre(usuarioEntity.getNombre());
        return usuarioDTO;
    }
}
