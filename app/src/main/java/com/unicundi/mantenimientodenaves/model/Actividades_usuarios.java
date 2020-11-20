package com.unicundi.mantenimientodenaves.model;

public class Actividades_usuarios {

    private String codigo;
    private String idActividad;
    private String nombreActividad;
    private String codEmpleado;
    private String nombre;
    private String Estado;
    private String insumos;

    public Actividades_usuarios() {
    }

    public Actividades_usuarios(String nombreActividad, String nombre) {
        this.nombreActividad = nombreActividad;
        this.nombre = nombre;
    }

    public String getInsumos() {
        return insumos;
    }

    public void setInsumos(String insumos) {
        this.insumos = insumos;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String  toString() { return "Empleado: "+nombreActividad +"\nActividad: "+ nombre; }
}
