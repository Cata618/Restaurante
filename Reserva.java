import java.util.Date;

public class Reserva {
    private String nombreCliente;
    private int numeroPersonas;
    private Date fecha;

    public Reserva(String nombreCliente, int numeroPersonas, Date fecha) {
        this.nombreCliente = nombreCliente;
        this.numeroPersonas = numeroPersonas;
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", numeroPersonas=" + numeroPersonas +
                ", fecha=" + fecha +
                '}';
    }
}
