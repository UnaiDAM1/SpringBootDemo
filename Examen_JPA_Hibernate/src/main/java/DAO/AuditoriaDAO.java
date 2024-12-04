package DAO;

import DTO.Auditoria;

import java.util.List;

public interface AuditoriaDAO {
    public void crearAuditoria(Auditoria auditoria);
    public void actualizarAuditoria(Auditoria auditoria);
    public void eliminarAuditoria(int id);
    public List<Auditoria> listarAuditorias();
}
