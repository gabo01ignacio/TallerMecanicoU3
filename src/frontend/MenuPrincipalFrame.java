package frontend;

import java.awt.*;
import javax.swing.*;
import sistemas.menu;

public class MenuPrincipalFrame extends BaseFrame {

    public MenuPrincipalFrame(menu backend) {
        super("menu principal", backend);
        setSize(600, 480);
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));
        JPanel contenedorFilasBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel lblTitulo = new JLabel("Taller Mecánicoooo", SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelGridBotones1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        JPanel panelGridBotones2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        JPanel exitLayout = new JPanel(new FlowLayout(FlowLayout.CENTER, 30,0));

        JButton btnIngresar = new JButton("Ingresar Trabajo");
        JButton btnTienda = new JButton("Tienda Repuestos");
        JButton btnConsultas = new JButton("Consultas");
        JButton btnAdmin = new JButton("Administración");
        JButton btnBusqueda = new JButton("Búsqueda y Reportes");

        Dimension btnSize = new Dimension(300, 50);
        panelGridBotones1.add(btnIngresar);
        btnIngresar.setPreferredSize(btnSize);
        panelGridBotones1.add(btnTienda);
        btnTienda.setPreferredSize(btnSize);
        panelGridBotones2.add(btnConsultas);
        btnConsultas.setPreferredSize(btnSize);
        panelGridBotones2.add(btnAdmin);
        btnAdmin.setPreferredSize(btnSize);
        panelGridBotones2.add(btnBusqueda);
        btnBusqueda.setPreferredSize(btnSize);

        JButton btnSalir = new JButton("Guardar y Salir");
        btnSalir.setPreferredSize(btnSize);

        btnIngresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnTienda.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnConsultas.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnAdmin.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnBusqueda.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));

        btnSalir.addActionListener(e -> {
            int r = JOptionPane.showConfirmDialog(this, "¿guardar y salir?", "confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                backend.guardar();
                System.exit(0);
            }
        });
        
        
        add(contenedorFilasBotones, BorderLayout.CENTER);
        contenedorFilasBotones.add(panelGridBotones1);
        contenedorFilasBotones.add(panelGridBotones2);
        exitLayout.add(btnSalir);
        add(exitLayout, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
