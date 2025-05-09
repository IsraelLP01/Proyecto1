public  class Empleados{
    // Atributos
    private int ID_Empleado;
    private String nombre;
    private String contraseña;
    private String correo;
    private boolean rol; // El empleado siempre será 1 en el boooleano, ya que es el rol de empleado.
    
    // Constructor
    public Empleados(int ID_Empleado, String nombre, String contraseña, String correo, boolean rol) {
        this.ID_Empleado = ID_Empleado;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rol = rol;
    }
    // Getters y Setters
    public int getID_Empleado() { return ID_Empleado;}
    public void setID_Empleado(int ID_Empleado) {this.ID_Empleado = ID_Empleado;}

    public String getNombre() { return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getContraseña() { return contraseña;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}

    public String getCorreo() { return correo;}
    public void setCorreo(String correo) {this.correo = correo;}

    public boolean isRol() { return rol;}
    public void setRol(boolean rol) {this.rol = rol;}

}