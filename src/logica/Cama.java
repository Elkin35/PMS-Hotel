package logica;

public class Cama {

	// Attributes

	private String tamano;
	private int capacidad;
	private boolean nino;

	// Constructor method

	public Cama(String tamano, int capacidad, boolean nino) {
		this.tamano = tamano;
		this.capacidad = capacidad;
		this.nino = nino;
	}

	// Getters

	public String getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public boolean isNino() {
		return nino;
	}

}