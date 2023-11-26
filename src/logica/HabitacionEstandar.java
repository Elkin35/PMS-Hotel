package logica;

import java.util.ArrayList;

public class HabitacionEstandar extends Habitacion {

	public HabitacionEstandar(int identificador, String ubicacion, int capacidad, int tamanio, int voltaje, boolean balcon, boolean vista,
			boolean cocinaIntegrada, boolean calefaccion, boolean aireAc, boolean TV, boolean cafetera, boolean hipoalergenico, boolean plancha,
			boolean secador, boolean usb_a, boolean usb_c, boolean desayuno, String tipo, boolean disponibilidad, boolean nino, int capacidadNinos) {
		super(identificador, ubicacion, capacidad, tamanio, voltaje, balcon, vista,
				cocinaIntegrada, calefaccion, aireAc, TV, cafetera, hipoalergenico, plancha,
				secador, usb_a, usb_c, desayuno, tipo, disponibilidad, nino, capacidadNinos);
		super.disponibilidad = true;
		super.tipo = "Estandar";
		super.camas = super.getCamas();
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public boolean haveBalcon() {
		return balcon;
	}

	public void setBalcon(boolean balcon) {
		this.balcon = balcon;
	}

	public boolean haveVista() {
		return vista;
	}

	public void setVista(boolean vista) {
		this.vista = vista;
	}

	public boolean haveCocinaIntegrada() {
		return cocinaIntegrada;
	}

	public void setCocinaIntegrada(boolean cocinaIntegrada) {
		this.cocinaIntegrada = cocinaIntegrada;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean haveDisponibilidad() {
		return disponibilidad;
	}

	public void changeDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public ArrayList<Cama> getCamas() {
		return camas;
	}

}
