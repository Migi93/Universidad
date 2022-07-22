#include "Prueba.h"
#include <iostream>
#include <fstream>
#include <conio.h>
#include <stdio.h>
#include <string.h>
#include <Clasificacion.h>
#include <stdlib.h>

using namespace std;

typedef char cadena[TAM_CADENA];

        Prueba::Prueba(char FicheroOrigen[],char FicheroDestino[]){
            numCiclistas = 0;
            Ciclista temporal; //Para guardar cada ciclista de forma temporal e introducirlo en el otro fichero
            int numCiclistasEnFichero = 0; //Variable en la que guardaremos el numero de ciclistas que hay por pais
            fich.open(FicheroOrigen, ios::binary | ios::in);
            if(fich.fail()){ //Si da fallo debemos crear el fichero destino vacio y guardar el valor de 0 en numCiclistas
                fichero.open(FicheroDestino, ios::binary | ios::out); //Cuando abrimos un fichero de esta forma, lo crea si no existe, por tanto creamos el fichero y ponemos 0 en numciclistas
                fichero.write((char*) &numCiclistas, sizeof(int));
                fichero.close();
                fich.clear();
                fichero.open(FicheroDestino, ios::binary | ios::out| ios::in);
            } else {
                fichero.open(FicheroDestino, ios::binary |ios::out);//Creamos el fichero, volcando la informacion
                fich.seekg(0, ios::beg);//Nos situamos al principio
                fichero.seekp(sizeof(int), ios::beg);//Nos situamos al prinipio para escritura
                fich.read((char*) &numCiclistasEnFichero, sizeof(int));
                while(!fich.eof()){
                    for(int i = 0 ; i < numCiclistasEnFichero; i++){
                    fich.read((char*) &temporal, sizeof(Ciclista));
                    fichero.write((char*) &temporal, sizeof(Ciclista));
                    numCiclistas++;
                    }
                    fich.read((char*) &numCiclistasEnFichero, sizeof(int));
                }
                fichero.seekp(sizeof(int), ios::beg);
                fichero.write((char*) &numCiclistas, sizeof(int));
                fichero.close();
                fich.close();
                fichero.open(FicheroDestino, ios::binary | ios::out | ios::in);
            }
        }

        Prueba::~Prueba(){
            fichero.close();
        }

        int Prueba::getNumCiclistas(){
            return numCiclistas;
        }

        void Prueba::mostrar(cadena pais){
            Ciclista tem;
                for(int i = 0; i < numCiclistas; i++){
                    fichero.seekg(sizeof(int) + i * sizeof(Ciclista), ios::beg);
                    fichero.read((char*) &tem, sizeof(Ciclista));
                    if(!fichero.fail()){
                        if(strcmp(pais,"*") == 0 || strcmp(pais, tem.pais) == 0){
                            cout << "Dorsal: " << tem.dorsal << endl;
                            cout << "Pais: " << tem.pais << endl;
                            cout << "Nombre: " << tem.nombre << endl;
                            cout << "Apellidos: " << tem.apellidos << endl;
                            cout << endl;
                    }
                } else {
                    cout << "Error al leer el fichero." << endl;
                }
            }
        }

        Ciclista Prueba::consultar(int posicion){
            Ciclista p;
            if(posicion >= 0 && posicion < numCiclistas){
            fichero.seekg(0, ios::beg);
            fichero.seekg(sizeof(int) + posicion * sizeof(Ciclista), ios::beg);
            fichero.read((char*) &p, sizeof(Ciclista));
            return p;
            } else {
                strcpy(p.nombre,"NULL");
                p.dorsal = -1;
                strcpy(p.apellidos,"NULL");
                strcpy(p.pais,"NULL");
                return p;
            }
        }

        int Prueba::buscar(int dorsal){
            Ciclista tem;
            int i = 0;
            int resultado = -1;
            bool encontrado = false;
            fichero.seekg(0, ios::beg);
            while(i < numCiclistas && !encontrado){
                fichero.seekg(sizeof(int) + i * sizeof(Ciclista), ios::beg);
                fichero.read((char*) &tem, sizeof(Ciclista));
                if(tem.dorsal == dorsal){
                    encontrado = true;
                    resultado = i;
                } else {
                    i++;
                }
            }
            return resultado;
        }

        void Prueba::insertar(Ciclista c){
            Ciclista nuevo;
            bool encontrado = false;
            int i = 0;
            fichero.seekg(0, ios::beg);
            while(i < numCiclistas && !encontrado){
                fichero.seekg(sizeof(int) + i * sizeof(Ciclista), ios::beg);
                fichero.read((char*) &nuevo, sizeof(Ciclista));
                if(strcmp(c.pais, nuevo.pais) == 0){
                    encontrado = true;
                } else{
                    i++;
                }
            }
            if(encontrado == false){
                fichero.seekp(0, ios::end);
                fichero.write((char*) &c, sizeof(Ciclista));
                numCiclistas++;
            } else {
                int j;
                int lee = numCiclistas - 1;
                int escribe = numCiclistas;
                fichero.seekg(0, ios::beg);
                fichero.seekp(0, ios::beg);
                for(j = i; j < numCiclistas; j++){
                    fichero.seekg(sizeof(int) + lee * sizeof(Ciclista), ios::beg);
                    fichero.read((char*) &nuevo, sizeof(Ciclista));
                    lee--;
                    fichero.seekp(sizeof(int) + escribe * sizeof(Ciclista), ios::beg);
                    fichero.write((char*) &nuevo, sizeof(Ciclista));
                    escribe--;
                }
                fichero.seekp(sizeof(int) + i * sizeof(Ciclista), ios::beg);
                fichero.write((char*) &c, sizeof(Ciclista));
                numCiclistas++;
            }
        }

        void Prueba::modificar(Ciclista c, int posicion){
                Ciclista tem = consultar(posicion);
                tem = c;
                fichero.seekp(0, ios::beg);
                fichero.seekp(sizeof(int) + posicion * sizeof(Ciclista), ios::beg);
                fichero.write((char*) &tem, sizeof(Ciclista));
        }

        void Prueba::eliminar(int posicion){
            Ciclista p;
            fichero.seekp(0, ios::beg);
            int posi = posicion + 1;
            fichero.seekp(sizeof(int) + posi * sizeof(Ciclista), ios::beg);
            int i;
            int j;
            int primera = 1;
            for(i = posi; i < numCiclistas; i++){
            p = consultar(i);
            if(primera == 1){
                j = posicion;
            } else {
                j = j + 1;
            }
            primera++;
            fichero.seekp(0, ios::beg);
            fichero.seekp(sizeof(int) + j * sizeof(Ciclista), ios::beg);
            fichero.write((char*) &p, sizeof(Ciclista));
            }
            numCiclistas--;
        }

        int marcas(int s) {
            return (rand()%s + 28000);
        }

        void tiempos(int tiempo, int &h, int &m, int &s){
            s = tiempo % 60;
            m = ((tiempo - s) % 3600) / 60;
            h = (tiempo - (s + (60 * m))) / 3600;
        }

        void Prueba::mostrarClasificacion(){
            Ciclista tem;
            int h, m ,s;
            for(int i = 0; i < numCiclistas; i++){
                fichero.seekg(sizeof(int) + i * sizeof(Ciclista), ios::beg);
                fichero.read((char*) &tem, sizeof(Ciclista));
                tem.marca = marcas(3500);
                fichero.seekp(sizeof(int) + i * sizeof(Ciclista), ios::beg);
                fichero.write((char*) &tem, sizeof(Ciclista));
            }
            Clasificacion c;
            Participante p;
            fichero.seekg(sizeof(int), ios::beg);
            for(int i = 0; i < numCiclistas; i++){
                fichero.read((char*) &tem, sizeof(Ciclista));
                p.dorsal = tem.dorsal;
                p.indice = i;
                p.marca = tem.marca;
                c.anadirparticipante(p);
            }
            c.ordenar();
            for(int i = 1; i <= c.numpartipantes() ; i++){
                p = c.consultar(i);
                p.indice = i;
                cout << "Posicion de carrera: " << p.indice << endl;
                cout << "Dorsal: " << p.dorsal << endl;
                tiempos(p.marca, h, m, s);
                cout << "Marca: "; if(h < 10){cout << "0";} cout << h << ":"; if(m < 10){cout << "0";} cout << m <<":"; if(s < 10){cout << "0";} cout << s << endl;
                cout << endl;
            }
        }



