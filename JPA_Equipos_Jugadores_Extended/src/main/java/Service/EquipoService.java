package Service;

import DAO.EquipoDAO;
import DTO.Equipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoService {
    EquipoDAO equipoDAO;
    List<Equipo> equiposEnMemoria = new ArrayList<>();

    public EquipoService(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
        sincronizar();
    }
    public void insertEquipo(Equipo equipo) {
        equipoDAO.insertarEquipo(equipo);
        sincronizar();
    }
    public void actualizarEquipo(Equipo equipo) {
        equipoDAO.actualizarEquipo(equipo);
        sincronizar();
    }
    public void eliminarEquipo(int id) {
        equipoDAO.eliminarEquipo(id);
        sincronizar();
    }
    public Equipo buscarEquipo(int id) {
        return equipoDAO.buscarEquipo(id);
    }
    public List<Equipo> getEquiposEnMemoria() {
        return equiposEnMemoria;
    }
    public List<Equipo> getEquiposEnMemoriaPorNombre() {
        return equipoDAO.listarEquiposPorNombre();
    }
    public void sincronizar(){
        equiposEnMemoria = equipoDAO.listarEquipos();
    }
}
