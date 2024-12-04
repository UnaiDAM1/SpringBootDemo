package DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "estadio", nullable = false, length = 100)
    private String estadio;

    @Column(name = "titulos", nullable = false)
    private Integer titulos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Integer getTitulos() {
        return titulos;
    }

    public void setTitulos(Integer titulos) {
        this.titulos = titulos;
    }

    public Equipo() {
    }

    public Equipo(Integer id, String nombre, String estadio, Integer titulos) {
        this.id = id;
        this.titulos = titulos;
        if (validarEstadio(estadio)) {
            this.estadio = estadio;
        } else {
            System.out.println("Estadio incorrecto");
        }
        this.nombre = nombre;
    }

    public boolean validarEstadio(String estadio) {
        if (estadio.matches(".*[0-9].*")){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Equipo: " +
                "\nID=" + id +
                ", Nombre = '" + nombre + '\'' +
                ", Estadio = '" + estadio + '\'' +
                ", Titulos = " + titulos;
    }
}