/*
 * Clase AlgoritmosBusqueda que implementa los Algoritmos de B�squeda para buscar un elemento en un vector de enteros.
 * Define las implementaciones de los siguientes m�todos de b�squeda
 * de b�squeda en vectores:
 *	- Secuencial
 *	- Binaria o dicot�mica
 *  - Ternaria
 */

#include "AlgoritmosBusqueda.h"


/*
 * Implementaci�n de los m�todos de la clase AlgoritmosBusqueda
 */
AlgoritmosBusqueda::AlgoritmosBusqueda() { }
AlgoritmosBusqueda:: ~AlgoritmosBusqueda() { }

/*
	 * Funci�n busquedaSecuencialIt, implementa el m�todo de b�squeda secuencial Iterativo
	 * param v: el array de enteros donde buscar
	 * param size: tama�o del array
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array
	 */
int AlgoritmosBusqueda::busquedaSecuencialIt(int v[], int size,int key)
{
   int i=1;
   while(v[i]!=key && i<=size){
    i=i+1;
   }
   if(v[i]==key){
    return i+1;
   }
   else{
    return -1;
   }
}

/*
	 * Funci�n busquedaBinariaRc, implementa el m�todo de b�squeda binaria Recursivo
	 * param v: el array de enteros donde buscar
	 * param size: tama�o del array
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array
	 */

int AlgoritmosBusqueda::busquedaBinariaRc(int v[], int size,int key)
{
    return BinariaRc(v, 0, size-1, key);
}

int AlgoritmosBusqueda::BinariaRc(int v[], int left, int right, int key)
{
    if (left>right){
		return -1;
    }
    else{
        int mitad=((right+left+1)/2);
        if (key==v[mitad]){
		return mitad+1;
        }
        else{
		if (key<v[mitad]){
			return BinariaRc(v, left, mitad-1, key);
		}
		else{
			return BinariaRc(v, mitad+1, right, key);
            }
        }
    }
}

/*
	 * Funci�n busquedaTernariaRc, implementa el m�todo de b�squeda ternaria recursiva
	 * param v: el array de enteros donde buscar
	 * param size: tama�o del array
	 * param key: clave o elemento a buscar
	 * return posici�n de la clave en el array
	 */

int AlgoritmosBusqueda::busquedaTernariaRc(int v[], int size,int key)
{
	return TernariaRc(v, 0, size, key);
}

int AlgoritmosBusqueda::TernariaRc(int v[], int left, int right, int key)
{
	if (left>right){
		return -1;
	}
	else{
	int tercio=((right-left+1)/3);
	if (key==v[left+tercio]){
		return left+tercio+1;
	}
	else{
		if (key < v[left+tercio]){
			return TernariaRc(v, left, left+tercio-1, key);
		}
		else{
			if (key==v[right-tercio]){
				return  right-tercio+1;
			}
			else{
				if (key < v[right-tercio]){
					return TernariaRc(v, left+tercio+1, right-tercio-1, key);
				}
				else{
					return TernariaRc(v, right-tercio+1, right, key);
                    }
				}
			}
        }
	}
}
