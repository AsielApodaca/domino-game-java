package mvc;

import builder.UsuarioPanelBuilder;
import dominodto.UsuarioDTO;
import elementosview.UsuarioPanel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class SalaEsperaModel {

    private List<UsuarioDTO> listaUsarios;
    private List<UsuarioPanel> listaUsuariosPaneles;
    private boolean esPantallaDeAnfitrion;

    public SalaEsperaModel() {
        this.listaUsarios = new ArrayList<>();
        this.listaUsuariosPaneles = new ArrayList<>();
    }
    
    public void agregarUsuarioPanel(UsuarioDTO usuario) {
        UsuarioPanelBuilder builder = new UsuarioPanelBuilder();
        UsuarioPanel panel = builder.construirUsuarioPanel(usuario);
        listaUsuariosPaneles.add(panel);
        listaUsarios.add(usuario);
    }

    public void removerUsuarioPanel(UsuarioDTO usuario) {

    }

    public boolean isEsPantallaDeAnfitrion() {
        return esPantallaDeAnfitrion;
    }

    public void setEsPantallaDeAnfitrion(boolean esPantallaDeAnfitrion) {
        this.esPantallaDeAnfitrion = esPantallaDeAnfitrion;
    }

    public List<UsuarioDTO> getListaUsarios() {
        return listaUsarios;
    }

    public void setListaUsarios(List<UsuarioDTO> listaUsarios) {
        this.listaUsarios = listaUsarios;
    }

    public List<UsuarioPanel> getListaUsuariosPaneles() {
        return listaUsuariosPaneles;
    }

    public void setListaUsuariosPaneles(List<UsuarioPanel> listaUsuariosPaneles) {
        this.listaUsuariosPaneles = listaUsuariosPaneles;
    }

}
