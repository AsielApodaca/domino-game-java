package fachada;

import listeners.IContenedorListener;
import listeners.IPresentacionMenuDominoListener;
import mvc.MenuDominoController;
import mvc.MenuDominoModel;
import mvc.MenuDominoView;
import notificador.PresentacionMenuDominoNotificador;
import notificador.IPresentacionMenuDominoNotificador;

/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FachadaMenuDomino implements IFachadaMenuDomino{

    private MenuDominoController menuDominoController;
    private MenuDominoModel menuDominoModel;
    private MenuDominoView menuDominoView;
    private IPresentacionMenuDominoNotificador presentacionNotificadorManager;

    @Override
    public IContenedorListener iniciarPantalla() {
        this.presentacionNotificadorManager = new PresentacionMenuDominoNotificador();
        this.menuDominoModel = new MenuDominoModel();
        menuDominoModel.setPresentacionNotificadorManager(presentacionNotificadorManager);
        this.menuDominoView = new MenuDominoView(menuDominoModel);
        this.menuDominoController = new MenuDominoController(menuDominoModel, menuDominoView);
        menuDominoView.suscribirListener(menuDominoController);
        menuDominoView.suscribirListener(menuDominoController);
        return menuDominoController;
    }
    
    @Override
    public void suscribirPresentacionListener(IPresentacionMenuDominoListener listener) {
        presentacionNotificadorManager.suscribirPresentacionListener(listener);
    }
    
}
