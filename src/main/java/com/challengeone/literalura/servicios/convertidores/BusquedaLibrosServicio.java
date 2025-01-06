package com.challengeone.literalura.servicios.convertidores;

import com.challengeone.literalura.clienteshttp.SolicitudesGutendexAPI;
import com.challengeone.literalura.modelos.dto.LibroDTO;
import com.challengeone.literalura.modelos.dto.RespuestaApiDTO;
import com.challengeone.literalura.modelos.entidades.LibroEntidad;
import com.challengeone.literalura.servicios.persistencia.BaseDatosServicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BusquedaLibrosServicio {

    private final List<LibroDTO> listaLibrosEncontrados = new ArrayList<>();
    private final BaseDatosServicio baseDatosServicio;
    private final SolicitudesGutendexAPI solicitudesGutendexAPI;

    @Autowired
    public BusquedaLibrosServicio(BaseDatosServicio baseDatosServicio, SolicitudesGutendexAPI solicitudesGutendexAPI) {
        this.baseDatosServicio = baseDatosServicio;
        this.solicitudesGutendexAPI = solicitudesGutendexAPI;
    }

    public void buscarLibroPorTitulo() {
        Scanner sc = new Scanner(System.in);
        String tituloLibroBuscado;

        while (true) {
            System.out.print("\nIngresa el titulo del libro que estas buscando: ");
            tituloLibroBuscado = sc.nextLine();

            System.out.println("\nBuscando el libro.....");

            String endpoint = "?search=" + tituloLibroBuscado.trim().replace(" ", "%20");

            String respuestaApiJson = SolicitudesGutendexAPI.solicitudAPI(endpoint);

            if (respuestaApiJson != null) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                RespuestaApiDTO respuestaApiDTO;

                try {
                    respuestaApiDTO = gson.fromJson(respuestaApiJson, RespuestaApiDTO.class);
                } catch (JsonParseException e) {
                    System.out.println("\nError: No se pudo convertir la respuesta JSON en un objeto Java.");
                    System.out.println(e.getMessage());
                    continue;
                }

                if (respuestaApiDTO != null) {

                    System.out.println("\nSe han encontrado (" + respuestaApiDTO.resultadosEncontrados().size() + ") resultados " +
                            "que coinciden con tu búsqueda. \nSe ha seleccionado la primera coincidencia para ti.");


                    listaLibrosEncontrados.addAll(respuestaApiDTO.resultadosEncontrados());
                    System.out.println(listaLibrosEncontrados.get(0));

                    baseDatosServicio.agregarLibro(new LibroEntidad(listaLibrosEncontrados.get(0)));

                    listaLibrosEncontrados.clear();
                    break;
                }
            } else {
                System.out.println("\nLa consulta a la API de Gutendex retornó como respuesta un NULL.");
            }
        }
    }
}

