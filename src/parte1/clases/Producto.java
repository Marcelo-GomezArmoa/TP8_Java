package parte1.clases;

import parte1.interfaces.Pagable;

/**
 * Clase que representa un producto en el sistema de e-commerce.
 * Implementa la interfaz Pagable para calcular su total.
 */
public class Producto implements Pagable {
    private String nombre;
    private double precio;
    
    /**
     * Constructor para crear un producto.
     * @param nombre el nombre del producto
     * @param precio el precio del producto
     * @throws IllegalArgumentException si el precio es negativo
     */
    public Producto(String nombre, double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.nombre = nombre;
        this.precio = precio;
    }
    
    /**
     * Implementación del método calcularTotal de la interfaz Pagable.
     * @return el precio del producto
     */
    @Override
    public double calcularTotal() {
        return precio;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return String.format("Producto{nombre='%s', precio=%.2f}", nombre, precio);
    }
}