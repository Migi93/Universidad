#ifndef FECHA_H
#define FECHA_H

#include <iostream>

using namespace std;

class Fecha
{
    int dia,mes,anio;/*Como no tiene memoria dinamica esta clase, no hará falta implementar el constructor de copia ya que se hace por defecto, aunque si se quiere se puede implementar, ni
                     el operador de asignación ni el destructor ya que no tenemos memoria dinamica y si estatica*/
public:
    Fecha(int d,int m,int a);
    //Fecha(const Fecha &f); NO HACE FALTA IMPLEMENTARLO COMO HEMOS EXPLICADO ARRIBA
    //Fecha& operator=(const Fecha &f); NO HACE FALTA IMPLEMENTAR
    //~Fecha(); NO HACE FALTA IMPLEMENTAR
    int getDia() const {return this->dia;}
    int getMes() const {return this ->mes;}
    int getAnio() const {return this->anio;}
    void setFecha(int d,int m,int a);
    void ver() const;
    bool bisiesto() const;
    Fecha operator++();//++f
    Fecha operator++(int i);//f++
    Fecha operator+(int i) const;//f+1 --> f.operator+(i)
    //Fecha operator==(Fecha &f);

    friend Fecha operator+(int i, const Fecha &f);//i+f --> operator+(i,f)
    friend ostream& operator<<(ostream &s, const Fecha &f);//Le ponemos el friend para poder ver los atributos privados de la clase
};
    Fecha operator+(int i, const Fecha &f);//i+f --> operator+(i,f) En el apartado B del PDF nos dice que lo pongamos asi para que sea mas eficiente el programa si no quedaria asi -> Fecha operator+(int i, Fecha f);
                                           //Sirve para f1=2+f2+3 en el main
    ostream& operator<<(ostream &s, const Fecha &f);

#endif // FECHA_H
