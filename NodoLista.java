// ==================== NODO PARA LISTA ====================
public class NodoLista {
    Cliente cliente;
    NodoLista siguiente;

    public NodoLista(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }
}