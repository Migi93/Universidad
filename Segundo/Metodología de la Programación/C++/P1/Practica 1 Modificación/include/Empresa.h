#ifndef EMPRESA_H
#define EMPRESA_H

#include "Cliente.h"
#include "Contrato.h"
#include "Fecha.h"
#include "ContratoTP.h"
#include "ContratoMovil.h"

class Empresa{
    Cliente *clientes[100]; //array estático (tamaño 100)de punteros a Clientes
    Contrato **contratos; //no haria falta crear tablas de contratoTP ni ContratoMovil porque la clase contrato es padre de la clase contratomovil y contratotp, las cuales derivan de la clase contrato
    int ncli; //para saber cuántos clientes hay en el array (al principio 0)
    const int nmaxcli; //para saber cuántos caben en el arrayclientes(100)
    int ncon; //para saber cuántos Contratos hay en el array (al principio 0)
    int nmaxcon; //para saber cuántos caben en el array Contratos
    public:
        //debido a que hay memoria dinamica habria que hacer el constructor de copia y el operador de asignacion aunque en la practica se dice que no y por eso no lo haremos
        Empresa();
        ~Empresa();

        void crearContrato();
        bool cancelarContrato(int idContrato); //true si el Contrato existe, false si no
        bool bajaCliente(long int dni);        //true si el Cliente existe, false si no
        int descuento (float porcentaje)const;//devuelve a cuantos aplica el descuento
        int nContratosTP()const;
        int buscarCliente(long int dni) const;
        int altaCliente(Cliente *c);
        void cargarDatos();
        void ver() const;//mostrara por pantalla los contratos y clientes de la empresa
        //bool cancelaContratoTP();
        int multarClientesContratoUnico();
        int nContratosMV(long int d) const;
        static void eliminarPeoresClientes(Empresa Vodafone);
        //bool cancelaContratoMovil(Fecha f);
};

#endif // EMPRESA_H
