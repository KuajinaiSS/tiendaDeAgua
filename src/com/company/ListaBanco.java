package com.company;
import java.util.Arrays;

public class ListaBanco {
    private Banco[] vecBanco;
    private int cantActual;
    private int max;
    public ListaBanco(int max){
        this.max = max;
        cantActual=0;
        vecBanco = new Banco[max];

    }


    public Banco buscarBanco(String nombre){
        for (int i = 0; i < cantActual; i++) {

            if(nombre.equalsIgnoreCase(vecBanco[i].getNombreBanco())){
                return vecBanco[i];
            }
        }
        return null;
    }


    public boolean agregarBanco(Banco banco){
        if(cantActual>=max){
            return false;
        }
        if(buscarBanco(banco.getNombreBanco())==null){
            vecBanco[cantActual] = banco;
            cantActual++;
            return true;
        }else{
            return false;
        }
    }

    public Banco[] getVecBanco() {
        return vecBanco;
    }


    public void imprimirBancos(){
        for (int i = 0; i < cantActual; i++) {
            System.out.println("["+(i+1)+"] "+vecBanco[i].getNombreBanco()+"\nImpuesto 5L= "+ vecBanco[i].getImpuesto5L()+"\nImpuesto 20L= "+ vecBanco[i].getImpuesto20L()+"\n"  );
        }
    }

}
