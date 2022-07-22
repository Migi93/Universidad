#include <Clasificacion.h>
#include <iostream>

using namespace std;

        Clasificacion::Clasificacion(){
            tamano = SALTO;
            participantes = 0;
            elementos = new Participante[tamano];
        }

        Clasificacion::~Clasificacion(){
            delete [] elementos;
        }

        void Clasificacion::anadirparticipante(Participante a){
            if(participantes < tamano){
                elementos[participantes] = a;
                participantes++;
            } else{
                Participante *nuevotamano = new Participante[tamano + SALTO];
                if(nuevotamano != NULL){
                   for(int i =0; i < participantes; i++){
                    nuevotamano[i] = elementos[i];
                }
                delete [] elementos;
                elementos = nuevotamano;
                tamano = tamano + SALTO;
                elementos[participantes] = a;
                participantes++;
                } else {
                    cout << "Error al asignar memoria nueva" << endl;
                }
            }
        }

        void Clasificacion::eliminar(int i){
            for(int j = i - 1; j < (participantes - 1); j++){
                elementos[j] = elementos[j + 1];
            }
            participantes--;
        }

        Participante Clasificacion::consultar(int i){
            return elementos[i-1];
        }

        bool Clasificacion::vacio(){
            if(participantes == 0){
                return true;
            } else {
                return false;
            }
        }

        int Clasificacion::numpartipantes(){
            return participantes;
        }

        void intercambiar(Participante &a, Participante &b){
            Participante auxiliar = b;
            b = a;
            a = auxiliar;
        }

        void Clasificacion::ordenar(){
            int pos,ele;
            int fin=participantes;
            int inicio=0;
            for (pos=inicio; pos<fin; pos++)
                for (ele=fin-1; ele>pos; ele--)
                    if(elementos[ele-1].marca>elementos[ele].marca)
                        intercambiar(elementos[ele-1],elementos[ele]);
        }



