/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1;

/**
 *
 * @author Miguel Sánchez
 */
public interface ICola {
    /**
     * Metodo que te da el numero de elementos que hay actualmente en la cola
     * @return Te devuelve el numero de elementos introducidos en la cola
     */
    public int GetNum();
    
    /**
     * Metodo que introduce un elemento en la cola
     * @param obj Elemento que se va a introducir en la cola
     * @throws Exception Salta un error cuando la cola esta en su limite maximo
     */
    public void Acola(Object obj) throws Exception;
    
    /**
     * Metodo que quita un elemento de la cola
     * @return Te devuelve el elemento que se ha quitado de la cola
     * @throws Exception salta un error cuando no exista ningun elemento en la cola
     */
    public Object Desacola()throws Exception;
    
    /**
     * Metodo que te devuelve el primer elemento de la cola
     * @return Te devuelve el primero de la cola
     * @throws Exception salta un error en el caso de que la cola este vacia
     */
    public Object Primero() throws Exception;
}
