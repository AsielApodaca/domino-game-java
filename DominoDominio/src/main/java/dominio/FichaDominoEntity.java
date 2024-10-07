/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;


/**
 *
 * @author Hisamy Cinco Cota
 * @author Gael Rafael Castro Molina
 * @author Oliver Inzunza Valle
 * @author Asiel Apodaca Monge
 */
public class FichaDominoEntity {

    private Integer extremo1;
    private Integer extremo2;

    public FichaDominoEntity(Integer extremo1, Integer extremo2) {
        this.extremo1 = extremo1;
        this.extremo2 = extremo2;
    }

    public Integer getExtremo1() {
        return extremo1;
    }

    public void setExtremo1(Integer extremo1) {
        this.extremo1 = extremo1;
    }

    public Integer getExtremo2() {
        return extremo2;
    }

    public void setExtremo2(Integer extremo2) {
        this.extremo2 = extremo2;
    }

}
