package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

//Este ClassRecord recibe json encapsulando la propiedad results que tiene todos los datos que ocupamos para el literalura

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseDatosLibros(
        @JsonAlias("results") List<RecordDatosLibros> results
) {
}
