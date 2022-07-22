/*
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 */
#include "TestBusqueda.h"
#include "Constantes.h"
#include "AlgoritmosBusqueda.h"
#include "AlgoritmosOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;

TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("SecuencialI");
	nombreAlgoritmo.push_back("BinariaR");
	nombreAlgoritmo.push_back("TernariaR");
}
TestBusqueda::~TestBusqueda()
{
}

/*
 * Busca en un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de búsqueda a utilizar
 * return Tiempo empleado en la busqueda (en milisegundos)
 */
double TestBusqueda::buscaEnArrayDeInt(int key,int v[],int size,int metodo,int &posicion)
{
    AlgoritmosBusqueda strategia;
		Mtime t;
		LARGE_INTEGER t_inicial, t_final;
		QueryPerformanceCounter(&t_inicial);
// Invoca al método de búsqueda elegido
    switch (metodo)
		{
        case SECUENCIALIt: posicion=strategia.busquedaSecuencialIt(v, size, key);
            break;
				case BINARIARc: posicion=strategia.busquedaBinariaRc(v, size, key);
            break;
				case TERNARIARc: posicion=strategia.busquedaTernariaRc(v, size, key);
            break;
		}
		QueryPerformanceCounter(&t_final);
    return t.performancecounter_diff(&t_final, &t_inicial) * 1000000;
}

/*
 * Comprueba los metodos de búsqueda
 */
void TestBusqueda::comprobarMetodosBusqueda(){
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla;
	system("cls");
	ConjuntoInt *v= new ConjuntoInt(talla);
	AlgoritmosOrdenacion AlOrdena;
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++){
		v->GeneraVector(talla);
		/* Ordenar array*/
		AlOrdena.ordenaInsercion(v->getDatos(),talla);
		cout <<endl<<endl<< "vector para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		int key;
		cout<<endl<<endl<<"Introduce la clave a buscar: ";
		cin>>key;
		int posicion;
		buscaEnArrayDeInt(key,v->getDatos(),talla,metodo,posicion);
		cout<<endl<<endl<<"posicion de "<<key<<" buscado con el metodo "<<nombreAlgoritmo[metodo]<< " : "<<posicion<<endl<<endl;
		v->vaciar();
		system("pause");
		system("cls");
	}
	system("cls");
}

/*
 * Compara dos metodos de búsqueda.
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo1: Primer metodo de búsqueda a comparar
 * param metodo2: Segundo metodo de búsqueda a comparar
 */
void TestBusqueda::comparar(int metodo1, int metodo2)
{
   //** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
   cout << "\n\t     *** Comparativa del algoritmo " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << " ***\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t             \t\t       \t-------------------------\n"
		<< "\t       TALLA \t\t    \t" << nombreAlgoritmo[metodo1] << "  \t" << nombreAlgoritmo[metodo2] << "\n"
        << "\t       _____\t\t       ________ " << "  \t" << "________" << "\n";
	ofstream file(("t" + nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat").c_str());
	if (file.fail())
		cout << "Error al abrir el archivo.\n";
	//double tiempo1=0, tiempo2=0;
	int key;
	int posicion;
	AlgoritmosOrdenacion AlOrdena;
	for (int talla = tallaIni2; talla <= tallaFin2; talla += incTalla2) {
		ConjuntoInt *v = new ConjuntoInt(talla);
		ConjuntoInt *vclon=new ConjuntoInt(talla);
		double tiempo1=0, tiempo2=0;
		for (int i = 0; i < NUMREPETICIONES; i++) {
			v->GeneraVector(talla);
			AlOrdena.ordenaInsercion(v->getDatos(),talla);
			vclon->Clonar(v->getDatos(),talla);
			key=v->generaKey();
			tiempo1 += buscaEnArrayDeInt(key, v->getDatos(), talla, metodo1, posicion);
			tiempo2 += buscaEnArrayDeInt(key, vclon->getDatos(), talla, metodo2, posicion);
			v->vaciar();
			}
		tiempo1 /= NUMREPETICIONES;
		tiempo2 /= NUMREPETICIONES;
		delete v;
		cout.precision(4);
		cout << "\t\t" << talla << "\t\t\t" << tiempo1 << "\t\t " << tiempo2 << "\n";
		file << talla << "\t" << tiempo1 << "\t" << tiempo2 << "\n";
		}
	file.close();
	char respuesta;
	cout << "\nGenerar grafica (s, n): "; cin >> respuesta;
	if (respuesta == 's' || respuesta == 'S') {
		Graficas g;
		g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
		cout << "La grafica fue generada.\n\n";
		system("start graficaCMP.gpl");
	}else
	cout << "No se generara la grafica.\n\n";
	system("pause");
}
/*
 * Calcula el caso medio de un metodo de búsqueda,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de búsqueda a estudiar.
 */
void TestBusqueda::casoMedio(int metodo)
{
   cout << "\n\t  *** Medicion del algoritmo " << nombreAlgoritmo[metodo] << " ***\n" << endl
		<< "\t       TALLA \t       TIEMPO (ms)\n";
   cout << "\t       ----- \t       -----------\n";
	ofstream file(("t"+nombreAlgoritmo[metodo] + ".dat").c_str());
	if (file.fail())
		cout << "Error al abrir el archivo.\n";
    AlgoritmosOrdenacion AlOrdena;
	//double tiempo=0;
	int posicion, key;
	for (int talla = tallaIni2; talla <= tallaFin2; talla += incTalla2)
        {
            double tiempo=0;
		ConjuntoInt *v = new ConjuntoInt(talla);
		for (int i = 0; i<NUMREPETICIONES; i++){
			v->GeneraVector(talla);
			key=v->generaKey();
			AlOrdena.ordenaInsercion(v->getDatos(),talla);
			tiempo += buscaEnArrayDeInt(key,v->getDatos(),talla,metodo,posicion);
			v->vaciar();
			}
		tiempo /= NUMREPETICIONES;
		delete v;
		cout << "\t\t" << talla << "\t\t" << tiempo << "\n";
		file << talla << "\t" << tiempo << "\n";
		}
	file.close();
	char respuesta;
	cout << "\nGenerar grafica (s, n): "; cin >> respuesta;
	if (respuesta == 's' || respuesta == 'S') {
		Graficas g;
		if(metodo==0){
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], N);///AQUI ES DONDE HAY QUE ELEGIR EL ORDEN DEL ALGORITMO A CAMBIAR
		}
		if(metodo==1){
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], LOGN);///AQUI ES DONDE HAY QUE ELEGIR EL ORDEN DEL ALGORITMO A CAMBIAR
		}
		if(metodo==2){
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], LOGN);///AQUI ES DONDE HAY QUE ELEGIR EL ORDEN DEL ALGORITMO A CAMBIAR
		}
		cout << "La grafica fue generada.\n\n";
		system("start CmdMedio.gpl");
		}else {
	    cout << "No se generara la grafica.\n\n";
	}
	system("pause");
}
