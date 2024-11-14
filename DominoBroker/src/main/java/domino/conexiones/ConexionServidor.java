/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.conexiones;

import com.google.gson.JsonObject;
import domino.enums.Status;
import java.net.Socket;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ConexionServidor {
    
    private Socket socket ;
    private String id ;
    private Status status;
    
    public ConexionServidor(String id, Socket socket) {
        this.id = id ;
        this.socket = socket ;
        this.status = Status.LIBRE ;
    }
    
    public void mandarSolicitudServidor(JsonObject solicitud) {
        
    }
    
    public Status getStatus() {
        return this.status ;
    }
    
    public String getId() {
        return this.id ;
    }
}