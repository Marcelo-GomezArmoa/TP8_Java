package parte1.clases;

import parte1.interfaces.Notificable;

/**
 * Clase que representa un cliente del sistema.
 * Implementa Notificable para recibir notificaciones sobre cambios en pedidos.
 */
public class Cliente implements Notificable {
    private String nombre;
    private String email;
    private String identificador;
    
    /**
     * Constructor para crear un cliente.
     * @param nombre el nombre del cliente
     * @param email el email del cliente
     * @param identificador el identificador único del cliente
     */
    public Cliente(String nombre, String email, String identificador) {
        this.nombre = nombre;
        this.email = email;
        this.identificador = identificador;
    }
    
    /**
     * Implementación del método recibirNotificacion de la interfaz Notificable.
     * @param mensaje el mensaje de la notificación
     */
    @Override
    public void recibirNotificacion(String mensaje) {
        System.out.println("=== NOTIFICACIÓN PARA " + nombre.toUpperCase() + " ===");
        System.out.println("Email: " + email);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Timestamp: " + java.time.LocalDateTime.now());
        System.out.println("=======================================");
    }
    
    /**
     * Implementación del método getIdentificador de la interfaz Notificable.
     * @return el identificador único del cliente
     */
    @Override
    public String getIdentificador() {
        return identificador;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    @Override
    public String toString() {
        return String.format("Cliente{nombre='%s', email='%s', id='%s'}", 
                           nombre, email, identificador);
    }
}