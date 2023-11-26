package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import persistencia.PersistenciaTarifas;

public class Administrador {

	// Methods

	public void actualizarTarifas(Tarifa tarifa, LocalDate inicio, LocalDate fin, float valor, PersistenciaTarifas pT) {
		tarifa.setTarifa(inicio, fin, valor);
		if (tarifa.tipoHabitacion.equals("Estandar")) {
			for (LocalDate dia : tarifa.diasSemana) {
				pT.estandar.put(dia, (double) valor);
			}
		} else if (tarifa.tipoHabitacion.equals("Suite")) {
			for (LocalDate dia : tarifa.diasSemana) {
				pT.suite.put(dia, (double) valor);
			}
		} else if (tarifa.tipoHabitacion.equals("Suite doble")) {
			for (LocalDate dia : tarifa.diasSemana) {
				pT.suiteDoble.put(dia, (double) valor);
			}
		}
	}

	public boolean crearTarifa(LocalDate fechaInicio, LocalDate fechaFin, ArrayList<LocalDate> diasSemana, float valor,
			String tipo, PersistenciaTarifas pT) {

		Tarifa tarifa = new Tarifa(fechaInicio, fechaFin, diasSemana, valor, tipo);

		if (tarifa.tipoHabitacion.equals("Estandar")) {
			for (LocalDate dia : tarifa.diasSemana) {
				pT.estandar.put(dia, (double) valor);
			}
			return true;
		} else if (tarifa.tipoHabitacion.equals("Suite")) {
			for (LocalDate dia : tarifa.diasSemana) {
				pT.suite.put(dia, (double) valor);
			}
			return true;
		} else if (tarifa.tipoHabitacion.equals("Suite doble")) {
			for (LocalDate dia : tarifa.diasSemana) {
				pT.suiteDoble.put(dia, (double) valor);
			}
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("static-access")
	public boolean crearHabitacion(int identificador, String ubicacion, int capacidad, int tamanio, int voltaje, boolean balcon, boolean vista,
			boolean cocinaIntegrada, boolean calefaccion, boolean aireAc, boolean TV, boolean cafetera, boolean hipoalergenico, boolean plancha,
			boolean secador, boolean usb_a, boolean usb_c, boolean desayuno, String tipo, boolean disponibilidad, boolean nino, int capacidadNinos,
			Inventario inventario) {

		if (tipo.equals("Estandar")) {
			HabitacionEstandar habitacion = new HabitacionEstandar(identificador, ubicacion, capacidad, tamanio, voltaje, balcon, vista,
					cocinaIntegrada, calefaccion, aireAc, TV, cafetera, hipoalergenico, plancha,
					secador, usb_a, usb_c, desayuno, tipo, disponibilidad, nino, capacidadNinos);
			inventario.estandar.add(habitacion);
			return true;
		} else if (tipo.equals("Suite")) {
			HabitacionSuite habitacion = new HabitacionSuite(identificador, ubicacion, capacidad, tamanio, voltaje, balcon, vista,
					cocinaIntegrada, calefaccion, aireAc, TV, cafetera, hipoalergenico, plancha,
					secador, usb_a, usb_c, desayuno, tipo, disponibilidad, nino, capacidadNinos);
			inventario.suite.add(habitacion);
			return true;
		} else if (tipo.equals("Suite doble")) {
			HabitacionSuiteDoble habitacion = new HabitacionSuiteDoble(identificador, ubicacion, capacidad, tamanio, voltaje, balcon, vista,
					cocinaIntegrada, calefaccion, aireAc, TV, cafetera, hipoalergenico, plancha,
					secador, usb_a, usb_c, desayuno, tipo, disponibilidad, nino, capacidadNinos);
			inventario.suiteDoble.add(habitacion);
			return true;
		} else {
			return false;
		}
	}

}