package frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelos.ordenesdetrabajo;
import sistemas.menu;

/**
 *
 * @author 4rtur
 */

//[requisito JTable]
public class MostrarTrabajosFrame extends BaseFrame {

    private JTable tablatrabajos;
    private DefaultTableModel modelotabla;

    public MostrarTrabajosFrame(menu backend) {
        super("mostrar trabajos", backend);
        setSize(800, 500);
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));

        //titulo principal de la vista
        JLabel etiquetatitulo = new JLabel("lista de ordenes de trabajo", SwingConstants.CENTER);
        etiquetatitulo.setFont(new Font("Helvetica", Font.BOLD, 20));
        etiquetatitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(etiquetatitulo, BorderLayout.NORTH);

        //[requisito JTable] columnas y defaultablemodel
        String[] columnas = {
            "numero orden","vehiculo","mecanico","estado","costo total"
        };
        modelotabla = new DefaultTableModel(columnas, 0);
        tablatrabajos = new JTable(modelotabla);
        tablatrabajos.setRowHeight(25);

        //[requisito jtable] envolver la tabla en un jscrollpane
        JScrollPane panelscrolltabla = new JScrollPane(tablatrabajos);
        panelscrolltabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(panelscrolltabla, BorderLayout.CENTER);

        //panel inferior con los botones de accion
        JPanel panelsur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton botonactualizar = new JButton("actualizar tabla");
        botonactualizar.addActionListener(e -> cargartabla());

        JButton botonvolver = new JButton("volver al menu");
        botonvolver.addActionListener(e -> {
            dispose();
            new InicioVista(backend).mostrar();
        });

        panelsur.add(botonactualizar);
        panelsur.add(botonvolver);
        add(panelsur, BorderLayout.SOUTH);

        //[requisito jtable] cargar los datos del backend al abrir la vista
        cargartabla();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // [REQUISITO: JTable] - metodo para limpiar el modelo y volver a poblar con los datos del backend
    private void cargartabla() {
        modelotabla.setRowCount(0); // limpia las filas existentes sin borrar columnas

        if (backend != null && backend.getOrdenes() != null) {
            for (ordenesdetrabajo orden : backend.getOrdenes()) {
                String idorden = orden.getNumeroorden();
                
                String infovehiculo = (orden.getVehiculo() != null) 
                    ? orden.getVehiculo().getPatente() + " (" + orden.getVehiculo().getMarca() + " " + orden.getVehiculo().getModelo() + ")" 
                    : "sin vehiculo";
                
                String infomecanico = (orden.getMecanicoasignado() != null) 
                    ? orden.getMecanicoasignado().getNombre() 
                    : "sin asignar";
                
                String estado = (orden.getEstado() != null) 
                    ? orden.getEstado().toString() 
                    : "pendiente";
                
                String costototal = "$" + (int) orden.getCostoTotal();

                Object[] fila = {
                    idorden, 
                    infovehiculo, 
                    infomecanico, 
                    estado, 
                    costototal
                };

                modelotabla.addRow(fila);
            }
        }
    }
}
