/*
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    guardando los datos y permitiendo imprimir la gráfica correspondiente
 * 3. Comparar el coste temporal de dos de los métodos de ordenación
 *    burbuja, inserción, y selección, guardando los datos y permitiendo imprimir la gráfica correspondiente.
 */
#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Graficas.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>



using namespace std;


TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Burbuja");
	nombreAlgoritmo.push_back("Insercion");
	nombreAlgoritmo.push_back("Seleccion");
}
TestOrdenacion::~TestOrdenacion(){}

/*
 * Ordena un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de ordenacion a utilizar
 * return Tiempo empleado en la ordenación (en milisegundos)
 */
double TestOrdenacion::ordenarArrayDeInt(int v[],int size,int metodo)
{
	AlgoritmosOrdenacion estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	// Invoca al método de ordenación elegido
	switch (metodo){
		case BURBUJA: estrategia.ordenaBurbuja(v, size);
			break;
		case INSERCION: estrategia.ordenaInsercion(v, size);
			break;
		case SELECCION: estrategia.ordenaSeleccion(v, size);
			break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;
}

/*
 * Comprueba los metodos de ordenacion
 */
void TestOrdenacion::comprobarMetodosOrdenacion()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla del vector a ordenar: ";
	cin>>talla;
	system("cls");
	for(int metodo=0;metodo<nombreAlgoritmo.size();metodo++){
		ConjuntoInt *v= new ConjuntoInt(talla);
		v->GeneraVector(talla);
		cout << endl << endl << "vector inicial para el metodo " << nombreAlgoritmo[metodo] << ":" << endl << endl;
		v->escribe();
		ordenarArrayDeInt(v->getDatos(),talla,metodo);
		cout << endl << endl <<"Array ordenado con metodo " << nombreAlgoritmo[metodo] << ":" << endl << endl;
		v->escribe();
		cout << endl;
		v->vaciar();
		delete v;
		system("pause");
		system("cls");
	} /* fin del for */
	system("cls");
}

/*
 * Calcula el caso medio de un metodo de ordenacion,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de ordenacion a estudiar.
 */
void TestOrdenacion::casoMedio(int metodo){
   cout << "\n\t  *** Medicion del algoritmo " << nombreAlgoritmo[metodo] << " ***\n" << endl
		<< "\t       TALLA \t       TIEMPO (ms)\n";
   cout << "\t       ----- \t       -----------\n";
	ofstream file(("t"+nombreAlgoritmo[metodo] + ".dat").c_str());
	if (file.fail())
		cout << "Error al abrir el archivo.\n";
	double tiempo=0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
        {
		ConjuntoInt *v = new ConjuntoInt(talla);
		for (int i = 0; i<NUMREPETICIONES; i++){
			v->GeneraVector(talla);
			tiempo += ordenarArrayDeInt(v->getDatos(), talla, metodo);
			v->vaciar();}
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
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], CUADRADO);///AQUI ES DONDE HAY QUE ELEGIR EL ORDEN DEL ALGORITMO A CAMBIAR
		cout << "La grafica fue generada.\n\n";
		system("start CmdMedio.gpl");//corregir
		}else {
	    cout << "No se generara la grafica.\n\n";
	}
	system("pause");
}
/*
 * Compara dos metodos de ordenacion.
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2) {
	cout << "\n\t     *** Comparativa del algoritmo " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2] << " ***\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t             \t\t       \t-------------------------\n"
		<< "\t       TALLA \t\t    \t" << nombreAlgoritmo[metodo1] << "  \t" << nombreAlgoritmo[metodo2] << "\n"
        << "\t       _____\t\t       ________ " << "  \t" << "________" << "\n";
	ofstream file(("t" + nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat").c_str());
	if (file.fail())
		cout << "Error al abrir el archivo.\n";
	double tiempo1=0, tiempo2=0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
		ConjuntoInt *v = new ConjuntoInt(talla);
		ConjuntoInt *vclon=new ConjuntoInt(talla);
		for (int i = 0; i < NUMREPETICIONES; i++) {
			v->GeneraVector(talla);
			vclon->Clonar(v->getDatos(),talla);
			tiempo1 += ordenarArrayDeInt(v->getDatos(), talla, metodo1);
			tiempo2 += ordenarArrayDeInt(vclon->getDatos(), talla, metodo2);
			v->vaciar();}
		tiempo1 /= NUMREPETICIONES;
		tiempo2 /= NUMREPETICIONES;
		delete v;
		cout.precision(4);
		cout << "\t\t" << talla << "\t\t\t" << tiempo1 << "\t\t " << tiempo2 << "\n";
		file << talla << "\t" << tiempo1 << "\t" << tiempo2 << "\n";}
	file.close();
	char respuesta;
	cout << "\nGenerar grafica (s, n): "; cin >> respuesta;
	if (respuesta == 's' || respuesta == 'S') {
		Graficas g;
		g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
		cout << "La grafica fue generada.\n\n";
		system("start graficaCMP.gpl");
	}else cout << "No se generara la grafica.\n\n";
	system("pause");
}


/*void TestOrdenacion::compararTeorico(int metodo1, int metodo2, int metodo3)
{
	ofstream f( (nombreAlgoritmo[metodo1]+nombreAlgoritmo[metodo2]+nombreAlgoritmo[metodo3]+"Teorico.dat").c_str() );
	system("cls");
	cout<<endl<<"Busqueda Secuencial" << " Teorico";
	cout<<". Tiempos de ejecucion "<<endl<<endl;
	cout<<endl;;
	cout<<"\tTalla\t\tTiempo (oe)"<<endl<<endl;
	double tiempoPeor=0;int tiempoMedio=0; int tiempoMejor=0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		Caso peor (T(n)= 7n+9) */
		//tiempoPeor = 7*talla+9;
		/*Caso medio (T(n)= (7/2)n+9)*/
		//tiempoMedio = ((7/2)*talla)+9;
		/*Caso mejor (T(n)= 9)*/
		//tiempoMejor = 9;
		/*escribir en el fichero*/
		//f<<talla<<"\t"<< tiempoPeor<<"\t"<< tiempoMedio<<"\t"<< tiempoMejor<<endl;
		/*Visualizar en pantalla*/
		/*cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempoPeor<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMedio<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMejor<<" \t\t" <<endl;
		cout<<endl;
	}
	f.close();
	cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmo[metodo1]+nombreAlgoritmo[metodo2]+nombreAlgoritmo[metodo3]<<"Teorico.dat "<<endl;
	Generar grafica */
		/*char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			Ejecutar el fichero por lotes (comandos)*/
			/*system("CmdCompararTeorico.gpl"); system("cls");
			//system((gpl).c_str());
			cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmo[metodo1]+nombreAlgoritmo[metodo2]+nombreAlgoritmo[metodo3]+"Teorico"<<".pdf"<<endl;
						 }break;
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;
		}
		cout<<endl;
		system("pause");
		system("cls");
}*/

