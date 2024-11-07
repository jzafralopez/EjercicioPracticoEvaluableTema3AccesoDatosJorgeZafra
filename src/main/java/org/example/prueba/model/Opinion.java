package org.example.prueba.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "opinion")
public class Opinion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;

    @Column(name = "puntuacion", nullable = false, precision = 3, scale = 1)
    private BigDecimal puntuacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", puntuacion=" + puntuacion + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}