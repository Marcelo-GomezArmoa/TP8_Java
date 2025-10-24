package parte2.ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Ejercicio 3: Lectura de archivo con manejo de FileNotFoundException.
 * Demuestra el manejo de excepciones checked al leer archivos.
 */
public class LecturaArchivo {
    
    /**
     * Lee un archivo de texto y muestra su contenido.
     */
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== EJERCICIO 3: LECTURA DE ARCHIVO ===");
        System.out.println("Este ejercicio demuestra el manejo de FileNotFoundException");
        
        try {
            System.out.print("Ingrese la ruta del archivo a leer: ");
            String rutaArchivo = scanner.nextLine();
            
            // Intentar leer el archivo
            String contenido = leerArchivo(rutaArchivo);
            
            System.out.println("\n✓ Archivo leído exitosamente:");
            System.out.println("═══════════════════════════════════");
            System.out.println(contenido);
            System.out.println("═══════════════════════════════════");
            System.out.println("Caracteres totales: " + contenido.length());
            System.out.println("Líneas: " + (contenido.split("\n").length));
            
        } catch (FileNotFoundException e) {
            System.err.println("\n❌ Archivo no encontrado: " + e.getMessage());
            System.err.println("Verifique que:");
            System.err.println("• La ruta del archivo sea correcta");
            System.err.println("• El archivo exista en la ubicación especificada");
            System.err.println("• Tenga permisos de lectura sobre el archivo");
        } catch (IOException e) {
            System.err.println("\n❌ Error al leer el archivo: " + e.getMessage());
            System.err.println("Puede ser debido a:");
            System.err.println("• Problemas de permisos");
            System.err.println("• Archivo corrupto");
            System.err.println("• Error de red (si es una ruta de red)");
        } catch (Exception e) {
            System.err.println("\n❌ Error inesperado: " + e.getMessage());
        } finally {
            System.out.println("\n--- Fin del ejercicio de lectura de archivo ---");
        }
    }
    
    /**
     * Lee un archivo de texto y retorna su contenido como String.
     * @param rutaArchivo la ruta del archivo a leer
     * @return el contenido del archivo
     * @throws FileNotFoundException si el archivo no existe
     * @throws IOException si hay problemas al leer el archivo
     */
    private static String leerArchivo(String rutaArchivo) throws FileNotFoundException, IOException {
        StringBuilder contenido = new StringBuilder();
        
        // Usar try-with-resources para manejo automático de recursos
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 1;
            
            while ((linea = reader.readLine()) != null) {
                contenido.append(String.format("%3d: %s%n", numeroLinea, linea));
                numeroLinea++;
            }
            
            if (contenido.length() == 0) {
                contenido.append("(El archivo está vacío)");
            }
            
        } catch (FileNotFoundException e) {
            // Re-lanzar con mensaje más descriptivo
            throw new FileNotFoundException("No se pudo encontrar el archivo: " + rutaArchivo);
        } catch (IOException e) {
            // Re-lanzar con mensaje más descriptivo
            throw new IOException("Error al leer el archivo " + rutaArchivo + ": " + e.getMessage());
        }
        
        return contenido.toString();
    }
    
    /**
     * Método alternativo que usa try-with-resources de forma más explícita.
     * @param rutaArchivo la ruta del archivo a leer
     * @return información sobre el archivo leído
     * @throws FileNotFoundException si el archivo no existe
     * @throws IOException si hay problemas al leer el archivo
     */
    public static String leerArchivoConEstadisticas(String rutaArchivo) throws FileNotFoundException, IOException {
        StringBuilder resultado = new StringBuilder();
        int totalLineas = 0;
        int totalCaracteres = 0;
        int lineasVacias = 0;
        String lineaMasLarga = "";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            
            resultado.append("CONTENIDO DEL ARCHIVO:\n");
            resultado.append("════════════════════════════════════════\n");
            
            while ((linea = reader.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();
                
                if (linea.trim().isEmpty()) {
                    lineasVacias++;
                }
                
                if (linea.length() > lineaMasLarga.length()) {
                    lineaMasLarga = linea;
                }
                
                resultado.append(String.format("%4d │ %s%n", totalLineas, linea));
            }
            
            resultado.append("════════════════════════════════════════\n");
            resultado.append("ESTADÍSTICAS:\n");
            resultado.append("• Total de líneas: ").append(totalLineas).append("\n");
            resultado.append("• Total de caracteres: ").append(totalCaracteres).append("\n");
            resultado.append("• Líneas vacías: ").append(lineasVacias).append("\n");
            resultado.append("• Líneas con contenido: ").append(totalLineas - lineasVacias).append("\n");
            if (!lineaMasLarga.isEmpty()) {
                resultado.append("• Línea más larga (").append(lineaMasLarga.length()).append(" caracteres): ")
                        .append(lineaMasLarga.substring(0, Math.min(50, lineaMasLarga.length())))
                        .append(lineaMasLarga.length() > 50 ? "..." : "").append("\n");
            }
            
        }
        
        return resultado.toString();
    }
    
    /**
     * Crea un archivo de ejemplo para pruebas.
     * @param rutaArchivo la ruta donde crear el archivo
     */
    public static void crearArchivoEjemplo(String rutaArchivo) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(rutaArchivo)) {
            writer.println("Este es un archivo de ejemplo para el TP 8 de Java");
            writer.println("Tema: Interfaces y Excepciones");
            writer.println("");
            writer.println("Contenido de ejemplo:");
            writer.println("- Línea con texto normal");
            writer.println("- Línea con números: 123, 456, 789");
            writer.println("- Línea con caracteres especiales: ñáéíóú@#$%");
            writer.println("");
            writer.println("¡Archivo creado exitosamente!");
            
            System.out.println("✓ Archivo de ejemplo creado en: " + rutaArchivo);
            
        } catch (IOException e) {
            System.err.println("❌ Error al crear archivo de ejemplo: " + e.getMessage());
        }
    }
    
    /**
     * Método para probar diferentes casos de lectura de archivos.
     */
    public static void probarCasos() {
        System.out.println("\n=== PRUEBAS AUTOMATIZADAS DE LECTURA ===");
        
        String[] rutasPrueba = {
            "archivo_existente.txt",     // Si existe
            "archivo_inexistente.txt",   // No existe
            "",                          // Ruta vacía
            "C:\\ruta\\inexistente\\archivo.txt",  // Ruta inválida
        };
        
        for (String ruta : rutasPrueba) {
            try {
                String contenido = leerArchivo(ruta);
                System.out.println("✓ Archivo leído: " + ruta);
                System.out.println("  Primeros 50 caracteres: " + 
                                 contenido.substring(0, Math.min(50, contenido.length())) + "...");
            } catch (FileNotFoundException e) {
                System.err.println("❌ Archivo no encontrado: " + ruta);
            } catch (IOException e) {
                System.err.println("❌ Error de E/S en: " + ruta + " → " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ Error inesperado en: " + ruta + " → " + e.getMessage());
            }
        }
    }
}