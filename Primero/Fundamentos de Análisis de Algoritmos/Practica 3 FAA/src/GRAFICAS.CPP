/*
 * Clase Graficas, contiene m�todos crear los ficheros de comandos para dibujar
 * la gr�fica que corresponda.
 */
#include "Graficas.h"
#include "Constantes.h"
#include <fstream>
#include <iostream>
using namespace std;

/*
 * M�todo generarGraficaMEDIO, genera el fichero de comandos para GNUPlot
 * y dibuja la gr�fica del caso medio de un m�todo de
 * ordenaci�n y su ajuste a la funci�n correspondiente.
 * param nombre_metodo: metodo de ordenacion.
 * param orden: Orden del metodo de ordenacion.
 */
void Graficas::generarGraficaMEDIO(string nombre_metodo,int orden)
{
    ofstream file("CmdMedio.gpl");
	file << "#ESTE ES UN SCRIPT DE GNUPLOT PARA GNUPLOT PARA ESTUDIO DE 1 METODO" << endl;
	file << "set title \"" << nombre_metodo << "\"" << endl;
	file << "set key top left vertical inside" << endl;
	file << "set grid" << endl;
	file << "set xlabel \"Talla (n)\"" << endl;
	file << "set ylabel \"Tiempo(ms)\"" << endl;
	file << "\n";
	file << "Funcion(x) = ";
	switch (orden) {
		case CUADRADO:	file << "a*x*x + b*x + c\n";
		break;
		case NLOGN:		file << "a*x*log(x)+b\n";
		break;
		case N:		file << "a*x + b\n";
		break;
		case LOGN:		file << "a*log(x) + b\n";
		break;
	}
	file << "fit Funcion(x) \"t" << nombre_metodo << ".dat\" using 1:2 via ";
	switch (orden) {
		case CUADRADO:	file << "a,b,c\n";
		break;
		case NLOGN:		file << "a,b\n";
		break;
		case N:		file << "a,b\n";//corregir
		break;
		case LOGN:	file << "a,b\n";
		break;
	}
	file << "plot Funcion(x) title \"Aproximaci�n\", \"t" << nombre_metodo << ".dat\" using 1:2 title \"Resultado emp�rico\"" << endl;
	file << "set terminal pdf" << endl;
	file << "set output \"busqueda" << nombre_metodo << ".pdf\"" << endl;
	file << "replot" << endl;
	file << "pause -1 \"Pulsa Enter para continuar...\"" << endl;
	file.close();
}
/*
 * M�todo generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer m�todo de ordenaci�n
 * param nombre2: es el nombre del fichero de datos del segundo m�todo de ordenaci�n
 */
void  Graficas::generarGraficaCMP(string nombre1,string nombre2)
{
    ofstream file("graficaCMP.gpl");
	file << "#ESTE ES UN SCRIPT DE GNUPLOT PARA GNUPLOT PARA COMPARACION DE DOS METODOS" << endl;
	file << "set title \"" << nombre1 << " con " << nombre2 << "\"" << endl;
	file << "set key top left vertical inside" << endl;
	file << "set grid" << endl;
	file << "set xlabel \"Talla (n)\"" << endl;
	file << "set ylabel \"Tiempo(ms)\"" << endl;
	file << "\n";
	file << "plot \"t" << nombre1 << nombre2 << ".dat\" using 1:2 with lines title \""<< nombre1 <<"\", \"t" << nombre1 << nombre2 << ".dat\" using 1:3 with lines title \"" << nombre2 << "\"" << endl;
	file << "set terminal pdf" << endl;
	file << "set output \"busqueda" << nombre1 << nombre2 << ".pdf\"" << endl;
	file << "replot" << endl;
	file << "pause -1 \"Pulsa Enter para continuar...\"" << endl;
	file.close();
}
