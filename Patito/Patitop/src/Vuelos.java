import java.util.Date;

public class Vuelos {
    // Atributos
    private int ID_Vuelo;
    private int Asientos_disponibles;
    private int ID_empleado;
    private String Origen;
    private String Destino;
    private Date Hora_Despegue;
    private Date Hora_Arrivo;
    private Date Fecha_Vuelo;
    private boolean Estado; // true = activo, false = inactivo

    // Constructor
    public Vuelos(int ID_Vuelo, int Asientos_disponibles, int ID_empleado, String Origen, String Destino,
            Date Hora_Despegue, Date Hora_Arrivo, Date Fecha_Vuelo, boolean Estado) {
        this.ID_Vuelo = ID_Vuelo;
        this.Asientos_disponibles = Asientos_disponibles;
        this.ID_empleado = ID_empleado;
        this.Origen = Origen;
        this.Destino = Destino;
        this.Hora_Despegue = Hora_Despegue;
        this.Hora_Arrivo = Hora_Arrivo;
        this.Fecha_Vuelo = Fecha_Vuelo;
        this.Estado = Estado;
    }

    // Getters y Setters
    public int getID_Vuelo() {
        return ID_Vuelo;
    }

    public void setID_Vuelo(int ID_Vuelo) {
        this.ID_Vuelo = ID_Vuelo;
    }

    public int getAsientos_disponibles() {
        return Asientos_disponibles;
    }

    public void setAsientos_disponibles(int Asientos_disponibles) {
        this.Asientos_disponibles = Asientos_disponibles;
    }

    public int getID_empleado() {
        return ID_empleado;
    }

    public void setID_empleado(int ID_empleado) {
        this.ID_empleado = ID_empleado;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public Date getHora_Despegue() {
        return Hora_Despegue;
    }

    public void setHora_Despegue(Date Hora_Despegue) {
        this.Hora_Despegue = Hora_Despegue;
    }

    public Date getHora_Arrivo() {
        return Hora_Arrivo;
    }

    public void setHora_Arrivo(Date Hora_Arrivo) {
        this.Hora_Arrivo = Hora_Arrivo;
    }

    public Date getFecha_Vuelo() {
        return Fecha_Vuelo;
    }

    public void setFecha_Vuelo(Date Fecha_Vuelo) {
        this.Fecha_Vuelo = Fecha_Vuelo;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    // Método para mostrar información del vuelo

    public void mostrarInformacion() {
        System.out.println("ID Vuelo: " + ID_Vuelo);
        System.out.println("Asientos disponibles: " + Asientos_disponibles);
        System.out.println("ID Empleado: " + ID_empleado);
        System.out.println("Origen: " + Origen);
        System.out.println("Destino: " + Destino);
        System.out.println("Fecha Vuelo: " + Fecha_Vuelo);
        System.out.println("Estado: " + (Estado ? "Activo" : "Inactivo"));
    }

}