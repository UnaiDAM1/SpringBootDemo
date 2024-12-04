package DAO;

import DTO.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    EntityManager em;
    public UsuarioDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void actualizar(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void eliminar(String nickname) {
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, nickname);
        if (usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<Usuario> leerUsuarios() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
}
