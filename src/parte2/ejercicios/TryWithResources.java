package parte2.ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Ejercicio 5: Uso de try-with-resources para manejo automático de recursos.
 * Demuestra las buenas prácticas para el manejo de recursos que implementan AutoCloseable.
 */
public class TryWithResources {
    
    /**
     * Demuestra el uso de try-with-resources con BufferedReader.
     * @param rutaArchivo la ruta del archivo a leer
     */
    public static void ejecutar(String rutaArchivo) {
        System.out.println("\n=== EJERCICIO 5: TRY-WITH-RESOURCES ===");
        System.out.println("Este ejercicio demuestra el manejo automático de recursos");
        System.out.println("Archivo a leer: " + rutaArchivo);
        
        // Ejemplo 1: Try-with-resources básico
        leerArchivoConTryWithResources(rutaArchivo);
        
        // Ejemplo 2: Try-with-resources con múltiples recursos
        System.out.println("\n--- Ejemplo con múltiples recursos ---");
        leerArchivoConMultiplesRecursos(rutaArchivo);
        
        // Ejemplo 3: Comparación con manejo manual
        System.out.println("\n--- Comparación con manejo manual ---");
        mostrarDiferenciasManejo(rutaArchivo);
    }
    
    /**
     * Lee un archivo usando try-with-resources.
     * Los recursos se cierran automáticamente al final del bloque try.
     * @param rutaArchivo la ruta del archivo a leer
     */
    public static void leerArchivoConTryWithResources(String rutaArchivo) {
        System.out.println("\n🔄 Leyendo archivo con try-with-resources...");
        
        // El BufferedReader se cierra automáticamente al final del try
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            
            String linea;
            int numeroLinea = 1;
            int totalLineas = 0;
            
            System.out.println("📖 Contenido del archivo:");
            System.out.println("═══════════════════════════════════════");
            
            while ((linea = reader.readLine()) != null) {
                System.out.printf("%3d │ %s%n", numeroLinea, linea);
                numeroLinea++;
                totalLineas++;
            }
            
            System.out.println("═══════════════════════════════════════");
            System.out.println("✓ Archivo leído exitosamente");
            System.out.println("📊 Total de líneas: " + totalLineas);
            
        } catch (IOException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
            System.err.println("Detalles: " + e.getClass().getSimpleName());
            
            // Verificar tipo específico de excepción
            if (e instanceof java.io.FileNotFoundException) {
                System.err.println("💡 Solución: Verifique que el archivo exista en la ruta especificada");
            } else {
                System.err.println("💡 Solución: Verifique permisos de lectura y integridad del archivo");
            }
        } finally {
            System.out.println("🔒 Bloque finally ejecutado - Recursos liberados automáticamente");
        }
    }
    
    /**
     * Demuestra el uso de try-with-resources con múltiples recursos.
     * @param rutaArchivo la ruta del archivo a procesar
     */
    public static void leerArchivoConMultiplesRecursos(String rutaArchivo) {
        System.out.println("\n🔄 Procesando con múltiples recursos...");
        
        // Múltiples recursos separados por punto y coma
        try (
            // Primer recurso: FileReader
            FileReader fileReader = new FileReader(rutaArchivo);
            // Segundo recurso: BufferedReader que envuelve al FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            
            System.out.println("📈 Análisis estadístico del archivo:");
            
            String linea;
            int totalLineas = 0;
            int totalCaracteres = 0;
            int lineasVacias = 0;
            String lineaMasLarga = "";
            String lineaMasCorta = null;
            
            while ((linea = bufferedReader.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();
                
                if (linea.trim().isEmpty()) {
                    lineasVacias++;
                }
                
                if (linea.length() > lineaMasLarga.length()) {
                    lineaMasLarga = linea;
                }
                
                if (lineaMasCorta == null || linea.length() < lineaMasCorta.length()) {
                    lineaMasCorta = linea;
                }
            }
            
            // Mostrar estadísticas
            System.out.println("═══════════════════════════════════════");
            System.out.println("📊 ESTADÍSTICAS DEL ARCHIVO:");
            System.out.println("• Total de líneas: " + totalLineas);
            System.out.println("• Total de caracteres: " + totalCaracteres);
            System.out.println("• Líneas vacías: " + lineasVacias);
            System.out.println("• Líneas con contenido: " + (totalLineas - lineasVacias));
            
            if (totalLineas > 0) {
                System.out.println("• Promedio de caracteres por línea: " + 
                                 String.format("%.1f", (double) totalCaracteres / totalLineas));
                System.out.println("• Línea más larga: " + lineaMasLarga.length() + " caracteres");
                System.out.println("• Línea más corta: " + (lineaMasCorta != null ? lineaMasCorta.length() : 0) + " caracteres");
            }
            System.out.println("═══════════════════════════════════════");
            
        } catch (IOException e) {
            System.err.println("❌ Error durante el análisis: " + e.getMessage());
        } finally {
            System.out.println("🔒 Ambos recursos (FileReader y BufferedReader) cerrados automáticamente");
        }
    }
    
    /**
     * Compara el manejo automático vs manual de recursos.
     * @param rutaArchivo la ruta del archivo a leer
     */
    public static void mostrarDiferenciasManejo(String rutaArchivo) {
        System.out.println("⚖️ COMPARACIÓN: Try-with-resources vs Manejo manual");
        
        // Versión con try-with-resources (RECOMENDADA)
        System.out.println("\n✅ MÉTODO RECOMENDADO - Try-with-resources:");
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String primeraLinea = reader.readLine();
            System.out.println("Primera línea: " + (primeraLinea != null ? primeraLinea : "(archivo vacío)"));
            System.out.println("🎯 Recurso cerrado automáticamente, incluso si ocurre excepción");
        } catch (IOException e) {
            System.err.println("Error (try-with-resources): " + e.getMessage());
        }
        
        // Versión con manejo manual (PROPENSA A ERRORES)
        System.out.println("\n⚠️ MÉTODO TRADICIONAL - Manejo manual:");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(rutaArchivo));
            String primeraLinea = reader.readLine();
            System.out.println("Primera línea: " + (primeraLinea != null ? primeraLinea : "(archivo vacío)"));
        } catch (IOException e) {
            System.err.println("Error (manejo manual): " + e.getMessage());
        } finally {
            // IMPORTANTE: Cerrar manualmente el recurso
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("🔧 Recurso cerrado manualmente en finally");
                } catch (IOException e) {
                    System.err.println("❌ Error al cerrar recurso: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Demuestra try-with-resources con Java NIO (más moderno).
     * @param rutaArchivo la ruta del archivo a leer
     */
    public static void leerArchivoConNIO(String rutaArchivo) {
        System.out.println("\n🆕 MÉTODO MODERNO - Java NIO con try-with-resources:");
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(rutaArchivo))) {
            
            // Leer todas las líneas usando streams (Java 8+)
            long totalLineas = reader.lines()
                    .peek(linea -> System.out.println("📝 " + linea)) // Mostrar cada línea
                    .count();
            
            System.out.println("📊 Total de líneas procesadas: " + totalLineas);
            
        } catch (IOException e) {
            System.err.println("❌ Error con NIO: " + e.getMessage());
        }
    }
    
    /**
     * Ejemplo de recurso personalizado que implementa AutoCloseable.
     */
    public static class RecursoPersonalizado implements AutoCloseable {
        private String nombre;
        private boolean cerrado = false;
        
        public RecursoPersonalizado(String nombre) {
            this.nombre = nombre;
            System.out.println("🔓 Recurso abierto: " + nombre);
        }
        
        public void usar() {
            if (cerrado) {
                throw new IllegalStateException("El recurso " + nombre + " ya está cerrado");
            }
            System.out.println("⚙️ Usando recurso: " + nombre);
        }
        
        @Override
        public void close() {
            if (!cerrado) {
                cerrado = true;
                System.out.println("🔒 Recurso cerrado automáticamente: " + nombre);
            }
        }
        
        public boolean isCerrado() {
            return cerrado;
        }
    }
    
    /**
     * Demuestra el uso de try-with-resources con recurso personalizado.
     */
    public static void demostrarRecursoPersonalizado() {
        System.out.println("\n🛠️ RECURSO PERSONALIZADO con AutoCloseable:");
        
        try (RecursoPersonalizado recurso = new RecursoPersonalizado("MiRecurso")) {
            recurso.usar();
            recurso.usar();
            System.out.println("✅ Operaciones completadas exitosamente");
            // El recurso se cierra automáticamente aquí
        } catch (Exception e) {
            System.err.println("❌ Error con recurso personalizado: " + e.getMessage());
        }
        
        System.out.println("🎯 El recurso se cerró automáticamente al salir del bloque try");
    }
    
    /**
     * Crea un archivo de ejemplo para las pruebas.
     * @param rutaArchivo la ruta donde crear el archivo
     */
    public static void crearArchivoEjemplo(String rutaArchivo) {
        System.out.println("\n📝 Creando archivo de ejemplo...");
        
        try (java.io.PrintWriter writer = new java.io.PrintWriter(rutaArchivo)) {
            writer.println("=== ARCHIVO DE EJEMPLO PARA TRY-WITH-RESOURCES ===");
            writer.println("Este archivo demuestra el manejo automático de recursos.");
            writer.println("");
            writer.println("Ventajas del try-with-resources:");
            writer.println("1. Cierre automático de recursos");
            writer.println("2. Código más limpio y legible");
            writer.println("3. Prevención de memory leaks");
            writer.println("4. Manejo automático de excepciones en close()");
            writer.println("");
            writer.println("Línea corta");
            writer.println("Esta es una línea considerablemente más larga que las anteriores para demostrar el análisis de longitud");
            writer.println("¡Try-with-resources es la mejor práctica en Java 7+!");
            
            System.out.println("✅ Archivo de ejemplo creado: " + rutaArchivo);
            
        } catch (IOException e) {
            System.err.println("❌ Error al crear archivo de ejemplo: " + e.getMessage());
        }
    }
}