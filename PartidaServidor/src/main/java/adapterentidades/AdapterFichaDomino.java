/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterentidades;

import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class AdapterFichaDomino implements IAdapterFichaDomino {

    private List<FichaDominoEntity> listaFichasDelJuego;

    public AdapterFichaDomino(List<FichaDominoEntity> listaFichasDelJuego) {
        this.listaFichasDelJuego = listaFichasDelJuego;
    }
    
    @Override
    public FichaDominoDTO adaptToDTO(FichaDominoEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("La entidad Ficha Domino no puede ser nula");
        }
        return new FichaDominoDTO(
                entity.getExtremo1(),
                entity.getExtremo2()
        );
    }

    @Override
    public FichaDominoEntity adaptToEntity(FichaDominoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        int valorExtremo1DTO = dto.getValorExtremo1();
        int valorExtremo2DTO = dto.getValorExtremo2();
        
        for(FichaDominoEntity ficha : listaFichasDelJuego) {
            if(ficha.getExtremo1() == valorExtremo1DTO && ficha.getExtremo2() == valorExtremo2DTO) {
                return ficha;
            }
        }
        return null; // No encontró la ficha
    }
    
    @Override
    public List<FichaDominoDTO> adaptListToDTO(List<FichaDominoEntity> listaFichasEntity){
        List<FichaDominoDTO> listaFichasDominoDTO = new ArrayList<>();
         for (FichaDominoEntity fichaEntity : listaFichasEntity) {
               listaFichasDominoDTO.add(adaptToDTO(fichaEntity));
         }
         return listaFichasDominoDTO;
    }


}
