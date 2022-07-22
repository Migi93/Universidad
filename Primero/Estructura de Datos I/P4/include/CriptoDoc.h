#ifndef CRIPTODOC_H
#define CRIPTODOC_H
#include "Lista.h"
#include<cstdlib>
typedef char cadena[30];


class CriptoDoc
{
    private:
        Lista texto;

    public:
        bool leer(char fichero[]);
        bool escribir(char fichero[]);
        void cifrar(int codigo);
        void descifrar(int codigo);
        void vaciar();
        void concatenar(CriptoDoc &doc);
        int numlineas();
        linea observar(int i);
        bool esDocumentoCifrado(CriptoDoc &doc);

};

#endif // CRIPTODOC_H
