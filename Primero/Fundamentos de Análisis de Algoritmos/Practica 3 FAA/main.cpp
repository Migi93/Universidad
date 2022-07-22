#include "TestOrdenacion.h"
#include "TestBusqueda.h"
#include <iostream>
#include <cstdlib>

using namespace std;

///a*log(x)+b para gnuplot en la parte nueva

int menu();
int menu2();
int menu3();
int menu4();
int menu5();


int main ()
{
    menu3();		//muestra las opciones del programa
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
        cout << "\n\t\t\t*** MENU DE ORDENACION ***" << endl;
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
            cout << "\n\t\t\t 1.- QuickSort\n";
            cout << "\n\t\t\t 2.- Seleccion\n";
            cout << "\n\t\t\t 3.- Inserccion\n";
            cout << "\n\t\t\t 4.- Salir\n";
            cout << "\n\t\t\t ----------\n";
            cout << "\n\tIntroduce el primer metodo a comparar: "; cin >> metodo1;
            cout << "\n\tIntroduce el segundo metodo a comparar: "; cin >> metodo2;
            if(metodo1==4 || metodo2==4){
            system("cls");
                cout << "\nVolviendo al menu de ordenacion." << endl;
            }else{
            system("cls");
            test.comparar(metodo1,metodo2);
            }
            }while(metodo1!=4 && metodo2!=4);
            break;
        }
        case 0:
        {
            system("cls");
            cout << "\nVolviendo al menu de principal." << endl;
            break;
        }
        default:
        {
            cout << "Opcion " << opcion << " no valida. "<<endl;
            break;
        }
        }
        system ("pause");
    }while (opcion !=4);
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
        cout << "\n\t\t\t1.- QuickSort\n";
        cout << "\n\t\t\t2.- Seleccion\n";
        cout << "\n\t\t\t3.- Inserccion\n";
        cout << "\n\t\t\t4.- Salir\n";
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
            test.casoMedio(opc);
            break;
        }
        case 4:
        {
            system("cls");
            cout << "\nVolviendo al menu de ordenacion" << endl;
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
        }while(opc!=4);
        return 0;
}

int menu3(){
    int opcion;
    do{
        system("cls");
        cout << "\n\t\t*** FAA. Practica 3. Curso 18/19 ***\n";
        cout << "\n";
        cout<<"\t\t\t\t\t  Alum: Miguel A. Sanchez De La Rosa\n\n";
        cout << "\t\t*** MENU PRINCIPAL ***" << endl;
        cout << "\n";
        cout << "\n\t\t1.- MENU DE ORDENACION\n";
        cout << "\n\t\t2.- MENU DE BUSQUEDA\n";
        cout << "\n\t\t0.- Salir\n";
        cout << "\n\t\t--------\n";
        cout << "\n\t\tElige Opcion: "; cin >> opcion;
        switch (opcion)
        {
        case 1:
        {
            system("cls");
            menu();
            break;
        }
        case 2:
        {
            system("cls");
            menu4();
            break;
        }

        case 0:
        {
            system("cls");
            cout << "\nGracias por usar el programa, hasta la vista" << endl;
            cout << endl;
            break;
        }
        default:
        {
            cout << "\nOpcion " << opcion << " no valida. "<<endl;
            cout << endl;
            break;
        }
        }
        system("pause");

    }while(opcion!=0);
    return 0;
}

int menu4()
{
    TestBusqueda Test;
    int opcion;
    do
    {
        system("cls");
        cout << "\n\t\t\t*** MENU DE BUSQUEDA ***" << endl;
        cout << "\n\t\t1.- Probar los metodos de busqueda\n";
        cout << "\n\t\t2.- Obtener el caso medio de un metodo de busqueda\n";
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
            Test.comprobarMetodosBusqueda();
            break;
        }
        case 2:
        {   menu5();
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
            cout << "\n\t\t\t 0.- Busqueda Secuencial Iterativa\n";
            cout << "\n\t\t\t 1.- Busqueda Binaria Recursiva\n";
            cout << "\n\t\t\t 2.- Busqueda Ternaria Recursiva\n";
            cout << "\n\t\t\t 3.- Salir\n";
            cout << "\n\t\t\t ----------\n";
            cout << "\n\tIntroduce el primer metodo a comparar: "; cin >> metodo1;
            cout << "\n\tIntroduce el segundo metodo a comparar: "; cin >> metodo2;
            if(metodo1==3 || metodo2==3){
            system("cls");
                cout << "\nVolviendo al menu de busqueda." << endl;
            }
            else{
            system("cls");
            Test.comparar(metodo1,metodo2);
                }
            }while(metodo1!=3 && metodo2!=3);
            break;
        }
        case 0:
        {
            system("cls");
            cout << "\nVolviendo al menu principal." << endl;
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

int menu5(){
    TestBusqueda test;
    int opc;
    do
    {
        system("cls");
        cout << "\n\t*** Metodo a estudiar para el caso medio ***\n";
        cout << "\n\t\t\t0.- Busqueda Secuencial Iterativa\n";
        cout << "\n\t\t\t1.- Busqueda Binaria Recursiva\n";
        cout << "\n\t\t\t2.- Busqueda Ternaria Recursiva\n";
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
            cout << "\nVolviendo al menu de busqueda." << endl;
            break;
        }
        default:
        {
            cout << "\nOpcion " << opc << " no valida. "<<endl;
            break;
        }
        }
        system("pause");
        }while(opc!=3);
        return 0;
}
