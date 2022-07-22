package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class manejaCaso {

    // Crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Crea un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla CASO
     *
     * @param c conexión con Oracle
     */
    public manejaCaso(conexionOracle c) {
        conexion = c;
    }

    /**
     * Comprueba si existe un caso en la tabla de CASO_POLICIAL dado un código
     * de caso
     *
     * @param codCaso código del caso a buscar
     * @return
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeCaso(String codCaso) throws SQLException {
        // TODO implementar operaciones
        ps = conexion.getConexionOracle().prepareStatement("select CODCASO from CASO_POLICIAL where CODCASO = ?");
        ps.setString(1, codCaso);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    /**
     * Inserta caso en la tabla de CASO_POLICIAL
     *
     * @param cs caso a insertar
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaCaso(caso cs) throws SQLException {
        // TODO implementar operaciones
        ps = conexion.getConexionOracle().prepareStatement("insert into CASO_POLICIAL values (?,?,?,?)");
        ps.setString(1, cs.getCodCaso());
        ps.setString(2, cs.getNombre());
        ps.setString(3, cs.getFechaInicio());
        ps.setString(4, cs.getFechaFin());
        ps.executeUpdate();
        ps.close();
    }

    public ArrayList<caso> listacasospoliciales() throws SQLException {
        ArrayList<caso> casos = new ArrayList<>();
        Statement s = conexion.getConexionOracle().createStatement();
        ResultSet rs = s.executeQuery("select * from CASO_POLICIAL");
        while (rs.next() == true) {            
            casos.add(new caso(rs.getString("CODCASO"), rs.getString("NOMBRE"), rs.getString("FECHA_INICIO"), rs.getString("FECHA_FIN")));
        }
        s.close();
        return casos;
    }
    
    public void eliminarCasoPolicial(String cod) throws SQLException{
        ps = conexion.getConexionOracle().prepareStatement("delete from CASO_POLICIAL where CODCASO = ?");
        ps.setString(1, cod);
        ps.executeUpdate();//Al ser una eliminacion no devuelve nada, por tanto no es necesario un resulset
    }
}
