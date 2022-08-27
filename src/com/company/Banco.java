package com.company;

public class Banco {
    private String nombreBanco;
    private double impuesto5L;
    private double impuesto20L;

    public Banco(String nombreBanco, double impuesto5L, double impuesto20L) {
        this.nombreBanco = nombreBanco;
        this.impuesto5L = impuesto5L;
        this.impuesto20L = impuesto20L;

    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public double getImpuesto5L() {
        return impuesto5L;
    }

    public double getImpuesto20L() {
        return impuesto20L;
    }

}
