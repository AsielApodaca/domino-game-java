
package builders;

import dominodto.CasillaDTO;
import partidadomino.elementostablero.CasillaPanel;

/**
 *
 * @author asielapodaca
 */
public class CasillaBuilder {
    private CasillaDTO casillaDTO;
    private CasillaPanel casillaPanel;

    public CasillaPanel construirCasilla(CasillaDTO casillaDTO) {
        this.casillaPanel = new CasillaPanel();
        this.casillaDTO = casillaDTO;
        
        iniciarCasillaParaTablero();
        
        return casillaPanel;
    }

    private void iniciarCasillaParaTablero() {
        asignarOrientacion();
        asignarLocacion();
        asignarExtremoDeTablero();
        casillaPanel.cargarFondo();
        casillaPanel.setCasillaDTO(casillaDTO);
    }
    
    private void asignarOrientacion() {
        int rotacion = casillaDTO.getRotacion();
        if(rotacion == 0 || rotacion == 180) {
            casillaPanel.setEsHorizontal(true);
        } else {
            casillaPanel.setEsHorizontal(false);
        }
    }
    
    private void asignarLocacion() {
        int locacionX = casillaDTO.getLocacionX();
        int locacionY = casillaDTO.getLocacionY();
        casillaPanel.setLocacionX(locacionX);
        casillaPanel.setLocacionY(locacionY);
    }
    
    private void asignarExtremoDeTablero() {
        casillaPanel.setExtremo(casillaDTO.getExtremo());
    }
}
