package com.company;
import ucn.ArchivoSalida;
import ucn.Registro;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class SistemaAguasImpl implements SistemaAguas  {
    private ListaAgua listaAgua;
    private ListaCliente listaCliente;
    private ListaVentas listaVentas;
    private ListaBanco listaBanco;

    Scanner scan = new Scanner(System.in);

    public SistemaAguasImpl() {
        listaAgua = new ListaAgua(10);
        listaCliente = new ListaCliente(20);
        listaVentas = new ListaVentas(25);
        listaBanco = new ListaBanco(4);

    }


    /**
     * Este método sirve para iniciar sesión,
     * recibe un RUT y una contraseña luego corrobora
     * si está el RUT en la lista de clientes, si el RUT
     * EXISTE, corrobora si la contraseña es igual a la
     * que está ligada a ese RUT si una de estas no se cumple
     * arroja cuál de estas no se cumplió
     * @param rut
     * @param password
     * @return
     */
    @Override
    public boolean iniciarSesion(String rut, String password) {



        if (listaCliente.buscarRutCliente(rut) == null) {
            System.out.println("rut Incorrecto");
            return false;

        } else {

            if (listaCliente.buscarRutCliente(rut).getPassword().equals(password)){
                return true;


            } else {
                System.out.println("contraseña Incorrecta");
                return false;
            }
        }
    }


    /**
     * Este método recibe el RUT con el que se inicia sesión,
     * para que con este RUT desplegar NOMBRE, APELLIDO, RUT, BANCO, PIN
     * pero el PIN esta cifrado los primeros 2 números con 'x' [xx12].
     * Si el RUT NO está afiliado a un banco, BANCO Y PIN saldrán como ninguno
     * @param rut
     */
    @Override
    public void consultarDatos(String rut) {

        System.out.println("Nombre Cliente: " + buscarRutCliete(rut).getNombre());
        System.out.println("Apellido Cliente: " + buscarRutCliete(rut).getApellido());
        System.out.println("RUT: " + buscarRutCliete(rut).getRut());
        if (buscarRutCliete(rut).getBanco() != null) {

            System.out.println("Banco afiliado: " + buscarRutCliete(rut).getBanco().getNombreBanco());
            System.out.print("PIN: ");

            int pin = buscarRutCliete(rut).getPin();
            char[] x_x_p_i_n = String.valueOf(pin).toCharArray();

            for (int i = 0; i < x_x_p_i_n.length; i++) {
                if (i == (x_x_p_i_n.length - 2)) {
                    System.out.println(x_x_p_i_n);
                    break;
                }
                x_x_p_i_n[i] = 'x';
            }

        } else {
            System.out.println("Banco afiliado: ninguno");
            System.out.println("PIN: ninguno");
        }


    }


    /**
     * Este método recibe el RUT con el que se inició sesión
     * primero corrobora si él el RUT está afiliado a un banco, si NO esta
     * afiliado a un banco despliega los bancos dando a elegir al usuario a
     * cual quire afiliarse para luego afiliarse a ese banco y crear un PIN
     * que serán los últimos 4 dígitos del RUT (si es k se remplaza por 0),
     * si el usuario Está afiliado a un banco desplegara que no se puede afiliar
     * porque ya está afiliado a un banco y el banco al que está afiliado
     * @param rut
     * @return
     */
    @Override
    public boolean afiliarBanco(String rut) {
        if (buscarRutCliete(rut).getBanco() == null) {

            System.out.println("Ingrese a que banco te quieres afiliar\n");
            desplegarBanco();
            String bancoAfiliar = scan.next();

            switch (bancoAfiliar){

                case "1":{
                    buscarRutCliete(rut).setBanco(buscarBanco("Banco Estado"));
                    buscarRutCliete(rut).setPin(crearPinRut(rut));
                    System.out.println("Te as afiliado a "+buscarRutCliete(rut).getBanco().getNombreBanco()+" correctamente");
                    return true;
                }


                case "2":{
                    buscarRutCliete(rut).setBanco(buscarBanco("Banco de Chile"));
                    buscarRutCliete(rut).setPin(crearPinRut(rut));
                    System.out.println(buscarRutCliete(rut).getBanco().getNombreBanco());
                    return true;
                }


                case "3":{
                    buscarRutCliete(rut).setBanco(buscarBanco("Banco Santander"));
                    buscarRutCliete(rut).setPin(crearPinRut(rut));
                    System.out.println(buscarRutCliete(rut).getBanco().getNombreBanco());
                    return true;
                }


                case "4":{
                    buscarRutCliete(rut).setBanco(buscarBanco("Banco Bci"));
                    buscarRutCliete(rut).setPin(crearPinRut(rut));
                    System.out.println(buscarRutCliete(rut).getBanco().getNombreBanco());
                    return true;
                }

                default:{
                    System.out.println("el banco no existe");
                    return  false;
                }
            }
        }
        System.out.println("ya estas afiliado al banco "+buscarRutCliete(rut).getBanco().getNombreBanco());
        return false;
    }


    /**
     * Este método recibe el RUT con el que se inició sesión,
     * crea un pin con los últimos dígitos del RUT
     * @param rut
     * @return
     */
    public int crearPinRut(String rut){

        char cuatro = rut.charAt(9);
        char tres = rut.charAt(7);
        char dos = rut.charAt(6);
        char uno = rut.charAt(5);


        String unoo = String.valueOf(uno);
        String doss =String.valueOf(dos);
        String tress =String.valueOf(tres);
        String cuatroo =String.valueOf(cuatro);

        if(cuatroo.equalsIgnoreCase("k")){
            cuatroo = "0";
        }

        String PIN = unoo+doss+tress+cuatroo;
        int pin = Integer.parseInt(PIN);
        System.out.println("Tu PIN será tus últimos 4 dígitos de tu RUT");
        return pin;
    }



    /**
     * Este método recibe el RUT con el que se inició sesión,
     * revisa si el RUT está afiliado a un banco, si Está afiliado
     * a un banco le pedirá el PIN, si el PIN coincide se volverá a
     * preguntar si se quiere desafilar, si responde que si el usuario
     * ya no pertenecerá a un banco, de lo contrario no hará nada
     * @param rut
     * @return
     */
    @Override
    public boolean desafiliarBanco(String rut) {

        if(buscarRutCliete(rut).getBanco()!=null){
            System.out.print("Ingrese el PIN para desafilarse del banco: ");
            String pinIngresadoo = scan.next();

            if(validacionInt(pinIngresadoo)==false){
                return false;
            }

            int pinIngresado = Integer.parseInt(pinIngresadoo);

            if(pinIngresado==buscarRutCliete(rut).getPin()){
                System.out.println("Seguro que se quiere desafilar del banco "+buscarRutCliete(rut).getBanco().getNombreBanco()+"" +
                        "\n[1] SI" +
                        "\n[2] NO");
                String respuesta = scan.next();
                switch (respuesta){
                    case "1":{
                        buscarRutCliete(rut).setBanco(null);
                        System.out.println("Se a desafilado del banco con éxito");
                        return true;
                    }
                    default:{
                        System.out.println("NO se a desafilado del banco");
                        return false;
                    }
                }
            }else{
                System.out.println("PIN incorrecto");
                return false;
            }
        }else{
            System.out.println("no esta afiliado a un banco");
            return false;
        }
    }


    /**
     * Este método recibe el RUT con el que se inició sesión,
     * pregunta al usuario su contraseña actual, si esta es Igual
     * procederá a preguntarle a cuál contraseña quiere cambiar, si
     * la contraseña ingresada es mayor o igual a 6 caracteres,
     * esta se cambiara a esta última ingresada, de lo contrario
     * mandara el error correspondiente
     * @param rut
     * @return
     */
    @Override
    public boolean cambiarContraseña(String rut) {

        System.out.print("Ingrese la contraseña actual: ");
        String contraseñaAnterior = scan.next();
        System.out.print("Ingrese su nueva contraseña: ");
        String nuevaContraseña = scan.next();

        if(contraseñaAnterior.equals(buscarRutCliete(rut).getPassword())) {
            if (nuevaContraseña.length() >= 6) {
                buscarRutCliete(rut).setPassword(nuevaContraseña);
                System.out.println("Contraseña cambiada con éxito a " + buscarRutCliete(rut).getPassword());
                return true;
            } else {
                System.out.println("La contraseña no es mayor a 6 caracteres");
                return false;
            }
        }else{

            System.out.println("La contraseña no es idéntica a la actual");
            return false;
        }
    }


    /**
     * El método recibe el RUT con el que se inició sesión,
     * primero corroborara si el usuario pertenece a un banco
     * si pertenece a un banco le pedirá el PIN asociado a ese banco
     * , si este es igual procederá a preguntarle a qué pin
     * numérico de 4 caracteres quiere cambiar si el pin ingresado
     * es correcto se cambiara a ese, de lo contrario
     * desplegara que sucedido correspondientemente
     * @param rut
     * @return
     */
    @Override
    public boolean cambiarPin(String rut) {
        if (buscarRutCliete(rut).getBanco() != null) {
            System.out.print("Ingrese el PIN actual: ");


            String pinActuall = scan.next();

            if(validacionInt(pinActuall)==false){
                return false;
            }

            int pinActual = Integer.parseInt(pinActuall);


            System.out.print("Ingrese el PIN de 4 números que quiere cambiar ");



            String pinNuevoo = scan.next();

            if(validacionInt(pinNuevoo)==false){
                return false;
            }

            int pinNuevo = Integer.parseInt(pinNuevoo);


            String tamañoPinNuevo = Integer.toString(pinNuevo);

            if (pinActual == buscarRutCliete(rut).getPin()) {
                if (tamañoPinNuevo.length() == 4) {
                    buscarRutCliete(rut).setPin(pinNuevo);
                    System.out.println("El PIN se a cambiado exitosamente a " + buscarRutCliete(rut).getPin());
                    return true;
                } else {
                    System.out.println("El tamaño del PIN no es de 4 caracteres");
                    return false;
                }
            } else {
                System.out.println("El PIN ingresado es erróneo");
                return false;
            }
        } else {
            System.out.println("No esta afiliado a un banco");
            return false;
        }
    }


    /**
     * Este método recibe el RUT con el que se inició sesión,
     * este método reinicia el PIN a sus últimos 4 dígitos del RUT
     * con ayuda del método crearPinRut(); primero corrobora que el
     * RUT este asociado a un banco, si está asociado procede a reiniciar
     * el PIN, de no ser así, desplegara el error correspondiente
     * @param rut
     */
    @Override
    public void reiniciarPin(String rut) {
        buscarRutCliete(rut).setPin(crearPinRut(rut));
    }


    /**
     * Este método apaga el sistema, por lo que antes de apagarlo
     * reescribe los archivos TXT con la información nueva que se ingresó
     * durante la ejecución del sistema
     * @throws IOException
     */
    @Override
    public void salir() throws IOException {
        escrituraClientes();
        escrituraAguas();
        escrituraVentas();
    }

    /**
     * sobreescribe el archivo "clientes.txt" con la información
     *      * que se recolectó durante la ejecución del sistema
     * @throws IOException
     */
    public void escrituraClientes() throws IOException {

        ArchivoSalida archivoClientes = new ArchivoSalida("clientes.txt");

        for (int i = 0; i < listaCliente.getCantActual(); i++) {
            if(listaCliente.getVecCliente()[i].getBanco()==null){
                Registro registroSalida = new Registro(5);

                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getNombre());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getApellido());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getRut());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getDireccion());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getPassword());

                archivoClientes.writeRegistro(registroSalida);


            }else{
                Registro registroSalida = new Registro(7);

                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getNombre());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getApellido());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getRut());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getDireccion());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getPassword());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getBanco().getNombreBanco());
                registroSalida.agregarCampo(listaCliente.getVecCliente()[i].getPin());

                archivoClientes.writeRegistro(registroSalida);
            }
        }
        archivoClientes.close();
    }

    /**
     * sobre escribe el atchivo "aguas.txt" con la informacion
     * que se recolecto durante la ejecucion del sistema
     * @throws IOException
     */
    public void escrituraAguas() throws IOException {

        ArchivoSalida archivoAguas = new ArchivoSalida("aguas.txt");
        for (int i = 0; i < listaAgua.getCantActual(); i++) {
            Registro registroSalida = new Registro(4);
            registroSalida.agregarCampo(listaAgua.getVecAgua()[i].getNombre());
            registroSalida.agregarCampo(listaAgua.getVecAgua()[i].getPrecio5L());
            registroSalida.agregarCampo(listaAgua.getVecAgua()[i].getPrecio20L());
            registroSalida.agregarCampo(listaAgua.getVecAgua()[i].getImpuesto());
            archivoAguas.writeRegistro(registroSalida);
        }
        archivoAguas.close();
    }

    /**
     * sobreescribe el archivo "ventas.txt" con la información
     * que se recolectó durante la ejecución del sistema
     * @throws IOException
     */
    public void escrituraVentas() throws IOException {


        ArchivoSalida archivosVentas = new ArchivoSalida("ventas.txt");
        for (int i = 0; i < listaVentas.getCantActual(); i++) {
            Registro registroSalida = new Registro(8);
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getNombreCliente());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getApellidoCliente());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getRutCliente());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getDireccionCliente());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getMarcaAgua());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getTipoBidon());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getCantidadCompras());
            registroSalida.agregarCampo(listaVentas.getVecVenta()[i].getTotalCompra());

            archivosVentas.writeRegistro(registroSalida);
        }
        archivosVentas.close();
    }



    /**
     * Este método despliega las estadísticas del sistema[banco más clientes, cliente más compras, agua con menos ventas, cliente que compro más bidones (5L y 20L)]
     */
    public void estadisticas(){
        clienteMasVentas();
        aguaMenosVentas();
        cantComprasBidones();
        bancoMasClientes();

    }

    /**
     * Despliega el banco con más clientes
     */
    @Override
    public void bancoMasClientes() {
        int bancoEstado=0;
        int bancoChile=0;
        int bancoSantander=0;
        int bancoBci=0;
        int[] bancos = new int[] {bancoEstado,bancoChile,bancoSantander,bancoBci};
        int indiceDelMayor = 0;


        for (int i = 0; i < listaCliente.getCantActual(); i++) {
            if (listaCliente.getVecCliente()[i].getBanco()!=null){//si tiene banco
                if(listaCliente.getVecCliente()[i].getBanco().getNombreBanco().equals("Banco Estado")){
                    bancoEstado=bancoEstado+1;
                }

                if(listaCliente.getVecCliente()[i].getBanco().getNombreBanco().equals("Banco de Chile")){
                    bancoChile = bancoChile+1;
                }

                if(listaCliente.getVecCliente()[i].getBanco().getNombreBanco().equals("Banco Santander")){
                    bancoSantander=bancoSantander+1;
                }

                if(listaCliente.getVecCliente()[i].getBanco().getNombreBanco().equals("Banco Bci")){
                    bancoBci = bancoBci+1;
                }
            }
        }



        for (int x = 1; x < bancos.length; x++) {
            if (bancos[x] > bancos[indiceDelMayor]) {
                indiceDelMayor = x;
            }
        }

        int mayor = bancos[indiceDelMayor];

        System.out.println("\nEl banco con menos afiliados es: '"+listaBanco.getVecBanco()[indiceDelMayor].getNombreBanco()+"'  con "+mayor+" afiliados\n");

    }

    /**
     * Despliega el cliente con más compras
     */
    @Override
    public void clienteMasVentas() {
        int maxCompras20L = 0;
        Cliente clienteMax20L = null;

        int maxCompras5L = 0;
        Cliente clienteMax5L = null;


        for (int i = 0; i < listaCliente.getCantActual(); i++) {//20litros
            if(listaCliente.getVecCliente()[i].getCompras20L()>maxCompras20L){
                maxCompras20L = listaCliente.getVecCliente()[i].getCompras20L();
                clienteMax20L = listaCliente.getVecCliente()[i];
            }
        }


        for (int i = 0; i < listaCliente.getCantActual(); i++) {//5litros
            if(listaCliente.getVecCliente()[i].getCompras5L()>maxCompras5L){
                maxCompras5L = listaCliente.getVecCliente()[i].getCompras5L();
                clienteMax5L = listaCliente.getVecCliente()[i];
            }
        }

        System.out.println("El cliente con mas compras de 20L:\n-Nombre: "+clienteMax20L.getNombre()+" "+clienteMax20L.getApellido()+"\n-Dirección: "+clienteMax20L.getDireccion()+"\n-Unidades compradas: "+clienteMax20L.getCompras20L()+"\n");
        System.out.println("El cliente con mas compras de 5L:\n-Nombre: "+clienteMax5L.getNombre()+" "+clienteMax5L.getApellido()+"\n-Dirección: "+clienteMax5L.getDireccion()+"\n-Unidades compradas: "+clienteMax5L.getCompras5L()+"\n");

    }

    /**
     * Despliega el agua con menos ventas
     */
    @Override
    public void aguaMenosVentas() {
        Agua aguaMenosVentas = null;
        int menosVentas = 999999999;


        for (int i = 0; i < listaAgua.getCantActual(); i++) {//20litros

            if(listaAgua.getVecAgua()[i].getVentas()<menosVentas){

                menosVentas = listaAgua.getVecAgua()[i].getVentas();
                aguaMenosVentas = listaAgua.getVecAgua()[i];
            }
        }

        System.out.println("El agua con menos ventas es:\n-Nombre: "+aguaMenosVentas.getNombre()+"\n-Ventas: "+aguaMenosVentas.getVentas());

    }

    /**
     * Despliega cliente que compro mas bidones (5L y 20L)
     */
    @Override
    public void cantComprasBidones() {
        int masDeUnaCompra =0;

        for (int i = 0; i < listaAgua.getCantActual(); i++) {
            if(listaAgua.getVecAgua()[i].getVentas()>1){
                masDeUnaCompra = masDeUnaCompra +1;
            }
        }
        System.out.println("\nVeces que se compro mas de un bidón: "+masDeUnaCompra);
    }


    /**
     * Este método recibe el nombre del banco a buscar, para luego buscar si este existe en
     * la lista de bancos
     * @param nombreBanco
     * @return La clase banco buscada
     */
    public Banco buscarBanco(String nombreBanco){
        return listaBanco.buscarBanco(nombreBanco);
    }


    /**
     * Este método recibe un RUT y luego busca que cliente está asociado a ese RUT en la
     *  lista clientes
     * @param rut
     * @return el cliente
     */
    public Cliente buscarRutCliete(String rut){
        return listaCliente.buscarRutCliente(rut);
    }



    /**
     * Este método recibe el RUT con el que se inició sesión,
     * primero corrobora que el RUT este asociado a un banco
     * luego despliega las aguas que están registradas para luego
     * preguntarle que agua desea comprar, que litros desea comprar
     * y cuantas unidades de este va a comprar; Luego le despliega el
     * total de la compra y le solicita el PIN del banco asociado si lo
     * ingresa correctamente, la compra se registrara en ventas
     * @param rut
     * @return
     */
    @Override
    public boolean realizarCompra(String rut) {

        System.out.println("¿Que agua desea comprar?");

        desplegarAgua();

        System.out.print("Ingrese el nombre del agua que desea comprar: ");
        String nombre = scan.next();
        getListaAgua().buscarAgua(nombre);


        if(getListaAgua().buscarAgua(nombre)==null){
            System.out.println("El nombre ingresado no es valido");

        }else{
            System.out.println("Que litro va a comprar");

            System.out.println("[1] 5L = "+getListaAgua().buscarAgua(nombre).getPrecio5L());
            System.out.println("[2] 20L = "+getListaAgua().buscarAgua(nombre).getPrecio20L());





            String litro = scan.next();

            if(Objects.equals(litro, "1")){
                System.out.print("¿Cuantas unidades de 5L comprara?: ");

                String cantidadd = scan.next();


                if(validacionInt(cantidadd)==false){
                    return false;
                }

                int cantidad = Integer.parseInt(cantidadd);


                double impuestoBanco = (buscarRutCliete(rut).getBanco().getImpuesto5L())/100 ;
                double impuestoAgua = (getListaAgua().buscarAgua(nombre).getImpuesto())/100 ;
                double descuento = 0;

                if(cantidad>=5 && cantidad<=7){
                    descuento = (1)/100;
                }

                if(cantidad>=8 && cantidad<=10){
                    descuento = (1.3)/100;
                }

                if(cantidad>=11){
                    descuento = (1.75)/100;
                }

                double precioVenta =getListaAgua().buscarAgua(nombre).getPrecio5L()*( cantidad + impuestoBanco + impuestoAgua-descuento);


                System.out.println("El precio seria de $"+precioVenta);
                System.out.print("Ingrese el PIN para confirmar: ");
                int PINIngresado = scan.nextInt();

                if(PINIngresado == buscarRutCliete(rut).getPin()){

                    agregarVenta(buscarRutCliete(rut).getNombre(),buscarRutCliete(rut).getApellido(),rut,buscarRutCliete(rut).getDireccion(),nombre,"5L",cantidad,precioVenta);

                    System.out.println("Transacción realizada");
                    return true;

                }else{
                    System.out.println("PIN incorrecto");
                    return false;
                }
            }

            if(Objects.equals(litro, "2")){


                System.out.print("¿Cuantas unidades de 20L comprara?: ");
                String cantidadd = scan.next();

                if(validacionInt(cantidadd)==false){
                    return false;
                }

                int cantidad = Integer.parseInt(cantidadd);

                double impuestoBanco = (buscarRutCliete(rut).getBanco().getImpuesto20L())/100 ;
                double impuestoAgua = (getListaAgua().buscarAgua(nombre).getImpuesto())/100 ;
                double descuento = 0;

                if(cantidad>=5 && cantidad<=7){
                    descuento = (1)/100;
                }

                if(cantidad>=8 && cantidad<=10){
                    descuento = (1.3)/100;
                }

                if(cantidad>=11){
                    descuento = (1.75)/100;
                }

                double precioVenta =getListaAgua().buscarAgua(nombre).getPrecio20L()*( cantidad + impuestoBanco + impuestoAgua-descuento);

                System.out.println("El precio seria de $"+precioVenta);
                System.out.print("Ingrese el PIN para confirmar: ");

                String PINIngresadoo = scan.next();

                if(validacionInt(PINIngresadoo)==false){
                    return false;
                }


                int PINIngresado = Integer.parseInt(PINIngresadoo);
                if(PINIngresado == buscarRutCliete(rut).getPin()){

                    agregarVenta(buscarRutCliete(rut).getNombre(),buscarRutCliete(rut).getApellido(),rut,buscarRutCliete(rut).getDireccion(),nombre,"20L",cantidad,precioVenta);
                    System.out.println("Transacción realizada");
                    return true;

                }else{
                    System.out.println("PIN incorrecto");
                    return false;
                }
            }

        }
        return false;
    }//cambiar decimales

    /**
     * Este método corrobora si el String ingresado es un Int
     * para esto primero intenta transformarlo a int, si este manda error
     * lo encapsula y regresa que no es un número, si se logra la transformación
     * es que efectivamente se trata de un Int
     * @param cadena
     * @return
     */
    public boolean validacionInt(String cadena){
        int num;
        try{
            num = Integer.parseInt(cadena);
            return true;
        }catch (Exception e){

            System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("xxxxxx La acción no se realizo, no ingreso un numero xxxxxxx");
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

            return false;
        }
    }


    /**
     * Este método recibe [nombre, apellido, RUT, dirección, password, banco y pin] luego
     *      * usa el constructor correspondiente para usuarios QUE TIENEN BANCO, luego lo ingresa a
     *      * la lista de clientes, si el RUT está repetido el proceso arrogara el error correspondiente
     * @param nombre
     * @param apellido
     * @param rut
     * @param direccion
     * @param password
     * @param banco
     * @param pin
     * @return
     */
    public boolean agregarClienteExistenteBanco (String nombre,String apellido, String rut,String direccion, String password, Banco banco,int pin){
        Cliente nuevcliente = new Cliente(nombre,apellido,rut,direccion,password,banco,pin);
        listaCliente.agregarCliente(nuevcliente);
        return true;
    }

    /**
     * Este método recibe [nombre, apellido, RUT, dirección y password] luego
     * usa el constructor correspondiente para usuarios QUE NO TIENEN BANCO, luego lo ingresa a
     * a lista de clientes, si el RUT está repetido el proceso arrogara el error correspondiente
     *
     * @param nombre
     * @param apellido
     * @param rut
     * @param direccion
     * @param password
     * @return
     */
    public boolean agregarClienteExistenteNoBanco(String nombre,String apellido, String rut,String direccion, String password){
        Cliente nuevcliente = new Cliente(nombre,apellido,rut,direccion,password);
        listaCliente.agregarCliente(nuevcliente);
        return true;
    }

    /**
     * Este método recibe [nombre del agua, precio 5L, precio 20L y el impuesto del agua]
     * corroborara el nombre del agua ya existe, si esta no existe utilizara el constructor de agua
     * luego agregara el agua a la lista de aguas
     * @param nombre
     * @param precio5L
     * @param precio20L
     * @param impuesto
     * @return
     */
    @Override
    public boolean agregarAgua(String nombre, int precio5L, int precio20L, double impuesto) {
        Agua nuevaAgua = new Agua(nombre, precio5L, precio20L, impuesto);
        listaAgua.agregarAgua(nuevaAgua);
        return true;
    }

    /**
     * Este método recibe [nombre del banco, impuesto 5L e impuesto 20L]
     * creara los bancos que reciba con el constructor de Banco luego lo agregara
     * a la lista bancos
     * @param nombreBanco
     * @param impuesto5L
     * @param impuesto20L
     * @return
     */
    public boolean agregarBanco(String nombreBanco, double impuesto5L, double impuesto20L){
        Banco nuevoBanco = new Banco(nombreBanco,impuesto5L,impuesto20L);
        listaBanco.agregarBanco(nuevoBanco);
        return true;
    }

    /**
     * Este método recibe [nombre, apellido, RUT, dirección, marca de agua, litro de agua, cantidad que compro y el total]
     * de la compra que realizo el cliente para crear la venta con el constructor de Venta para luego agregarlo a la lista de
     * ventas
     * @param nombreCliente
     * @param apellidoCliente
     * @param rutCliente
     * @param direccinCliente
     * @param marcaAgua
     * @param tipoAgua
     * @param cantidadCompradas
     * @param totalCompra
     * @return
     */
    public boolean agregarVenta (String nombreCliente, String apellidoCliente, String rutCliente, String direccinCliente, String marcaAgua, String tipoAgua, int cantidadCompradas, double totalCompra){

        Venta nuevaVenta = new Venta(nombreCliente,apellidoCliente,rutCliente,direccinCliente,marcaAgua,tipoAgua,cantidadCompradas,totalCompra);
        listaVentas.agregarVenta(nuevaVenta);

        if(tipoAgua.equalsIgnoreCase("20l")){
            int comprasActual = buscarRutCliete(rutCliente).getCompras20L();
            buscarRutCliete(rutCliente).setCompras20L(  comprasActual + cantidadCompradas );
        }

        if(tipoAgua.equalsIgnoreCase("5l")){
            int comprasActual = buscarRutCliete(rutCliente).getCompras5L();
            buscarRutCliete(rutCliente).setCompras5L(  comprasActual + cantidadCompradas );
        }

        int ventasActual = listaAgua.buscarAgua(marcaAgua).getVentas();
        listaAgua.buscarAgua(marcaAgua).setVentas(   ventasActual + cantidadCompradas   );

        return true;
    }


    /**
     * Este método crea una agua nueva preguntándole al usuario la marca, precio 5L precio 20L y
     * el impuesto del agua luego corroborara que el agua ya no esté registrada y que el impuesto no supere el 10%
     * si no arrojara el error correspondiente
     * @return
     */
    public boolean crearAgua(){

        System.out.print("ingresar marca: ");
        String aguaNombre = scan.next();
        System.out.print("ingresar precio 5L: ");

        String precio5LL = scan.next();
        if(validacionInt(precio5LL)==false){
            return false;
        }
        int precio5L = Integer.parseInt(precio5LL);


        System.out.print("ingresar precio 20L: ");
        String precio20LL = scan.next();
        if(validacionInt(precio20LL)==false){
            return false;
        }
        int precio20L = Integer.parseInt(precio5LL);


        System.out.print("ingresar impuesto (no superar 10%): ");
        String impuestoo = scan.next();

        if(validacionInt(impuestoo)==false){
            return false;
        }

        double impuesto = Double.parseDouble(impuestoo);


        if (impuesto < 10) {
            agregarAgua(aguaNombre, precio5L, precio20L, impuesto);
            desplegarAgua();
        } else {
            System.out.println("el impuesto no puede superar el 10%");
        }

        return false;
    }


    /**
     * Este método registra un nuevo usuario en el sistema preguntándole por teclado
     * el nombre, apellido, y el RUT; Luego corroborara que el RUT no esté registrado en
     * la lista de clientes, para luego proceder a agregar el cliente con ayuda del
     * método agregarClienteExistenteNoBanco(); ya que no se ingresara con banco
     * @return
     */
    public boolean crearUsuario(){
        System.out.print("Ingrese el nombre: ");
        String nombre = scan.next();
        System.out.print("Ingrese el apellido: ");
        String apellido = scan.next();
        System.out.print("Ingrese el rut: ");
        String rut = scan.next();

        if(buscarRutCliete(rut)!=null){
            System.out.println("El rut ya esta registrado");
            return false;
        }

        System.out.print("Ingrese la direccion: ");
        String direccion = scan.next();
        System.out.println("Ingrese la contraseña: ");
        String password = scan.next();

        agregarClienteExistenteNoBanco(nombre, apellido, rut, direccion, password);
        System.out.println("El usuario se a registrado con exito");

        return true;
    }


    /**
     * despliega las aguas registradas
     */
    public void desplegarAgua(){
        listaAgua.imprimirAguasVenta();
    }

    /**
     * despliega los bancos registrados
     */
    public void desplegarBanco(){
        listaBanco.imprimirBancos();
    }

    /**
     * le dara la lista de aguas
     * @return
     */
    public ListaAgua getListaAgua() {

        return listaAgua;
    }
}
