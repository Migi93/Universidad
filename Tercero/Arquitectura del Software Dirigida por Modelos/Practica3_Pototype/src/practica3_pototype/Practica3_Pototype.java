/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3_pototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Miguel Sánchez
 */
public class Practica3_Pototype {

    public static void main(String[] args) {
        int opcion;
        Scanner sc = new Scanner(System.in);
        ArrayList<List<Prototipo>> familias = new ArrayList<>();
        do {
            System.out.println("-----------MENU-----------");
            System.out.println("1- Nueva Persona.");
            System.out.println("2- Crear un solo familiar.");
            System.out.println("3- Mostrar Familias.");
            System.out.println("4- Salir.\n");
            System.out.println("Introduzca una opcion a realizar: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Persona tempo;
                    ArrayList<Prototipo> personas = new ArrayList<>();
                    Prototipo persona_nueva = new Persona();
                    int idfamilia;
                    tempo = (Persona) persona_nueva;
                    idfamilia = tempo.getIdfamilia();
                    boolean encontrado = false;
                    int k = 0;
                    while (k < familias.size() && encontrado == false) {
                        if (idfamilia == familias.get(k).get(0).getIdfamilia()) {
                            encontrado = true;
                        }
                        k++;
                    }
                    if (encontrado == true) {
                        System.out.println("Ya existe una familia con ese ID. No se ha podido crear la nueva persona.\n");
                    } else {
                        personas.add(persona_nueva);
                        familias.add(personas);
                    }
                    break;
                case 2:
                    Prototipo pro;
                    Prototipo nuevo_familiar;
                    int indice;
                    if (familias.size() == 0) {
                        System.out.println("\n****No existe ninguna familia aun creada****.\n");
                    } else {
                        for (int i = 0; i < familias.size(); i++) {
                            pro = familias.get(i).get(0);
                            System.out.println((i+1) + "- Familia con ID: " + pro.getIdfamilia());
                        }
                        System.out.println("\nIntroduce el indice de la familia para introducir el nuevo miembro: ");
                        indice = sc.nextInt() - 1;
                        nuevo_familiar = familias.get(indice).get(0).clonar();
                        familias.get(indice).add(nuevo_familiar);
                    }
                    break;
                case 3:
                    Prototipo temporal;
                    Persona temp;
                    if (familias.size() == 0) {
                        System.out.println("\n****No existe ninguna familia aun creada****.\n");
                    } else {
                        for (int i = 0; i < familias.size(); i++) {
                            temporal = familias.get(i).get(0);
                            System.out.println("Familia con ID: " + temporal.getIdfamilia());
                            for (int j = 0; j < familias.get(i).size(); j++) {
                                temp = (Persona) familias.get(i).get(j);
                                System.out.println("-Nombre: " + temp.getNombre() + " -Apellidos: " + temp.getApellido() + " -Ciudad: " + temp.getCiudad() + " -CP: " + temp.getCod_postal() + " -Direccion: " + temp.getDireccion() + " -Tlf: " + temp.getTelefono()
                                + " -Nivel Economico: " + temp.getNivel_economico() + " -Nivel de Estudios: " + temp.getNivel_estudios());
                            }
                            System.out.println("------------------------------------------------------------------");
                        }
                    }
                    break;
                default:
                    System.out.println("\n**********Seleccione una opcion correcta.**********\n");
            }
        } while (opcion != 4);
        System.out.println("\n¡HASTA PRONTO!\n");
    }

}
