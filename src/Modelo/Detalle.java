package Modelo;
/**
 *
 * @author LUIS
 */
public class Detalle {
    private int id;
    private int idProducto;
    private int cantidad;
    private double precio;
    private int idVenta;

    // Constructor vacío
    public Detalle() {
    }

    // Constructor con parámetros
    public Detalle(int idProducto, int cantidad, double precio, int idVenta) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idVenta = idVenta;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
}
