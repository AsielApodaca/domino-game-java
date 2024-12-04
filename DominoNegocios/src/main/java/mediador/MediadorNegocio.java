package mediador;

import listeners.IContenedorListener;
import logica.contenedorpantallaslogica.IContenedorPantallasLogica;
import setup.Setup;

/**
 * Clase que actúa como mediador entre las distintas clases de lógica,
 * coordinando la interacción entre la lógica del contenedor de pantallas y y la
 * lógica de las demás pantallas.
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
     * Constructor que inicializa el mediador con la configuración de la
     * aplicación.
     *
     * @param setup instancia de configuración que contiene las dependencias
     * necesarias.
     */
    public MediadorNegocio(Setup setup) {
        this.setup = setup;
        this.contenedorPantallasLogica = setup.getContenedorPantallasLogica();
    }

    @Override
    public void irAMenu() {
        cerrarOperaciones();
        // Inicia la lógica del menu y obtiene la clase control
        // que implementa IContenedorListener
        IContenedorListener pantalla = setup.getMenuDominoLogica().iniciar();
        // Muestra la pantalla sobre el contenedor de pantallas
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }

    /**
     * Cambia la vista actual a la sala de espera. Coordina la lógica del
     * contenedor de pantallas con la lógica de la sala de espera.
     */
    @Override
    public void irASalaEspera() {
        cerrarOperaciones();
        // Inicia la lógica de la sala de espera y obtiene la clase control
        // que implementa IContenedorListener
        IContenedorListener pantalla = setup.getSalaEsperaLogica().iniciar();

        // Muestra la pantalla sobre el contenedor de pantallas
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }

    /**
     * Ejecuta la lógica necesaria para crear una nueva sala de espera.
     */
    @Override
    public void crearSala() {
        // logica de SalaEspera para crear sala
        setup.getSalaEsperaLogica().crearSala();
    }

    /**
     * Ejecuta la lógica necesaria para unirse a una sala de espera existente.
     */
    @Override
    public void unirseSala() {
        // logica de SalaEspera para unirse a sala
        setup.getSalaEsperaLogica().unirseSala();

    }

    /**
     * Cambia la vista actual a la pantalla de la partida de dominó. Coordina la
     * lógica del contenedor de pantallas con la lógica de la partida.
     */
    @Override
    public void irAPartidaDomino() {
        cerrarOperaciones();
        // Inicia la lógica de la partida de dominó y obtiene la clase control
        // que implementa IContenedorListener
        IContenedorListener pantalla = setup.getPartidaDominoLogica().iniciar();

        // Muestra la pantalla sobre el contenedor de pantallas
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }

    /**
     * Cambia la vista actual a la pantalla de crear usuario. Coordina la lógica
     * del contenedor de pantallas con la lógica de crear usuario.
     */
    @Override
    public void irACrearUsuario() {
        cerrarOperaciones();
        // Inicia la logica de crear usuario y obtiene la clase control 
        // que implementa el listener
        IContenedorListener pantalla = setup.getCrearUsuarioLogica().iniciar();
        // Muestra la pantalla sobre el contenedor de pantallas
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }

    @Override
    public void irABuscarSala() {
        cerrarOperaciones();
        IContenedorListener pantalla = setup.getBuscarSalaLogica().iniciar() ;
        contenedorPantallasLogica.abrirPantalla(pantalla);
    }
    
    private void cerrarOperaciones() {
        setup.getCrearUsuarioLogica().cerrar();
        setup.getMenuDominoLogica().cerrar();
        setup.getBuscarSalaLogica().cerrar();
        setup.getSalaEsperaLogica().cerrar();
        setup.getPartidaDominoLogica().cerrar();
    }
}
