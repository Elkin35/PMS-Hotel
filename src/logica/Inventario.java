package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {

	// Attributes

	public static ArrayList<Habitacion> estandar;
	public static ArrayList<Habitacion> suite;
	public static ArrayList<Habitacion> suiteDoble;
	static HashMap<String, ArrayList<Habitacion>> inventario;

	// Constructor method

	public Inventario() {
		this.estandar = new ArrayList<Habitacion>();
		this.suite = new ArrayList<Habitacion>();
		this.suiteDoble = new ArrayList<Habitacion>();
		this.inventario = new HashMap<String, ArrayList<Habitacion>>();
		this.inventario.put("Estandar", estandar);
		this.inventario.put("Suite", suite);
		this.inventario.put("Suite doble", suiteDoble);
	}

	// Methods

	public static HashMap<String, ArrayList<Habitacion>> getInventario() {
		return inventario;
	}

	public static Habitacion getHabitacion(int id, String ubicacion) {
		try {
			for (Habitacion habitacion : estandar) {
				if (habitacion.identificador == id && habitacion.ubicacion.equals(ubicacion)) {
					return habitacion;
				}
			}
			;
			for (Habitacion habitacion : suite) {
				if (habitacion.identificador == id && habitacion.ubicacion.equals(ubicacion)) {
					return habitacion;
				}
			}
			;
			for (Habitacion habitacion : suiteDoble) {
				if (habitacion.identificador == id && habitacion.ubicacion.equals(ubicacion)) {
					return habitacion;
				}
			}
			;
		} catch (Exception e) {
			System.out.println("No hay una habitación con el id " + id + " en la ubicación " + ubicacion);
		}
		return null;
	}

	public HashMap<String, ArrayList<Habitacion>> habitacionesDispoinblesPorTipo() {

		HashMap<String, ArrayList<Habitacion>> disponibles = new HashMap<String, ArrayList<Habitacion>>();

		ArrayList<Habitacion> estandarDisp = new ArrayList<Habitacion>();
		ArrayList<Habitacion> suiteDisp = new ArrayList<Habitacion>();
		ArrayList<Habitacion> suiteDobleDisp = new ArrayList<Habitacion>();
		disponibles.put("Estandar", estandarDisp);
		disponibles.put("Suite", suiteDisp);
		disponibles.put("Suite doble", suiteDobleDisp);

		for (Habitacion habitacion : estandar) {
			if (habitacion.disponibilidad) {
				estandarDisp.add(habitacion);
			}
		}
		;
		for (Habitacion habitacion : suite) {
			if (habitacion.disponibilidad) {
				suiteDisp.add(habitacion);
			}
		}
		;
		for (Habitacion habitacion : suiteDoble) {
			if (habitacion.disponibilidad) {
				suiteDobleDisp.add(habitacion);
			}
		}
		;

		return disponibles;
	}

}
