/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Pozo;

import dominio.FichaDominoEntity;
import dominio.PozoEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class PozoLogica {

    private List<FichaDominoEntity> listaFichas = new ArrayList<>();
    private PozoEntity pozo;

    public PozoLogica(PozoEntity pozo) {
        this.pozo = pozo;
    }

    /**
     * Metodo encargado de generar todas las fichas posibles de domino y
     * mezclarlas de forma aleatoria
     */
    public void generarFichas() {
        listaFichas = new ArrayList<>();
        // Generar todas las fichas posibles del dominó (del 0-0 al 6-6)
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                listaFichas.add(new FichaDominoEntity(i, j));
            }
        }
        // Mezcla las fichas
        Collections.shuffle(listaFichas);
        pozo.setListaFichas(listaFichas);
    }

    public List<FichaDominoEntity> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaDominoEntity> listaFichas) {
        this.listaFichas = listaFichas;
    }

}
