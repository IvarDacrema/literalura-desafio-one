package com.challengeone.literalura.clienteshttp;

import org.springframework.stereotype.Component;

@Component
public class SolicitudesGutendexAPI {

    private static ClienteGutendexAPI clienteGutendexAPI = new ClienteGutendexAPI();

    public static String solicitudAPI(String endpoint) {
        String json = null;
        try {
            json = clienteGutendexAPI.solicitarGutendexAPI(endpoint);
            if (json != null) {
                return json;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            return null;
        }
        return null;
    }
}
