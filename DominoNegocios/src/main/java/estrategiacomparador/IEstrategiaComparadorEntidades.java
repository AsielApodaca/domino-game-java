/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package estrategiacomparador;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import java.util.List;

/**
 *
 * @author asielapodaca
 */
public interface IEstrategiaComparadorEntidades {
    FichaDominoEntity comparar(FichaDominoDTO fichaDTO, List<FichaDominoEntity> listaFichasEntity);
}
