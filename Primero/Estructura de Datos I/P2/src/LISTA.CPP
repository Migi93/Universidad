#include "lista.h"
#include <cstring>
#include <iostream>
#define INCREMENTO 5

void copiarpeluquero(peluquero &destino, peluquero &origen){
    strcpy(destino.Apellidos,origen.Apellidos);
    strcpy(destino.Nombre,origen.Nombre);
    destino.Codigo=origen.Codigo;
    destino.TipoServicio=origen.TipoServicio;
    while(!destino.Col.esvacia()){
        destino.Col.desencolar();
    }
    for(int i=0;i<origen.Col.longitud();i++){
        destino.Col.encolar(origen.Col.primero());
        origen.Col.encolar(origen.Col.primero());
        origen.Col.desencolar();
    }
}

lista::lista(){
    elementos=new peluquero[INCREMENTO];
    if(elementos!=NULL){
        Tama=INCREMENTO;
        n=0;
    } else {
        Tama=n=-1;
    }
}

lista::~lista(){
    if(elementos!=NULL){
        delete [] elementos;
    }
    elementos=NULL;
    Tama=n=0;
}

lista::lista(peluquero &e){
    elementos=new peluquero[INCREMENTO];
    if(elementos!=NULL){
        Tama=INCREMENTO;
        n=1;
        elementos[0]=e;
    } else {
        Tama=n=-1;
    }
}

bool lista::esvacia(){
    if(n==0){
        return true;
    } else {
        return false;
    }
}

int lista::longitud(){
    return n;
}
        //void anadirIzq(peluquero e); No necesario implementar
        //void anadirDch(peluquero e);No necesario implementar
        //void eliminarIzq();No necesario implementar
        //void eliminarDch();No necesario implementar
        //peluquero observarIzq();No necesario implementar
        //peluquero observarDch();No necesario implementar
        //void concatenar(lista l); No necesario implementar
bool lista::pertenece(peluquero &e){
    if(posicion(e)==-1){
        return false;
    }
    else{
        return true;
    }
}

void lista::insertar(int i, peluquero &e){
    int pos;
    if(n==Tama){
        peluquero *aux=new peluquero[Tama+INCREMENTO];
        if(elementos!=NULL){
            for(int i=0;i<n;i++){
                copiarpeluquero(aux[i],elementos[i]);
            }
            Tama+=INCREMENTO;
            delete [] elementos;
            elementos=aux;
        }
    }
    if(n<Tama){
        for(pos=n-1;pos>=i-1;pos--){
            copiarpeluquero(elementos[pos+1],elementos[pos]);
        }
        copiarpeluquero(elementos[i-1],e);
        n++;
    }
}

void lista::eliminar(int i){
    while(i<n){
        copiarpeluquero(elementos[i-1],elementos[i]);
        i++;
    }
    n--;
    if(Tama-n>=INCREMENTO && Tama>INCREMENTO){
        peluquero *aux=new peluquero[Tama+INCREMENTO];
        if(elementos!=NULL){
            Tama-=INCREMENTO;
            for(int i=0;i<Tama;i++){
                copiarpeluquero(aux[i],elementos[i]);
                delete [] elementos;
                elementos=aux;
            }
        }
    }
}

void lista::modificar(int i, peluquero &e){
    copiarpeluquero(elementos[i-1],e);
}

peluquero &lista::observar(int i){
    return elementos[i-1];
}

int lista::posicion(peluquero &e){
    int i=0;
    while((elementos[i].Codigo!=e.Codigo)&&(i<n)){
        i++;
    }
    if(elementos[i].Codigo==e.Codigo){
        return(i+1);
    } else {
        return -1;
    }
}

