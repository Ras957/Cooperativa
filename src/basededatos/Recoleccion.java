/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basededatos;

import java.util.Date;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Recoleccion {
    private int codigoRecoleccion;
    private String anyo;
    private int codigoSocio;
    private int codigoAceituna;
    private int kilos;
    private float precioKilo;
    private Date fechaEntrega;

    /**
     * @return the codigoRecoleccion
     */
    public int getCodigoRecoleccion() {
        return codigoRecoleccion;
    }

    /**
     * @param codigoRecoleccion the codigoRecoleccion to set
     */
    public void setCodigoRecoleccion(int codigoRecoleccion) {
        this.codigoRecoleccion = codigoRecoleccion;
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

    /**
     * @return the codigoSocio
     */
    public int getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(int codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    /**
     * @return the codigoAceituna
     */
    public int getCodigoAceituna() {
        return codigoAceituna;
    }

    /**
     * @param codigoAceituna the codigoAceituna to set
     */
    public void setCodigoAceituna(int codigoAceituna) {
        this.codigoAceituna = codigoAceituna;
    }

    /**
     * @return the kilos
     */
    public int getKilos() {
        return kilos;
    }

    /**
     * @param kilos the kilos to set
     */
    public void setKilos(int kilos) {
        this.kilos = kilos;
    }

    /**
     * @return the precioKilo
     */
    public float getPrecioKilo() {
        return precioKilo;
    }

    /**
     * @param precioKilo the precioKilo to set
     */
    public void setPrecioKilo(float precioKilo) {
        this.precioKilo = precioKilo;
    }

    /**
     * @return the fechaEntrega
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
}
