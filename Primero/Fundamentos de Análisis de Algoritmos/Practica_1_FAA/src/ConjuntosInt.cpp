#include <iostream>
using namespace std;
#include <ctime>  //para time
#include <cstdlib> // for srand, rand
#include "ConjuntosInt.h"

ConjuntoInt::ConjuntoInt (int max)
{
 tamano= max;
 n=0;
 datos= new int[max];
}
ConjuntoInt::~ConjuntoInt ()
{
 delete[] datos;
}
void ConjuntoInt::vaciar ()
{
 n= 0;
}
int* ConjuntoInt::getDatos()
{
    /*
	int* v=datos;
	for (int i= 0; i <tamano;i++){
		v[i]=datos[i];}
	return v;
	*/
	return datos;
}

void ConjuntoInt::GeneraVector (int tam)
{
  if (tam>tamano) {
    tamano=tam;
    delete [] datos;
    datos=new int[tamano];
  }
  n=tam;
  srand( (unsigned)time( NULL ) ); //srand(time(0));
  for (int i=0; i<n; i++) {
    datos[i] = rand()%1000; //genera un número aleatorio entre 0 y 999
  }
}
int ConjuntoInt::generaKey()
{
 //srand( (unsigned)time( NULL ) ); //srand(time(0));
 return rand()%1000; //genera un número aleatorio entre 1 y 999
}
void ConjuntoInt::escribe()
{
 for (int i= 0; i<n; i++)
     cout << datos[i] << (i<n-1? ", ": "\n");
}
int ConjuntoInt::getPrimero() {
	return datos[0];
}
int ConjuntoInt::busquedaSecuencial (int valor)
{
	int i=0;
	while (datos[i]!= valor && i<n)
	{
		i=i+1;
	}
	if (datos[i]==valor)
		return i;	 // se encuentra el elemento en el array
	else
		return -1;  // no se encuentra el elemento en el array
}
