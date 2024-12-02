/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc;

import javax.swing.JPanel;
import listeners.IContenedorListener;
import notificador.IPresentacionMenuDominoNotificador;
import listeners.IMenuDominoViewListener;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MenuDominoController implements IMenuDominoViewListener, IContenedorListener{

    private final MenuDominoModel menuDominoModel;
    private final MenuDominoView menuDominoView;
    private final IPresentacionMenuDominoNotificador presentacionNotificadorManager;

    public MenuDominoController(MenuDominoModel menuDominoModel, MenuDominoView menuDominoView) {
        this.menuDominoModel = menuDominoModel;
        this.menuDominoView = menuDominoView;
        this.presentacionNotificadorManager = menuDominoModel.getPresentacionNotificadorManager();
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
