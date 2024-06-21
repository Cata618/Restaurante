import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Restaurante {
    private List<Reserva> reservas;

    public Restaurante() {
        this.reservas = new ArrayList<>();
    }

    // Método para añadir una nueva reserva
    public void añadirReserva(String nombreCliente, int numeroPersonas, Date fecha) {
        Reserva nuevaReserva = new Reserva(nombreCliente, numeroPersonas, fecha);
        reservas.add(nuevaReserva);
        System.out.println("Reserva añadida: " + nuevaReserva);
    }

    // Método para verificar la disponibilidad de mesas en una fecha
    public boolean verificarDisponibilidad(Date fecha) {
        // Supongamos que el restaurante tiene un máximo de 10 mesas
        int capacidadMaxima = 10;
        int reservasParaFecha = 0;

        for (Reserva reserva : reservas) {
            if (reserva.getFecha().equals(fecha)) {
                reservasParaFecha++;
            }
        }

        return reservasParaFecha < capacidadMaxima;
    }

    // Método para listar todas las reservas en una fecha específica
    public List<Reserva> listarReservas(Date fecha) {
        List<Reserva> reservasParaFecha = new ArrayList<>();

        for (Reserva reserva : reservas) {
            if (reserva.getFecha().equals(fecha)) {
                reservasParaFecha.add(reserva);
            }
        }

        return reservasParaFecha;
    }
}
