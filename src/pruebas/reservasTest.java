package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.*;
import persistencia.PersistenciaHabitacionReservada;
import persistencia.PersistenciaReserva;

class reservasTest {
	private PMS hotel;
	private Huesped user;
	private ArrayList<String> tipoHabitacion;
	private HashMap<String, Reserva> data;
	private HashMap<String, ArrayList<LocalDate>> data2;
	private PersistenciaReserva control;
	private PersistenciaHabitacionReservada control2;
	
	@BeforeEach
	void setUp() throws Exception {
		hotel = new PMS();
		control = new PersistenciaReserva();
		control2 = new PersistenciaHabitacionReservada();
		user = new Huesped("Jaimito", 1000000L, "jm@gmail.com", 311000000L, 0);
		tipoHabitacion = new ArrayList<>();
		tipoHabitacion.add("Estandar");
		data = control.leer("reservass.txt");
		data2 = control2.leer("OcupacionDeHabitaciones.txt");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		control.escribir("reservass.txt", data);
		control2.escribir("OcupacionDeHabitaciones.txt", data2);
	}
	
	@Test
	void testReservar() throws FileNotFoundException {
		
		hotel.reservar("101", LocalDateTime.now(), LocalDateTime.now());
		String id = hotel.crearRecerva(LocalDateTime.now(), LocalDateTime.now(), 0, 
				null, null, LocalDateTime.now(), "101", "Jaimito", 0L, 
				"jm@gmail.com", 0L, 0, tipoHabitacion);
		assertTrue (hotel.consultarReserva(id)!= null);
		assertTrue(hotel.buscarReservas(id) != null );
		ArrayList<String> dispo = hotel.consultar(LocalDate.of(1, 1, 1), LocalDate.now());
		assertFalse (dispo.contains(id));
		assertFalse (dispo.contains(id));
	}
	
	

}
