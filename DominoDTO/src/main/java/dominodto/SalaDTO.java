/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominodto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author olive
 */
public class SalaDTO {
    
    private String idSala ;
    private int size ;
    private List<UsuarioDTO> usuarios ;
    private UsuarioDTO anfitrion ;

    public SalaDTO(String idSala, int size, List<UsuarioDTO> usuarios) {
        this.idSala = idSala;
        this.size = size;
        this.usuarios = usuarios;
    }
    public SalaDTO(String idSala, int size) {
        this.idSala = idSala;
        this.size = size;
        this.usuarios = new ArrayList() ;
    }

    public SalaDTO() {
        
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioDTO getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(UsuarioDTO anfitrion) {
        this.anfitrion = anfitrion;
    }
    
    
}
