package parte2.excepciones;

/**
 * Excepción personalizada para validar edades inválidas.
 * Demuestra la creación de excepciones personalizadas que extienden Exception.
 */
public class EdadInvalidaException extends Exception {
    private int edadIngresada;
    
    /**
     * Constructor con mensaje por defecto.
     * @param edadIngresada la edad que causó la excepción
     */
    public EdadInvalidaException(int edadIngresada) {
        super("Edad inválida: " + edadIngresada + ". La edad debe estar entre 0 y 120 años.");
        this.edadIngresada = edadIngresada;
    }
    
    /**
     * Constructor con mensaje personalizado.
     * @param mensaje el mensaje de error personalizado
     * @param edadIngresada la edad que causó la excepción
     */
    public EdadInvalidaException(String mensaje, int edadIngresada) {
        super(mensaje);
        this.edadIngresada = edadIngresada;
    }
    
    /**
     * Constructor con mensaje y causa.
     * @param mensaje el mensaje de error
     * @param causa la causa de la excepción
     * @param edadIngresada la edad que causó la excepción
     */
    public EdadInvalidaException(String mensaje, Throwable causa, int edadIngresada) {
        super(mensaje, causa);
        this.edadIngresada = edadIngresada;
    }
    
    /**
     * Obtiene la edad que causó la excepción.
     * @return la edad inválida
     */
    public int getEdadIngresada() {
        return edadIngresada;
    }
    
    /**
     * Obtiene una descripción detallada del error.
     * @return descripción del error con sugerencias
     */
    public String getDescripcionDetallada() {
        StringBuilder descripcion = new StringBuilder();
        descripcion.append("ERROR DE VALIDACIÓN DE EDAD\n");
        descripcion.append("Edad ingresada: ").append(edadIngresada).append("\n");
        
        if (edadIngresada < 0) {
            descripcion.append("Problema: La edad no puede ser negativa.\n");
            descripcion.append("Sugerencia: Ingrese un número positivo.");
        } else if (edadIngresada > 120) {
            descripcion.append("Problema: La edad excede el límite máximo de 120 años.\n");
            descripcion.append("Sugerencia: Verifique el valor ingresado.");
        } else {
            descripcion.append("Problema: Edad fuera del rango válido (0-120).\n");
            descripcion.append("Sugerencia: Ingrese una edad entre 0 y 120 años.");
        }
        
        return descripcion.toString();
    }
}