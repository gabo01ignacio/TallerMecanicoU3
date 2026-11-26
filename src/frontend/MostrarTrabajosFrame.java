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
        etiquetatitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(etiquetatitulo, BorderLayout.NORTH);

        //[requisito jtable] columnas y defaultablemodel
        String[] columnas = {
            "numero orden","vehiculo","mecanico","estado","costo total"};
        modelotabla = new DefaultTableModel(columnas, 0){};
        tablatrabajos = new JTable(modelotabla);
        //[requisito jtable] envolver la tabla en un jscrollpane
        JScrollPane panelscrolltabla = new JScrollPane(tablatrabajos);
        panelscrolltabla.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(panelscrolltabla, BorderLayout.CENTER);
        //panel inferior con los botones de accion
        JPanel panelsur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton botonactualizar = new JButton("actualizar tabla");
        
        botonactualizar.addActionListener(e -> CargarTabla());

        JButton botonvolver = new JButton("volver al menu");
        botonvolver.addActionListener(e -> {
            dispose();
            new InicioVista(backend).mostrar();
        });
        //panelsur.add(botonactualizar);
        panelsur.add(botonvolver);
        add(panelsur, BorderLayout.SOUTH);
        //[requisito jtable] cargar los datos del backend al abrir la vista
        CargarTabla();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void CargarTabla() {
        for (ordenesdetrabajo orden : backend.getOrdenes()) {
            modelotabla.addRow(new Object[]{
                orden.getNumeroorden(),
                orden.getVehiculo().getPatente() + " (" + orden.getVehiculo().getMarca() + " " + orden.getVehiculo().getModelo() + ")",
                orden.getMecanicoasignado().getNombre(),
                orden.getEstado(),
                "$" + (int) orden.getCostoTotal()
            });
        }
    }
}
