/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3_pototype;

import java.util.Scanner;

/**
 *
 * @author Miguel Sánchez
 */
public class Persona implements Prototipo {

    private String nombre, apellidos, telefono,
            direccion, cod_postal, ciudad, nivel_economico,
            correo, nivel_estudios;

    public String getNivel_estudios() {
        return nivel_estudios;
    }
    private int idfamilia;

    public Persona() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id de la familia: ");
        idfamilia = sc.nextInt();
        sc.nextLine();
        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Apellidos: ");
        apellidos = sc.nextLine();
        System.out.println("Telefono: ");
        telefono = sc.nextLine();
        System.out.println("Direccion: ");
        direccion = sc.nextLine();
        System.out.println("Codigo Postal: ");
        cod_postal = sc.nextLine();
        System.out.println("Ciudad: ");
        ciudad = sc.nextLine();
        System.out.println("Nivel económico (Bajo, Medio o Alto): ");
        nivel_economico = sc.nextLine();
        System.out.println("Correo: ");
        correo = sc.nextLine();
        System.out.println("Nivel de Estudios (Bajo, Medio o Alto): ");
        nivel_estudios = sc.nextLine();
    }

    public Persona(int idfamilia, String telefono, String direccion, String cod_postal, String ciudad, String nivel_economico) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Apellidos: ");
        apellidos = sc.nextLine();
        System.out.println("Correo: ");
        correo = sc.nextLine();
        System.out.println("Nivel de Estudios (Bajo, Medio o Alto): ");
        nivel_estudios = sc.nextLine();
        this.idfamilia = idfamilia;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cod_postal = cod_postal;
        this.ciudad = ciudad;
        this.nivel_economico = nivel_economico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    @Override
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public void setNivel_economico(String nivel_economico) {
        this.nivel_economico = nivel_economico;
    }

    @Override
    public void setIdfamilia(int idfamilia) {
        this.idfamilia = idfamilia;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public String getCod_postal() {
        return cod_postal;
    }

    @Override
    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String getNivel_economico() {
        return nivel_economico;
    }

    @Override
    public int getIdfamilia() {
        return idfamilia;
    }

    @Override
    public Prototipo clonar() {
        Prototipo persona = new Persona(idfamilia, telefono, direccion, cod_postal, ciudad, nivel_economico);
        return persona;
    }

}
