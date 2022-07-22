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

void complejo::ver() const{
  if (this->imaginario>=0)
    cout << this->real << "+" << this->imaginario << "i\n";
  else
    cout << this->real << this->imaginario << "i\n";
}

complejo complejo::operator+(complejo c) const { //c1.operator+(c2) --> c1+c2
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
complejo complejo::operator+(complejo c) const
{
    return complejo(this->real+c.real, this->imaginario+c.imaginario);
}

//y ESTA VERSION TAMBIEN
complejo complejo::operator+(complejo c)
{
    complejo suma(0, 0);
    suma.set(this->real+c.real, this->geti()+c.geti());
    return suma;
}
*/


complejo complejo::operator+(int i) const {      //c1.operator+(i)  --> c1+i
  return complejo (this->real+i,this->imaginario);
  //complejo suma(this->real+i,this->imaginario);
  //return suma;
}

//FUNCION AJENA A LA CLASE (NO ES AMIGA DE ELLA TAMPOCO)
complejo operator+(int i, complejo c) {  //operator+(i,c) --> i+c
  complejo suma(c.getr()+i,c.geti());
  return suma;
  //complejo suma(0,0);
  //suma.set(i+c.getr(), c.geti());
  //return suma;
}

complejo complejo::operator-() const { //c1.operator-() --> -c1
  return complejo(-this->real, -this->imaginario);
  //complejo aux(0,0);
  //aux.real=-this->real;
  //aux.imaginario=-this->imaginario;
  //eturn aux;
}

complejo complejo::operator++() { //++c
  //this->set(this->getr()+1, this->geti());
  this->real=this->real+1;
  return *this;
}

complejo complejo::operator++(int i) { //c++
  complejo aux(this->real, this->imaginario);
  //this->set(this->getr()+1, this->geti());
  this->real=this->real+1;
  return aux;
/*
  this->set(this->getr()+1, this->geti());
  //this->real=this->real+1;
  return complejo(this->getr()-1, this-geti());
*/
}

bool complejo::operator==(complejo c) const { //c1.operator==(c2) --> c1==c2
  if (this->real==c.real && this->imaginario==c.imaginario)
    return true;
  else
    return false;
  //return (this->real==c.real && this->imaginario==c.imaginario);
}

bool complejo::operator==(int i) const { //c1.operator==(8) --> c1==8
  return (this->real==i && this->imaginario==0);
  //if (this->real==i && this->imaginario==0)
  //  return true;
  //else
  //  return false;
}

//FUNCION AJENA A LA CLASE (NO ES AMIGA DE ELLA TAMPOCO)
bool operator==(int i, complejo c) { //operator==(8,c) --> 8==c
  return (c==i);
  //if (i==c.getr() && c.geti()==0)
  //  return true;
  //else
  //  return false;
}

//FUNCION AJENA A LA CLASE (NO ES AMIGA DE ELLA TAMPOCO)
ostream & operator<<(ostream &s, const complejo &c) {
  if (c.geti()>=0)
    s << c.getr() << "+" << c.geti() << "i";
  else
    s << c.getr() << c.geti() << "i";
  return s;
}

complejo::operator int() const {
  return this->real;
}
