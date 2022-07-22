/*
 * La clase Test contiene los metodos para:
 * 1. Comprobar que el/los algoritmos funcionan adecuadamente.
 * 2. Calcular la eficiencia para los casos de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente
 * 3. Comparar el coste temporal de los casos de búsqueda
 *    secuencial, permitiendo guardar los datos e imprimir la
 *    gráfica correspondiente.
 */
#include "TestAlgoritmo.h"
#include "Constantes.h"
#include "ConjuntoInt.h"
#include "Mtime.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;

TestAlgoritmo::TestAlgoritmo()
{
	nombreAlgoritmoCaso.push_back("SecuencialPeor");
	nombreAlgoritmoCaso.push_back("SecuencialMedio");
	nombreAlgoritmoCaso.push_back("SecuencialMejor");
}
TestAlgoritmo::~TestAlgoritmo()
{
}

/* Comprueba que el algoritmo funciona */
void TestAlgoritmo::comprobarAlgoritmo()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla;
	ConjuntoInt *v= new ConjuntoInt(talla);
	v->GeneraVector(talla);
	cout <<endl<<endl<< "vector para el algoritmo "<< ":"<<endl<<endl;
	v->escribe();
	int key;
	cout<<endl<<endl<<"Introduce la clave a buscar: ";
	cin>>key;
	int posicion;
	// Buscar la clave en el array
	posicion=v->busquedaSecuencial(key);
	cout<<endl<<endl<<"posicion de "<<key<<" buscado con el algoritmo de busqueda secuencial "/*<<nombreAlgoritmoCaso[metodo]*/<< " : "<<posicion<<endl<<endl;
	v->vaciar();
	system("pause");
	system("cls");
}
/*
 * Calcula el coste TEORICO de los casos de un Algoritmo,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: caso del algoritmo a estudiar.
 */
	void TestAlgoritmo::costeCasoTeorico(int numerocaso)
	{
		ofstream f( (nombreAlgoritmoCaso[numerocaso]+"Teorico.dat").c_str() ) ;
		system("cls");
		cout<<endl<<"Busqueda Secuencial" << nombreAlgoritmoCaso[numerocaso]+" Teorico";
		cout<<"Tiempos de ejecucion "<<endl<<endl;
		cout<<"\tTalla\t\tTiempo (oe)"<<endl<<endl;
		double tiempo=0;
		for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
		{
			switch (numerocaso){
			case SECUENCIALPEOR: /*Caso peor (T(n)= 7n+9)*/
				{
					tiempo = 7*talla+9;
				}
				break;
			case SECUENCIALMEDIO:/*Caso medio (T(n)= (7/2)n+9)*/
				{
					tiempo = ((7/2)*talla)+9;
				}
				break;
			case SECUENCIALMEJOR:/*Caso mejor (T(n)= 9)*/
				{
					tiempo = 9;
				}
				break;
			}
			f<<talla<<"\t"<< tiempo<<endl;
			cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempo<<" \t\t"<<endl;
		}
		f.close();
		cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmoCaso[numerocaso]<<"Teorico.dat "<<endl;

		/* Generar grafica */
		char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			switch (numerocaso){
			case SECUENCIALMEJOR:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdMejorTeorico.gpl"); system("cls");
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Teorico"<<".pdf"<<endl;
													 }break;
			case SECUENCIALPEOR:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdPeorTeorico.gpl"); system("cls");
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Teorico"<<".pdf"<<endl;
													}break;
			case SECUENCIALMEDIO:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdMedioTeorico.gpl");
				system("cls");
				//system((gpl).c_str());
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Teorico"<<".pdf"<<endl;
													 }break;
			default: {cout <<"Error caso "<<endl;}break;
			}
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;
						 }
		}
		cout<<endl;
		system("pause");
		system("cls");
	}

/*
 * Compara los casos de un algoritmo.
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo1: Primer algoritmo a comparar
 * param metodo2: Segundo algoritmo a comparar
 * param metodo3: Segundo algoritmo a comparar
 */
void TestAlgoritmo::compararTeorico(int metodo1, int metodo2, int metodo3)
{
	ofstream f( (nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]+"Teorico.dat").c_str() );
	system("cls");
	cout<<endl<<"Busqueda Secuencial" << " Teorico";
	cout<<". Tiempos de ejecucion "<<endl<<endl;
	cout<<endl;;
	cout<<"\tTalla\t\tTiempo (oe)"<<endl<<endl;
	double tiempoPeor=0;int tiempoMedio=0; int tiempoMejor=0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		/* Caso peor (T(n)= 7n+9) */
		tiempoPeor = 7*talla+9;
		/*Caso medio (T(n)= (7/2)n+9)*/
		tiempoMedio = ((7/2)*talla)+9;
		/*Caso mejor (T(n)= 9)*/
		tiempoMejor = 9;
		/*escribir en el fichero*/
		f<<talla<<"\t"<< tiempoPeor<<"\t"<< tiempoMedio<<"\t"<< tiempoMejor<<endl;
		/*Visualizar en pantalla*/
		cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempoPeor<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMedio<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMejor<<" \t\t" <<endl;
		cout<<endl;
	}
	f.close();
	cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]<<"Teorico.dat "<<endl;
	/* Generar grafica */
		char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			/* Ejecutar el fichero por lotes (comandos)*/
			system("CmdCompararTeorico.gpl"); system("cls");
			//system((gpl).c_str());
			cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]+"Teorico"<<".pdf"<<endl;
						 }break;
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;
		}
		cout<<endl;
		system("pause");
		system("cls");
}
void TestAlgoritmo::compararEmpirico(int metodo1, int metodo2, int metodo3)
{
    LARGE_INTEGER t_ini, t_fin;
    double secs;
    int num;
    ofstream f((nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]+"Empirico.dat").c_str());
	system("cls");
	cout<<endl<<"Busqueda Secuencial" << " Empirico";
	cout<<". Tiempos de ejecucion "<<endl<<endl;
	cout<<endl;;
	cout<<"\tTalla\t\tTiempo (oe)"<<endl<<endl;
	double tiempoPeor=0, tiempoMedio=0, tiempoMejor=0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
	    //caso peor
		ConjuntoInt *v=new ConjuntoInt(talla);
        v->GeneraVector(talla);
        num=-1;
        QueryPerformanceCounter(&t_ini);
        v->busquedaSecuencial(num);
        QueryPerformanceCounter(&t_fin);
        secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
        tiempoPeor=secs*1000.0;

        //caso medio
        v->GeneraVector(talla);
        num=v->generaKey();
        QueryPerformanceCounter(&t_ini);
        v->busquedaSecuencial(num);
        QueryPerformanceCounter(&t_fin);
        secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
        tiempoMedio=secs*1000.0;

        //caso mejor
        v->GeneraVector(talla);
        num=v->getPrimero();
        QueryPerformanceCounter(&t_ini);
        v->busquedaSecuencial(num);
        QueryPerformanceCounter(&t_fin);
        secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
        tiempoMejor=secs*1000.0;

		/*escribir en el fichero*/
		f<<talla<<"\t"<< tiempoPeor<<"\t"<< tiempoMedio<<"\t"<< tiempoMejor<<endl;
		/*Visualizar en pantalla*/
		cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempoPeor<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMedio<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMejor<<" \t\t" <<endl;
		cout<<endl;
	}
	f.close();
	cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]<<"Empirico "<<endl;
	/* Generar grafica */
		char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			/* Ejecutar el fichero por lotes (comandos)*/
			system("CmdCompararEmpirico.gpl"); system("cls");
			//system((gpl).c_str());
			cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]+"Empirico"<<".pdf"<<endl;
						 }break;
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;
		}
		cout<<endl;
		system("pause");
		system("cls");
}
/* Comprueba que el algoritmo funciona cuanto tiempo tarda*/
void TestAlgoritmo::comprobarAlgoritmo2()
{
LARGE_INTEGER t_ini, t_fin;
double secs;
    system("cls");
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla;
	ConjuntoInt *v= new ConjuntoInt(talla);
	v->GeneraVector(talla);
	cout <<endl<<endl<< "vector para el algoritmo "<< ":"<<endl<<endl;
	v->escribe();
	int key;
	cout<<endl<<endl<<"Introduce la clave a buscar: ";
	cin>>key;
	int posicion;
	// Buscar la clave en el array
QueryPerformanceCounter(&t_ini);
	posicion=v->busquedaSecuencial(key);
QueryPerformanceCounter(&t_fin);
	cout<<endl<<endl<<"posicion de "<<key<<" buscado con el algoritmo de busqueda secuencial "/*<<nombreAlgoritmoCaso[metodo]*/<< " : "<<posicion<<endl<<endl;
secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
    v->vaciar();
cout<<"Tiempo de ejecucuion = ";
cout<<setprecision(6)<<secs *1000.0<<" ms"<<endl;

	system("pause");
	system("cls");
}

/*
 * Calcula el coste TEORICO de los casos de un Algoritmo,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: caso del algoritmo a estudiar.
 */
	void TestAlgoritmo::costeCasoEmpirico(int numerocaso)
	{
		ofstream f( (nombreAlgoritmoCaso[numerocaso]+"Empirico.dat").c_str() ) ;
LARGE_INTEGER t_ini, t_fin;
double secs;
int num;
		system("cls");
		cout<<endl<<"Busqueda Secuencial" << nombreAlgoritmoCaso[numerocaso]+" Empirico";
		cout<<"Tiempos de ejecucion "<<endl<<endl;
		cout<<"\tTalla\t\tTiempo (oe)"<<endl<<endl;
		double tiempo=0;
		for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
		{
			switch (numerocaso){
			case SECUENCIALPEOR:
				{
				ConjuntoInt *v=new ConjuntoInt(talla);
				v->GeneraVector(talla);
				num=-1;
				QueryPerformanceCounter(&t_ini);
				v->busquedaSecuencial(num);
				QueryPerformanceCounter(&t_fin);
				secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
				tiempo=secs*1000.0;
				}
				break;
			case SECUENCIALMEDIO:
				{
				ConjuntoInt *v=new ConjuntoInt(talla);
				v->GeneraVector(talla);
				num=v->generaKey();
				QueryPerformanceCounter(&t_ini);
				v->busquedaSecuencial(num);
				QueryPerformanceCounter(&t_fin);
				secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
				tiempo=((secs*1000.0)/4);
				}
				break;
			case SECUENCIALMEJOR:
				{
				ConjuntoInt *v=new ConjuntoInt(talla);
				v->GeneraVector(talla);
				num=v->getPrimero();
				QueryPerformanceCounter(&t_ini);
				v->busquedaSecuencial(num);
				QueryPerformanceCounter(&t_fin);
				secs = Mtime::performancecounter_diff(&t_fin, &t_ini);
				tiempo=(secs*1000.0);
				}
				break;
			}
			f<<talla<<"\t"<< tiempo<<endl;
			cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempo<<" \t\t"<<endl;
		}
		f.close();
		cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmoCaso[numerocaso]<<"Empirico.dat "<<endl;

		/* Generar grafica */
		char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			switch (numerocaso){
			case SECUENCIALMEJOR:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdMejorEmpirico.gpl"); system("cls");
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Empirico"<<".pdf"<<endl;
													 }break;
			case SECUENCIALPEOR:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdPeorEmpirico.gpl"); system("cls");
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Empirico"<<".pdf"<<endl;
													}break;
			case SECUENCIALMEDIO:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdMedioEmpirico.gpl");
				system("cls");
				//system((gpl).c_str());
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Empirico"<<".pdf"<<endl;
													 }break;
			default: {cout <<"Error caso "<<endl;}break;
			}
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;
						 }
		}
		cout<<endl;
		system("pause");
		system("cls");
	}


