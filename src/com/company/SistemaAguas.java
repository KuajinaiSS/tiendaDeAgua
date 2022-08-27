package com.company;

import java.io.IOException;

public interface SistemaAguas {

    public boolean iniciarSesion(String rut,String password);

    public boolean realizarCompra(String banco);

    public void consultarDatos(String rut);

    public boolean afiliarBanco(String rut);

    public boolean desafiliarBanco(String rut);

    public boolean cambiarContraseña(String rut);

    public boolean cambiarPin(String rut);

    public void reiniciarPin(String rut);

    public boolean agregarAgua(String marca,int precio5L,int precio20L,double impuesto );

    public void bancoMasClientes();

    public void clienteMasVentas();

    public void aguaMenosVentas();

    public void cantComprasBidones();

    public void salir() throws IOException;
}


