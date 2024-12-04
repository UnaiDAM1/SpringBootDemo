package DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "jugador")
public class Jugador {
    @Id
    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "estatura", nullable = false)
    private Float estatura;

    @Column(name = "peso", nullable = false)
    private Float peso;

    @Column(name = "productividad", nullable = false)
    private Float productividad;

    @Column(name = "salario", nullable = false)
    private Float salario;

    @Column(name = "salario_base", nullable = false)
    private Float salarioBase = 1000f;

    @Column(name = "incentivo", nullable = false)
    private Integer incentivo = 1000;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idEquipo", nullable = false)
    private Equipo idEquipo;

    @Column(name = "n_goles", nullable = false)
    private Integer nGoles;

    @Column(name = "asistencias", nullable = false)
    private Integer asistencias;

    @Column(name = "n_partidos_jugados", nullable = false)
    private Integer nPartidosJugados;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getProductividad() {
        return productividad;
    }

    public void setProductividad(Float productividad) {
        this.productividad = productividad;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Integer getIncentivo() {
        return incentivo;
    }

    public void setIncentivo(Integer incentivo) {
        this.incentivo = incentivo;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getNGoles() {
        return nGoles;
    }

    public void setNGoles(Integer nGoles) {
        this.nGoles = nGoles;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public Integer getNPartidosJugados() {
        return nPartidosJugados;
    }

    public void setNPartidosJugados(Integer nPartidosJugados) {
        this.nPartidosJugados = nPartidosJugados;
    }

    public Jugador() {
    }

    public Jugador(String dni, String nombre, LocalDate fechaNacimiento, Float estatura, Float peso, Equipo idEquipo, Integer nGoles, Integer asistencias, Integer nPartidosJugados) {
        if (validarDNI(dni)){
            this.dni = dni;
        } else {
            System.out.println("DNI incorrecto");
        }
        this.nombre = nombre;
        if (validadFecha(fechaNacimiento)){
            this.fechaNacimiento = fechaNacimiento;
        } else {
            System.out.println("Fecha incorrecto");
        }
        if (validarEstatura(estatura)){
            this.estatura = estatura;
        } else {
            System.out.println("Estatura incorrecto");
        }
        if (validarPeso(peso)){
            this.peso = peso;
        } else {
            System.out.println("Peso incorrecto");
        }
        this.idEquipo = idEquipo;
        this.nGoles = nGoles;
        this.asistencias = asistencias;
        this.nPartidosJugados = nPartidosJugados;
        this.productividad = (0.8f*nGoles + 0.2f*asistencias)/nPartidosJugados;
        this.salario = salarioBase + incentivo*productividad;
    }

    public boolean validarDNI(String dni) {
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (dni.length() != 9) {
            return false;
        } else {
            if (dni.substring(0, 1).equals("X")){
                dni = "0" + dni.substring(1);
            } else if (dni.substring(0, 1).equals("Y")) {
                dni = "1" + dni.substring(1);
            } else if (dni.substring(0, 1).equals("Z")) {
                dni = "2" + dni.substring(1);
            }
            int numero = Integer.parseInt(dni.substring(0,8));
            if (letras[numero%23].equals(dni.substring(8))){
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean validadFecha(LocalDate fechaNacimiento){
        if (fechaNacimiento.isBefore(LocalDate.now().minusYears(16))){
            return true;
        } else{
            return false;
        }
    }
    public boolean validarPeso(Float peso){
        if (peso < 40 || peso > 100){
            return false;
        } else {
            return true;
        }
    }
    public boolean validarEstatura(Float estatura){
        if (estatura < 1.50 || estatura > 2.10){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Jugador: " +
                "\nDNI = '" + dni + '\'' +
                ", Nombre = '" + nombre + '\'' +
                ", Fecha Nacimiento = " + fechaNacimiento +
                ", Estatura = " + estatura +
                ", Peso = " + peso +
                ", Salario = " + salario +
                "\nEquipo: " + idEquipo +
                "\nEstadisticas: [Nº Goles = " + nGoles +
                ", Asistencias = " + asistencias +
                ", Nº Partidos Jugados=" + nPartidosJugados + "]";
    }
}