/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author castr
 */
public class Pozo {

    private List<FichaDomino> listaPiezas;

    public Pozo(List<FichaDomino> listaPiezas) {
        this.listaPiezas = listaPiezas;
    }

    public List<FichaDomino> getListaPiezas() {
        return listaPiezas;
    }

    public void setListaPiezas(List<FichaDomino> listaPiezas) {
        this.listaPiezas = listaPiezas;
    }

}
