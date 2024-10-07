/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import contenedorView.FormContenedorModel;
import dominio.FichaDominoEntity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import contenedorView.EscalaObserver;
import contenedorView.EscalaProveedor;
import dominodto.FichaDominoDTO;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoController implements EscalaObserver{
    
    private TableroDominoModel model ;
    private TableroDominoView view ;
    private EscalaProveedor scaleProvider;
    
    public TableroDominoController(TableroDominoModel model, TableroDominoView view, FormContenedorModel formContenedorModel) {
        this.model = model;
        this.view = view;
        this.scaleProvider = formContenedorModel;
        formContenedorModel.addScaleObserver(this);
        inicializarListener();
    }
    
    private void inicializarListener() {
        view.setSeleccionarFichaListener(new SeleccionarFichaListener());
    }

    @Override
    public void onScaleChanged(float newScale) {
        updateScale(newScale);
    }
    
    private void updateScale(float scale) {
        // Actualizar escala en modelo
        model.setEscala(scale);
        // Por ejemplo:
//        view.setSize((int)(view.getWidth() * scale), (int)(view.getHeight() * scale));
//        view.revalidate();
//        view.repaint();
        System.out.println(scale);
    }
    
    public void cleanup() {
        if (scaleProvider instanceof FormContenedorModel) {
            ((FormContenedorModel) scaleProvider).removeScaleObserver(this);
        }
    }

    class SeleccionarFichaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel fichaDominoPanel = (JPanel) e.getSource();
            int index = view.getFichasDominoUsuario().indexOf(fichaDominoPanel);

            if (index != -1) {
                try {
                    FichaDominoDTO selectedFicha = model.getListaFichasUsuario().get(index);
                    System.out.println("Ficha Seleccionada: [" + selectedFicha.getValorExtremo1()
                            + " | " + selectedFicha.getValorExtremo2() + "]");
                    model.setFichaSeleccionada(selectedFicha);
                    model.validarExtremoCompatible(selectedFicha);

                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Error");
            }
        }
    }
    public void startNewGame() {
        model.repartirFichas();
    }
    
    public void endTurn() {
        
    }
}
