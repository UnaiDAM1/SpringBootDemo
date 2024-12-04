package Service;

import DAO.AuditoriaDAO;
import DTO.Auditoria;

import java.util.ArrayList;
import java.util.List;

public class AuditoriaService {
    private AuditoriaDAO auditoriaDAO;
    List<Auditoria> auditoriasEnMemoria = new ArrayList<>();

    public AuditoriaService(AuditoriaDAO auditoriaDAO) {
        this.auditoriaDAO = auditoriaDAO;
    }
    public void sincronizar(){
        auditoriasEnMemoria = auditoriaDAO.listarAuditorias();
    }
    public void insertar(Auditoria auditoria){
        auditoriaDAO.crearAuditoria(auditoria);
        sincronizar();
    }
    public void update(Auditoria auditoria){
        auditoriaDAO.actualizarAuditoria(auditoria);
        sincronizar();
    }
    public void delete(Auditoria auditoria){
        auditoriasEnMemoria.remove(auditoria);
        sincronizar();
    }
    public List<Auditoria> listarAuditorias(){
        return auditoriasEnMemoria;
    }

}
