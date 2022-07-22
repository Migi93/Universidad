package practica.pkg2.servidor;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Practica2Servidor {

    public static void main(String[] args) throws RemoteException, InterruptedException {
        try {
            Scanner sc = new Scanner(System.in);
            GestorAlmacenes ig = new GestorAlmacenes();
            int puerto = 1999;
            Registry registry = LocateRegistry.createRegistry(puerto);
            registry.rebind("Almacen", ig);
            //Naming.rebind("rmi://localhost:" + puerto + "/Almacen", ig);
            System.out.println("Servidor Almacen esperando peticiones ... ");
            //Naming.unbind("rmi://localhost:" + puerto + "/Almacen");
            System.out.println("Pulsa enter para desregistrar...");
            sc.nextLine();
            registry.unbind("Almacen");
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error en el servidor Almacen" + e.getMessage());
        }
    }
}
