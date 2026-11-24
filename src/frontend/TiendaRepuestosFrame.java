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
    private JLabel lblTotal;

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
        JLabel lblTitulo = new JLabel("tienda de repuestos", SwingConstants.CENTER);
        lblTitulo.setFont(Estilo.fuente_titulo);
        add(lblTitulo, BorderLayout.NORTH);

        //panel central (dividido en 2: repuestos a la izquierda, carro a la derecha)
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 15, 0));

        //izquierda: repuestos
        JPanel panelIzquierdo = new JPanel(new BorderLayout(5, 5));
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("repuestos"));

        modelRepuestos = new DefaultListModel<>();
        listRepuestos = new JList<>(modelRepuestos);
        listRepuestos.setFont(Estilo.fuente_normal);
        listRepuestos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        actualizarListaRepuestosUI();

        JScrollPane scrollRepuestos = new JScrollPane(listRepuestos);
        panelIzquierdo.add(scrollRepuestos, BorderLayout.CENTER);

        //botones de la izquierda
        JPanel panelBotonesIzquierda = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JButton btnAgregar = new JButton("agregar al carro");
        btnAgregar.setFont(Estilo.fuente_boton);
        btnAgregar.addActionListener(e -> agregarAlCarro());

        JButton btnCrearRepuesto = new JButton("crear repuesto");
        btnCrearRepuesto.setFont(Estilo.fuente_boton);
        btnCrearRepuesto.addActionListener(e -> crearNuevoRepuesto());

        panelBotonesIzquierda.add(btnAgregar);
        panelBotonesIzquierda.add(btnCrearRepuesto);
        panelIzquierdo.add(panelBotonesIzquierda, BorderLayout.SOUTH);


        JPanel panelDerecho = new JPanel(new BorderLayout(5, 5));
        panelDerecho.setBorder(BorderFactory.createTitledBorder("carro"));

        modelCarro = new DefaultListModel<>();
        listCarro = new JList<>(modelCarro);
        listCarro.setFont(Estilo.fuente_normal);

        JScrollPane scrollCarro = new JScrollPane(listCarro);
        panelDerecho.add(scrollCarro, BorderLayout.CENTER);

        lblTotal = new JLabel("total: $0", SwingConstants.RIGHT);
        lblTotal.setFont(Estilo.fuente_boton);
        panelDerecho.add(lblTotal, BorderLayout.SOUTH);

        panelCentro.add(panelIzquierdo);
        panelCentro.add(panelDerecho);
        add(panelCentro, BorderLayout.CENTER);

        //panel sur botones volver y pagar venta
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnVolver = new JButton("volver");
        btnVolver.setFont(Estilo.fuente_boton);
        btnVolver.addActionListener(e -> {
            dispose();
            new InicioVista(backend).mostrar();
        });

        JButton btnPagar = new JButton("pagar venta");
        btnPagar.addActionListener(e -> pagar());

        panelSur.add(btnVolver);
        panelSur.add(btnPagar);

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
        JTextField txtCod = new JTextField();
        JTextField txtNom = new JTextField();
        JTextField txtPre = new JTextField();
        JTextField txtStk = new JTextField();

        Object[] formulario = {
            "codigo:", txtCod,
            "nombre:", txtNom,
            "precio:", txtPre,
            "stock:", txtStk
        };

        int opcion = JOptionPane.showConfirmDialog(this, formulario, "registrar nuevo repuesto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String codigo = txtCod.getText().trim();
            String nombre = txtNom.getText().trim();
            String precioStr = txtPre.getText().trim();
            String stockStr = txtStk.getText().trim();

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
        int idx = listRepuestos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "seleccione un repuesto de la lista de la izquierda.", "aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        repuesto r = listaRepuestos.get(idx);

        String cantStr = JOptionPane.showInputDialog(this, "ingrese cantidad a comprar", "cantidad", JOptionPane.QUESTION_MESSAGE);
        if (cantStr == null || cantStr.trim().isEmpty()) return;

        try {
            int cant = Integer.parseInt(cantStr.trim());
            if (cant <= 0) {
                JOptionPane.showMessageDialog(this, "la cantidad debe ser mayor a 0", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cant > r.getStock()) {
                JOptionPane.showMessageDialog(this, "el stock no es suficiente. stock disponible: " + r.getStock(), "error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double subtotal = r.getPrecio() * cant;
            totalVenta += subtotal;

            r.disminuirStock(cant);
            actualizarListaRepuestosUI();

            modelCarro.addElement(r.getNombre() + " x" + cant + " = $" + (int)subtotal);
            lblTotal.setText("total: $" + (int)totalVenta);

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
            lblTotal.setText("total: $0");
        }
    }
}
