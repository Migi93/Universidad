#ifndef CONJUNTOINT_H
#define CONJUNTOINT_H


#include <iostream>
using namespace std;

/////////// Declaración de la clase conjuntoInt /////////////

class ConjuntoInt
{
private:
	int tamano;
	int *datos;
public:
	ConjuntoInt (int max= 0);
	~ConjuntoInt ();
	void vaciar ();
	void GeneraVector (int tam);
	int* getDatos();
	void escribe ();
	void Clonar(int *v, int tam);
};

#endif // CONJUNTOINT_H
