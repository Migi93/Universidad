#ifndef VENTAS_H
#define VENTAS_H
#include <fstream>
#include <cstring>
#include <iostream>

typedef char cadena[101];
using namespace std;
struct Sfecha//fecha
{
    int dia;
    int mes;
    int anno;
};
struct venta//fichero de ventas
{
 Sfecha fecha;//fecha de la venta
 int unidades;//unidades de la venta
 int producto;//codigo del producto
 float importe;//importe de la ventas
};
class ventas
{

  fstream detalle;
  cadena fichero;
  cadena ficheroresumen;

    public:

       void anadirventa(int num);
       void mostrarventas();
       void resumirfichero();
       void estadisticas(int tipo,int annoini,int annofin);
       bool asignar(cadena Fichero,cadena FicheroResumen);
       void numeroventaportipo();
       void corregirventas(int codigoproducto, int annoini, int annofin, float importe);

};

#endif // VENTAS_H
