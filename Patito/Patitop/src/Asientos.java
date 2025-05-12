public class Asientos {

    private int ID_Asiento;
    private String num_asiento;
    private boolean Estado;
    private int ID_Vuelo;

    public Asientos(int ID_Asiento, String num_asiento, boolean Estado, int ID_Vuelo) {
        this.ID_Asiento = ID_Asiento;
        this.num_asiento = num_asiento;
        this.Estado = Estado;
        this.ID_Vuelo = ID_Vuelo;
    }

    public int getID_Asiento() {
        return ID_Asiento;
    }

    public void setID_Asiento(int ID_Asiento) {
        this.ID_Asiento = ID_Asiento;
    }

    public String getNum_asiento() {
        return num_asiento;
    }

    public void setNum_asiento(String num_asiento) {
        this.num_asiento = num_asiento;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public int getID_Vuelo() {
        return ID_Vuelo;
    }

    public void setID_Vuelo(int ID_Vuelo) {
        this.ID_Vuelo = ID_Vuelo;
    }

    public String toString() {
        return "Asiento [ID:" + ID_Asiento + ", numero:" + num_asiento +
                ", disponible:" + Estado + ", vuelo:" + ID_Vuelo + "]";
    }
}