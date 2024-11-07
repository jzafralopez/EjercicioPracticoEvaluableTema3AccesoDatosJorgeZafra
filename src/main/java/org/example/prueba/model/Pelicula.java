package org.example.prueba.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @OneToMany (mappedBy = "pelicula", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Opinion> peliculas = new ArrayList<>();

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", peliculas=" + peliculas +
                '}';
    }
}