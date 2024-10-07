/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PozoEntity {

    private List<FichaDominoEntity> listaFichas;

    public PozoEntity() {
        this.listaFichas = new ArrayList<>();
    }

    public List<FichaDominoEntity> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaDominoEntity> listaFichas) {
        this.listaFichas = listaFichas;
    }

}
