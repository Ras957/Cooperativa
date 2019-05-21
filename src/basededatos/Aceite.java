/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basededatos;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Aceite {

    private int codigoProduccion;
    private int litros;
    private float precioLitro;
    private String anyo;
    
    /**
     * @return the codigoProduccion
     */
    public int getCodigoProduccion() {
        return codigoProduccion;
    }

    /**
     * @param codigoProduccion the codigoProduccion to set
     */
    public void setCodigoProduccion(int codigoProduccion) {
        this.codigoProduccion = codigoProduccion;
    }

    /**
     * @return the litros
     */
    public int getLitros() {
        return litros;
    }

    /**
     * @param litros the litros to set
     */
    public void setLitros(int litros) {
        this.litros = litros;
    }

    /**
     * @return the precioLitro
     */
    public float getPrecioLitro() {
        return precioLitro;
    }

    /**
     * @param precioLitro the precioLitro to set
     */
    public void setPrecioLitro(float precioLitro) {
        this.precioLitro = precioLitro;
    }

    /**
     * @return the anyo
     */
    public String getAnyo() {
        return anyo;
    }

    /**
     * @param anyo the anyo to set
     */
    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }
    
}
