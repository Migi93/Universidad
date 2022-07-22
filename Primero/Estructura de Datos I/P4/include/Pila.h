#ifndef PILA_H
#define PILA_H

#include <iostream>

using namespace std;

struct TNodo_Pila
{
 int Datos; // Dato a almacenar en cada nodo
 TNodo_Pila *Siguiente; // Puntero al siguiente nodo
};

class Pila {

    private:
        TNodo_Pila *elementos; // elementos de la pila
        int n; // nº de nodos que tiene la lista

    public:
        Pila(); //constructor de la clase
        ~Pila(); // destructor de la clase
        void apilar(int e);
        void desapilar();
        bool esvacia();
        int cima() ;
        int longitud();
};

#endif // PILA_H
