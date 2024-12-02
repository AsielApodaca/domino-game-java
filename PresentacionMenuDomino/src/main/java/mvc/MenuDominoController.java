/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import listeners.IContenedorListener;
import listeners.IViewListener;
import notificador.IPresentacionNotificadorManager;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MenuDominoController implements IViewListener, IContenedorListener{

    private final MenuDominoModel menuDominoModel;
    private final MenuDominoView menuDominoView;
    private final IPresentacionNotificadorManager presentacionNotificadorManager;

    public MenuDominoController(MenuDominoModel menuDominoModel, MenuDominoView menuDominoView) {
        this.menuDominoModel = menuDominoModel;
        this.menuDominoView = menuDominoView;
        this.presentacionNotificadorManager = menuDominoModel.getPresentacionNotificadorManager();
    }

    public void addMouseListenerToCrearSala() {
        this.menuDominoView.getPanelBtnCrearSala().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBtnCrearSala();
            }
        });
    }

    public void addMouseListenerToUnirseSala() {
        this.menuDominoView.getPanelBtnUnirseSala().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBtnUnirseSala();
            }
        });
    }

    @Override
    public void onEscalaChange(float escala) {
    }

    @Override
    public JPanel obtenerView() {
        return menuDominoView;
    }

    @Override
    public void onBtnCrearSala() {
        presentacionNotificadorManager.notificarCrearSala();
    }

    @Override
    public void onBtnUnirseSala() {
        presentacionNotificadorManager.notificarUnirseSala();
    }
}
