package frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import sistemas.menu;


public class InicioVista extends BaseFrame {
    public InicioVista(menu backend) {
        super("Menú Principal", backend);
        setSize(1280, 720);
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));
        
        //contenedorFilasBotones contiene los 2 FlowLayouts con los botones
        JPanel contenedorFilasBotones = new JPanel(new GridLayout(2, 1, 0, 0));
        contenedorFilasBotones.setBorder(new EmptyBorder(200, 20, 200, 20)); //solución parche, centra verticalmente las filas de los botones
        
        //titulo
        JLabel lblTitulo = new JLabel("Taller Mecánicoooo", SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        //los 2 FlowLayouts son añadidos a contenedorFilasBotones
        JPanel panelGridBotones1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        JPanel panelGridBotones2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        JPanel exitLayout = new JPanel(new FlowLayout(FlowLayout.CENTER, 30,0));

        JButton btnIngresar = new JButton("Ingresar Trabajo");
        JButton btnTienda = new JButton("Tienda Repuestos");
        JButton btnConsultas = new JButton("Consultas");
        JButton btnAdmin = new JButton("Administración");
        JButton btnBusqueda = new JButton("Búsqueda y Reportes");
        JButton btnMostrarTrabajos = new JButton("Mostrar Trabajos");

        //tamaño de los botones
        Dimension btnSize = new Dimension(300, 50);
        panelGridBotones1.add(btnIngresar);
        btnIngresar.setPreferredSize(btnSize);
        panelGridBotones1.add(btnMostrarTrabajos);
        btnMostrarTrabajos.setPreferredSize(btnSize);
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
        
        lblTitulo.setFont(Estilo.fuente_titulo);
        
        //acciones
        btnIngresar.addActionListener(e -> {
            dispose();
            new CrearTrabajoFrame(backend).mostrar();
        });

        btnTienda.addActionListener(e -> {
            dispose();
            new TiendaRepuestosFrame(backend).mostrar();
        });
           
        
        btnConsultas.addActionListener(e -> {
            dispose();
            new ConsultasFrame(backend).mostrar();
        });
        btnAdmin.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));
        btnBusqueda.addActionListener(e -> JOptionPane.showMessageDialog(this, "aa"));

        btnSalir.addActionListener(e -> {
            int r = JOptionPane.showConfirmDialog(this, "¿guardar y salir?", "confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                backend.guardar();
                System.exit(0);
            }
        });
        
        //se añaden los paneles
        add(contenedorFilasBotones, BorderLayout.CENTER);
        contenedorFilasBotones.add(panelGridBotones1);
        contenedorFilasBotones.add(panelGridBotones2);
        exitLayout.add(btnSalir);
        add(exitLayout, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
