package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String idioma;
    private Integer cantidadDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Libro() {}

    public Libro(RecordDatosLibros recordDatosLibros) {
        this.titulo = recordDatosLibros.titulo();
        this.cantidadDescargas = recordDatosLibros.cantidadDescargas();

        var idiomas = recordDatosLibros.idiomas();
        this.idioma = (idiomas != null && !idiomas.isEmpty()) ? idiomas.get(0) : null;
        setAutor(recordDatosLibros.autores());
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", cantidadDescargas=" + cantidadDescargas +
                ", autor=" + (autor != null ? autor.getNombreAutor() : "Sin autor") +
                '}';
    }

    private void setAutor(List<RecordDatosAutores> listaAutores){

         String nombreAutor = "Desconocido";
         Integer añoNacimientoAutor = 0;
         Integer añoFalleciAutor = 0 ;

        var recordAutor = (listaAutores != null && !listaAutores.isEmpty()) ? listaAutores.get(0) : null;

        Autor autor;

        if (recordAutor != null){
            autor =  new Autor(recordAutor);
        }else{
            autor = new Autor(nombreAutor, añoNacimientoAutor, añoFalleciAutor);
        }
this.autor = autor;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
