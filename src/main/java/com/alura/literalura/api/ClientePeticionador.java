package com.alura.literalura.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientePeticionador {

    //    String urlDireccion = "gutendex.com/books";

    private String urlSeccionBase = "https://gutendex.com/books";
    private String urlSeccionBusqueda = "?search=";
//    private String tituloLibro = "great";

    //Metodo Define como  va a ser la request
    private HttpRequest construccionDePeticion(String setearTituloLibro){
        //Crea un nuevo constructor para configurar una solicitud HTTP.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlSeccionBase + urlSeccionBusqueda + setearTituloLibro))
                .build(); //Construye el objeto HttpRequest con la configuración proporcionada.

        return request; //Devuelve el objeto HttpRequest creado, listo para ser usado en una solicitud HTTP.
    }


    public String obtenerResultadoBusquedaLibro( String tituloLibro){
        //variable request que recibe el objeto obtenido al ejec el metod contruccionDePeticion
        var request = construccionDePeticion(tituloLibro);
        System.out.println("///corriendo peticionador");
        //Se crea el "posman"
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS).build();
        try {
            HttpResponse<String> respuestaDelServidor = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println("///ClientePeticionardor respuestaDelAPI " + respuestaDelServidor);
            String respuestaJson = respuestaDelServidor.body();
//            System.out.println("///ClientePeticionardor respuestaJson" + respuestaJson);
//            System.out.println("///respuestaJson respuestaDelServidor.statusCode " + respuestaDelServidor.statusCode());
            //Retorna el cuerpo de la respuesta en formato Json
            return respuestaJson;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


}
