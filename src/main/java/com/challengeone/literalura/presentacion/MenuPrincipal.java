package com.challengeone.literalura.presentacion;

import com.challengeone.literalura.servicios.persistencia.BaseDatosServicio;
import com.challengeone.literalura.servicios.convertidores.BusquedaLibrosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class MenuPrincipal {

    private final BaseDatosServicio baseDatosServicio;
    private final BusquedaLibrosServicio busquedaLibrosServicio;

    @Autowired
    public MenuPrincipal(BaseDatosServicio baseDatosServicio, BusquedaLibrosServicio busquedaLibrosServicio) {
        this.baseDatosServicio = baseDatosServicio;
        this.busquedaLibrosServicio = busquedaLibrosServicio;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario;
        String menuPrincipal = """
                                \n MENU
                                     1. Buscar libro.
                                     2. Listar libros registrados.
                                     3. Listar autores registrados.
                                     4. Listar autores por año.
                                     5. Listar libros por idioma.
                                     0. Salir
                               """;

        while (true) {
            System.out.print(menuPrincipal + "\nTu eleccion: ");
            try {
                eleccionUsuario = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nError: Ingresaste un caracter invalido.");
                sc.nextLine();  // Limpiar el buffer
                continue;
            }

            switch (eleccionUsuario) {
                case 0:
                    System.out.println("\nSaliendo de la aplicacion.");
                    break;
                case 1:
                    busquedaLibrosServicio.buscarLibroPorTitulo();
                    break;
                case 2:
                    baseDatosServicio.listarLibrosAlmacenados();
                    break;
                case 3:
                    baseDatosServicio.listarAutoresRegistrados();
                    break;
                case 4:
                    baseDatosServicio.listarAutoresPorAno();
                    break;
                case 5:
                    baseDatosServicio.listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("Error -> Recuerda elegir unicamente entre las opciones disponibles " +
                            "en el menu principal");
                    break;
            }
        }
    }
}
