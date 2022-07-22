#include "Cliente.h"
#include <iostream>
#include <cstring>

using namespace std;

Cliente::Cliente(long int d, char *nom, Fecha f):fechaAlta(f){
    this->dni=d;
    this->nombre=new char[strlen(nom)+1];
    strcpy(this->nombre,nom);
}

Cliente::~Cliente(){
    delete [] this->nombre;
}

Cliente& Cliente::operator=(const Cliente& c) {
  if (this != &c) { //si no es x=x //Si el cliente apunta a una direccion de memoria distinta, entonces copia el dni del cliente que se desee asignar
    this->dni=c.dni;
    delete [] this->nombre;
    //this->nombre=c.nombre;  //MAL!!!! esta mal porque es una cadena de char
    this->nombre=new char[strlen(c.nombre)+1];
    strcpy(this->nombre, c.nombre);
    this->fechaAlta=c.fechaAlta;
  }
  return *this;
}

bool Cliente::operator==(Cliente &c) const {
  if(this->dni!=c.dni){//Si el dni es distinto devuelve falso
    return false;
  }
  else if(strcmp(this->nombre,c.nombre)!=0) {//Si el nombre es distinto devuelveme falso
    return false;
  }
  else if(this->fechaAlta.getDia()!=c.fechaAlta.getDia() || this->fechaAlta.getMes()!=c.fechaAlta.getMes() || this->fechaAlta.getAnio()!=c.fechaAlta.getAnio()) {//Si las fechas de alta son distintos devuelveme falso
    return false;
      }
  else
  return true;//Si el cliente es el mismo devuelveme verdadero
}

void Cliente::setNombre(char *nom){//Los set son para modificar el atributo que se desee en el main
    if(strcmp(this->nombre,nom)!=0){//Si los nombre son distintos, lo modificamos
        delete [] this->nombre;
        this->nombre=new char[strlen(nom)+1];
        strcpy(this->nombre,nom);
    }
}

void Cliente::setFecha(Fecha f){
    fechaAlta=f;
}

ostream& operator<<(ostream &s, const Cliente &c){
    const char *meses[] = {"", "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};
    Fecha f=c.getFecha();
    s << c.getNombre() << " (" << c.getDni() << " - ";
    s << f.getDia() << " " << meses[f.getMes()] << " " << f.getAnio() << ")";
    return s;
}
