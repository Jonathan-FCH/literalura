package com.alura.literalura.api;

import com.alura.literalura.model.RecordDatosLibros;
import com.alura.literalura.model.ResponseDatosLibros;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertidorDatos {

    public RecordDatosLibros convertidor(String jsonObtenido) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

// Deserializa el JSON en una lista de RecordDatosLibros

        try {
            var datosParseados = objectMapper.readValue(jsonObtenido, ResponseDatosLibros.class);

// Muestra los datos que especifgicamos en consola
//            for (RecordDatosLibros libro : datosParseados.results()) {
////     //           System.out.println("ID: " + libro.id());
//                System.out.println("Title: " + libro.titulo());
//                System.out.println("Authors: " + libro.autores());
//                System.out.println("Languages: " + libro.idiomas());
//                System.out.println("Download count: " + libro.cantidadDescargas());
//            }
//            if (!datosParseados.results().isEmpty()) {
//                return datosParseados.results().get(0); // Devuelve el primer libro
//
//            } else {
//                throw new RuntimeException("No se encontraron libros en los resultados.");
//            }
            // Verifica si hay resultados
            if (!datosParseados.results().isEmpty()) {
                // Obtiene el primer libro de la lista
                RecordDatosLibros primerLibro = datosParseados.results().get(0);

                // Muestra en consola los datos del primer libro
                System.out.println("Título: " + primerLibro.titulo());
                System.out.println("Autor/es: " + primerLibro.autores());
                System.out.println("Idioma: " + primerLibro.idiomas());
                System.out.println("Cantidad de descargas: " + primerLibro.cantidadDescargas());

                return primerLibro; // Devuelve el primer libro
            } else {
                throw new RuntimeException("No se encontraron libros en los resultados.");
            }

        }catch (JsonProcessingException e){
            // Manejo de la excepción
            System.err.println("Error al procesar el JSON: " + e.getMessage());
            throw new RuntimeException(e); // Propagar la excepción si es necesario
        }

    }
}
