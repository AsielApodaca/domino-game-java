package mediador;

import listeners.IContenedorListener;
import logica.contenedorpantallaslogica.IContenedorPantallasLogica;
import setup.Setup;

/**
 * Clase que actúa como mediador entre las distintas clases de lógica,
 * coordinando la interacción entre la lógica del contenedor de pantallas y 
 * y la lógica de las demás pantallas.
 * 
 * Este mediador se utiliza para navegar de una pantalla a otra.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class MediadorNegocio implements IMediadorNegocio {

    private Setup setup;
    private IContenedorPantallasLogica contenedorPantallasLogica;

    /**
     * Constructor que inicializa el mediador con la configuración de la aplicación.
     * 
     * @param setup instancia de configuración que contiene las dependencias necesarias.
     */
    public MediadorNegocio(Setup setup) {
        this.setup = setup;
        this.contenedorPantallasLogica = setup.getContenedorPantallasLogica();
    }
    
    @Override
    public void irASalaEspera() {
        
        // Inicia la lógica de la sala de espera y obtiene la clase control
        // que implementa IContenedorListener
        IContenedorListener pantalla = setup.getSalaEsperaLogica().iniciar();
        
        // Muestra la pantalla sobre el contenedor de pantallas
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }
    /**
     * Cambia la vista actual a la pantalla de la partida de dominó.
     * Coordina la lógica del contenedor de pantallas con la lógica de la partida.
     */
    @Override
    public void irAPartidaDomino() {
        
        // Inicia la lógica de la partida de dominó y obtiene la clase control
        // que implementa IContenedorListener
        IContenedorListener pantalla = setup.getPartidaDominoLogica().iniciar();
        
        // Muestra la pantalla sobre el contenedor de pantallas
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }
}
