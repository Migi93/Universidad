#include <iostream>
#include "complejo.h"

using namespace std;


complejo::complejo(int r, int i)
{
    setr(r);
    seti(i);
}

void complejo::set()
{
    cout << "Introduce el valor real:"; cin >> real;
    cout << "Introduce el valor imaginario:"; cin >> imaginario;
}

void complejo::set(int r, int i)
{
    real=r;
    imaginario=i;
}

void complejo::ver()
{
    if(imaginario>=0){
    cout << "z = " << getr() << " + " << geti() <<"i" << endl;
    }
    else{
    cout << "z = " << getr() << " " << geti() << "i" << endl;
    }
}
complejo operator+(complejo a, complejo b){

}
complejo operator-(complejo c, complejo d){

}
