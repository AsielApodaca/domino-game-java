/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setup;

import domino.fachada.FachadaServidorProxy;
import domino.fachada.IFachadaServidorProxy;
import generadorrespuestas.GeneradorRespuestas;
import logica.IPartidaServerLogica;
import logica.PartidaServerLogica;
import manejadorsolicitudserverproxy.GestorSolicitudServerProxy;

/**
 *
 * @author asielapodaca
 */
public class Setup implements ISetup{

    private IPartidaServerLogica partidaServerLogica;
    private GestorSolicitudServerProxy gestorSolicitudServerProxy;
    private GeneradorRespuestas generadorRespuestas;
    private IFachadaServidorProxy fachadaServidorProxy;
    
    @Override
    public void iniciar() {
        iniciarLogica();
        iniciarManejadorSolicitudesServerProxy();
        iniciarGeneradorDeRespuestasServerProxy();
        iniciarConexionServerProxy();
    }
    
    private void iniciarLogica() {
        partidaServerLogica = new PartidaServerLogica(generadorRespuestas);
    }
    
    private void iniciarManejadorSolicitudesServerProxy() {
        // Aqui falta la cadena de manejadores
        //gestorSolicitudServerProxy = new GestorSolicitudServerProxy(manejadorCabeza);
    }
    
    private void iniciarGeneradorDeRespuestasServerProxy() {
        generadorRespuestas = new GeneradorRespuestas(fachadaServidorProxy);
    }
    
    private void iniciarConexionServerProxy() {
        fachadaServidorProxy = new FachadaServidorProxy();
        fachadaServidorProxy.agregarListener(gestorSolicitudServerProxy);
    }
    
}
