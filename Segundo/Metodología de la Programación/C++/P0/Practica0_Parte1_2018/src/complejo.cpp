#include <iostream>
#include <cmath>
#include "complejo.h"

#define PI 3.14159265

using namespace std;


complejo::complejo(int vr, int vi) {
  this->real=vr;
  this->imaginario=vi;
}

void complejo::set() {
  cout << "real: ";
  cin >> this->real;
  cout << "imaginaria: ";
  cin >> this->imaginario;
}

void complejo::ver() {
  if (this->imaginario>=0)
    cout << this->real << "+" << this->imaginario << "i\n";
  else
    cout << this->real << this->imaginario << "i\n";
}

complejo complejo::operator+(complejo c) { //c1.operator+(c2) --> c1+c2
  complejo suma(0,0);
  suma.real=this->real+c.real;
  suma.imaginario=this->imaginario+c.imaginario;
  return suma;
}

/*
//a continuacion muestra 2 formas diferentes de implementar
//el metodo anterior que tambien son correctas
//Podeis sustituir la version de arriba por cualquiera de
//estas dos siguientes ya que son equivalentes

//ESTA VERSION TAMBIEN FUNCIONA
complejo complejo::operator+(complejo c)
{
    return complejo(this->real+c.real, this->imaginario+c.imaginario);
}

//y ESTA VERSION TAMBIEN
complejo complejo::operator+(complejo c)
{
    complejo suma(this->real, this->imaginario);
    suma.set(this->real+c.real, this->geti()+c.geti());
    return suma;
}
*/


complejo complejo::operator+(int i) {      //c1.operator+(i)  --> c1+i
  return complejo (this->real+i,this->imaginario);
  //complejo suma(this->real+i,this->imaginario);
  //return suma;
}

complejo complejo::operator-() { //c1.operator-() --> -c1
  return complejo(-this->real, -this->imaginario);
}

//FUNCION AMIGA DE LA CLASE
complejo operator+(int i, complejo c) {  //operator+(i,c) --> i+c
  complejo suma(c.real+i,c.imaginario);
  return suma;
}

//FUNCION AMIGA DE LA CLASE
ostream & operator<<(ostream &s, const complejo &c) {
  if (c.imaginario>=0)
    s << c.real << "+" << c.imaginario << "i";
  else
    s << c.real << c.imaginario << "i";
  return s;
}
