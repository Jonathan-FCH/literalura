package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutoresRepository extends JpaRepository<Autor, Long> {


    @Query("SELECT s FROM Autor s")
    List<Autor> findAllAutores();

//    @Query("SELECT a FROM Autor a WHERE a.añoNacimientoAutor <= :año AND (a.añoFalleciAutor IS NULL OR a.añoFalleciAutor > :año)")
//    List<Autor> findAutoresVivosEnAño(int año);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros WHERE :anio BETWEEN a.añoNacimientoAutor AND a.añoFalleciAutor")
    List<Autor> findAutoresVivosEnAño(int anio);


    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> findAllAutoresConLibros();





}
