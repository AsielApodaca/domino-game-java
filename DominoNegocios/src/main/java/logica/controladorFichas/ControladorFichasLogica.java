/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.controladorFichas;

import dominio.CasillaEntity;
import dominio.FichaDominoEntity;
import dominio.PozoEntity;
import dominio.TableroDominoEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ControladorFichasLogica implements IControladorFichasLogica{

    private PozoEntity pozo;
    private TableroDominoEntity tableroDominoEntity;

    public ControladorFichasLogica() {
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
    public List<FichaDominoEntity> repartirFichas(int cantidadFichas) {
        List<FichaDominoEntity> fichasRepartidas = new ArrayList<>();
        for (int i = 0; i < cantidadFichas; i++) {
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
     * @return primer ficha disponible
     */
    public FichaDominoEntity sacarFicha() {
        if (pozo.getListaFichas().isEmpty()) {
            throw new IllegalStateException("No hay fichas en el pozo");
        }
        return pozo.getListaFichas().remove(0);
    }

    public void setPozo(PozoEntity pozo) {
        this.pozo = pozo;
    }
    
    public void setTableroDomino(TableroDominoEntity tableroDominoEntity) {
        this.tableroDominoEntity = tableroDominoEntity;
    }
    
    /**
     * Método que retorna arreglo booleano de extremos compatibles
     * 
     * La posición 0 representa el extremo 1 del tablero 
     * La posición 1 representa el extremo 2 del tablero 
     * 
     * El valor de cada posición es true si cualquiera de los valores de la ficha
     * es compatible con el extremo que representa la posición.
     * @param fichaDominoEntity Ficha a comparar con extremos del tablero.
     * @return arreglo booleano de extremos compatibles
     */
    public boolean[] obtenerExtremosCompatibles(FichaDominoEntity fichaDominoEntity) {
        boolean[] extremosCompatibles = new boolean[2];
        
        int fichaExtremo1 = fichaDominoEntity.getExtremo1();
        int fichaExtremo2 = fichaDominoEntity.getExtremo2();
        
        int tableroExtremo1 = tableroDominoEntity.getValorExtremo1();
        int tableroExtremo2 = tableroDominoEntity.getValorExtremo2();
        
        if(tableroExtremo1 == -1) {
            extremosCompatibles[0] = true;
            extremosCompatibles[1] = true;
        } else {
            extremosCompatibles[0] = tableroExtremo1 == fichaExtremo1 || tableroExtremo1 == fichaExtremo2;
            extremosCompatibles[1] = tableroExtremo2 == fichaExtremo1 || tableroExtremo2 == fichaExtremo2;
        }
        
        return extremosCompatibles;
    }
    
    /**
     * Método que retorna lista de fichas compatibles con cualquier extremo del tablero
     * Si aun no hay una mula inicial colocada, retorna la ficha más apropiada
     * para ser la ficha inicial
     * 
     * @param listaFichas lista de fichas a comparar si son compatibles con cualquier extremo
     * @return lista de fichas compatibles con cualquier extremo del tablero,
     * si aun no hay una mula inicial colocada, retorna la ficha más apropiada
     * para ser la ficha inicial
     */
    @Override
    public List<FichaDominoEntity> obtenerFichasCompatibles(List<FichaDominoEntity> listaFichas) {
        List<FichaDominoEntity> fichasCompatibles = new ArrayList<>();
        if(tableroDominoEntity.getValorExtremo1() == -1) { // Todavía no hay mula puesta y solo debe haber una ficha compatible
            System.out.println("if");
            FichaDominoEntity fichaInicial = obtenerMulaMayorEntreFichas(listaFichas);
            if(fichaInicial == null) {
                fichaInicial = obtenerFichaMayorEntreFichas(listaFichas);
            }
            fichasCompatibles.add(fichaInicial);
        } else { // Ya existe una mula y puede haber varias fichas compatibles
            System.out.println("else");
            for(FichaDominoEntity ficha : listaFichas) {
                boolean[] extremosCompatibles = obtenerExtremosCompatibles(ficha);
                System.out.println(extremosCompatibles[0] + " " + extremosCompatibles[1]);
                if(extremosCompatibles[0] || extremosCompatibles[1]) {
                    fichasCompatibles.add(ficha);
                }
            }
        }
        
        
        return fichasCompatibles;
    }
    
    /**
     * Método que retorna la mula mayor entre una lista de fichas
     * 
     * Si no se encuentrá una mula se retorna null
     * @param listaFichas lista de fichas
     * @return mula mayor entre una lista de fichas, si no se encuentrá una mula se retorna null
     */
    private FichaDominoEntity obtenerMulaMayorEntreFichas(List<FichaDominoEntity> listaFichas) {
        FichaDominoEntity mula = new FichaDominoEntity(-1, -1);
        for(FichaDominoEntity ficha : listaFichas) {
            int extremo1 = ficha.getExtremo1();
            int extremo2 = ficha.getExtremo2();
            if(extremo1 == extremo2) {
                
                int sumaExtremosMula = extremo1 + extremo2;
                int sumaExtremosMulaGuardada = mula.getExtremo1() + mula.getExtremo2();
                
                if(sumaExtremosMula > sumaExtremosMulaGuardada) {
                    mula = ficha;
                }
                
            }
        }
        
        return mula.getExtremo1() == -1 ? null : mula;
    }
    
    /**
     * Método que retorna la primera ficha mayor entre lista de fichas
     * 
     * la ficha mayor será la que su suma de ambós extremos sea mayor a la de los demás
     * @param listaFichas
     * @return 
     */
    private FichaDominoEntity obtenerFichaMayorEntreFichas(List<FichaDominoEntity> listaFichas) {
        FichaDominoEntity fichaMayor = listaFichas.getFirst();
        for(FichaDominoEntity ficha : listaFichas) {
            int sumaExtremosFicha = ficha.getExtremo1() + ficha.getExtremo2();
            int sumaExtremosFichaMayor = fichaMayor.getExtremo1() + fichaMayor.getExtremo2();
            
            if(sumaExtremosFicha > sumaExtremosFichaMayor) {
                fichaMayor = ficha;
            }
        }
        
        return fichaMayor;
    }
    
    /**
     * Método que retorna las fichas que no son compatibles con ningún extremo
     * @param listaFichas Lista de fichas a comparar si no som compatibles
     * @return las fichas que no son compatibles con ningún extremo
     */
    @Override
    public List<FichaDominoEntity> obtenerFichasNoCompatibles(List<FichaDominoEntity> listaFichas) {
        List<FichaDominoEntity> listaFichasNoCompatibles = new ArrayList<>();
        List<FichaDominoEntity> listaFichasCompatibles = obtenerFichasCompatibles(listaFichas);
        for(FichaDominoEntity ficha : listaFichas) {
            if(!listaFichasCompatibles.contains(ficha)) {
                listaFichasNoCompatibles.add(ficha);
            }
        }
        
        return listaFichasNoCompatibles;
    }
    

    
    @Override
    public void removerFichaLista(List<FichaDominoEntity> listaFichas, FichaDominoEntity ficha) {
        listaFichas.remove(ficha);
    }
    
}
