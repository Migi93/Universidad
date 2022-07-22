#ifndef COLA_H
#define COLA_H
#include <fstream>
#include <string>
#define INCREMENTO 4

using namespace std;


typedef std::string linea;

class Cola
{
    private:
     linea *elementos; //elementos de la cola
     int inicio, fin; //principio y fin de la cola
     int Tama; //Capacidad de la tabla
     int ne; //Nº de elementos

     public:
     Cola(); // constructor de la clase
     ~Cola(); // destructor de la clase
     void encolar(linea e);
     void desencolar();
     bool esvacia();
     linea primero() ;
     int longitud();
};

#endif // COLA_H
