#ifndef MTIME_H
#define MTIME_H


#include <stdio.h>
#include <windows.h>
/////////// Declaración de la clase TIEMPO /////////////

class Mtime
{
public:
	/* retorna "a - b" en segundos */
	static double performancecounter_diff(LARGE_INTEGER *a, LARGE_INTEGER *b);
};


/* Uso
int main(int argc, char *argv[])
{
LARGE_INTEGER t_ini, t_fin;
double secs;

QueryPerformanceCounter(&t_ini);
 ...hacer algo...

QueryPerformanceCounter(&t_fin);

secs = performancecounter_diff(&t_fin, &t_ini);
printf("%.16g milliseconds\n", secs * 1000.0);
return 0;
}
*/


#endif // MTIME_H
