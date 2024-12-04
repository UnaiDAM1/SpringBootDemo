package DTO;

public class EquipoDTO {
    int id;
    String nombre;
    String estadio;
    public EquipoDTO(String nombre, String estadio) {
        this.nombre = nombre;
        this.estadio = estadio;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEstadio() {
        return estadio;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
    @Override
    public String toString() {
        return "Equipo: " + nombre + ", Estadio=" + estadio + ", ID Equipo = " + id;
    }
}

