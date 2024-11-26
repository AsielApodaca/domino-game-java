/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package partidadomino.fachada;

import listeners.IContenedorListener;
import listeners.SalaEspera.IPresentacionSalaEsperaListener;

/**
 *
 * @author castr
 */
public interface IFachadaPartidaDominoProvisional {

    public IContenedorListener iniciar(IPresentacionSalaEsperaListener presentacionSalaEsperaListener);
}
