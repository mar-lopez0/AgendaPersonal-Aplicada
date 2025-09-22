package componentes;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class DIseño {

    // Colores
    public static final Color COLOR_FONDO = new Color(245, 245, 245);
    public static final Color COLOR_BOTON = new Color(33, 150, 243);
    public static final Color COLOR_TEXTO = Color.BLACK;
    public static final Color COLOR_TEXTO_BOTON = Color.WHITE;

    // Fuentes
    public static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 18);
    public static final Font FUENTE_TEXTO = new Font("Arial", Font.PLAIN, 14);

    // Bordes
    public static final Border BORDE_GENERAL = BorderFactory.createLineBorder(Color.GRAY, 1);
    public static final Border BORDE_BOTON = BorderFactory.createLineBorder(COLOR_BOTON, 2);

};
