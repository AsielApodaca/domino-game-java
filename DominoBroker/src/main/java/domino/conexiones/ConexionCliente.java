/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.conexiones;

import com.google.gson.JsonObject;
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
public class ConexionCliente {
    
    private Socket socket ;
    private BufferedReader reader;
    private PrintWriter writer;
    private String id ;
    
    public ConexionCliente(String id, Socket socket) {
        this.id = id ;
        this.socket = socket ;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
        }
    }
    
    public void mandarRespuestaCliente(JsonObject respuestaJSON) {
        writer.println(respuestaJSON.getAsString());
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public String getId() {
        return id;
    }
    
    
}
