/*
 *Definiciones de las Constantes utilizadas en la pr�ctica 3.
 */
#ifndef _CONSTANTES
#define _CONSTANTES

/* Constantes simb�licas para indicar el metodo de ordenacion*/
enum algoritmosOrdenacion { BURBUJA,QUICKSORT, SELECCION,INSERCION};

/* Constantes simb�licas para indicar el metodo de Busqueda*/
enum algoritmosBusquedaClave{SECUENCIALIt, BINARIARc, TERNARIARc};

/* Constantes para indicar el Orden de complejidad*/
enum ordenes {LOGN,N,NLOGN,CUADRADO};

/* Constantes para indicar el Numero de repeticiones para el caso medio de cada m�todo de ordenaci�n-b�squeda */
static const int NUMREPETICIONES=100;

/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100};

/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas2 { tallaIni2 = 1000,tallaFin2 = 10000,incTalla2 = 1000};



#endif
