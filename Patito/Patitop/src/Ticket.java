import java.time.LocalDateTime;

public class Ticket { 
    private static int contadorTickets = 1; 
    private int ticket;
    private LocalDateTime fechaYHora_emision;  
    private int ID_Asiento;
    private Pasajeros pasajero; 

    public Ticket(int ID_Asiento, Pasajeros pasajero) {
        this.ticket = contadorTickets++; 
        this.fechaYHora_emision = LocalDateTime.now(); 
        this.ID_Asiento = ID_Asiento;
        this.pasajero = pasajero;
    }

    public int getTicket() {
        return ticket;
    }

    public LocalDateTime getFechaYHora_emision() {
        return fechaYHora_emision;
    }
    
    public int getID_Asiento() {
        return ID_Asiento;
    }

    public void setID_Asiento(int ID_Asiento) {
        this.ID_Asiento = ID_Asiento;
    }

    public Pasajeros getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajeros pasajero) {
        this.pasajero = pasajero;
    }
}