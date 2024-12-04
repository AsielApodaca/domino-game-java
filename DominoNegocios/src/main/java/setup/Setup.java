package setup;

import dominio.UsuarioEntity;
import domino.fachada.FachadaClienteProxy;
import domino.fachada.IFachadaClienteProxy;
import logica.contenedorpantallaslogica.ContenedorPantallasLogica;
import logica.contenedorpantallaslogica.IContenedorPantallasLogica;
import logica.crearusuariologica.CrearUsuarioLogica;
import logica.crearusuariologica.ICrearUsuarioLogica;
import logica.gestorusuario.GestorUsuario;
import logica.gestorusuario.IGestorUsuario;
import logica.menudominologica.IMenuDominoLogica;
import logica.menudominologica.MenuDominoLogica;
import logica.partidadominologica.IPartidaDominoLogica;
import logica.partidadominologica.PartidaDominoLogica;
import logica.salaesperalogica.ISalaEsperaLogica;
import logica.salaesperalogica.SalaEsperaLogica;
import manejadorrespuestaclienteproxy.GestorRespuestaClienteProxy;
import manejadorrespuestaclienteproxy.ManejadorRespuestaActualizarCantidadFichas;
import manejadorrespuestaclienteproxy.ManejadorRespuestaAgregarFichaJugador;
import manejadorrespuestaclienteproxy.ManejadorRespuestaBloquearPozo;
import manejadorrespuestaclienteproxy.ManejadorRespuestaColocarFichaTablero;
import manejadorrespuestaclienteproxy.ManejadorRespuestaDesbloquearPozo;
import manejadorrespuestaclienteproxy.ManejadorRespuestaMostrarCasillasDisponibles;
import manejadorrespuestaclienteproxy.ManejadorRespuestaMostrarFichasActualizadasDeJugador;
import manejadorrespuestaclienteproxy.ManejadorRespuestaMostrarJugadoresPartida;
import manejadorrespuestaclienteproxy.ManejadorRespuestaMostrarPantallaPartida;
import manejadorrespuestaclienteproxy.ManejadorRespuestaOcultarCasillasDisponibles;
import manejadorrespuestaclienteproxy.ManejadorRespuestaOtorgarTurno;
import manejadorrespuestaclienteproxy.ManejadorRespuestaRemoverJugadorPartida;
import mediador.IMediadorNegocio;
import mediador.MediadorNegocio;

/**
 * Clase de configuración principal del sistema. La clase {@code Setup} se
 * encarga de inicializar todos los componentes necesarios para que el sistema
 * funcione correctamente, incluyendo el usuario local, la lógica de negocio, el
 * proxy cliente y el mediador de negocio.
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class Setup implements ISetup {

    private UsuarioEntity usuarioLocal;
    private IFachadaClienteProxy fachadaClienteProxy;
    private GestorRespuestaClienteProxy gestorRespuestaClienteProxy;
    private IContenedorPantallasLogica contenedorPantallasLogica;
    private IMenuDominoLogica menuDominoLogica;
    private ISalaEsperaLogica salaEsperaLogica;
    private IPartidaDominoLogica partidaDominoLogica;
    private ICrearUsuarioLogica crearUsuarioLogica;
    private IMediadorNegocio mediadorNegocio;
    private IGestorUsuario gestorUsuario;

    /**
     * Constructor de la clase {@code Setup}.
     */
    public Setup() {
    }

    /**
     * Inicia la configuración del sistema en el orden correcto. Este método no
     * debe ser modificado ya que garantiza la inicialización adecuada de cada
     * componente.
     */
    @Override
    public void iniciar() {
        iniciarConexionProxy();
        iniciarLogicaDeNegocio();
        iniciarMediadorDeNegocio();
        iniciarManejadorRespuestasClienteProxy();
        correrJuego();
    }

    /**
     * Configura la lógica de negocio necesaria para manejar las pantallas y la
     * partida de dominó.
     */
    private void iniciarLogicaDeNegocio() {
        this.contenedorPantallasLogica = new ContenedorPantallasLogica();
        this.contenedorPantallasLogica.iniciarContenedorDePantallas();
        this.menuDominoLogica = new MenuDominoLogica(this);
        this.salaEsperaLogica = new SalaEsperaLogica(this);
        this.partidaDominoLogica = new PartidaDominoLogica(this);
        this.crearUsuarioLogica = new CrearUsuarioLogica(this);
    }

    /**
     * Configura los manejadores de respuesta del proxy cliente, implementando
     * la cadena de responsabilidad.
     *
     * Este método instancia una serie de manejadores de respuestas específicas
     * para el proxy cliente, creando una cadena de responsabilidad en la que
     * cada manejador tiene la capacidad de procesar un tipo de respuesta
     * específico o pasar la respuesta al siguiente manejador si no es capaz de
     * manejarla.
     *
     * Finalmente, se crea y configura un gestor de respuestas, el cual escucha
     * las respuestas del proxy cliente y las delega a la cabeza de la cadena de
     * manejadores.
     */
    private void iniciarManejadorRespuestasClienteProxy() {
        // Instancia los manejadores de respuestas, configurando la cadena de responsabilidad
        ManejadorRespuestaMostrarPantallaPartida manejadorRespuestaMostrarPantallaPartida
                = new ManejadorRespuestaMostrarPantallaPartida(mediadorNegocio);

        ManejadorRespuestaRemoverJugadorPartida manejadorRespuestaRemoverJugadorPartida
                = new ManejadorRespuestaRemoverJugadorPartida(partidaDominoLogica, manejadorRespuestaMostrarPantallaPartida);

        ManejadorRespuestaMostrarJugadoresPartida manejadorRespuestaMostrarJugadoresPartida
                = new ManejadorRespuestaMostrarJugadoresPartida(partidaDominoLogica, manejadorRespuestaRemoverJugadorPartida);

        ManejadorRespuestaBloquearPozo manejadorRespuestaBloquearPozo
                = new ManejadorRespuestaBloquearPozo(partidaDominoLogica, manejadorRespuestaMostrarJugadoresPartida);

        ManejadorRespuestaDesbloquearPozo manejadorRespuestaDesbloquearPozo
                = new ManejadorRespuestaDesbloquearPozo(partidaDominoLogica, manejadorRespuestaBloquearPozo);

        ManejadorRespuestaAgregarFichaJugador manejadorRespuestaAgregarFichaJugador
                = new ManejadorRespuestaAgregarFichaJugador(partidaDominoLogica, manejadorRespuestaDesbloquearPozo);

        ManejadorRespuestaOtorgarTurno manejadorRespuestaCambiarTurno
                = new ManejadorRespuestaOtorgarTurno(partidaDominoLogica, manejadorRespuestaAgregarFichaJugador);

        ManejadorRespuestaColocarFichaTablero manejadorRespuestaAgregarFichaTablero
                = new ManejadorRespuestaColocarFichaTablero(partidaDominoLogica, manejadorRespuestaCambiarTurno);

        ManejadorRespuestaOcultarCasillasDisponibles manejadorRespuestaOcultarCasillasDisponibles
                = new ManejadorRespuestaOcultarCasillasDisponibles(partidaDominoLogica, manejadorRespuestaAgregarFichaTablero);

        ManejadorRespuestaMostrarCasillasDisponibles manejadorRespuestaMostrarCasillasDisponibles
                = new ManejadorRespuestaMostrarCasillasDisponibles(partidaDominoLogica, manejadorRespuestaOcultarCasillasDisponibles);

        ManejadorRespuestaActualizarCantidadFichas manejadorRespuestaActualizarCantidadFichas
                = new ManejadorRespuestaActualizarCantidadFichas(partidaDominoLogica, manejadorRespuestaMostrarCasillasDisponibles);

        ManejadorRespuestaMostrarFichasActualizadasDeJugador manejadorRespuestaMostrarFichasActualizadasDeJugador
                = new ManejadorRespuestaMostrarFichasActualizadasDeJugador(partidaDominoLogica, manejadorRespuestaActualizarCantidadFichas);

        // Instancia el gestor de respuestas, quien escucha las respuestas del ClientProxy
        // y las pasa al primer manejador en la cadena.
        gestorRespuestaClienteProxy = new GestorRespuestaClienteProxy(manejadorRespuestaMostrarFichasActualizadasDeJugador);
        // Suscribe el gestor de respuestas del proxy como oyente del ClientProxy
        fachadaClienteProxy.suscribirClientProxyListener(gestorRespuestaClienteProxy);
    }

    /**
     * Establece la conexión con el proxy cliente y suscribe los manejadores
     * necesarios.
     *
     * Este método crea una instancia de {@link FachadaClienteProxy} y configura
     * el gestor de respuestas como oyente de las respuestas del proxy cliente,
     * asegurando que las respuestas sean procesadas por la cadena de
     * manejadores previamente configurada.
     */
    private void iniciarConexionProxy() {
        // Inicializa la fachada del cliente proxy
        String host = "localhost";
        int puerto = 3000;
        this.fachadaClienteProxy = new FachadaClienteProxy(host, puerto);
    }

    /**
     * Inicializa el mediador de negocio, encargado de coordinar las acciones
     * entre componentes.
     */
    private void iniciarMediadorDeNegocio() {
        this.mediadorNegocio = new MediadorNegocio(this);
    }

    /**
     * Ejecuta el flujo principal del juego, llevando al usuario a la interfaz
     * de la partida.
     */
    private void correrJuego() {
        this.mediadorNegocio.irACrearUsuario();
    }

    public void iniciarGestoresConUsuario(String nombre, String rutaIcono) {
        this.gestorUsuario = new GestorUsuario();
        this.usuarioLocal = this.gestorUsuario.crearUsuario(nombre, rutaIcono);
    }

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

    public IMenuDominoLogica getMenuDominoLogica() {
        return menuDominoLogica;
    }

    /**
     * Obtiene la lógica de la sala de espera.
     *
     * @return la lógica de la sala de espera como {@link ISalaEsperaLogica}.
     */
    public ISalaEsperaLogica getSalaEsperaLogica() {
        return salaEsperaLogica;
    }

    /**
     * Obtiene la lógica de la partida de dominó.
     *
     * @return la lógica de la partida como {@link IPartidaDominoLogica}.
     */
    public IPartidaDominoLogica getPartidaDominoLogica() {
        return this.partidaDominoLogica;
    }

    public IMediadorNegocio getMediadorNegocio() {
        return mediadorNegocio;
    }

    public ICrearUsuarioLogica getCrearUsuarioLogica() {
        return crearUsuarioLogica;
    }

    /**
     * Obtiene el gestor de usuarios del sistema.
     *
     * @return el gestor de usuarios como IGestorUsuario
     */
    public IGestorUsuario getGestorUsuario() {
        return gestorUsuario;
    }

}
