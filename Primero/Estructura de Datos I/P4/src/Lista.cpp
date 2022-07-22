#include "Lista.h"
#include <iostream>
#define INCREMENTO 5

using namespace std;

    Lista::Lista(){
        elementos = new linea[INCREMENTO];
        if(elementos != NULL){
            Tama = INCREMENTO;
            n = 0;
        } else {
            Tama = n = -1;
        }
    }

    Lista::Lista(linea &l){
        elementos = new linea[INCREMENTO];
        if (elementos != NULL) {
            Tama = INCREMENTO;
            n = 1;
            elementos[0]=l;
        }
        else {
            Tama = n = -1;
        }
    }

    Lista::~Lista(){
        if(elementos != NULL){
            delete [] elementos;
        }
        elementos = NULL;
        Tama = n = 0;
    }

    bool Lista::esvacia(){
        if(n == 0){
            return true;
        } else {
            return false;
        }
    }

    int Lista::longitud(){
        return n;
    }

    void Lista::anadirIzq(linea l){
        insertar(1, l);
    }

    void Lista::anadirDch(linea l){
        insertar(n+1, l);
    }

    void Lista::eliminarIzq() {
        eliminar(1);
    }

    void Lista::eliminarDch() {
      eliminar(n);
    }

    linea Lista::observarIzq() {
      return(observar(1));
    }

    linea Lista::observarDch() {
      return(observar(n));
    }

    void Lista::concatenar(Lista l)  {
      int lon = l.longitud();
      for (int i=1; i<=lon; i++)
        insertar(n+1, l.observar(i));
    }

    bool Lista::pertenece(linea &l){
        if(posicion(l) == -1){
            return false;
        }
        else {
            return true;
        }
    }

    void Lista::insertar(int i, linea l){
        int pos;
        if(n == Tama){
            linea *aux = new linea[Tama + INCREMENTO];
            if(elementos != NULL){
                for(int i=0; i < n; i++){
                    aux[i] = elementos[i];
                }
                Tama += INCREMENTO;
                delete [] elementos;
                elementos = aux;
            }
        }
        if( n < Tama){
            for(pos = n-1; pos >= i-1; pos--){
                elementos[pos + 1] = elementos[pos];
        }
        elementos[i-1] = l;
        n++;
        }
    }

    void Lista::eliminar(int i){
        while(i < n){
        elementos[i-1] = elementos[i];
        i++;
        }
        n--;
        if(Tama-n >= INCREMENTO && Tama > INCREMENTO){
            linea *elementosTMP = new linea[Tama-INCREMENTO];
            if(elementosTMP != NULL){
                for(int pos = 0; pos < Tama-INCREMENTO; pos++)
                    elementosTMP[pos] = elementos[pos];
                Tama -= INCREMENTO;
                delete [] elementos;
                elementos = elementosTMP;
            }
        }
    }

    void Lista::modificar(int i, linea &l){
        elementos[i-1] = l;
    }

    linea Lista::observar(int i){
        return elementos[i-1];
    }

    int Lista::posicion(linea &l){
        int i=0;
        while((elementos[i] != l) && (i<n)){
            i++;
        }
        if(elementos[i] == l){
            return(i+1);
        } else {
            return -1;
        }
    }

