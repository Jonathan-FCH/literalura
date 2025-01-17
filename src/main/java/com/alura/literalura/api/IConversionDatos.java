package com.alura.literalura.api;

public interface IConversionDatos {
        <T> T obtenerDatos(String json, Class<T> clase);
        }
