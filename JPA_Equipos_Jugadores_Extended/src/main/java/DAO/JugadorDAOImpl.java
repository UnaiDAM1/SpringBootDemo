package DAO;

import DTO.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {
    EntityManager em;

    public JugadorDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void insertarJugador(Jugador j) {
        em.getTransaction().begin();
        em.persist(j);
        em.getTransaction().commit();
    }
    public void actualizarJugador(Jugador j) {
        em.getTransaction().begin();
        em.merge(j);
        em.getTransaction().commit();
    }
    public void eliminarJugador(int id) {
        em.getTransaction().begin();
        if (em.contains(em.find(Jugador.class, id))) {
            em.remove(em.find(Jugador.class, id));
        }
    }
    public Jugador buscarJugador(int id) {
        return em.find(Jugador.class, id);
    }
    public List<Jugador> listarJugadores() {
        TypedQuery<Jugador> query = em.createQuery("select j from Jugador j", Jugador.class);
        return query.getResultList();
    }
    public List<Jugador> listarJugadoresPorNombre() {
        TypedQuery<Jugador> query = em.createQuery("select j from Jugador j order by j.nombre", Jugador.class);
        return query.getResultList();
    }
}
