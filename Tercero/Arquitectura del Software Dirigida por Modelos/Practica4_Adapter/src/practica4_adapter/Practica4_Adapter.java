/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4_adapter;

import java.util.Scanner;

/**
 *
 * @author Miguel Sánchez
 */
public class Practica4_Adapter {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String s;
        while (opcion != 6) {
            System.out.println("-----------MENU-----------");
            System.out.println("1- Coche Manual.");
            System.out.println("2- Coche Automatico.");
            System.out.println("3- Adaptador Coche Manual.");
            System.out.println("4- Barco");
            System.out.println("5- Adaptador Barco.");
            System.out.println("6- Salir.\n");
            System.out.println("Introduzca una opcion a realizar: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    CocheManual cm = new CocheManual();
                    System.out.println("Se ha creado un Coche Manual, pulsa enter para continuar ...");
                    sc.nextLine();
                    sc.nextLine();
                    int elegir = 0;
                    while (elegir != 5) {
                        System.out.println("***MENU COCHE MANUAL***");
                        System.out.println("1- Subir Marcha.");
                        System.out.println("2- Acelerar.");
                        System.out.println("3- Bajar Marcha.");
                        System.out.println("4- Frenar.");
                        System.out.println("5- Salir.");
                        System.out.println("Introduzca una opcion a realizar: ");
                        elegir = sc.nextInt();
                        sc.nextLine();
                        switch (elegir) {
                            case 1:
                                cm.SubeMarcha();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 2:
                                cm.Acelera();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 3:
                                cm.BajaMarcha();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 4:
                                cm.Frena();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                        }
                    }
                    break;
                case 2:
                    CocheAutomatico imbarco = new CocheAutomatico();
                    System.out.println("Se ha creado un Adaptador de Coche Manual, pulsa enter para continuar ...");
                    sc.nextLine();
                    sc.nextLine();
                    int elige = 0;
                    while (elige != 3) {
                        System.out.println("***MENU COCHE ADAPTADOR MANUAL***");
                        System.out.println("1- Acelerar.");
                        System.out.println("2- Frenar.");
                        System.out.println("3- Salir.");
                        System.out.println("Introduzca una opcion a realizar: ");
                        elige = sc.nextInt();
                        sc.nextLine();
                        switch (elige) {
                            case 1:
                                imbarco.Acelera();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 2:
                                imbarco.Frena();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                        }
                    }
                    break;

                case 3:
                    IMandos im = new AdaptadorCocheManual();
                    System.out.println("Se ha creado un Adaptador de Coche Manual, pulsa enter para continuar ...");
                    sc.nextLine();
                    sc.nextLine();
                    int escoge = 0;
                    while (escoge != 3) {
                        System.out.println("***MENU ADAPATADOR COCHE MANUAL***");
                        System.out.println("1- Acelerar.");
                        System.out.println("2- Frenar.");
                        System.out.println("3- Salir.");
                        System.out.println("Introduzca una opcion a realizar: ");
                        escoge = sc.nextInt();
                        sc.nextLine();
                        switch (escoge) {
                            case 1:
                                im.Acelera();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 2:
                                im.Frena();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                        }
                    }
                    break;
                case 4:
                    Barco b = new Barco();
                    System.out.println("Se ha creado un Barco, pulsa enter para continuar ...");
                    sc.nextLine();
                    sc.nextLine();
                    int opc = 0;
                    while (opc != 6) {
                        System.out.println("***MENU BARCO***");
                        System.out.println("1- Conectar Grifo.");
                        System.out.println("2- Activar Grifo.");
                        System.out.println("3- Acelerar.");
                        System.out.println("4- Frenar.");
                        System.out.println("5- Desconectar.");
                        System.out.println("6- Salir.");
                        System.out.println("Introduzca una opcion a realizar: ");
                        opc = sc.nextInt();
                        sc.nextLine();
                        switch (opc) {
                            case 1:
                                b.Conectar();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 2:
                                b.Activar();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 3:
                                b.MoverMasRapido();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 4:
                                b.Detener();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 5:
                                b.Desconectar();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                        }
                    }
                    break;
                case 5:
                    IMandos imb = new AdaptadorBarco();
                    System.out.println("Se ha creado un Adaptador de Barco, pulsa enter para continuar ...");
                    sc.nextLine();
                    sc.nextLine();
                    int elg = 0;
                    while (elg != 3) {
                        System.out.println("***MENU COCHE ADAPTADOR BARCO***");
                        System.out.println("1- Acelerar.");
                        System.out.println("2- Frenar.");
                        System.out.println("3- Salir.");
                        System.out.println("Introduzca una opcion a realizar: ");
                        elg = sc.nextInt();
                        sc.nextLine();
                        switch (elg) {
                            case 1:
                                imb.Acelera();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                            case 2:
                                imb.Frena();
                                Thread.sleep(2000);
                                System.out.println("\n");
                                break;
                        }
                    }
                    break;
                default:
                    if (opcion > 6) {
                        System.out.println("\n**********Seleccione una opcion correcta.**********\n");
                        Thread.sleep(2500);
                    }
                    break;
            }
        }
        System.out.println("\n¡HASTA PRONTO!\n");
    }
}
