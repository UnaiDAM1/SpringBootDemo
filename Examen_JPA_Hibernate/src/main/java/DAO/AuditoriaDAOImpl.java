package DAO;

import DTO.Auditoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AuditoriaDAOImpl implements AuditoriaDAO {

    EntityManager em;
    public AuditoriaDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void crearAuditoria(Auditoria auditoria) {
        em.getTransaction().begin();
        em.persist(auditoria);
        em.getTransaction().commit();
    }

    @Override
    public void actualizarAuditoria(Auditoria auditoria) {
        em.getTransaction().begin();
        em.merge(auditoria);
        em.getTransaction().commit();
    }

    @Override
    public void eliminarAuditoria(int id) {
        em.getTransaction().begin();
        Auditoria auditoria = em.find(Auditoria.class, id);
        if (auditoria != null) {
            em.remove(auditoria);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<Auditoria> listarAuditorias() {
        TypedQuery<Auditoria> query = em.createQuery("SELECT a FROM Auditoria a", Auditoria.class);
        return query.getResultList();
    }
}
