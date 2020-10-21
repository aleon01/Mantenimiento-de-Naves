package com.unicundi.mantenimientodenaves.model;

public class Usuarios {
    private int cod;
    private String nombre;
    private String apellido;
    private String tidentificacion;
    private String identifcacion;
    private String telefono;
    private String direccion;
    private String correo;
    private String contrasena;
    private int rol;
    private int estado;

    public Usuarios() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTidentificacion() {
        return tidentificacion;
    }

    public void setTidentificacion(String tidentificacion) {
        this.tidentificacion = tidentificacion;
    }

    public String getIdentifcacion() {
        return identifcacion;
    }

    public void setIdentifcacion(String identifcacion) {
        this.identifcacion = identifcacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
