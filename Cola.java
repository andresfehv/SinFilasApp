// ==================== COLA (QUEUE) ====================
public class Cola {
    private NodoCola frente;
    private NodoCola finalCola;
    private int tamano;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    public void encolar(Cliente cliente) {
        NodoCola nuevoNodo = new NodoCola(cliente);
        if (estaVacia()) {
            frente = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
        tamano++;
    }

    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }
        Cliente cliente = frente.cliente;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        tamano--;
        return cliente;
    }

    public Cliente verFrente() {
        return estaVacia() ? null : frente.cliente;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamano() {
        return tamano;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        System.out.println("\n=== COLA DE ESPERA ===");
        System.out.println("Nombre               | Documento       | Prioridad  | Hora Llegada | Hora Atención");
        System.out.println("-------------------------------------------------------------------------------------");
        NodoCola actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.cliente);
            actual = actual.siguiente;
            posicion++;
        }
        System.out.println("Total en cola: " + tamano);
    }

    // Búsqueda recursiva por documento
    public Cliente buscarPorDocumentoRecursivo(String documento) {
        return buscarRecursivo(frente, documento);
    }

    private Cliente buscarRecursivo(NodoCola nodo, String documento) {
        if (nodo == null) return null;
        if (nodo.cliente.getDocumento().equals(documento)) return nodo.cliente;
        return buscarRecursivo(nodo.siguiente, documento);
    }

    // Eliminar cliente específico
    public boolean eliminarCliente(String documento) {
        if (estaVacia()) return false;
        
        if (frente.cliente.getDocumento().equals(documento)) {
            desencolar();
            return true;
        }

        NodoCola actual = frente;
        while (actual.siguiente != null) {
            if (actual.siguiente.cliente.getDocumento().equals(documento)) {
                actual.siguiente = actual.siguiente.siguiente;
                if (actual.siguiente == null) {
                    finalCola = actual;
                }
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}