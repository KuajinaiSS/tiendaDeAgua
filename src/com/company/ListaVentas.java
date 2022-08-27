package com.company;

import java.util.Arrays;

public class ListaVentas {
    private Venta[] vecVenta;
    private int cantActual;
    private int max;

    public ListaVentas(int max){
        this.max = max;
        cantActual=0;
        vecVenta = new Venta[max];

    }

    public boolean agregarVenta(Venta venta){
        if(cantActual>=max){
            return false;
        }
            vecVenta[cantActual] = venta;
            cantActual++;
            return true;

    }

    public Venta[] getVecVenta() {
        return vecVenta;
    }

    public int getCantActual() {
        return cantActual;
    }

}
