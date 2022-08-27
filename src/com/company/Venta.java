package com.company;

public class Venta {

    private String nombreCliente;
    private String apellidoCliente;
    private String rutCliente;
    private String direccionCliente;
    private String marcaAgua;
    private String tipoBidon;
    private int cantidadCompras;
    private double totalCompra;

    public Venta(String nombreCliente, String apellidoCliente, String rutCliente, String direccionCliente, String marcaAgua, String tipoBidon, int cantidadCompras, double totalCompra) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.rutCliente = rutCliente;
        this.direccionCliente = direccionCliente;
        this.marcaAgua = marcaAgua;
        this.tipoBidon = tipoBidon;
        this.cantidadCompras = cantidadCompras;
        this.totalCompra = totalCompra;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }


    public String getRutCliente() {
        return rutCliente;
    }


    public String getDireccionCliente() {
        return direccionCliente;
    }


    public String getMarcaAgua() {
        return marcaAgua;
    }


    public String getTipoBidon() {
        return tipoBidon;
    }


    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

}
