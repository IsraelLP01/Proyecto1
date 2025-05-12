import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Pasajeros {
    private int ID_pasajero;
    private String nombre;
    private String contraseña;
    private String correo;
    private Vuelos ticketVuelo; // Ticket reservado por el pasajero
    private String asientoReservado; // Asiento reservado por el pasajero

    public Pasajeros(int ID_pasajero, String nombre, String contraseña, String correo) {
        this.ID_pasajero = ID_pasajero;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    public int getID_pasajero() {
        return ID_pasajero;
    }

    public void setID_pasajero(int ID_pasajero) {
        this.ID_pasajero = ID_pasajero;
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

    public Vuelos getTicketVuelo() {
        return ticketVuelo;
    }

    public void setTicketVuelo(Vuelos ticketVuelo) {
        this.ticketVuelo = ticketVuelo;
    }

    public String getAsientoReservado() {
        return asientoReservado;
    }

    public void setAsientoReservado(String asientoReservado) {
        this.asientoReservado = asientoReservado;
    }

    public void registrarse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre:");
        this.nombre = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        this.contraseña = scanner.nextLine();
        System.out.println("Ingrese su correo:");
        this.correo = scanner.nextLine();
    }

    public void buscarVuelo(ArrayList<Vuelos> vuelos) {
        System.out.println("\n=== VUELOS DISPONIBLES ===");
        System.out.println("ID\tOrigen\tDestino\tFecha\tAsientos disponibles");
        System.out.println("----------------------------------------------");

        boolean hayVuelosDisponibles = false;

        // Lista todos los vuelos activos
        for (Vuelos vuelo : vuelos) {
            if (vuelo.isEstado()) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                String fechaStr = vuelo.getFecha_Vuelo() != null ? formatoFecha.format(vuelo.getFecha_Vuelo())
                        : "No disponible";

                System.out.println(vuelo.getID_Vuelo() + "\t" +
                        vuelo.getOrigen() + "\t" +
                        vuelo.getDestino() + "\t" +
                        fechaStr + "\t" +
                        vuelo.getAsientos_disponibles());
                hayVuelosDisponibles = true;
            }
        }

        if (!hayVuelosDisponibles) {
            System.out.println("No hay vuelos disponibles en este momento.");
        }
    }

    public void verTicket() {
        if (this.ticketVuelo == null) {
            System.out.println("No tiene ningun ticket reservado.");
            return;
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("\n=== TICKET DE VUELO ===");
        System.out.println("Pasajero: " + this.getNombre());
        System.out.println("ID Vuelo: " + this.ticketVuelo.getID_Vuelo());
        System.out.println("Origen: " + this.ticketVuelo.getOrigen());
        System.out.println("Destino: " + this.ticketVuelo.getDestino());

        if (this.ticketVuelo.getFecha_Vuelo() != null) {
            System.out.println("Fecha: " + formatoFecha.format(this.ticketVuelo.getFecha_Vuelo()));
        } else {
            System.out.println("Fecha: No disponible");
        }

        System.out.println("Asiento: " + this.asientoReservado);
        System.out.println("=======================");
    }

    public boolean reservarVuelo(ArrayList<Vuelos> listaVuelos, ArrayList<Asientos> listaAsientos) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar vuelos disponibles
        ArrayList<Vuelos> vuelosActivos = new ArrayList<>();
        System.out.println("\n=== VUELOS DISPONIBLES ===");

        for (Vuelos vuelo : listaVuelos) {
            if (vuelo.isEstado() && vuelo.getAsientos_disponibles() > 0) {
                vuelosActivos.add(vuelo);
            }
        }

        if (vuelosActivos.isEmpty()) {
            System.out.println("No hay vuelos disponibles en este momento.");
            return false;
        }

        System.out.println("ID\tOrigen\tDestino\tFecha\tAsientos disponibles");
        System.out.println("----------------------------------------------");

        for (Vuelos vuelo : vuelosActivos) {
            System.out.println(vuelo.getID_Vuelo() + "\t" +
                    vuelo.getOrigen() + "\t" +
                    vuelo.getDestino() + "\t" +
                    vuelo.getFecha_Vuelo() + "\t" +
                    vuelo.getAsientos_disponibles());
        }

        // Seleccionar un vuelo
        System.out.print("\nIngrese el ID del vuelo que desea reservar: ");
        int idVueloSeleccionado;
        try {
            idVueloSeleccionado = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un numero valido.");
            return false;
        }

        // Buscar el vuelo seleccionado
        Vuelos vueloSeleccionado = null;
        for (Vuelos vuelo : vuelosActivos) {
            if (vuelo.getID_Vuelo() == idVueloSeleccionado) {
                vueloSeleccionado = vuelo;
                break;
            }
        }

        if (vueloSeleccionado == null) {
            System.out.println("Error: El vuelo seleccionado no existe o no esta disponible.");
            return false;
        }

        // Mostrar asientos disponibles
        ArrayList<Asientos> asientosDisponibles = new ArrayList<>();
        for (Asientos asiento : listaAsientos) {
            if (asiento.getID_Vuelo() == vueloSeleccionado.getID_Vuelo() && asiento.isEstado()) {
                asientosDisponibles.add(asiento);
            }
        }

        if (asientosDisponibles.isEmpty()) {
            System.out.println("Error: No hay asientos disponibles en este vuelo.");
            return false;
        }

        System.out.println("\n=== ASIENTOS DISPONIBLES ===");
        // Muestra la lista de asientos como grid
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
                // Fallback to direct index selection
                seleccion = Integer.parseInt(seleccionInput);
                if (seleccion < 1 || seleccion > asientosDisponibles.size()) {
                    System.out.println("Selección inválida.");
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar números válidos.");
            return false;
        }

        Asientos asientoSeleccionado = asientosDisponibles.get(seleccion - 1);

        vueloSeleccionado.setAsientos_disponibles(vueloSeleccionado.getAsientos_disponibles() - 1);

        // Guarda la informacion del ticket
        this.ticketVuelo = vueloSeleccionado;
        this.asientoReservado = asientoSeleccionado.getNum_asiento();

        System.out.println("\n=== RESERVA CONFIRMADA ===");
        System.out.println("Pasajero: " + this.getNombre());
        System.out.println("Vuelo: " + vueloSeleccionado.getID_Vuelo() + " (" +
                vueloSeleccionado.getOrigen() + " - " + vueloSeleccionado.getDestino() + ")");
        System.out.println("Fecha: " + vueloSeleccionado.getFecha_Vuelo());
        System.out.println("Asiento: " + asientoSeleccionado.getNum_asiento());

        return true;
    }

    public boolean cancelarReserva(ArrayList<Asientos> listaAsientos) {
        if (this.ticketVuelo == null) { // Revisa si el pasajero tiene un vuelo reservado
            System.out.println("No tiene ningún vuelo reservado para cancelar.");
            return false;
        }

        Asientos asientoOcupado = null;
        for (Asientos asiento : listaAsientos) { // Revisa si el asiento reservado por el pasajero existe
            if (asiento.getID_Vuelo() == this.ticketVuelo.getID_Vuelo() &&
                    asiento.getNum_asiento().equals(this.asientoReservado)) {
                asientoOcupado = asiento;
                break;
            }
        }

        if (asientoOcupado == null) {
            System.out.println("Error: No se encontro el asiento " + this.asientoReservado);
            return false;
        }

        asientoOcupado.setEstado(true); // Marca el asiento como disponible
        this.ticketVuelo.setAsientos_disponibles(this.ticketVuelo.getAsientos_disponibles() + 1);

        int idVuelo = this.ticketVuelo.getID_Vuelo();
        String origen = this.ticketVuelo.getOrigen();
        String destino = this.ticketVuelo.getDestino();
        String asiento = this.asientoReservado;

        // Limpia la informacion del ticket
        this.ticketVuelo = null;
        this.asientoReservado = null;

        System.out.println("\n=== RESERVA CANCELADA ===");
        System.out.println("Vuelo: " + idVuelo + " (" + origen + " - " + destino + ")");
        System.out.println("Asiento liberado: " + asiento);
        System.out.println("Su reserva ha sido cancelada exitosamente.");

        return true;
    }
}