/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.dominopresentacion;

import dev.itson.dominopresentacion.tablerodomino.TableroDominoController;
import dev.itson.dominopresentacion.tablerodomino.TableroDominoModel;
import dev.itson.dominopresentacion.tablerodomino.TableroDominoView;
import javax.swing.JFrame;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class DominoPresentacion {

    public static void main(String[] args) {
        TableroDominoModel tableroDominoModel = new TableroDominoModel() ;
        TableroDominoView tableroDominoView = new TableroDominoView(tableroDominoModel);
        TableroDominoController tableroDominoController = new TableroDominoController(tableroDominoModel, tableroDominoView) ;
    }
}
