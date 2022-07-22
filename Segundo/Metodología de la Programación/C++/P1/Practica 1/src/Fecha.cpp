#include "Fecha.h"
#include <iostream>

using namespace std;

Fecha::Fecha(int d,int m,int a){//El codigo es el mismo que el de setFecha por lo que se puede hacer esto y ahorrarnos trabajo
    this->setFecha(d, m, a);
}

void Fecha::setFecha(int d, int m, int a){//Se usa para poder darle valores a tus atributos privados de la clase en el main y poder ver lo que valen con el get????
    int dmax,diasMes[]={0,31,28,31,30,31,30,31,31,30,31,30,31};
    this->anio=a;

    if(m<1){
        m=1;
    }
    else if(m>12){
        m=12;
    }
    this->mes=m;
    dmax=diasMes[m];
    if(this->bisiesto()){
        diasMes[2]=29;
    }
    if(d<1){
        d=1;
    }
    else if(d>dmax){
        d=dmax;
    }
    this->dia=d;
}

void Fecha::ver() const{
    const char *meses[] = {"", "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};
    if (this->dia < 10){//Hacemos esto por si el día es menor de 9 por tanto si es menor de 9 ponemos este if para que nos ponga un 0 delante y quede así 09/...
    cout << "0";
    }
    cout << this->dia << " ";
    /*if (this->mes < 10){//Misma explicación que con el día, para que el mes quede asi -> 09 y no así -> 9
    cout << "0";
    }*/
    cout << meses[this->mes] << " " << this->anio;//El año nos da igual ya que siempre vamos a introducir los 4 valores del año que queramos meter
}

bool Fecha::bisiesto() const{
    if(this->anio%400==0){
        return true;
    }
    else if(this->anio%4==0 && this->anio%100!=0){
        return true;
    }
    else{
        return false;
    }
}

Fecha Fecha::operator++(){//++f primero lo suma y despues lo asigna
    int diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    if (this->bisiesto()==true){ //si el año es bisiesto hay que poner que febrero es de 29 dias
      diaMes[2]=29;//asignamos al mes 2(febrero) 29 dias como corresponde en tal caso
    }
    this->dia++;//aumentamos el dia
    if (this->dia>diaMes[this->mes]) { //si al incrementar dia superamos el numero de dias del mes que estamos incrementando
        this->dia=1;      //lo pasamos a 1
        this->mes++;      //del mes siguiente para que no aumentemos malamente
        if (this->mes>12) { //si al incrementar mes pasamos de 12 meses
            this->mes=1;    //pasamos al mes 1
            this->anio++;   //del año siguiente por tanto habria que aumentar todo
        }
    }
    return *this; //devolvemos el contenido del objeto fecha ya incrementado en +1
}

Fecha Fecha::operator++(int i){//f++ primero lo asigna y despues suma la fecha
    Fecha f(this->dia,this->mes,this->anio);//f vale la fecha que sea del objeto que creemos
    ++(*this);//aumentara su contenido llamando automaticamente el metodo operator++() que ya hemos implementado
    return f;//pero devuelvo la misma fecha sin aumentar
}

Fecha Fecha::operator+(int i) const {//f+i
    Fecha f(this->dia,this->mes,this->anio);//Creamos una variable tipo Fecha que nos devuelva la fecha actual
    for(int j=1;j<=i;j++){
        f.operator++();//Como el metodo operator++() nos aumenta en 1 la fecha, hacemos un bucle segun el numero de veces que queramos aumentar el objeto fecha
    }
        return f;//Finalmente devolvemos la fecha aumentada
}

Fecha operator+(int i, const Fecha &f){//f+i Funcion amiga
    return f+i;//Debido a que en nuestra clase fecha tenemos un operador ya sobrecargado que hace esta operacion (la de f+i que es el operator+(int i) const ) el mismo metodo llamara a ese otro metodo y se hara sin problema alguno
}

//Fecha operator==(){
//}

ostream& operator<<(ostream &s, const Fecha &f){ //Funcion amiga
    const char *meses[] = {"", "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};
  if (f.dia < 10)
    s << "0";
  s << f.dia << " " << meses[f.mes] << " " << f.anio;
  return s;
}

/*
x = 1;
y = ++x; ---'x' es ahora 2, 'y' es también 2, por lo que, primero lo suma y despues lo asigna a 'y'
y = x++; ---'x' es ahora 3, 'y' es 2, por lo que, primero lo asigna a 'y', y luego lo suma por lo que 'y' sigue valiendo igual que antes pero 'x' no vale lo mismo que antes
*/



