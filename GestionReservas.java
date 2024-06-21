import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GestionReservas {
    private static Restaurante restaurante = new Restaurante();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Reservas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);

            // Crear el panel con pestañas
            JTabbedPane tabbedPane = new JTabbedPane();

            // Pestaña para añadir reservas
            JPanel añadirReservaPanel = crearAñadirReservaPanel();
            tabbedPane.addTab("Añadir Reserva", añadirReservaPanel);

            // Pestaña para verificar disponibilidad
            JPanel verificarDisponibilidadPanel = crearVerificarDisponibilidadPanel();
            tabbedPane.addTab("Verificar Disponibilidad", verificarDisponibilidadPanel);

            // Pestaña para listar reservas
            JPanel listarReservasPanel = crearListarReservasPanel();
            tabbedPane.addTab("Listar Reservas", listarReservasPanel);

            frame.add(tabbedPane);
            frame.setVisible(true);
        });
    }

    private static JPanel crearAñadirReservaPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // Crear campos de entrada
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(10);  // Cambiar ancho del campo de texto

        JLabel personasLabel = new JLabel("Número de Personas:");
        JComboBox<Integer> personasComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            personasComboBox.addItem(i);
        }

        JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField fechaField = new JTextField(10);  // Añadir ancho al campo de texto

        JButton añadirButton = new JButton("Añadir");

        // Añadir acción al botón
        añadirButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            int numeroPersonas = (int) personasComboBox.getSelectedItem();
            Date fecha = parseDate(fechaField.getText());

            if (nombre.isEmpty() || fecha == null) {
                JOptionPane.showMessageDialog(panel, "Todos los campos deben ser completados correctamente.");
            } else {
                restaurante.añadirReserva(nombre, numeroPersonas, fecha);
                JOptionPane.showMessageDialog(panel, "Reserva añadida.");
                nombreField.setText("");
                fechaField.setText("");
            }
        });

        // Añadir componentes al panel usando GridBagConstraints
        c.gridx = 0;
        c.gridy = 0;
        panel.add(nombreLabel, c);

        c.gridx = 1;
        panel.add(nombreField, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(personasLabel, c);

        c.gridx = 1;
        panel.add(personasComboBox, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(fechaLabel, c);

        c.gridx = 1;
        panel.add(fechaField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(añadirButton, c);

        return panel;
    }

    private static JPanel crearVerificarDisponibilidadPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        // Crear campos de entrada
        JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField fechaField = new JTextField(10);  // Añadir ancho al campo de texto

        JButton verificarButton = new JButton("Verificar");

        // Añadir acción al botón
        verificarButton.addActionListener(e -> {
            Date fecha = parseDate(fechaField.getText());
            if (fecha == null) {
                JOptionPane.showMessageDialog(panel, "Ingrese una fecha válida.");
            } else {
                boolean disponibilidad = restaurante.verificarDisponibilidad(fecha);
                String mensaje = disponibilidad ? "Hay disponibilidad." : "No hay disponibilidad.";
                JOptionPane.showMessageDialog(panel, mensaje);
            }
        });

        // Añadir componentes al panel usando GridBagConstraints
        c.gridx = 0;
        c.gridy = 0;
        panel.add(fechaLabel, c);

        c.gridx = 1;
        panel.add(fechaField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(verificarButton, c);

        return panel;
    }

    private static JPanel crearListarReservasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear campo de entrada
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        JLabel fechaLabel = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField fechaField = new JTextField();
        JButton listarButton = new JButton("Listar");

        inputPanel.add(fechaLabel);
        inputPanel.add(fechaField);

        JTextArea reservasArea = new JTextArea();
        reservasArea.setEditable(false);

        // Añadir acción al botón
        listarButton.addActionListener(e -> {
            Date fecha = parseDate(fechaField.getText());
            if (fecha == null) {
                JOptionPane.showMessageDialog(panel, "Ingrese una fecha válida.");
            } else {
                List<Reserva> reservas = restaurante.listarReservas(fecha);
                StringBuilder reservasTexto = new StringBuilder();

                for (Reserva reserva : reservas) {
                    reservasTexto.append(reserva.toString()).append("\n");
                }

                reservasArea.setText(reservasTexto.toString());
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(reservasArea), BorderLayout.CENTER);
        panel.add(listarButton, BorderLayout.SOUTH);

        return panel;
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}
