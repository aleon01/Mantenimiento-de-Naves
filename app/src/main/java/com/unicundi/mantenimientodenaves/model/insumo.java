package com.unicundi.mantenimientodenaves.model;

public class insumo {

    private String codigo ;
    private String nombre;
    private String cantidad;
    private String valor;

    public insumo(String codigo, String nombre, String cantidad, String valor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valor = valor;
    }

    public insumo() {

    }

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad (String cantidad) {
        this.cantidad = cantidad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString (){return nombre+" Cantidad: "+cantidad+ " Valor: "+valor;}

}
