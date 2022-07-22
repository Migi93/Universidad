#include "productos.h"
#include <conio.h>



       productos::productos(cadena Fichero,cadena FicheroVentas){//Fichero recibe el fichero resumen y el FicheroVentas es el fichero de venta
           numproductos=0;
           maxcatalogo=0;
           producto pro;
           strcpy(fichero,Fichero);
           if(ven.asignar(FicheroVentas,Fichero)==false){
                cout << "No se puede trabajar con el fichero de ventas.";
           }
           resumen.open(Fichero,ios::in|ios::out|ios::binary);/*Abrimos el fichero, si solo pusieramos ios::out el fichero se hubiese creado y nos hubiera machacado la informacion que contenia el fichero que nos facilitan, por tanto para que no ocurra nada parecido se recomiendo abrir el fichero con el modo ios::in y el modo ios::out, así si el fichero existe nos lo va a leer y si no existe simplemente va a dar fallo de lectura*/
           if(resumen.fail())/*.fail() se usa para ver si no ha dado fallos, es decir, si ha leido el fichero, en caso contrario entra en la sentencia*/
            {
            resumen.clear();/*Limpiamos la variable .clear()*/
            resumen.open(Fichero,ios::out|ios::binary);/*Ahora creamos el fichero en caso de que al principio el fichero no existiera*/
            resumen.close();/*Volvemos a cerrar el fichero para que no surjan problemas*/
           }
            else
                {
            resumen.read((char *)&pro,sizeof(producto));//si no ha dado error, leemos el archivo
            while(!resumen.eof())//recorremos el fichero hasta que llegue al final, este es el for para los ficheros
                {
                if(pro.tipo!=-1){//si el tipo de producto es distinto de -1 entra en la sentencia
                    numproductos=numproductos+1;
                    maxcatalogo=pro.producto;
                    }
            resumen.read((char *)&pro,sizeof(producto));
            }
            resumen.close();
                }
       }

       int productos::getmaxcatalogo()
       {
           return maxcatalogo;
       }

       void productos::setmaxcatalogo(int num)
       {
           maxcatalogo=num;
       }

       int productos::getnumproductos()
       {

           return numproductos;
        }

       void productos::setnumproductos(int num)
       {
           numproductos=num;
       }

       void productos::mostrarproductos()
       {
           cout << "            PRODUCTOS             " << endl;
           cout << "Maxcatalogo: " << maxcatalogo << endl;
           cout << "Numproductos: " << numproductos << endl;
           cout << "-------------------------------" << endl;
           producto p;
           resumen.open(fichero, ios::in|ios::binary);
           if(!resumen.fail())
           {
            resumen.read((char *) &p, sizeof(producto));
            while(!resumen.eof()){
             if(p.tipo!=-1){
                cout << "Producto: " << p.nombre << endl;
                cout << "Ultima venta: " << p.ultimaventa.dia << " / " << p.ultimaventa.mes << " / " << p.ultimaventa.anno << endl;
                cout << "Importe: " << p.importe << endl;
                cout << "Unidades: " << p.unidades << endl;
                cout << "Tipo de producto: " << p.tipo << endl;
                cout << "-------------------------------" << endl;
                    }
                resumen.read((char *) &p, sizeof(producto));
                }
           }
           else{
               cout << "El fichero de productos no se ha podido abrir.";
           }
           resumen.close();
       }

       void productos::mostrarventas()
       {
           ven.mostrarventas();
       }

       bool productos::anadirventa()
       {
           bool introducido=false;
           producto p, aux;
           aux.tipo=-1;
           char nombre_producto[20];
           int codigo_producto;
           cout << "       ***NUEVA VENTA***" << endl;
           cout << "\nIntroduce el codigo del producto a vender: "; cin >> codigo_producto;
           if(codigo_producto>maxcatalogo){//como maxcatalogo te da el mayor numero de codigo de productos, si el que vamos a introducir es mayor sera porque no existe, por tanto procedemos a crear el producto e introducir la venta
            cout << "\n       ***CREANDO PRODUCTO***" << endl;
            cout << "\nIntroduce el nombre del producto a crear: "; cin >> nombre_producto;
            strcpy(p.nombre,nombre_producto);//copiamos el nombre
            p.producto=codigo_producto;//copiamos el codigo del producto
            // copiamos todos los datos a 0 ya que no existe venta anterior por ser un nuevo producto
            p.importe=0;
            p.unidades=0;
            p.ultimaventa.dia=0;
            p.ultimaventa.mes=0;
            p.ultimaventa.anno=0;
            numproductos++;//avanzamos el numero de productos porque ya hay uno mas
            do{
            cout << "\nIntroduce el tipo de producto (tipo 1: Electronica, tipo 2: Papeleria, tipo 3: Otros): "; cin >> p.tipo;
            }while(p.tipo<1 || p.tipo>3);
            resumen.open(fichero, ios::in|ios::out|ios::binary);//abro fichero
            if(!resumen.fail()){
            resumen.seekp(0, ios::end);//Me situo al final para escritura
            //resumen.write((char *) &p, sizeof(producto));//Escribo el nuevo producto al final porque es mas grande que maxcatalogo
            //resumen.close();
            for(int i=maxcatalogo;i<p.producto-1;i++){
                   resumen.write((char *) &aux, sizeof(producto));
                }
            resumen.write((char *) &p, sizeof(producto));//Escribo el nuevo producto al final porque es mas grande que maxcatalogo
            resumen.close();
            }
            maxcatalogo=codigo_producto;
            ven.anadirventa(codigo_producto);//procedemos a vender el producto que acabamos de introducir
            introducido=true;
           }
                else{
                    resumen.open(fichero, ios::in|ios::out|ios::binary);//abro el fichero
                    resumen.seekg(sizeof(producto)*(codigo_producto-1));//me coloco una posicion arriba del codigo del producto para leer
                    resumen.read((char *) &p, sizeof(producto));//leo
                    resumen.close();
                        if(p.tipo!=-1){//si el tipo es distinto de -1 se puede vender el producto porque existe
                            ven.anadirventa(codigo_producto);
                            introducido=true;
                        }
                        else{
                            introducido=false;//no puede vender el producto porque se habrá dado de baja o esta en desuso
                        }
                }
           return introducido;
       }

       void productos::actualizarresumen()
       {
           ven.resumirfichero();
       }

       void productos::obtenerestadisticas(int tipo,int annoini,int annofin)
       {
           ven.estadisticas(tipo,annoini,annofin);
       }

       void productos::obtenernumeroventaportip(){
           ven.numeroventaportipo();
       }

       void productos::corregirventasderror(int codigoproducto, int annoini, int annofin, float importe)
       {
           ven.corregirventas(codigoproducto,annoini,annofin,importe);
       }
