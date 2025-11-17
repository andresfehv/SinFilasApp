// ==================== NODO PARA PILA ====================
public class NodoPila {
    Cliente cliente;
    NodoPila siguiente;

    public NodoPila(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }
}