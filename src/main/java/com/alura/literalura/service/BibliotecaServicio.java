package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutoresRepository;
import com.alura.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaServicio {

    @Autowired
    LibrosRepository librosRepository;
    @Autowired
    AutoresRepository autoresRepository;

    public Libro guardarLibroConAutor(Libro libro){

        System.out.println(libro.getAutor());
        autoresRepository.save(libro.getAutor());

        return librosRepository.save(libro);

    }

    public List<Libro> mostrarLibrosGuardadosEnBd(){

        return librosRepository.findAll();
    }










}
