// ==================== LISTA ENLAZADA ====================
public class ListaEnlazada {
    private NodoLista cabeza;
    private int tamano;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregar(Cliente cliente) {
        NodoLista nuevoNodo = new NodoLista(cliente);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("No hay clientes atendidos.");
            return;
        }
        System.out.println("\n=== CLIENTES ATENDIDOS ===");
        System.out.println("Nombre               | Documento       | Prioridad  | Hora Llegada | Hora Atención");
        System.out.println("-------------------------------------------------------------------------------------");
        NodoLista actual = cabeza;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.cliente);
            actual = actual.siguiente;
            posicion++;
        }
        System.out.println("Total atendidos: " + tamano);
    }

    public int getTamano() {
        return tamano;
    }

    // Búsqueda secuencial
    public Cliente busquedaSecuencial(String nombre) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.cliente.getNombre().equalsIgnoreCase(nombre)) {
                return actual.cliente;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // Ordenamiento Burbuja por nombre
    public void ordenarBurbujaPorNombre() {
        if (cabeza == null || cabeza.siguiente == null) return;
        
        boolean intercambio;
        do {
            intercambio = false;
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.cliente.getNombre().compareToIgnoreCase(actual.siguiente.cliente.getNombre()) > 0) {
                    Cliente temp = actual.cliente;
                    actual.cliente = actual.siguiente.cliente;
                    actual.siguiente.cliente = temp;
                    intercambio = true;
                }
                actual = actual.siguiente;
            }
        } while (intercambio);
    }

    // Ordenamiento por Inserción por prioridad
    public void ordenarInsercionPorPrioridad() {
        if (cabeza == null || cabeza.siguiente == null) return;

        ListaEnlazada listaOrdenada = new ListaEnlazada();
        NodoLista actual = cabeza;

        while (actual != null) {
            Cliente clienteActual = actual.cliente;
            insertarOrdenado(listaOrdenada, clienteActual);
            actual = actual.siguiente;
        }

        this.cabeza = listaOrdenada.cabeza;
    }

    private void insertarOrdenado(ListaEnlazada lista, Cliente cliente) {
        NodoLista nuevoNodo = new NodoLista(cliente);
        
        if (lista.cabeza == null || lista.cabeza.cliente.getPrioridad() > cliente.getPrioridad()) {
            nuevoNodo.siguiente = lista.cabeza;
            lista.cabeza = nuevoNodo;
        } else {
            NodoLista actual = lista.cabeza;
            while (actual.siguiente != null && actual.siguiente.cliente.getPrioridad() <= cliente.getPrioridad()) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
    }

    // Convertir lista a arreglo para ordenamiento por selección
    public Cliente[] aArreglo() {
        if (cabeza == null) return new Cliente[0];
        
        Cliente[] arreglo = new Cliente[tamano];
        NodoLista actual = cabeza;
        int i = 0;
        while (actual != null) {
            arreglo[i++] = actual.cliente;
            actual = actual.siguiente;
        }
        return arreglo;
    }

    public void desdeArreglo(Cliente[] arreglo) {
        cabeza = null;
        tamano = 0;
        for (Cliente cliente : arreglo) {
            agregar(cliente);
        }
    }
}