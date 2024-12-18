/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.conexiones;

import com.google.gson.JsonObject;
import domino.enums.Status;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class ConexionServidor {
    
    private Socket socket ;
    private BufferedReader reader;
    private PrintWriter writer;
    private String id ;
    private Status status;
    
    public ConexionServidor(String id, Socket socket) {
        this.id = id ;
        this.socket = socket ;
        this.status = Status.LIBRE ;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
        }
    }
    
    public void mandarSolicitudServidor(JsonObject solicitud) {
        writer.println(solicitud.toString());
    }
    
    public Status getStatus() {
        return this.status ;
    }
    
    public String getId() {
        return this.id ;
    }
    
    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }
    
    public void setStatus(Status status) {
        this.status = status ;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    
}