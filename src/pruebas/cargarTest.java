package pruebas;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import logica.*;

class cargarTest {
	
	private PMS hotel;
	
	@BeforeEach
	void setUp() throws Exception {
		hotel = new PMS();
	}

	@Test
	void testCargarReservas() {
		hotel.cargar();
		assertTrue(hotel.reservas() != null);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testCargarHabitaciones() {
		hotel.cargarHabitaciones();
		assertTrue(hotel.getReservaHabitaciones() != null);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testCargarFacturas() {
		hotel.cargarFacturas();
		assertTrue(hotel.getMapaServicios() != null);
	}
	
	@Test
	void testCargarDatos() throws FileNotFoundException {
		hotel.cargarDatos("pTar.txt", "pInv.txt");
		assertTrue(hotel.pTarifas != null);
		assertTrue(hotel.pInventario != null);
	}
	
	@Test
	void testCargarDatosException() {
		assertThrows(FileNotFoundException.class, () -> {
			hotel.cargarDatos("p.txt", "q.txt");
        });
	}

	@Test
	void testCargarUsuarios() {
		hotel.cargarUsuarios();
		assertTrue(hotel.obtenerUsuarios() != null);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testCargarServiciosPrecios() {
		hotel.cargarServiciosPrecios();
		assertTrue(hotel.getMapaServicios() != null);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testCargarMenus() {
		hotel.cargarMenus();
		assertTrue(hotel.mapaPlatosMenuHabitacion != null);
		assertTrue(hotel.mapaBebidasMenuHabitacion != null);
		assertTrue(hotel.mapaPlatosMenuComedor != null);
		assertTrue(hotel.mapaBebidasMenuComedor != null);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testCargarServicios() {
		hotel.cargarServicios();
		assertTrue(hotel.getMapaServicios() != null);
	}
	
	@Test
	void testCargarPasarelas() throws IOException {
		hotel.cargarPasarelas();
		assertTrue(hotel.getpasarelasDePagoStr() != null);
	}
	
	@Test
	void testCargarVentas() throws Exception {
		assertTrue(Ventas.mapaVentas != null);
	}
	
	@Test
	void testCargarVentasException() {
		assertThrows(Exception.class, () -> {
			@SuppressWarnings("unused")
			Ventas test = new Ventas("no.txt");
        });
	}
	
}
