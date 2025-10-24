package parte2.ejercicios;

import parte2.excepciones.EdadInvalidaException;
import java.util.Scanner;

/**
 * Ejercicio 4: Uso de excepción personalizada EdadInvalidaException.
 * Demuestra la creación y uso de excepciones personalizadas.
 */
public class ValidadorEdad {
    
    /**
     * Solicita una edad al usuario y la valida usando la excepción personalizada.
     */
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== EJERCICIO 4: EXCEPCIÓN PERSONALIZADA ===");
        System.out.println("Este ejercicio demuestra el uso de EdadInvalidaException");
        
        try {
            System.out.print("Ingrese su edad: ");
            int edad = scanner.nextInt();
            
            // Validar la edad usando la excepción personalizada
            validarEdad(edad);
            
            System.out.println("\n✓ Edad válida: " + edad + " años");
            System.out.println(obtenerMensajeSegunEdad(edad));
            
        } catch (EdadInvalidaException e) {
            System.err.println("\n❌ " + e.getMessage());
            System.err.println("\nDetalles del error:");
            System.err.println(e.getDescripcionDetallada());
        } catch (Exception e) {
            System.err.println("\n❌ Error inesperado: " + e.getMessage());
            System.err.println("Asegúrese de ingresar un número entero válido.");
        } finally {
            System.out.println("\n--- Fin del ejercicio de validación de edad ---");
        }
    }
    
    /**
     * Valida que una edad esté dentro del rango válido (0-120).
     * @param edad la edad a validar
     * @throws EdadInvalidaException si la edad está fuera del rango válido
     */
    public static void validarEdad(int edad) throws EdadInvalidaException {
        if (edad < 0) {
            throw new EdadInvalidaException("La edad no puede ser negativa", edad);
        }
        
        if (edad > 120) {
            throw new EdadInvalidaException("La edad no puede ser mayor a 120 años", edad);
        }
        
        // Si llegamos aquí, la edad es válida
        System.out.println("Validación exitosa: La edad " + edad + " está dentro del rango válido.");
    }
    
    /**
     * Obtiene un mensaje personalizado según el rango de edad.
     * @param edad la edad válida
     * @return mensaje personalizado
     */
    private static String obtenerMensajeSegunEdad(int edad) {
        if (edad == 0) {
            return "¡Felicidades por el recién nacido!";
        } else if (edad >= 1 && edad <= 12) {
            return "Categoría: Niño/a. ¡Qué edad tan divertida para jugar y aprender!";
        } else if (edad >= 13 && edad <= 17) {
            return "Categoría: Adolescente. ¡Una etapa llena de descubrimientos!";
        } else if (edad >= 18 && edad <= 64) {
            return "Categoría: Adulto. ¡La edad perfecta para grandes aventuras!";
        } else if (edad >= 65 && edad <= 120) {
            return "Categoría: Adulto mayor. ¡Sabiduría y experiencia son tus tesoros!";
        }
        
        return "Edad procesada correctamente.";
    }
    
    /**
     * Versión avanzada que valida múltiples aspectos de la edad.
     * @param edad la edad a validar
     * @param contexto el contexto de la validación (ej: "registro", "votación", etc.)
     * @throws EdadInvalidaException si la edad no cumple los criterios
     */
    public static void validarEdadConContexto(int edad, String contexto) throws EdadInvalidaException {
        // Validación básica
        validarEdad(edad);
        
        // Validaciones específicas según el contexto
        switch (contexto.toLowerCase()) {
            case "votacion":
                if (edad < 18) {
                    throw new EdadInvalidaException("Para votar debe ser mayor de edad (18+ años)", edad);
                }
                break;
                
            case "jubilacion":
                if (edad < 60) {
                    throw new EdadInvalidaException("La edad de jubilación mínima es 60 años", edad);
                }
                break;
                
            case "licencia":
                if (edad < 16) {
                    throw new EdadInvalidaException("Para obtener licencia debe tener al menos 16 años", edad);
                }
                break;
                
            case "universidad":
                if (edad < 17 || edad > 80) {
                    throw new EdadInvalidaException("Edad fuera del rango típico universitario (17-80 años)", edad);
                }
                break;
                
            default:
                // Solo validación básica para contextos desconocidos
                break;
        }
        
        System.out.println("✓ Edad válida para el contexto: " + contexto);
    }
    
    /**
     * Método para probar diferentes casos de validación de edad.
     */
    public static void probarCasos() {
        System.out.println("\n=== PRUEBAS AUTOMATIZADAS DE VALIDACIÓN ===");
        
        int[] edadesPrueba = {-5, 0, 10, 17, 18, 25, 65, 100, 120, 130};
        
        for (int edad : edadesPrueba) {
            try {
                validarEdad(edad);
                System.out.printf("✓ Edad %d: VÁLIDA%n", edad);
            } catch (EdadInvalidaException e) {
                System.err.printf("❌ Edad %d: %s%n", edad, e.getMessage());
            }
        }
    }
    
    /**
     * Método para probar validaciones con contexto.
     */
    public static void probarValidacionesConContexto() {
        System.out.println("\n=== PRUEBAS DE VALIDACIÓN CON CONTEXTO ===");
        
        Object[][] casos = {
            {25, "votacion"},      // Válido
            {16, "votacion"},      // Inválido - menor de edad
            {65, "jubilacion"},    // Válido
            {50, "jubilacion"},    // Inválido - muy joven
            {17, "licencia"},      // Válido
            {15, "licencia"},      // Inválido - muy joven
            {20, "universidad"},   // Válido
            {90, "universidad"},   // Límite superior
        };
        
        for (Object[] caso : casos) {
            int edad = (Integer) caso[0];
            String contexto = (String) caso[1];
            
            try {
                validarEdadConContexto(edad, contexto);
                System.out.printf("✓ Edad %d para %s: VÁLIDA%n", edad, contexto);
            } catch (EdadInvalidaException e) {
                System.err.printf("❌ Edad %d para %s: %s%n", edad, contexto, e.getMessage());
            }
        }
    }
    
    /**
     * Clase interna para representar una persona con edad validada.
     */
    public static class Persona {
        private String nombre;
        private int edad;
        
        public Persona(String nombre, int edad) throws EdadInvalidaException {
            this.nombre = nombre;
            validarEdad(edad); // Lanza excepción si es inválida
            this.edad = edad;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public int getEdad() {
            return edad;
        }
        
        public void cumplirAnios() throws EdadInvalidaException {
            int nuevaEdad = this.edad + 1;
            validarEdad(nuevaEdad); // Validar antes de asignar
            this.edad = nuevaEdad;
            System.out.println("¡Feliz cumpleaños " + nombre + "! Ahora tienes " + edad + " años.");
        }
        
        @Override
        public String toString() {
            return String.format("Persona{nombre='%s', edad=%d}", nombre, edad);
        }
    }
}