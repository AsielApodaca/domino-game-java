/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcbuscarsalas;

import dominodto.SalaDTO;
import javax.swing.JPanel;
import listeners.IBuscarSalaViewListener;
import listeners.IContenedorListener;
import notificador.IPresentacionBuscarSalaNotificador;

/**
 *
 * @author olive
 */
public class BuscarSalaController implements IBuscarSalaViewListener, IContenedorListener {

    private IPresentacionBuscarSalaNotificador buscarSalaNotificador ;
    private BuscarSalaView view ;
    private BuscarSalaModel model ;
    
    public BuscarSalaController(BuscarSalaView view, BuscarSalaModel model) {
        this.model = model ;
        this.view = view ;
    }
    
    @Override
    public void onBtnUnirseSalaPresionado(SalaDTO sala) {
        buscarSalaNotificador.notificarBtnUnirseSalaPresionado(sala);
    }

    @Override
    public void onBtnSalirPresionado() {
        buscarSalaNotificador.notificarBtnSalirPresionado();
    }
    
    public void setNotificador(IPresentacionBuscarSalaNotificador buscarSalaNotificador) {
        this.buscarSalaNotificador = buscarSalaNotificador ;
    }

    @Override
    public void onEscalaChange(float escala) {
        
    }

    @Override
    public JPanel obtenerView() {
        return this.view ;
    }

    public void setBuscarSalaNotificador(IPresentacionBuscarSalaNotificador buscarSalaNotificador) {
        this.buscarSalaNotificador = buscarSalaNotificador;
    }
    
    public void mostrarSala(SalaDTO sala) {
        model.agregarPanel(sala);
        view.repintarPaneles();
    }

    public void ocultarSala(SalaDTO sala) {
        model.removerPanel(sala);
        view.repintarPaneles();
    }
    
}
