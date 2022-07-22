#include "ContratoTP.h"

using namespace std;

int ContratoTP::minutosTP=300;
float ContratoTP::precioTP=10;
const float ContratoTP::precioExcesoMinutos=0.15;

ContratoTP::ContratoTP(long int dni, Fecha f, int m):Contrato(dni,f){//*long dni y Fecha f son atributos privados de la clase Contrato de la cual deriva este, por tanto se inicializa de esta forma
                                                                     //aunque realmente no se inicializa si no que, sle deja a su clase perteneciente*/
    this->minutosHablados=m;
}

/*ContratoTP::ContratoTP(const ContratoTP& c):Contrato(c.getDniContrato(),c.getFechaContrato()){
    this->minutosHablados=c.minutosHablados;
}*/

ContratoTP::~ContratoTP()
{
    //dtor
}

void ContratoTP::setTarifaPlana(int m, float p){
    ContratoTP::minutosTP=m;
    ContratoTP::precioTP=p;
}

void ContratoTP::setMinutosHablados(int m){
    this->minutosHablados=m;
}

void ContratoTP::ver() const{
    Contrato::ver();
    //cout << *this;
    //cout << this->minutosHablados << "m, " << this->minutosTP  << " (" << this->precioTP << ")";
    cout << this->getMinutosHablados() << "m, " << this->getLimiteMinutos()  << "(" << this->getPrecio() << ")"
    << " - " << this->factura() << (char)(199);
}

float ContratoTP::factura() const{
        float precio=0;
        int m=ContratoTP::minutosTP;
        float p=ContratoTP::precioTP;
        float pe=ContratoTP::precioExcesoMinutos;
        if(this->minutosHablados>300){
            precio=(p+((this->minutosHablados-m)*pe));
        }
        else{
            precio=p;
        }
        return precio;
}

ostream& operator<<(ostream &s, const ContratoTP &c){
    s << c.getDniContrato() << " (" << c.getIdContrato() << "- " << c.getFechaContrato() << ") "
      << c.getMinutosHablados() << "m, "  << c.getLimiteMinutos() << "(" << c.getPrecio() << ")" << " - " << c.factura() << "€";
    return s;
}
