package frontend;

import javax.swing.JFrame;
import sistemas.menu;
public abstract class BaseFrame extends JFrame {
    protected menu backend;
    public BaseFrame(String titulo, menu backend) {
        super(titulo);
        this.backend = backend;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public abstract void mostrar();
}