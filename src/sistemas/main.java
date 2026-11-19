package sistemas;
import frontend.InicioVista;
import javax.swing.SwingUtilities;


/**
 *
 * @author 4rtur
 */

public class main {
    public static void main(String[] args) {
        menu aMenu = new menu();
        aMenu.cargar();
        SwingUtilities.invokeLater(() -> {
            new InicioVista(aMenu).mostrar();
        });
    }
}