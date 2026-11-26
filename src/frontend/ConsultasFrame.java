
package frontend;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import modelos.cliente;
import sistemas.menu;

public class ConsultasFrame extends BaseFrame {

    private JTextField camporut;
    private JLabel etiquetanombrevalor;
    private JLabel etiquetarutvalor;
    private JLabel etiquetatelefonovalor;
    private JTextArea areatextohistorial;
    private JLabel etiquetamontototal;

    //datos de prueba en memoria para garantizar funcionamiento inmediato
    private Map<String, cliente> clientesprueba;
    private Map<String, List<String>> historialprueba;
    private Map<String, Double> montototalprueba;

    public ConsultasFrame(menu backend) {
        super("consultas de clientes", backend);
        setSize(650, 520);
        inicializardatosprueba();
    }

    private void inicializardatosprueba() {
        clientesprueba = new HashMap<>();
        historialprueba = new HashMap<>();
        montototalprueba = new HashMap<>();

        //cliente 1
        cliente cliente1 = new cliente("22509396-2", "alonso baeza", "+56966666666");
        clientesprueba.put(cliente1.getRut(), cliente1);
        List<String> historial1 = new ArrayList<>();
        historial1.add("orden: ot-1 | vehiculo: aa1111 [toyota yaris] | mecanico: pancho gacitua | estado: finalizado | costo: $45.000");
        historial1.add("orden: ot-4 | vehiculo: dd4444 [honda cb190] | mecanico: alonso baeza | estado: en_reparacion | costo: $30.000");
        historialprueba.put(cliente1.getRut(), historial1);
        montototalprueba.put(cliente1.getRut(), 75000.0);

        //cliente 2
        cliente cliente2 = new cliente("18123456-7", "maria perez", "+56911112222");
        clientesprueba.put(cliente2.getRut(), cliente2);
        List<String> historial2 = new ArrayList<>();
        historial2.add("orden: ot-2 | vehiculo: bb2222 [hyundai accent] | mecanico: pancho gacitua | estado: finalizado | costo: $60.000");
        historialprueba.put(cliente2.getRut(), historial2);
        montototalprueba.put(cliente2.getRut(), 60000.0);

        //cliente 3
        cliente cliente3 = new cliente("15888999-0", "carlos gonzalez", "+56933334444");
        clientesprueba.put(cliente3.getRut(), cliente3);
        List<String> historial3 = new ArrayList<>();
        historial3.add("orden: ot-3 | vehiculo: cc3333 [tesla model 3] | mecanico: alonso baeza | estado: pendiente | costo: $120.000");
        historialprueba.put(cliente3.getRut(), historial3);
        montototalprueba.put(cliente3.getRut(), 120000.0);
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));

        //norte: titulo de la ventana
        JLabel etiquetatitulo = new JLabel("consulta de clientes", SwingConstants.CENTER);
        etiquetatitulo.setFont(new Font("Helvetica", Font.BOLD, 20));
        etiquetatitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
        add(etiquetatitulo, BorderLayout.NORTH);

        //centro: panel principal
        JPanel panelcentro = new JPanel(new BorderLayout(10, 10));
        panelcentro.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        //panel de busqueda por rut
        JPanel panelbusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelbusqueda.add(new JLabel("ingrese rut del cliente:"));

        camporut = new JTextField(15);
        panelbusqueda.add(camporut);

        JButton botonbuscar = new JButton("buscar");
        botonbuscar.addActionListener(e -> buscarcliente());
        panelbusqueda.add(botonbuscar);

        panelcentro.add(panelbusqueda, BorderLayout.NORTH);

        //panel de detalles (informacion del cliente e historial)
        JPanel paneldetalles = new JPanel(new BorderLayout(10, 10));

        //seccion informacion del cliente
        JPanel panelinformacioncompleto = new JPanel(new BorderLayout(5, 5));

        JLabel etiquetatituloinformacion = new JLabel("informacion del cliente");
        etiquetatituloinformacion.setFont(new Font("Helvetica", Font.BOLD, 14));
        panelinformacioncompleto.add(etiquetatituloinformacion, BorderLayout.NORTH);

        JPanel panelinformacioncliente = new JPanel(new GridLayout(3, 1, 5, 5));

        etiquetarutvalor = new JLabel("rut:");
        panelinformacioncliente.add(etiquetarutvalor);

        etiquetanombrevalor = new JLabel("nombre:");
        panelinformacioncliente.add(etiquetanombrevalor);

        etiquetatelefonovalor = new JLabel("telefono:");
        panelinformacioncliente.add(etiquetatelefonovalor);

        panelinformacioncompleto.add(panelinformacioncliente, BorderLayout.CENTER);
        paneldetalles.add(panelinformacioncompleto, BorderLayout.NORTH);

        //seccion historial de ordenes y vehiculos
        JPanel panelhistorialcompleto = new JPanel(new BorderLayout(5, 5));

        JLabel etiquetatitulohistorial = new JLabel("historial de ordenes y vehiculos");
        etiquetatitulohistorial.setFont(new Font("Helvetica", Font.BOLD, 14));
        panelhistorialcompleto.add(etiquetatitulohistorial, BorderLayout.NORTH);

        areatextohistorial = new JTextArea();
        areatextohistorial.setEditable(false);
        areatextohistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane panelscrollhistorial = new JScrollPane(areatextohistorial);
        panelscrollhistorial.setBorder(null);
        panelhistorialcompleto.add(panelscrollhistorial, BorderLayout.CENTER);

        paneldetalles.add(panelhistorialcompleto, BorderLayout.CENTER);

        //monto total acumulado
        etiquetamontototal = new JLabel("monto total gastado: $0", SwingConstants.RIGHT);
        etiquetamontototal.setFont(new Font("Helvetica", Font.BOLD, 14));
        etiquetamontototal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        paneldetalles.add(etiquetamontototal, BorderLayout.SOUTH);

        panelcentro.add(paneldetalles, BorderLayout.CENTER);
        add(panelcentro, BorderLayout.CENTER);

        
        
        // boton pdf tarea 3
        
        JButton botonpdf = new JButton("exportar pdf");
        botonpdf.setBackground(Estilo.color_principal);
        botonpdf.setForeground(Color.WHITE);
        botonpdf.setFont(Estilo.fuente_boton);
        botonpdf.setOpaque(true);
        botonpdf.addActionListener(e -> exportarPDF());
        
        
        
        //sur: boton volver
        JPanel panelsur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonvolver = new JButton("volver");
        botonvolver.addActionListener(e -> {
            dispose();
            new InicioVista(backend).mostrar();
        });
        panelsur.add(botonvolver);
        panelsur.add(botonpdf);
        add(panelsur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarcliente() {
        String rutbuscado = camporut.getText().trim();
        if (rutbuscado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ingrese un rut para buscar", "aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //busqueda en los datos de prueba
        cliente clienteencontrado = clientesprueba.get(rutbuscado);

        if (clienteencontrado != null) {
            etiquetarutvalor.setText("rut: " + clienteencontrado.getRut());
            etiquetanombrevalor.setText("nombre: " + clienteencontrado.getNombre());
            etiquetatelefonovalor.setText("telefono: " + clienteencontrado.getTelefono());

            List<String> listaordenes = historialprueba.get(rutbuscado);
            areatextohistorial.setText("");
            if (listaordenes != null && !listaordenes.isEmpty()) {
                for (String ordenitem : listaordenes) {
                    areatextohistorial.append(ordenitem + "\n");
                }
            } else {
                areatextohistorial.setText("el cliente no registra ordenes de trabajo");
            }

            Double monto = montototalprueba.get(rutbuscado);
            if (monto != null) {
                etiquetamontototal.setText("monto total gastado: $" + monto.intValue());
            } else {
                etiquetamontototal.setText("monto total gastado: $0");
            }

        } else {
            etiquetarutvalor.setText("rut:");
            etiquetanombrevalor.setText("nombre:");
            etiquetatelefonovalor.setText("telefono:");
            areatextohistorial.setText("");
            etiquetamontototal.setText("monto total gastado: $0");
        }
        
        
    }
    private void exportarPDF() {
        String rut = camporut.getText().trim();
        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(this, "primero busque un cliente");
            return;
        }

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("cliente_" + rut + ".pdf"));
            doc.open();
            Paragraph parrafo = new Paragraph("Taller Mecanico");
            parrafo.setAlignment(parrafo.ALIGN_CENTER);
            doc.add(parrafo);
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph(etiquetarutvalor.getText()));
            doc.add(new Paragraph(etiquetanombrevalor.getText()));
            doc.add(new Paragraph(etiquetatelefonovalor.getText()));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph(areatextohistorial.getText()));
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph(etiquetamontototal.getText()));

            doc.close();
            JOptionPane.showMessageDialog(this, "pdf guardado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "error al crear pdf");
        }
    }
}

