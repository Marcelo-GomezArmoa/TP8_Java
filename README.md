# TP 8: Interfaces y Excepciones en Java

**Alumno**: Marcelo, Gomez Armoa  
**Comisión**: 8
**Materia**: Programación II  
**Institución**: Tecnicatura Universitaria en Programación  

## 📋 Descripción del Proyecto

Este proyecto implementa un sistema completo de E-commerce que demuestra el uso avanzado de **interfaces** y **manejo de excepciones** en Java. El trabajo práctico está dividido en dos partes principales que se integran para mostrar las mejores prácticas de programación orientada a objetos.

## 🎯 Objetivos

- **Interfaces**: Implementar contratos de comportamiento y herencia múltiple
- **Excepciones**: Manejar errores de forma robusta y segura
- **Integración**: Combinar ambos conceptos en un sistema real
- **Buenas Prácticas**: Aplicar patrones de diseño y técnicas modernas

## 🏗️ Estructura del Proyecto

```
TP8_Java/
├── src/
│   ├── Main.java                          # Clase principal con menú interactivo
│   ├── parte1/                            # Sistema E-commerce con Interfaces
│   │   ├── interfaces/
│   │   │   ├── Pagable.java              # Interfaz para objetos con total
│   │   │   ├── Pago.java                 # Interfaz base para medios de pago
│   │   │   ├── PagoConDescuento.java     # Interfaz para pagos con descuento
│   │   │   └── Notificable.java          # Interfaz para notificaciones
│   │   └── clases/
│   │       ├── Producto.java             # Producto del e-commerce
│   │       ├── Cliente.java              # Cliente con notificaciones
│   │       ├── Pedido.java               # Pedido con lista de productos
│   │       ├── TarjetaCredito.java       # Medio de pago con tarjeta
│   │       └── PayPal.java               # Medio de pago PayPal
│   └── parte2/                            # Ejercicios de Excepciones
│       ├── excepciones/
│       │   └── EdadInvalidaException.java # Excepción personalizada
│       └── ejercicios/
│           ├── DivisionSegura.java       # ArithmeticException
│           ├── ConversionNumero.java     # NumberFormatException
│           ├── LecturaArchivo.java       # FileNotFoundException
│           ├── ValidadorEdad.java        # Excepción personalizada
│           └── TryWithResources.java     # Try-with-resources
├── .gitignore                            # Archivos a ignorar en Git
└── README.md                             # Este archivo
```

## 🚀 Funcionalidades Principales

### Parte 1: Sistema E-commerce con Interfaces

#### 🏪 **Gestión de Productos**
- Creación de productos con validaciones
- Implementación de interfaz `Pagable` para calcular totales
- Manejo de precios y datos del producto

#### 👥 **Gestión de Clientes**
- Sistema de notificaciones mediante interfaz `Notificable`
- Registro de clientes con datos completos
- Recepción de notificaciones sobre cambios en pedidos

#### 📋 **Gestión de Pedidos**
- Creación y modificación de pedidos
- Agregado/eliminación de productos
- Cálculo automático de totales
- Notificaciones automáticas a clientes
- Cambios de estado con seguimiento

#### 💳 **Medios de Pago**
- **Tarjeta de Crédito**: Con límite de crédito y descuentos
- **PayPal**: Con saldo y verificación de cuenta
- Interfaz `PagoConDescuento` para aplicar promociones
- Procesamiento seguro de pagos

### Parte 2: Manejo de Excepciones

#### ➗ **División Segura**
- Manejo de `ArithmeticException` para división por cero
- Validaciones de entrada y casos especiales
- Múltiples escenarios de prueba

#### 🔢 **Conversión de Números**
- Manejo de `NumberFormatException`
- Conversión de strings a diferentes tipos numéricos
- Análisis completo de formato de entrada

#### 📄 **Lectura de Archivos**
- Manejo de `FileNotFoundException` e `IOException`
- Lectura segura de archivos con estadísticas
- Try-with-resources para manejo automático de recursos

#### 🎂 **Validación de Edad**
- Excepción personalizada `EdadInvalidaException`
- Validaciones contextuales (votación, jubilación, etc.)
- Mensajes de error descriptivos

#### 📚 **Try-with-Resources**
- Manejo automático de recursos
- Comparación con manejo manual tradicional
- Recursos personalizados que implementan `AutoCloseable`

## 🛠️ Compilación y Ejecución

### Requisitos
- Java 8 o superior
- JDK instalado y configurado
- Terminal o IDE compatible

### Compilación
```bash
# Navegar al directorio del proyecto
cd "C:\Users\Usuario\Desktop\Programacion 2\TP8_Java"

# Compilar todos los archivos Java
javac -cp src src/Main.java src/parte1/interfaces/*.java src/parte1/clases/*.java src/parte2/excepciones/*.java src/parte2/ejercicios/*.java
```

### Ejecución
```bash
# Ejecutar el programa principal
java -cp src Main
```

## 📱 Uso del Programa

Al ejecutar el programa, se presenta un menú interactivo con las siguientes opciones:

1. **🏪 Sistema E-commerce**: Demuestra interfaces con un flujo completo de compra
2. **⚡ Ejercicios de Excepciones**: Ejecuta cada tipo de manejo de errores
3. **🎯 Demostración Integrada**: Combina ambas partes en un escenario real
4. **🧪 Pruebas Automatizadas**: Ejecuta todos los casos de prueba

### Ejemplos de Uso

#### Flujo Completo E-commerce:
1. Creación de productos y cliente
2. Armado de pedido con notificaciones automáticas
3. Aplicación de descuentos según el medio de pago
4. Procesamiento de pago con validaciones
5. Generación de reportes

#### Ejercicios de Excepciones:
- **División**: Ingrese dos números para ver el manejo de división por cero
- **Conversión**: Pruebe convertir diferentes textos a números
- **Archivos**: El programa crea archivos de ejemplo automáticamente
- **Edad**: Valide edades con diferentes contextos
- **Recursos**: Observe el manejo automático de archivos

## 🔧 Características Técnicas

### Interfaces Implementadas
- **Pagable**: Contrato para objetos que pueden calcular un total
- **Pago**: Contrato base para medios de pago
- **PagoConDescuento**: Extensión para pagos con descuentos
- **Notificable**: Contrato para objetos que reciben notificaciones

### Excepciones Manejadas
- **ArithmeticException**: División por cero y operaciones inválidas
- **NumberFormatException**: Conversiones numéricas fallidas
- **FileNotFoundException**: Archivos no encontrados
- **IOException**: Errores de entrada/salida
- **EdadInvalidaException**: Validación personalizada de edades

### Patrones de Diseño Aplicados
- **Observer Pattern**: Sistema de notificaciones
- **Strategy Pattern**: Diferentes medios de pago
- **Template Method**: Estructura común de validaciones
- **Factory Pattern**: Creación de objetos con validaciones

## 📊 Casos de Prueba

El programa incluye pruebas automatizadas que cubren:

- ✅ Operaciones aritméticas válidas e inválidas
- ✅ Conversiones de texto a número exitosas y fallidas
- ✅ Lectura de archivos existentes e inexistentes
- ✅ Validaciones de edad con diferentes contextos
- ✅ Manejo correcto de recursos con try-with-resources
- ✅ Flujos completos de e-commerce con y sin errores

## 🎓 Conceptos Demostrados

### Interfaces
- ✅ Definición de contratos de comportamiento
- ✅ Herencia múltiple de interfaces
- ✅ Implementación en clases concretas
- ✅ Polimorfismo con interfaces

### Excepciones
- ✅ Diferencia entre checked y unchecked exceptions
- ✅ Try-catch-finally blocks
- ✅ Try-with-resources para AutoCloseable
- ✅ Creación de excepciones personalizadas
- ✅ Propagación y manejo de errores

### Buenas Prácticas
- ✅ Código limpio y documentado
- ✅ Manejo defensivo de errores
- ✅ Separación de responsabilidades
- ✅ Reutilización de código
- ✅ Validaciones robustas

## 🤝 Contribuciones

Este proyecto es parte del TP 8 de la materia Programación II. Las mejoras y sugerencias son bienvenidas.

## 📞 Contacto

