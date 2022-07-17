package Modelo;

public record Producto(int id, String nombre, String codigo,
                       double precio, int cantidad, String fechaVencimiento) {

    public String[] getDatos(){
        return new String[]{id + "", nombre, codigo, precio + "", cantidad + "", fechaVencimiento};
    }

}
