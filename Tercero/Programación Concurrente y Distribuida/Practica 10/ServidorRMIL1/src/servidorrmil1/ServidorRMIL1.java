/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmil1;

import Remoto.Ejemplo;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.registry.*;


/**
 *
 * @author Miguel Sánchez
 */
public class ServidorRMIL1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, IOException{
        // TODO code application logic here
        Ejemplo ejem = new Ejemplo(0);
        
        java.rmi.registry.Registry registro = LocateRegistry.createRegistry(2021);
        registro.rebind("ejeremoto", ejem);

        System.out.println("El contador vale " + ejem.incrementa("main", 4));
        System.out.println("El contador vale " + ejem.incrementa("la main", 4));
        
        System.out.println("Servidor funcionando, pulsa intro para finalizar .......");
        System.in.read();
        System.exit(0);

    }

}
