/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

/**
 *
 * @author olive
 */
public class RespuestaAprobarCreacionSala extends EventoRespuesta {

    private boolean ES_PARA_TODOS = false ;
    
    public RespuestaAprobarCreacionSala(String idCliente) {
        super(idCliente);
    }

    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS ;
    }
    
}
