#include "ventas.h"
#include "productos.h"

#define SALTO 2
#define SALTO2 20

using namespace std;

void intercambiar(producto *p1, producto *p2){
    producto tmp;
    tmp = *p1;
    *p1 = *p2;
    *p2 = tmp;
}
void Burbuja(producto *prods, int inicio, int fin){
    int pos,ele;
    for (pos=inicio; pos<fin; pos++)
        for (ele=fin; ele>pos; ele--)
            if(prods[ele-1].importe > prods[ele].importe)
                intercambiar(&prods[ele-1], &prods[ele]);//Función genérica que tendrá que ser implementada

}

bool compararFecha(Sfecha f1, Sfecha f2){ //Devuelve True si f1 > f2
    bool f1_mayor=true;
    if (f1.anno >  f2.anno || (f1.anno == f2.anno && f1.mes >  f2.mes) ||
        (f1.anno == f2.anno && f1.mes == f2.mes && f1.dia > f2.dia)){
          f1_mayor=true;
        }
          else{
            f1_mayor=false;
          }
            return f1_mayor;
}

       void ventas::anadirventa(int num){
        venta v,vaux;
        int numventas = 0;
        int i = 1;
        detalle.clear();//Inicializamos el descriptor de fichero y lo abrimos en modo lectura/escritura
        detalle.open(fichero,ios::binary | ios::in | ios::out );// Abrimos el fichero
        if(detalle.fail())// Verificamos que la apertura se realizó correctamente
                cout << "Ha ocurrido un error con el fichero de ventas" << endl;
            else{
            v.producto = num;
            cout << "Introduce el Dia de la venta:"; cin >> v.fecha.dia;
            cout << "Introduce el Mes de la venta:"; cin >> v.fecha.mes;
            cout << "Introduce el Anno de la venta:";cin >> v.fecha.anno;
            do{
                cout << endl<< "Unidades:";
                cin >> v.unidades;
                if(v.unidades<1) cout << "Se ha de vender al menos una unidad del producto.\n";
            }while(v.unidades<1);

            do{
                cout << endl<< "Importe:";
                cin >> v.importe;
                if(v.importe<1) cout << "El importe de la venta ha de ser mayor de 0€.\n";
       }while(v.importe<1);
         detalle.seekg(0,ios::end);
         numventas = detalle.tellg()/sizeof(v);// Calculamos el número total de elementos
         detalle.seekg((numventas-1)*sizeof(v));
         detalle.read((char *)&vaux,sizeof(vaux));
         if(compararFecha(v.fecha,vaux.fecha)==true){ //si la fecha que vamos a introducir es mayor que la ultimo del fichero la escribo
            detalle.write((char *)&v,sizeof(v));
         }else{
               detalle.seekp(0,ios::beg);// Colocamos el puntero al principio del fichero
               detalle.read((char *)&vaux,sizeof(vaux));
               while((compararFecha(vaux.fecha,v.fecha)==false) && (!detalle.eof())){
                     i++;  // Variable que almacena la posición de inserción de la nueva venta
                     detalle.read((char *)&vaux,sizeof(vaux));
                }
                for(int j=numventas;j>=i;j--){// Desplazamos a la derecha todos los registros de ventas posteriores a la actual [i..numventas]
                    detalle.seekg((j-1)*sizeof(v),ios::beg);// Colocamos el puntero en el último registro
                    detalle.read((char *)&vaux,sizeof(vaux));//leemos
                    detalle.seekp((j)*sizeof(v),ios::beg);// Colocamos el puntero en la posición siguiente
                    detalle.write((char *)&vaux,sizeof(vaux));//escribimos
                    }
                detalle.seekp((i-1)*sizeof(v),ios::beg);
                detalle.write((char *)&v,sizeof(v));// Escribimos el registro en la posición correspospondiente
                }
            detalle.close();
            }
        }

       void ventas::mostrarventas()
       {
           cout << "            VENTAS             " << endl;
           cout << "-------------------------------" << endl;
           venta v;
           producto pro;
           int numero_productos=0;
           int i=0;
           ifstream resumen_productos;
           detalle.open(fichero,ios::in|ios::binary);
           resumen_productos.open(ficheroresumen,ios::in|ios::binary);
           if(detalle.fail() || resumen_productos.fail()){
                cout << "No se puede trabajar con alguno de los ficheros";
                detalle.clear();
                resumen_productos.clear();
                    }
           else
           {
               detalle.read((char *) &v,sizeof(venta));//leemos el fichero de venta que es el que hay que mostrar y ya va a leer la primera posicion del fichero
               while(!detalle.eof()){//mientras que el fichero de ventas no llegue al final(entro en el bucle)
                    resumen_productos.seekg(sizeof(producto)*(v.producto-1),ios::beg);//como ambos ficheros estan ordenados y la posicion de ambos son las mismas le digo que lea los bytes que ocupa un dato del fichero y se situe en el mismo menos una posicion ya que habrá leido ya la posicion inficada y se situara uno mas abajo
                    resumen_productos.read((char *) &pro, sizeof(producto));//despues de situar el archivo en la posicin correcta, le decimos que lea esa posicion y así la mostrara por pantalla correspondiendose con su nombre correcto.
                    if(pro.tipo!=-1){
                    i++;
                    cout << "Venta " << i << endl;
                    cout << "Fecha de venta : " << v.fecha.dia << "/" << v.fecha.mes << "/" << v.fecha.anno << endl;
                    cout << "Nombre del producto: " << pro.nombre << endl;
                    cout << "Unidades: " << v.unidades << endl;
                    cout << "Importe: " << v.importe << endl;
                    cout << "-------------------------------" << endl;
                    numero_productos++;
                    detalle.read((char *) &v,sizeof(venta));//leo el fichero de ventas (lo va a leer uno a uno, cada vez que entre en el bucle va a leer uno), esta instruccion se pone al final porque antes del while ya el fichero hace una lectura, por tanto  como va a seguir en el while decimos que avance al final cuando ya ha terminado de mostrar por patanlla lo correpondiente y asi en la siguiente vuelta mostrara lo correcto
                    }
                }
            }
                    cout << "Hay " << numero_productos << " ventas" << endl;
                    detalle.close();//cerramos archivos
                    resumen_productos.close();//cerramos archivo
        }
         void ventas::resumirfichero(){
            producto p;
            venta v;
            fstream productosf;
            productosf.open(ficheroresumen, ios::binary | ios::in | ios::out);
            detalle.open(fichero, ios::binary | ios::in | ios::out);
            if(productosf.fail() || detalle.fail()){
                cout << "Ha ocurrido un error al abrir alguno de los ficheros" << endl;
            }
            else
            {
                detalle.seekg(0,ios::end);
                if(detalle.tellg()<=0){
                    cout << "Su fichero no contiene ninguna venta, por tanto no se puede actualizar" << endl;
                }
                else{
                productosf.seekg(0, ios::beg);
                productosf.read((char *) &p, sizeof(producto));
                while(!productosf.eof()){
                    detalle.seekg(0, ios::beg);
                    detalle.read((char *) &v, sizeof(venta));
                    while(!detalle.eof()){
                        if((p.producto==v.producto) &&(compararFecha(v.fecha,p.ultimaventa)==true)){
                            p.ultimaventa=v.fecha;
                            p.unidades=p.unidades+v.unidades;
                            p.importe=p.importe+v.importe;
                        }
                        detalle.read((char *) &v, sizeof(venta));
                    }
                    if(detalle.fail()){ //Esto hace que el metodo no se quede en bucle infinito
                        detalle.clear();
                    }
                    productosf.seekp((int)-sizeof(producto), ios::cur);//usar (int) para mover de posicion ya que con -1 se queda en bucle infinito
                    productosf.write((char *) &p, sizeof(producto));
                    productosf.read((char *) &p, sizeof(producto));
                }
                cout << "Su resumen de ventas ha sido creado y el fichero de productos se ha actualizado." << endl;
              }
            }
            productosf.close();
            detalle.close();
         }

       /*void ventas::resumirfichero(){
       venta v;
       producto p;
       fstream resumendat;
       producto *tablav=new producto[SALTO2];
       if(tablav==NULL){
        cout << "No se ha podido reservar memoria para su tabla" << endl;
       }else{
            detalle.open(fichero, ios::binary | ios::in | ios::out);
            if(detalle.fail()){
                cout << "Ha ocurrido un error con su fichero de ventas" << endl;
                detalle.clear();
            }else{
                detalle.seekg(0,ios::beg);
                detalle.read((char *) &v, sizeof(venta));
                int i=0;
                while(!detalle.eof()){
                        tablav[i].importe=v.importe;
                        tablav[i].unidades=v.unidades;
                        tablav[i].ultimaventa=v.fecha;
                        tablav[i].producto=v.producto;
                    detalle.read((char *) &v, sizeof(venta));
                    i++;
                    if(i==SALTO2){
                        producto *tablavnueva=new producto[i+SALTO];
                        if(tablavnueva==NULL){
                            cout << "Error, no se ha podido asignar memoria para su nueva tabla" << endl;
                        }else{
                        for(int j=0;j<i;j++){
                            tablavnueva[j]=tablav[j];
                            }
                            delete [] tablav;
                            tablav=tablavnueva;
                        }
                    }
                }
                i--;
                int iaux=i;
                    if(detalle.eof()){
                            resumendat.open("resumen.dat", ios::binary | ios::in | ios::out);
                        if(resumendat.fail()){
                            cout << "Ha ocurrido un error al abrir el fichero de productos" << endl;
                            resumendat.clear();
                            }
                            else{
                                resumendat.seekg(0,ios::beg);
                                resumendat.read((char *) &p, sizeof(producto));
                                while(!resumendat.eof()){
                                    for(int k=0;k<=i;k++){
                                    if(p.producto==tablav[k].producto && compararFecha(tablav[k].ultimaventa,p.ultimaventa)==true){
                                        p.ultimaventa=tablav[k].ultimaventa;
                                        p.importe=p.importe+tablav[k].importe;
                                        p.unidades=p.unidades+tablav[k].unidades;
                                            }
                                        }
                                  resumendat.seekp((int)-sizeof(producto), ios::cur);
                                  resumendat.write((char *) &p, sizeof(producto));
                                  resumendat.read((char *) &p, sizeof(producto));
                                  i=iaux;
                            }
                        }
                    }
                    delete [] tablav;
            }
        }
            detalle.close();
            resumendat.close();
    }*/

       void ventas::estadisticas(int tipo,int annoini,int annofin)
       {
           venta v;
           producto p;
           fstream resumen_producto;
           resumen_producto.open(ficheroresumen, ios::in|ios::out|ios::binary);
           detalle.open(fichero, ios::in|ios::out|ios::binary);
           producto *tabla=new producto[SALTO];//creamos una tabla dinamica de punteros al struct producto con el tamaño de la cantidad de productos totales
           if(tabla==NULL){
            cout << "No se ha podido asignar la memoria para su tabla." << endl;
           }
           else{
                int i=0;
            if(!detalle.fail() && !resumen_producto.fail()){
                    detalle.read((char *) &v, sizeof(venta));
            while(!detalle.eof()){
                resumen_producto.seekg((v.producto-1)*sizeof(producto),ios::beg);//me situo en el fichero de productos en el producto correspondiente al que ha leido en el fichero de ventas
                resumen_producto.read((char *) &p, sizeof(producto));//leo fichero
                    if(p.tipo==tipo&&v.fecha.anno>=annoini&&v.fecha.anno<=annofin){//hago las correspondientes comparaciones
                        //j siempre va a valer menos que i por tanto siempre entra en el bucle
                        int j = 0;
                        while((j<i) && tabla[j].producto!=v.producto){//comprobaremos si ya hay una venta de un mismo produco para no introducirlo repetidamente y actualizar solo las unidades e importe
                            j++;
                        }
                        if(j<i){//si es meno es porque existe ya una venta introducia en la tabla del mismo producto por tanto solo actualiza
                            tabla[j].importe=tabla[j].importe+v.importe;
                            tabla[j].unidades=tabla[j].unidades+v.unidades;
                        }
                        else{//sino metes el producto en la tabla porque no existian antoriormente
                            tabla[i].importe=v.importe;
                            tabla[i].unidades=v.unidades;
                            tabla[i].producto=v.producto;
                            strcpy(tabla[i].nombre,p.nombre);
                            i++;
                        }
                }
                if(i%SALTO==0){//cuando el resto de la division de 0 se aumenta la tabla porque se ha quedado sin espacio
                    producto *tablanueva=new producto[i+SALTO];
                    if(tablanueva==NULL){
                        cout << "No se ha podido asignar memoria para aumentar su tabla." << endl;
                    }
                    else{
                        for(int j=0;j<i;j++){//copiamos los datos y liberamos memoria de la antigua tabla que quedara en desuso
                            tablanueva[j]=tabla[j];
                        }
                            delete [] tabla;
                            tabla=tablanueva;
                      }
                    }
                    detalle.read((char *) &v, sizeof(venta));
                  }
                }
                else{
                    cout << "Ha ocurrido un error al abrir uno de los ficheros" << endl;
                    detalle.clear();
                    resumen_producto.clear();
                }
            i--; //por si la ultima lectura esta vacia o no fue remplazada
            Burbuja(tabla,0,i);//ordenamos de menos a mayor
            cout << "\n-----Productos mas vendidos-----\n" << endl;
            for(;i>=0;i--){
            cout << "Producto: " << tabla[i].nombre <<"\n"
                 << "Unidades: " << tabla[i].unidades <<"\n"
                 << "Importe: " << tabla[i].importe <<"\n";
            cout << "______________________________________________\n\n";

            }
            delete [] tabla;
           }
           detalle.close();
       }

       bool ventas::asignar(cadena Fichero,cadena FicheroResumen)
       {
           bool creado=true;
           strcpy(ficheroresumen,FicheroResumen);
           strcpy(fichero,Fichero);
           detalle.open(Fichero,ios::in|ios::out|ios::binary);//intentamos abrir el fichero
           if(detalle.fail())//si ha ocurrido un error al abrir el archivo
           {
            detalle.clear();//limpiamos la variable que se queda en el sistema del error
            detalle.open(Fichero,ios::out|ios::binary);//lo abrimos para que se cree
            if(detalle.fail()){//si no se ha podido crear porque quizas el disco duro este protegido contra escritura
                detalle.clear();//limpiamos la variable del error en el sistema
                creado=false;//devolvemos false para que se entienda que no se ha creado finalmente
            }
           }
            detalle.close();
            return creado;
        }

    void ventas::numeroventaportipo(){
           cout << " VENTAS POR TIPO DE PRODUCTOS             " << endl;
           cout << "-------------------------------" << endl;
           venta v;
           producto pro;
           int totalelectronica=0, totalpapeleria=0, totalotros=0;
           ifstream resumen_productos;
           detalle.open(fichero,ios::in|ios::binary);
           resumen_productos.open(ficheroresumen,ios::in|ios::binary);
           if(detalle.fail() || resumen_productos.fail()){
                cout << "No se puede trabajar con alguno de los ficheros";
                detalle.clear();
                resumen_productos.clear();
                    }
           else
           {
               detalle.read((char *) &v,sizeof(venta));
               while(!detalle.eof()){
                    resumen_productos.seekg(sizeof(producto)*(v.producto-1),ios::beg);
                    resumen_productos.read((char *) &pro, sizeof(producto));
                    if(pro.tipo!=-1){
                            if(pro.tipo==1){
                        totalelectronica++;
                    }
                    if(pro.tipo==2){
                        totalpapeleria++;
                    }
                    if(pro.tipo==3){
                        totalotros++;
                    }
                    detalle.read((char *) &v,sizeof(venta));
                    }
                }
            }
                    cout << "Electronica: " << totalelectronica << " ventas." << endl;
                    cout << "Papeleria: " << totalpapeleria << " ventas." << endl;
                    cout << "Otros: " << totalotros << " ventas." << endl;
                    detalle.close();
                    resumen_productos.close();
        }

        void ventas::corregirventas(int codigoproducto, int annoini, int annofin, float importe){
        venta v;
        detalle.open(fichero,ios::in|ios::binary);
        if(detalle.fail()){
            cout << "Se ha producido un error con el fichero de ventas" << endl;
            detalle.clear();
        }
        else{
            detalle.read((char *) &v, sizeof(venta));
            while(!detalle.eof()){
                    if(v.producto==codigoproducto && (v.fecha.anno>=annoini) && (v.fecha.anno<=annofin)){
                        v.importe=v.importe*importe;
                        detalle.seekp((int)-sizeof(venta), ios::cur);
                        detalle.write((char *) &v, sizeof(venta));
                    }

                detalle.read((char *) &v, sizeof(venta));
            }
          }
            detalle.close();
            cout << "El importe de las ventas han sido corregido" << endl;
        }





