package Paquete.Clases;

import Paquete.Validaciones;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(Validaciones.ISBN13Valido(isbn)) {
            this.isbn = isbn;
        } else {
            throw new IllegalArgumentException("ISBN no es valido");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    //Constructor
    public Libro(String isbn, String titulo, String autor) {
        if(Validaciones.ISBN13Valido(isbn)) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
        } else {
            throw new IllegalArgumentException("ISBN no es valido");
        }
    }

    public Libro() {
    }

    //ToString

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}