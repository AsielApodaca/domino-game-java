/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.salaesperalogica;

import listeners.IContenedorListener;
import partidadomino.fachada.FachadaPartidaDominoProvisional;
import partidadomino.fachada.IFachadaPartidaDominoProvisional;
import setup.ISetup;

/**
 *
 * @author castr
 */
public class SalaEsperaLogica implements ISalaEsperaLogica {

    private IContenedorListener contenedorListener;
    private ISetup setup;
    private IFachadaPartidaDominoProvisional fachadaPartidaDominoProvisional;

    public SalaEsperaLogica(ISetup setup) {
        this.setup = setup;
        this.fachadaPartidaDominoProvisional = new FachadaPartidaDominoProvisional();
    }

    @Override
    public IContenedorListener iniciar() {
        mostrarPresentacionSalaEspera();
        return contenedorListener;
    }
    
    public void mostrarPresentacionSalaEspera() {
        this.contenedorListener = fachadaPartidaDominoProvisional.iniciar();
    }

}
