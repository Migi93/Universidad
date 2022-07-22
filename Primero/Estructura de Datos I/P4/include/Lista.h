#ifndef LISTA_H
#define LISTA_H
#include <fstream>
#include <string>

typedef std::string linea;


class Lista
{
    private:
        linea *elementos; // elementos de la lista
        int n; // nº de elementos que tiene la lista
        int Tama; // tamaño de la tabla en cada momento

    public:
        Lista();
        ~Lista();
        Lista(linea &l);
        bool esvacia();
        int longitud();
        void anadirIzq(linea e);
        void anadirDch(linea e);
        void eliminarIzq();
        void eliminarDch();
        linea observarIzq();
        linea observarDch();
        void concatenar(Lista l);
        bool pertenece(linea &l);
        void insertar(int i, linea l);
        void eliminar(int i);
        void modificar(int i, linea &l);
        linea observar(int i);
        int posicion(linea &l);

};

#endif // LISTA_H
