#ifndef CONTRATO_H
#define CONTRATO_H

#include "Fecha.h"

class Contrato
{
    static int contador;
    const int idContrato;
    long int dniContrato;
    Fecha fechaContrato;
    public:
        Contrato(long int dni, Fecha f);
        Contrato(const Contrato& c);//Hay que implementar el constructor de copia a pesar de que no hay memoria dinamica debido a que si igualamos dos objetos se copiaria
                                    //absolutamente todo y no podemos tener dos contratros con el mismo id de contrato
        virtual ~Contrato();
        //Contrato& operator=(const Contrato& c); //no es necesario y ademas no puede ser usado porque Contrato tiene un
                                          //atributo constante idContrato que no puede modificarse
                                          //no se puede usar el = en el main con objetos Contrato
        int getIdContrato() const { return this->idContrato; };
        long int getDniContrato() const { return this->dniContrato; };
        Fecha getFechaContrato() const { return this->fechaContrato; };
        void setFechaContrato(Fecha f) { this->fechaContrato=f; };
        void setDniContrato(long int dni) { this->dniContrato=dni; };
        virtual void ver() const;
        virtual void nada() const =0;//para indicar que el metodo no se va a implementar y que esta clase sea una clase abstracta.
                                     //hay que poner este mismo metodo en todas sus clases hijas pero implementando el metodo poniendo cualquier cosa sin importancia
};

ostream& operator<<(ostream &s, const Contrato &c);

#endif // CONTRATO_H
