#include <iostream>
#include <cstdlib>
#include <Pila.h>
#include <CriptoDoc.h>
#include <Cola.h>

#include <stdio.h>
#include <cstring>
#include <stdio.h>

using namespace std;

//Miguel Ángel Sánchez De La Rosa

int main(){

    char opcion;
    bool salida = false;
    CriptoDoc c;
    Cola cola;

    do {
    system("cls");
        cout << endl << " CriptoDoc" << endl;
        cout << " ----------" << endl;
        cout << endl;
        cout << "\t1. Cifrar documento" << endl;
        cout << "\t2. Descifrar documento" << endl;
        cout << "\t3. Descifrar varios documentos" << endl;
        cout << "\t4. Invertir fichero de codigos" << endl;
        cout << "\t5. Comparar documentos" << endl;
        cout << "\t6. Almacenar fichero" << endl;
        cout << "\t7. Cifrar fichero almacenado" << endl;
        cout << "\t8. Salir" << endl << endl;
        cout << " Indique la opcion deseada: ";
        cin >> opcion;

        switch(opcion) {
			case '1':{
			    system("cls");
			    cadena nombreC, nombreR;
			    int codigoNum;
			    fflush(stdin);
			    cout << "Nombre del documento a cifrar: " << endl;
			    gets(nombreC);
                bool resultado = c.leer(nombreC);
                if(resultado == true){
                    fflush(stdin);
                    cout << "Nombre del documento resultante: " << endl;
                    gets(nombreR);
                    do{
                    cout << "Codigo numerico: " << endl;
                    cin >> codigoNum;
                        if(codigoNum < 100 || codigoNum > 200){
                            cout << "ERROR, el codigo debe estar comprendido entre 100 y 200 (ambos inclusive)." << endl;
                        }
                    } while(codigoNum < 100 || codigoNum > 200);
                    c.cifrar(codigoNum);
                    c.escribir(nombreR);
                } else{
                    cout << "ERROR. No se ha podido abrir el fichero entrada." << endl;
                }
                system("pause");
				break;
            }

			case '2':{
			    system("cls");
                cadena nombreC, nombreR;
			    int codigoNum;
                fflush(stdin);
			    cout << "Nombre del documento a descifrar: " << endl;
                gets(nombreC);
                bool resultado = c.leer(nombreC);
                if(resultado == true){
                    cout << "Nombre del documento resultante: " << endl;
                    gets(nombreR);
                    do{
                    cout << "Codigo numerico: " << endl;
                    cin >> codigoNum;
                        if(codigoNum < 100 || codigoNum > 200){
                            cout << "ERROR, el codigo debe estar comprendido entre 100 y 200 (ambos inclusive)." << endl;
                        }
                    } while(codigoNum < 100 || codigoNum > 200);

                    c.descifrar(codigoNum);
                    c.escribir(nombreR);
                } else{
                    cout << "ERROR. No se ha podido abrir el fichero entrada." << endl;
                }
                system("pause");
				break;
			}

			case '3':{
			    system("cls");
			    ifstream f;
			    CriptoDoc concatenar;
			    int codigo;
			    char doc[100];
			    int i = 1;
			    bool leido = true;
			    bool resultado;
			    c.leer("fichCodigos");
			    c.descifrar(123);
                c.escribir("fichCodigos");
                f.open("fichCodigos", ios::in); //Abrimos el fichCodigos con sus codigos ya descifrados
                if(!f.fail()){
                    f >> codigo;
                    while(!f.eof() && leido == true){
                        string nom = "doc-";
                        string num = std::to_string(i);
                        nom = nom + num;
                        i++;
                        strcpy(doc,nom.c_str());
                        resultado = c.leer(doc); //Leemos el primer doc
                        if(resultado == true){ //Si es true es porque existe ese doc
                            c.descifrar(codigo); //Se descifra con el codigo leido en fichCodigos
                            cout << "Se ha leido correctamente el documento: " + nom << endl; //Se indica los que se han leido
                            //Ahora se escribe el resultado en docDescifrado.txt
                            concatenar.concatenar(c);
                            concatenar.escribir("docDescifrado.txt");
                        } else {
                            leido = false;
                        }
                        f >> codigo;
                    }
                }
                //Para dejar el documento tal y como estaba
                c.leer("fichCodigos");
                c.cifrar(123);
                c.escribir("fichCodigos");
                f.close();
                system("pause");
				break;
			}

			case '4':{
			    fstream f;
			    linea l;
			    c.leer("fichCodigos");
			    c.descifrar(123);
			    Pila p;
			    for(int i = 1; i <= c.numlineas(); i++){
                    char num[100];
                    l = c.observar(i);
                    strcpy(num,l.c_str());
                    int codigo = atoi(num);
                    p.apilar(codigo);
			    }
                f.open("fichCodigos", ios::trunc | ios::out);
                if(!f.fail()){
                    while(!p.esvacia()){
                        int c = p.cima();
                        string cifrado = std::to_string(c);
                        f << cifrado;
                        f << endl;
                        p.desapilar();
                    }
                    f.close();
                }
                c.leer("fichCodigos");
                c.cifrar(123);
                c.escribir("fichCodigos");
                cout << endl;
                system("pause");
				break;
			}
			case '5':{
                system("cls");
                cadena documentoUno, documentoDos;
                CriptoDoc doc1, doc2;
                cout << "Nombre del primer documento cifrado: " << endl;
                fflush(stdin);
                gets(documentoUno);
                doc1.leer(documentoUno);
                cout << "Nombre del segundo documento para comprobar si el documento anterior es un cifrado de este: " << endl;
                fflush(stdin);
                gets(documentoDos);
                doc2.leer(documentoDos);
                int i = 100;
                bool comprobado = false;
                while(i < 201 && comprobado == false){
                    doc1.descifrar(i);
                    doc1.escribir("resultante");
                    if(doc1.esDocumentoCifrado(doc2) == true){
                        comprobado = true;
                    } else {
                        i++;
                    }
                }
                if(comprobado == true){
                    cout << "El documento primero es un texto cifrado del segundo" << endl;
                } else {
                    cout << "El documento primero no es un texto cifrado del segundo " << endl;
                }

                system("pause");
				break;
			}
			case '6':{
                system("cls");
                cadena nombreC;
                fflush(stdin);
			    cout << "Nombre del documento a encolar: " << endl;
			    gets(nombreC);
			    cola.encolar(nombreC);
                system("pause");
				break;
			}
			case '7':{
                system("cls");

                    if(cola.longitud() > 0){
                    int codigoNum;;
                    char doc[100];
                    cadena nombreR;
                    string nom = cola.primero();
                    cola.desencolar();
                    strcpy(doc,nom.c_str());
                    bool resultado = c.leer(doc);
                    if(resultado == true){
                    fflush(stdin);
                    cout << "Nombre del documento resultante: " << endl;
                    gets(nombreR);
                    do{
                    cout << "Codigo numerico: " << endl;
                    cin >> codigoNum;
                        if(codigoNum < 100 || codigoNum > 200){
                            cout << "ERROR, el codigo debe estar comprendido entre 100 y 200 (ambos inclusive)." << endl;
                        }
                    } while(codigoNum < 100 || codigoNum > 200);
                    c.cifrar(codigoNum);
                    c.escribir(nombreR);
                    } else {
                    cout << "ERROR. No se ha podido abrir el fichero entrada." << endl;
                }
                    } else {
                    cout << "ERROR. La cola esta vacia." << endl;
                    }

                system("pause");
				break;
			}


			case '8':{
                system("cls");
                salida = true;
				break;
			}

			default:{
                system("cls");
                if(opcion < 1 || opcion > 8){
                cout << "Opcion no valida.\a\n";
                system("pause");
				}
				break;
			}
		}
    } while(salida != true);
    cout << endl <<"***Hasta pronto.***\a\n";
    return 0;
}
