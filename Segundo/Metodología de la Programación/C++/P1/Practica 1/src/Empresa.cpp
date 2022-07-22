#include "Empresa.h"
#include <iostream>
#include <windows.h>
#include <typeinfo>

using namespace std;

typedef char cadena[100];

Empresa::Empresa():nmaxcli(100){
    this->ncli=0;
    this->ncon=0;
    this->nmaxcon=10;//en el enunciado se dice que al principio la tabla sera de 10 inicialmente por tanto hay que inicializar la variable con 10
    this->contratos=new Contrato*[10];
}

Empresa::~Empresa(){
    for(int i=0;i<this->ncon;i++){
        delete this->contratos[i];//con esto nos cargamos cada objeto
    }
        delete [] this->contratos;//con esto nos cargamos la tabla que contiene los punteros a los objetos

    for(int j=0;j<this->ncli;j++){
        delete this->clientes[j]; //nos cargamos los objetos de la tabla pero no podemos carganos la tabla porque es una tabla dinamica por tanto no la liberamos en memoria
    }
}

int Empresa::altaCliente(Cliente *c){
    int pos=-1;
    if(this->ncli<100){
        this->clientes[this->ncli]=c;
        pos=this->ncli;//Nos quedamos con la posicion donde se ha introducido al cliente
        this->ncli++;
    }
    else{
        std::cout << "Ha superado el numero maximo de clientes que pueden darse de alta, por tanto no se ha podido introducir" << endl;
    }
    return pos;//Devolvemos -1 si no se ha introducido y devolvemos la posicion donde se ha introducido si, es que se ha introducido
}

int Empresa::buscarCliente(long int dni) const{//devuelve 1 si el cliente ha sido encontrado, 0 si no se ha encontrado
    int i=0;
    int pos=-1;
    bool encontrado=false;
    while(i<this->ncli && encontrado==false){
        if(this->clientes[i]->getDni()==dni){
            encontrado=true;
            pos=i;
        }
        i++;
    }
    return pos;
}

void Empresa::crearContrato(){
    //Ampliamos la tabla siempre que este llena, para hacer mas corto el codigo de este metodo lo ponemos al principio para que se compruebe antes de meter un nuevo contrato
    if(this->ncon==this->nmaxcon){
    nmaxcon=nmaxcon+5;
    Contrato **aux;
    aux=new Contrato*[nmaxcon];
    for(int i=0;i<this->ncon;i++){
        aux[i]=this->contratos[i];
    }
    delete [] this->contratos;
    this->contratos=aux;
    }

    long int dni;
    std::cout << "Introduce el dni del contrato a crear: "; std::cin >> dni;
    int pos=this->buscarCliente(dni);
    if(pos==-1){//Si es igual a -1 es porque el cliente no existe, por tanto lo creamos
        int dia, mes, anio;
        cadena nombre;
        Cliente *c;
        cout << "El cliente no existe, vamos a crearlo" << endl;
        cout << "Introduce el Nombre: ";
        cin.ignore();
        cin.getline(nombre,100);
        cout << "Introduce la Fecha de alta del cliente (dd/mm/aaaa): " << endl << "Dia: "; cin >> dia; cout << "Mes: "; cin >> mes;  cout << "Anio: "; cin >> anio;
        c=new Cliente(dni, nombre, Fecha(dia, mes, anio));
        pos=this->altaCliente(c);//Tenemos que pasarle a pos la posicion donde se ha introducido a la hora de crear al cliente
                                 //ya que necesitamos saber si se ha creado o no el cliente
        }
        if(pos!=-1){//Si pos es distinto de -1 enonces el cliente existe y se procede a preguntarle los parametros para crear el contrato que este elija
        int tipocontrato, minutoshablados, d, m, a;
        cout << "Introduce los minutos hablados para el contrato: "; cin >> minutoshablados;
        cout << "Introduce la Fecha del contrato del cliente (dd/mm/aaaa): " << endl << "Dia: "; cin >> d; cout << "Mes: "; cin >> m;  cout << "Anio: "; cin >> a;
        cout << "Elige el tipo de contrato que deseas crear (1-Tarifa Plana, 2-Contrato Movil): ";
        cin >> tipocontrato;
            if(tipocontrato==1){//Introducimos los datos para este contrato
                contratos[ncon]=new ContratoTP(dni,Fecha(d,m,a),minutoshablados);
                ncon++;
            }
            else if(tipocontrato==2){
                cadena nacionalidad;
                float preciominuto;
                cout << "Introduce la nacionalidad del cliente: ";
                cin.ignore();
                cin.getline(nacionalidad,100);
                cout << "Introduce el precio por minuto del contrato: "; cin >> preciominuto;
                contratos[ncon]=new ContratoMovil(dni,Fecha(d,m,a),preciominuto,minutoshablados,nacionalidad);
                ncon++;
            }
            else{
                cout << "El contrato no ha podido ser creado" << endl;
        }
    }
}

bool Empresa::cancelarContrato(int idContrato){
    bool encontrado=false;
    int i=0;
    while(i<ncon && !encontrado){
        if(contratos[i]->getIdContrato()==idContrato){
            encontrado=true;
            delete this->contratos[i];
            contratos[i]=contratos[this->ncon-1];
            this->ncon--;
        }
        i++;
    }
    if(ncon<(nmaxcon/2)){
        nmaxcon=nmaxcon-(nmaxcon/2);
        Contrato **aux;
        aux=new Contrato*[nmaxcon];
        for(int i=0;i<this->ncon;i++){
            aux[i]=this->contratos[i];
    }
    delete [] contratos;
    this->contratos=aux;
    }
    return encontrado;
}

bool Empresa::bajaCliente(long int dni){
    int i=0, j=0;
    bool encontrado=false;
    while(i<ncli && !encontrado){
        if(clientes[i]->getDni()==dni){
            encontrado=true;
            while(j<ncon){
                if(contratos[j]->getDniContrato()==dni){
                    cancelarContrato(contratos[j]->getIdContrato());
                }
                else{
                    j++;
                }
            }
            delete this->clientes[i];
            clientes[i]=clientes[this->ncli-1];
            this->ncli--;
        }
        i++;
    }
    return encontrado;
}

int Empresa::descuento(float porcentaje) const{
    int afectados=0;
    for(int i=0;i<ncon;i++){
        if(ContratoMovil *c = dynamic_cast <ContratoMovil*> (contratos[i])){//Si el puntero ContratoMovil *c pertenece a ContratoMovil de la tabla contratos[i], entonces modifico o hago lo que quiera
            c->setPrecioMinuto(c->getPrecioMinuto()-((porcentaje/100)*c->getPrecioMinuto()));
            afectados++;
        }
    }
    return afectados;
}

int Empresa::nContratosTP() const{
    int conTP=0;
    for(int i=0;i<ncon;i++){
        if(typeid(*contratos[i])==typeid(ContratoTP)){
            conTP++;
        }
    }
    return conTP;
}

void Empresa::ver() const{
    cout << "La empresa tiene " << ncli << " clientes y " << ncon << " contratos" << endl;
    cout << "Clientes:" << endl;
    for(int i=0;i<this->ncli;i++){
        cout << *clientes[i] << endl;//Hay que hacerlo asi para que muestre el precio
    }
    cout << "\n" << "Contratos:" << endl;
    for(int j=0;j<this->ncon;j++){
        contratos[j]->ver(); cout << endl;
    }
}

void Empresa::cargarDatos(){
    clientes[ncli++] = new Cliente(75547001, "Peter Lee", Fecha(28, 2, 2001));
    clientes[ncli++] = new Cliente(45999000, "Juan Perez", Fecha(29, 2, 2000));
    clientes[ncli++] = new Cliente(37000017, "Luis Bono", Fecha(31, 1, 2002));

    contratos[ncon++] = new ContratoMovil(75547001, Fecha(28, 2, 2001), 0.12, 110, "DANES");
    contratos[ncon++] = new ContratoMovil(75547001, Fecha(31, 1, 2002), 0.09, 170, "DANES");
    contratos[ncon++] = new ContratoTP(37000017, Fecha(1, 2, 2002), 250);
    contratos[ncon++] = new ContratoTP(75547001, Fecha(28, 2, 2001), 312);
    contratos[ncon++] = new ContratoMovil(45999000, Fecha(31, 1, 2002), 0.10, 202, "ESPANOL");
    contratos[ncon++] = new ContratoMovil(75547001, Fecha(31, 1, 2002), 0.15, 80, "DANES");
    contratos[ncon++] = new ContratoTP(45999000, Fecha(1, 2, 2002), 400);
}

bool Empresa::cancelaContratoTP(){
    int c_cancelados=0;
    float precio=-1;
    for(int i=0;i<ncon;i++){
        if(ContratoTP *c = dynamic_cast <ContratoTP*> (contratos[i])){//Si el puntero ContratoMovil *c pertenece a ContratoMovil de la tabla contratos[i], entonces modifico o hago lo que quiera
            if(precio>=c->factura() || precio==-1){
                precio=c->factura();
            }
        }
    }
    if(precio!=-1){
    for(int j=0;j<ncon;j++){
        if(ContratoTP *c = dynamic_cast <ContratoTP*> (contratos[j])){
            if(precio==c->factura()){
                this->cancelarContrato(contratos[j]->getIdContrato());
                c_cancelados++;
                j--;
                }
            }
        }
    }
    if(c_cancelados!=0){
        return true;
    }
    else{
    return false;
    }
}

/*bool Empresa::cancelaContratoMovil(Fecha f){
    for(int i=0;i<ncon;i++){
        if(typeid(*contratos[i])==typeid(ContratoTP)){
            if(contratos[i]->getFechaContrato().getAnio()==f.getAnio() && contratos[i]->getFechaContrato().getMes()==f.getMes() && contratos[i]->getFechaContrato().getDia()==f.getDia()){
                cancelarContrato(contratos[i]->getIdContrato());
                return true;
            }
        }
    }
    return false;
}*/




