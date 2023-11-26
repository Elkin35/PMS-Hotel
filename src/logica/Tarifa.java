package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tarifa {
	// Attributes

	LocalDate fechaInicio;
	LocalDate fechaFin;
	ArrayList<LocalDate> diasSemana;
	float costo;
	String tipoHabitacion;

	// Constructor method

	public Tarifa(LocalDate fechaInicio, LocalDate fechaFin, ArrayList<LocalDate> diasSemana, float costo,
			String tipo) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.diasSemana = diasSemana;
		this.costo = costo;
		this.tipoHabitacion = tipo;
	}

	// Getters and setters

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public ArrayList<LocalDate> getDiasSemana() {
		return diasSemana;
	}

	public float getCosto() {
		return costo;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTarifa(LocalDate fechaInicio, LocalDate fechaFin, float costo) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.costo = costo;
	}

}
