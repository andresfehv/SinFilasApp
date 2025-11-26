// ==================== SISTEMA PRINCIPAL ====================
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SinFilasApp {
    private Cola colaEspera;
    private ListaEnlazada clientesAtendidos;
    private Pila historialAtenciones;
    private ArbolBinarioBusqueda arbolClientes;
    private Scanner scanner;
    private DateTimeFormatter formatoHora;

    public SinFilasApp() {
        this.colaEspera = new Cola();
        this.clientesAtendidos = new ListaEnlazada();
        this.historialAtenciones = new Pila();
        this.arbolClientes = new ArbolBinarioBusqueda();
        this.scanner = new Scanner(System.in);
        this.formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
        }
        System.out.println("\n¡Gracias por usar SinFilasApp! Hasta pronto.");
    }

    private void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         SINFILAS APP - MENÚ            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1.  Registrar nuevo cliente");
        System.out.println("2.  Ver cola de espera");
        System.out.println("3.  Atender siguiente cliente");
        System.out.println("4.  Ver clientes atendidos");
        System.out.println("5.  Buscar cliente en cola (Recursivo)");
        System.out.println("6.  Buscar cliente en árbol (BST)");
        System.out.println("7.  Buscar cliente atendido (Secuencial)");
        System.out.println("8.  Modificar cliente en cola");
        System.out.println("9.  Eliminar cliente de cola");
        System.out.println("10. Deshacer última atención");
        System.out.println("11. Ordenar atendidos (Burbuja - Nombre)");
        System.out.println("12. Ordenar atendidos (Inserción - Prioridad)");
        System.out.println("13. Ordenar atendidos (Selección - Documento)");
        System.out.println("14. Ver estadísticas");
        System.out.println("15. Mostrar árbol de clientes");
        System.out.println("0.  Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: registrarCliente(); break;
            case 2: colaEspera.mostrar(); break;
            case 3: atenderCliente(); break;
            case 4: clientesAtendidos.mostrar(); break;
            case 5: buscarClienteEnCola(); break;
            case 6: buscarClienteEnArbol(); break;
            case 7: buscarClienteAtendido(); break;
            case 8: modificarCliente(); break;
            case 9: eliminarClienteDeCola(); break;
            case 10: deshacerAtencion(); break;
            case 11: ordenarPorNombre(); break;
            case 12: ordenarPorPrioridad(); break;
            case 13: ordenarPorDocumento(); break;
            case 14: mostrarEstadisticas(); break;
            case 15: arbolClientes.mostrarInorden(); break;
            case 0: return false;
            default: System.out.println("❌ Opción inválida. Intente nuevamente.");
        }
        return true;
    }

    private void registrarCliente() {
        System.out.println("\n=== REGISTRO DE CLIENTE ===");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Documento: ");
        String documento = scanner.nextLine().trim();
        if (documento.isEmpty()) {
            System.out.println("❌ El documento no puede estar vacío.");
            return;
        }

        System.out.println("Prioridad (1=Alta, 2=Media, 3=Baja): ");
        int prioridad = leerOpcion();
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("❌ Prioridad inválida.");
            return;
        }

        String horaLlegada = LocalTime.now().format(formatoHora);
        Cliente nuevoCliente = new Cliente(nombre, documento, prioridad, horaLlegada);
        
        colaEspera.encolar(nuevoCliente);
        arbolClientes.insertar(nuevoCliente);
        
        System.out.println("✅ Cliente registrado exitosamente a las " + horaLlegada);
    }

    private void atenderCliente() {
        Cliente cliente = colaEspera.desencolar();
        if (cliente == null) {
            System.out.println("❌ No hay clientes en la cola.");
            return;
        }

        String horaAtencion = LocalTime.now().format(formatoHora);
        cliente.setHoraAtencion(horaAtencion);
        
        clientesAtendidos.agregar(cliente);
        historialAtenciones.apilar(cliente);
        
        System.out.println("✅ Cliente atendido: " + cliente.getNombre() + " a las " + horaAtencion);
    }

    private void buscarClienteEnCola() {
        System.out.print("Ingrese el documento a buscar: ");
        String documento = scanner.nextLine().trim();
        
        Cliente cliente = colaEspera.buscarPorDocumentoRecursivo(documento);
        if (cliente != null) {
            System.out.println("\n✅ Cliente encontrado en cola:");
            System.out.println(cliente);
        } else {
            System.out.println("❌ Cliente no encontrado en la cola.");
        }
    }

    private void buscarClienteEnArbol() {
        System.out.print("Ingrese el documento a buscar: ");
        String documento = scanner.nextLine().trim();
        
        Cliente cliente = arbolClientes.buscar(documento);
        if (cliente != null) {
            System.out.println("\n✅ Cliente encontrado en árbol:");
            System.out.println(cliente);
        } else {
            System.out.println("❌ Cliente no encontrado en el árbol.");
        }
    }

    private void buscarClienteAtendido() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine().trim();
        
        Cliente cliente = clientesAtendidos.busquedaSecuencial(nombre);
        if (cliente != null) {
            System.out.println("\n✅ Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("❌ Cliente no encontrado.");
        }
    }

    private void modificarCliente() {
        System.out.print("Ingrese el documento del cliente a modificar: ");
        String documento = scanner.nextLine().trim();
        
        Cliente cliente = colaEspera.buscarPorDocumentoRecursivo(documento);
        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }

        System.out.println("Cliente actual: " + cliente);
        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nuevoNombre = scanner.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            cliente.setNombre(nuevoNombre);
        }

        System.out.print("Nueva prioridad 1-3 (0 para mantener): ");
        int nuevaPrioridad = leerOpcion();
        if (nuevaPrioridad >= 1 && nuevaPrioridad <= 3) {
            cliente.setPrioridad(nuevaPrioridad);
        }

        System.out.println("✅ Cliente modificado exitosamente.");
    }

    private void eliminarClienteDeCola() {
        System.out.print("Ingrese el documento del cliente a eliminar: ");
        String documento = scanner.nextLine().trim();
        
        if (colaEspera.eliminarCliente(documento)) {
            System.out.println("✅ Cliente eliminado de la cola.");
        } else {
            System.out.println("❌ Cliente no encontrado.");
        }
    }

    private void deshacerAtencion() {
        Cliente cliente = historialAtenciones.desapilar();
        if (cliente == null) {
            System.out.println("❌ No hay atenciones para deshacer.");
            return;
        }

        cliente.setHoraAtencion("");
        colaEspera.encolar(cliente);
        System.out.println("✅ Atención revertida. Cliente devuelto a la cola: " + cliente.getNombre());
    }

    private void ordenarPorNombre() {
        clientesAtendidos.ordenarBurbujaPorNombre();
        System.out.println("✅ Lista ordenada por nombre (Burbuja).");
        clientesAtendidos.mostrar();
    }

    private void ordenarPorPrioridad() {
        clientesAtendidos.ordenarInsercionPorPrioridad();
        System.out.println("✅ Lista ordenada por prioridad (Inserción).");
        clientesAtendidos.mostrar();
    }

    private void ordenarPorDocumento() {
        Cliente[] arreglo = clientesAtendidos.aArreglo();
        ordenamientoSeleccion(arreglo);
        clientesAtendidos.desdeArreglo(arreglo);
        System.out.println("✅ Lista ordenada por documento (Selección).");
        clientesAtendidos.mostrar();
    }

    private void ordenamientoSeleccion(Cliente[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getDocumento().compareTo(arr[minIdx].getDocumento()) < 0) {
                    minIdx = j;
                }
            }
            Cliente temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           ESTADÍSTICAS                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Clientes en cola: " + colaEspera.getTamano());
        System.out.println("Clientes atendidos: " + clientesAtendidos.getTamano());
        System.out.println("Historial de atenciones: " + historialAtenciones.getTamano());
        System.out.println("Nodos en árbol: " + arbolClientes.contarNodos());
        System.out.println("Altura del árbol: " + arbolClientes.calcularAltura());
    }

    public static void main(String[] args) {
        SinFilasApp app = new SinFilasApp();
        app.iniciar();
    }
}