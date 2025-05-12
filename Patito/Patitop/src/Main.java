import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vuelos> listaVuelos = new ArrayList<>();
        ArrayList<Pasajeros> listaPasajeros = new ArrayList<>();
        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        ArrayList<Asientos> listaAsientos = new ArrayList<>();

        // Crear un administrador por defecto
        Empleados admin = new Empleados(0, "Admin", "admin123", "admin@correo.com");
        listaEmpleados.add(admin);

        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTION DE VUELOS ===");
            System.out.println("1. Iniciar sesion como administrador");
            System.out.println("2. Iniciar sesion como empleado");
            System.out.println("3. Registrarse como pasajero");
            System.out.println("4. Iniciar sesion como pasajero");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Login de administrador
                    System.out.print("Ingrese su contraseña de administrador: ");
                    String adminPassword = scanner.nextLine();
                    if (adminPassword.equals(admin.getContraseña())) {
                        menuAdmin(scanner, listaEmpleados);
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                    break;

                case 2:
                    // Login de empleado
                    System.out.print("Ingrese su correo: ");
                    String correoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contraseñaEmpleado = scanner.nextLine();
                    Empleados empleado = buscarEmpleadoPorCredenciales(listaEmpleados, correoEmpleado,
                            contraseñaEmpleado);
                    if (empleado != null) {
                        menuEmpleado(scanner, listaVuelos, listaPasajeros, listaAsientos, empleado);
                    } else {
                        System.out.println("Credenciales incorrectas.");
                    }
                    break;

                case 3:
                    // Registrar un nuevo pasajero
                    System.out.println("\n=== REGISTRO DE PASAJERO ===");
                    System.out.print("Ingrese su nombre: ");
                    String nombrePasajero = scanner.nextLine();
                    System.out.print("Ingrese su correo: ");
                    String correoPasajeroRegistro = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contraseñaPasajeroRegistro = scanner.nextLine();

                    // Crear un nuevo pasajero y agregarlo a la lista
                    Pasajeros nuevoPasajero = new Pasajeros(listaPasajeros.size() + 1, nombrePasajero,
                            contraseñaPasajeroRegistro, correoPasajeroRegistro);
                    listaPasajeros.add(nuevoPasajero);
                    System.out.println("Pasajero registrado exitosamente con ID: " + nuevoPasajero.getID_pasajero());
                    break;

                case 4:
                    // Login de pasajero
                    System.out.print("Ingrese su correo: ");
                    String correoPasajero = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contraseñaPasajero = scanner.nextLine();
                    Pasajeros pasajero = buscarPasajeroPorCredenciales(listaPasajeros, correoPasajero,
                            contraseñaPasajero);
                    if (pasajero != null) {
                        menuPasajero(scanner, listaVuelos, listaAsientos, pasajero);
                    } else {
                        System.out.println("Credenciales incorrectas.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no valida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void menuAdmin(Scanner scanner, ArrayList<Empleados> listaEmpleados) {
        System.out.println("\n=== MENU ADMINISTRADOR ===");
        System.out.print("Ingrese el nombre del nuevo empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el correo del nuevo empleado: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese la contraseña del nuevo empleado: ");
        String contraseña = scanner.nextLine();

        Empleados nuevoEmpleado = new Empleados(listaEmpleados.size() + 1, nombre, contraseña, correo);
        listaEmpleados.add(nuevoEmpleado);
        System.out.println("Empleado agregado exitosamente.");
    }

    private static void menuEmpleado(Scanner scanner, ArrayList<Vuelos> listaVuelos,
            ArrayList<Pasajeros> listaPasajeros, ArrayList<Asientos> listaAsientos, Empleados empleado) {
        int opcion;
        do {
            System.out.println("\n=== MENU EMPLEADO ===");
            System.out.println("1. Dar de alta un vuelo");
            System.out.println("2. Dar de baja un vuelo");
            System.out.println("3. Listar todos los pasajeros");
            System.out.println("4. Dar de alta un pasajero a un vuelo");
            System.out.println("5. Dar de baja un pasajero de un vuelo");
            System.out.println("6. Ver vuelos disponibles");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Dar de alta un vuelo
                    try {
                        System.out.print("Origen: ");
                        String origen = scanner.nextLine();
                        System.out.print("Destino: ");
                        String destino = scanner.nextLine();
                        System.out.print("Asientos disponibles: ");
                        int asientosDisponibles = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Fecha del vuelo (dd/MM/yyyy): ");
                        String fechaVueloStr = scanner.nextLine();

                        Date fechaVuelo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVueloStr);

                        // Usar null para los campos de hora
                        Vuelos nuevoVuelo = empleado.darDeAltaVuelo(asientosDisponibles, empleado.getID_Empleado(),
                                origen, destino, fechaVuelo, listaAsientos);
                        listaVuelos.add(nuevoVuelo);
                        System.out.println("Vuelo registrado exitosamente.");
                    } catch (ParseException e) {
                        System.out.println("Error al ingresar las fechas. Intente de nuevo.");
                    }
                    break;

                case 2:
                    // Dar de baja un vuelo
                    System.out.print("Ingrese el ID del vuelo a dar de baja: ");
                    int idVuelo = scanner.nextInt();
                    scanner.nextLine();
                    Vuelos vueloADarBaja = buscarVueloPorID(listaVuelos, idVuelo);
                    if (vueloADarBaja != null) { // Da de baja el vuelo, por lo tanto el ticket
                        empleado.darDeBajaVuelo(idVuelo, vueloADarBaja, listaPasajeros);
                    } else {
                        System.out.println("Vuelo no encontrado.");
                    }
                    break;

                case 3:
                    // Listar todos los pasajeros
                    listarTodosPasajeros(listaPasajeros);
                    break;

                case 4:
                    // Dar de alta un pasajero a un vuelo
                    System.out.print("Ingrese el ID del pasajero: ");
                    int idPasajeroAlta = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el ID del vuelo: ");
                    int idVueloAlta = scanner.nextInt();
                    scanner.nextLine();

                    Pasajeros pasajeroAlta = buscarPasajeroPorID(listaPasajeros, idPasajeroAlta);
                    Vuelos vueloAlta = buscarVueloPorID(listaVuelos, idVueloAlta);

                    if (empleado.darDeAltaUsuarioAVuelo(pasajeroAlta, vueloAlta, listaAsientos)) {
                        System.out.println("Pasajero agregado al vuelo exitosamente.");
                    }
                    break;

                case 5:
                    // Dar de baja un pasajero de un vuelo
                    System.out.print("Ingrese el ID del pasajero: ");
                    int idPasajeroBaja = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el ID del vuelo: ");
                    int idVueloBaja = scanner.nextInt();
                    scanner.nextLine();

                    Pasajeros pasajeroBaja = buscarPasajeroPorID(listaPasajeros, idPasajeroBaja);
                    Vuelos vueloBaja = buscarVueloPorID(listaVuelos, idVueloBaja);

                    if (empleado.darDeBajaUsuarioDeVuelo(pasajeroBaja, vueloBaja, listaAsientos)) {
                        System.out.println("Pasajero dado de baja del vuelo exitosamente.");
                    }
                    break;

                case 6:
                    // Ver vuelos disponibles
                    empleado.buscarVuelosActivos(listaVuelos);
                    break;

                case 7:
                    System.out.println("Saliendo del menu empleado...");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private static void listarTodosPasajeros(ArrayList<Pasajeros> listaPasajeros) {
        if (listaPasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados en el sistema.");
            return;
        }

        System.out.println("\n=== LISTA DE PASAJEROS ===");
        System.out.println("ID\tNombre\t\tCorreo");
        System.out.println("--------------------------------------------");

        for (Pasajeros pasajero : listaPasajeros) {
            System.out.println(pasajero.getID_pasajero() + "\t" +
                    pasajero.getNombre() + "\t\t" +
                    pasajero.getCorreo());
        }
    }

    private static void menuPasajero(Scanner scanner, ArrayList<Vuelos> listaVuelos, ArrayList<Asientos> listaAsientos,
            Pasajeros pasajero) {
        int opcion;
        do {
            System.out.println("\n=== MENU PASAJERO ===");
            System.out.println("1. Buscar vuelos");
            System.out.println("2. Reservar vuelo");
            System.out.println("3. Ver ticket reservado");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    pasajero.buscarVuelo(listaVuelos);
                    break;

                case 2:
                    pasajero.reservarVuelo(listaVuelos, listaAsientos);
                    break;

                case 3:
                    pasajero.verTicket();
                    break;

                case 4:
                    pasajero.cancelarReserva(listaAsientos);
                    break;

                case 5:
                    System.out.println("Saliendo del menu pasajero...");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private static Empleados buscarEmpleadoPorCredenciales(ArrayList<Empleados> listaEmpleados, String correo,
            String contraseña) {
        for (Empleados empleado : listaEmpleados) {
            if (empleado.getCorreo().equalsIgnoreCase(correo) && empleado.getContraseña().equals(contraseña)) {
                return empleado;
            }
        }
        return null;
    }

    private static Pasajeros buscarPasajeroPorCredenciales(ArrayList<Pasajeros> listaPasajeros, String correo,
            String contraseña) {
        for (Pasajeros pasajero : listaPasajeros) {
            if (pasajero.getCorreo().equalsIgnoreCase(correo) && pasajero.getContraseña().equals(contraseña)) {
                return pasajero;
            }
        }
        return null;
    }

    private static Pasajeros buscarPasajeroPorID(ArrayList<Pasajeros> listaPasajeros, int id) {
        for (Pasajeros pasajero : listaPasajeros) {
            if (pasajero.getID_pasajero() == id) {
                return pasajero;
            }
        }
        return null;
    }

    private static Vuelos buscarVueloPorID(ArrayList<Vuelos> listaVuelos, int id) {
        for (Vuelos vuelo : listaVuelos) {
            if (vuelo.getID_Vuelo() == id) {
                return vuelo;
            }
        }
        return null;
    }
}
