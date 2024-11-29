/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import dominodto.UsuarioDTO;
import elementosview.UsuarioPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class UsuarioPanelBuilder implements IUsuarioPanelBuilder {

    private UsuarioPanel usuarioPanel;
    private UsuarioDTO usuarioDTO;

    @Override
    public UsuarioPanel construirUsuarioPanel(UsuarioDTO usuarioDTO) {
        this.usuarioPanel = new UsuarioPanel();
        this.usuarioDTO = usuarioDTO;
        construirDatosUsuarioPanel();
        return usuarioPanel;
    }

    private void construirDatosUsuarioPanel() {
        usuarioPanel.setUsuarioDTO(usuarioDTO);
        usuarioPanel.setNombre(usuarioDTO.getNombre()); // Asignar el nombre del usuario
        Icon icon = new ImageIcon(getClass().getResource("/multimedia/Icono1.png")); // Imagen del avatar
        usuarioPanel.setIcon(icon);
        usuarioPanel.organizarComponentes();
    }
}
