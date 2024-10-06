/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import contenedorView.FormContenedorModel;
import dominio.FichaDominoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import mediadorNavegacion.MediadorNavegacionPantallas;
import presentacion.tablerodomino.ITableroDominoModeloListener;
import presentacion.tablerodomino.TableroDominoController;
import presentacion.tablerodomino.TableroDominoModel;
import presentacion.tablerodomino.TableroDominoView;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica, ITableroDominoModeloListener {

    TableroDominoModel model;
    TableroDominoView view;
    TableroDominoController controller;
    
    FormContenedorModel formContenedorModel;

    public TableroDominoLogica(FormContenedorModel formContenedorModel) {
        this.formContenedorModel = formContenedorModel;
    }

    
    
    @Override
    public void crearYMostrarPantalla() {
        this.model = new TableroDominoModel();
        this.view = new TableroDominoView(model);
        this.controller = new TableroDominoController(model, view, this.formContenedorModel);
        this.model.addListener(this);
        MediadorNavegacionPantallas.getInstance().navegarA(view);
    }

    @Override
    public void onChangeListaFichasUsuario(List<FichaDominoEntity> listaFichasUsuario) {
        return;
    }

    @Override
    public void onChangeFichaComparativa(FichaDominoEntity fichaComparativa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onChangeFichasComparativas(List<FichaDominoEntity> listaFichasComparativas) {
        model.getListaFichasValidas().clear();
        FichaDominoEntity fichaIzquierda = model.getFichasComparativas().getFirst();
        FichaDominoEntity fichaDerecha = model.getFichasComparativas().getLast();
        model.getListaFichasUsuario().forEach(ficha -> {
            boolean coincideConIzquierda = ficha.getExtremo1().equals(fichaIzquierda.getExtremo1())
                    || ficha.getExtremo2().equals(fichaIzquierda.getExtremo1());

            boolean coincideConDerecha = ficha.getExtremo1().equals(fichaDerecha.getExtremo2())
                    || ficha.getExtremo2().equals(fichaDerecha.getExtremo2());

            if (coincideConIzquierda || coincideConDerecha) {
                model.getListaFichasValidas().add(ficha);
            }
            
            System.out.println(model.getListaFichasValidas().toString());
        });
    }

    @Override
    public void onChangeFichaSeleccionada(FichaDominoEntity fichaSeleccionada) {
        FichaDominoEntity fichaIzquierda = model.getFichasComparativas().getFirst();
        FichaDominoEntity fichaDerecha = model.getFichasComparativas().getLast();
        
        boolean sePuedeColocarIzquierda = false;
        boolean sePuedeColocarDerecha = false;

        List<String> extremosIzquierdaUsados = new ArrayList<>();
        List<String> extremosDerechaUsados = new ArrayList<>();

        if (fichaSeleccionada.getExtremo1().equals(fichaIzquierda.getExtremo1())) {
            sePuedeColocarIzquierda = true;
            extremosIzquierdaUsados.add("extremo1");
        }
        if (fichaSeleccionada.getExtremo2().equals(fichaIzquierda.getExtremo1())) {
            sePuedeColocarIzquierda = true;
            extremosIzquierdaUsados.add("extremo2");
        }

        if (fichaSeleccionada.getExtremo1().equals(fichaDerecha.getExtremo2())) {
            sePuedeColocarDerecha = true;
            extremosDerechaUsados.add("extremo1");
        }
        if (fichaSeleccionada.getExtremo2().equals(fichaDerecha.getExtremo2())) {
            sePuedeColocarDerecha = true;
            extremosDerechaUsados.add("extremo2");
        }

        if (sePuedeColocarIzquierda && sePuedeColocarDerecha) {
            //SE PUEDE COLOCAR EN LOS 2 LADOS
        } else if (sePuedeColocarIzquierda) {
            //NOMAS SE PUEDE COLOCAR EN EL LADO IZQUIERDO
        } else if (sePuedeColocarDerecha) {
            //NOMAS SE PUEDE COLOCAR EN EL LADO DERECHO
        } else {
            //NO SE PUEDE COLOCAR, PERO PUES NO DEBERIA DE LLEGAR AQUI SIN YA FUE VALIDADO PREVIAMENTE
        }

    }

}
