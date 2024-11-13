/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domino.manejadores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author olive
 */
public class ManejadorCliente {
    
    private Socket socket ;
    private BufferedReader reader;
    private PrintWriter writer;
    private String id ;
    
    public ManejadorCliente(String id, Socket socket) {
        this.id = id ;
        this.socket = socket ;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
        }
    }
    
    public void mandarRespuestaCliente(String respuesta) {
        
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
