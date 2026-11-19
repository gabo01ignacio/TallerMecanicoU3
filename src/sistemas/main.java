package sistemas;
import frontend.MenuPrincipalFrame;
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
            new MenuPrincipalFrame(aMenu).mostrar();
        });
    }
}