/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.controladorFichas;

import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominio.PozoEntity;
import dominio.TableroDominoEntity;
import java.util.List;

/**
 *
 * @author hisam
 */
public interface IControladorFichasLogica {
      List<FichaDominoEntity> repartirFichas(int cantidad);   
      void setPozo(PozoEntity pozo);
      void setTableroDomino(TableroDominoEntity tableroDominoEntity);
      List<FichaDominoEntity> obtenerFichasCompatibles(List<FichaDominoEntity> listaFichas);
      List<FichaDominoEntity> obtenerFichasNoCompatibles(List<FichaDominoEntity> listaFichas);
      void removerFichaLista(List<FichaDominoEntity> listaFichas, FichaDominoEntity ficha);
}
      
