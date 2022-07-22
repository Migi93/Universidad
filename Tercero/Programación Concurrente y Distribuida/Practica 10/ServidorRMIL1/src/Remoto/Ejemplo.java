/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remoto;

import IRemoto.IEjemplo;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
/**
 *
 * @author Miguel Sánchez
 */
public class Ejemplo extends UnicastRemoteObject implements IEjemplo{

    private volatile int contador;      

    public Ejemplo(int inicial) throws RemoteException{
        super();
        contador = inicial;
    }

    @Override
    public int incrementa(String quien, int valor) throws RemoteException{
        contador = contador + valor;
        System.out.println(quien + " ha incrementado en " + valor + " y vale " + contador + " desde " + Thread.currentThread().getName());
        return contador;
    }

}
