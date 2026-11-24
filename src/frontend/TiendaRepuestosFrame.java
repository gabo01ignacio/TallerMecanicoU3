package frontend;

/**
 *
 * @author 4rtur
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelos.repuesto;
import sistemas.menu;


public class TiendaRepuestosFrame extends BaseFrame {

    //componentes para la lista de repuestos ya existente
    private DefaultListModel<String> modelRepuestos;
    private JList<String> listRepuestos;

    private DefaultListModel<String> modelCarro;
    private JList<String> listCarro;
    private JLabel labeltotal;

    //control del total y lista
    private double totalVenta = 0.0;
    private List<repuesto> listaRepuestos;

    public TiendaRepuestosFrame(menu backend) {
        super("tienda de repuestos", backend);
        setSize(750, 520);
        obtenerListaRepuestos();
    }

    private void obtenerListaRepuestos() {
        if (backend != null && backend.getRepuestos() != null && !backend.getRepuestos().isEmpty()) {
            listaRepuestos = backend.getRepuestos();
        } else {
            listaRepuestos = new ArrayList<>();
            listaRepuestos.add(new repuesto("0", "pastillas de freno", 25000, 8));
            listaRepuestos.add(new repuesto("1", "bateria 12V", 75000, 15));
            listaRepuestos.add(new repuesto("2", "WD-40", 8000, 20));
        }
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));

        //titulo en el norte
        JLabel labeltitulo = new JLabel("tienda de repuestos", SwingConstants.CENTER);
        labeltitulo.setFont(Estilo.fuente_titulo);
        add(labeltitulo, BorderLayout.NORTH);

        //panel central (dividido en 2: repuestos a la izquierda, carro a la derecha)
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 15, 0));

        //izquierda: repuestos
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));
        JLabel labelrepuestos = new JLabel("repuestos", SwingConstants.CENTER);
        labelrepuestos.setFont(Estilo.fuente_boton);
        panelIzquierdo.add(labelrepuestos, BorderLayout.NORTH);

        modelRepuestos = new DefaultListModel<>();
        listRepuestos = new JList<>(modelRepuestos);
        listRepuestos.setFont(Estilo.fuente_normal);
        listRepuestos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        actualizarListaRepuestosUI();

        JScrollPane scrollRepuestos = new JScrollPane(listRepuestos);
        panelIzquierdo.add(scrollRepuestos, BorderLayout.CENTER);

        //botones de la izquierda
        JPanel panelBotonesIzquierda = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JButton botonagregar = new JButton("agregar al carro");
        botonagregar.setFont(Estilo.fuente_boton);
        botonagregar.addActionListener(e -> agregarAlCarro());

        JButton botoncrearRepuesto = new JButton("crear repuesto");
        botoncrearRepuesto.setFont(Estilo.fuente_boton);
        botoncrearRepuesto.addActionListener(e -> crearNuevoRepuesto());

        panelBotonesIzquierda.add(botonagregar);
        panelBotonesIzquierda.add(botoncrearRepuesto);
        panelIzquierdo.add(panelBotonesIzquierda, BorderLayout.SOUTH);


        JPanel panelDerecho = new JPanel(new BorderLayout(5, 5));
        JLabel labelCarro = new JLabel("carro", SwingConstants.CENTER);
        labelCarro.setFont(Estilo.fuente_boton);
        panelDerecho.add(labelCarro, BorderLayout.NORTH);

        modelCarro = new DefaultListModel<>();
        listCarro = new JList<>(modelCarro);
        listCarro.setFont(Estilo.fuente_normal);

        JScrollPane scrollCarro = new JScrollPane(listCarro);
        panelDerecho.add(scrollCarro, BorderLayout.CENTER);

        labeltotal = new JLabel("total: $0", SwingConstants.RIGHT);
        labeltotal.setFont(Estilo.fuente_boton);
        panelDerecho.add(labeltotal, BorderLayout.SOUTH);

        panelCentro.add(panelIzquierdo);
        panelCentro.add(panelDerecho);
        add(panelCentro, BorderLayout.CENTER);

        //panel sur botones volver y pagar venta
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton botonvolver = new JButton("volver");
        botonvolver.setFont(Estilo.fuente_boton);
        botonvolver.addActionListener(e -> {
            dispose();
            new InicioVista(backend).mostrar();
        });

        JButton botonpagar = new JButton("pagar venta");
        botonpagar.addActionListener(e -> pagar());

        panelSur.add(botonvolver);
        panelSur.add(botonpagar);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarListaRepuestosUI() {
        modelRepuestos.clear();
        for (repuesto r : listaRepuestos) {
            modelRepuestos.addElement("[" + r.getCodigo() + "] " + r.getNombre() + " - $" + (int)r.getPrecio() + " (stock: " + r.getStock() + ")");
        }
    }

    private void crearNuevoRepuesto() {
        JTextField textocodigo = new JTextField();
        JTextField textonombre = new JTextField();
        JTextField textoprecio = new JTextField();
        JTextField textostock = new JTextField();

        Object[] formulario = {
            "codigo:", textocodigo,
            "nombre:", textonombre,
            "precio:", textoprecio,
            "stock:", textostock
        };

        int opcion = JOptionPane.showConfirmDialog(this, formulario, "registrar nuevo repuesto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String codigo = textocodigo.getText().trim();
            String nombre = textonombre.getText().trim();
            String precioStr = textoprecio.getText().trim();
            String stockStr = textostock.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "debe llenar todos los campos", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double precio = Double.parseDouble(precioStr);
                int stock = Integer.parseInt(stockStr);

                if (precio <= 0 || stock < 0) {
                    JOptionPane.showMessageDialog(this, "precio y stock deben ser valores positivos.", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                repuesto nuevo = new repuesto(codigo, nombre, precio, stock);
                listaRepuestos.add(nuevo);

                // si el backend no tenia la lista actualizada
                if (backend != null && backend.getRepuestos() != null && !backend.getRepuestos().contains(nuevo)) {
                    backend.getRepuestos().add(nuevo);
                }

                actualizarListaRepuestosUI();
                JOptionPane.showMessageDialog(this, "repuesto creado exitosamente", "exito", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "precio y stock deben ser numeros validos.", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void agregarAlCarro() {
        int i = listRepuestos.getSelectedIndex();
        if (i < 0) {
            JOptionPane.showMessageDialog(this, "seleccione un repuesto de la lista de la izquierda.", "aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        repuesto r = listaRepuestos.get(i);

        String cantidadStr = JOptionPane.showInputDialog(this, "ingrese cantidadidad a comprar", "cantidadidad", JOptionPane.QUESTION_MESSAGE);
        if (cantidadStr == null || cantidadStr.trim().isEmpty()) return;

        try {
            int cantidad = Integer.parseInt(cantidadStr.trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "la cantidadidad debe ser mayor a 0", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cantidad > r.getStock()) {
                JOptionPane.showMessageDialog(this, "el stock no es suficiente. stock disponible: " + r.getStock(), "error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = r.getPrecio() * cantidad;
            totalVenta += subtotal;

            r.disminuirStock(cantidad);
            actualizarListaRepuestosUI();

            modelCarro.addElement(r.getNombre() + " x" + cantidad + " = $" + (int)subtotal);
            labeltotal.setText("total: $" + (int)totalVenta);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ingrese un numero valido", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pagar() {
        if (modelCarro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "el carro esta vacio", "aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int r = JOptionPane.showConfirmDialog(this, "confirmar pago de $" + (int)totalVenta + "?", "confirmar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "venta realizada correctamente\ncosto total: $" + (int)totalVenta, "", JOptionPane.INFORMATION_MESSAGE);

            modelCarro.clear();
            totalVenta = 0.0;
            labeltotal.setText("total: $0");
        }
    }
}
