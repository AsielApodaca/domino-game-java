package logica.contenedorpantallaslogica;

import fachada.FachadaContenedorPantallas;
import fachada.IFachadaContenedorPantallas;
import listeners.IContenedorListener;

/**
 * Implementación de la lógica para manejar un contenedor de pantallas.
 * Se encarga de interactuar con la fachada para inicializar y abrir pantallas.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ContenedorPantallasLogica implements IContenedorPantallasLogica {
    
    /**
     * Instancia de la fachada que gestiona el contenedor de pantallas.
     */
    private IFachadaContenedorPantallas fachadaContenedorPantallas;

    /**
     * Constructor que inicializa la fachada del contenedor de pantallas.
     */
    public ContenedorPantallasLogica() {
        this.fachadaContenedorPantallas = new FachadaContenedorPantallas();
    }
    
    /**
     * Inicializa el contenedor de pantallas mediante la fachada.
     */
    @Override
    public void iniciarContenedorDePantallas() {
        fachadaContenedorPantallas.iniciarContenedorDePantallas();
    }

    /**
     * Abre una pantalla específica en el contenedor utilizando la fachada.
     * 
     * @param pantalla una instancia que implementa {@link IContenedorListener},
     *                 representando la pantalla a establecer.
     */
    @Override
    public void abrirPantalla(IContenedorListener pantalla) {
        fachadaContenedorPantallas.establecerPantalla(pantalla);
    }
}
