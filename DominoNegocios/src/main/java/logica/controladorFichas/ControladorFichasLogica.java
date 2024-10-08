/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controladorFichas;

import dominio.FichaDominoEntity;
import dominio.PozoEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorFichasLogica {

    private PozoEntity pozo;

    public ControladorFichasLogica(PozoEntity pozo) {
        this.pozo = pozo;
    }

    /**
     * Metodo encargado de repartir la cantidad de fichas especificadas
     *
     * Las fichas se toman de la primera posición de la lista del pozo y se
     * eliminan de la misma a medida que se reparten.
     *
     * @param cantidad
     * @return fichas repartidas
     */
    public List<FichaDominoEntity> repartirFichas(int cantidad) {
        List<FichaDominoEntity> fichasRepartidas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            if (!pozo.getListaFichas().isEmpty()) {
                fichasRepartidas.add(pozo.getListaFichas().remove(0)); // Se saca la primera ficha del pozo
            }
        }
        return fichasRepartidas;
    }

    /**
     * Metodo que saca una ficha del pozo
     *
     * Dicha ficha es la primera disponible del pozo y despues la elimina de la
     * lista de fichas del pozo
     *
     * @return
     */
    public FichaDominoEntity sacarFicha() {
        if (pozo.getListaFichas().isEmpty()) {
            throw new IllegalStateException("No hay fichas en el pozo");
        }
        return pozo.getListaFichas().remove(0);
    }

    public void asignarFichas() {

    }
}
