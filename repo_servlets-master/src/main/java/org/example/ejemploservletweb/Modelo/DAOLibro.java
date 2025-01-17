package org.example.ejemploservletweb.Modelo;

import jakarta.persistence.*;

import java.util.List;

public class DAOLibro {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad-biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public DAOLibro() {
    }

    public void insertLibro(Libro libro) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(libro);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void updateLibro(Libro libro) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }


    }

    public void deleteLibro(String isbn) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            em.getTransaction().begin();
            Libro lib = em.find(Libro.class, isbn);
            if (lib != null) {
                em.remove(lib);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Libro> getLibros() {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l", Libro.class);
        return query.getResultList();
    }

    public Libro getLibroPorISBN(String isbn) {
        return em.find(Libro.class, isbn);
    }
    public boolean libroExiste(String isbn) {
        if (em.find(Libro.class, isbn) != null) {
            return true;
        }else {
            return false;
        }
    }
}
