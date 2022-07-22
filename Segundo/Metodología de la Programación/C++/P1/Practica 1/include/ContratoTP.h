#ifndef CONTRATOTP_H
#define CONTRATOTP_H

#include <Contrato.h>


class ContratoTP : public Contrato
{
  static int minutosTP;
  static float precioTP;
  int minutosHablados;
  static const float precioExcesoMinutos;
  public:
    ContratoTP(long int dni, Fecha f, int m);
    ~ContratoTP(); //¿es necesario? pensar y reflexionad
    //ContratoTP(const ContratoTP& c);//¿es necesario? pensar y reflexionad //realmente al ser una clase hija no hace falta ya que como se suele decir, al
                                      //padre lo que es del padre?
    //ContratoTP& operator=(const ContratoTP& c);//¿es necesario? pensar y reflexionad //Pienso que no porque tenemos variables staticas que son comunes
                                                 //al objeto y siempre tendrán el mismo valor(se van a compartir para todos los objetos creados, por tanto no es necesario.
    static int getLimiteMinutos() { return ContratoTP::minutosTP; }//Las variables static se inicializan asi
    static float getPrecio() { return ContratoTP::precioTP; }
    int getMinutosHablados() const {return this->minutosHablados; }
    static void setTarifaPlana(int m, float p);//en el .cpp se pone la cabecera sin la palabra static
    void setMinutosHablados(int m);
    void ver() const;
    float factura() const;
    virtual void nada() const {;};
};

ostream& operator<<(ostream &s, const ContratoTP &c);

#endif // CONTRATOTP_H
