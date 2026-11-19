package sistemas;

import exception.AnioInvalidoException;
import exception.PatenteDupliException;
import java.io.*;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashMap;

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
    //elimine el scanner 
    
    public menu() {
    }
 /*
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
*/
    
    
/*
    @SuppressWarnings("empty-statement")
    private void administrarVehiculos() {
    administrarVehiculos(new ArrayList<>(lista));
}
*/


/*
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
*/
/*    
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
*/
  
    //modificacion crearTrabajo
    
    public ordenesdetrabajo crearTrabajo(vehiculo nuevovehiculo, String rutcliente, String nombrecliente, String telefonocliente, mecanico mecanicoseleccionado, double costotrabajo) throws PatenteDupliException {
        for (vehiculo vehiculo : lista) {
            if (vehiculo.getPatente().equalsIgnoreCase(nuevovehiculo.getPatente())) {
                throw new PatenteDupliException(nuevovehiculo.getPatente());
            }
        }

        lista.add(nuevovehiculo);

        // [REQUISITO: Hashmap]
        cliente clienteseleccionado = clientes.get(rutcliente);
        if (clienteseleccionado == null) {
            clienteseleccionado = new cliente(rutcliente, nombrecliente, telefonocliente);
            clientes.put(rutcliente, clienteseleccionado);
        }

        ordenesdetrabajo nuevaorden = new ordenesdetrabajo(nuevovehiculo, mecanicoseleccionado, costotrabajo);
        ordenes.add(nuevaorden);

        return nuevaorden;
    }
    
 
    
/*    
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
*/
    
    public boolean venderRepuesto(ordenesdetrabajo otAsociada, repuesto repSel, int cant) {
       if (repSel == null || cant <= 0 || cant > repSel.getStock()) {
           return false;
       }
       repSel.disminuirStock(cant);
       if (otAsociada != null) {
           for (int k = 0; k < cant; k++) {
               otAsociada.getRepuestosusado().add(repSel);
           }
           otAsociada.calcularCostoTotal();
       } else {
           totalVentas += repSel.getPrecio() * cant;
       }
       return true;
   }

    
    
    public void crearTrabajador(String rut, String nombre, String cargo) {
        mecanicos.add(new mecanico(rut, nombre, cargo));
    }
    
    public void crearRepuesto(String codigo, String nombre, double precio, int stock) {
        repuestos.add(new repuesto(codigo, nombre, precio, stock));
    }

    public vehiculo buscarVehiculo(String patente) {
        for (vehiculo v : lista) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                return v;
            }
        }
        return null;
    }
    public cliente consultarCliente(String rut) {
        return clientes.get(rut.trim());
    }
/*    
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
*/    
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
            ordenesdetrabajo.setContadorordenes(ordenes.size() + 1);
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
    
    //nuevos getters y seters
    
    public ArrayList<vehiculo> getListaVehiculos() {
        return lista;
    }
    public HashMap<String, cliente> getClientes() {
        return clientes;
    }
    public ArrayList<mecanico> getMecanicos() {
        return mecanicos;
    }
    public ArrayList<repuesto> getRepuestos() {
        return repuestos;
    }
    public ArrayList<ordenesdetrabajo> getOrdenes() {
        return ordenes;
    }
    public double getTotalVentas() {
        return totalVentas;
    }
    
    
    
    
    
}
