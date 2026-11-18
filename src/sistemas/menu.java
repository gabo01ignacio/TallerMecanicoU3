package sistemas;

import exception.AnioInvalidoException;
import exception.PatenteDupliException;
import java.io.*;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import modelos.*;
import vehiculo.auto;
import vehiculo.camioneta;
import vehiculo.moto;   // [REQUISITO: Hashmap]
import vehiculo.tipovehiculo;
import vehiculo.vehiculo;

public class menu {
    private static final String Archivo = "datos.dat";
    private static final String Titulo = "Taller mecanico";
    
    private ArrayList<vehiculo> lista = new ArrayList<>();
    // [REQUISITO: Hashmap]
    private HashMap<String, cliente> clientes = new HashMap<>();
    private ArrayList<mecanico> mecanicos = new ArrayList<>();
    private ArrayList<repuesto> repuestos = new ArrayList<>();
    private ArrayList<ordenesdetrabajo> ordenes = new ArrayList<>();
    private double totalVentas = 0.0;
    
    // [REQUISITO: Scanner]
    private Scanner sc = new Scanner(System.in);
    
    public menu() {
    }
    
    // [REQUISITO: Scanner]
    public void iniciar() {
        cargar();
        int opcion;
        do {
            System.out.println("=============================================================");
            System.out.println("=                        " + Titulo + "                     =");
            System.out.println("=============================================================");
            System.out.println("==================== Consultas ==============================");
            System.out.println("= [ 1 ] Mostrar vehiculos        |  [ 2 ] Mostrar clientes  =");
            System.out.println("= [ 3 ] Mostrar trabajadores     |  [ 4 ] Crear trabajo     =");
            System.out.println("=============================================================");
            System.out.println("===================  Administracion =========================");
            System.out.println("= [ 5 ] Administrar trabajos     |  [ 6 ] Vender repuestos  =");
            System.out.println("= [ 7 ] Registrar trabajador     |  [ 8 ] Registrar repuesto=");    
            System.out.println("=============================================================");
            System.out.println("==================== Busqueda y Reportes ====================");
            System.out.println("= [ 9 ]  Buscar vehiculo por patente                        =");
            System.out.println("= [ 10 ] Consultar cliente                                  =");
            System.out.println("= [ 11 ] Ver reporte de ventas                              =");
            System.out.println("=============================================================");
            System.out.println("= [ 0 ] Guardar y salir                                     =");
            System.out.println("=============================================================");
            opcion = Integer.parseInt(sc.nextLine());
            
            if (opcion == 1) administrarVehiculos();
            else if (opcion == 2) administrarClientes();
            else if (opcion == 3) administrarTrabajadores();
            else if (opcion == 4) crearTrabajo();
            else if (opcion == 5) administrarTrabajos();
            else if (opcion == 6) venderRepuesto();
            else if (opcion == 7) crearTrabajador();
            else if (opcion == 8) crearRepuesto();
            else if (opcion == 9) buscarVehiculo();
            else if (opcion == 10) consultarCliente();
            else if (opcion ==11) reporte();
            else if (opcion ==12) nukeardatos();
            else if (opcion !=0) System.out.print("Opcion no valida");
        } while (opcion != 0);
        guardar();
    }

    @SuppressWarnings("empty-statement")
    private void administrarVehiculos() {
    administrarVehiculos(new ArrayList<>(lista));
}

private void administrarVehiculos(ArrayList<vehiculo> vistaVehiculos) {
    if (vistaVehiculos.isEmpty()) {
        System.out.println(" ");
        System.out.println("===============================");
        System.out.println(" No hay vehiculos registrados");
        System.out.println("===============================");
        return;
    }

    System.out.println(" ");
    System.out.println("=============================");
    System.out.println("   Vehiculos registrados");
    System.out.println("=============================");

    for (int i = 0; i < vistaVehiculos.size(); i++) {
        vehiculo v = vistaVehiculos.get(i);
        System.out.println((i + 1) + ". " + v.obtenerDescripcion());
        System.out.println("costo base: $" + (int) v.calcularCostoBase());
        System.out.println(" ");
    }

    System.out.print("1. ordenar por anio (de menor a mayor) | 2. ordenar por ingreso | 0. volver al menu principal: ");
    int opcion = Integer.parseInt(sc.nextLine());

    if (opcion == 1) {
        Collections.sort(vistaVehiculos);
        administrarVehiculos(vistaVehiculos); 
    } else if (opcion == 2) {
        administrarVehiculos(new ArrayList<>(lista)); 
    } else if (opcion == 0) {
        return; 
    }
}

    
    private void administrarClientes() {
        System.out.println("");
        System.out.println("=============================");
        System.out.println("      lista de clientes");
        System.out.println("=============================");
        if (clientes.isEmpty()) {
            System.out.println("no hay clientes registrados.");
            return;
        }
        int contador = 1;
        for (cliente cliente : clientes.values()) {
            System.out.println(contador + ". rut: " + cliente.getRut() + " | nombre: " + cliente.getNombre() + " | telefono: " + cliente.getTelefono());
            contador+=1;
        }
        System.out.println(" ");
    }

    private void administrarTrabajadores() {
        System.out.println("");
        System.out.println("=============================");
        System.out.println("    Lista de trabajadores");
        System.out.println("=============================");
        if (mecanicos.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }
        for (int i = 0; i < mecanicos.size(); i++) {
            mecanico mecanico = mecanicos.get(i);
            System.out.println((i + 1) + ". rut: " + mecanico.getRut() + " | nombre: " + mecanico.getNombre() + " | cargo: " + mecanico.getCargo());
        }
        System.out.println(" ");
    }

    private void crearTrabajo() {
        try {
            System.out.println("");
            System.out.println("=============================");
            System.out.println("     Registrar vehiculo");
            System.out.println("=============================");
            System.out.println("");
            System.out.print("Tipo 1. Auto | 2. Moto | 3. Camioneta: ");
            int tipo = Integer.parseInt(sc.nextLine());
            System.out.print("Patente: ");
            String patente = sc.nextLine();

            for (vehiculo vehiculo : lista) {
                if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                    throw new PatenteDupliException(patente);
                }
            }

            System.out.print("Marca: ");
            String marca = sc.nextLine();
            System.out.print("Modelo: ");
            String modelo = sc.nextLine();
            System.out.print("Tipo de motor (1= Bencinero, 2= Diesel, 3= Hibrido, 4= Electrico): ");
            int opcMotor = Integer.parseInt(sc.nextLine()); 
            tipovehiculo motorSeleccionado = tipovehiculo.BENCINERO; 
            System.out.print("Anio: ");
            int anio = Integer.parseInt(sc.nextLine());
            
            switch (opcMotor) {
                case 2:
                    motorSeleccionado = tipovehiculo.DIESEL;
                    break;
                case 3:
                    motorSeleccionado = tipovehiculo.HIBRIDO;
                    break;
                case 4:
                    motorSeleccionado = tipovehiculo.ELECTRICO;
                    break;
                default:
                    break;
            }

            vehiculo nuevovehiculo = null;
            switch (tipo) {
                case 1:
                    System.out.print("Cantidad de puertas: ");
                    nuevovehiculo = new auto(patente, marca, modelo, anio, motorSeleccionado, Integer.parseInt(sc.nextLine()));
                    break;
                case 2:
                    System.out.print("cc: ");
                    nuevovehiculo = new moto(patente, marca, modelo, anio, motorSeleccionado, Integer.parseInt(sc.nextLine()));
                    break;
                default:
                    System.out.print("Capacidad de carga (en kg): ");
                    nuevovehiculo = new camioneta(patente, marca, modelo, anio, motorSeleccionado, Integer.parseInt(sc.nextLine()));
                    break;
            }
            lista.add(nuevovehiculo);

            System.out.println(" ");
            System.out.println("Asignar cliente");
            System.out.print("Ingrese rut del cliente: ");
            String rutcliente = sc.nextLine();

            // [REQUISITO: Hashmap]
            cliente clienteseleccionado = clientes.get(rutcliente);

            if (clienteseleccionado == null) {
                System.out.println("Cliente no encontrado. Registrando cliente nuevo:");
                System.out.print("Nombre completo: ");
                String nombrecliente = sc.nextLine();
                System.out.print("Telefono: ");
                String telefonocliente = sc.nextLine();
                clienteseleccionado = new cliente(rutcliente, nombrecliente, telefonocliente);
                clientes.put(rutcliente, clienteseleccionado);
            } else {
                System.out.println("Cliente encontrado: " + clienteseleccionado.getNombre());
            }

            System.out.println(" ");
            System.out.println("Asignar trabajador");
            administrarTrabajadores();
            System.out.print("Seleccione numero del trabajador: ");
            int posicionmecanico = Integer.parseInt(sc.nextLine()) - 1;

            if (posicionmecanico < 0 || posicionmecanico >= mecanicos.size()) {
                System.out.println("Trabajador no valido.");
                return;
            }
            mecanico mecanicoseleccionado = mecanicos.get(posicionmecanico);

            System.out.print("Ingrese costo del trabajo: ");
            double costotrabajo = Double.parseDouble(sc.nextLine());

            ordenesdetrabajo nuevaorden = new ordenesdetrabajo(nuevovehiculo, mecanicoseleccionado, costotrabajo);
            ordenes.add(nuevaorden);

            System.out.println(" ");
            System.out.println("Numero de Orden: " + nuevaorden.getNumeroorden());
            System.out.println("Vehiculo: " + nuevovehiculo.getPatente());
            System.out.println("Cliente: " + clienteseleccionado.getNombre());
            System.out.println("Trabajador: " + mecanicoseleccionado.getNombre());

        } catch (PatenteDupliException | AnioInvalidoException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }
    
    private void administrarTrabajos() {
        System.out.println(" ");
        System.out.println("Historial de trabajos");
        if (ordenes.isEmpty()) {
            System.out.println("No hay trabajos registrados");
            return;
        }

        for (int i = 0; i < ordenes.size(); i++) {
            ordenesdetrabajo orden = ordenes.get(i);
            System.out.println(orden.obtenerDetalleCompleto());
        }

        System.out.println(" ");
        System.out.print("Ingrese el numero del trabajo para cambiar estado (0 para volver al menu): ");
        int seleccion = Integer.parseInt(sc.nextLine());

        if (seleccion == 0) {
            return;
        }

        int posicion = seleccion - 1;
        if (posicion >= 0 && posicion < ordenes.size()) {
            System.out.print("Seleccione estado: ");
            System.out.print("1. Pendiente | 2. En Reparacion | 3. Finalizado | 4. Cancelado: ");
            int nuevoestado = Integer.parseInt(sc.nextLine());

            estadoorden estado = estadoorden.PENDIENTE;
            if (nuevoestado == 2) estado = estadoorden.REPARACION;
            else if (nuevoestado == 3) estado = estadoorden.FINALIZADO;
            else if (nuevoestado == 4) estado = estadoorden.CANCELADO;

            ordenes.get(posicion).cambiarEstado(estado);
        } else {
            System.out.println("Numero de trabajo no valido");
        }
    }

    private void venderRepuesto() {
    if (repuestos.isEmpty()) {
        System.out.println("No hay repuestos en el sistema.");
        return;
    }

    System.out.println("\n--- Venta de repuestos ---");
    System.out.println("1. Venta directa por mostrador");
    System.out.println("2. Cargar repuesto a una Orden de Trabajo");
    System.out.print("Seleccione tipo de venta: ");
    
    String modo = sc.nextLine().trim();
    ordenesdetrabajo otAsociada = null;

    if (modo.equals("2")) {
        if (ordenes.isEmpty()) {
            System.out.println("No hay ordenes de trabajo registradas. Se procedera como venta directa.");
        } else {
            System.out.println("\nOrdenes de trabajo disponibles:");
            for (int i = 0; i < ordenes.size(); i++) {
                ordenesdetrabajo ot = ordenes.get(i);
                String pat = (ot.getVehiculo() != null) ? ot.getVehiculo().getPatente() : "S/I";
                System.out.println((i + 1) + ". " + ot.getNumeroorden() + 
                                   " [Vehiculo: " + pat + " | Estado: " + ot.getEstado() + 
                                   " | Costo actual: $" + (int)ot.getCostoTotal() + "]");
            }
            System.out.print("Seleccione numero de Orden de Trabajo: ");
            try {
                int posOt = Integer.parseInt(sc.nextLine()) - 1;
                if (posOt >= 0 && posOt < ordenes.size()) {
                    otAsociada = ordenes.get(posOt);
                    System.out.println("Seleccionada orden: " + otAsociada.getNumeroorden());
                } else {
                    System.out.println("Seleccion invalida. Se continuara como venta directa.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Se continuara como venta directa.");
            }
        }
    }

    boolean continuar = true;
    double subtotalVenta = 0.0;

    while (continuar) {
        System.out.println("\nCatalogo de repuestos:");
        for (int i = 0; i < repuestos.size(); i++) {
            repuesto r = repuestos.get(i);
            System.out.println((i + 1) + ". " + r.getNombre() + " - $" + (int)r.getPrecio() + " [Stock actual: " + r.getStock() + "]");
        }

        System.out.print("Seleccione repuesto (0 para terminar): ");
        try {
            int eleccion = Integer.parseInt(sc.nextLine());
            if (eleccion == 0) break;

            if (eleccion < 1 || eleccion > repuestos.size()) {
                System.out.println("Opcion no valida.");
                continue;
            }

            repuesto repSel = repuestos.get(eleccion - 1);
            System.out.print("Cantidad: ");
            int cant = Integer.parseInt(sc.nextLine());

            if (cant > repSel.getStock()) {
                System.out.println("Stock insuficiente.");
            } else if (cant <= 0) {
                System.out.println("La cantidad debe ser mayor a 0.");
            } else {
                repSel.disminuirStock(cant);
                double subtotal = repSel.getPrecio() * cant;

                if (otAsociada != null) {
                    for (int k = 0; k < cant; k++) {
                        otAsociada.getRepuestosusado().add(repSel);
                    }
                    otAsociada.calcularCostoTotal();
                    System.out.println("-> Cargado a " + otAsociada.getNumeroorden() + ": " + cant + "x " + repSel.getNombre() + 
                                       " ($" + (int)subtotal + "). Nuevo costo del trabajo: $" + (int)otAsociada.getCostoTotal());
                } else {
                    subtotalVenta += subtotal;
                    totalVentas += subtotal;
                    System.out.println("-> Anadido a venta directa: " + cant + "x " + repSel.getNombre() + " = $" + (int)subtotal);
                }
            }

            System.out.print("Desea agregar otro repuesto? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("n")) {
                continuar = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opcion no valida.");
        }
    }

    System.out.println("\n=========================================");
    if (otAsociada != null) {
        System.out.println("Repuestos cargados exitosamente a la orden " + otAsociada.getNumeroorden() + ".");
        System.out.println("Costo total final de la Orden de Trabajo: $" + (int)otAsociada.getCostoTotal());
    } else {
        System.out.println("Venta directa por mostrador finalizada.");
        System.out.println("Monto total abonado: $" + (int)subtotalVenta);
    }
    System.out.println("=========================================");
}


    
    
    private void crearTrabajador() {
        System.out.println(" "); 
        System.out.println("====== Registrar trabajador ======");
        System.out.print("Rut: "); 
        String rut = sc.nextLine(); 
        System.out.print("Nombre completo: "); String nombre = sc.nextLine(); 
        System.out.print("Cargo: "); 
        String cargo = sc.nextLine(); 
        mecanicos.add(new mecanico(rut, nombre, cargo)); 
        System.out.println("Trabajador registrado con exito"); 
    }
    
    private void crearRepuesto() {
        System.out.println(" "); 
        System.out.println("====== Registrar repuesto ======"); 
        System.out.print("Codigo: "); 
        String codigo = sc.nextLine(); 
        System.out.print("Nombre: "); 
        String nombre = sc.nextLine(); 
        System.out.print("Precio: $"); 
        double precio = Double.parseDouble(sc.nextLine()); 
        System.out.print("Stock inicial: "); 
        int stock = Integer.parseInt(sc.nextLine()); 
        repuestos.add(new repuesto(codigo, nombre, precio, stock)); 
        System.out.println("Repuesto registrado con exito");
    }

    private void buscarVehiculo() {
        System.out.println(" ");
        System.out.println("====== Busqueda de vehiculo por patente ======"); 
        System.out.print("Ingrese patente: "); 
        String patente = sc.nextLine(); for (vehiculo v : lista) {
            if (v.getPatente().equalsIgnoreCase(patente)) { 
                System.out.println("Vehiculo encontrado:"); 
                System.out.println(v.obtenerDescripcion()); 
                System.out.println("Costo base: $" + (int) v.calcularCostoBase()); 
                return; 
            } 
        } System.out.println("No se encontro ningun vehiculo con esa patente");
    }
    
    private void consultarCliente(){
        System.out.println(" ");
        System.out.println("====== Consulta de cliente por rut ======");
        System.out.print("Ingrese rut: ");
        String rut = sc.nextLine();

        cliente c = clientes.get(rut);
    if (c == null) {
        System.out.println("No se encontro ningun cliente con ese rut");
        return;
    }

    System.out.println(" ");
    System.out.println("Datos del cliente:");
    System.out.println("Rut: " + c.getRut());
    System.out.println("Nombre: " + c.getNombre());
    System.out.println("Telefono: " + c.getTelefono());
    }
    
    private void reporte() {
    double recTrabajos = 0.0;
    int pendiente = 0, reparacion = 0, finalizado = 0, cancelado = 0;

    for (ordenesdetrabajo orden : ordenes) {
        recTrabajos += orden.getCostoTotal();
        if (orden.getEstado() == estadoorden.PENDIENTE) pendiente++;
        else if (orden.getEstado() == estadoorden.REPARACION) reparacion++;
        else if (orden.getEstado() == estadoorden.FINALIZADO) finalizado++;
        else if (orden.getEstado() == estadoorden.CANCELADO) cancelado++;
    }

    System.out.println("");
    System.out.println("====== Reporte del taller ======");
    System.out.println("");
    System.out.println("Total de ordenes: " + ordenes.size());
    System.out.println("-Pendientes: " + pendiente);
    System.out.println("-En Reparacion: " + reparacion);
    System.out.println("-Finalizadas: " + finalizado);
    System.out.println("-Canceladas: " + cancelado);
    System.out.println("Ganancia de los trabajos: $" + (int) recTrabajos);
    System.out.println("Ganancia de los repuestos: $" + (int) totalVentas);
    System.out.println("Ganancia total: $" + (int) (recTrabajos + totalVentas));
    System.out.println("");
    }
    
    // [REQUISITO: Persistencia]
    public void guardar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Archivo))) {
            out.writeObject(lista);
            out.writeObject(repuestos);
            out.writeObject(clientes);
            out.writeObject(mecanicos);
            out.writeObject(ordenes);
            out.writeObject(totalVentas);
            System.out.println("Guardado listo en el archivo " + Archivo);
            System.out.println("Hasta luego");
        } catch (Exception e) {
            System.out.println("Error al guardar");
        }
    }

    // [REQUISITO: Persistencia]
    public void cargar() {
        File f = new File(Archivo);
        if (!f.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Archivo))) {
            lista = (ArrayList<vehiculo>) in.readObject();
            repuestos = (ArrayList<repuesto>) in.readObject();
            clientes = (HashMap<String, cliente>) in.readObject();
            mecanicos = (ArrayList<mecanico>) in.readObject();
            ordenes = (ArrayList<ordenesdetrabajo>) in.readObject();
            totalVentas = (Double) in.readObject();
            System.out.println("Cargado listo (" + lista.size() + " vehiculos).");
        } catch (Exception e) {
            System.out.println("Error al cargar");
        }
    }
    
    public void nukeardatos(){
        System.out.println("\n--- LIMPIEZA DE DATOS DEL SISTEMA ---");
    
    lista.clear();
    clientes.clear();
    mecanicos.clear();
    repuestos.clear();
    ordenes.clear();

    File f = new File(Archivo);
    if (f.exists()) {
        if (f.delete()) {
            System.out.println("Se ha eliminado el archivo de persistencia '" + Archivo + "' y limpiado los datos en memoria.");
        } else {
            System.out.println("No se pudo eliminar el archivo '" + Archivo + "'.");
        }
    } else {
        System.out.println("Datos en memoria limpiados. No se encontro archivo guardado en disco.");
    }

    }
}
