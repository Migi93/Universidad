#include "Contrato.h"
#include <iostream>

using namespace std;

int Contrato::contador=1;
Contrato::Contrato(long int dni, Fecha f):fechaContrato(f),idContrato(contador){
    Contrato::contador++;
    this->dniContrato=dni;
}

Contrato::Contrato(const Contrato& c):fechaContrato(c.fechaContrato),idContrato(contador){//Hay que implementar el constructor de copia a pesar de que no hay memoria dinamica debido a que si igualamos dos objetos se copiaria absolutamente todo y no podemos tener dos contratros con el mismo id de contrato
    Contrato::contador++;
    this->dniContrato=c.dniContrato;
}

void Contrato::ver() const{
    cout << this->dniContrato << " (" << this->idContrato << " - ";
    fechaContrato.ver(); cout << ") ";
}

Contrato::~Contrato(){
    //dtor
}

ostream& operator<<(ostream &s, const Contrato &c){
    s << c.getDniContrato() << " (" << c.getIdContrato() << " - "
      << c.getFechaContrato() << ")";
    return s;
}

