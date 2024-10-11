/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import dominio.ConfiguracionJuegoEntity;
import dominio.FichaDominoEntity;
import dominio.JugadorDominoEntity;
import dominio.PozoEntity;
import dominio.TableroDominoEntity;
import dominodto.FichaDominoDTO;
import dominodto.JugadorDominoDTO;
import java.util.ArrayList;
import java.util.List;
import logica.controladorFichas.ControladorFichasLogica;
import logica.controladorFichas.IControladorFichasLogica;
import presentacion.partidadomino.fachada.FachadaPartidaDomino;
import presentacion.partidadomino.fachada.IFachadaPartidaDomino;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica {

    private IFachadaPartidaDomino fachadaPartidaDomino;
    private IControladorFichasLogica controladorFicha;
    private PozoEntity pozo;
    private JugadorDominoDTO jugadorDominoDTO;

    private List<FichaDominoDTO> fichasRepartidasDTO;
    private TableroDominoEntity tableroDominoEntity;

    public TableroDominoLogica(ConfiguracionJuegoEntity configuracionEntity) {
        this.fachadaPartidaDomino = new FachadaPartidaDomino();
        this.tableroDominoEntity = new TableroDominoEntity();
        this.fichasRepartidasDTO = new ArrayList<>();
        this.jugadorDominoDTO = new JugadorDominoDTO();
        repartirFichasJugador(configuracionEntity.getCantidadFichas());
        crearPozo();
        controladorFicha = new ControladorFichasLogica(pozo);
    }

    @Override
    public void iniciar() {
        crearPresentacionPartida();
        simularListaFichasDTO(); // Se colocará este metodo cuando el mvc ya tenga listeners
    }

    private void crearPresentacionPartida() {
        fachadaPartidaDomino.iniciarPantalla();
    }

    private void repartirFichasJugador(int cantidadFichas) {
        for (JugadorDominoEntity jugadorEntity : tableroDominoEntity.getListaJugadores()) {
            List<FichaDominoEntity> fichasRepartidasEntity = controladorFicha.repartirFichas(cantidadFichas);
            jugadorEntity.setListaFichasJugador(fichasRepartidasEntity);

            for (FichaDominoEntity fichaEntity : fichasRepartidasEntity) {
                fichasRepartidasDTO.add(
                        new FichaDominoDTO(
                                fichaEntity.getExtremo1(),
                                fichaEntity.getExtremo2())); // Conversión
            }

        }
    }
    
    

    public void mostrarFichas() {
         fachadaPartidaDomino.mostrarFichas(jugadorDominoDTO.getListaFichasJugador());
    }

    private void crearPozo() {
        pozo = new PozoEntity();
    }

    private void simularListaFichasDTO() { // temporal
        List<FichaDominoDTO> listaFichas = new ArrayList<>();

        listaFichas.add(new FichaDominoDTO(6, 6));
        listaFichas.add(new FichaDominoDTO(1, 6));
        listaFichas.add(new FichaDominoDTO(1, 4));
        listaFichas.add(new FichaDominoDTO(4, 4));
        listaFichas.add(new FichaDominoDTO(4, 5));
        listaFichas.add(new FichaDominoDTO(3, 4));
        listaFichas.add(new FichaDominoDTO(3, 6));

        fachadaPartidaDomino.actualizarFichasJugadorLocal(listaFichas);

    }

}
