package Paquete.Service;

import Paquete.Clases.Ejemplar;
import Paquete.DAOS.DAOEjemplar;
import java.util.ArrayList;
import java.util.List;

public class ServiceEjemplar {

    private DAOEjemplar daoEjemplar;
    private List<Ejemplar> listaEjemplaresMemoria;

    // Constructor
    public ServiceEjemplar(DAOEjemplar daoEjemplar) {
        this.daoEjemplar = daoEjemplar;
        this.listaEjemplaresMemoria = new ArrayList<>();
        cargarEjemplaresMemoria();
    }

    public void cargarEjemplaresMemoria() {
        this.listaEjemplaresMemoria = daoEjemplar.readAllEjemplares();
    }

    public List<Ejemplar> getListaEjemplaresMemoria() {
        return listaEjemplaresMemoria;
    }

    public Ejemplar getEjemplarMemoria(int id) {
        for (Ejemplar ejemplar : listaEjemplaresMemoria) {
            if (ejemplar.getId().equals(id)) {
                return ejemplar;
            }
        }
        return null;
    }

    public Ejemplar getEjemplarPorID(int id) {
        return daoEjemplar.readEjemplarPorID(id);
    }

    public void insertEjemplarMemoria(Ejemplar ejemplar) {
        listaEjemplaresMemoria.add(ejemplar); // Añadir a memoria
        daoEjemplar.insertEjemplar(ejemplar); // Persistir en base de datos
    }

    public void updateEjemplarMemoria(Ejemplar ejemplar) {
        // Buscar y actualizar el ejemplar en memoria
        for (int i = 0; i < listaEjemplaresMemoria.size(); i++) {
            if (listaEjemplaresMemoria.get(i).getId().equals(ejemplar.getId())) {
                listaEjemplaresMemoria.set(i, ejemplar);
                break;
            }
        }
        daoEjemplar.updateEjemplar(ejemplar);
    }

    public void deleteEjemplarMemoria(int id) {
        listaEjemplaresMemoria.removeIf(ejemplar -> ejemplar.getId().equals(id));
        Ejemplar ejemplar = daoEjemplar.readEjemplarPorID(id);
        daoEjemplar.deleteEjemplar(ejemplar);
    }

    public void actualizarEstadoEjemplar(int id, Ejemplar.EstadoEjemplar estadoActualizado) {
        for (Ejemplar ejemplar : listaEjemplaresMemoria) {
            if (ejemplar.getId().equals(id)) {
                ejemplar.setEstado(estadoActualizado);
                daoEjemplar.actualizarSoloEstado(id, estadoActualizado);
                break;
            }
        }
    }
}
