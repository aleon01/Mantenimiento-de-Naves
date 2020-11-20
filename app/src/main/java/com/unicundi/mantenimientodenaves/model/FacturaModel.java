package com.unicundi.mantenimientodenaves.model;

public class FacturaModel {
    private String facturaID;
    private String nombrePer;
    private String codPer;
    private String numFactura;
    private String fecha;
    private String producto;
    private String cantidad;
    private String descripcion;
    private String TotalFactura;

    //Constructor vacio
    public FacturaModel() {
    }

    //Constructor con los objetos

    public FacturaModel(String nombre, String apellido, String numFactura, String descripcion, String total) {
    }


    //Getter and Setter

    public String getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(String facturaID) {
        this.facturaID = facturaID;
    }

    public String getNombrePer() {
        return nombrePer;
    }

    public void setNombrePer(String nombrePer) {
        this.nombrePer = nombrePer;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotalFactura() {
        return TotalFactura;
    }

    public void setTotalFactura(String totalFactura) {
        TotalFactura = totalFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodPer() {
        return codPer;
    }

    public void setCodPer(String codPer) {
        this.codPer = codPer;
    }

    @Override
    public String toString() {
        return nombrePer+"\n Num. factura: "+ numFactura+"\n Actividad: "+descripcion+"\n Total: "+ TotalFactura;
    }
}
