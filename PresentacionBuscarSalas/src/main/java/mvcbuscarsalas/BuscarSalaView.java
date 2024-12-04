/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcbuscarsalas;

import dominodto.SalaDTO;
import dominodto.UsuarioDTO;
import elementosview.SalaPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import listeners.IBuscarSalaViewListener;

/**
 *
 * @author olive
 */
public class BuscarSalaView extends JPanel {
    
    private BuscarSalaModel model ;
    private IBuscarSalaViewListener viewListener ;
    private List<SalaPanel> salasPaneles ;
    
    public BuscarSalaView(BuscarSalaModel model) {
        this.model = model ;
        this.salasPaneles = new ArrayList() ;
    }

    public void setViewListener(IBuscarSalaViewListener viewListener) {
        this.viewListener = viewListener;
    }
    
    private void notificarBtnUnirseSala(SalaDTO sala) {
        this.viewListener.onBtnUnirseSalaPresionado(sala);
    }
    
    private void notificarBtnSalir() {
        this.viewListener.onBtnSalirPresionado();
    }
    
    public void repintarPaneles() {
        this.salasPaneles.forEach(sala -> {
            remove(sala);
        });

        Collection<SalaPanel> paneles = model.obtenerMapaSalaPaneles().values();
        this.salasPaneles = new ArrayList(paneles);

        salasPaneles.forEach(sala -> {
            sala.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    notificarBtnUnirseSala(sala.getSala()) ;
                }
            });
            add(sala);
        });
    }
    
}
