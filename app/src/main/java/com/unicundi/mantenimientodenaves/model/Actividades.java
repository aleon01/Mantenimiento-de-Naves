package com.unicundi.mantenimientodenaves.model;

public class Actividades {

    private String idActividad;
    private String nombreActividad;
    private String descripcionActividad;
    private int estadoActvidad;

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public int getEstadoActvidad() {
        return estadoActvidad;
    }

    public void setEstadoActvidad(int estadoActvidad) {
        this.estadoActvidad = estadoActvidad;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public String toString() {
        return nombreActividad;
    }

}

