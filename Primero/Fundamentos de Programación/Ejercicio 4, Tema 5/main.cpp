#include <iostream>
#include <string.h>
using namespace std;
typedef char Cadena[50];//Tipo de datos Cadena
#define MAX_CUENTAS 10//N�mero de Cuentas
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
#define MAX_CLIENTES 100//N�mero de clientes
class Cliente
{
    Cadena Nombre; //Nombre y direcci�n
    Cadena Direccion;
    Cuenta Cuentas[MAX_CUENTAS];//cuentas corrientes
    int NoCuentas;//N� de cuentas abiertas

public:
    Cliente();/*Un constructor sin par�metros donde se inicialice las cadenas a vacio y el n�mero de cuentas a cero*/
    void ActualizarCliente(Cadena pNomb, Cadena pDir);/*M�todo que actualiza los atributos nombre y direcci�n con los par�metros
    pNomb y pDir respectivamente. El atributo n�mero de cuentas (NoCuentas) se inicializa a 0.*/
    void DameNombre(Cadena pNom);/*M�todo que devuelve el nombre del cliente mediante el par�metro pNom.*/
    void DameDireccion(Cadena pDir);/*M�todo que devuelve la direcci�n del cliente mediante el par�metro pDir.*/
    int BuscarCuenta(int pNoCuenta);/*M�todo que buscar� en todas las cuentas del cliente, aquella cuyo n�mero de cuenta coincide
    con el valor del par�metro pNoCuenta.*/
    bool CrearCuenta(Cuenta pCu);/*M�todo para crear una cuenta nueva al cliente siempre y cuando tenga espacio, en caso de no
    tenerlo el m�todo devuelve false. El objeto cuenta ya inicializado es pasado al m�todo mediante el par�metro pCu. El m�todo buscar�
    entre las cuentas del cliente aquella cuyo n�mero de cuenta coincida con el que posee la cuenta pasada por par�metro. Si no la
    encuentra, asignara el objeto pCu al final del vector de cuentas del cliente y devolver� true y en caso contrario el m�todo solo
    devolver� false.*/
    bool ActualizarCuenta(Cuenta pCu);/*M�todo que actualizar� la cuenta del cliente con los datos de la cuenta pasada por par�metro.
    Para ello, el m�todo buscar� aquella cuenta del cliente cuyo n�mero de cuenta coincida con el que posee el objeto cuenta pCu. Si
    la encuentra actualizar� la cuenta del cliente y devolver� true, en caso contario devolver� false.*/
    bool BorrarCuenta(int pNoCuenta);/*M�todo que elimina la cuenta del cliente cuyo n�mero de cuenta es pasado por par�metro. Si la
    encuentra el m�todo la eliminar� del vector de cuentas y devolver� true, en caso contrario devolver� false.*/
    int DameNoCuentas();/*M�todo que devuelve el n�mero de cuentas que posee el cliente.*/
    Cuenta DameCuenta(int pos);/*M�todo que devolver� la cuenta del cliente que est� en la posici�n que indica el par�metro pos*/
    void Mostrar(char Campo);/*M�todo que mostrar� el nombre y todas las cuentas del cliente seg�n indique el par�metro Campo. Si
    Campo contiene 's' o 't' mostrar� el nombre y la direcci�n del cliente. Si el Campo contiene 'c' o 't' mostrar� todas las cuentas
    del cliente.*/
};
int BuscarCliente(Cliente Ctes[MAX_CLIENTES], int NCtes, Cadena Nombre){/*Funci�n gen�rica que busca un cliente en el vector de
cliente Ctes cuyo nombre coincidida con el nombre pasado por par�metro Nombre. El  par�metro NCtes indica el n�mero de objetos cliente
que tienen informaci�n en el vector Ctes.Esta funci�n devolver� la posici�n del cliente encontrado o -1 si no lo encuentra.*/
int i=0;
Cadena pNom;
bool encontrado=false;
    while((i<NCtes)&&(!encontrado)){
            Ctes[i].DameNombre(pNom);
        if(strcmp(pNom,Nombre)==0)
            encontrado=true;
        else
            i++;
    }
    if(encontrado==false)
        i=-1;
    return i;
}
Cliente::Cliente(){
    strcpy(Nombre," ");
    strcpy(Direccion," ");
    NoCuentas=0;
}
void Cliente::ActualizarCliente(Cadena pNom, Cadena pDir){
    strcpy(Nombre,pNom);
    strcpy(Direccion,pDir);
    NoCuentas=0;
}
void Cliente::DameNombre(Cadena pNom){
    strcpy(pNom,Nombre);
}
void Cliente::DameDireccion(Cadena pDir){
    strcpy(pDir,Direccion);
}
int Cliente::BuscarCuenta(int pNoCuenta){
int i=0;
bool encontrado=false;
    while((i<NoCuentas)&&(!encontrado)){
        if(Cuentas[i].DameNoCuenta()==pNoCuenta)
            encontrado=true;
        else
            i++;
    }
    if(encontrado==false)
        i=-1;
    return i;
}
bool Cliente::CrearCuenta(Cuenta pCu){
bool creada;
int cuenta_nueva=pCu.DameNoCuenta();
    if(NoCuentas==MAX_CUENTAS || BuscarCuenta(cuenta_nueva)!=-1)
        creada=false;
        else{
            creada=true;
            Cuentas[NoCuentas]=pCu;
            NoCuentas++;
        }
    return creada;
}
bool Cliente::ActualizarCuenta(Cuenta pCu){
bool actualizada;
int actualizar_cuenta=pCu.DameNoCuenta();
int busca_cuenta=BuscarCuenta(actualizar_cuenta);
    if(busca_cuenta==-1)
        actualizada=false;
        else{
            actualizada=true;
            Cuentas[busca_cuenta]=pCu;
        }
    return actualizada;
}
bool Cliente::BorrarCuenta(int pNoCuenta){
bool borrada;
int cuenta_buscar=BuscarCuenta(pNoCuenta);
    if(cuenta_buscar==-1)
        borrada=false;
        else{
            for(int i=cuenta_buscar+1;i<NoCuentas;i++){
                Cuentas[i-1]=Cuentas[i];
                borrada=true;
            }
            NoCuentas--;
        }
    return borrada;
}
int Cliente::DameNoCuentas(){
    return NoCuentas;
}
Cuenta Cliente::DameCuenta(int pos){
Cuenta a;
    a=Cuentas[pos];
    return a;
}
void Cliente::Mostrar(char Campo){
    if(Campo=='s' || Campo=='t'){
        cout << "Nombre del cliente: " << Nombre << "\n";
        cout << "Direccion del cliente: " << Direccion << "\n";
    }
    if(Campo=='c' || Campo=='t'){
        for(int i=0;i<NoCuentas;i++){
            cout << "Numero de cuenta: " << Cuentas[i].DameNoCuenta() << "\n";
            cout << "Saldo de la cuenta: " << Cuentas[i].DameSaldo() << "\n";
            cout << "Bloqueo: " << Cuentas[i].EstaBloqueada() << "\n";
        }
    }
}
int Menu(){/*Funci�n gen�rica que mostrar� el siguiente men� y devolver� la opci�n seleccionada.*/
int opcion;
    cout << "\nMenu principal";
    cout << "\n1. Introducir un cliente.";
    cout << "\n2. Actualizar la direccion del cliente.";
    cout << "\n3. Mostrar un cliente.";
    cout << "\n4. Mostrar todos lo clientes.";
    cout << "\n5. Submenu gestion de cuentas.";
    cout << "\n6. Salir.";
    cout << "\nElige una opcion: "; cin >> opcion;
    system("cls");
    return opcion;
}
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
int main()
{
Cliente Datos[MAX_CLIENTES];
int nClientes=0;
Cliente c;
Cadena Nombre, Direccion;
int opc, opc2, busca;
    do{
        opc=Menu();
        switch(opc)
        {
        case 1: cout << "Introduce el nombre del nuevo cliente: "; cin >> Nombre;
                cout << "Introduce la direccion del nuevo cliente: "; cin >> Direccion;
                c.ActualizarCliente(Nombre,Direccion);
                Datos[nClientes]=c;
                nClientes++;
            break;
        case 2: cout << "Introduce el nombre del cliente a actualizar: "; cin >> Nombre;
                busca=BuscarCliente(Datos,nClientes,Nombre);
                if(busca!=-1){
                    cout << "Introduzca la direccion del cliente a actualizar: "; cin >> Direccion;
                    Datos[busca].ActualizarCliente(Nombre,Direccion);
                }
                else
                    cout << "El cliente no se ha encontrado.";
            break;
        case 3: cout << "Introduce el nombre del cliente a mostrar: "; cin >> Nombre;
                busca=BuscarCliente(Datos,nClientes,Nombre);
                if(busca!=-1)
                    Datos[busca].Mostrar('t');
                else
                    cout << "El cliente no se ha encontrado.\n";
            break;
        case 4: for(int i=0;i<nClientes;i++)
                Datos[i].Mostrar('t');
            break;
        case 5: cout << "Introduce el nombre de la cuenta a ser gestionada: "; cin >> Nombre;
                busca=BuscarCliente(Datos,nClientes,Nombre);
                if(busca!=-1){
                    int numero_cuenta;
                    float saldo_cuenta;
                    do{
                        opc2=MenuCuentas();
                        switch(opc2)
                        {
                        case 1:{cout << "Introduzca el numero de la cuenta a crear: "; cin >> numero_cuenta;
                                cout << "Introduce el saldo de la cuenta: "; cin >> saldo_cuenta;
                                Cuenta cu(numero_cuenta,saldo_cuenta);
                                if(Datos[busca].CrearCuenta(cu)==true)
                                    cout << "Su cuenta se ha creado correctamente.";
                                    else
                                        cout << "Su cuenta no se ha podido crear.";
                        }
                            break;
                        case 2:{cout << "Cuentas del cliente: ";
                                Datos[busca].Mostrar('c');
                        }
                            break;
                        case 3:{cout << "Introduzca el numero de cuenta a ser eliminado: "; cin >> numero_cuenta;
                                if(Datos[busca].BorrarCuenta(numero_cuenta)==true)
                                    cout << "Su cuenta se ha eliminado";
                                    else
                                        cout << "Su cuenta no se ha podido eliminar";
                        }
                            break;
                        case 4:{cout << "Introduzca el numero de cuenta a ser actualizada: "; cin >> numero_cuenta;
                                cout << "Introduzca el saldo de la cuenta a ser actualizado: "; cin >> saldo_cuenta;
                                Cuenta cu(numero_cuenta,saldo_cuenta);
                                if(Datos[busca].ActualizarCuenta(cu)==true)
                                    cout << "La cuenta se ha actualizado correctamente.";
                                    else
                                        cout << "La cuenta no se ha podido actualizar.";
                        }
                            break;
                        case 5:/*NO ENTRA*/
                            break;
                        case 6:  cout << "Hasta pronto.";
                            break;
                        default: cout << "Opcion incorrecta, elige una opcion valida.";
                            break;
                        }
                    }while(opc2!=6);
                }
                else
                    cout << "El cliente a gestionar no se encuentra.";
            break;
        case 6:  cout << "\nGracias por usar el programa, hasta pronto.\n";
            break;
        default: cout << "\nOpcion incorrecta, elige una opcion valida.\n";
            break;
        }
        system("pause");
    }while(opc!=6);
    return 0;
}
