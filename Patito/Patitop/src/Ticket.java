package Patito.Patitop;

import java.util.Date;
import java.util.Scanner;

public class Ticket{ 
    private int ticket;
    private Date fechaYHora_emision;
    private int ID_Asiento;
    private int ID_pasajero;

    public Ticket(int ticket, Date fechaYHora_emision, int ID_Asiento, int ID_pasajero) {
        this.ticket = ticket;
        this.fechaYHora_emision = fechaYHora_emision;
        this.ID_Asiento = ID_Asiento;
        this.ID_pasajero = ID_pasajero;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public Date getFechaYHora_emision() {
        return fechaYHora_emision;
    }

    public void setFechaYHora_emision(Date fechaYHora_emision) {
        this.fechaYHora_emision = fechaYHora_emision;
    }

    public int getID_Asiento() {
        return ID_Asiento;
    }

    public void setID_Asiento(int ID_Asiento) {
        this.ID_Asiento = ID_Asiento;
    }

    public int getID_pasajero() {
        return ID_pasajero;
    }

    public void setID_pasajero(int ID_pasajero) {
        this.ID_pasajero = ID_pasajero;
    }
}