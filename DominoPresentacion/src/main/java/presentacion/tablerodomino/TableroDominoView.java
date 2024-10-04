/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tablerodomino;

import dominio.FichaDomino;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class TableroDominoView extends JPanel implements ITableroDominoModeloListener {

    private TableroDominoModel tableroDominoModel ;
    private List<JButton> botonesFichasDominoUsuario;
    private List<JButton> fichasComparativa ;
    private List<FichaDomino> fichasDominoUsuario ;

    public TableroDominoView(TableroDominoModel tableroDominoModel) {
        this.tableroDominoModel = tableroDominoModel;
        this.botonesFichasDominoUsuario = new ArrayList<>(); 
        this.fichasComparativa = new ArrayList<>();
        this.fichasDominoUsuario = new ArrayList<>();

        setLayout(null);
        setPreferredSize(new Dimension(1200, 800));

        tableroDominoModel.addListener(this);
        tableroDominoModel.repartirFichas();
    }

    private void crearFichasVisuales() {
        removeAll(); 
        
        for (int i = 0; i < tableroDominoModel.getListaFichasUsuario().size(); i++) {
            FichaDomino ficha = tableroDominoModel.getListaFichasUsuario().get(i);
            JButton btn = createDominoButton(ficha, i);
            botonesFichasDominoUsuario.add(btn);
            add(btn);
        }
        
        revalidate();
        repaint();
    }
    
    private JButton createDominoButton(FichaDomino ficha, int index) {
        JButton btn = new JButton("[" + ficha.getExtremo1() + " | " + ficha.getExtremo2() + "]");
        btn.setPreferredSize(new Dimension(110, 70));
        btn.setFocusable(false);
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        
        int xPosition = 377 + (index * 75);
        btn.setBounds(xPosition, 678, 70, 110);
        
        return btn;
    }

    public void setSeleccionarFichaListener(ActionListener listener) {
        for (JButton ficha : botonesFichasDominoUsuario) {
            ficha.addActionListener(listener);
        }
    }

    @Override
    public void onChangeListaFichasUsuario(List<FichaDomino> listaFichasUsuario) {
        SwingUtilities.invokeLater(() -> {
            for (JButton ficha : botonesFichasDominoUsuario) {
                remove(ficha);
            }
            botonesFichasDominoUsuario.clear();
            
            for (int i = 0; i < listaFichasUsuario.size(); i++) {
                FichaDomino fichaDomino = listaFichasUsuario.get(i);
                JButton fichaButton = new JButton("[" + fichaDomino.getExtremo1() + 
                                                 " | " + fichaDomino.getExtremo2() + "]");
                fichaButton.setPreferredSize(new Dimension(110, 70));
                fichaButton.setFocusable(false);
                fichaButton.setBackground(Color.LIGHT_GRAY);
                fichaButton.setFont(new Font("Arial", Font.BOLD, 16));
                
                int xPosition = 377 + (i * 75);
                fichaButton.setBounds(xPosition, 678, 70, 110);
                
                botonesFichasDominoUsuario.add(fichaButton);
                add(fichaButton);
            }
            
            revalidate();
            repaint();
        });
    }

    @Override
    public void onChangeListaBotonesFichasUsuario(List<JButton> listaBotonesFichasUsuario) {
        SwingUtilities.invokeLater(() -> {
            this.botonesFichasDominoUsuario = listaBotonesFichasUsuario ;
            
            crearFichasVisuales() ;
        });
    }

    @Override
    public void onChangeFichaComparativa(JButton fichaComparativa) {
        SwingUtilities.invokeLater(() -> {
            for (JButton ficha : fichasComparativa) {
                remove(ficha);
            }
            fichasComparativa.clear();
            
            fichaComparativa.setBounds(550, 350, 70, 110);
            fichasComparativa.add(fichaComparativa);
            add(fichaComparativa);
            
            revalidate();
            repaint();
        });
    }

    public TableroDominoModel getTableroDominoModel() {
        return tableroDominoModel;
    }

    public void setTableroDominoModel(TableroDominoModel tableroDominoModel) {
        this.tableroDominoModel = tableroDominoModel;
    }

    public List<JButton> getBotonesFichasDominoUsuario() {
        return botonesFichasDominoUsuario;
    }

    public void setBotonesFichasDominoUsuario(List<JButton> botonesFichasDominoUsuario) {
        this.botonesFichasDominoUsuario = botonesFichasDominoUsuario;
    }

    public List<JButton> getFichasComparativa() {
        return fichasComparativa;
    }

    public void setFichasComparativa(List<JButton> fichasComparativa) {
        this.fichasComparativa = fichasComparativa;
    }

    public List<FichaDomino> getFichasDominoUsuario() {
        return fichasDominoUsuario;
    }

    public void setFichasDominoUsuario(List<FichaDomino> fichasDominoUsuario) {
        this.fichasDominoUsuario = fichasDominoUsuario;
    }
    
    
    
}
