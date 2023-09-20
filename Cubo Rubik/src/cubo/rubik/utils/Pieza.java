/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cubo.rubik.utils;

/**
 *
 * @author Ashley Mendoza V
 */
public class Pieza {
    
    public static enum TipoPieza {CENTRO, ESQUINA, ARISTA};
    
    private Integer id;
    private final String[] subCaras = new String[6];
    private TipoPieza tipoPieza;
    
    public Pieza(Integer id, TipoPieza tp){
        this.id = id;
        this.tipoPieza = tp;
        for(int i = 0; i < 6; i ++){
            this.subCaras[i] = null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getSubCaras() {
        return subCaras;
    }

    public TipoPieza getTipoPieza() {
        return tipoPieza;
    }

    public void setTipoPieza(TipoPieza tipoPieza) {
        this.tipoPieza = tipoPieza;
    }
    
}
