#include "ContratoMovil.h"
#include <cstring>
#include <iostream>

using namespace std;

ContratoMovil::ContratoMovil(long int dni, Fecha f, float p, int m, char *nac):Contrato(dni,f){
    this->precioMinuto=p;
    this->minutosHablados=m;
    this->nacionalidad=new char[strlen(nac)+1];
    strcpy(this->nacionalidad,nac);
}

ContratoMovil::~ContratoMovil(){
    delete [] this->nacionalidad;
}

ContratoMovil::ContratoMovil(const ContratoMovil& c):Contrato(c){
    this->precioMinuto=c.precioMinuto;
    this->minutosHablados=c.minutosHablados;
    this->nacionalidad=new char[strlen(c.nacionalidad)+1];
    strcpy(this->nacionalidad,c.nacionalidad);
}

ContratoMovil& ContratoMovil::operator=(const ContratoMovil& c){
    if(this != &c){
        delete [] this->nacionalidad;
        this->precioMinuto=c.precioMinuto;
        this->minutosHablados=c.minutosHablados;
        strcpy(this->nacionalidad,c.nacionalidad);
    }
    return *this;
}

void ContratoMovil::setNacionalidad(char *nac){
    delete [] this->nacionalidad;
    this->nacionalidad=new char[strlen(nac)+1];
    strcpy(this->nacionalidad,nac);
}

void ContratoMovil::setPrecioMinuto(float p){
    this->precioMinuto=p;
}

void ContratoMovil::setMinutosHablados(int m){
    this->minutosHablados=m;
}

float ContratoMovil::factura() const{
    float precio=0;
        precio=(getPrecioMinuto()*getMinutosHablados());
    return precio;
}

void ContratoMovil::ver() const{
    Contrato::ver();
    cout << this->getMinutosHablados() << "m, " << this->getNacionalidad() << " "
         << this->getPrecioMinuto() << " - " << this->factura() << (char)(199);
}

ostream& operator<<(ostream &s, const ContratoMovil &c){
    s << c.getDniContrato() << " (" << c.getIdContrato() << " - " << c.getFechaContrato() << ")"
      << c.getMinutosHablados() << "m, " << c.getNacionalidad() << c.getPrecioMinuto()
      << " - " << c.factura() << "€";
    return s;
}
