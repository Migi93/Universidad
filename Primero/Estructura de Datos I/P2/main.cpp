#include <iostream>
#include <peluqueria.h>
#include <cstdlib>
#include <cola.h>
#include <string>
#include <fstream>
#include <iomanip>

using namespace std;

int menu(){
    int opc;
    system("cls");
    cout << "\n\tPractica 2: E.D.I" << endl;
    cout << "\n\t\tAlum: Miguel A. Sanchez De La Rosa" << endl;
    cout << "\n\tPeluqueria de corte 2.0 Huelva" << endl
    << "\t_____________________________________________________" << endl
    << "\t1 - Leer fichero (rescatar copia)" << endl
    << "\t2 - Insertar peluquero" << endl
    << "\t3 - Insertar cliente" << endl
    << "\t4 - Retirar peluquero" << endl
    << "\t5 - Atender cliente" << endl
    << "\t6 - Mostrar peluqueria" << endl
    << "\t7 - Eliminar un cliente" << endl
    << "\t8 - Volcar a fichero (crear copia)" << endl
    << "\t9 - Crear peluquero para clientes de una edad concreta" << endl
    << "\t10 - Salir" << endl
    << "\t-----------" << endl
    << "\tEscoge una opcion: "; cin >> opc;
    system("cls");
    return opc;
}

int main()
{
    cliente c;
    peluqueria p;
    peluquero t;
    int m;
    do{
        m=menu();
        switch(m){
        case 1:{
            char inicial_dat[30];
            strcpy(inicial_dat,"inicial.dat");
            p.AbrirPeluqueria(inicial_dat);
        }
            break;
        case 2:{
            cin.ignore();
            cout << "Introduce el Nombre del peluquero a incorporar: "; cin.getline(t.Nombre, sizeof(cadena));
            cout << "Introduce el Apellido del peluquero a incorporar: "; cin.getline(t.Apellidos, sizeof(cadena));
            cout << "Introduce el codigo del peluquero a incorporar: "; cin >> t.Codigo;
            cout << "Introduce el Tipo de servicio del peluquero a incorporar: "; cin >> t.TipoServicio;
            system("cls");
            p.IncorporarPeluquero(t);
        }
            break;
        case 3:{
            int hora;
            int minutos;
            cin.ignore();
            cout << "Introduce el Nombre del cliente: "; cin.getline(c.Nombre, sizeof(cadena));
            cout << "Introduce el Apellido del cliente: "; cin.getline(c.Apellidos, sizeof(cadena));
            cout << "Introduce la edad del cliente: "; cin >> c.Edad;
            cout << "Introduce el tipo de servicio que desea recibir el cliente: "; cin >> c.TipoServicio;
            cout << "Introduce la hora de llegada del cliente: "; cin >> hora;
            cout << "Introduce los minutos de la hora de llegada del cliente: "; cin >> minutos;
            c.HoraLlegada=((hora*60)+minutos);
            if(p.IncorporarCliente(c)==true){
                cout << "\nEl cliente ha sido incorporado a la cola.\n" << endl;
            }
            else{
                cout << "\nEl cliente no ha podido ser incorporado a la cola.\n" << endl;
            }
        }
            break;
        case 4:{
            cout<<"Introduce codigo de peluquero: "; cin>>m;
                if(p.RetirarPeluquero(m)==false){
                    cout<<"\nEl peluquero no ha podido ser retirado de la cola por ser el ultimo que queda o porque el codigo introducido no conincide con ningun peluquero.\n" << endl;
                } else {
                    cout<<"\nEl peluquero ha sido retirado de la lista.\n" << endl;
                }
        }
            break;
        case 5:{
            int codigo_peluquero;
            cout << "Introduce el codigo del peluquero para retirar al cliente que va a ser atendido: "; cin >> codigo_peluquero;
            if(p.AtenderCliente(codigo_peluquero)==true){
                cout << "\nCliente atendido.\n" << endl;
            }
            else{
                cout << "\nEl cliente no ha podido ser atendido.\n" << endl;
            }
        }
            break;
        case 6:{
            p.Mostrar();
        }
            break;
        case 7:{
            cadena nombre, apellidos;
            cin.ignore();//Para que no lea basura en el cin por si anteriormente queda algo en el buffer
            cout << "\tNombre del cliente: "; cin.getline(nombre, sizeof(cadena));
            cout << "\tApellidos del cliente: "; cin.getline(apellidos, sizeof(cadena));
            if(p.EliminarCliente(nombre,apellidos)==true){
                cout << "\nEl cliente ha sido eliminado de la cola." << endl;
            }
            else{
                cout << "\nEl cliente no ha podido ser eliminado de la cola." << endl;
            }
        }
            break;
        case 8:{
            char nombrefichero[30];
            strcpy(nombrefichero,"inicial.dat");
            p.VolcarPeluqueria(nombrefichero);
        }
            break;
        case 9:{
            cin.ignore();
            cout << "Introduce el Nombre del peluquero a incorporar: "; cin.getline(t.Nombre, sizeof(cadena));
            cout << "Introduce el Apellido del peluquero a incorporar: "; cin.getline(t.Apellidos, sizeof(cadena));
            cout << "Introduce el codigo del peluquero a incorporar: "; cin >> t.Codigo;
            cout << "Introduce el Tipo de servicio del peluquero a incorporar: "; cin >> t.TipoServicio;
            cout << "Edad de los clientes que va a atender este peluquero: " ; cin >> m;
            system("cls");
            p.ejerciciounomodificacion(t,m);
        }
            break;
        case 10:{
            cout << "Gracias por usar el programa, hasta la vista" << endl;
        }
            break;
        default:{
            cout << "Opcion incorrecta, escoja una opcion valida." << endl;
        }
            break;
        }
        system("pause");
    }while(m!=9);
    return 0;
}

