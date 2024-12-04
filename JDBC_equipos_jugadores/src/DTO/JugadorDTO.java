package DTO;

public class JugadorDTO {
    int id;
    String nombre;
    float estatura;
    float peso;
    int idEquipos;

    public JugadorDTO(String nombre, float estatura, float peso, int idEquipos) {
        this.nombre = nombre;
        this.estatura = estatura;
        this.peso = peso;
        this.idEquipos = idEquipos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getEstatura() {
        return estatura;
    }

    public float getPeso() {
        return peso;
    }

    public int getIdEquipos() {
        return idEquipos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setIdEquipos(int idEquipos) {
        this.idEquipos = idEquipos;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + ", ID = " + id + ", Estatura = " + estatura + ", Peso=" + peso + ", ID Equipo = " + idEquipos;
    }
}