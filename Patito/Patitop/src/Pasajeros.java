import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Pasajeros {
    private int ID_pasajero;
    private String nombre;
    private String contraseña;
    private String correo;
    private boolean rol; // true = admin, false = pasajero
    private Vuelos ticketVuelo; // Ticket reservado por el pasajero
    private String asientoReservado; // Asiento reservado por el pasajero

    public Pasajeros(int ID_pasajero, String nombre, String contraseña, String correo, boolean rol) {
        this.ID_pasajero = ID_pasajero;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rol = rol;
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

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public void registrarse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su nombre:");
        this.nombre = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        this.contraseña = scanner.nextLine();
        System.out.println("Ingrese su correo:");
        this.correo = scanner.nextLine();
        this.rol = false; // Por defecto, el rol es pasajero
    }

    public void buscarVuelo(ArrayList<Vuelos> vuelos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el origen del vuelo:");
        String origen = scanner.nextLine();
        System.out.println("Ingrese el destino del vuelo:");
        String destino = scanner.nextLine();

        System.out.println("Vuelos disponibles:");
        for (Vuelos vuelo : vuelos) {
            if (vuelo.getOrigen().equalsIgnoreCase(origen) && vuelo.getDestino().equalsIgnoreCase(destino)
                    && vuelo.isEstado()) {
                vuelo.mostrarInformacion();
            }
        }
    }

    public void verTicket() {
        if (this.ticketVuelo == null) {
            System.out.println("No tiene ningun ticket reservado.");
            return;
        }

        // Formatear fechas y horas correctamente
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("\n=== TICKET DE VUELO ===");
        System.out.println("Pasajero: " + this.getNombre());
        System.out.println("ID Vuelo: " + this.ticketVuelo.getID_Vuelo());
        System.out.println("Origen: " + this.ticketVuelo.getOrigen());
        System.out.println("Destino: " + this.ticketVuelo.getDestino());
        System.out.println("Fecha: " + formatoFecha.format(this.ticketVuelo.getFecha_Vuelo()));
        System.out.println("Hora de despegue: " + formatoHora.format(this.ticketVuelo.getHora_Despegue()));
        System.out.println("Hora de llegada: " + formatoHora.format(this.ticketVuelo.getHora_Arrivo()));
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
        for (int i = 0; i < asientosDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + asientosDisponibles.get(i).getNum_asiento());
        }

        // Seleccionar un asiento
        System.out.print("\nSeleccione un numero de asiento: ");
        int seleccion;

        try {
            seleccion = Integer.parseInt(scanner.nextLine());
            if (seleccion < 1 || seleccion > asientosDisponibles.size()) {
                System.out.println("Seleccion invalida.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un numero.");
            return false;
        }

        Asientos asientoSeleccionado = asientosDisponibles.get(seleccion - 1);
        asientoSeleccionado.setEstado(false);

        vueloSeleccionado.setAsientos_disponibles(vueloSeleccionado.getAsientos_disponibles() - 1);

        // Guarda la información del ticket
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
}