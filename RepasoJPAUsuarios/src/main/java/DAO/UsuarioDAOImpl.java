package DAO;

import Modelos.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private EntityManager em;

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
        Usuario actualizado = em.merge(usuario);
        em.getTransaction().commit();
//        return actualizado;
    }

    @Override
    public void borrar(Integer id) {
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        if(usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
    }

    @Override
    public Usuario leer(Integer id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> leerTodos() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    @Override
    public List<Usuario> ordenarPorNombre() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.nombre", Usuario.class);
        return query.getResultList();
    }
}
