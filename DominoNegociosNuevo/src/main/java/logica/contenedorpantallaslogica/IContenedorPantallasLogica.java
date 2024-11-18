package logica.contenedorpantallaslogica;

import listeners.IContenedorListener;

/**
 * Interfaz que define las operaciones lógicas para manejar un contenedor de pantallas.
 * Permite inicializar el contenedor y abrir pantallas específicas.
 * 
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public interface IContenedorPantallasLogica {

    /**
     * Inicializa el contenedor de pantallas, configurando los recursos necesarios
     * para su funcionamiento.
     */
    public void iniciarContenedorDePantallas();

    /**
     * Abre una pantalla específica dentro del contenedor.
     * 
     * @param pantalla una instancia que implementa {@link IContenedorListener}, 
     *                 representando la pantalla a abrir.
     */
    public void abrirPantalla(IContenedorListener pantalla);
}
