package parte1.interfaces;

/**
 * Interfaz que define el contrato para objetos que pueden calcular un total.
 * Implementa el concepto de interfaces como contratos de comportamiento.
 */
public interface Pagable {
    /**
     * Calcula el total del objeto pagable.
     * @return el valor total a pagar
     */
    double calcularTotal();
}