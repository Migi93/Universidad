/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "GestorAlmacenes.h"

typedef struct Almacen { //Para guardar todos los datos de cada almacen
	TDatosAlmacen TDatos;
	TProducto *tProd; //Array dinamico de productos
	int Nproductos; //Numero de productos que habrá en el array
	int CliEnAlmacen; //Numero de clientes que estan usando el almacen
} Almacen;



Almacen *al = NULL; //Array de struct Almacen
int Nalmacenes = 0; //Numero de almacenes que hay en memoria del struc almacen


TDatosAlmacen* datosalmacen_1_svc(int *argp, struct svc_req *rqstp) {
	static TDatosAlmacen result;
	int posicion = *argp;

	if(Nalmacenes == 0) {
		strcpy(result.Nombre,"NULL");
	} else {
		if(posicion > Nalmacenes || posicion < 0 || al[posicion].CliEnAlmacen == 0) {
			strcpy(result.Nombre,"NULL");
		} else{
			result = al[posicion].TDatos;
			}
	}
	return &result;
}

int* nproductos_1_svc(int *argp, struct svc_req *rqstp) {
	static int result;
	int posicion = *argp;
	if (Nalmacenes == 0 || posicion > Nalmacenes || posicion < 0 || al[posicion].CliEnAlmacen == 0){
		result = -1;
	} else {
		result = al[posicion].Nproductos;
	}
	return &result;
}

int* crearalmacen_1_svc(TDatosAlmacen *argp, struct svc_req *rqstp) {
	static int result;
	int cero = 0;
		FILE *fichero = fopen((*argp).Fichero, "wb"); //Abrir para solo escritura (sobrescribe lo que hubiera) ///wb(escritaura binaria)
		fwrite(&cero, sizeof(int), 1, fichero);
		fwrite((*argp).Nombre, sizeof(Cadena), 1, fichero);
		fwrite((*argp).Direccion, sizeof(Cadena), 1, fichero);
		fclose(fichero);
		result = *abriralmacen_1_svc((*argp).Fichero,rqstp);
	return &result;
}

int* abriralmacen_1_svc(char *argp, struct svc_req *rqstp) {
	static int  result;
	result = -1;
	bool_t encontrado = FALSE;
	int i = 0;
	
	while(i < Nalmacenes && encontrado == FALSE){
		if(al[i].CliEnAlmacen > 0 && strcmp(al[i].TDatos.Fichero,argp) == 0 ){
			encontrado = TRUE;
			result = i;
		} else {
			i++;
		}
	}
	if(result == -1){
		bool_t huecolibre = FALSE;
		int j = 0;
		while( j < Nalmacenes && huecolibre == FALSE){
			if(al[j].CliEnAlmacen == 0){
				huecolibre = TRUE;
				result = j;
			} else {
				j++;
			}
		}
		if(result == -1){
			Nalmacenes++;
			Almacen *temporal = (Almacen*)realloc(al, Nalmacenes * sizeof(Almacen));
			if(temporal != NULL){
				al = temporal;
				result = Nalmacenes - 1;
			}
		}
		if(result != -1){
			FILE *fichero = fopen(argp, "rb"); //Abrir para solo lectura ///rb(lectura binaria)
			if(fichero == NULL){
				al[result].CliEnAlmacen = 0;
				result = -1;
			} else {
				al[result].CliEnAlmacen = 1;
				strcpy(al[result].TDatos.Fichero,argp);
				fread(&al[result].Nproductos, sizeof(int), 1, fichero);
				fread(al[result].TDatos.Nombre, sizeof(Cadena), 1, fichero);
				fread(al[result].TDatos.Direccion, sizeof(Cadena), 1, fichero);
				if(al[result].Nproductos == 0){
					al[result].tProd = NULL;
				} else {
					al[result].tProd = malloc(sizeof(TProducto)*al[result].Nproductos);
					fread(al[result].tProd, sizeof(TProducto), al[result].Nproductos, fichero);
				}
				fclose(fichero);
			}
			
		}
	}
	return &result;
}

bool_t* guardaralmacen_1_svc(int *argp, struct svc_req *rqstp) {
	static bool_t  result;
	int posicion = (*argp);
	if(Nalmacenes == 0 || posicion > Nalmacenes || posicion < 0 || al[posicion].CliEnAlmacen == 0){
		result = FALSE;
	} else {
		FILE *fichero = fopen(al[posicion].TDatos.Fichero, "wb"); //Abrir para solo escritura (sobrescribe lo que hubiera) ///wb(escritaura binaria)
		fwrite(&al[posicion].Nproductos, sizeof(int), 1, fichero);
		fwrite(al[posicion].TDatos.Nombre, sizeof(Cadena), 1, fichero);
		fwrite(al[posicion].TDatos.Direccion, sizeof(Cadena), 1, fichero);
		fwrite(al[posicion].tProd, sizeof(TProducto), al[posicion].Nproductos, fichero);
		fclose(fichero);
		result = TRUE;
	}

	return &result;
}

bool_t* cerraralmacen_1_svc(int *argp, struct svc_req *rqstp) {
	static bool_t  result;
	int posicion = *argp;

	if(Nalmacenes == 0 || posicion > Nalmacenes || posicion < 0 ){
		result = FALSE;
	} else {
		if(al[posicion].CliEnAlmacen == 0) {
			result = FALSE;
		} else if(al[posicion].CliEnAlmacen > 1 ) {
			al[posicion].CliEnAlmacen--;
			result = TRUE;
		} else if(al[posicion].CliEnAlmacen == 1 ) {
			al[posicion].CliEnAlmacen--;
			guardaralmacen_1_svc(argp, rqstp); //tambien podría pasarle con un &posicion, el argp es sin * porque ya es un puntero en si
			free(al[posicion].tProd);
			result = TRUE;
		}
	}
	return &result;
}

bool_t* almacenabierto_1_svc(int *argp, struct svc_req *rqstp) {
	static bool_t  result;
	int posicion = *argp;

	if(Nalmacenes == 0 || posicion > Nalmacenes || posicion < 0 || al[posicion].CliEnAlmacen == 0) {
		result = FALSE;
	} else if(al[posicion].CliEnAlmacen != 0 ) {
		result = TRUE;
	}
	return &result;
}

int* buscaproducto_1_svc(TBusProd *argp, struct svc_req *rqstp) {
	static int result;
	int posicion = (*argp).Almacen;
	Cadena codpro;
	strcpy(codpro, (*argp).CodProducto);
	if(Nalmacenes == 0 || posicion > Nalmacenes || posicion < 0 || al[posicion].CliEnAlmacen == 0) {
		result = -1;
	} else {
		bool_t encontrado = FALSE;
		int i = 0;
		result = -1;
		while (i < al[posicion].Nproductos && encontrado == FALSE) {
			if(strcmp(al[posicion].tProd[i].CodProd,codpro) == 0) {
				result = i;
				encontrado = TRUE;
			} else {

				i++;
			}
		}
	}
	return &result;
}

TProducto* obtenerproducto_1_svc(TObtProd *argp, struct svc_req *rqstp) {
	static TProducto  result;
	int posicion = (*argp).Almacen;
	int pospro = (*argp).PosProducto;
	if(Nalmacenes == 0 || posicion > Nalmacenes || posicion < 0 || al[posicion].CliEnAlmacen == 0){
		strcpy(result.NombreProd,"NULL");
	} else{
		result = al[posicion].tProd[pospro];
	}
	return &result;
}

bool_t* anadirproducto_1_svc(TActProd *argp, struct svc_req *rqstp) {
	static bool_t  result;
	int posiAlmacen = (*argp).Almacen;
	TBusProd tBprod;
	tBprod.Almacen = posiAlmacen;
	strcpy(tBprod.CodProducto, (*argp).Producto.CodProd);
	int encontrado = *buscaproducto_1_svc(&tBprod,rqstp);
	if(encontrado == -1) {
		al[posiAlmacen].Nproductos++;
		TProducto *temporal = (TProducto*)realloc(al[posiAlmacen].tProd, al[posiAlmacen].Nproductos * sizeof(TProducto));
		if(temporal == NULL){
			result = FALSE;
		} else {
			al[posiAlmacen].tProd = temporal;
			al[posiAlmacen].tProd[al[posiAlmacen].Nproductos - 1] = (*argp).Producto;
			result = TRUE;
		}
	} else {
		result = FALSE;
	}

	return &result;
}

bool_t* actualizarproducto_1_svc(TActProd *argp, struct svc_req *rqstp) {
	static bool_t  result;
	int posicion = (*argp).Almacen;
	TBusProd tBprod;
	tBprod.Almacen = posicion;
	strcpy(tBprod.CodProducto,(*argp).Producto.CodProd);
	int encontrado = *buscaproducto_1_svc(&tBprod,rqstp);
	if(encontrado == -1){
		result = FALSE;
	} else{
	 	al[posicion].tProd[encontrado] = (*argp).Producto;
		 result = TRUE;
	}
	return &result;
}

bool_t* eliminarproducto_1_svc(TBusProd *argp, struct svc_req *rqstp) {
	static bool_t  result;
	int posicion = *buscaproducto_1_svc(argp, rqstp);
	int posiAlmacen = (*argp).Almacen;
	if(posicion == -1){
		result = FALSE;
	} else {
		for (int i = posicion; i < al[posiAlmacen].Nproductos - 1; i++){
			al[posiAlmacen].tProd[i] = al[posiAlmacen].tProd[i+1];	
		}
		al[posiAlmacen].Nproductos--;
		TProducto *temporal = (TProducto*)realloc(al[posiAlmacen].tProd,al[posiAlmacen].Nproductos * sizeof(TProducto));
		if(temporal == NULL){
			result = FALSE;
		} else {
			al[posiAlmacen].tProd = temporal;
			result = TRUE;
		}
	}
	return &result;
}
