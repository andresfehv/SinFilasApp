// ==================== PILA (STACK) ====================
public class Pila {
    private NodoPila tope;
    private int tamano;

    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    public void apilar(Cliente cliente) {
        NodoPila nuevoNodo = new NodoPila(cliente);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamano++;
    }

    public Cliente desapilar() {
        if (estaVacia()) {
            return null;
        }
        Cliente cliente = tope.cliente;
        tope = tope.siguiente;
        tamano--;
        return cliente;
    }

    public Cliente verTope() {
        return estaVacia() ? null : tope.cliente;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamano() {
        return tamano;
    }
}