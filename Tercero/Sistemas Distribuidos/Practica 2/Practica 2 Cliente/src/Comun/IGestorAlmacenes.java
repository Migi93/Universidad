package Comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestorAlmacenes extends Remote {

    TDatosAlmacen DatosAlmacen(int pAlmacen) throws RemoteException;

    int NProductos(int pAlmacen) throws RemoteException;

    int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero) throws RemoteException;

    int AbrirAlmacen(String pNomFiche) throws RemoteException;

    boolean GuardarAlmacen(int pAlmacen) throws RemoteException;

    boolean CerrarAlmacen(int pAlmacen) throws RemoteException;

    boolean AlmacenAbierto(int pAlmacen) throws RemoteException;

    int BuscaProducto(int pAlmacen, String pCodProducto) throws RemoteException;

    TProducto ObtenerProducto(int pAlmacen, int pPosProducto) throws RemoteException;

    boolean AnadirProducto(int pAlmacen, TProducto pProdNuevo) throws RemoteException;

    boolean ActualizarProducto(int pAlmacen, TProducto pProducto) throws RemoteException;

    boolean EliminarProducto(int pAlmacen, String pCodProducto) throws RemoteException;
}
