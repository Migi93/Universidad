#include <iostream>
#include <conio.h>
#include <cstdlib>
#include <stdio.h>
#include <Prueba.h>

using namespace std;

int main()
{
    char opcion;
    Prueba p("huelvaextremapro.dat","huelvaextrema.dat");
    bool salida = false;
    do {
        system("cls");
        cout << "Huelva Extrema" << endl;
        cout << "-----------------------------------------" << endl;
        cout << "Ciclistas: " << p.getNumCiclistas() << endl << endl;
        cout << "\t1. Consulta de inscripciones" << endl;
        cout << "\t2. Inscripcion a la prueba" << endl;
        cout << "\t3. Busqueda de una inscripcion" << endl;
        cout << "\t4. Modificar datos de una inscripcion" << endl;
        cout << "\t5. Eliminar una inscripcion" << endl;
        cout << "\t6. Mostrar Clasificacion" << endl;
        cout << "\t7. Salir" << endl << endl;
        cout << " Indique la opcion deseada: ";
        cin >> opcion;

		switch(opcion) {
			case '1':{
			    system("cls");
			    cadena pais;
			    cin.ignore();
			    cout << "Indique las inscripciones del pais que desea mostrar o un '*' para mostrar todas." << endl;
			    cin.getline(pais, sizeof(cadena));
                p.mostrar(pais);
                system("pause");
				break;
            }

			case '2':{
			    system("cls");
                Ciclista nuevo;
                cin.ignore();
                cout << "Introduzca el dorsal del nuevo ciclista que desea introducir: " << endl;
                cin >> nuevo.dorsal;
                int busqueda = p.buscar(nuevo.dorsal);
                if(busqueda == -1){
                    cin.ignore();
                    cout << "Introduzca su Nombre: " << endl;
                    cin.getline(nuevo.nombre, sizeof(cadena));
                    cout << "Introduzca su Apellido: " << endl;
                    cin.getline(nuevo.apellidos, sizeof(cadena));
                    cout << "Introduzca su Pais: " << endl;
                    cin.getline(nuevo.pais, sizeof(cadena));
                    p.insertar(nuevo);
                } else {
                    cout << endl << "ERROR. Ya existe un ciclista con ese dorsal." << endl << endl;
                }
                system("pause");
				break;
			}

			case '3':{
			    system("cls");
                Ciclista tem;
                int dorsal;
                int posicion;
                cin.ignore();
                cout << "Introduzca el dorsal del ciclista que desea consultar: " << endl;
                cin >> dorsal;
                posicion = p.buscar(dorsal);
                tem = p.consultar(posicion);
                if(posicion != -1){
                    cout << endl;
                    cout << "Dorsal: " << tem.dorsal << endl;
                    cout << "Pais: " << tem.pais << endl;
                    cout << "Nombre: " << tem.nombre << endl;
                    cout << "Apellidos: " << tem.apellidos << endl;
                    cout << endl;
                } else {
                    cout << endl << "ERROR. No existe ningun ciclista con ese dorsal." << endl << endl;
                }
                system("pause");
				break;
			}

			case '4':{
                system("cls");
                Ciclista c;
                int dorsal;
                cin.ignore();
                cout << "Introduzca el dorsal del ciclista que desea actualizar: " << endl;
                cin >> dorsal;
                cout << endl;
                int busqueda = p.buscar(dorsal);
                if(busqueda != -1){
                    c = p.consultar(busqueda);
                    cin.ignore();
                    cout << "Introduzca nuevo Nombre: " << endl;
                    cin.getline(c.nombre, sizeof(cadena));
                    cout << "Introduzca nuevo Apellidos: " << endl;
                    cin.getline(c.apellidos, sizeof(cadena));
                    cout << "Introduzca nuevo Dorsal: " << endl;
                    cin >> c.dorsal;
                    p.modificar(c,busqueda);
                    cout << endl;
                } else {
                    cout << endl << "ERROR. No existe ningun ciclista con ese dorsal." << endl << endl;
                }
                system("pause");
				break;
			}

			case '5':{
                system("cls");
                int dorsal;
                cin.ignore();
                cout << "Introduzca el dorsal del ciclista que desea eliminar: " << endl;
                cin >> dorsal;
                cout << endl;
                int busqueda = p.buscar(dorsal);
                if(busqueda != -1){
                    p.eliminar(busqueda);
                } else {
                    cout << endl << "ERROR. No existe ningun ciclista con ese dorsal." << endl << endl;
                }
                system("pause");
				break;
			}

            case '6':{
                system("cls");
                p.mostrarClasificacion();
                system("pause");
				break;
            }

            case '7':{
                system("cls");
                salida = true;
				break;
            }

			default:{
                system("cls");
                if(opcion < 1 || opcion > 7){
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

