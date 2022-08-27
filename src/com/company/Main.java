package com.company;
import java.awt.*;

import ucn.Registro;
import ucn.ArchivoEntrada;

import java.io.IOException;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        SistemaAguasImpl sys = new SistemaAguasImpl();
        String opcion1, opcion2, opcion3;
        boolean acceso = false;
        String passwordLogin = "";
        String rutLogin = "";

        //crear bancos
        sys.agregarBanco("Banco Estado",2.5,3.5);
        sys.agregarBanco("Banco de Chile",2.2,4);
        sys.agregarBanco("Banco Santander",2.7,3.8);
        sys.agregarBanco("Banco Bci",2.9,4.2);

        //lectura de aguas
        ArchivoEntrada archivo = new ArchivoEntrada("aguas.txt");
        while (!archivo.isEndFile()) {
            Registro registro = archivo.getRegistro();

            String marcaAgua = registro.getString();
            int presio5L = registro.getInt();
            int presio20L = registro.getInt();
            double impuesto = registro.getDouble();
            sys.agregarAgua(marcaAgua, presio5L, presio20L, impuesto);
        }

        //lectura de clientes
        ArchivoEntrada archivo2 = new ArchivoEntrada("clientes.txt");
        while (!archivo2.isEndFile()) {
            Registro registro2 = archivo2.getRegistro();

            String nombreCliente = registro2.getString();
            String apellidoCliente = registro2.getString();
            String rutCliente = registro2.getString();
            String direccinCliente = registro2.getString();
            String contraseñaCliente = registro2.getString();


            String bancoClientetemp = registro2.getString();
            String pinCliente = registro2.getString();


            if (bancoClientetemp == null) {

                sys.agregarClienteExistenteNoBanco(nombreCliente, apellidoCliente, rutCliente, direccinCliente, contraseñaCliente);

            } else {

                int pinClienteInt = Integer.parseInt(pinCliente);
                Banco bancoCliente = sys.buscarBanco(bancoClientetemp);
                sys.agregarClienteExistenteBanco(nombreCliente,apellidoCliente,rutCliente,direccinCliente,contraseñaCliente,bancoCliente,pinClienteInt);

            }
        }

        //lectura de ventas
        ArchivoEntrada archivo3 = new ArchivoEntrada("ventas.txt");
        while (!archivo3.isEndFile()) {
            Registro registro3 = archivo3.getRegistro();

            String nombreCliente = registro3.getString();
            String apellidoCliente = registro3.getString();
            String rutCliente = registro3.getString();
            String direccinCliente = registro3.getString();
            String marcaAgua = registro3.getString();
            String tipoAgua = registro3.getString();
            int cantidadCompradas = registro3.getInt();
            double totalCompra = registro3.getDouble();
            sys.agregarVenta(nombreCliente,apellidoCliente,rutCliente,direccinCliente,marcaAgua,tipoAgua,cantidadCompradas,totalCompra);

        }

        try{


            for (int i = 0; i < 1; i++) {
                // int numero = 0/0;
                System.out.println("⠂⠂⠂⠂⢂⣂⠦⠔⠒⠒⣆⣂⣂⣂⠒\n" +
                        "⢀⡠⠤⠞⠉              ⢡⣴⠇\n" +
                        "⠘⣶⣦    ⢀⢄  ⡠⣀⠔⢄  ⡏\n" +
                        "  ⠈⡇  ⢠⠊⢸⠙⡄⢏⠸⢈⡆⢳\n" +
                        "  ⢰⠁  ⢸⠁  ⠨⣀⡤⠆⢀⣷⢸\n" +
                        "  ⠸⡴⢇⢸⢶⣤⡤⠤⢤⡴⡟⠘⠏\n" +
                        "        ⠁⢠⠃⡄    ⠈⢆\n" +
                        "          ⡎  ⡇      ⠘⡄\n" +
                        "          ⣏⢩⠇      ⣧⡹\n" +
                        "          ⠈⠉⣹⠉⢹⡏\n" +
                        "          ⢰⣋⡹⠰⣉⣙⡆" +
                        "burunya");
            }


            for (int i = 1; i <= 100; i++) {
                System.out.println("pene "+i);

            }




            do {


                System.out.println("\n====================== Aguas Antofagasta ===================\n" +
                        "\n Escoge una opción que desea realizar:" +
                        "\n -[1] Iniciar sesión" +
                        "\n -[2] Registrar usuario" +
                        "\n -[0] Salir aplicación");
                opcion1 = scan.next();



                switch (opcion1) {
                    case "1": {


                            System.out.println("\n===================== Iniciando sesión =====================\n");
                            System.out.print("Ingrese Rut: ");
                            rutLogin = scan.next();


                            System.out.print("Ingrese contraseña: ");
                            passwordLogin = scan.next();

                            acceso = sys.iniciarSesion(rutLogin, passwordLogin);




                        if(acceso == true){

                            do {
                                System.out.println("\n===================== Sesión iniciada ======================\n"
                                        + "\n -[1] Realizar compra"
                                        + "\n -[2] Gestionar cuenta"
                                        + "\n -[3] Ingresar marca de agua"
                                        + "\n -[4] Estadísticas"
                                        + "\n -[0] Cerrar sesión");
                                opcion2 = scan.next();


                                switch (opcion2) {
                                    case "1": {

                                        System.out.println("\n===================== Realizando compra ====================\n");


                                        if (sys.buscarRutCliete(rutLogin).getBanco() != null) {
                                            sys.realizarCompra(rutLogin);
                                            break;
                                        } else {
                                            System.out.println("El usuario no tiene banco asociado\n" +
                                                    "afiliarse a banco antes de continuar");
                                            break;
                                        }

                                    }


                                    case "2": {

                                        do {
                                            System.out.println("\n==================== Gestionando cuenta ====================\n"
                                                    + "\n -[1] Consultar Datos"
                                                    + "\n -[2] Afiliarse a un banco"
                                                    + "\n -[3] Desafilarse a un banco"
                                                    + "\n -[4] Cambiar contraseña"
                                                    + "\n -[5] Cambiar PIN"
                                                    + "\n -[6] Reiniciar PIN"
                                                    + "\n -[0] Salir");


                                            opcion3 = scan.next();

                                            switch (opcion3) {

                                                case "1": {

                                                    System.out.println("\n************************************************************");
                                                    sys.consultarDatos(rutLogin);
                                                    System.out.println("************************************************************\n");
                                                    break;
                                                }

                                                case "2": {

                                                    System.out.println("\n======================= Afiliándose ========================\n");
                                                    sys.afiliarBanco(rutLogin);
                                                    break;
                                                }

                                                case "3": {

                                                    System.out.println("\n====================== Desafilándose ======================\n");
                                                    sys.desafiliarBanco(rutLogin);
                                                    break;
                                                }

                                                case "4": {

                                                    System.out.println("\n=================== Cambiando contraseña ===================\n");
                                                    sys.cambiarContraseña(rutLogin);
                                                    break;
                                                }

                                                case "5": {

                                                    System.out.println("\n======================= Cambiando PIN ======================\n");
                                                    sys.cambiarPin(rutLogin);

                                                    break;
                                                }

                                                case "6": {

                                                    System.out.println("\n************************************************************");
                                                    sys.reiniciarPin(rutLogin);
                                                    System.out.println("************************************************************\n");
                                                    break;
                                                }
                                            }



                                        } while (!opcion3.equals("0"));
                                        break;
                                    }


                                    case "3": {
                                        System.out.println("\n================= Ingresando marca de agua =================\n");
                                        sys.crearAgua();
                                        break;
                                    }

                                    case "4": {

                                        System.out.println("\n======================= Estadísticas =======================\n");
                                        sys.estadisticas();
                                        break;
                                    }

                                }


                            } while (!opcion2.equals("0"));

                        }else{
                            break;
                        }


                        break;
                    }

                    case "2": {
                        System.out.println("\n==================== Registrando usuario ===================\n");
                        sys.crearUsuario();
                        break;
                    }

                    default: {
                        sys.salir();
                    }
                }


            }while(!opcion1.equals("0"));{
                System.out.println("\n==================== Cerrando aplicación ===================\n");

                sys.salir();
            }

        }catch (Exception e){
            Toolkit.getDefaultToolkit().beep();
            System.out.println(
                    "░░░░░░░░░░░░▄▐\n" +
                    "░░░░░░▄▄▄░░▄██▄\n" +
                    "░░░░░▐▀█▀▌░░░░▀█▄\n" +
                    "░░░░░▐█▄█▌░░░░░░▀█▄\n" +
                    "░░░░░░▀▄▀░░░▄▄▄▄▄▀▀\n" +
                    "░░░░▄▄▄██▀▀▀▀\n" +
                    "░░░█▀▄▄▄█░▀▀\n" +
                    "░░░▌░▄▄▄▐▌▀▀▀\n" +
                    "▄░▐░░░▄▄░█░▀▀ U HAVE BEEN SPOOKED BY THE\n" +
                    "▀█▌░░░▄░▀█▀░▀   [ "+e+" ]  E R R O R \n"+
                    "░░░░░░░▄▄▐▌▄▄\n" +
                    "░░░░░░░▀███▀█░▄\n" +
                    "░░░░░░▐▌▀▄▀▄▀▐▄\n" +
                    "░░░░░░▐▀░░░░░░▐▌\n" +
                    "░░░░░░█░░░░░░░░█\n" +
                    "░░░░░▐▌░░░░░░░░░█\n" +
                    "░░░░░█░░░░░░░░░░▐\n" +
                            "CERRANDO SISTEMA");
        }
    }
}