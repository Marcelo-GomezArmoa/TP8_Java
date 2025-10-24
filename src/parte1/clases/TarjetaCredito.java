package parte1.clases;

import parte1.interfaces.PagoConDescuento;

/**
 * Implementación de un medio de pago con tarjeta de crédito.
 * Implementa PagoConDescuento para aplicar descuentos especiales.
 */
public class TarjetaCredito implements PagoConDescuento {
    private String numeroTarjeta;
    private String titular;
    private String fechaVencimiento;
    private double limiteCredito;
    private double descuentoMaximo;
    
    /**
     * Constructor para crear una tarjeta de crédito.
     * @param numeroTarjeta el número de la tarjeta (últimos 4 dígitos)
     * @param titular el nombre del titular
     * @param fechaVencimiento la fecha de vencimiento
     * @param limiteCredito el límite de crédito disponible
     */
    public TarjetaCredito(String numeroTarjeta, String titular, String fechaVencimiento, double limiteCredito) {
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.limiteCredito = limiteCredito;
        this.descuentoMaximo = 15.0; // 15% de descuento máximo para tarjetas de crédito
    }
    
    /**
     * Implementación del método procesarPago de la interfaz Pago.
     * @param monto el monto a pagar
     * @return true si el pago fue exitoso, false en caso contrario
     */
    @Override
    public boolean procesarPago(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor a cero");
            return false;
        }
        
        if (monto > limiteCredito) {
            System.out.println("Error: Límite de crédito insuficiente. Límite: $" + 
                             String.format("%.2f", limiteCredito) + ", Monto: $" + 
                             String.format("%.2f", monto));
            return false;
        }
        
        // Simular procesamiento del pago
        System.out.println("Procesando pago con Tarjeta de Crédito...");
        System.out.println("Tarjeta: ****" + numeroTarjeta.substring(Math.max(0, numeroTarjeta.length() - 4)));
        System.out.println("Titular: " + titular);
        System.out.println("Monto: $" + String.format("%.2f", monto));
        
        // Simular tiempo de procesamiento
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        limiteCredito -= monto;
        System.out.println("✓ Pago procesado exitosamente");
        System.out.println("Límite restante: $" + String.format("%.2f", limiteCredito));
        return true;
    }
    
    /**
     * Implementación del método getNombreMedioPago de la interfaz Pago.
     * @return el nombre del medio de pago
     */
    @Override
    public String getNombreMedioPago() {
        return "Tarjeta de Crédito";
    }
    
    /**
     * Implementación del método aplicarDescuento de la interfaz PagoConDescuento.
     * @param monto el monto original
     * @param porcentajeDescuento el porcentaje de descuento a aplicar
     * @return el monto con descuento aplicado
     */
    @Override
    public double aplicarDescuento(double monto, double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        
        if (porcentajeDescuento > descuentoMaximo) {
            System.out.println("Advertencia: Descuento reducido al máximo permitido (" + 
                             descuentoMaximo + "%)");
            porcentajeDescuento = descuentoMaximo;
        }
        
        double descuento = monto * (porcentajeDescuento / 100);
        double montoConDescuento = monto - descuento;
        
        System.out.println("Descuento aplicado: " + String.format("%.1f", porcentajeDescuento) + "% = $" + 
                          String.format("%.2f", descuento));
        
        return montoConDescuento;
    }
    
    /**
     * Implementación del método getDescuentoMaximo de la interfaz PagoConDescuento.
     * @return el porcentaje máximo de descuento
     */
    @Override
    public double getDescuentoMaximo() {
        return descuentoMaximo;
    }
    
    // Getters y setters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    
    public double getLimiteCredito() {
        return limiteCredito;
    }
    
    @Override
    public String toString() {
        return String.format("TarjetaCredito{titular='%s', numero='****%s', limite=$%.2f}", 
                           titular, 
                           numeroTarjeta.substring(Math.max(0, numeroTarjeta.length() - 4)), 
                           limiteCredito);
    }
}