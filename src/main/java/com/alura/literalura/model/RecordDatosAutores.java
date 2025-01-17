package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordDatosAutores(

        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") Integer añoNacimientoAutor,
        @JsonAlias("death_year") Integer añoFalleciAutor

) {
}
