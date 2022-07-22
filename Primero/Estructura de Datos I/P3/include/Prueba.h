#ifndef PRUEBA_H
#define PRUEBA_H

#include "Clasificacion.h"
#include <iostream>
#include <fstream>

const int TAM_CADENA = 30;

typedef char cadena[TAM_CADENA];

using namespace std;

    struct Ciclista {
    int dorsal;
    cadena pais;
    cadena nombre;
    cadena apellidos;
    int marca;
    int posicion;
    };

class Prueba
{
    fstream fich; //fichero primera fase
    fstream fichero; //fichero segunda fase
    int numCiclistas;

    public:
         Prueba(char FicheroOrigen[],char FicheroDestino[]);
         ~Prueba();
         int getNumCiclistas();
         void mostrar(cadena pais);
         Ciclista consultar(int posicion);
         int buscar(int dorsal);
         void insertar(Ciclista c);
         void modificar(Ciclista c, int posicion);
         void eliminar(int posicion);
         void mostrarClasificacion();
};

#endif // PRUEBA_H

