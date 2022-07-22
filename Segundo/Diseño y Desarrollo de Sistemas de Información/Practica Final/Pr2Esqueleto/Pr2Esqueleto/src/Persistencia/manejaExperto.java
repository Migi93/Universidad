package Persistencia;

import java.sql.*;
import java.util.ArrayList;

public class manejaExperto {

    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle conexion = null;

    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    CallableStatement cl = null;

    /**
     * Implementa operaciones sobre la tabla EXPERTO
     *
     * @param c conexión con Oracle
     */
    public manejaExperto(conexionOracle c) {
        conexion = c;
    }

    /**
     * Devuelve una lista con todos los expertos cuyo país se pase por parámetro
     *
     * @param pais
     * @throws SQLException si ocurre alguna anomalía
     * @return ArrayList<experto>
     */
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException {
        // TODO implementar operaciones
        ArrayList<experto> lista = new ArrayList<>();
        ps = conexion.getConexionOracle().prepareStatement("select * from EXPERTO where PAIS = ?");
        //Statement s = conexion.getConexionOracle().createStatement();
        //s.executeQuery("consulta")
        ps.setString(1, pais);//Reemplaza la ? en la consulta
        ResultSet rs = ps.executeQuery();//Ejecuta la consulta
        while (rs.next() == true) {
            lista.add(new experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD")));
        }
        ps.close();
        return lista;
    }

    public ArrayList<experto> listaExpertos() throws SQLException {
        ArrayList<experto> lista = new ArrayList<>();
        Statement s = conexion.getConexionOracle().createStatement(); //Al no ser parametrizada la consulta la decidimos hacer así
        //ps = conexion.getConexionOracle().prepareStatement("select * from EXPERTO"); //Se puede hacer asi, pero como no es parametrizada la hacemos de otra forma
        ResultSet rs = s.executeQuery("select * from EXPERTO");
        while (rs.next()) {
            lista.add(new experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD")));
        }
        s.close();
        return lista;
    }
    
    public ArrayList<experto> listaExpertosordenadas() throws SQLException {
        ArrayList<experto> lista = new ArrayList<>();
        Statement s = conexion.getConexionOracle().createStatement(); //Al no ser parametrizada la consulta la decidimos hacer así
        //ps = conexion.getConexionOracle().prepareStatement("select * from EXPERTO"); //Se puede hacer asi, pero como no es parametrizada la hacemos de otra forma
        ResultSet rs = s.executeQuery("SELECT CODEXPERTO, EXPERTO.NOMBRE as NOMBRE_EXPERTO, ESPECIALIDAD, DESCRIPCION_COLABORACION  FROM COLABORA INNER JOIN CASO_POLICIAL USING(CODCASO) INNER JOIN EXPERTO USING(CODEXPERTO) ORDER BY ESPECIALIDAD");
        while (rs.next()) {
            lista.add(new experto(rs.getString("CODEXPERTO"), rs.getString("NOMBRE"), rs.getString("PAIS"), rs.getString("SEXO"), rs.getString("ESPECIALIDAD")));
        }
        s.close();
        return lista;
    }

    /**
     * Comprueba si existe un experto
     *
     * @param codExperto
     * @return
     * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeExperto(String codExperto) throws SQLException {
        // TODO implementar operaciones
        ps = conexion.getConexionOracle().prepareStatement("select count(*) from EXPERTO where CODEXPERTO = ?");
        ps.setString(1, codExperto);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) == 1) {
                ps.close();
                return true;
            } else {
                ps.close();
                return false;
            }
        } else {
            ps.close();
            return false;
        }
    }

    /**
     * inserta un experto
     *
     * @param exp
     * @throws SQLException si ocurre alguna anomalía
     */
    public void insertaExperto(experto exp) throws SQLException {
        // TODO implementar operaciones
        ps = conexion.getConexionOracle().prepareStatement("insert into EXPERTO values (?,?,?,?,?)");
        ps.setString(1, exp.getCodExperto());
        ps.setString(2, exp.getNombre());
        ps.setString(3, exp.getPais());
        ps.setString(4, exp.getSexo());
        ps.setString(5, exp.getEspecialidad());
        ps.executeUpdate();
        ps.close();
    }
    
    public void eliminarExperto(String cod) throws SQLException{
        ps = conexion.getConexionOracle().prepareStatement("delete from EXPERTO where CODEXPERTO = ?");
        ps.setString(1, cod);
        ps.executeUpdate();//Al ser una eliminacion no devuelve nada, por tanto no es necesario un resulset
    }
    
    public int sexoExperto(String s) throws SQLException{
        int contar = 0;
        String sql = "{? = call fsexoexperto(?)}"; //La ? = del principio es porque la funcion devuelve algo
        cl = conexion.getConexionOracle().prepareCall(sql);
        cl.registerOutParameter(1, Types.INTEGER);//Recibe un valor, en este caso lo recibimos en la primera ? ya que es una funcion que te devuleve un numero
        cl.setString(2, s);
        cl.executeUpdate();
        contar = cl.getInt(1);
        cl.close();
        return contar;
    }
}
