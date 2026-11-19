package frontend;
import exception.AnioInvalidoException;
import exception.PatenteDupliException;
import java.awt.*;
import javax.swing.*;
import modelos.mecanico;
import modelos.ordenesdetrabajo;
import sistemas.menu;
import vehiculo.*;

public class CrearTrabajoFrame extends BaseFrame {

    private JRadioButton rbAuto, rbMoto, rbCamioneta;
    private JTextField txtPatente, txtMarca, txtModelo, txtAnio, txtExtra;
    private JTextField txtRut, txtNombre, txtTelefono, txtCosto;
    private JComboBox<tipovehiculo> cbMotor;
    private JComboBox<String> cbMecanico;
    private JLabel lblExtra;
    private JTextField txtObservaciones;
    public CrearTrabajoFrame(menu backend) {
        super("Crear Trabajo", backend);
        setSize(520, 620);
    }

    @Override
    public void mostrar() {
        setLayout(new BorderLayout(10, 10));

      
        JLabel lblTitulo = new JLabel("registrar vehiculo y trabajo", SwingConstants.CENTER);
        lblTitulo.setFont(Estilo.fuente_normal);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(13, 2, 8, 8));
        panel.setBackground(Estilo.color_fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        //  tipo vehiiculo
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelTipo.setBackground(Estilo.color_fondo);
        rbAuto = new JRadioButton("Auto", true);
        rbMoto = new JRadioButton("Moto");
        rbCamioneta = new JRadioButton("Camioneta");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbAuto);
        grupo.add(rbMoto);
        grupo.add(rbCamioneta);
        panelTipo.add(rbAuto);
        panelTipo.add(rbMoto);
        panelTipo.add(rbCamioneta);

        rbAuto.addActionListener(e -> lblExtra.setText("cantidad de puertas:"));
        rbMoto.addActionListener(e -> lblExtra.setText("cilindrada (cc):"));
        rbCamioneta.addActionListener(e -> lblExtra.setText("capacidad carga (kg):"));

        txtPatente = new JTextField();
        txtMarca = new JTextField();
        txtModelo = new JTextField();
        cbMotor = new JComboBox<>(tipovehiculo.values());
        txtAnio = new JTextField();
        lblExtra = new JLabel("Cantidad de puertas:");
        txtExtra = new JTextField();
        txtRut = new JTextField();
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        cbMecanico = new JComboBox<>();
        for (mecanico m : backend.getMecanicos()) {
            cbMecanico.addItem(m.getNombre() + " (" + m.getCargo() + ")");
        }
        txtCosto = new JTextField();

        panel.add(new JLabel("Tipo:"));         
        panel.add(panelTipo);
        panel.add(new JLabel("Patente:"));      
        panel.add(txtPatente);
        panel.add(new JLabel("Marca:"));        
        panel.add(txtMarca);
        panel.add(new JLabel("Modelo:"));       
        panel.add(txtModelo);
        panel.add(new JLabel("Tipo de motor:"));
        panel.add(cbMotor);
        panel.add(new JLabel("Año:"));          
        panel.add(txtAnio);
        panel.add(lblExtra);                    
        panel.add(txtExtra);
        panel.add(new JLabel("RUT cliente:"));  
        panel.add(txtRut);
        panel.add(new JLabel("Nombre cliente:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Teléfono:"));     
        panel.add(txtTelefono);
        panel.add(new JLabel("Trabajador:"));   
        panel.add(cbMecanico);
        panel.add(new JLabel("Costo trabajo:"));
        panel.add(txtCosto);
        txtObservaciones = new JTextField();
        panel.add(new JLabel("Observaciones:")); panel.add(txtObservaciones);
        add(panel, BorderLayout.CENTER);

        // mas botones
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSur.setBackground(Estilo.color_fondo);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(Estilo.fuente_boton);
        btnVolver.addActionListener(e -> {
            dispose();
            new InicioVista(backend).mostrar();
        });

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(Estilo.color_principal);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(Estilo.fuente_boton);
        btnRegistrar.setOpaque(true);
        btnRegistrar.addActionListener(e -> registrar());

        panelSur.add(btnVolver);
        panelSur.add(btnRegistrar);
        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registrar() {
        String patente = txtPatente.getText().trim();
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String rut = txtRut.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (patente.isEmpty() || marca.isEmpty() || modelo.isEmpty() || rut.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (backend.getMecanicos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay trabajadores registrados", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int r = JOptionPane.showConfirmDialog(this, "registrar trabajo para " + patente + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) return;

        try {
            int anio = Integer.parseInt(txtAnio.getText().trim());
            int extra = Integer.parseInt(txtExtra.getText().trim());
            double costo = Double.parseDouble(txtCosto.getText().trim());
            tipovehiculo motor = (tipovehiculo) cbMotor.getSelectedItem();

            vehiculo nuevo;
            if (rbAuto.isSelected()) {
                nuevo = new auto(patente, marca, modelo, anio, motor, extra);
            } else if (rbMoto.isSelected()) {
                nuevo = new moto(patente, marca, modelo, anio, motor, extra);
            } else {
                nuevo = new camioneta(patente, marca, modelo, anio, motor, extra);
            }

            mecanico mec = backend.getMecanicos().get(cbMecanico.getSelectedIndex());
            ordenesdetrabajo ot = backend.crearTrabajo(nuevo, rut, nombre, telefono, mec, costo);
            ot.setObservaciones(txtObservaciones.getText().trim());
            JOptionPane.showMessageDialog(this,
                "Orden: " + ot.getNumeroorden() +
                "\nVehículo: " + nuevo.getPatente() +
                "\nCliente: " + nombre +
                "\nTrabajador: " + mec.getNombre());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año, dato extra y costo deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (PatenteDupliException | AnioInvalidoException ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}