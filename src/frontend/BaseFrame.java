package frontend;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import sistemas.menu;
public abstract class BaseFrame extends JFrame {
    protected menu backend;
    protected final Color color_fondo = new Color(245, 247, 250);
    protected final Color color_principal = new Color(0, 123, 255);
    protected final Color color_verde = new Color(40, 167, 69);
    protected final Color color_rojo = new Color(220, 53, 69);
    protected final Font fuente_titulo = new Font("Segoe UI", Font.BOLD, 20);
    protected final Font fuente_normal = new Font("Segoe UI", Font.PLAIN, 14);
    protected final Font fuente_boton = new Font("Segoe UI", Font.BOLD, 14);
    public BaseFrame(String titulo, menu backend) {
        super(titulo);
        this.backend = backend;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(color_fondo);
    }
    public abstract void mostrar();
}