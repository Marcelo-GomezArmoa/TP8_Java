package parte1.clases;

import parte1.interfaces.Pagable;
import parte1.interfaces.Notificable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pedido en el sistema de e-commerce.
 * Implementa Pagable para calcular el total del pedido.
 * Mantiene una lista de clientes notificables para informar cambios de estado.
 */
public class Pedido implements Pagable {
    private static int contadorPedidos = 1;
    
    private int numeroPedido;
    private List<Producto> productos;
    private String estado;
    private List<Notificable> clientesNotificables;
    
    /**
     * Constructor para crear un pedido vacío.
     */
    public Pedido() {
        this.numeroPedido = contadorPedidos++;
        this.productos = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.clientesNotificables = new ArrayList<>();
    }
    
    /**
     * Agrega un producto al pedido.
     * @param producto el producto a agregar
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        notificarCambio("Producto agregado: " + producto.getNombre() + 
                       " - Total del pedido: $" + String.format("%.2f", calcularTotal()));
    }
    
    /**
     * Elimina un producto del pedido.
     * @param producto el producto a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarProducto(Producto producto) {
        boolean eliminado = productos.remove(producto);
        if (eliminado) {
            notificarCambio("Producto eliminado: " + producto.getNombre() + 
                           " - Total del pedido: $" + String.format("%.2f", calcularTotal()));
        }
        return eliminado;
    }
    
    /**
     * Implementación del método calcularTotal de la interfaz Pagable.
     * Calcula la suma total de todos los productos en el pedido.
     * @return el total del pedido
     */
    @Override
    public double calcularTotal() {
        return productos.stream()
                       .mapToDouble(Producto::calcularTotal)
                       .sum();
    }
    
    /**
     * Cambia el estado del pedido y notifica a los clientes suscritos.
     * @param nuevoEstado el nuevo estado del pedido
     */
    public void cambiarEstado(String nuevoEstado) {
        String estadoAnterior = this.estado;
        this.estado = nuevoEstado;
        notificarCambio("Estado del pedido #" + numeroPedido + 
                       " cambió de '" + estadoAnterior + "' a '" + nuevoEstado + "'");
    }
    
    /**
     * Agrega un cliente para recibir notificaciones sobre este pedido.
     * @param cliente el cliente a notificar
     */
    public void agregarClienteNotificable(Notificable cliente) {
        if (!clientesNotificables.contains(cliente)) {
            clientesNotificables.add(cliente);
        }
    }
    
    /**
     * Elimina un cliente de la lista de notificaciones.
     * @param cliente el cliente a eliminar
     */
    public void eliminarClienteNotificable(Notificable cliente) {
        clientesNotificables.remove(cliente);
    }
    
    /**
     * Notifica un cambio a todos los clientes suscritos.
     * @param mensaje el mensaje a enviar
     */
    private void notificarCambio(String mensaje) {
        for (Notificable cliente : clientesNotificables) {
            cliente.recibirNotificacion(mensaje);
        }
    }
    
    // Getters y setters
    public int getNumeroPedido() {
        return numeroPedido;
    }
    
    public List<Producto> getProductos() {
        return new ArrayList<>(productos); // Retorna una copia para evitar modificaciones externas
    }
    
    public String getEstado() {
        return estado;
    }
    
    public int getCantidadProductos() {
        return productos.size();
    }
    
    @Override
    public String toString() {
        return String.format("Pedido{numero=%d, estado='%s', productos=%d, total=$%.2f}", 
                           numeroPedido, estado, productos.size(), calcularTotal());
    }
}