package parte1.interfaces;

/**
 * Interfaz para objetos que pueden recibir notificaciones.
 * Implementa el patrón Observer para notificar cambios de estado.
 */
public interface Notificable {
    /**
     * Recibe una notificación con un mensaje específico.
     * @param mensaje el mensaje de la notificación
     */
    void recibirNotificacion(String mensaje);
    
    /**
     * Obtiene el identificador del objeto notificable.
     * @return el identificador único
     */
    String getIdentificador();
}