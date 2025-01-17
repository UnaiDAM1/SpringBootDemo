package Paquete.Service;

import Paquete.Clases.Libro;
import Paquete.Clases.Usuario;
import Paquete.DAOS.DAOLibro;

import java.util.ArrayList;
import java.util.List;

public class ServiceLibro {
    private DAOLibro daoLibro;
    private List<Libro> listaLibrosMemoria;

    //Constructor
    public ServiceLibro(DAOLibro daoLibro) {
        this.daoLibro = daoLibro;
        this.listaLibrosMemoria = new ArrayList();
        //Para que cargue al iniciar el servicio
        cargarLibrosMemoria();
    }


    //Cargar de la base de datos a la memoria
    public void cargarLibrosMemoria() {
        this.listaLibrosMemoria = daoLibro.readAllLibros();
    }

    //Obtener todos
    public List<Libro> getListaLibrosMemoria() {
        return listaLibrosMemoria;
    }
    //Obtener uno por isbn
    public Libro getLibroMemoria(String isbn) {
        for (Libro libro : listaLibrosMemoria) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }
    //Agregar
    public void insertLibroMemoria(Libro libro) {
        listaLibrosMemoria.add(libro);
        daoLibro.updateLibro(libro);
    }

    //Actualizar
    public void updateLibroMemoria(Libro libro) {
        for (int i = 0; i < listaLibrosMemoria.size(); i++) {
            if (listaLibrosMemoria.get(i).getIsbn() == libro.getIsbn()) {
                listaLibrosMemoria.set(i, libro);
                break;
            }
        }
        daoLibro.updateLibro(libro);
    }

    //Eliminar
    public void deleteLibroMemoria(String isnb) {
        listaLibrosMemoria.remove(isnb);
        daoLibro.deleteLibro(isnb);
    }
}
