package logica;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logica.PMS;
import persistencia.PersistenciaPasarela;

public class PasarelaGenerica {
	
	public String nombre;
	private PMS hotel = new PMS();
	
	private PersistenciaPasarela pPasarela = new PersistenciaPasarela();

	public PasarelaGenerica(String nombre) {
		this.nombre = nombre;
		
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String realizarPago(String idReserva, String namePasarela, String nameTitular, String numTarjeta, String precioNeto, 
			String iva, String total, String caducidad, String codSeguridad) { // falta agregar los otros datos como parametro y que el metodo cree los archivos
		boolean centinela = true;

		try {
			
			if(namePasarela.length()>=1) {
				if(nameTitular.length()>=1) {
					if(numTarjeta.length()>=1) {
						try {
							double intNumTarjeta = Double.parseDouble(numTarjeta);
						} catch (NumberFormatException numEx) {
							centinela = false;
							pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
							return "Se dedbe ingresar un numero en el numero de tarjeta debe ser un numero, estado de la transacción fallido";
							
						}
						
						if(caducidad.length()>=1) {
							try {
								SimpleDateFormat formato = new SimpleDateFormat("MM/yy");
								Date fecha = formato.parse(caducidad);
							} catch(ParseException parseEx) {
								centinela = false;
								pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
								return "Se debe ingresar una fecha en el formato (MM/YY), estado de la transacción fallido";
							}
							
							if(codSeguridad.length()>=1) {
								
								try {
									Double intNumTarjeta = Double.parseDouble(numTarjeta);
								} catch (NumberFormatException numEx) {
									centinela = false;
									pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
									return "Se dedbe ingresar un numero en el codigo de seguridad, estado de la transacción fallido";
								}
								
								if(centinela) {
								try {
									hotel.eliminarHuespedes(idReserva);
									pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
									return "Todo en orden. Transaccion exitosa";
								} catch (FileNotFoundException e) {
									
									e.printStackTrace();
									return "No se encontró la ubicación para guardar el archivo";
								}
								}
								
								//--------------Else's--------------------
								
							} else { // Codigo de seguridad
								centinela = false;
								pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
								return "No se ha ingresado el codigo de seguridad, estado de la transacción fallido";
							}
							
						} else { //Caducidad
							centinela = false;
							pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
							return "No se ha ingresado la fecha de caducidad, estado de la transacción fallido";
						}
						
					} else { // numTarjeta
						centinela = false;
						pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
						return "No se ha ingresado el numero de tarjeta, estado de la transacción fallido";
					}
					
					
				} else { //nameTitular
					centinela = false;
					pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
					return "No se ha ingresado el nombre del titular, estado de la transacción fallido";
				}
				
			} else { //idReserva,  namePasarela
				centinela = false;
				pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
				return "No se ha seleccionado una pasarela de pago, estado de la transacción fallido";
			}
			
			pPasarela.escribirPlato(idReserva,  namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", centinela);
			return "Error";
			
			
		} catch (NumberFormatException e2) {
			return "El saldo a pagar debe ser un numero. Intentelo de nuevo";
		}
	}
	

}
