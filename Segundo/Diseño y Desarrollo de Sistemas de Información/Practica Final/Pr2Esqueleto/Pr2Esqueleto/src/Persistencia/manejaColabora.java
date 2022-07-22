package Persistencia;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class manejaColabora {

    // Creamos un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Creamos un PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;

    /**
     * Implementa operaciones sobre la tabla COLABORA
     *
     * @param c conexión con Oracle
     */
    public manejaColabora(conexionOracle c) {
        conexion = c;
    }

    /**
     * Comprueba si existe una colaboración en la tabla de COLABORA dado su
     * código
     *
     * @param codExperto
     * @param codCaso
     * @return
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeColaboracion(String codExperto, String codCaso) throws SQLException {
        ps = conexion.getConexionOracle().prepareStatement("select * from COLABORA where CODEXPERTO = ? and CODCASO = ?");
        ps.setString(1, codExperto);
        ps.setString(2, codCaso);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public ArrayList<colabora> colaboraciones(String codexp) throws SQLException {
        ArrayList<colabora> colab = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("select * from COLABORA where CODEXPERTO = ?");
        ps.setString(1, codexp);
        ResultSet rs = ps.executeQuery();
        while (rs.next() == true) {
            colab.add(new colabora(rs.getString("CODEXPERTO"), rs.getString("CODCASO"), rs.getString("FECHA"), rs.getString("DESCRIPCION_COLABORACION")));
        }
        ps.close();
        return colab;
    }
    
    public ArrayList<colabora> listadocolaboraciones() throws SQLException {
        ArrayList<colabora> colab = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("select * from COLABORA");
        ResultSet rs = ps.executeQuery();
        while (rs.next() == true) {
            colab.add(new colabora(rs.getString("CODEXPERTO"), rs.getString("CODCASO"), rs.getString("FECHA"), rs.getString("DESCRIPCION_COLABORACION")));
        }
        ps.close();
        return colab;
    }
    
    

    /**
     * Inserta una colaboración en la tabla COLABORA
     *
     * @param col
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaColaboracion(colabora col) throws SQLException {
        // TODO implementar operaciones
        ps = conexion.getConexionOracle().prepareStatement("insert into COLABORA values (?,?,?,?)");
        ps.setString(1, col.getCodExperto());
        ps.setString(2, col.getCodCaso());
        ps.setString(3, col.getFecha());
        ps.setString(4, col.getDescripcionColaboracion());
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet listaColaboradoresPorCaso(String codCaso) throws SQLException {
        CallableStatement cs = null;
        String sql = "{call pColaboradoresCaso(?,?)}";
        cs = conexion.getConexionOracle().prepareCall(sql);
        cs.setString(1, codCaso);
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.executeUpdate();   
        ResultSet rs = (ResultSet) cs.getObject(2);//Te devuelve un objeto (cursor)
        return rs;
    }
    
    public void eliminarColaboracion(String codexp, String codcaso) throws SQLException{
        ps = conexion.getConexionOracle().prepareStatement("delete from COLABORA where CODEXPERTO = ? and CODCASO = ?");
        ps.setString(1, codexp);
        ps.setString(2, codcaso);
        ps.executeUpdate();//Al ser una eliminacion no devuelve nada, por tanto no es necesario un resulset
    }
}
