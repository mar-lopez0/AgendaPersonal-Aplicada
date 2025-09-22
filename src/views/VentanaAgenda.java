package componentes;

import java.awt.*;
import javax.swing.*;
import java.util.Calendar;

public class VentanaAgenda extends JFrame {
    private DefaultListModel<Evento> modeloLista = new DefaultListModel<>();
    private JList<Evento> listaEventos = new JList<>(modeloLista);
    private JTable tablaDias = new JTable(6, 7);
    private JSpinner spMes, spAnio;

    public VentanaAgenda() {
        setTitle("Agenda Personal");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Panel Izquierdo =====
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(200, 1000));
        panelIzq.setBackground(Color.PINK);

        // Botones
        JPanel panelBtns = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        panelBtns.add(btnAgregar);
        panelBtns.add(btnEditar);
        panelBtns.add(btnEliminar);

        // Lista de eventos
        panelIzq.add(panelBtns, BorderLayout.NORTH);
        panelIzq.add(new JScrollPane(listaEventos), BorderLayout.CENTER);

       // ===== Panel Derecho (Calendario) =====
        JPanel panelDer = new JPanel(new BorderLayout());

        spMes = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        spAnio = new JSpinner(new SpinnerNumberModel(2025, 1900, 2100, 1));
        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("Mes:"));
        panelTop.add(spMes);
        panelTop.add(new JLabel("Año:"));
        panelTop.add(spAnio);

        panelDer.add(panelTop, BorderLayout.NORTH);

        JScrollPane scrollCalendario = new JScrollPane(tablaDias);
        scrollCalendario.setPreferredSize(new Dimension(500, 100)); // aumentar altura del calendario
        panelDer.add(scrollCalendario, BorderLayout.CENTER);


        // ===== Agregar a la ventana =====
        add(panelIzq, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);

        // ===== Eventos de botones =====
        btnAgregar.addActionListener(e -> agregarEvento());
        btnEditar.addActionListener(e -> editarEvento());
        btnEliminar.addActionListener(e -> eliminarEvento());

        spMes.addChangeListener(e -> actualizarCalendario());
        spAnio.addChangeListener(e -> actualizarCalendario());
        actualizarCalendario();
    }

    // ==== Métodos básicos ====
    private void agregarEvento() {
        String fecha = obtenerFechaSeleccionada();
        String titulo = JOptionPane.showInputDialog(this, "Título del evento:");
        if (titulo != null && !titulo.isEmpty()) {
            modeloLista.addElement(new Evento(titulo, fecha, "", ""));
        }
    }

    private void editarEvento() {
        Evento ev = listaEventos.getSelectedValue();
        if (ev != null) {
            String nuevoTitulo = JOptionPane.showInputDialog(this, "Editar título:", ev.getTitulo());
            if (nuevoTitulo != null) {
                modeloLista.setElementAt(new Evento(nuevoTitulo, ev.getFecha(), ev.getHora(), ev.getDescripcion()),
                        listaEventos.getSelectedIndex());
            }
        }
    }

    private void eliminarEvento() {
        Evento ev = listaEventos.getSelectedValue();
        if (ev != null) {
            modeloLista.removeElement(ev);
        }
    }

    // ==== Calendario simple ====
    private void actualizarCalendario() {
        int mes = (int) spMes.getValue() - 1;
        int anio = (int) spAnio.getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes, 1);

        int primerDia = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if (primerDia < 0) primerDia = 6;
        int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int f = 0; f < 6; f++)
            for (int c = 0; c < 7; c++)
                tablaDias.setValueAt("", f, c);

        int fila = 0;
        for (int d = 1; d <= diasMes; d++) {
            int col = (primerDia + d - 1) % 7;
            fila = (primerDia + d - 1) / 7;
            tablaDias.setValueAt(d, fila, col);
        }
    }

    private String obtenerFechaSeleccionada() {
        int f = tablaDias.getSelectedRow();
        int c = tablaDias.getSelectedColumn();
        Object val = (f == -1 || c == -1) ? null : tablaDias.getValueAt(f, c);
        if (val == null || val.toString().isEmpty()) return "dd/MM/yyyy";
        return String.format("%02d/%02d/%04d", (int) val, (int) spMes.getValue(), (int) spAnio.getValue());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaAgenda().setVisible(true));
    }
}
