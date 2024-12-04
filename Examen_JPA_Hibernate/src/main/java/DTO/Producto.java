package DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @Column(name = "codigo", nullable = false, length = 100)
    private String codigo;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "precio", precision = 10)
    private Double precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "disponible")
    private Boolean disponible;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, Double precio, Integer stock, Boolean disponible) {
        if (validarCodigo(codigo, nombre, stock)){
            this.codigo = codigo;
        } else {
            System.out.println("Codigo invalido.");
        }
        this.nombre = nombre;
        if (validarDescripcion(descripcion)) {
            this.descripcion = descripcion;
        } else {
            System.out.println("Descripcion invalida.");
        }
        if (validarPrecio(precio)) {
            this.precio = precio;
        } else {
            System.out.println("El precio no es valido.");
        }
        if (validarStock(stock)) {
            this.stock = stock;
        } else {
            System.out.println("Stock invalido.");
        }

        this.disponible = disponible;
    }

    public boolean validarCodigo(String codigo, String nombre, int stock) {
        if (codigo.length() >= 6 && codigo.length() <= 8){
            if (codigo.substring(0, 3).toUpperCase().equals(nombre.substring(0, 3).toUpperCase())) {
                if (codigo.substring(0, 3).toUpperCase().equals(codigo.substring(0, 3))){
                    if (Integer.parseInt(codigo.substring(3, 5)) == stock) {
                        if (!codigo.substring(5).matches(".*[a-zA-Z].*")){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean validarPrecio(double precio){
        if (precio >= 0 && precio <= 1000){
            return true;
        } else {
            return false;
        }
    }
    public boolean validarDescripcion(String descripcion){
        if (descripcion.indexOf("droga") == 0){
            if (descripcion.indexOf("arma") == 0){
                return true;
            }
        }
        return false;
    }
    public boolean validarStock(int stock){
        if (stock < 0){
            return false;
        }
        else return true;
    }

    @Override
    public String toString() {
        return "Producto: " +
                "\nCodigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", disponible=" + disponible;
    }
}