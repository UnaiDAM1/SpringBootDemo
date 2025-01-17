package Paquete.DAOS;

import Paquete.Clases.Ejemplar;
import jakarta.persistence.*;

import java.util.List;

public class DAOEjemplar {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Paquete");

    //Obtener todos los ejemplares
    public List<Ejemplar> readAllEjemplares() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM Ejemplar e");
            List<Ejemplar> listaEjemplares = q.getResultList();
            return listaEjemplares;
        } catch (NoResultException e) {
            System.out.println("No hay Ejemplares registrados");
            throw new NoResultException("No hay ejemplares en la base de datos");
        } finally {
            em.close();
        }
    }
    //Obtener un ejemplar por id
    public Ejemplar readEjemplarPorID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM Ejemplar e WHERE e.id = :id");
            q.setParameter("id", id);
            return (Ejemplar) q.getSingleResult();
        }catch (NoResultException e) {
            System.out.println("Ejemplar no encontrado");
            throw new NoResultException("No existe un ejemplar con el id " + id);
        }finally {
            em.close();
        }
    }

    //Obtener datos de préstamo y ejemplar por usuario
    public List<Ejemplar> readEjemplaresPorUsuarioId(int usuarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery(
                    "SELECT e FROM Prestamo p JOIN p.ejemplar e WHERE p.usuario.id = :usuarioId", Ejemplar.class
            );
            q.setParameter("usuarioId", usuarioId);
            return q.getResultList();
        } catch (NoResultException e) {
            throw new NoResultException("El usuario con ID " + usuarioId + " no tiene ejemplares prestados");
        } finally {
            em.close();
        }
    }


    //Crear
    public void insertEjemplar(Ejemplar ejemplar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ejemplar);
        em.getTransaction().commit();
        em.close();
    }
    //Actualizar
    public void updateEjemplar(Ejemplar ejemplar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(ejemplar);
        em.getTransaction().commit();
        em.close();
    }

    //Actualizar solo el estado del péstamo
    public void actualizarSoloEstado(int id, Ejemplar.EstadoEjemplar estadoActualizado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ejemplar ejemplar = em.find(Ejemplar.class, id);
            ejemplar.setEstado(estadoActualizado);
            em.merge(ejemplar);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            throw new NoResultException("No existe un ejemplar con el id " + id);
        } finally {
            em.close();
        }
    }

    //Borrar
    public void deleteEjemplar(Ejemplar ejemplar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(ejemplar);
        em.getTransaction().commit();
        em.close();
    }
    //Cerrar
    public static void cerrarEMF(){
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }
}
