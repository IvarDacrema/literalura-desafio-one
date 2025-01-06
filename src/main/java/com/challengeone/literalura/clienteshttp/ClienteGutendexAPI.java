package com.challengeone.literalura.clienteshttp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteGutendexAPI {
    private final String URL_BASE = "https://gutendex.com/books";

    public String solicitarGutendexAPI(String endpoint) {

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_BASE + endpoint)).build(); // URI: Configura la URI de la solicitud HTTP usando .uri(URI.create(url)), donde url es el parámetro pasado al método.

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "\nError: Código de estado: " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "\n¡Error en la solicitud a la API! " + e.getMessage();
        }
    }
}
