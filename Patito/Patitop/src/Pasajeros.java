import java.util.Scanner;

public class Pasajeros {
    private int ID_pasajero;
    private String nombre;
    private String contraseña;
    private String correo;
    private boolean rol; // true = admin, false = pasajero

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
    public void buscarVuelo() {
        // Implementar lógica para buscar vuelos


        System.out.println("Buscando vuelos...");
    }
    public void verTicket() {

        
        System.out.println("Mostrando ticket...");
    }
    public void reservarVuelo() {
        // Implementar lógica para reservar vuelo
        System.out.println("Reservando vuelo...");
    }

    
}