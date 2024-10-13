/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.controladorFichas;

import dominio.FichaDominoEntity;
import dominio.PozoEntity;
import java.util.List;

/**
 *
 * @author hisam
 */
public interface IControladorFichasLogica {
      List<FichaDominoEntity> repartirFichas(int cantidad);   
      void setPozo(PozoEntity pozoEntity);
}
