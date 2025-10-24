package parte2.ejercicios;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Ejercicio 2: Conversión de cadena a número con manejo de NumberFormatException.
 * Demuestra el manejo de excepciones al convertir strings a números.
 */
public class ConversionNumero {
    
    /**
     * Lee texto del usuario e intenta convertirlo a entero.
     */
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== EJERCICIO 2: CONVERSIÓN DE CADENA A NÚMERO ===");
        System.out.println("Este ejercicio demuestra el manejo de NumberFormatException");
        
        try {
            System.out.print("Ingrese un texto para convertir a número entero: ");
            String textoIngresado = scanner.nextLine();
            
            // Intentar convertir el texto a número
            int numero = convertirAEntero(textoIngresado);
            
            System.out.println("\n✓ Conversión exitosa:");
            System.out.println("Texto ingresado: \"" + textoIngresado + "\"");
            System.out.println("Número convertido: " + numero);
            System.out.println("El número " + (numero % 2 == 0 ? "es par" : "es impar"));
            System.out.println("Valor absoluto: " + Math.abs(numero));
            
        } catch (NumberFormatException e) {
            System.err.println("\n❌ Error de conversión: " + e.getMessage());
            System.err.println("El texto ingresado no es un número válido.");
            System.err.println("Ejemplos de números válidos: 123, -456, 0, 2024");
        } catch (Exception e) {
            System.err.println("\n❌ Error inesperado: " + e.getMessage());
        } finally {
            System.out.println("\n--- Fin del ejercicio de conversión ---");
        }
    }
    
    /**
     * Convierte una cadena de texto a número entero.
     * @param texto la cadena a convertir
     * @return el número entero convertido
     * @throws NumberFormatException si la cadena no es un número válido
     */
    private static int convertirAEntero(String texto) throws NumberFormatException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new NumberFormatException("Texto vacío o nulo no se puede convertir a número");
        }
        
        // Limpiar espacios en blanco
        texto = texto.trim();
        
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            // Re-lanzar con mensaje más descriptivo
            throw new NumberFormatException("No se puede convertir \"" + texto + "\" a número entero: " + e.getMessage());
        }
    }
    
    /**
     * Versión que maneja diferentes tipos de conversiones numéricas.
     * @param texto la cadena a convertir
     * @return información sobre la conversión
     */
    public static String analizarTextoNumerico(String texto) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Análisis del texto: \"").append(texto).append("\"\n");
        
        if (texto == null || texto.trim().isEmpty()) {
            resultado.append("❌ Texto vacío o nulo\n");
            return resultado.toString();
        }
        
        texto = texto.trim();
        
        // Intentar diferentes tipos de conversión
        try {
            int entero = Integer.parseInt(texto);
            resultado.append("✓ Válido como INTEGER: ").append(entero).append("\n");
        } catch (NumberFormatException e) {
            resultado.append("❌ No es un INTEGER válido\n");
        }
        
        try {
            long largo = Long.parseLong(texto);
            resultado.append("✓ Válido como LONG: ").append(largo).append("\n");
        } catch (NumberFormatException e) {
            resultado.append("❌ No es un LONG válido\n");
        }
        
        try {
            double decimal = Double.parseDouble(texto);
            resultado.append("✓ Válido como DOUBLE: ").append(decimal).append("\n");
        } catch (NumberFormatException e) {
            resultado.append("❌ No es un DOUBLE válido\n");
        }
        
        try {
            float flotante = Float.parseFloat(texto);
            resultado.append("✓ Válido como FLOAT: ").append(flotante).append("\n");
        } catch (NumberFormatException e) {
            resultado.append("❌ No es un FLOAT válido\n");
        }
        
        return resultado.toString();
    }
    
    /**
     * Método para probar diferentes casos de conversión.
     */
    public static void probarCasos() {
        System.out.println("\n=== PRUEBAS AUTOMATIZADAS DE CONVERSIÓN ===");
        
        String[] casos = {
            "123",           // Número válido
            "-456",          // Número negativo
            "0",             // Cero
            "12.34",         // Decimal (inválido para Integer.parseInt)
            "abc",           // Texto no numérico
            "123abc",        // Número con texto
            "",              // Cadena vacía
            "  789  ",       // Número con espacios
            "2147483647",    // Integer.MAX_VALUE
            "2147483648",    // Fuera del rango de Integer
        };
        
        for (String caso : casos) {
            try {
                int resultado = convertirAEntero(caso);
                System.out.printf("✓ \"%s\" → %d%n", caso, resultado);
            } catch (NumberFormatException e) {
                System.err.printf("❌ \"%s\" → %s%n", caso, e.getMessage());
            }
        }
    }
    
    /**
     * Método interactivo que permite al usuario probar múltiples conversiones.
     */
    public static void modoInteractivo() {
        Scanner scanner = new Scanner(System.in);
        String entrada;
        
        System.out.println("\n=== MODO INTERACTIVO DE CONVERSIÓN ===");
        System.out.println("Ingrese textos para convertir (escriba 'salir' para terminar):");
        
        do {
            System.out.print("\nTexto a convertir: ");
            entrada = scanner.nextLine();
            
            if (!"salir".equalsIgnoreCase(entrada.trim())) {
                System.out.println(analizarTextoNumerico(entrada));
            }
            
        } while (!"salir".equalsIgnoreCase(entrada.trim()));
        
        System.out.println("¡Gracias por usar el conversor!");
    }
}