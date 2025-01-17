package Paquete.DAOS;

import Paquete.Clases.Usuario;
import jakarta.persistence.*;

import java.util.List;

public class DAOUsuario {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Paquete");

    //Obtener todos los usuarios
    public List<Usuario> readAllUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u");
            List<Usuario> listaUsuarios = q.getResultList();
            return listaUsuarios;
        } catch (NoResultException e) {
            System.out.println("No hay usuarios registrados");
            throw new NoResultException("No hay usuarios en la base de datos");
        } finally {
            em.close();
        }
    }
    //Obtener un usuario por id
    public Usuario readUsuarioPorID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
            q.setParameter("id", id);
            return (Usuario) q.getSingleResult();
        }catch (NoResultException e) {
            System.out.println("Usuario no encontrado");
            throw new NoResultException("No existe un usuario con el id " + id);
        }finally {
            em.close();
        }
    }
    //Crear un usuario
    public void insertUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }
    //Actualizar un usuario
    public void updateUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
    }
    //Borrar un usuario
    public void deleteUsuario(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        em.remove(usuario);
        em.getTransaction().commit();
        em.close();
    }
    //Cerrar Entity manager
    //Esto realmente no hace falta
    public static void cerrarEMF(){
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }
}
