package DTO;

public class AuditoriaDTO {
    int id;
    String nombre;
    String password;

    public AuditoriaDTO(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Auditoria: " +
                "\nID = " + id +
                ", Nombre = '" + nombre + '\'' +
                ", Password = '" + password;
    }
}
