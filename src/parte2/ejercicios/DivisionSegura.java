package parte2.ejercicios;

import java.util.Scanner;

/**
 * Ejercicio 1: División segura con manejo de ArithmeticException.
 * Demuestra el manejo de excepciones unchecked (RuntimeException).
 */
public class DivisionSegura {
    
    /**
     * Solicita dos números al usuario y realiza la división manejando la excepción de división por cero.
     */
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== EJERCICIO 1: DIVISIÓN SEGURA ===");
        System.out.println("Este ejercicio demuestra el manejo de ArithmeticException");
        
        try {
            System.out.print("Ingrese el dividendo (número a dividir): ");
            double dividendo = scanner.nextDouble();
            
            System.out.print("Ingrese el divisor (número por el cual dividir): ");
            double divisor = scanner.nextDouble();
            
            // Realizar la división
            double resultado = dividirSeguro(dividendo, divisor);
            
            System.out.println("\n✓ Resultado de la división:");
            System.out.printf("%.2f ÷ %.2f = %.4f%n", dividendo, divisor, resultado);
            
        } catch (ArithmeticException e) {
            System.err.println("\n❌ Error aritmético: " + e.getMessage());
            System.err.println("No se puede dividir por cero. Por favor, ingrese un divisor diferente de cero.");
        } catch (Exception e) {
            System.err.println("\n❌ Error inesperado: " + e.getMessage());
        } finally {
            System.out.println("\n--- Fin del ejercicio de división segura ---");
        }
    }
    
    /**
     * Realiza una división segura verificando que el divisor no sea cero.
     * @param dividendo el número a dividir
     * @param divisor el número por el cual dividir
     * @return el resultado de la división
     * @throws ArithmeticException si el divisor es cero
     */
    private static double dividirSeguro(double dividendo, double divisor) throws ArithmeticException {
        if (divisor == 0) {
            throw new ArithmeticException("División por cero no permitida");
        }
        
        return dividendo / divisor;
    }
    
    /**
     * Versión alternativa que maneja múltiples casos especiales.
     * @param dividendo el número a dividir
     * @param divisor el número por el cual dividir
     * @return el resultado de la división
     * @throws ArithmeticException si el divisor es cero o hay overflow
     */
    public static double dividirConValidaciones(double dividendo, double divisor) throws ArithmeticException {
        // Verificar división por cero
        if (divisor == 0) {
            throw new ArithmeticException("División por cero: No se puede dividir " + dividendo + " entre 0");
        }
        
        // Verificar casos especiales con infinito
        if (Double.isInfinite(dividendo) || Double.isInfinite(divisor)) {
            throw new ArithmeticException("No se puede operar con valores infinitos");
        }
        
        // Verificar NaN (Not a Number)
        if (Double.isNaN(dividendo) || Double.isNaN(divisor)) {
            throw new ArithmeticException("No se puede operar con valores NaN");
        }
        
        double resultado = dividendo / divisor;
        
        // Verificar overflow del resultado
        if (Double.isInfinite(resultado)) {
            throw new ArithmeticException("El resultado de la división excede los límites numéricos");
        }
        
        return resultado;
    }
    
    /**
     * Método para probar diferentes casos de división.
     */
    public static void probarCasos() {
        System.out.println("\n=== PRUEBAS AUTOMATIZADAS DE DIVISIÓN ===");
        
        double[][] casos = {
            {10, 2},      // Caso normal
            {15, 3},      // Caso normal
            {7, 0},       // División por cero
            {-8, 2},      // Números negativos
            {0, 5}        // Cero dividido por número
        };
        
        for (double[] caso : casos) {
            try {
                double resultado = dividirSeguro(caso[0], caso[1]);
                System.out.printf("✓ %.1f ÷ %.1f = %.4f%n", caso[0], caso[1], resultado);
            } catch (ArithmeticException e) {
                System.err.printf("❌ %.1f ÷ %.1f → %s%n", caso[0], caso[1], e.getMessage());
            }
        }
    }
}