package Modelo;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private int idCliente;
    private String vendedor;
    private double total;
    private LocalDateTime fecha;

    // Constructor vacío
    public Venta() {}

    // Constructor con todos los campos
    public Venta(int id, int idCliente, String vendedor, double total, LocalDateTime fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
