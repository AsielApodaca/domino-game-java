/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import domino.conexiones.ConexionServidor;
import domino.enums.Status;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ManejadorServidores {

    private final Map<String, ConexionServidor> servidores;
    private Long contadorIdServers ;

    public ManejadorServidores() {
        this.servidores = new HashMap();
        this.contadorIdServers = 0L ;
    }

    public Map<String, ConexionServidor> getServidores() {
        return this.servidores;
    }
    
    public ConexionServidor agregarServidor(Socket socket) {
        contadorIdServers += 1L ;
        String id = "SER" + contadorIdServers ;
        ConexionServidor servidor = new ConexionServidor(id, socket) ;
        this.servidores.put(id, servidor) ;
        return servidor ;
    }
    
    public ConexionServidor buscarServidorLibre() {
        ConexionServidor servidorLibre = servidores.values().stream()
                .filter(servidor -> servidor.getStatus() == Status.LIBRE)
                .findFirst()
                .orElse(null);
        
        if(servidorLibre != null) {
            return servidorLibre ;
        } else {
            return null ;
        }
    }
    
}
