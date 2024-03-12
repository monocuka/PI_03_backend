package com.backend.integrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.integrador.entity.Categoria;
import com.backend.integrador.repository.ICategoriaRepository;

@SpringBootApplication
public class Pi03BackendApplication implements CommandLineRunner{
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Pi03BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Prueba", "Categoria de prueba");
		categoriaRepository.save(categoria1);

		
	}

}
