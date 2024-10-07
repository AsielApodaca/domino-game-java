/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.tableroDominoLogica;

import contenedorView.FormContenedorModel;
import dominio.FichaDominoEntity;
import dominodto.FichaDominoDTO;
import java.util.ArrayList;
import java.util.List;
import mediadorNavegacion.MediadorNavegacionPantallas;
import partidadomino.PartidaDominoController;
import partidadomino.PartidaDominoModel;
import partidadomino.PartidaDominoView;
import presentacion.tablerodomino.*;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoLogica implements ITableroDominoLogica, ITableroDominoModeloListener {

//    TableroDominoModel model;
//    TableroDominoView view;
//    TableroDominoController controller;
    private PartidaDominoModel model;
    private PartidaDominoView view;
    private PartidaDominoController controller;
    
    FormContenedorModel proveedorDeEscala;

    public TableroDominoLogica(FormContenedorModel proveedorDeEscala) {
        this.proveedorDeEscala = proveedorDeEscala;
    }

    
    
    @Override
    public void crearYMostrarPantalla() {
        this.model = new PartidaDominoModel();
        this.model.setEscala(proveedorDeEscala.getScale());
        simularListaFichasDTO(); // Asigna lista simulada de fichas de domino a modelo
        this.view = new PartidaDominoView(model);
        this.controller = new PartidaDominoController(model, view, proveedorDeEscala);
        //this.model.addListener(this);
        MediadorNavegacionPantallas.getInstance().navegarA(view);
    }
    
    public void simularListaFichasDTO() { // temporal
        List<FichaDominoDTO> listaFichas = new ArrayList<>();
        
        listaFichas.add(new FichaDominoDTO(6, 6));
        listaFichas.add(new FichaDominoDTO(1, 6));
        listaFichas.add(new FichaDominoDTO(1, 4));
        listaFichas.add(new FichaDominoDTO(4, 4));
        listaFichas.add(new FichaDominoDTO(4, 5));
        listaFichas.add(new FichaDominoDTO(3, 4));
        listaFichas.add(new FichaDominoDTO(3, 6));
        
        model.setListaFichasJugadorLocal(listaFichas);
        
    }

    @Override
    public void onChangeListaFichasUsuario(List<FichaDominoDTO> listaFichasUsuario) {
        return;
    }

    @Override
    public void onChangeFichaComparativa(FichaDominoDTO fichaComparativa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onChangeFichasComparativas(List<FichaDominoDTO> listaFichasComparativas) {
//        model.getListaFichasValidas().clear();
//        FichaDominoDTO fichaIzquierda = model.getFichasComparativas().getFirst();
//        FichaDominoDTO fichaDerecha = model.getFichasComparativas().getLast();
//        model.getListaFichasUsuario().forEach(ficha -> {
//            boolean coincideConIzquierda = ficha.getExtremo1().equals(fichaIzquierda.getExtremo1())
//                    || ficha.getExtremo2().equals(fichaIzquierda.getExtremo1());
//
//            boolean coincideConDerecha = ficha.getExtremo1().equals(fichaDerecha.getExtremo2())
//                    || ficha.getExtremo2().equals(fichaDerecha.getExtremo2());
//
//            if (coincideConIzquierda || coincideConDerecha) {
//                model.getListaFichasValidas().add(ficha);
//            }
//            
//            System.out.println(model.getListaFichasValidas().toString());
//        });
    }

    @Override
    public void onChangeFichaSeleccionada(FichaDominoDTO fichaSeleccionada) {
//        FichaDominoDTO fichaIzquierda = model.getFichasComparativas().getFirst();
//        FichaDominoDTO fichaDerecha = model.getFichasComparativas().getLast();
//        
//        boolean sePuedeColocarIzquierda = false;
//        boolean sePuedeColocarDerecha = false;
//
//        List<String> extremosIzquierdaUsados = new ArrayList<>();
//        List<String> extremosDerechaUsados = new ArrayList<>();
//
//        if (fichaSeleccionada.getExtremo1().equals(fichaIzquierda.getExtremo1())) {
//            sePuedeColocarIzquierda = true;
//            extremosIzquierdaUsados.add("extremo1");
//        }
//        if (fichaSeleccionada.getExtremo2().equals(fichaIzquierda.getExtremo1())) {
//            sePuedeColocarIzquierda = true;
//            extremosIzquierdaUsados.add("extremo2");
//        }
//
//        if (fichaSeleccionada.getExtremo1().equals(fichaDerecha.getExtremo2())) {
//            sePuedeColocarDerecha = true;
//            extremosDerechaUsados.add("extremo1");
//        }
//        if (fichaSeleccionada.getExtremo2().equals(fichaDerecha.getExtremo2())) {
//            sePuedeColocarDerecha = true;
//            extremosDerechaUsados.add("extremo2");
//        }
//
//        if (sePuedeColocarIzquierda && sePuedeColocarDerecha) {
//            //SE PUEDE COLOCAR EN LOS 2 LADOS
//        } else if (sePuedeColocarIzquierda) {
//            //NOMAS SE PUEDE COLOCAR EN EL LADO IZQUIERDO
//        } else if (sePuedeColocarDerecha) {
//            //NOMAS SE PUEDE COLOCAR EN EL LADO DERECHO
//        } else {
//            //NO SE PUEDE COLOCAR, PERO PUES NO DEBERIA DE LLEGAR AQUI SIN YA FUE VALIDADO PREVIAMENTE
//        }

    }

}
