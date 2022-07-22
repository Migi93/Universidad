/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Miguel Sánchez
 */
public class Practica1_Singleton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        boolean salir = false;
        ArrayList<Usuario> usucreados = new ArrayList<>();
        Agencia agencia = new Agencia();
        while (salir == false) {
            System.out.println("-------------------------------------");
            System.out.println("\n---------MENU---------");
            System.out.println("1. Crear Usuarios.");
            System.out.println("2. Comprar Billetes.");
            System.out.println("3. Devolver Billetes");
            System.out.println("4. Salir \n");
            System.out.println("-------------------------------------");
            System.out.print("Elige una opcion a realizar: ");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    if (usucreados.isEmpty() == true) {
                        int i;
                        System.out.print("\n¿Cuantos usuarios quieres crear? ");
                        int usuarios = sc.nextInt();
                        sc.nextLine();
                        for (i = 0; i < usuarios; i++) {
                            usucreados.add(new Usuario(i));
                        }
                        System.out.println("\n->Se han creado correctamente los " + i + " usuarios.");
                    } else {
                        System.out.println("\n***ERROR*** -> Ya se han creado los usuarios para el vuelo, no se pueden crear de nuevo.");
                    }
                    System.out.println("\nPresiona una tacla para continuar...");
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("\nIntroduce el numero del usuario: ");
                    int dni = sc.nextInt();
                    sc.nextLine();
                    Usuario u = usucreados.get(dni);
                    System.out.print("\n¿Cuantos billetes desea comprar? ");
                    int compro = sc.nextInt();
                    sc.nextLine();
                    List<Billete> comprado = u.QuieroComprarBilletes(compro);
                    System.out.println("\n->Billetes que ha comprado el usuario " + dni + ":");
                    for (int i = 0; i < comprado.size(); i++) {
                        System.out.println("Asiento: " + comprado.get(i).getAsiento());
                        System.out.println("Codigo del ticket: " + comprado.get(i).getCodigo());
                    }
                    System.out.println("\nPresiona una tacla para continuar...");
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("\nIntroduce el numero del usuario: ");
                    int nif = sc.nextInt();
                    sc.nextLine();
                    Usuario us = usucreados.get(nif);
                    System.out.print("\n¿Cuantos billetes desea devolver? ");
                    int devuelvo = sc.nextInt();
                    sc.nextLine();
                    List<Billete> devuelto = agencia.QuieroDevolverBilletes(devuelvo, us);
                    if (devuelto.isEmpty() == true) {
                        System.out.println("\n->Este usuario no ha comprado ningun asiento.");
                    } else {
                        System.out.println("\n->Billetes que ha devuelto el usuario " + nif + ":");
                        for (int i = 0; i < devuelto.size(); i++) {
                            System.out.println("Asiento: " + devuelto.get(i).getAsiento());
                            System.out.println("Codigo del ticket: " + devuelto.get(i).getCodigo());
                        }
                    }
                    System.out.println("\nPresiona una tacla para continuar...");
                    sc.nextLine();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("\n****Opcion no valida****\n");

                    break;
            }
        }
        System.out.println("\n --------Hasta pronto-------- \n");
    }
}
