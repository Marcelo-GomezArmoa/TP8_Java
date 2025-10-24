package parte1.clases;

import parte1.interfaces.PagoConDescuento;

/**
 * Implementación de un medio de pago con PayPal.
 * Implementa PagoConDescuento para aplicar descuentos especiales.
 */
public class PayPal implements PagoConDescuento {
    private String email;
    private double saldo;
    private boolean cuentaVerificada;
    private double descuentoMaximo;
    
    /**
     * Constructor para crear una cuenta PayPal.
     * @param email el email asociado a la cuenta
     * @param saldo el saldo disponible en la cuenta
     * @param cuentaVerificada si la cuenta está verificada
     */
    public PayPal(String email, double saldo, boolean cuentaVerificada) {
        this.email = email;
        this.saldo = saldo;
        this.cuentaVerificada = cuentaVerificada;
        // Las cuentas verificadas tienen mayor descuento máximo
        this.descuentoMaximo = cuentaVerificada ? 20.0 : 10.0; 
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
        
        if (monto > saldo) {
            System.out.println("Error: Saldo insuficiente en PayPal. Saldo: $" + 
                             String.format("%.2f", saldo) + ", Monto: $" + 
                             String.format("%.2f", monto));
            return false;
        }
        
        if (!cuentaVerificada && monto > 500) {
            System.out.println("Error: Para pagos superiores a $500 se requiere cuenta verificada");
            return false;
        }
        
        // Simular procesamiento del pago
        System.out.println("Procesando pago con PayPal...");
        System.out.println("Email: " + email);
        System.out.println("Cuenta verificada: " + (cuentaVerificada ? "Sí" : "No"));
        System.out.println("Monto: $" + String.format("%.2f", monto));
        
        // Simular tiempo de procesamiento
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        saldo -= monto;
        System.out.println("✓ Pago procesado exitosamente con PayPal");
        System.out.println("Saldo restante: $" + String.format("%.2f", saldo));
        return true;
    }
    
    /**
     * Implementación del método getNombreMedioPago de la interfaz Pago.
     * @return el nombre del medio de pago
     */
    @Override
    public String getNombreMedioPago() {
        return "PayPal";
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
                             descuentoMaximo + "%) para " + 
                             (cuentaVerificada ? "cuenta verificada" : "cuenta no verificada"));
            porcentajeDescuento = descuentoMaximo;
        }
        
        double descuento = monto * (porcentajeDescuento / 100);
        double montoConDescuento = monto - descuento;
        
        System.out.println("Descuento PayPal aplicado: " + String.format("%.1f", porcentajeDescuento) + 
                          "% = $" + String.format("%.2f", descuento));
        
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
    
    /**
     * Agrega saldo a la cuenta PayPal.
     * @param monto el monto a agregar
     */
    public void agregarSaldo(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Saldo agregado: $" + String.format("%.2f", monto) + 
                             ". Nuevo saldo: $" + String.format("%.2f", this.saldo));
        }
    }
    
    /**
     * Verifica la cuenta PayPal.
     */
    public void verificarCuenta() {
        if (!cuentaVerificada) {
            cuentaVerificada = true;
            descuentoMaximo = 20.0; // Aumentar descuento máximo
            System.out.println("✓ Cuenta PayPal verificada exitosamente");
            System.out.println("Descuento máximo actualizado a: " + descuentoMaximo + "%");
        }
    }
    
    // Getters y setters
    public String getEmail() {
        return email;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public boolean isCuentaVerificada() {
        return cuentaVerificada;
    }
    
    @Override
    public String toString() {
        return String.format("PayPal{email='%s', saldo=$%.2f, verificada=%s}", 
                           email, saldo, cuentaVerificada ? "Sí" : "No");
    }
}