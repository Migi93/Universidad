#include "cola.h"
#include <cstring>
#define INCREMENTO 5

cola::cola(){
    elementos=new cliente[INCREMENTO];
    if(elementos!=NULL){
        ne=fin=inicio=0;
        Tama=INCREMENTO;
    } else {
        ne=fin=inicio=-1;
        Tama=-1;
    }
}

cola::~cola(){
    if(elementos!=NULL){
        delete [] elementos;
    }
    elementos=NULL;
    ne=fin=inicio=-1;
    Tama=0;
}

void cola::encolar(cliente e){
    if(ne==Tama){
        cliente *aux=new cliente[Tama+INCREMENTO];;
        if(aux!=NULL){
            for(int i=0;i<ne;i++){
                aux[i]=elementos[inicio];
                inicio++;
                if(inicio==Tama){
                    inicio=0;
                }
            }
            inicio=0;
            fin=ne;
            Tama+=INCREMENTO;
            delete [] elementos;
            elementos=aux;
        }
    }
    if(ne<Tama){
        elementos[fin]=e;
        fin=(fin+1)%Tama;
        ne++;
    }
}

void cola::desencolar(){
    inicio++;
    if(inicio==Tama){
        inicio=0;
    }
    ne--;
    if((Tama-ne>=INCREMENTO)&&(Tama>INCREMENTO)){
        cliente *aux=new cliente[Tama-INCREMENTO];
        if(aux!=NULL){
            for(int i=0;i<ne;i++){
                aux[i]=elementos[inicio++];
                if(inicio==Tama){
                    inicio=0;
                }
            }
            Tama-=INCREMENTO;
            inicio=0;
            delete [] elementos;
            elementos=aux;
        }
    }
}

bool cola::esvacia(){
    if(ne==0){
        return true;
    } else {
        return false;
    }
}

cliente cola::primero(){
    return elementos[inicio];
}

int cola::longitud(){
    return ne;
}

