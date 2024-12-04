package Services;

import DAO.EquipoDAO;
import Modelos.Equipo;
import java.util.ArrayList;
import java.util.List;

public class EquipoServices {
    private EquipoDAO equipoDAO;
    private List<Equipo> equiposEnMemoria = new ArrayList<>();

    public EquipoServices(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
        sincronizarEquipos();
    }

    public void insertarEquipo(Equipo equipo) {
        equipoDAO.insertar(equipo);
        sincronizarEquipos();
    }

    public void actualizarEquipo(Equipo equipo) {
        equipoDAO.actualizar(equipo);
        sincronizarEquipos();
    }

    public void borrarEquipo(Integer id) {
        equipoDAO.borrar(id);
        sincronizarEquipos();
    }

    public List<Equipo> obtenerEquipos() {
        return equiposEnMemoria;
    }

    private void sincronizarEquipos() {
        equiposEnMemoria = equipoDAO.leerTodos();
    }
}

