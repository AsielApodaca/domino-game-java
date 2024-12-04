/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcbuscarsalas;

import dominodto.SalaDTO;
import elementosview.SalaPanel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author olive
 */
public class BuscarSalaModel {
    
    private Map<String, SalaPanel> mapaSalasPaneles ;
    
    public BuscarSalaModel() {
        this.mapaSalasPaneles = new HashMap() ;
    }
    
    public void agregarPanel(SalaDTO sala) {
        this.mapaSalasPaneles.put(sala.getIdSala(), new SalaPanel(sala)) ;
    }
    
    public void removerPanel(SalaDTO sala) {
        this.mapaSalasPaneles.remove(sala.getIdSala()) ;
    }
    
    public SalaPanel obtenerPanel(SalaDTO sala) {
        return this.mapaSalasPaneles.get(sala.getIdSala()) ;
    }
    
    public Map<String, SalaPanel> obtenerMapaSalaPaneles() {
        return this.mapaSalasPaneles ;
    }
}
