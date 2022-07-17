package Modelo;

import java.sql.*;

public class Conexion {
    // Nombre de la base de datos
    private String database = "producto";

    // Host
    private String hostname = "localhost"; // 127.0.0.1 == localhost

    // Puerto
    private String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    // jdbc:mysql://127.0.0.1:3306/gimnasio

    // Nombre de usuario
    private String username = "root";

    // Clave de usuario
    private String password = "root";

    private Connection con;
    private static Conexion instancia;

    private Conexion(){}

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public void conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet consulta(Statement statement, String comando){
        try {
            return statement.executeQuery(comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutar(Statement statement, String comando) throws SQLException {
        statement.execute(comando);
    }

    public void desconectar(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConexion() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}