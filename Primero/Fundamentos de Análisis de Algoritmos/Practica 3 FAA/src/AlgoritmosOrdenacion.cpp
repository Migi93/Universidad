/*
 * Clase AlgoritmosOrdenacion que implementa los Algoritmos de Ordenaci�n para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes m�todos de Ordenaci�n en vectores:
 *	- Burbuja
 *	- Inserci�n
 *	- Selecci�n.
 */

#include "AlgoritmosOrdenacion.h"

AlgoritmosOrdenacion :: AlgoritmosOrdenacion() {}
AlgoritmosOrdenacion :: ~AlgoritmosOrdenacion(){}

/*
 * Funci�n ordenaBurbuja, implementa el m�todo de ordenaci�n Burbuja
 * param v: el array de enteros a ordenar
 * param size: tama�o del array de enteros a ordenar
 */

void AlgoritmosOrdenacion :: ordenaBurbuja(int v[], int size)
{
int auxiliar;
    for(int i=size-2;i>=0;i--){
        for(int j=0;j<=i;j++){
            if(v[j]>v[j+1]){
                auxiliar=v[j];
                v[j]=v[j+1];
                v[j+1]=auxiliar;
            }
        }
    }
}


/*
 * Funci�n ordenaInsercion, implementa el m�todo de ordenaci�n por Inserci�n
 * param v: el array de enteros a ordenar
 * param size: tama�o del array de enteros a ordenar
 */

void AlgoritmosOrdenacion :: ordenaInsercion(int v[], int size)
{
int x,j=0;
    for(int i=1;i<=size-1;i++){
        x=v[i];
        j=i-1;
        while(j>-1 && x<v[j]){
            v[j+1]=v[j];
            j=j-1;
        }
        v[j+1]=x;
    }
}

/*
 * Funci�n ordenaSeleccion, implementa el m�todo de ordenaci�n por Selecci�n
 * param v: el array de enteros a ordenar
 * param size: tama�o del array de enteros a ordenar
 */
void AlgoritmosOrdenacion :: ordenaSeleccion(int v[], int size)
{
int posminimo,auxiliar;
    for(int i=0;i<=size-2;i++){
        posminimo=i;
            for(int j=i+1;j<size;j++){
                if(v[j]<v[posminimo]){
                    posminimo=j;
                }
            }
        auxiliar=v[posminimo];
        v[posminimo]=v[i];
        v[i]=auxiliar;
    }
}
void AlgoritmosOrdenacion::ordenaQuicksort(int v[], int size){
    Quicksort( v, 0, size-1);
}

void AlgoritmosOrdenacion::Quicksort(int v[], int e, int d){
    if(e<d){
      int q=particion(v,e,d);
      Quicksort(v,e,q);
      Quicksort(v,q+1,d);
    }
}

int AlgoritmosOrdenacion::particion(int v[], int e, int d){
    int x=v[e];
    int i=e-1;
    int j=d+1;
    for(;;){
        while(x<v[--j]);
        while(v[++i]<x);
        if(i>=j) return j;
        int aux=v[i];
        v[i]=v[j];
        v[j]=aux;

    }
}











