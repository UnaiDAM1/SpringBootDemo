package DAO;

import Modelos.Equipo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EquipoDAOImpl implements EquipoDAO {
    private EntityManager em;

    public EquipoDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Equipo equipo) {
        em.getTransaction().begin();
        em.persist(equipo);
        em.getTransaction().commit();
    }

    @Override
    public void actualizar(Equipo equipo) {
        em.getTransaction().begin();
        em.merge(equipo);
        em.getTransaction().commit();
    }

    @Override
    public void borrar(Integer id) {
        em.getTransaction().begin();
        Equipo equipo = em.find(Equipo.class, id);
        if (equipo != null) {
            em.remove(equipo);
        }
        em.getTransaction().commit();
    }

    @Override
    public Equipo leer(Integer id) {
        return em.find(Equipo.class, id);
    }

    @Override
    public List<Equipo> leerTodos() {
        TypedQuery<Equipo> query = em.createQuery("SELECT e FROM Equipo e", Equipo.class);
        return query.getResultList();
    }

    @Override
    public List<Equipo> ordenarPorNombre() {
        TypedQuery<Equipo> query = em.createQuery("SELECT e FROM Equipo e ORDER BY e.nombre", Equipo.class);
        return query.getResultList();
    }
}