// ==================== NODO PARA ÁRBOL ====================
public class NodoArbol {
    Cliente cliente;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(Cliente cliente) {
        this.cliente = cliente;
        this.izquierdo = null;
        this.derecho = null;
    }
}