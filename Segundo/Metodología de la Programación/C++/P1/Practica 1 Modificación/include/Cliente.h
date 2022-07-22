#ifndef CLIENTE_H
#define CLIENTE_H

#include <iostream>
#include "Fecha.h"

using namespace std;

class Cliente{
    long int dni;
    char *nombre;
    Fecha fechaAlta;
public:
    Cliente(long int d, char *nom, Fecha f);
    //
    ~Cliente();
    Cliente& operator=(const Cliente& c);
    long int getDni() const { return this->dni; }
    const char* getNombre() const { return this->nombre; } //IMPORTANTE devolver un puntero constante //const char* para evitar que desde el main() se puede modificar el nombre
    Fecha getFecha() const { return this->fechaAlta; }
    void setNombre(char *nom);
    void setFecha(Fecha f);
    bool operator==(Cliente &c) const;
};

ostream& operator<<(ostream &s, const Cliente &c); //funcion no amiga de la clase

#endif // CLIENTE_H
