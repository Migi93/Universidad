#ifndef ALGORITMOSBUSQUEDA_H
#define ALGORITMOSBUSQUEDA_H


/*
 * Clase AlgoritmosBusqueda que implementa los Algoritmos de B�squeda para buscar un elemento en un vector de enteros.
 * Define las implementaciones de los siguientes m�todos de b�squeda
 * de b�squeda en vectores:
 *	- Secuencial
 *	- Binaria o dicot�mica
 *  - Ternaria
 */

class AlgoritmosBusqueda
{
public:
     AlgoritmosBusqueda();
    ~AlgoritmosBusqueda();

  /*
	 * Funci�n busquedaSecuencialIt, implementa el m�todo de b�squeda secuencial Iterativo
	 * param v: el array de enteros donde buscar
	 * param size: tama�o del array
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array
	 */
     int busquedaSecuencialIt(int v[], int size,int key);

	/*
	 * Funci�n busquedaBinariaRc, implementa el m�todo de b�squeda binaria Recursivo
	 * param v: el array de enteros donde buscar
	 * param size: tama�o del array
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array
	 */
     int busquedaBinariaRc(int v[], int size,int key);
	 int BinariaRc(int A[], int left, int right, int key);

	/*
	 * Funci�n busquedaTernariaRc, implementa el m�todo de b�squeda ternaria recursiva
	 * param v: el array de enteros donde buscar
	 * param size: tama�o del array
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array
	 */
	 int busquedaTernariaRc(int v[], int size,int key);
	 int TernariaRc(int A[], int left, int right, int key);
};

#endif // ALGORITMOSBUSQUEDA_H
