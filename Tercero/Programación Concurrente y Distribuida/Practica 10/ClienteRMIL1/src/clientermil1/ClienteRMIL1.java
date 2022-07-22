/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientermil1;
import IRemoto.IEjemplo;
import java.net.MalformedURLException;
import java.rmi.*;
/**
 *
 * @author Miguel Sánchez
 */
public class ClienteRMIL1 {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // TODO code application logic here
        
        IEjemplo objremoto = (IEjemplo) Naming.lookup("rmi://217.217.77.253:2021/ejemremoto");
        System.out.println("El contador remoto queda en " + objremoto.incrementa("Miguel Ángel", 7));
    }
}
