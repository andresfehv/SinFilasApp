// ==================== NODO PARA COLA ====================
public class NodoCola {
    Cliente cliente;
    NodoCola siguiente;

    public NodoCola(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }
}