package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l")
    List<Libro> findAllLibros();


    @Query("SELECT l FROM Libro l WHERE l.idioma = :lenguage")
    List<Libro> findLibrosByIdioma(String lenguage);


}
