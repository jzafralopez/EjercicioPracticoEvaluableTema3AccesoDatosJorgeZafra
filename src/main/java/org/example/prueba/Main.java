package org.example.prueba;

import org.example.prueba.model.Opinion;
import org.example.prueba.model.Pelicula;
import org.example.prueba.servicio.DataService;

import java.math.BigDecimal;
import java.util.List;

import static org.example.prueba.HibernateUtil.getSessionFactory;

public class Main {
    public static void main(String[] args) {

        /*** Comprobación de historia del usuario 1 ***/
        Pelicula peli = new Pelicula();
        peli.setTitulo("El señor de los anillos");

        DataService.guardarPelicula(peli);

        System.out.println(peli);

        /*** Comprobación de historia del usuario 2 ***/
        Opinion opinion = new Opinion();
        opinion.setPuntuacion(new BigDecimal("6.0"));
        opinion.setDescripcion("Ni fu ni fa");
        opinion.setUsuario("pepito@cesur.com");

        DataService.meterOpinion(1L, opinion);

        /*** Comprobacion de historia del usuario 3 ***/


                /*** Comprobación de historia del usuario 4 ***/
        System.out.println("\nPeliculas con puntuación menor o igual a 3.0 (NO hay):");
        BigDecimal puntuacionMaxima = new BigDecimal("3.0");
        System.out.println("\nPeliculas con puntuación menor o igual a 3.0 (NO hay):");
        List<Pelicula> peliculasBajaNota = DataService.buscarPorBajaNota(puntuacionMaxima);

        for (Pelicula pelicula : peliculasBajaNota) {
            System.out.println(pelicula);
        }

        getSessionFactory().close();
    }
}
