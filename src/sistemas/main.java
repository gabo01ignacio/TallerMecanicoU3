package sistemas;
import frontend.InicioVista;
import javax.swing.SwingUtilities;


public class main {
    public static void main(String[] args) {
        menu aMenu = new menu();
        aMenu.cargar(); 
        aMenu.crearTrabajador("22509396-2", "Alonso Baezzza", "Mecánico");
        aMenu.crearTrabajador("21345678-9", "pancho gacituas", "Eléctrico");
        SwingUtilities.invokeLater(() -> {
            new InicioVista(aMenu).mostrar();
        });
    }
}