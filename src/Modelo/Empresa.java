package Modelo;

public class Empresa {

    private int id;
    private String dniNif;
    private String nombre;
    private String telefono;
    private String razonSocial;
    private String direccion;

    // Constructor
    public Empresa(int id, String dniNif, String nombre, String telefono, String razonSocial, String direccion) {
        this.id = id;
        this.dniNif = dniNif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDniNif() {
        return dniNif;
    }

    public void setDniNif(String dniNif) {
        this.dniNif = dniNif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método toString para mostrar la empresa de manera legible
    @Override
    public String toString() {
        return "Empresa{id=" + id + ", dniNif='" + dniNif + "', nombre='" + nombre + "', telefono='" + telefono + "', razonSocial='" + razonSocial + "', direccion='" + direccion + "'}";
    }
}
