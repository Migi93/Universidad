#include "CriptoDoc.h"
#include <iostream>
#include <stdio.h>
#include <cstring>

using namespace std;

    bool CriptoDoc::leer(char fichero[]){
        vaciar();
        ifstream f;
        linea l;
        bool resultado = false;
        int i = 1;
        f.open(fichero, ios::in);
        if(!f.fail()){
            getline(f,l);
            while(!f.eof()){
            texto.insertar(i,l);
            i++;
            getline(f,l);
            }
            resultado = true;
        }
            f.close();

            return resultado;
    }

    bool CriptoDoc::escribir(char fichero[]){
        ofstream f;
        bool resultado = false;
        linea l;
        f.open(fichero, ios::out);
        if(!f.fail()){
            f.seekp(0, ios::beg);
            for(int i = 1; i <= texto.longitud(); i++){
                l = texto.observar(i);
                    f << l << endl;
            }
            //f << "\n";
            resultado = true;
        }
        f.close();
        return resultado;
    }

    void CriptoDoc::cifrar(int codigo){
        int longitud;
        linea l;
        for(int i = 1; i <= texto.longitud(); i++){
            linea lin;
            l = texto.observar(i);
            longitud = l.length();
            for(int j = 0; j < longitud; j++){
                l[j] = l[j] + codigo;
                if(l[j] > 255){
                    l[j] = l[j] - 256;
                }
            }
            texto.modificar(i,l);
        }
    }

    void CriptoDoc::descifrar(int codigo){
        linea l;
        int longit;
        for(int i = 1; i <= texto.longitud(); i++){
            l = texto.observar(i);
            longit = l.length();
            for(int j = 0; j < longit; j++){
                l[j] = (l[j] - codigo);
                if(l[j] < 0){
                    l[j] = l[j] + 256;
                }
            }
            texto.modificar(i,l);
        }
    }

    void CriptoDoc::vaciar(){
        while(texto.esvacia() == false){
            texto.eliminarDch();
        }
    }

    void CriptoDoc::concatenar(CriptoDoc &doc){
        for(int i = 1; i <= doc.numlineas(); i++){
            texto.anadirDch(doc.observar(i));
        }
    }

    int CriptoDoc::numlineas(){
        return texto.longitud();
    }

    linea CriptoDoc::observar(int i){
        return texto.observar(i);
    }

    bool CriptoDoc::esDocumentoCifrado(CriptoDoc &doc){
        bool distinto = false;
        int i = 1;
        while(i <= numlineas() && distinto == false){
            if(observar(i) == doc.observar(i)){
                distinto = true;
            } else {
             i++;
            }
        }
        return distinto;
    }

