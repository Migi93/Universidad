#ifndef CONTRATOMOVIL_H
#define CONTRATOMOVIL_H

#include "Contrato.h"


class ContratoMovil : public Contrato
{
    float precioMinuto;
    int minutosHablados;
    char *nacionalidad;
    public:
        ContratoMovil(long int dni, Fecha f, float p, int m, char *nac);
        ~ContratoMovil();
        ContratoMovil(const ContratoMovil& c);
        ContratoMovil& operator=(const ContratoMovil& c);
        float getPrecioMinuto() const { return this->precioMinuto; }
        int getMinutosHablados() const { return this->minutosHablados; }
        const char* getNacionalidad() const { return this->nacionalidad; }//const delante para que no se modifique el char
        void setNacionalidad(char *nac);
        void setPrecioMinuto(float p);
        void setMinutosHablados(int m);
        void ver() const;
        float factura() const;
        virtual void nada() const {;}; //Al declarar la clase contrato como abstracta hay que implementar estos metodos en las clases que deriven de contrato(la clases hijas)
};

ostream& operator<<(ostream &s, const ContratoMovil &c);

#endif // CONTRATOMOVIL_H
