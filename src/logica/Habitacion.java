package logica;

import java.util.ArrayList;

public abstract class Habitacion {

	// Attributes

	protected int identificador;
	protected String ubicacion;
	protected int capacidad;
	protected boolean balcon;
	protected boolean vista;
	protected boolean cocinaIntegrada;
	protected String tipo;
	protected boolean disponibilidad;
	protected boolean nino;
	protected ArrayList<Cama> camas;
	protected int capacidadAdultos;
	protected int capacidadNinos;
	
	// nuevos 
	
	protected boolean calefaccion;
	protected boolean aireAc;
	protected boolean TV;
	protected boolean cafetera;
	protected boolean hipoalergenico;
	protected boolean plancha;
	protected boolean secador;
	protected boolean usb_a;
	protected boolean usb_c;
	protected boolean desayuno;
	protected int tamanio;
	protected int voltaje;
	
	// Constructor method

	public Habitacion(int identificador, String ubicacion, int capacidad, int tamanio, int voltaje, boolean balcon, boolean vista,
			boolean cocinaIntegrada, boolean calefaccion, boolean aireAc, boolean TV, boolean cafetera, boolean hipoalergenico, boolean plancha,
			boolean secador, boolean usb_a, boolean usb_c, boolean desayuno, String tipo, boolean disponibilidad, boolean nino, int capacidadNinos) {
		this.identificador = identificador;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.balcon = balcon;
		this.vista = vista;
		this.cocinaIntegrada = cocinaIntegrada;
		this.tipo = tipo;
		this.disponibilidad = disponibilidad;
		this.nino = nino;
		this.capacidadNinos = capacidadNinos;
		this.capacidadAdultos = capacidad - capacidadNinos;
		this.camas = setCamas(capacidadAdultos, capacidadNinos);
		this.calefaccion = calefaccion;
		this.aireAc = aireAc;
		this.TV = TV;
		this.cafetera = cafetera;
		this.hipoalergenico = hipoalergenico;
		this.plancha = plancha;
		this.secador = secador;
		this.usb_a = usb_a;
		this.usb_c= usb_c;
		this.desayuno = desayuno;
		this.tamanio = tamanio;
		this.voltaje = voltaje;

	}

	// Getters and setters

	public int getIdentificador() {
		return identificador;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public boolean getNinos() {
		return nino;
	}

	public int getCapacidadNinos() {
		return capacidadNinos;
	}

	public int getCapacidadAdultos() {
		return capacidadAdultos;
	}

	public boolean haveBalcon() {
		return balcon;
	}

	public boolean haveVista() {
		return vista;
	}

	public boolean haveCocinaIntegrada() {
		return cocinaIntegrada;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean haveDisponibilidad() {
		return disponibilidad;
	}

	public void changeDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	// getters nuevos
	
	private ArrayList<Cama> setCamas(int capacidadAdultos, int capacidadNino) {

		ArrayList<Cama> camasList = new ArrayList<Cama>();

		int cantidadAdulto;
		int cantidadNino;

		if (capacidadAdultos % 2 == 0) {
			cantidadAdulto = capacidadAdultos / 2;
		} else {
			cantidadAdulto = (capacidadAdultos - 1) / 2;
			camasList.add(new Cama("Sencilla", 1, false));
		}

		for (int i = 1; i <= cantidadAdulto; i++) {
			camasList.add(new Cama("Doble", 2, false));
		}
		if (capacidadNino % 2 == 0) {
			cantidadNino = capacidadNino / 2;
		} else {
			cantidadNino = (capacidadNino - 1) / 2;
			camasList.add(new Cama("Sencilla", 1, true));
		}

		for (int i = 1; i <= cantidadNino; i++) {
			camasList.add(new Cama("Doble", 2, true));
		}
		return camasList;

	}
	
	// Other methods

	public boolean isBalcon() {
		return balcon;
	}

	public boolean isCalefaccion() {
		return calefaccion;
	}
	
	public boolean isAireAc() {
		return aireAc;
	}

	public boolean isTV() {
		return TV;
	}

	public boolean isCafetera() {
		return cafetera;
	}

	public boolean isHipoalergenico() {
		return hipoalergenico;
	}

	public boolean isPlancha() {
		return plancha;
	}

	public boolean isSecador() {
		return secador;
	}

	public boolean isUsb_a() {
		return usb_a;
	}

	public boolean isUsb_c() {
		return usb_c;
	}

	public boolean isDesayuno() {
		return desayuno;
	}

	public int getTamanio() {
		return tamanio;
	}

	public int getVoltaje() {
		return voltaje;
	}

	public ArrayList<Cama> getCamas() {
		return camas;
	}

	public boolean perfecta(int capacidadAdultos, int CapacidadNinos, boolean balcon, boolean vista, boolean cocina) {

		if (this.haveBalcon() == balcon && this.haveVista() == vista && this.cocinaIntegrada == cocina
				&& disponibilidad == true) {

			if (capacidadAdultos <= this.capacidadAdultos
					&& CapacidadNinos <= (this.capacidadNinos + this.capacidadAdultos)-capacidadAdultos) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

}

