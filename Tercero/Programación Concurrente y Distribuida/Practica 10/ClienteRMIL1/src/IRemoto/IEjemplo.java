/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IRemoto;
import java.rmi.*;                                                                    

/**
 *
 * @author Miguel Sánchez
 */
public interface IEjemplo extends Remote{
    
    public int incrementa(String quien, int valor);
    
}
