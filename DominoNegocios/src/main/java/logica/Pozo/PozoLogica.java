/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Pozo;

import dominio.FichaDomino;
import dominio.Pozo;
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

    private List<FichaDomino> listaPiezas = new ArrayList<>();
    private Pozo pozo;

    public PozoLogica(Pozo pozo) {
        this.pozo = pozo;
    }

    /**
     * Metodo encargado de generar todas las fichas posibles de domino y
     * mezclarlas de forma aleatoria
     */
    public void generarFichas() {
        listaPiezas = new ArrayList<>();
        // Generar todas las fichas posibles del dominó (del 0-0 al 6-6)
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                listaPiezas.add(new FichaDomino(i, j));
            }
        }
        // Mezcla las fichas
        Collections.shuffle(listaPiezas);
        pozo.setListaPiezas(listaPiezas);
    }

    public List<FichaDomino> getListaPiezas() {
        return listaPiezas;
    }

    public void setListaPiezas(List<FichaDomino> listaPiezas) {
        this.listaPiezas = listaPiezas;
    }

}
