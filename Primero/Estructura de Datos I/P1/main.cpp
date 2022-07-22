#include <iostream>
#include "productos.h"
#include <cstdlib>
#include <string.h>

using namespace std;
int menu(){
    int opc;
    cout << "\n\t\tPractica 1  Miguel A. Sanchez De La Rosa" << endl;
    cout << "\n\t\t\t\tMENU"
     << "\n\t\t\t1. Ver fichero de ventas" << "\n"
     << "\t\t\t2. Ver fichero de productos" << "\n"
     << "\t\t\t3. Introducir venta" << "\n"
     << "\t\t\t4. Crear resumen de venta" << "\n"
     << "\t\t\t5. Obtener estadisticas (Tipo producto y periodo)" << "\n"
     << "\t\t\t6. Mostrar el numero de ventas por tipo de producto" << "\n"
     << "\t\t\t7. Corregir error de las ventas" << "\n"
     << "\t\t\t0. Salir" << "\n"
     << "\t\t\t--------" << "\n"
     << "\t\t\tIntroduce una opcion: "; cin >> opc;
     system("cls");
     return opc;
}
int main()
{
    char rm[20];
    char vt[20];
    strcpy(rm,"resumen.dat");
    strcpy(vt,"ventas.dat");
    ventas v;
    productos p(rm,vt);
    int m;
    do {
        m=menu();
        switch(m){

    case 1: {p.mostrarventas();
    }
        break;
    case 2: {p.mostrarproductos();
    }
        break;
    case 3: {if(p.anadirventa()==true){
        cout << "Su producto ha sido vendido." << endl;
        }
            else{
                    cout << "No se ha podido vender el producto." << endl;
            }
    }
        break;
    case 4:{p.actualizarresumen();
    }
        break;
    case 5:{int tipo,annoini,annofin;
        cout << "Introduzca el tipo de producto que quieres resumir(1. Electronica, 2. Papeleria, 3. Otros): "; cin >> tipo;
        cout << "Introduce el anno de inicio(incluido en la estadistica): "; cin >> annoini;
        cout << "Introduce el anno final(incluido en la estadistica): "; cin >> annofin;
        p.obtenerestadisticas(tipo,annoini,annofin);
    }
        break;
    case 6:{
        p.obtenernumeroventaportip();
    }
        break;
        case 7:{
            int codigoproducto,annoini,annofin;
            float importe;
            cout << "Introduzca el codigo de produco de la venta que se quiere modificar: "; cin >> codigoproducto;
            cout << "Introduce el anno de inicio(incluido): "; cin >> annoini;
            cout << "Introduce el anno final(incluido): "; cin >> annofin;
            cout << "Introduce el porcentaje del que se quiere aumentar la venta: "; cin >> importe;
            p.corregirventasderror(codigoproducto, annoini, annofin, importe);
        }
        break;
    case 0: cout << "Gracias por usar el programa, hasta la vista." << endl;
        break;
    default: cout << "opcion incorrecta, elige una opcion correcta." << endl;
        break;
    }
    system("pause");
    system("cls");
    }while (m!=0);
    return 0;
}
//fflush(stdin);
