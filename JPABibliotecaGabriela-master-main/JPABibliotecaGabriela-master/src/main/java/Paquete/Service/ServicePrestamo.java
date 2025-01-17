package Paquete.Service;

import Paquete.Clases.Prestamo;
import Paquete.DAOS.DAOPrestamo;
import jakarta.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ServicePrestamo {

    private DAOPrestamo daoPrestamo;
    private List<Prestamo> listaPrestamosMemoria;

    // Constructor
    public ServicePrestamo(DAOPrestamo daoPrestamo) {
        this.daoPrestamo = daoPrestamo;
        this.listaPrestamosMemoria = new ArrayList<>();
        cargarPrestamosMemoria();
    }

    public void cargarPrestamosMemoria() {
        this.listaPrestamosMemoria = daoPrestamo.readAllPrestamos();
    }

    public List<Prestamo> getListaPrestamosMemoria() {
        return listaPrestamosMemoria;
    }

    public Prestamo getPrestamoMemoria(int id) {
        for (Prestamo prestamo : listaPrestamosMemoria) {
            if (prestamo.getId().equals(id)) {
                return prestamo;
            }
        }
        return null;
    }

    public Prestamo getPrestamoPorID(int id) {
        return daoPrestamo.readPrestamoPorID(id);
    }

    public void insertPrestamoMemoria(Prestamo prestamo) {
        listaPrestamosMemoria.add(prestamo);
        daoPrestamo.insertPrestamo(prestamo);
    }

    public void updatePrestamoMemoria(Prestamo prestamo) {
        for (int i = 0; i < listaPrestamosMemoria.size(); i++) {
            if (listaPrestamosMemoria.get(i).getId().equals(prestamo.getId())) {
                listaPrestamosMemoria.set(i, prestamo);
                break;
            }
        }
        daoPrestamo.updatePrestamo(prestamo); // Actualizar en base de datos
    }

    public void deletePrestamoMemoria(int id) {
        listaPrestamosMemoria.removeIf(prestamo -> prestamo.getId().equals(id));
        Prestamo prestamo = daoPrestamo.readPrestamoPorID(id);
        daoPrestamo.deletePrestamo(prestamo);
    }
}
