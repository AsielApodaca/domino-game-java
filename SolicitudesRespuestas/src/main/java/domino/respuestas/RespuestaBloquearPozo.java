/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;


/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaBloquearPozo extends EventoRespuesta {
    public static final boolean ES_PARA_TODOS = false;
    public RespuestaBloquearPozo(String idCliente) {
        super(idCliente);
    }

    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }

}
