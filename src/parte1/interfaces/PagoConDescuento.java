package parte1.interfaces;

/**
 * Interfaz que extiende Pago para medios de pago que pueden aplicar descuentos.
 * Demuestra la herencia de interfaces.
 */
public interface PagoConDescuento extends Pago {
    /**
     * Aplica un descuento al monto total.
     * @param monto el monto original
     * @param porcentajeDescuento el porcentaje de descuento a aplicar (0-100)
     * @return el monto con descuento aplicado
     */
    double aplicarDescuento(double monto, double porcentajeDescuento);
    
    /**
     * Obtiene el descuento máximo permitido para este medio de pago.
     * @return el porcentaje máximo de descuento
     */
    double getDescuentoMaximo();
}