/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author castr
 */
public class PozoDTO {

    private List<FichaDominoDTO> listaFichas;

    public PozoDTO() {
        listaFichas = new ArrayList<>();
    }

    public List<FichaDominoDTO> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaDominoDTO> listaFichas) {
        this.listaFichas = listaFichas;
    }

}
