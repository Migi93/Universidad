#ifndef PELUQUERIA_H
#define PELUQUERIA_H
#include <lista.h>
#include <cola.h>
#include <cstring>
#include <fstream>
#include <iostream>

struct peluquerof
{
    int Codigo;
    cadena Apellidos;
    cadena Nombre;
    int TipoServicio;
};

class peluqueria
{
    private:
    lista L; //lista de los peluqueros que están atendiendo a los clientes

    public:
    void AbrirPeluqueria(char *nombrefichero);
    void IncorporarPeluquero(peluquero t);
    bool RetirarPeluquero(int codigo);
    bool EliminarCliente(cadena Nombre, cadena Apellidos);
    bool IncorporarCliente(cliente cli);
    void Mostrar();
    bool AtenderCliente(int CodigoPeluquero);
    void VolcarPeluqueria(char *nombrefichero);
    void ejerciciounomodificacion(peluquero t, int edadclientes);
};

#endif // PELUQUERIA_H

