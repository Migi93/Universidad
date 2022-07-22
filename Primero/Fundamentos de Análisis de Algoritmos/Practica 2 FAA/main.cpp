#include "TestOrdenacion.h"
#include <iostream>
#include <cstdlib>
using namespace std;

int menu();
int menu2();


int main ()
{
    menu();		//muestra las opciones del programa
    system ("pause");
    return 0;
}

int menu()
{
    TestOrdenacion test;
    int opcion;
    do
    {
        system("cls");
        cout << "\n\t\t\t\t\t*** FAA. Practica 1. Curso 18/19 ***\n";
        cout << "\n";
        cout<<"\t\t\t\t\t  Alum: Miguel A. Sanchez De La Rosa\n\n";
        cout << "\t\t\t*** MENU PRINCIPAL ***" << endl;
        cout << "\n\t*** ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION ***\n";
        cout << "\n";
        cout << "\n\t\t1.- Probar los metodos de ordenacion\n";
        cout << "\n\t\t2.- Obtener el caso medio de un metodo de ordenacion\n";
        cout << "\n\t\t3.- Comparar dos metodos\n";
        cout << "\n\t\t0.- Salir\n";
        cout << "\n\t\t--------\n";
        cout << "\n\t\tElige Opcion: ";
        cin >> opcion;
        switch (opcion)
        {
        case 1:
        {
            system("cls");
            test.comprobarMetodosOrdenacion();
            break;
        }
        case 2:
        {   menu2();
            break;
        }
        case 3:
        {
            system("cls");
            int metodo1, metodo2;
            do{
            system("cls");
            cout << "\n\t\t\t*** METODOS ***" << endl;
            cout << "\t\t\t _____________" << endl;
            cout << "\n\t\t\t 0.- Burbuja\n";
            cout << "\n\t\t\t 1.- Inserccion\n";
            cout << "\n\t\t\t 2.- Seleccion\n";
            cout << "\n\t\t\t 3.- Salir\n";
            cout << "\n\t\t\t ----------\n";
            cout << "\n\tIntroduce el primer metodo a comparar: "; cin >> metodo1;
            cout << "\n\tIntroduce el segundo metodo a comparar: "; cin >> metodo2;
            if(metodo1==3 || metodo2==3){
            system("cls");
                cout << "\nVolviendo al menu principal." << endl;
            }else{
            system("cls");
            test.comparar(metodo1,metodo2);
            }
            }while(metodo1!=3 && metodo2!=3);
            break;
        }
        case 0:
        {
            cout << "Gracias por usar el programa, hasta la vista" << endl;
            break;
        }
        default:
        {
            cout << "Opcion " << opcion << " no valida. "<<endl;
            break;
        }
        }
        system ("pause");
    }while (opcion !=0);
    return 0;
}

int menu2(){
    int opc;
    TestOrdenacion test;
    do
    {
        system("cls");
        cout << "\n\t*** Metodo a estudiar para el caso medio ***\n";
        cout << "\n\t\t\t0.- Burbuja\n";
        cout << "\n\t\t\t1.- Inserccion\n";
        cout << "\n\t\t\t2.- Seleccion\n";
        cout << "\n\t\t\t3.- Salir\n";
        cout << "\n\t\t\t----------\n";
        cout << "\t\t\telige una opcion: "; cin >> opc;
        switch (opc)
        {
        case 0:
        {
            system("cls");
            test.casoMedio(opc);
            break;
        }
        case 1:
        {
            system("cls");
            test.casoMedio(opc);
            break;
        }
        case 2:
        {
            system("cls");
            test.casoMedio(opc);
            break;
        }
        case 3:
        {
            system("cls");
            cout << "\nVolviendo al menu principal" << endl;
            cout << endl;
            break;
        }
        default:
        {
            cout << "\nOpcion " << opc << " no valida. "<<endl;
            cout << endl;
            break;
        }
        }
        system("pause");
        }while(opc!=3);
        return 0;
}
