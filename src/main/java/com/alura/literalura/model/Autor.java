package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreAutor;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;
    private Integer añoNacimientoAutor;
    private Integer añoFalleciAutor;

    public Autor() {}

    public Autor(RecordDatosAutores recordDatosAutores) {
        this.nombreAutor = recordDatosAutores.nombreAutor();
        this.añoNacimientoAutor = recordDatosAutores.añoNacimientoAutor();
        this.añoFalleciAutor = recordDatosAutores.añoFalleciAutor();
    }

    public Autor(String nombreAutor, Integer añoNacimientoAutor, Integer añoFalleciAutor) {
        this.nombreAutor = nombreAutor;
        this.añoNacimientoAutor = añoNacimientoAutor;
        this.añoFalleciAutor = añoFalleciAutor;
    }


    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", añoNacimientoAutor=" + añoNacimientoAutor +
                ", añoFalleciAutor=" + añoFalleciAutor +
                '}';
    }


    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Integer getAñoNacimientoAutor() {
        return añoNacimientoAutor;
    }

    public void setAñoNacimientoAutor(Integer añoNacimientoAutor) {
        this.añoNacimientoAutor = añoNacimientoAutor;
    }

    public Integer getAñoFalleciAutor() {
        return añoFalleciAutor;
    }

    public void setAñoFalleciAutor(Integer añoFalleciAutor) {
        this.añoFalleciAutor = añoFalleciAutor;
    }
}
