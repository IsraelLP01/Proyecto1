import java.time.LocalDate;

public class Reserva {
    private int ID_Reserva;
    private LocalDate Fecha_Reserva;
    private int ID_Pasajero;
    private int ID_Asiento;

    public Reserva() {
    }

    public Reserva(int ID_Reserva, LocalDate Fecha_Reserva, int ID_Pasajero, int ID_Asiento) {
        this.ID_Reserva = ID_Reserva;
        this.Fecha_Reserva = Fecha_Reserva;
        this.ID_Pasajero = ID_Pasajero;
        this.ID_Asiento = ID_Asiento;
    }

    public int getID_Reserva() {
        return ID_Reserva;
    }

    public void setID_Reserva(int ID_Reserva) {
        this.ID_Reserva = ID_Reserva;
    }

    public LocalDate getFecha_Reserva() {
        return Fecha_Reserva;
    }

    public void setFecha_Reserva(LocalDate Fecha_Reserva) {
        this.Fecha_Reserva = Fecha_Reserva;
    }

    public int getID_Pasajero() {
        return ID_Pasajero;
    }

    public void setID_Pasajero(int ID_Pasajero) {
        this.ID_Pasajero = ID_Pasajero;
    }

    public int getID_Asiento() {
        return ID_Asiento;
    }

    public void setID_Asiento(int ID_Asiento) {
        this.ID_Asiento = ID_Asiento;
    }

    public String toString() {
        return "Reserva{" +
                "ID_Reserva=" + ID_Reserva +
                ", Fecha_Reserva=" + Fecha_Reserva +
                ", ID_Pasajero=" + ID_Pasajero +
                ", ID_Asiento=" + ID_Asiento +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reserva reserva = (Reserva) o;

        return ID_Reserva == reserva.ID_Reserva;
    }

    public int hashCode() {
        return ID_Reserva;
    }
}