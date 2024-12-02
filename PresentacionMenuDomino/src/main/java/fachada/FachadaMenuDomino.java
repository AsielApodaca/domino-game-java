package fachada;

import listeners.IContenedorListener;
import listeners.IPresentacionMenuDominoListener;
import mvc.MenuDominoController;
import mvc.MenuDominoModel;
import mvc.MenuDominoView;
import notificador.IPresentacionNotificadorManager;

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
    private IPresentacionNotificadorManager presentacionNotificadorManager;

    @Override
    public IContenedorListener iniciarPantalla() {
        this.menuDominoModel = new MenuDominoModel();
        this.menuDominoView = new MenuDominoView(menuDominoModel);
        menuDominoView.suscribirListener(menuDominoController);
        this.menuDominoController = new MenuDominoController(menuDominoModel, menuDominoView);
        menuDominoView.suscribirListener(menuDominoController);
        
        return menuDominoController;
    }
    
    @Override
    public void suscribirPresentacionListener(IPresentacionMenuDominoListener listener) {
        presentacionNotificadorManager.suscribirPresentacionListener(listener);
    }
    
}
