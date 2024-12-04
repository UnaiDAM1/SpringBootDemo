package DAO;

import DTO.Equipo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EquipoDAOImpl implements EquipoDAO {

    EntityManager em;

    public EquipoDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarEquipo(Equipo equipo) {
        em.getTransaction().begin();
        em.persist(equipo);
        em.getTransaction().commit();
    }

    @Override
    public void actualizarEquipo(Equipo equipo) {
        em.getTransaction().begin();
        em.merge(equipo);
        em.getTransaction().commit();
    }

    @Override
    public void eliminarEquipo(int id) {
        em.getTransaction().begin();
        if (em.find(Equipo.class, id) != null) {
            em.remove(em.find(Equipo.class, id));
        }
        em.getTransaction().commit();
    }

    @Override
    public Equipo buscarEquipo(int id) {
        return em.find(Equipo.class, id);
    }

    @Override
    public List<Equipo> listarEquipos() {
        TypedQuery<Equipo> query = em.createQuery("SELECT e FROM Equipo e", Equipo.class);
        return query.getResultList();
    }

    @Override
    public List<Equipo> listarEquiposPorNombre() {
        TypedQuery<Equipo> query = em.createQuery("SELECT e FROM Equipo e ORDER BY e.nombre", Equipo.class);
        return query.getResultList();
    }
}
