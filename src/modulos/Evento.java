package componentes;

public class Evento {
    private String titulo;
    private String fecha;   // formato: dd/MM/yyyy
    private String hora;    // formato: HH:mm
    private String descripcion;

    public Evento(String titulo, String fecha, String hora, String descripcion) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public String getTitulo() { return titulo; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return fecha + " " + hora + " - " + titulo;
    }
}
