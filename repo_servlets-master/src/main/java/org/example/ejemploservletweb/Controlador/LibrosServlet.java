package org.example.ejemploservletweb.Controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ejemploservletweb.Modelo.Libro;
import org.example.ejemploservletweb.Modelo.DAOLibro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "librosServlet", value = "/libros-servlet")
public class LibrosServlet extends HttpServlet {

    private DAOLibro daolibro;

    @Override
    public void init() {
        daolibro = new DAOLibro();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("operaciones");
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            switch (operacion) {
                case "insert":
                    if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                        out.println("Todos los campos son obligatorios para añadir un libro.");
                    } else {
                        Libro nuevoLibro = new Libro(isbn, titulo, autor);
                        daolibro.insertLibro(nuevoLibro);
                        out.println("Libro añadido correctamente.");
                    }
                    break;
                case "update":
                    if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                        out.println("Todos los campos son obligatorios para actualizar un libro.");
                    } else {
                        if (daolibro.getLibroPorISBN(isbn) != null) {
                            Libro libroActualizado = daolibro.getLibroPorISBN(isbn);
                            libroActualizado.setTitulo(titulo);
                            libroActualizado.setAutor(autor);
                            daolibro.updateLibro(libroActualizado);
                            out.println("Libro actualizado correctamente.");
                        } else {
                            out.println("El libro con el ISBN proporcionado no existe.");
                        }
                    }
                    break;
                case "delete":
                    if (isbn.isEmpty()) {
                        out.println("El campo ISBN es obligatorio para eliminar un libro.");
                    } else {
                        if (daolibro.libroExiste(isbn)) {
                            daolibro.deleteLibro(isbn);
                            out.println("Libro eliminado correctamente.");
                        } else {
                            out.println("El libro con el ISBN proporcionado no existe.");
                        }
                    }
                    break;
                case "show":
                    if (isbn.isEmpty()) {
                        out.println("El campo ISBN es obligatorio para mostrar un libro.");
                    } else {
                        if (daolibro.getLibroPorISBN(isbn) != null) {
                            out.println(daolibro.getLibroPorISBN(isbn));
                        } else {
                            out.println("El libro con el ISBN proporcionado no existe.");
                        }
                    }
                    break;
                default:
                    out.println("Operación no reconocida.");
            }
        } catch (Exception e) {
            log("Error procesando la operación", e);
            out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        PrintWriter impresora = response.getWriter();
        ObjectMapper conversorJson = new ObjectMapper();
        conversorJson.registerModule(new JavaTimeModule());

        List<Libro> listaLibros = daolibro.getLibros();

        String jsonResponse = conversorJson.writeValueAsString(listaLibros);
        impresora.println(jsonResponse);
    }

    @Override
    public void destroy() {
    }
}
