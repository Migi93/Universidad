package Persistencia;
import java.sql.*;

public class conexionOracle {
    private Connection conn = null;
    
    /** 
      Establece la conexión con el servidor
    @throws Exception si ocurre cualquier anormalidad
    */
    public conexionOracle(String usuario, String passwod) throws Exception {
           // TODO implementar operaciones 
           conn = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida",usuario,passwod);
    }    
    /** 
      Implementa la desconexión con el servidor
    @throws SQLException si ocurre cualquier anormalidad
    */
    public void desconexion() throws SQLException {
            // TODO implementar operaciones
            conn.close();
    }
    /** 
      Devuelve la propiedad Connection
      @return conn
    @throws SQLException si ocurre cualquier anormalidad
    */
    public Connection getConexionOracle() throws SQLException {
           return conn;     
    }
    /**  
    *  Inicia una transacción
    *@throws SQLException si ocurre cualquier anormalidad
    */
    public void inicioTransaccion() throws SQLException {
        // TODO implementar operaciones
        conn.setAutoCommit(false);
    }
    /** 
    *  Finaliza una transacción con commint
    *@throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionCommit() throws SQLException {
       // TODO implementar operaciones
       conn.commit();
       conn.setAutoCommit(true);
    }
    /**  
    *  Finaliza una transacción con rollback
    @throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionRollback() throws SQLException {
        // TODO implementar operaciones
        conn.rollback();
        conn.setAutoCommit(true);
    }

}
