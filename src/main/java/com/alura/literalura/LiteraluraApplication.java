package com.alura.literalura;

import com.alura.literalura.api.ClientePeticionador;
import com.alura.literalura.api.ConvertidorDatos;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutoresRepository;
import com.alura.literalura.repository.LibrosRepository;
import com.alura.literalura.service.BibliotecaServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	//Instancio Objetos
	private static final Scanner scannerLectura = new Scanner(System.in);
	private static int variableEjecucionPrograma = 1;
	ClientePeticionador objClientePeticionador = new ClientePeticionador();
	ConvertidorDatos objConvertidor = new ConvertidorDatos();
	@Autowired
	BibliotecaServicio bibliotecaServicio;

	//repos
	@Autowired
	private LibrosRepository repositorioLibros;
	private List<Libro> libros;

	@Autowired
	private AutoresRepository repositorioAutores;
	private List<Autor> Autores;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {

		do {
			String menu = """
                ////////////////////////////////////////////////////////////////
                
                1) Buscar libro por titulo
                2) Listar libros registrados
                3) Listar autores registrados
                4) Listar autores vivos en un determinado año
                5) Listar libros por idioma
                0) Salir

                ////////////////////////////////////////////////////////////////
        
                """;

			System.out.println("""
					
					\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
					Bienvendido a Literalura
					\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
					""");
			System.out.println(menu);
			var datosIngresadosPorUsuario = scannerLectura.nextLine();

			switch (datosIngresadosPorUsuario) {
				case "1":
					System.out.println("Buscar libro por titulo / Escriba el nombre del libro a buscar");
					buscarLibroAPI();
					break;
				case "2":
					System.out.println("Listar libros registrados");
					mostrarLibrosBuscados();
					break;
				case "3":
					System.out.println("Listar autores registrados");
					listarAutoresRegistrados();
					break;
				case "4":
					System.out.println("Listar autores vivos en un determinado año");
					listarAutoresVivos();
					break;
				case "5":
					System.out.println("Listar libros por idioma");
					listarLibrosPorIdioma();
					break;
				case "0":
					System.out.println("Gracias por su visita");
					variableEjecucionPrograma = 0;
					break;
			}
			//fin del cliclo
		}while (variableEjecucionPrograma != 0);
	}

	public void buscarLibroAPI() throws JsonProcessingException {
		var libroScanner = scannerLectura.nextLine();
		var ejecPeticionObtengJson = objClientePeticionador.obtenerResultadoBusquedaLibro(libroScanner);
		Libro libro = new Libro(objConvertidor.convertidor(ejecPeticionObtengJson));
		bibliotecaServicio.guardarLibroConAutor(libro);
	}

	private void mostrarLibrosBuscados(){

		var  listaLibros = repositorioLibros.findAllLibros();

		for (Libro libro : listaLibros) {
			System.out.println("Título: " + libro.getTitulo());
			System.out.println("Idioma: " + libro.getIdioma());
			System.out.println("Cantidad de Descargas: " + libro.getCantidadDescargas());
			System.out.println("Autor: " + libro.getAutor());
			System.out.println("---------------");
		}
	}

	private void listarAutoresRegistrados(){
		var listaAutores = repositorioAutores.findAllAutoresConLibros();

		for (Autor autor : listaAutores){
			System.out.println("Autor: " + autor.getNombreAutor());
			System.out.println("Fecha de nacimiento " + autor.getAñoNacimientoAutor());
			System.out.println("Fecha de fallecimiento " + autor.getAñoFalleciAutor());

			List<Libro> libros = autor.getLibros();
			if (libros != null && !libros.isEmpty()) {
				System.out.println("Libros:");
				for (Libro libro : libros) {
					System.out.println(" - Título: " + libro.getTitulo());
					System.out.println("   Idioma: " + libro.getIdioma());
					System.out.println("   Descargas: " + libro.getCantidadDescargas());
				}
			} else {
				System.out.println("No hay libros asociados.");
			}
			System.out.println("---------------");
		}
	}

	private void listarAutoresVivos(){

		System.out.println("Seleccione el año vivo de autor(es) que desea buscar");
		var anioScanner = scannerLectura.nextLine();

		var listaAutoresVivos = repositorioAutores.findAutoresVivosEnAño(Integer.parseInt(anioScanner));

		for (Autor autor : listaAutoresVivos){
			System.out.println("Autor " + autor.getNombreAutor());
			System.out.println("Fecha de nacimiento " + autor.getAñoNacimientoAutor());
			System.out.println("Fecha de fallecimiento " + autor.getAñoFalleciAutor());

			List<Libro> libros = autor.getLibros();
			if (libros != null && !libros.isEmpty()) {
				System.out.println("Libros:");
				for (Libro libro : libros) {
					System.out.println(" - Título: " + libro.getTitulo());
					System.out.println("   Idioma: " + libro.getIdioma());
					System.out.println("   Descargas: " + libro.getCantidadDescargas());
				}
			} else {
				System.out.println("No hay libros asociados.");
			}
			System.out.println("---------------");
		}
	}
	private void listarLibrosPorIdioma(){

		System.out.println("""
				Ingrese el idioma para buscar los libros
				
				es-español
				en-ingles
				fr-frances
				pt-portugues
				
				""");
		var lenguageScanner = scannerLectura.nextLine();
		var listaLibrosPorLenguage = repositorioLibros.findLibrosByIdioma(lenguageScanner);

		for (Libro libro : listaLibrosPorLenguage) {
			System.out.println("Título: " + libro.getTitulo());
			System.out.println("Idioma: " + libro.getIdioma());
			System.out.println("Cantidad de Descargas: " + libro.getCantidadDescargas());
			System.out.println("Autor: " + libro.getAutor());
			System.out.println("---------------");
		}

	}




















}


