package MODELO;

public class cliente {

    private int id;
    private String nombre;
    private String identificacion;
    private String telefono;
    private String correo;

    public cliente() {

    }

    public cliente(int id, String nombre, String identificacion, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return """
               *****************************
               Id:          %s
               Nombre:      %s
               Cedula:      %s
               Telefono:    %s
               Correo:      %s  
               """.formatted(id, nombre, identificacion, telefono, correo);
    }
}