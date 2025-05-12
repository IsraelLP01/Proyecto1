import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Empleados {
    // Atributos
    private int ID_Empleado;
    private String nombre;
    private String contraseña;
    private String correo;

    // Constructor
    public Empleados(int ID_Empleado, String nombre, String contraseña, String correo) {
        this.ID_Empleado = ID_Empleado;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    // Getters y Setters
    public int getID_Empleado() {
        return ID_Empleado;
    }

    public void setID_Empleado(int ID_Empleado) {
        this.ID_Empleado = ID_Empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Vuelos darDeAltaVuelo(int asientosDisponibles, int idEmpleado, String origen,
            String destino, Date fechaVuelo, ArrayList<Asientos> listaAsientos) {
        int idVuelo = generarIDVuelo(); // Genrera un ID unico para el vuelo
        Vuelos nuevoVuelo = new Vuelos(idVuelo, asientosDisponibles, idEmpleado, origen, destino,
                fechaVuelo, true);

        // Crea los asientos para el nuevo vuelo
        for (int i = 1; i <= asientosDisponibles; i++) {
            int idAsiento = generarIDAsiento(); // Genera un ID unico para el asiento
            String numAsiento = "A" + i;
            Asientos nuevoAsiento = new Asientos(idAsiento, numAsiento, true, idVuelo);
            listaAsientos.add(nuevoAsiento);
        }

        System.out.println("Vuelo creado exitosamente con ID: " + nuevoVuelo.getID_Vuelo());
        System.out.println("Se crearon " + asientosDisponibles + " asientos para este vuelo.");
        return nuevoVuelo;
    }

    private static int generarIDVuelo() {
        return (int) (Math.random() * 10000); // Genera un ID aleatorio entre 0 y 9999
    }

    private static int generarIDAsiento() {
        return (int) (Math.random() * 100000); // Genera un ID aleatorio entre 0 y 99999
    }

    public boolean darDeBajaVuelo(int idVuelo, Vuelos vueloADarBaja, ArrayList<Pasajeros> listaPasajeros) {
        if (vueloADarBaja == null) {
            System.out.println("Error: El vuelo no existe.");
            return false;
        }
        if (vueloADarBaja.getID_Vuelo() != idVuelo) {
            System.out.println("Error: El ID del vuelo no coincide.");
            return false;
        }
        ///////////////////////////////////////////////////////////////////////////
        // Eliminar tickets de pasajeros que tienen este vuelo
        int pasajerosAfectados = 0;
        for (Pasajeros pasajero : listaPasajeros) {
            if (pasajero.getTicketVuelo() != null &&
                    pasajero.getTicketVuelo().getID_Vuelo() == idVuelo) {
                // Eliminar el ticket del pasajero
                pasajero.setTicketVuelo(null);
                pasajero.setAsientoReservado(null);
                pasajerosAfectados++;
            }
        }

        // Marcar el vuelo como inactivo
        vueloADarBaja.setEstado(false);

        System.out.println("Vuelo con ID " + idVuelo + " dado de baja exitosamente.");
        if (pasajerosAfectados > 0) {
            System.out.println("Se eliminaron los tickets de " + pasajerosAfectados + " pasajero(s).");
        }

        return true;
    }

    /////////////////////////////////////////////////////
    public Pasajeros buscarPasajero(ArrayList<Pasajeros> listaPasajeros, String criterio, String valor) {
        if (listaPasajeros == null || listaPasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados en el sistema.");
            return null;
        }

        switch (criterio.toLowerCase()) {
            case "id":
                try {
                    int idBuscado = Integer.parseInt(valor);
                    for (Pasajeros pasajero : listaPasajeros) {
                        if (pasajero.getID_pasajero() == idBuscado) {
                            System.out.println("Pasajero encontrado: " + pasajero.getNombre());
                            return pasajero;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: El ID debe ser un número válido.");
                    return null;
                }
                break;

            case "nombre":
                for (Pasajeros pasajero : listaPasajeros) {
                    if (pasajero.getNombre().equalsIgnoreCase(valor)) {
                        System.out.println("Pasajero encontrado con ID: " + pasajero.getID_pasajero());
                        return pasajero;
                    }
                }
                break;

            case "correo":
                for (Pasajeros pasajero : listaPasajeros) {
                    if (pasajero.getCorreo().equalsIgnoreCase(valor)) {
                        System.out.println("Pasajero encontrado: " + pasajero.getNombre() +
                                " con ID: " + pasajero.getID_pasajero());
                        return pasajero;
                    }
                }
                break;

            default:
                System.out.println("Criterio de busqueda no valido. Use 'id', 'nombre' o 'correo'.");
                return null;
        }

        System.out.println("No se encontro ningun pasajero con ese " + criterio + ".");
        return null;
    }

    public ArrayList<Vuelos> buscarVuelosActivos(ArrayList<Vuelos> listaVuelos) {
        if (listaVuelos == null || listaVuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados en el sistema.");
            return new ArrayList<>();
        }

        ArrayList<Vuelos> vuelosActivos = new ArrayList<>();
        System.out.println("\n=== VUELOS ACTIVOS ===");

        for (Vuelos vuelo : listaVuelos) {
            if (vuelo.isEstado()) {
                vuelosActivos.add(vuelo);
            }
        }

        if (vuelosActivos.isEmpty()) {
            System.out.println("No hay vuelos activos en este momento.");
        } else {
            System.out.println("Se encontraron " + vuelosActivos.size() + " vuelos activos:");
            System.out.println("-------------------------------------");

            for (Vuelos vuelo : vuelosActivos) {
                System.out.println("ID: " + vuelo.getID_Vuelo());
                System.out.println("Origen: " + vuelo.getOrigen());
                System.out.println("Destino: " + vuelo.getDestino());
                System.out.println("Fecha: " + vuelo.getFecha_Vuelo());
                System.out.println("Asientos disponibles: " + vuelo.getAsientos_disponibles());
                System.out.println("-------------------------------------");
            }
        }

        return vuelosActivos;
    }

    public boolean darDeAltaUsuarioAVuelo(Pasajeros pasajero, Vuelos vuelo, ArrayList<Asientos> listaAsientos) {
        if (pasajero == null) {
            System.out.println("Error: El pasajero no existe.");
            return false;
        }

        if (vuelo == null) {
            System.out.println("Error: El vuelo no existe.");
            return false;
        }

        if (!vuelo.isEstado()) {
            System.out.println("Error: El vuelo no esta activo.");
            return false;
        }

        if (vuelo.getAsientos_disponibles() <= 0) {
            System.out.println("Error: No hay asientos disponibles en este vuelo.");
            return false;
        }

        ArrayList<Asientos> asientosDisponibles = new ArrayList<>();
        for (Asientos asiento : listaAsientos) {
            if (asiento.getID_Vuelo() == vuelo.getID_Vuelo() && asiento.isEstado()) {
                asientosDisponibles.add(asiento);
            }
        }

        if (asientosDisponibles.isEmpty()) {
            System.out.println("Error: No hay asientos disponibles en este vuelo.");
            return false;
        }

        System.out.println("\n=== ASIENTOS DISPONIBLES ===");
        // Muestra los asientos como grid
        int columnas = 5;
        System.out.println("\nSeleccione un asiento:");

        System.out.print("    ");
        for (int i = 0; i < columnas; i++) {
            System.out.printf("%-6s", (i + 1));
        }
        System.out.println("\n    " + "-".repeat(columnas * 6));

        for (int i = 0; i < asientosDisponibles.size(); i += columnas) {
            System.out.printf("%2d | ", (i / columnas + 1));
            for (int j = 0; j < columnas && (i + j) < asientosDisponibles.size(); j++) {
                System.out.printf("%-6s", asientosDisponibles.get(i + j).getNum_asiento());
            }
            System.out.println();
        }

        System.out.println("\nIngrese fila y columna (ejemplo: 2,3):");
        Scanner scanner = new Scanner(System.in);
        String seleccionInput = scanner.nextLine();
        int seleccion = 0;

        try {
            String[] partes = seleccionInput.split(",");
            if (partes.length == 2) {
                int fila = Integer.parseInt(partes[0].trim());
                int columna = Integer.parseInt(partes[1].trim());
                seleccion = (fila - 1) * columnas + columna;

                if (seleccion < 1 || seleccion > asientosDisponibles.size()) {
                    System.out.println("Selección inválida.");
                    return false;
                }
            } else {
                seleccion = Integer.parseInt(seleccionInput);
                if (seleccion < 1 || seleccion > asientosDisponibles.size()) {
                    System.out.println("Seleccion invalida.");
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar numeros válidos.");
            return false;
        }

        Asientos asientoSeleccionado = asientosDisponibles.get(seleccion - 1);

        asientoSeleccionado.setEstado(false);

        vuelo.setAsientos_disponibles(vuelo.getAsientos_disponibles() - 1);

        pasajero.setTicketVuelo(vuelo);
        pasajero.setAsientoReservado(asientoSeleccionado.getNum_asiento());

        System.out.println("Pasajero " + pasajero.getNombre() + " con ID " +
                pasajero.getID_pasajero() + " registrado exitosamente en el vuelo " +
                vuelo.getID_Vuelo() + " (" + vuelo.getOrigen() + " - " +
                vuelo.getDestino() + ")");
        System.out.println("Asiento asignado: " + asientoSeleccionado.getNum_asiento());
        System.out.println("Asientos disponibles restantes: " + vuelo.getAsientos_disponibles());

        return true;
    }

    public boolean darDeBajaUsuarioDeVuelo(Pasajeros pasajero, Vuelos vuelo, ArrayList<Asientos> listaAsientos) {
        if (pasajero == null) {
            System.out.println("Error: El pasajero no existe.");
            return false;
        }

        if (vuelo == null) {
            System.out.println("Error: El vuelo no existe.");
            return false;
        }

        if (!vuelo.isEstado()) {
            System.out.println("Error: El vuelo no está activo.");
            return false;
        }

        if (pasajero.getTicketVuelo() == null || pasajero.getTicketVuelo().getID_Vuelo() != vuelo.getID_Vuelo()) {
            System.out.println("Error: El pasajero no tiene un boleto para este vuelo.");
            return false;
        }

        String numAsiento = pasajero.getAsientoReservado();
        if (numAsiento == null) {
            System.out.println("Error: El pasajero no tiene un asiento asignado en este vuelo.");
            return false;
        }

        Asientos asientoOcupado = null;
        for (Asientos asiento : listaAsientos) {
            if (asiento.getID_Vuelo() == vuelo.getID_Vuelo() &&
                    asiento.getNum_asiento().equals(numAsiento)) {
                asientoOcupado = asiento;
                break;
            }
        }

        if (asientoOcupado == null) {
            System.out.println("Error: No se encontró el asiento " + numAsiento + " en este vuelo.");
            return false;
        }

        asientoOcupado.setEstado(true);

        vuelo.setAsientos_disponibles(vuelo.getAsientos_disponibles() + 1);

        pasajero.setTicketVuelo(null);
        pasajero.setAsientoReservado(null);

        System.out.println("Pasajero " + pasajero.getNombre() + " con ID " +
                pasajero.getID_pasajero() + " dado de baja exitosamente del vuelo " +
                vuelo.getID_Vuelo() + " (" + vuelo.getOrigen() + " - " +
                vuelo.getDestino() + ")");
        System.out.println("Asiento liberado: " + asientoOcupado.getNum_asiento());
        System.out.println("Asientos disponibles actualizados: " + vuelo.getAsientos_disponibles());

        return true;
    }
}