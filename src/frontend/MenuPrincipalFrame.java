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

        JLabel lblTitulo = new JLabel("Taller Mecánicoooo", SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 120, 20, 120));

        JPanel panelGridBotones = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton btnIngresar = new JButton("Ingresar Trabajo");
        JButton btnTienda = new JButton("Tienda Repuestos");
        JButton btnConsultas = new JButton("Consultas");
        JButton btnAdmin = new JButton("Administración");
        JButton btnBusqueda = new JButton("Búsqueda y Reportes");

        panelGridBotones.add(btnIngresar);
        panelGridBotones.add(btnTienda);
        panelGridBotones.add(btnConsultas);
        panelGridBotones.add(btnAdmin);
        panelGridBotones.add(btnBusqueda);

        JButton btnSalir = new JButton("Guardar y Salir");

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

        panelCentro.add(panelGridBotones, BorderLayout.CENTER);
        panelCentro.add(btnSalir, BorderLayout.SOUTH);
        add(panelCentro, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
