#include <iostream>
#include "TestAlgoritmo.h"
#include "ConjuntosInt.h"
#include "Mtime.h"
#include "Constantes.h"

using namespace std;

int menu ();
int menu1 ();
int menu2 ();
int menu3 ();
int menu4 ();

////////////// Programa principal //////////////

int main ()
{
    menu();		//muestra las opciones del programa
    cout <<endl<<"Fin Programa"<<endl<<endl;
    system ("pause");
    return 0;
}

///////////// Definición función Menú////////////
int menu()
{
    int opcion;
    do
    {
        system("cls");
        cout << "*** FAA. Practica 1. Curso 18/19 ***\n";
        cout << "\n";
        cout<<"\t\t\t\t\t\tAlum: Miguel A. Sanchez De La Rosa\n\n";
        cout << "*** ESTUDIO DE LA COMPLEJIDAD DEL ALGORITMO BUSQUEDA SECUENCIAL ***\n";
        cout << "\n";
        cout << "\t1. ESTUDIO TEORICO\n";
        cout << "\t2. ESTUDIO EMPIRICO\n";
        cout << "\t0. Salir\n";
        cout << "---------\n";
        cout << "Elige Opcion: ";
        cin >> opcion;
        switch (opcion)
        {
        case 1:
        {
            menu1();
            break;
        }
        case 2:
        {
            menu2();
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

int menu1()
{
    int opcion;
    TestAlgoritmo test;
    do
    {
        system("cls");
        cout << "*** MENU TEORICO DEL ALGORITMO DE BUSQUEDA SECUENCIAL ***";
        cout << "\n";
        cout << "\t1. Probar el algoritmo busqueda secuencial\n";
        cout << "\t2. Obtener los casos del metodo de busqueda secuencial\n";
        cout << "\t3. Comparar los casos\n";
        cout << "\t0. Volver al menu principal\n";
        cout << "---------\n";
        cout << "Elige Opcion: ";
        cin >> opcion;
        switch (opcion)
        {
        case 1:
        {
            test.comprobarAlgoritmo();
            break;
        }
        case 2:
        {
	     menu3();
            break;
        }
        case 3:
        {
            test.compararTeorico(0,1,2);
            break;
        }
        default:
        {
            cout << "Opcion " << opcion << " no valida. "<<endl;
            break;
        }
        }
        system ("pause");
    }while(opcion!=0);
    return opcion;
}

int menu2()
{
    int opcion;
    TestAlgoritmo test;
    do
    {
        system("cls");
        cout << "*** MENU EMPIRICO DEL ALGORITMO DE BUSQUEDA SECUENCIAL ***";
        cout << "\n";
        cout << "\t1. Probar el algoritmo busqueda secuencial\n";
        cout << "\t2. Obtener los casos del metodo de busqueda secuencial\n";
        cout << "\t3. Comparar los casos\n";
        cout << "\t0. Volver al menu principal\n";
        cout << "---------\n";
        cout << "Elige Opcion: ";
        cin >> opcion;
        switch (opcion)
        {
            break;
        case 1:
        {
            test.comprobarAlgoritmo2();
            break;
        }
        case 2:
        {
            menu4();
            break;
        }
        case 3:
        {
            test.compararEmpirico(0,1,2);
            break;
        }
        default:
        {
            cout << "Opcion " << opcion << " no valida. "<<endl;

        }
        }
    }while (opcion !=0);
        system ("pause");
    return opcion;
}

int menu3()
{
    int opcion=1;
    TestAlgoritmo test;
    do{
    system("cls");
    cout << "*** CASO A ESTUDIAR PARA LA BUSQUEDA SECUENCIAL ***";
    cout << "\n";
    cout << "\t0. Caso peor\n";
    cout << "\t1. Caso medio\n";
    cout << "\t2. Caso mejor\n";
    cout << "\t3. Volver al menu anterior\n";
    cout << "---------\n";
    cout << "Elige Opcion: ";
    cin >> opcion;
    switch (opcion)
    {
    case 0:
        test.costeCasoTeorico(0);
        break;
    case 1:
    {
        test.costeCasoTeorico(1);
        break;
    }
    case 2:
    {
        test.costeCasoTeorico(2);

        break;
    }
    default:
    {
        cout << "Opcion " << opcion << " no valida. "<<endl;
    }
        }
    system ("pause");
    }while(opcion!=3);
    return opcion;
}

int menu4()
{
    int opcion;
    TestAlgoritmo test;
    system("cls");
    do{
    cout << "*** CASO A ESTUDIAR PARA LA BUSQUEDA SECUENCIAL***";
    cout << "\n";
    cout << "\t0. Caso peor\n";
    cout << "\t1. Caso medio\n";
    cout << "\t2. Caso mejor\n";
    cout << "\t3. Volver al menu anterior\n";
    cout << "---------\n";
    cout << "Elige Opcion: ";
    cin >> opcion;
    switch (opcion)
    {
    case 0:
        test.costeCasoEmpirico(0);
        break;
    case 1:
    {
        test.costeCasoEmpirico(1);
        break;
    }
    case 2:
    {
        test.costeCasoEmpirico(2);
        break;
    }
    default:
    {
        cout << "Opcion " << opcion << " no valida. "<<endl;
        break;
    }
    }
    system ("pause");
    }while(opcion!=3);
    return opcion;
}
