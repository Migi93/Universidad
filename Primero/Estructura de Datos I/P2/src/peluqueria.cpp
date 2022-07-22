#include "peluqueria.h"
#define INCRE 10
#define INCREMENTO 5


    void peluqueria::AbrirPeluqueria(char *nombrefichero){
        fstream f;
        peluquerof pf;
        peluquero p;
        cliente c;
        f.open(nombrefichero, ios::binary | ios::in | ios::out);
        int npeluqueros, nclientes;
        if(!f.fail()){
            f.read((char *) &npeluqueros, sizeof(int));//Con esto lo que hacemos es leer el numero total de peluqueros que hay en el fichero
            for(int i=1;i<=npeluqueros;i++){//hacemos un for con el numero total de peluqueros
                f.read((char *) &pf, sizeof(peluquerof));//leemos en cada pasada del for el nombre, apellidos, etc... del fichero y lo coiamos en el estruc con el que se trabaja
                strcpy(p.Nombre,pf.Nombre);
                strcpy(p.Apellidos,pf.Apellidos);
                p.Codigo=pf.Codigo;
                p.TipoServicio=pf.TipoServicio;
                L.insertar(i,p);//i+1 si nuestro i en el for empieza en 0, i si nuestro for empieza en 1
                }
                //Repetimos el proceso anterior pero para los clientes
                f.read((char *) &nclientes, sizeof(int));
                for(int j=1;j<=nclientes;j++){
                    f.read((char *) &c, sizeof(cliente));
                    IncorporarCliente(c);
                }
                cout << "\nCopia rescatada correctamente.\n" << endl;
            }
        else{
            f.clear();
            cout << "\nNo se ha podido rescatar la copia.\n" << endl;
        }
        f.close();
    }

    void peluqueria::IncorporarPeluquero(peluquero t){
        int i=1;
        int pos=0;
        bool introducido=false;
        if(L.esvacia()){
            L.insertar(i,t);
            cout << "\nEl peluquero ha sido introducido.\n" << endl;//No se hace ninguna particion porque la lista esta vacia
        }
        else{
        if(t.Codigo>L.observar(L.longitud()).Codigo){
                    pos=L.posicion(L.observar(L.longitud()+1));
                    introducido=true;
                    if(introducido==true){
                    int clong=0;//Contador de las longitudes
                    int npigual=0;//Contador del numero de peluqueros con el mismo servicio
                    int division;//Division con el numero de clienes que tengo que pasarle al nuevo peluquero
                       for(int i=1;i<=L.longitud();i++){
                        if(L.observar(i).TipoServicio==t.TipoServicio){
                            clong=clong+L.observar(i).Col.longitud();
                            npigual=npigual+1;
                          }
                       }
                       if(npigual!=0){
                        int menor=1;
                        int posip;
                        peluquero aux;
                        division=clong/(npigual+1);
                        for(int i=1;i<=division;i++){
                            for(int j=1;j<=L.longitud();j++){
                                if(L.observar(j).TipoServicio==t.TipoServicio){
                                    if(L.observar(j).Col.longitud()>=menor){
                                        copiarpeluquero(aux,L.observar(j));
                                        menor=L.observar(j).Col.longitud();
                                        posip=L.posicion(L.observar(j));
                                    }
                                }
                             }
                             t.Col.encolar(aux.Col.primero());
                             aux.Col.desencolar();
                             L.modificar(posip,aux);
                          }
                          L.insertar(pos,t);
                       }
                    }
            cout << "\nEl peluquero ha sido introducido.\n" << endl;
        }
        else{
            while((i<=L.longitud()) && (!introducido)){
                if(t.Codigo<=L.observar(i).Codigo){
                    if(t.Codigo==L.observar(i).Codigo){
                        cout << "\nYa existe un peluquero con ese codigo.\n" << endl;
                        introducido=true;
                        }
                        else{
                    pos=L.posicion(L.observar(i));
                    introducido=true;
                    for(int j=L.longitud();j<pos;j--){
                        L.insertar(j+1,L.observar(j));
                    }
                    if(introducido==true){
                    int clong=0;//Contador de las longitudes
                    int npigual=0;//Contador del numero de peluqueros con el mismo servicio
                    int division;//Division con el numero de clienes que tengo que pasarle al nuevo peluquero
                       for(int i=1;i<=L.longitud();i++){
                        if(L.observar(i).TipoServicio==t.TipoServicio){
                            clong=clong+L.observar(i).Col.longitud();
                            npigual=npigual+1;
                          }
                       }
                       if(npigual!=0){
                        int menor=1;
                        int posip;
                        peluquero aux;
                        division=clong/(npigual+1);
                        for(int i=1;i<=division;i++){
                            for(int j=1;j<=L.longitud();j++){
                                if(L.observar(j).TipoServicio==t.TipoServicio){
                                    if(L.observar(j).Col.longitud()>=menor){
                                        copiarpeluquero(aux,L.observar(j));
                                        menor=L.observar(j).Col.longitud();
                                        posip=L.posicion(L.observar(j));
                                    }
                                }
                             }
                             t.Col.encolar(aux.Col.primero());
                             aux.Col.desencolar();
                             L.modificar(posip,aux);
                          }
                          L.insertar(pos,t);
                       }
                    }
            cout << "\nEl peluquero ha sido introducido.\n" << endl;
                        }
                    }
                    i++;
                }
            }
        }
    }

    bool peluqueria::RetirarPeluquero(int codigo){
    bool encontrado=false;
    peluquero p_temp;
    cliente *cli=NULL;
    cliente *aux=NULL;
    int salto=INCREMENTO;
    int n_clientes=0;
    int tipo;
    int cont=0;
    int i=1;
    copiarpeluquero(p_temp,L.observar(i));
    while((i<=L.longitud()) && (!encontrado)){
        if(p_temp.Codigo==codigo){
            encontrado=true;
        } else {
            i++;
            copiarpeluquero(p_temp,L.observar(i));
        }
    }
    if(encontrado){
        tipo=p_temp.TipoServicio;
        for(int k=1;k<=L.longitud();k++){
            copiarpeluquero(p_temp,L.observar(k));
            if(tipo==p_temp.TipoServicio){
                cont++;
            }
        }
        if(cont==1){
            return false;
        } else {
            copiarpeluquero(p_temp,L.observar(i));
            while(!p_temp.Col.esvacia()){
                if(cli==NULL){
                    cli=new cliente[salto];
                    if(p_temp.Col.longitud()>0){
                        cli[0]=p_temp.Col.primero();
                        p_temp.Col.desencolar();
                        n_clientes++;
                    }
                } else {
                    if(n_clientes<salto){
                        cli[n_clientes]=p_temp.Col.primero();
                        p_temp.Col.desencolar();
                        n_clientes++;
                    } else {
                        aux=cli;
                        cli= new cliente[salto+INCREMENTO];
                        for(int i=0;i<salto;i++){
                            cli[i]=aux[i];
                        }
                        salto+=INCREMENTO;
                        delete [] aux;
                        aux=NULL;
                    }
                }
            }
        }
        L.eliminar(i);
        for(int j=0;j<n_clientes;j++){
            IncorporarCliente(cli[j]);
        }
        return true;
        }
        else {
        return false;
        }
    }

    bool peluqueria::EliminarCliente(cadena Nombre, cadena Apellidos){
        int i=1;
        cliente c;
        int longitud_cola=0;
        bool eliminado;
        while((i<=L.longitud())&&(!eliminado)){
                longitud_cola=L.observar(i).Col.longitud();
            for(int j=1;j<=longitud_cola;j++){
                if(strcmp(Nombre,L.observar(i).Col.primero().Nombre)==0 && strcmp(Apellidos,L.observar(i).Col.primero().Apellidos)==0){
                        L.observar(i).Col.desencolar();
                        eliminado=true;
                }
                else{
                    c=L.observar(i).Col.primero();
                    L.observar(i).Col.encolar(c);
                    L.observar(i).Col.desencolar();
                }
            }
            i++;
        }
        return eliminado;
    }

    bool peluqueria::IncorporarCliente(cliente cli){
        peluquero aux;
        int longitud_cola=1000;
        bool encolado;
        int pos=-1;
        if(!L.esvacia()){
            for(int i=1;i<=L.longitud();i++){
                if(L.observar(i).TipoServicio==cli.TipoServicio){
                    if(L.observar(i).Col.longitud()<longitud_cola){
                        longitud_cola=L.observar(i).Col.longitud();
                        pos=L.posicion(L.observar(i));
                    }
                }
            }
            if(pos!=-1){
                copiarpeluquero(aux,L.observar(pos));
                aux.Col.encolar(cli);
                L.modificar(pos,aux);
                encolado=true;
            }
            else{
                encolado=false;
            }
        }
        else{
            encolado=false;
        }
        return encolado;
    }

    void peluqueria::Mostrar(){
        peluquero p;
        int longitud_cola;
        if(!L.esvacia()){
            for(int i=1;i<=L.longitud();i++){
            copiarpeluquero(p,L.observar(i));
            cout << "\nPeluquero " << p.Codigo << ": " << p.Apellidos << ", " << p.Nombre << "\t" << "-Tipo de servicio: " << p.TipoServicio << endl;
            cout << "Clientes:" << endl;
            cout << "\tApellidos\t"  "Nombre\t"  "Edad\t"  "Tipo de servicio\t"  "Hora de llegada" << endl;
            cout << "\t.........\t"  "......\t"  "....\t"  "................\t"  "..............." << endl;
            longitud_cola=p.Col.longitud();
                for(int j=1;j<=longitud_cola;j++){
            cout << "\t" << p.Col.primero().Apellidos << "\t" << p.Col.primero().Nombre << "\t " << p.Col.primero().Edad << "\t\t"
                 <<  p.Col.primero().TipoServicio << "\t\t " << p.Col.primero().HoraLlegada << endl;
                 p.Col.desencolar();
                }
            cout << "\n";
            cout << "\n";
            }
        }
        else{
            cout << "\nNo hay ningun peluquero trabajando.\n" << endl;
        }
    }

    bool peluqueria::AtenderCliente(int CodigoPeluquero){
        peluquero aux;
        bool encontrado=false;
        int i=1;
        while(i<=L.longitud()&&(encontrado==false)){
            if(L.observar(i).Codigo==CodigoPeluquero){
                if(!L.observar(i).Col.esvacia()){
                copiarpeluquero(aux,L.observar(i));
                aux.Col.desencolar();
                L.modificar(i,aux);
                encontrado=true;
                }
                else{
                    return false;
                }
            }
            else{
                i++;
            }
        }
        if(encontrado==true){
            return true;
        }
        else{
            return false;
        }
    }

    void peluqueria::VolcarPeluqueria(char *nombrefichero){
        fstream f;
        peluquerof pf;
        peluquero p;
        cliente c;
        int longitud_cola=0;
        int npeluqueros=0;
        int nclientes=0;
        f.open(nombrefichero, ios::binary | ios::in | ios::out);
        if(!f.fail()){
            npeluqueros=L.longitud();
            f.write((char *) &npeluqueros, sizeof(int));//Escribimos en el fichero la longitud del numero de peluqueros que hay
            for(int i=1;i<=npeluqueros;i++){//Recorremos esos peluqueros
                copiarpeluquero(p,L.observar(i));//Copiamos los peluqueros en una variable auxiliar de tipo peluquero y a continuacion copiamos cada campo en el struc que usa el fichero
                strcpy(pf.Nombre,p.Nombre);
                strcpy(pf.Apellidos,p.Apellidos);
                pf.TipoServicio=p.TipoServicio;
                pf.Codigo=p.Codigo;
                f.write((char *) &pf, sizeof(peluquerof));//Escribimos en el fichero la informacion de los peluqueros contenidas en la variable pf
                nclientes=nclientes+p.Col.longitud();
            }
            f.write((char *) &nclientes, sizeof(int));
            for(int j=1;j<=npeluqueros;j++){
                copiarpeluquero(p,L.observar(j));
                longitud_cola=p.Col.longitud();
                for(int k=1;k<=longitud_cola;k++){
                    c=p.Col.primero();
                    f.write((char *) &c, sizeof(cliente));
                    p.Col.desencolar();
                }
            }
            cout << "Informacion volcada correctamente.\n" << endl;
        }
        else{
            cout << "La informacion del fichero no ha podido ser guardada.\n" << endl;
        }
        f.close();
    }

    void peluqueria::ejerciciounomodificacion(peluquero t, int edadclientes){
        int i=1;
        int pos=0;
        bool introducido=false;
        //bool introducido2=false;
        if(L.esvacia()){
            L.insertar(i,t);
            cout << "\nEl peluquero ha sido introducido.\n" << endl;//No se hace ninguna particion porque la lista esta vacia
        }
        else{
        if(t.Codigo>L.observar(L.longitud()).Codigo){
                    pos=L.posicion(L.observar(L.longitud()+1));
                    introducido=true;
                    if(introducido==true){
                        peluquero aux;
                       for(int i=1;i<=L.longitud();i++){
                            copiarpeluquero(aux,L.observar(i));
                            while(!aux.Col.esvacia()){
                                if(aux.Col.primero().Edad>=edadclientes){
                                    t.Col.encolar(aux.Col.primero());
                                    aux.Col.desencolar();
                                }
                                else{
                                    aux.Col.desencolar();

                                }
                            }
                          }
                          L.insertar(pos,t);
                       }
                       cout << "\nEl peluquero ha sido introducido.\n" << endl;
                    }
        else{
            while((i<=L.longitud()) && (!introducido)){
                if(t.Codigo<=L.observar(i).Codigo){
                    if(t.Codigo==L.observar(i).Codigo){
                        cout << "\nYa existe un peluquero con ese codigo.\n" << endl;
                        introducido=true;
                        }
                        else{
                    pos=L.posicion(L.observar(i));
                    introducido=true;
                    for(int j=L.longitud();j<pos;j--){
                        L.insertar(j+1,L.observar(j));
                        }
                    }
                    if(introducido==true){
                        peluquero aux;
                       for(int i=1;i<=L.longitud();i++){
                            copiarpeluquero(aux,L.observar(i));
                            while(!aux.Col.esvacia()){
                                if(aux.Col.primero().Edad>=edadclientes){
                                    t.Col.encolar(aux.Col.primero());
                                    aux.Col.desencolar();
                                }
                                else{
                                    aux.Col.desencolar();

                                }
                            }
                          }
                          L.insertar(pos,t);
                       }
                       cout << "\nEl peluquero ha sido introducido.\n" << endl;
                    }
                    i++;
                }
                /*if(introducido2==true){
                    peluquero aux2;
                    for(int i=1;i<=L.longitud();i++){
                        copiarpeluquero(aux2,L.observar(i));

                    }
                }*/
            }
        }
    }



