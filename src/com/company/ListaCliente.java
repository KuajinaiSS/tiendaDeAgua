package com.company;


import java.util.Arrays;

public class ListaCliente {
    private Cliente[] vecCliente;
    private int cantActual;
    private int max;

    public ListaCliente(int max){
        this.max = max;
        cantActual=0;
        vecCliente = new Cliente[max];
    }


    public Cliente buscarCliente(String nombre){
        for (int i = 0; i < cantActual; i++) {

            if(nombre.equalsIgnoreCase(vecCliente[i].getNombre())){

                return vecCliente[i];
            }
        }
        return null;
    }

    public Cliente buscarRutCliente(String rut){
        for (int i = 0; i < cantActual; i++) {
            if(rut.equals(vecCliente[i].getRut())){
                return vecCliente[i];
            }
        }
        return null;
    }

    public boolean agregarCliente(Cliente cliente){
        if(cantActual>=max){
            return false;
        }

        if(buscarCliente(cliente.getNombre())==null){
            vecCliente[cantActual] = cliente;
            cantActual++;
            return true;

        }else{
            return false;
        }
    }

    public Cliente[] getVecCliente() {
        return vecCliente;
    }

    public int getCantActual() {
        return cantActual;
    }

}
