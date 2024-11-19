package setup;

import dominio.UsuarioEntity;
import domino.fachada.FachadaClienteProxy;
import domino.fachada.IFachadaClienteProxy;
import logica.contenedorpantallaslogica.ContenedorPantallasLogica;
import logica.contenedorpantallaslogica.IContenedorPantallasLogica;
import logica.partidadominologica.IPartidaDominoLogica;
import logica.partidadominologica.PartidaDominoLogica;
import mediador.IMediadorNegocio;
import mediador.MediadorNegocio;

/**
 * Clase de configuración principal del sistema.
 * La clase {@code Setup} se encarga de inicializar todos los componentes necesarios
 * para que el sistema funcione correctamente, incluyendo el usuario local,
 * la lógica de negocio, el proxy cliente y el mediador de negocio.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Setup implements ISetup {

    private UsuarioEntity usuarioLocal;
    private IFachadaClienteProxy fachadaClienteProxy;
    private IContenedorPantallasLogica contenedorPantallasLogica;
    private IPartidaDominoLogica partidaDominoLogica;
    private IMediadorNegocio mediadorNegocio;

    /**
     * Constructor de la clase {@code Setup}.
     */
    public Setup() {
    }

    /**
     * Inicia la configuración del sistema en el orden correcto.
     * Este método no debe ser modificado ya que garantiza la inicialización
     * adecuada de cada componente.
     */
    @Override
    public void iniciar() {
        iniciarUsuario();
        iniciarConexionProxy();
        iniciarLogicaDeNegocio();
        iniciarManejadorRespuestasClienteProxy();
        iniciarMediadorDeNegocio();
        correrJuego();
    }

    /**
     * Inicializa el usuario local que interactuará con el sistema.
     */
    private void iniciarUsuario() {
        String nombre = "Chapo Guzman";
        this.usuarioLocal = new UsuarioEntity(nombre);
    }

    /**
     * Configura la lógica de negocio necesaria para manejar las pantallas y la partida de dominó.
     */
    private void iniciarLogicaDeNegocio() {
        this.contenedorPantallasLogica = new ContenedorPantallasLogica();
        this.partidaDominoLogica = new PartidaDominoLogica(this);
    }

    /**
     * Configura los manejadores de respuesta del proxy cliente, implementando
     * la cadena de responsabilidad.
     */
    private void iniciarManejadorRespuestasClienteProxy() {
        // Instanciar y configurar la cadena de responsabilidad
    }

    /**
     * Establece la conexión con el proxy cliente y suscribe los manejadores necesarios.
     */
    private void iniciarConexionProxy() {
        this.fachadaClienteProxy = new FachadaClienteProxy();
        // Aqui hay que agregar un método a fachadaClienteProxy para susbribir
        // el ManejadorRespuestaClienteProxy como IClienteProxyListener
    }

    /**
     * Inicializa el mediador de negocio, encargado de coordinar las acciones entre componentes.
     */
    private void iniciarMediadorDeNegocio() {
        this.mediadorNegocio = new MediadorNegocio(this);
    }

    /**
     * Ejecuta el flujo principal del juego, llevando al usuario a la interfaz de la partida.
     */
    private void correrJuego() {
        this.mediadorNegocio.irAPartidaDomino();
    }

    // Getters

    /**
     * Obtiene la instancia del usuario local.
     * 
     * @return el usuario local como {@link UsuarioEntity}.
     */
    public UsuarioEntity getUsuarioLocal() {
        return this.usuarioLocal;
    }

    /**
     * Obtiene la fachada del proxy cliente.
     * 
     * @return la fachada cliente como {@link IFachadaClienteProxy}.
     */
    public IFachadaClienteProxy getFachadaClienteProxy() {
        return this.fachadaClienteProxy;
    }

    /**
     * Obtiene la lógica del contenedor de pantallas.
     * 
     * @return la lógica del contenedor como {@link IContenedorPantallasLogica}.
     */
    public IContenedorPantallasLogica getContenedorPantallasLogica() {
        return this.contenedorPantallasLogica;
    }

    /**
     * Obtiene la lógica de la partida de dominó.
     * 
     * @return la lógica de la partida como {@link IPartidaDominoLogica}.
     */
    public IPartidaDominoLogica getPartidaDominoLogica() {
        return this.partidaDominoLogica;
    }
}
