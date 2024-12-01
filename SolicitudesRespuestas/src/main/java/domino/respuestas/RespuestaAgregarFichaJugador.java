/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.respuestas;

import dominodto.FichaDominoDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class RespuestaAgregarFichaJugador extends EventoRespuesta{
    public static final boolean ES_PARA_TODOS = false;
    private FichaDominoDTO fichaDominoDTO;
    
    public RespuestaAgregarFichaJugador(String idCliente, FichaDominoDTO fichaDominoDTO) {
        super(idCliente);
        this.fichaDominoDTO = fichaDominoDTO;
    }

    public FichaDominoDTO getFichaDominoDTO() {
        return fichaDominoDTO;
    }
    
    @Override
    public boolean esParaTodos() {
        return ES_PARA_TODOS;
    }
}
