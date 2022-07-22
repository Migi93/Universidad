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
public class ColaLenta implements ICola{
    
    private int head;//Posicion del array del cual se extrae el elemento
    private int tail;//Posicion en la que se va a introducir el elemento
    private final int capacidad;//Numero de elementos que caben en el array
    private int numelementos;//Numero de elementos que hay introducidos en el array
    private final Object datos[];//array para los elementos
    
    /**
     * Constructor de la clase Cola, estable el tamaño maximo de la cola
     * @param capacidad tamaño maximo de la cola
     */
    public ColaLenta(int capacidad){
        this.capacidad = capacidad;
        datos = new Object[this.capacidad];
        this.numelementos = 0;
        this.head = 0;
        this.tail = 0;
    }
    
    @Override
    public int GetNum(){
        return this.numelementos;
    }
    
    @Override
    public synchronized void Acola(Object obj) throws Exception{
        if(colallena() == true){
            throw new Exception("La cola esta llena, imposible insertar un elemento");
        }
            else{
                datos[this.tail] = obj;
                Thread.sleep(100);
                this.numelementos++;
                Thread.sleep(100);
                this.tail++;
                if(this.capacidad == this.tail){
                    this.tail = 0;
                }
            }
    }
    
    @Override
    public synchronized Object Desacola() throws Exception{
        if(colavacia() == true){
            throw new Exception("La cola esta vacia, imposible eliminar elementos");
        }
            else{
                Object obj = datos[this.head];
                Thread.sleep(100);
                datos[this.head] = null;
                Thread.sleep(100);
                this.numelementos--;
                Thread.sleep(100);
                this.head++;
                if(this.head == this.capacidad){
                    this.head = 0;
                }
                return obj;
            }
    }
    
    @Override
    public Object Primero() throws Exception{
        if(colavacia() == true){
            throw new Exception("La cola esta vacia, imposible devolver algun elemento");
        }
            else{
                return datos[this.head];               
            }
    }
    
    /**
     * Metodo que devuelve un true cuando la cola esta vacia
     * @return te devuelve un boolean en funcion del estado de la cola
     */
    public boolean colavacia(){
        if(GetNum() == 0){
            return true;
        }
            else{
                return false;
            }
    }
    
    /**
     * Metodo que devuelve un true cuando la cola esta llena
     * @return te devuelve un boolean en funcion del estado de la cola
     */
    public boolean colallena(){
        if(GetNum() == this.capacidad){
            return true;
        }
            else{
                return false;
            }
    }
    
    @Override
    public String toString(){
        String cola = "";
        for(int i = 0; i < this.capacidad; i++){
            if(datos[i] != null){
            cola = cola + datos[i].toString() + "-";
            }
        }
        return cola;
    }
}
