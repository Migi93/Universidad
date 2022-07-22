#include <iostream>
#include <string.h>
using namespace std;
typedef char Cadena[50];//Tipo de datos Cadena
#define MAX_CUENTAS 100//N�mero de Cuentas
class Cuenta//Contiene los datos de una cuenta bancaria
{
    float Saldo;//Saldo de la cuenta
    int NoCuenta;//N�mero de la cuenta
    bool Bloqueada;//true si est� bloqueada

public:
    Cuenta();/*Un constructor sin  par�metros  donde  se  inicialice  el  saldo,  y  n�mero  de  cuenta  a  0  y bloqueada a false.*/
    Cuenta(int pNo, float pSal);/*Un constructor parametrizado que inicializar� los atributos de la clase con el valor de los
    par�metros, pNo(n�mero  de  cuenta) y pSal(saldo inicial de la cuenta). El constructor inicializar� el atributo bloqueada a false.*/
    bool ActualizarSaldo(int pSal);/*Este m�todo actualizar� el atributo Saldo con el valor  del  par�metro pSal siempre y cuando la
    cuenta no est� bloqueada. El m�todo devuelve true si se ha actualizado el saldo y false en caso contrario.*/
    void ActualizarBloqueo(bool pBloq);/*Este m�todo actualizar� el atributo Bloqueada con el valor del par�metro pBloq.*/
    float DameSaldo();/*M�todo que devuelve el atributo Saldo de la cuenta.*/
    int DameNoCuenta();/*M�todo que devuelve el n�mero cuenta (NoCuenta).*/
    bool EstaBloqueada();/*M�todo  que  devuelve true si  la  cuenta  est�  bloqueada, false en caso contrario.*/
};
int MenuCuentas(){/*funci�n gen�rica que mostrar� el siguiente men� y devolver� la opci�n seleccionada. Las opciones del men� son las
siguientes:(Mirar pdf para ver cual es el menu)*/
    int opcion;
    cout << "\n Menu gestion de cuentas";
    cout << "\n1. Crear cuenta para un cliente.";
    cout << "\n2. Mostrar las cuentas del cliente.";
    cout << "\n3. Borrar una cuenta del cliente.";
    cout << "\n4. Modificar saldo de una cuenta.";
    cout << "\n5. Modificar estado de una cuenta.";
    cout << "\n6. Salir.";
    cout << "\nElige una opcion: "; cin >> opcion;
    system("cls");
    return opcion;
    }
int BuscarCuenta(Cuenta Ctas[MAX_CUENTAS], int NCuentas, int NoCuenta){/*funci�n gen�rica que recibe un vector de cuentas (Ctas),
cuantas cuentas est�n utilizandose (NCuentas) y el n�mero de cuenta a buscar (NoCuenta).Esta funci�n devuelve la posici�n dentro del
vector de cuentas que contiene el n�mero de cuenta especificado por par�metro. Si no existe ninguna cuenta en el vector con ese
n�mero de cuenta devolver� -1.*/
int i=0;
bool encontrado=false;
    while((i<NCuentas)&&(!encontrado)){
        if(Ctas[i].DameNoCuenta()==NoCuenta)
            encontrado=true;
        else
            i++;
    }
    if(encontrado==false)
        i=-1;
return i;
}
Cuenta::Cuenta(){
    Saldo=0;
    NoCuenta=0;
    Bloqueada=false;
}
Cuenta::Cuenta(int pNo, float pSal){
    Saldo=pSal;
    NoCuenta=pNo;
    Bloqueada=false;
}
bool Cuenta::ActualizarSaldo(int pSal){
bool actualizado=false;
    if(Bloqueada==false){
        actualizado=true;
        Saldo=pSal;
    }
    return actualizado;
}
void Cuenta::ActualizarBloqueo(bool pBloq){
    Bloqueada=pBloq;
}
float Cuenta::DameSaldo(){
    return Saldo;
}
int Cuenta::DameNoCuenta(){
    return NoCuenta;
}
bool Cuenta::EstaBloqueada(){
    return Bloqueada;
}
int main(){
Cuenta DatosCuentas[MAX_CUENTAS];
int nCuentas=0;
Cuenta c;
int menu, dato_cuenta,numero_cuenta;
float nuevo_saldo;
char bloqueo;
    do{
        menu=MenuCuentas();
        switch(menu)
        {
            case 1: if(nCuentas!=MAX_CUENTAS){
                        cout << "Introduzca el nuevo numero de la cuenta: "; cin >> numero_cuenta;
                        dato_cuenta=BuscarCuenta(DatosCuentas,nCuentas,numero_cuenta);
                        if(dato_cuenta==-1){
                            cout << "Introduzca el saldo de la cuenta: "; cin >> nuevo_saldo;
                            DatosCuentas[nCuentas]=Cuenta(numero_cuenta,nuevo_saldo);
                            nCuentas++;
                        }
                            else
                                cout << "La cuenta ya existe.";
                        }
                        else
                            cout << "No hay suficiente espacio.";
                break;
            case 2: cout << "Datos de las cuentas.";
                    for(int i=0;i<nCuentas;i++){
                        cout << "\n" << i+1 << ":";
                        cout << "\nNumero de cuenta: " <<DatosCuentas[i].DameNoCuenta();
                        cout << "\nSaldo: " << DatosCuentas[i].DameSaldo();
                        cout << "\nBloqueo: " << DatosCuentas[i].EstaBloqueada();
                        cout << " ";
                    }
                break;
            case 3: cout << "Introduzca el numero de la cuenta a ser eliminada: "; cin >> numero_cuenta;
                    dato_cuenta=BuscarCuenta(DatosCuentas,nCuentas,numero_cuenta);
                    if(dato_cuenta!=-1){
                        for(int i=dato_cuenta+1;i<nCuentas;i++){
                            DatosCuentas[i-1]=DatosCuentas[i];
                        }
                        nCuentas--;
                        cout << "Su cuenta ha sido borrada correctamente.";
                    }
                    else
                        cout << "La cuenta que quiere eliminar no aparece registrada.";
                break;
            case 4: cout << "Introduce el numero de cuenta a ser actualizada: "; cin >> numero_cuenta;
                    dato_cuenta=BuscarCuenta(DatosCuentas,nCuentas,numero_cuenta);
                    if(dato_cuenta!=-1){
                        cout << "Indique el saldo de la nueva cuenta: "; cin >> nuevo_saldo;
                        if(DatosCuentas[dato_cuenta].EstaBloqueada()==true)
                            cout << "La cuenta no se ha podido actualizar porque esta bloqueada.";
                            else
                                DatosCuentas[dato_cuenta].ActualizarSaldo(nuevo_saldo);
                    }
                    else
                        cout << "Su numero de cuenta no aparece.";
                break;
            case 5: cout << "Introduzca el numero de cuenta a ser actualizada: "; cin >> numero_cuenta;
                    dato_cuenta=BuscarCuenta(DatosCuentas,nCuentas,numero_cuenta);
                    if(dato_cuenta!=-1){
                        cout << "Escriba una 's' para bloquearla o una 'n' para no bloquearla: "; cin >> bloqueo;
                        if(bloqueo=='s')
                            DatosCuentas[dato_cuenta].ActualizarBloqueo(true);
                        else
                            DatosCuentas[dato_cuenta].ActualizarBloqueo(false);
                    }
                    else
                        cout << "La cuenta no existe.";
                break;
            case 6:  cout << "Hasta pronto.";
                break;
            default: cout << "Opcion incorrecta, elige una opcion valida.";
                break;
        }
    }while(menu!=6);
    return 0;
}
