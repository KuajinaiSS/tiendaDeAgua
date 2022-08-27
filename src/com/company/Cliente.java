package com.company;

public class Cliente {

    private int compras20L;
    private int compras5L;

    private String nombre;
    private String apellido;
    private String rut;
    private String direccion;
    private String password;
    private Banco banco;
    private int pin;

    public Cliente(String nombre, String apellido, String rut, String direccion, String password, Banco banco, int pin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.direccion = direccion;
        this.password = password;

        this.compras20L =0;
        this.compras5L =0;

        this.banco = banco;
        this.pin = pin;
    }

    public Cliente(String nombre, String apellido, String rut, String direccion, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.direccion = direccion;
        this.password = password;

        this.compras20L =0;
        this.compras5L =0;
    }

    public int getCompras20L() {
        return compras20L;
    }

    public void setCompras20L(int compras20L) {
        this.compras20L = compras20L;
    }

    public int getCompras5L() {
        return compras5L;
    }

    public void setCompras5L(int compras5L) {
        this.compras5L = compras5L;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
