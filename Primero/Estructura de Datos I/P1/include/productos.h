#ifndef PRODUCTOS_H
#define PRODUCTOS_H
#include "ventas.h"
#include <cstring>
#include <iostream>
using namespace std;
struct producto
{
 Sfecha ultimaventa;//fecha de la ultima venta
 int unidades;//unidades totales del producto
 int producto;//codigo del producto
 float importe;//importe totales del producto
 int tipo;//tipo de producto
 char nombre[20];//nombre del producto
};
class productos
{
    int numproductos;//numero de productos activos
    int maxcatalogo;//codigo de producto más grande que exista en el catalogo
    fstream resumen;
    cadena fichero;
    ventas ven;

    public:
       productos(cadena Fichero,cadena FicheroVentas);
       int getmaxcatalogo();
       void setmaxcatalogo(int num);
       int getnumproductos();
       void setnumproductos(int num);
       void mostrarproductos();
       void mostrarventas();
       bool anadirventa();
       void actualizarresumen();
       void obtenerestadisticas(int tipo,int annoini,int annofin);
       void obtenernumeroventaportip();
       void corregirventasderror(int codigoproducto, int annoini, int annofin, float importe);
};

#endif // PRODUCTOS_H

