package com.backend.integrador;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.integrador.entity.Caracteristica;
import com.backend.integrador.entity.Categoria;
import com.backend.integrador.entity.ImagenProducto;
import com.backend.integrador.entity.Producto;
import com.backend.integrador.entity.Reserva;
import com.backend.integrador.entity.Rol;
import com.backend.integrador.entity.Usuario;
import com.backend.integrador.repository.ICaracteristicasRepository;
import com.backend.integrador.repository.ICategoriaRepository;
import com.backend.integrador.repository.IImagenProductoRepository;
import com.backend.integrador.repository.IProductoRepository;
import com.backend.integrador.repository.IReservaRepository;
import com.backend.integrador.repository.IUsuarioRepository;

@SpringBootApplication
public class Pi03BackendApplication implements CommandLineRunner{
	@Autowired
	private ICategoriaRepository categoriaRepository;
	@Autowired
	private ICaracteristicasRepository caracteristicasRepository;
	@Autowired
	private IProductoRepository productoRepository;
	@Autowired
	private IImagenProductoRepository imagenProductoRepository;
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IReservaRepository reservaRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Pi03BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Se agregan las categorias del producto a la base de datos
		categoriaRepository.save(new Categoria(1L,"Compresores de aire","Son ideales para darle potencia a otras herramientas neumáticas o bien realizar múltiples tareas como inflar neumáticos de coches y bicicletas, limpiar o hasta rociar pintura."));
		categoriaRepository.save(new Categoria(2L,"Mezcladoras de cemento","Mezcla los componentes del concreto, tales como el cemento, la arena, la piedra y el agua. La ventaja de usar una mezcladora en vez de hacer el batido a mano, es que la mezcla de concreto queda uniforme y homogénea."));
		categoriaRepository.save(new Categoria(3L,"Apisonadores","Especiales para trabajos de compactación, mayor rendimiento y durabilidad en la construccion."));
		categoriaRepository.save(new Categoria(4L,"Maquinaria semipesada","Son maquinarias de tamaño mediano utilizados generalmente en la construcción por ejemplo: Camión volqueta, carros Cisternas o Aguateros, camiones escalera. El peso y volumen de estas unidades es mediano."));
		categoriaRepository.save(new Categoria(5L,"Cortadoras de concreto","se utilizan para realizar cortes lineales y con precisión en concreto y asfalto. Son muy usadas para realizar juntas de dilatación, trabajos de reparación y pavimentación. "));
		
		// Se agregan las caracteristicas del producto a la base de datos
		caracteristicasRepository.save(new Caracteristica(1L,"Domicilio incluido","fa-light fa-truck"));
		caracteristicasRepository.save(new Caracteristica(2L,"Nuevo","fa-solid fa-n"));
		caracteristicasRepository.save(new Caracteristica(3L,"Indicaciones Especiales","fa-solid fa-exclamation"));
		caracteristicasRepository.save(new Caracteristica(4L,"Requiere licencia para manipular","fa-solid fa-graduation-cap"));
		caracteristicasRepository.save(new Caracteristica(5L,"Seguro incluido","fa-regular fa-clipboard-check"));
		caracteristicasRepository.save(new Caracteristica(6L,"Equipo especializado","fa-solid fa-thumbs-up"));
		caracteristicasRepository.save(new Caracteristica(7L,"Incluye accesorios","fa-regular fa-toolbox"));
		caracteristicasRepository.save(new Caracteristica(8L,"Mantenimiento incluido","fa-regular fa-screwdriver-wrench"));

		List<Rol> rolesUser = new ArrayList<>();
		Rol rolUser = new Rol();
		rolUser.setNombreRol("ROLE_USER");
		rolesUser.add(rolUser);
		var user = Usuario.builder()
				.name("Usuario")
				.lastName("Usuario")
				.email("usuario@usuario.com")
				.password(passwordEncoder.encode("usuario"))
				.roles(rolesUser)
				.build();
		
		usuarioRepository.save(user);

		

		// Se cargan los productos a la base de datos
		var idCaracteristicas = List.of(1L, 2L, 3L);
		List<Caracteristica> caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(1L,"Apisonador (canguro) Motor HONDA GX120R Gasolina", "Motor Gasolina: Honda GXR120. Potencia: 4 Hp. Masa operacional: 75 Kg", 50.0, categoriaRepository.findById(3L).orElse(null), caracteristicas));
		reservaRepository.save(new Reserva(1L, LocalDate.of(2024,3,22), LocalDate.of(2024, 3,26), 2,  productoRepository.findById(1L).orElse(null), usuarioRepository.findById(1L).orElse(null)));

		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(2L,"Mezcladora de Concreto MASTER 1.5 Bultos (325litros/11ft³)", "MASTER 1.5 Bultos (325litros/11ft³ ) (con diferentes opciones de tipo de Motor). Capacidad litros 325 litros. Capacidad pies 3 11,4. Calibre lamina 3/16″.", 80.0, categoriaRepository.findById(2L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(3L,"Cortadora de concreto FORTE a gasolina 13 HP, conducción automática", "La cortadora de concreto de conducción automática, realiza cortes rectilíneos en pisos de concreto, asfalto, adoquín y mármol entre otros proporcionando al operario facilidad en el manejo y mayor precisión, sus ruedas facilitan el desplazamiento y maniobrabilidad de la máquina. Motor FORTE 13 HP.", 50.0, categoriaRepository.findById(5L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(4L,"Cortadora de concreto a gasolina motor Forte 13HP, disco de corte 18\'", "Capacidad tanque de agua: 35 L. Dimensiones: 105x58x94 cm. Material de tanque de agua: Metálico. Peso: 128 Kg. Motor: Forte GM390. Ajuste de profundidad: Manija de Rotación. Conducción: MANUAL. DIÁMETRO de disco: 5-50 cm (14-20”)", 43.0, categoriaRepository.findById(5L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(5L,"Mezcladora de Concreto MASTER 2 Bultos (390litros/13.7ft³)", "Capacidad litros: 390 litros. Capacidad pies: 3 13,7. Calibre lamina: 3/16″. Giro radial tolva: 360°. Llantas tipo carretilla ring: 13 negro. Revolución de salida: 32-35. Potencia requerida gasolina: 9hp. Potencia requerida diésel (acpm): 10hp. Potencia requerida eléctrica: 4hp. Dimensiones (L x An x Al): 122 × 73 × 164. Peso sin motor: 300kg", 36.0, categoriaRepository.findById(2L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(6L,"Apisonador FORTE - gasolina 4,0 HP", "Capacidad de tanque: 2,8 L. Dimensiones: 51x51x104 cm. Fuerza de impacto máxima: 13,7 kN. Golpes por minuto: 640-680. Peso: 74 kg. Tamaño de zapata: 35x28,5 cm. Motor: Forte GM165. Potencia: 4,0 HP.", 55.0, categoriaRepository.findById(3L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(7L,"Compresor de aire Portatil HYUNDAI HYAC100C 2hp", "Tipo de compresor: Pistón. Tipo de acople: Directo. Capacidad del tanque: 100Lts. Potencia: 2 HP. Voltaje: 110V. Frecuencia: 60 Hz. Numero de fases: Monofásico. Máx Presión: 115psi / 8 BAR. Caudal: 204Lt/min.", 20.0, categoriaRepository.findById(1L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(8L,"Compresor de aire ELITE 3.0HP", "Potencia: 3.0h/p. Capacidad: 50L. Flujo max a 0 psi: 8.4 cfm. Flujo max a 40 psi: 5,2 cfm. Flujo max a 90 psi: 4.2 cfm. Presión max: 125 psi. Caudal max: 360 l/m. 2 pistones de 47mm. Uso: profesional. Garantía: 1 año. Peso: 40.0 Kg. Dimensiones: 66cm Alto x 71cm Ancho x c36m Largo.", 23.0, categoriaRepository.findById(1L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(9L,"Minicargador 316GR iT4", "Capacidad de operación nominal: 795 kg (1.750 lb.). Potencia Bruta: 44 kW (60 CV) a 2.600 rpm. Potencia neta: 42 kW (56 CV) a 2.600 rpm. Peso operativo: 2806 kg (6.180 lb)", 150.0, categoriaRepository.findById(4L).orElse(null), caracteristicas));
		
		idCaracteristicas = List.of( 2L, 3L);
		caracteristicas = idCaracteristicas.stream().map(numero -> caracteristicasRepository.findById(numero).orElse(null)).toList();
		productoRepository.save(new Producto(10L,"Minicargador de Orugas 325G", "Capacidad de operación nominal: 1176 kg (2,590 lb.). Potencia Bruta: 55,0 kW (74 CV) a 2.500 rpm. Potencia neta: 52,7 kW (71 CV) a 2.500 rpm. Peso operativo: 4313 kg (9.500 lb.)", 150.0, categoriaRepository.findById(4L).orElse(null), caracteristicas));
		
		// Se le cargan la imagenes a los productos
		imagenProductoRepository.save(new ImagenProducto(1L,"localhost:8080/api/imagenes/img1_1.png", productoRepository.findById(1L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(2L,"localhost:8080/api/imagenes/img1_2.png", productoRepository.findById(1L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(3L,"localhost:8080/api/imagenes/img1_3.png", productoRepository.findById(1L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(4L,"localhost:8080/api/imagenes/img2_1.png", productoRepository.findById(2L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(5L,"localhost:8080/api/imagenes/img2_2.png", productoRepository.findById(2L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(6L,"localhost:8080/api/imagenes/img2_3.png", productoRepository.findById(2L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(7L,"localhost:8080/api/imagenes/img3_1.png", productoRepository.findById(3L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(8L,"localhost:8080/api/imagenes/img3_2.png", productoRepository.findById(3L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(9L,"localhost:8080/api/imagenes/img3_3.png", productoRepository.findById(3L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(10L,"localhost:8080/api/imagenes/img4_1.png", productoRepository.findById(4L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(11L,"localhost:8080/api/imagenes/img4_2.png", productoRepository.findById(4L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(12L,"localhost:8080/api/imagenes/img4_3.png", productoRepository.findById(4L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(13L,"localhost:8080/api/imagenes/img5_1.png", productoRepository.findById(5L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(14L,"localhost:8080/api/imagenes/img5_2.png", productoRepository.findById(5L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(15L,"localhost:8080/api/imagenes/img5_3.png", productoRepository.findById(5L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(16L,"localhost:8080/api/imagenes/img6_1.png", productoRepository.findById(6L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(17L,"localhost:8080/api/imagenes/img6_2.png", productoRepository.findById(6L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(18L,"localhost:8080/api/imagenes/img6_3.png", productoRepository.findById(6L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(19L,"localhost:8080/api/imagenes/img7_1.png", productoRepository.findById(7L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(20L,"localhost:8080/api/imagenes/img7_2.png", productoRepository.findById(7L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(21L,"localhost:8080/api/imagenes/img7_3.png", productoRepository.findById(7L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(22L,"localhost:8080/api/imagenes/img8_1.png", productoRepository.findById(8L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(23L,"localhost:8080/api/imagenes/img8.2.jpg", productoRepository.findById(8L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(24L,"localhost:8080/api/imagenes/img8.3.jpg", productoRepository.findById(8L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(25L,"localhost:8080/api/imagenes/img9_1.png", productoRepository.findById(9L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(26L,"localhost:8080/api/imagenes/img9_2.png", productoRepository.findById(9L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(27L,"localhost:8080/api/imagenes/img9_3.jpg", productoRepository.findById(9L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(28L,"localhost:8080/api/imagenes/img10_1.png", productoRepository.findById(10L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(29L,"localhost:8080/api/imagenes/img10_2.jpg", productoRepository.findById(10L).orElse(null)));
		imagenProductoRepository.save(new ImagenProducto(30L,"localhost:8080/api/imagenes/img10_3.jpg", productoRepository.findById(10L).orElse(null)));

		// Crear usuario ADMIN		
		List<Rol> roles = new ArrayList<>();
		Rol rolAdmin = new Rol();
		rolAdmin.setNombreRol("ROLE_ADMIN");
		roles.add(rolAdmin);
		var admin = Usuario.builder()
				.name("Administrador")
				.lastName("Admin")
				.email("admin@admin.com")
				.password(passwordEncoder.encode("admin"))
				.roles(roles)
				.build();
		
		usuarioRepository.save(admin);


		// Crear usuario ADMIN		
		
	}

}
