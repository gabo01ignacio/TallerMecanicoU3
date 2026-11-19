package frontend;

import java.awt.*;
import javax.swing.*;
import sistemas.menu;

<<<<<<< Updated upstream:src/frontend/MenuPrincipalFrame.java
public class MenuPrincipalFrame extends BaseFrame {

    public MenuPrincipalFrame(menu backend) {
        super("menu principal", backend);
        setSize(600, 480);
=======
public class InicioVista extends BaseFrame {
    public InicioVista(menu backend) {
        super("Menú Principal", backend);
        setSize(600, 400);
>>>>>>> Stashed changes:src/frontend/InicioVista.java
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));
<<<<<<< Updated upstream:src/frontend/MenuPrincipalFrame.java

        JLabel lblTitulo = new JLabel("Taller Mecánicoooo", SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 120, 20, 120));

        JPanel panelGridBotones = new JPanel(new GridLayout(5, 1, 10, 10));

=======
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBackground(Estilo.color_fondo);
        
        //margenes
        panel.setBorder(BorderFactory.createEmptyBorder(20, 120, 20, 120));
        
        
        JLabel lblTitulo = new JLabel("Taller Mecánicoooo", SwingConstants.CENTER);
        lblTitulo.setFont(Estilo.fuente_titulo);
>>>>>>> Stashed changes:src/frontend/InicioVista.java
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
<<<<<<< Updated upstream:src/frontend/MenuPrincipalFrame.java

        btnIngresar.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
=======
      
        
        
        JButton[] botones = {btnIngresar, btnTienda, btnConsultas, btnAdmin, btnBusqueda};
        for (JButton b : botones) {
            b.setBackground(Estilo.color_principal);
            b.setForeground(Color.WHITE);
            b.setFont(Estilo.fuente_boton);
            b.setOpaque(true);
        }
        btnSalir.setBackground(Estilo.color_rojo);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(Estilo.fuente_boton);
        btnSalir.setOpaque(true);
        // acciones
        btnIngresar.addActionListener(e -> {
            dispose();
            new CrearTrabajoFrame(backend).mostrar();
        });
>>>>>>> Stashed changes:src/frontend/InicioVista.java
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
