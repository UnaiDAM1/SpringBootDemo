package Modelos;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private Set<Empleado> empleados = new LinkedHashSet<>();

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

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

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados +
                "'}\n'";
    }
}