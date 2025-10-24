package parte1.interfaces;

/**
 * Interfaz que define el contrato para procesar pagos.
 * Se utiliza para implementar diferentes medios de pago.
 */
public interface Pago {
    /**
     * Procesa un pago por el monto especificado.
     * @param monto el monto a pagar
     * @return true si el pago fue exitoso, false en caso contrario
     */
    boolean procesarPago(double monto);
    
    /**
     * Obtiene el nombre del medio de pago.
     * @return el nombre del medio de pago
     */
    String getNombreMedioPago();
}