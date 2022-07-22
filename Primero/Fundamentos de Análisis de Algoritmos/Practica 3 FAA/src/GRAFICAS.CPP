/*
 * Clase Graficas, contiene métodos crear los ficheros de comandos para dibujar
 * la gráfica que corresponda.
 */
#include "Graficas.h"
#include "Constantes.h"
#include <fstream>
#include <iostream>
using namespace std;

/*
 * Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot
 * y dibuja la gráfica del caso medio de un método de
 * ordenación y su ajuste a la función correspondiente.
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
	file << "plot Funcion(x) title \"Aproximación\", \"t" << nombre_metodo << ".dat\" using 1:2 title \"Resultado empírico\"" << endl;
	file << "set terminal pdf" << endl;
	file << "set output \"busqueda" << nombre_metodo << ".pdf\"" << endl;
	file << "replot" << endl;
	file << "pause -1 \"Pulsa Enter para continuar...\"" << endl;
	file.close();
}
/*
 * Método generarGraficaCMP, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación
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
