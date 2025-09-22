package componentes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEventos {
    private static final String ARCHIVO = "eventos.txt";

    // Guardar todos los eventos en el archivo
    public static void guardarEventos(List<Evento> eventos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Evento e : eventos) {
                pw.println(e.getTitulo() + ";" + e.getFecha() + ";" + e.getHora() + ";" + e.getDescripcion());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Cargar todos los eventos desde el archivo
    public static List<Evento> cargarEventos() {
        List<Evento> lista = new ArrayList<>();
        File file = new File(ARCHIVO);

        if (!file.exists()) {
            return lista; // si no existe el archivo, devolvemos lista vacía
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    lista.add(new Evento(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}
