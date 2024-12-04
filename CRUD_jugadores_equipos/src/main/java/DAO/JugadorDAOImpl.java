package DAO;

import Modelos.Jugador;
import jakarta.persistence.EntityManager;
import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {
    private EntityManager em;

    public JugadorDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Jugador jugador) {
        em.getTransaction().begin();
        em.persist(jugador);
        em.getTransaction().commit();
    }

    @Override
    public Jugador actualizar(Jugador jugador) {
        em.getTransaction().begin();
        Jugador actualizado = em.merge(jugador);
        em.getTransaction().commit();
        return actualizado;
    }

    @Override
    public void borrar(Integer id) {
        em.getTransaction().begin();
        Jugador jugador = em.find(Jugador.class, id);
        if (jugador != null) {
            em.remove(jugador);
        }
        em.getTransaction().commit();
    }

    @Override
    public Jugador leer(Integer id) {
        return em.find(Jugador.class, id);
    }

    @Override
    public List<Jugador> leerTodos() {
        return em.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();
    }
}

