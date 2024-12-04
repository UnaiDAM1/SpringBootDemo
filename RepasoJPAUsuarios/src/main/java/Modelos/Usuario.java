package Modelos;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "dni", nullable = false, length = 20)
    private String dni;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

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
        if (validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre invalido");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (validarDNI(dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("DNI invalido");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email invalido");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (validarEdad(fechaNacimiento)) {
            this.fechaNacimiento = fechaNacimiento;
        } else {
            throw new IllegalArgumentException("Fecha nacimiento invalida");
        }
    }

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String dni, String email, String telefono, LocalDate fechaNacimiento) {
        this.id = id;
        if (validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre invalido");
        }

        if (validarDNI(dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("DNI invalido");
        }
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email invalido");
        }
        this.telefono = telefono;
        if (validarEdad(fechaNacimiento)) {
            this.fechaNacimiento = fechaNacimiento;
        } else {
            throw new IllegalArgumentException("Fecha nacimiento invalida");
        }
    }
    public boolean validarDNI(String dni){
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (dni.length() != 9){
            return false;
        } else{
            if (dni.substring(0, 1).equals("X")){
                dni = "0" + dni.substring(1);
            } else if (dni.substring(0, 1).equals("Y")) {
                dni = "1" + dni.substring(1);
            } else if (dni.substring(0, 1).equals("Z")) {
                dni = "2" + dni.substring(1);
            }
            int numero = Integer.parseInt(dni.substring(0, 8));
            if (letras[numero%23].equals(dni.substring(8))){
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean validarEdad(LocalDate fechaNacimiento){
        if (fechaNacimiento.getYear() > LocalDate.now().getYear() - 50) {
            return true;
        } else{
            return false;
        }
    }
    public boolean validarNombre(String nombre){
        if (nombre != null && nombre.length() > 0 && Character.isUpperCase(nombre.charAt(0))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarEmail(String email){
        if (email.contains("@")){
            if (email.substring(email.indexOf("@")).equals("@gmail.com")){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                " ID = " + id +
                "\nNombre = '" + nombre + '\'' +
                "\nDNI = '" + dni + '\'' +
                "\nemail = '" + email + '\'' +
                "\nTelefono = '" + telefono + '\'' +
                "\nFecha Nacimiento = " + fechaNacimiento +
                '}';
    }
}