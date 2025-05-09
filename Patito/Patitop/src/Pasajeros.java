import java.util.ArrayList;
import java.util.Scanner;

public class Pasajeros {
    private int ID_pasajero;
    private String nombre;
    private String contraseña;
    private String correo;
    private boolean rol; // true = admin, false = pasajero
    private Vuelos ticketReservado; // Ticket reservado por el pasajero

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
            if (vuelo.getOrigen().equalsIgnoreCase(origen) && vuelo.getDestino().equalsIgnoreCase(destino) && vuelo.isEstado()) {
                vuelo.mostrarInformacion();
            }
        }
    }

    public void verTicket() {
        if (ticketReservado != null) {
            System.out.println("Información de su ticket reservado:");
            ticketReservado.mostrarInformacion();
        } else {
            System.out.println("No tiene ningún ticket reservado.");
        }
    }

    public void reservarVuelo(ArrayList<Vuelos> vuelos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del vuelo que desea reservar:");
        int idVuelo = scanner.nextInt();

        for (Vuelos vuelo : vuelos) {
            if (vuelo.getID_Vuelo() == idVuelo && vuelo.isEstado() && vuelo.getAsientos_disponibles() > 0) {
                vuelo.setAsientos_disponibles(vuelo.getAsientos_disponibles() - 1);
                this.ticketReservado = vuelo;
                System.out.println("Vuelo reservado exitosamente.");
                return;
            }
        }
        System.out.println("No se pudo reservar el vuelo. Verifique el ID o la disponibilidad.");
    }
}