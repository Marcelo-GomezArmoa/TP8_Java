import parte1.clases.*;
import parte1.interfaces.*;
import parte2.ejercicios.*;
import parte2.excepciones.EdadInvalidaException;

import java.util.Scanner;

/**
 * Clase principal del TP 8: Interfaces y Excepciones en Java.
 * Demuestra el uso integrado de interfaces y manejo de excepciones.
 * 
 * @author [Tu Nombre]
 * @version 1.0
 * @since 2024
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarBienvenida();
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    demostrarParte1();
                    break;
                case 2:
                    ejecutarEjerciciosExcepciones();
                    break;
                case 3:
                    demostrarIntegracionCompleta();
                    break;
                case 4:
                    ejecutarPruebasAutomatizadas();
                    break;
                case 0:
                    continuar = false;
                    mostrarDespedida();
                    break;
                default:
                    System.out.println("❌ Opción inválida. Por favor, seleccione una opción del 0 al 4.");
            }
            
            if (continuar) {
                pausar();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el mensaje de bienvenida del programa.
     */
    private static void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TP 8 - JAVA AVANZADO                     ║");
        System.out.println("║              INTERFACES Y EXCEPCIONES EN JAVA               ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  👥 Interfaces: Contratos de comportamiento                 ║");
        System.out.println("║  ⚡ Excepciones: Manejo robusto de errores                  ║");
        System.out.println("║  🏪 Sistema E-commerce con patrones de diseño               ║");
        System.out.println("║  🛡️ Validaciones y buenas prácticas                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Muestra el menú principal de opciones.
     */
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                      MENÚ PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("1. 🏪 Demostrar Parte 1: Sistema E-commerce con Interfaces");
        System.out.println("2. ⚡ Ejecutar Parte 2: Ejercicios de Excepciones");
        System.out.println("3. 🎯 Demostración Integrada (Partes 1 y 2)");
        System.out.println("4. 🧪 Ejecutar Pruebas Automatizadas");
        System.out.println("0. 🚪 Salir del programa");
        System.out.println("=".repeat(60));
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Lee una opción del menú de forma segura.
     * @return la opción seleccionada por el usuario
     */
    private static int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            return -1; // Opción inválida
        }
    }
    
    /**
     * Demuestra la Parte 1: Sistema E-commerce con interfaces.
     */
    private static void demostrarParte1() {
        System.out.println("\n" + "🏪".repeat(20) + " PARTE 1: E-COMMERCE " + "🏪".repeat(20));
        
        try {
            // 1. Crear productos
            System.out.println("\n📦 CREANDO PRODUCTOS...");
            Producto laptop = new Producto("Laptop Gaming", 1200.00);
            Producto mouse = new Producto("Mouse Inalámbrico", 25.99);
            Producto teclado = new Producto("Teclado Mecánico", 89.50);
            
            System.out.println("✅ Productos creados:");
            System.out.println("  • " + laptop);
            System.out.println("  • " + mouse);
            System.out.println("  • " + teclado);
            
            // 2. Crear cliente
            System.out.println("\n👤 CREANDO CLIENTE...");
            Cliente cliente = new Cliente("Juan Pérez", "juan.perez@email.com", "CLI-001");
            System.out.println("✅ Cliente creado: " + cliente);
            
            // 3. Crear pedido y agregar cliente para notificaciones
            System.out.println("\n📋 CREANDO PEDIDO...");
            Pedido pedido = new Pedido();
            pedido.agregarClienteNotificable(cliente);
            System.out.println("✅ Pedido creado: " + pedido);
            
            // 4. Agregar productos al pedido (demostrará notificaciones)
            System.out.println("\n➕ AGREGANDO PRODUCTOS AL PEDIDO...");
            pedido.agregarProducto(laptop);
            pedido.agregarProducto(mouse);
            pedido.agregarProducto(teclado);
            
            // 5. Mostrar total del pedido (interfaz Pagable)
            System.out.println("\n💰 TOTAL DEL PEDIDO:");
            System.out.printf("Total a pagar: $%.2f%n", pedido.calcularTotal());
            
            // 6. Cambiar estado del pedido (más notificaciones)
            System.out.println("\n🔄 CAMBIANDO ESTADO DEL PEDIDO...");
            pedido.cambiarEstado("PROCESANDO");
            pedido.cambiarEstado("ENVIADO");
            
            // 7. Crear medios de pago
            System.out.println("\n💳 CREANDO MEDIOS DE PAGO...");
            TarjetaCredito tarjeta = new TarjetaCredito("1234567890123456", "Juan Pérez", "12/25", 2000.00);
            PayPal paypal = new PayPal("juan.perez@email.com", 1500.00, true);
            
            System.out.println("✅ Medios de pago creados:");
            System.out.println("  • " + tarjeta);
            System.out.println("  • " + paypal);
            
            // 8. Procesar pago con descuento
            System.out.println("\n💸 PROCESANDO PAGO CON DESCUENTO...");
            double totalPedido = pedido.calcularTotal();
            
            // Aplicar descuento con PayPal
            double totalConDescuento = paypal.aplicarDescuento(totalPedido, 10.0);
            System.out.printf("Total original: $%.2f%n", totalPedido);
            System.out.printf("Total con descuento: $%.2f%n", totalConDescuento);
            
            // Procesar el pago
            boolean pagoExitoso = paypal.procesarPago(totalConDescuento);
            
            if (pagoExitoso) {
                pedido.cambiarEstado("PAGADO");
                System.out.println("🎉 ¡Pago procesado exitosamente!");
            } else {
                System.out.println("❌ Error al procesar el pago");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error en la demostración: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Ejecuta los ejercicios de excepciones de la Parte 2.
     */
    private static void ejecutarEjerciciosExcepciones() {
        System.out.println("\n" + "⚡".repeat(20) + " PARTE 2: EXCEPCIONES " + "⚡".repeat(20));
        
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n📋 SELECCIONE UN EJERCICIO:");
            System.out.println("1. ➗ División Segura (ArithmeticException)");
            System.out.println("2. 🔢 Conversión de Número (NumberFormatException)");
            System.out.println("3. 📄 Lectura de Archivo (FileNotFoundException)");
            System.out.println("4. 🎂 Validación de Edad (Excepción Personalizada)");
            System.out.println("5. 📚 Try-with-Resources");
            System.out.println("0. ↩️ Volver al menú principal");
            System.out.print("Opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    DivisionSegura.ejecutar();
                    break;
                case 2:
                    ConversionNumero.ejecutar();
                    break;
                case 3:
                    // Crear archivo de ejemplo si no existe
                    String rutaArchivo = "ejemplo.txt";
                    LecturaArchivo.crearArchivoEjemplo(rutaArchivo);
                    LecturaArchivo.ejecutar();
                    break;
                case 4:
                    ValidadorEdad.ejecutar();
                    break;
                case 5:
                    String rutaEjemplo = "try-with-resources-ejemplo.txt";
                    TryWithResources.crearArchivoEjemplo(rutaEjemplo);
                    TryWithResources.ejecutar(rutaEjemplo);
                    TryWithResources.demostrarRecursoPersonalizado();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }
    
    /**
     * Demuestra la integración completa de interfaces y excepciones.
     */
    private static void demostrarIntegracionCompleta() {
        System.out.println("\n" + "🎯".repeat(15) + " DEMOSTRACIÓN INTEGRADA " + "🎯".repeat(15));
        System.out.println("Esta sección combina interfaces y excepciones en un escenario real.");
        
        try {
            // Escenario: Procesamiento de pedido con validaciones
            System.out.println("\n🎬 ESCENARIO: Procesamiento completo de pedido con validaciones");
            
            // 1. Crear cliente con validación de edad
            System.out.println("\n👤 Creando cliente con validación...");
            Cliente cliente = new Cliente("María García", "maria@email.com", "CLI-002");
            
            // Validar edad del cliente
            try {
                ValidadorEdad.validarEdadConContexto(25, "universidad");
                System.out.println("✅ Edad del cliente validada correctamente");
            } catch (EdadInvalidaException e) {
                System.err.println("❌ " + e.getMessage());
                return; // Salir si la edad es inválida
            }
            
            // 2. Crear productos con validaciones
            System.out.println("\n📦 Creando productos con validaciones...");
            Producto[] productos = {
                new Producto("Smartphone", 699.99),
                new Producto("Auriculares", 149.50),
                new Producto("Cargador", 29.99)
            };
            
            // 3. Crear pedido y configurar notificaciones
            Pedido pedido = new Pedido();
            pedido.agregarClienteNotificable(cliente);
            
            for (Producto producto : productos) {
                pedido.agregarProducto(producto);
            }
            
            // 4. Calcular total con manejo de errores
            double total = pedido.calcularTotal();
            System.out.printf("\n💰 Total del pedido: $%.2f%n", total);
            
            // 5. Crear archivo de reporte del pedido usando try-with-resources
            String archivoReporte = "reporte_pedido_" + pedido.getNumeroPedido() + ".txt";
            System.out.println("\n📄 Generando reporte del pedido...");
            
            try (java.io.PrintWriter writer = new java.io.PrintWriter(archivoReporte)) {
                writer.println("=== REPORTE DE PEDIDO ===");
                writer.println("Cliente: " + cliente.getNombre());
                writer.println("Email: " + cliente.getEmail());
                writer.println("Pedido #: " + pedido.getNumeroPedido());
                writer.println("Estado: " + pedido.getEstado());
                writer.println("\nPRODUCTOS:");
                
                for (Producto producto : pedido.getProductos()) {
                    writer.printf("- %s: $%.2f%n", producto.getNombre(), producto.getPrecio());
                }
                
                writer.printf("\nTOTAL: $%.2f%n", total);
                writer.println("\nReporte generado: " + java.time.LocalDateTime.now());
                
                System.out.println("✅ Reporte generado exitosamente: " + archivoReporte);
            }
            
            // 6. Procesar pago con manejo de múltiples excepciones
            System.out.println("\n💳 Procesando pago...");
            PayPal paypal = new PayPal(cliente.getEmail(), 1000.0, true);
            
            try {
                // Intentar aplicar descuento
                double totalConDescuento = paypal.aplicarDescuento(total, 15.0);
                
                // Procesar pago
                if (paypal.procesarPago(totalConDescuento)) {
                    pedido.cambiarEstado("PAGADO");
                    pedido.cambiarEstado("COMPLETADO");
                    System.out.println("🎉 ¡Pedido procesado exitosamente!");
                } else {
                    throw new RuntimeException("Pago rechazado por el proveedor");
                }
                
            } catch (Exception e) {
                System.err.println("❌ Error al procesar pago: " + e.getMessage());
                pedido.cambiarEstado("ERROR_PAGO");
            }
            
            // 7. Leer el reporte generado
            System.out.println("\n📖 Leyendo reporte generado...");
            TryWithResources.leerArchivoConTryWithResources(archivoReporte);
            
        } catch (Exception e) {
            System.err.println("❌ Error en la demostración integrada: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Ejecuta todas las pruebas automatizadas del proyecto.
     */
    private static void ejecutarPruebasAutomatizadas() {
        System.out.println("\n" + "🧪".repeat(15) + " PRUEBAS AUTOMATIZADAS " + "🧪".repeat(15));
        
        System.out.println("\n🔬 Ejecutando batería completa de pruebas...");
        
        // Pruebas de División Segura
        System.out.println("\n1️⃣ PRUEBAS DE DIVISIÓN SEGURA:");
        DivisionSegura.probarCasos();
        
        // Pruebas de Conversión de Números
        System.out.println("\n2️⃣ PRUEBAS DE CONVERSIÓN DE NÚMEROS:");
        ConversionNumero.probarCasos();
        
        // Pruebas de Lectura de Archivos
        System.out.println("\n3️⃣ PRUEBAS DE LECTURA DE ARCHIVOS:");
        LecturaArchivo.probarCasos();
        
        // Pruebas de Validación de Edad
        System.out.println("\n4️⃣ PRUEBAS DE VALIDACIÓN DE EDAD:");
        ValidadorEdad.probarCasos();
        ValidadorEdad.probarValidacionesConContexto();
        
        // Pruebas de recursos personalizados
        System.out.println("\n5️⃣ PRUEBAS DE TRY-WITH-RESOURCES:");
        TryWithResources.demostrarRecursoPersonalizado();
        
        System.out.println("\n✅ Todas las pruebas automatizadas completadas.");
    }
    
    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     */
    private static void pausar() {
        System.out.println("\n" + "─".repeat(60));
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine(); // Consumir el Enter pendiente
        scanner.nextLine(); // Esperar nuevo Enter
    }
    
    /**
     * Muestra el mensaje de despedida.
     */
    private static void mostrarDespedida() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                         ¡GRACIAS!                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ✅ TP 8 completado exitosamente                            ║");
        System.out.println("║  🎓 Interfaces y Excepciones dominadas                      ║");
        System.out.println("║  💡 Conocimientos aplicados en proyecto real                ║");
        System.out.println("║  🚀 ¡Listo para el siguiente desafío!                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println("\n👋 Programa finalizado. ¡Hasta pronto!");
    }
}