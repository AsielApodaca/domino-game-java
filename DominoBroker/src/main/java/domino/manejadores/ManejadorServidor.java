/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import com.google.gson.JsonObject;
import domino.enums.Status;
import java.net.Socket;

/**
 *
 * @author olive
 */
public class ManejadorServidor {
    
    private Socket socket ;
    private String id ;
    private Status status;
    
    public ManejadorServidor(String id, Socket socket) {
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