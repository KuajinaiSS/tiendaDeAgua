package com.company;
import java.util.Arrays;

public class ListaAgua {
    private Agua[] vecAgua;
    private int cantActual;
    private int max;

    public ListaAgua(int max){
        this.max = max;
        cantActual=0;
        vecAgua = new Agua[max];
    }

    public Agua buscarAgua(String nombre){
        for (int i = 0; i < cantActual; i++) {

            if(nombre.equalsIgnoreCase(vecAgua[i].getNombre())){
                return vecAgua[i];
            }
        }
        return null;
    }


    public boolean agregarAgua(Agua agua){
        if(cantActual>=max){
            return false;
        }

        if(buscarAgua(agua.getNombre())==null){
            vecAgua[cantActual] = agua;
            cantActual++;
            return true;

        }else{
            System.out.println("El agua ya esta registrada");
            return false;
        }
    }

    public Agua[] getVecAgua() {
        return vecAgua;
    }

    public int getCantActual() {
        return cantActual;
    }

    public void imprimirAguasVenta(){
        for (int i = 0; i < cantActual; i++) {
            System.out.println("//"+vecAgua[i].getNombre()+"//"+"\nPrecio 5L= "+ vecAgua[i].getPrecio5L()+"\nPrecio 20L= "+ vecAgua[i].getPrecio20L()+"\n"  );
        }
    }

}
