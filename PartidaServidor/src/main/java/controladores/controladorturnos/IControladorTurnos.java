/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controladores.controladorturnos;

import dominio.JugadorDominoEntity;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public interface IControladorTurnos {
    public void asignarTurnosAJugadores(List<JugadorDominoEntity> listaJugadores);
    public JugadorDominoEntity obtenerSiguienteTurno();
    public int turnoOmitido();
    public void reiniciarContadorTurnosOmitidos();
}
