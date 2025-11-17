// ==================== ÁRBOL BINARIO DE BÚSQUEDA ====================
public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public void insertar(Cliente cliente) {
        raiz = insertarRecursivo(raiz, cliente);
    }

    private NodoArbol insertarRecursivo(NodoArbol nodo, Cliente cliente) {
        if (nodo == null) {
            return new NodoArbol(cliente);
        }

        int comparacion = cliente.getDocumento().compareTo(nodo.cliente.getDocumento());
        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, cliente);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, cliente);
        }

        return nodo;
    }

    public Cliente buscar(String documento) {
        return buscarRecursivo(raiz, documento);
    }

    private Cliente buscarRecursivo(NodoArbol nodo, String documento) {
        if (nodo == null) {
            return null;
        }

        int comparacion = documento.compareTo(nodo.cliente.getDocumento());
        if (comparacion == 0) {
            return nodo.cliente;
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierdo, documento);
        } else {
            return buscarRecursivo(nodo.derecho, documento);
        }
    }

    public void mostrarInorden() {
        System.out.println("\n=== ÁRBOL DE CLIENTES (Inorden) ===");
        mostrarInordenRecursivo(raiz);
    }

    private void mostrarInordenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            mostrarInordenRecursivo(nodo.izquierdo);
            System.out.println(nodo.cliente);
            mostrarInordenRecursivo(nodo.derecho);
        }
    }

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodosRecursivo(nodo.izquierdo) + contarNodosRecursivo(nodo.derecho);
    }

    public int calcularAltura() {
        return calcularAlturaRecursivo(raiz);
    }

    private int calcularAlturaRecursivo(NodoArbol nodo) {
        if (nodo == null) return 0;
        int alturaIzq = calcularAlturaRecursivo(nodo.izquierdo);
        int alturaDer = calcularAlturaRecursivo(nodo.derecho);
        return 1 + Math.max(alturaIzq, alturaDer);
    }
}