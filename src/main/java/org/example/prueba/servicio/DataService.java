package org.example.prueba.servicio;

import org.example.prueba.model.Pelicula;
import org.example.prueba.model.Opinion;
import org.example.prueba.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio encargado de realizar operaciones de persistencia relacionadas con las películas y opiniones.
 */
public class DataService {
    private SessionFactory sessionFactory;

    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Guarda una nueva película en la base de datos. Si la película ya existe, la actualiza.
     * Utiliza la operación `merge` para sincronizar la entidad con la base de datos.
     */
    public static void guardarPelicula(Pelicula nuevaPelicula) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(nuevaPelicula);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todas las opiniones asociadas a un usuario específico por su correo electrónico.
     */
    public static List<Opinion> obtenerOpinionesPorUsuario(String usuarioEmail) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Opinion> opiniones = session.createQuery(
                    "from Opinion o where o.usuario = '" + usuarioEmail + "'", Opinion.class).list();
            return opiniones;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Añade una nueva opinión a una película existente en la base de datos.
     */
    public static void meterOpinion(Long idPelicula, Opinion opinion) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Pelicula pelicula = session.get(Pelicula.class, idPelicula);
            opinion.setPelicula(pelicula);
            session.persist(opinion);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca las películas que tienen opiniones con una puntuación igual o inferior a un valor dado.
     */
    public static List<Pelicula> buscarPorBajaNota(BigDecimal puntuacionMaxima) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct p from Pelicula p join p.peliculas o where o.puntuacion <= :puntuacionMaxima", Pelicula.class)
                    .setParameter("puntuacionMaxima", puntuacionMaxima)
                    .list();
        }
    }
}
