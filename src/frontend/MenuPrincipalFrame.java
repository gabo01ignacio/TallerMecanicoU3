package frontend;

import java.awt.*;
import javax.swing.*;
import sistemas.menu;

public class MenuPrincipalFrame extends BaseFrame {
    public MenuPrincipalFrame(menu backend) {
        super("Menú Principal", backend);
        setSize(600, 400);
    }
    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBackground(color_fondo);
        JLabel lblTitulo = new JLabel("Taller Mecánicoooo", SwingConstants.CENTER);
        lblTitulo.setFont(fuente_titulo);
        JButton btnIngresar = new JButton("Ingresar Trabajo");
        JButton btnTienda = new JButton("Tienda Repuestos");
        JButton btnConsultas = new JButton("Consultas");
        JButton btnAdmin = new JButton("Administración");
        JButton btnBusqueda = new JButton("Búsqueda y Reportes");
        JButton btnSalir = new JButton("Guardar y Salir");
      
        
        
        JButton[] botones = {btnIngresar, btnTienda, btnConsultas, btnAdmin, btnBusqueda};
        for (JButton b : botones) {
            b.setBackground(color_principal);
            b.setForeground(Color.WHITE);
            b.setFont(fuente_boton);
            b.setOpaque(true);
        }
        btnSalir.setBackground(color_rojo);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(fuente_boton);
        btnSalir.setOpaque(true);
        // acciones
        btnIngresar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "aa");
        });
        btnTienda.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnConsultas.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnAdmin.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnBusqueda.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnSalir.addActionListener(e -> {
            int r = JOptionPane.showConfirmDialog(this, "guardar y salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                backend.guardar();
                System.exit(0);
            }
        });
        panel.add(lblTitulo);
        panel.add(btnIngresar);
        panel.add(btnTienda);
        panel.add(btnConsultas);
        panel.add(btnAdmin);
        panel.add(btnBusqueda);
        panel.add(btnSalir);
        add(panel);
        
        
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    }








