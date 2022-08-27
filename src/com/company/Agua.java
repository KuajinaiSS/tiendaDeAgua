package com.company;

public class Agua {

    private int ventas;

    private String nombre;
    private int precio5L;
    private int precio20L;
    private double impuesto;

    public Agua(String nombre, int precio5L, int precio20L, double impuesto) {
        this.nombre = nombre;
        this.precio5L = precio5L;
        this.precio20L = precio20L;
        this.impuesto = impuesto;

        this.ventas=0;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public String getNombre() {
        return nombre;
    }


    public int getPrecio5L() {
        return precio5L;
    }


    public int getPrecio20L() {
        return precio20L;
    }


    public double getImpuesto() {
        return impuesto;
    }

}
