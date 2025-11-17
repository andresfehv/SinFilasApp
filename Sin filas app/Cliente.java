// ==================== CLASE CLIENTE ====================
public class Cliente {
    private String nombre;
    private String documento;
    private int prioridad; // 1=Alta, 2=Media, 3=Baja
    private String horaLlegada;
    private String horaAtencion;

    public Cliente(String nombre, String documento, int prioridad, String horaLlegada) {
        this.nombre = nombre;
        this.documento = documento;
        this.prioridad = prioridad;
        this.horaLlegada = horaLlegada;
        this.horaAtencion = "";
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
    public String getHoraLlegada() { return horaLlegada; }
    public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
    public String getHoraAtencion() { return horaAtencion; }
    public void setHoraAtencion(String horaAtencion) { this.horaAtencion = horaAtencion; }

    public String getPrioridadTexto() {
        switch(prioridad) {
            case 1: return "Alta";
            case 2: return "Media";
            case 3: return "Baja";
            default: return "N/A";
        }
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-15s | %-10s | %-10s | %-10s", 
            nombre, documento, getPrioridadTexto(), horaLlegada, 
            horaAtencion.isEmpty() ? "N/A" : horaAtencion);
    }
}