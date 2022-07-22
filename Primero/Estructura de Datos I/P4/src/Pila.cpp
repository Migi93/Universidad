#include "Pila.h"

    Pila::Pila(){
        elementos=NULL;
        n=0;
    }

    Pila::~Pila(){
        TNodo_Pila *Nodo_Borr;
        while (elementos != NULL) {
        Nodo_Borr = elementos;
        elementos = Nodo_Borr -> Siguiente;
        delete Nodo_Borr;
        }
        n = 0;
    }

    void Pila::apilar(int e){
        TNodo_Pila *Nodo_Aux = new TNodo_Pila;
        if (Nodo_Aux != NULL) {
        Nodo_Aux -> Datos = e;
        Nodo_Aux -> Siguiente = elementos;
        elementos = Nodo_Aux;
        n++;
 }
    }

    void Pila::desapilar(){
        TNodo_Pila *Nodo_Aux = elementos;
        elementos = Nodo_Aux -> Siguiente;
        delete Nodo_Aux;
        n--;
    }

    bool Pila::esvacia(){
        return elementos == NULL;
    }

    int Pila::cima(){
        return elementos -> Datos;
    }

    int Pila::longitud(){
        return n;
    }

