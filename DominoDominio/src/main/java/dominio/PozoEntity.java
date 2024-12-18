/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

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
public class PozoEntity {

    private List<FichaDominoEntity> listaFichas;

    public PozoEntity() {
        this.listaFichas = new ArrayList<>();
        // Se generan todas las fichas una unica vez y se revuelven
        generarFichas();
    }

    public List<FichaDominoEntity> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<FichaDominoEntity> listaFichas) {
        this.listaFichas = listaFichas;
    }
    
    /**
     * Metodo encargado de generar todas las fichas posibles de domino y
     * mezclarlas de forma aleatoria
     */
    private void generarFichas() {
        // Generar todas las fichas posibles del dominó (del 0-0 al 6-6)
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                listaFichas.add(new FichaDominoEntity(i, j));
            }
        }
        // Mezcla las fichas
        Collections.shuffle(listaFichas);
    }
    
    public List<FichaDominoEntity> sacarFichas(int cantidadFichas) {
        List<FichaDominoEntity> fichas = new ArrayList<>(listaFichas.subList(0, cantidadFichas));
        listaFichas.subList(0, cantidadFichas).clear();
        
        return fichas;
    }
    
    public boolean estaVacio() {
        return listaFichas.isEmpty();
    }
}
