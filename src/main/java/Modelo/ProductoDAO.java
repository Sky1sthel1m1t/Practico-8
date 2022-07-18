package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoDAO extends AbstractDao<Producto> {

    @Override
    public Producto get(int id) {
        Conexion conexion = Conexion.getInstance();
        Producto producto = null;

        String comando = "SELECT * FROM producto WHERE ID = " + id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            if (rs.next()) {
                producto = new Producto(rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Codigo"),
                        rs.getDouble("Precio"),
                        rs.getInt("Cantidad"),
                        rs.getString("FechaVencimiento"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return producto;
    }

    public Lista<Producto> getProductos() {
        Lista<Producto> productos = new Lista<>();
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM producto";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Codigo"),
                        rs.getDouble("Precio"),
                        rs.getInt("Cantidad"),
                        rs.getString("FechaVencimiento"));
                productos.adicionar(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return productos;
    }

    public void añadirProducto(Producto producto) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO producto VALUES (" +
                producto.id() + "," +
                "'" + producto.nombre() + "'" + "," +
                "'" + producto.codigo() + "'" + "," +
                producto.precio() + "," +
                producto.cantidad() + "," +
                "'" + producto.fechaVencimiento() + "'" + ")";

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);

        conexion.desconectar();

    }

    public void eliminarProducto(int id) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM producto WHERE ID = " + id;

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);

        conexion.desconectar();
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "UPDATE producto SET " +
                "Nombre = " + "'" + producto.nombre() + "'" + ", " +
                "Codigo = " + "'" + producto.codigo() + "'" + ", " +
                "Precio = " + producto.precio() + ", " +
                "Cantidad = " + producto.cantidad() + ", " +
                "FechaVencimiento = " + "'" + producto.fechaVencimiento() + "'" + " " +
                "WHERE ID = " + producto.id();

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);

        conexion.desconectar();
    }

    public Lista<String> columnas() {
        Lista<String> columnas = new Lista<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "DESC producto";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);
            while (rs.next()) {
                String aux = rs.getString("Field");
                columnas.adicionar(aux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return columnas;
    }


}
