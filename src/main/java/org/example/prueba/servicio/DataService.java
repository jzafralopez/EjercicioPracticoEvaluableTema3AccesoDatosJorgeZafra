package org.example.prueba.servicio;

import org.example.prueba.model.Pelicula;
import org.example.prueba.model.Opinion;
import org.example.prueba.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;


public class DataService {
    private SessionFactory sessionFactory;

    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /*** Este metodo es el de la historia de usuario 1 ***/
    public static void guardarPelicula(Pelicula nuevaPelicula) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(nuevaPelicula);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Opinion> obtenerOpinionesPorUsuario(String usuarioEmail) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Opinion> opiniones = session.createQuery(
                            "from Opinion o where o.usuario = '" + usuarioEmail + "'", Opinion.class).list();
            return opiniones;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


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


    /*** Este metodo es el de la historia de usuario 4 ***/
    public static List<Pelicula> buscarPorBajaNota(BigDecimal puntuacionMaxima) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct p from Pelicula p join p.peliculas o where o.puntuacion <= :puntuacionMaxima", Pelicula.class)
                    .setParameter("puntuacionMaxima", puntuacionMaxima)
                    .list();
        }
    }
}
