#include <iostream>
#include <string.h>
using namespace std;
    typedef char cad[20];
class tprod {
    cad nombre;
    float precio;
    int stock;
public:
    tprod();/*Un constructor para esta clase que ponga en el nombre “NO HAY PRODUCTO”, en el precio un 0, y en el stock un 0.*/
    void cambiarnombre(cad nom);/*Este método recibe nom como parámetro y lo copia en el atributo nombre.*/
    void cambiarprecio(float prec);/*Recibe prec como parámetro y lo copia en el atributo precio.*/
    void cambiarstock(int stoc);/*Recibe stoc como parámetro y lo copia en el atributo stock.*/
    void leenombre (cad nom);/*Devuelve en el parámetro nom el contenido del atributo nombre.*/
    float leeprecio ();/*Devuelve el contenido del atributo precio.*/
    void leestock(int &st);/*Devuelve en el parámetro st el contenido del atributo stock.*/
    void vender(int cantidad);/*Simula la venta del producto, quitando del stock la cantidad cant pasada como parámetro,
    además mostrará por pantalla el precio a cobrar que será el producto del precio unitario por la cantidad vendida.
    Si no hubiera en el stock suficiente cantidad mostrará un mensaje por pantalla expresando dicha circunstancia no
    realizándose la venta.*/
};
tprod::tprod()
{
    strcpy(nombre,"No hay productos.");
    precio = 0;
    stock = 0;
}
void tprod::cambiarnombre(cad nom)
{
    strcpy(nombre, nom);
}
void tprod::cambiarprecio(float prec)
{
    precio = prec;
}
void tprod::cambiarstock(int stoc)
{
    stock = stoc;
}
void tprod::leenombre(cad nom)
{
    strcpy(nom, nombre);
}
float tprod::leeprecio()
{
    return precio;
}
void tprod::leestock(int &st)
{
    st = stock;
}
void tprod::vender(int cantidad)
{
    if(cantidad>stock)
        cout << "No hay stock suficiente, actualmente solo quedan " << stock << "Unidades";
        else{
                stock = stock - cantidad;
            cout << "El precio total a pagar es: " << cantidad*precio << " Euros";
        }
}
/*int main()
{
    tprod sol;
    cad nom;
    int st, stoc, cant;
    float prec;
    sol.leenombre(nom);
    cout << "Producto: " << nom << "\n";
    cout << "Precio del producto: " << sol.leeprecio() << " Euros";
    sol.leestock(st);
    cout << "\nStock disponible: " << st << " Unidades";
    cout << "\n";
    sol.cambiarnombre(nom);
    cout << "\nIntroduzca el nombre del nuevo producto: "; cin >> nom;
    sol.cambiarprecio(prec);
    cout << "Introduzca un precio para el producto: "; cin >> prec;
    sol.cambiarstock(stoc);
    cout << "Introduzca la cantidad que esta disponible para vender:"; cin >> stoc;
    cout << "\n";
    sol.cambiarnombre(nom);
    cout << "Producto: " << nom;
    sol.cambiarprecio(prec);
    cout << "\nPrecio del producto: " << prec << " Euros";
    sol.cambiarstock(stoc);
    cout << "\nStock disponible: " << stoc << " Unidades";
    cout << "\n";
    cout << "\nIntroduca la cantidad deseada a comprar: "; cin >> cant;
    sol.vender(cant);
    cout << "\n";
    sol.leestock(st);
    cout << "Stock despues de la venta: " << st << " Unidades";
    return 0;}*/

#define  MAX  5
class almacen {

    tprod productos[MAX];
    int nprod;

public:

    almacen();/*Constructor que pondrá el almacén vacío.*/
    void vaciar();/*Método que pondrá el almacén vacío.*/
    int  existe(cad  nom);/*Método  que  recibiendo  el  nombre  de  un  producto  como  parámetro devolverá  en
    qué  posición  de  la  tabla  se  encuentra  almacenado  un  producto  con  ese nombre o bien -1 si no está.*/
    void  verprod  (int  pos,  tprod    &prod);/*Método  que  pondrá  en  prod  el  contenido  del producto que se
    encuentra en la posición pos de la tabla de productos.*/
    int insertar(tprod P);/*Método  que  intentará  insertar  un  nuevo  producto  P  pasado  como parámetro  en  la
    tabla  de  productos.  Si  la  tabla  está  llena  devolverá  un  2  y  si  ya  hay  un producto con el mismo nombre
    devolverá un 1 no insertando en ninguno de estos casos el producto. Si lo ha podido insertar devolverá un 0.*/
    void  vertabla  ();/*Método  que  visualizará  por  pantalla  el  contenido  del  almacén.  Cada producto  deberá  mostrarse
    en  una  línea  diferente  con  su  nombre,  precio  y  stock.  Si  el almacén está vacío expresará esta situación por pantalla.*/
    void  vender  (int  pos,  int cant);/*Método  que recibiendo  como  parámetros  pos  y  cantidad intentará  realizar  la
    venta  del  producto  que  está  en  la  posición  pos  de  la  tabla  y  una cantidad cant de ese producto.*/
};
    //Diseñe una función genérica:
    char menu ();/*Método que mostrará el siguiente menú(En el boletin de moodle)*/

almacen::almacen()
{
    nprod=0;
}
void almacen::vaciar()
{
    nprod=0;
}
int almacen::existe(cad nom)
{
    int encontrado=0;
    cad n;
    int i=0;
    while((i<nprod)&&(encontrado==0)){
            productos[i].leenombre(n);
        if(strcmp(nom,n)==0)
        encontrado=1;
        else
            i++;
    }
    if(encontrado==0)
        i=-1;
    return i;
}
void almacen::verprod(int pos, tprod &prod)
{
    prod=productos[pos];
}
int almacen::insertar(tprod P)
{
    int valor, lugar;
    cad nom;
    if(nprod==MAX)
        valor=2;
        else{
            P.leenombre(nom);
            lugar=existe(nom);/**existe(nom) es el metodo que te da -1 si el producto no esta, por tanto, declaramos
            una variable cualquiera(en este caso lugar),lo igualamos, y asi podemos compararlo con el -1, que es el valor
            que debe darnos cuando no existe un producto con el mismo nombre, por tanto si es distinto a -1 existe un
            producto con ese nombre lo cual no podra insertarse el nuevo producto**/
            if(lugar!=-1){
                valor=1;
            }
            else{
                productos[nprod]=P;
                nprod++;
                valor=0;
            }
        }
    return valor;
}
void almacen::vertabla()
{
    tprod p;
    cad nom;
    float prec;
    int st;
    cout << "**** Contenido del almacen ****\n";
    if(nprod==0)
        cout << "El almacen esta vacio.\n";
        else{
                cout << "Numero " << "Nombre " << "Precio " << "Stock\n";
    for(int i=0;i<nprod;i++){
        cout << i+1 << " ";
        verprod(i,p);
        p.leenombre(nom);
        cout << nom << " ";
        prec=p.leeprecio();
        cout << prec << " ";
        p.leestock(st);
        cout << st << "\n";
        }
    }
}
void almacen::vender(int pos, int cant)
{
    productos[pos].vender(cant);
}
char menu(){
char opc;
    cout << "MENU\n";
    cout << "A. Visualizar la tabla.\n";
    cout << "B. Insertar un producto\n";
    cout << "C. Vender un producto\n";
    cout << "D. Vaciar el almacen\n";
    cout << "E. Salir\n";
    cout << "Escoja una opcion: "; cin >> opc;
    system("cls");
return opc;
}
int main(){
almacen a;
tprod p;
cad nom;
int cant, pos, sto;
float prec;
char menuu;
do{
    menuu=menu();
    switch(menuu)
    {
    case 'a':
    case 'A': a.vertabla();
        break;
    case 'b':
    case 'B': cout << "Pon el nombre del producto: "; cin >> nom;
              p.cambiarnombre(nom);
              cout << "Pon el precio del producto: "; cin >> prec;
              p.cambiarprecio(prec);
              cout << "Pon el stock del producto: "; cin >> sto;
              p.cambiarstock(sto);
              pos=a.insertar(p);
              if(pos==2)
                cout << "El almacen esta lleno, no se puede insertar mas productos.\n";
                else if(pos==1)
                cout << "Ya existe un producto con ese nombre.\n";
                else
                cout << "El producto se ha insertado\n";
        break;
    case 'c':
    case 'C': cout << "Pon el nombre del producto: "; cin >> nom;
            pos=a.existe(nom);
            if(pos==-1)
                cout << "El producto no existe\n";
                else{
                    cout << "Cantidad a vender:"; cin >> cant;
                    a.vender(pos, cant);
                }
        break;
    case 'd':
    case 'D': a.vaciar();
        break;
    case 'e':
    case 'E': cout << "Hasta pronto\n";
        break;
    default: cout << "Error, escoge una opcion correcta.\n";
    }
    system("PAUSE");
    system("cls");
}while((menuu!='e')&&(menuu!='E'));

return 0;
}




