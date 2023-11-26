package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import logica.Habitacion;
import logica.HabitacionEstandar;
import logica.HabitacionSuite;
import logica.HabitacionSuiteDoble;
import logica.Inventario;

public class PersistenciaInventario {

	// Methods

	@SuppressWarnings("static-access")
	public HashMap<String, ArrayList<Habitacion>> leer(String archivo, Inventario inv) throws FileNotFoundException {

		File persistentFile = new File(archivo);

		Scanner Reader = new Scanner(persistentFile);

		ArrayList<Habitacion> estandar = inv.estandar;
		ArrayList<Habitacion> suite = inv.suite;
		ArrayList<Habitacion> suiteDoble = inv.suiteDoble;
		HashMap <String, ArrayList<Habitacion>> inventario = new HashMap<String, ArrayList<Habitacion>>();

		while (Reader.hasNextLine()) {
			String line = Reader.nextLine();

			if (line.equals("Estandar")) {
				while (Reader.hasNextLine()) {

					String lineEstandar = Reader.nextLine();

					if (lineEstandar.equals("Suite")) {

						while (Reader.hasNextLine()) {
							String lineSuite = Reader.nextLine();

							if (lineSuite.equals("Suite doble")) {

								while (Reader.hasNextLine()) {
									String lineDoble = Reader.nextLine();
									String[] info = lineDoble.split(";");
									suiteDoble.add(new HabitacionSuiteDoble(Integer.parseInt(info[0]), info[1],
											Integer.parseInt(info[2]), 
											
											// nuevo (tamaño y voltaje)
											
											Integer.parseInt(info[3]), Integer.parseInt(info[4]),
											
											Boolean.valueOf(info[5]),
											Boolean.valueOf(info[6]), Boolean.valueOf(info[7]), 
											
											// nuevo (calefacción, aireAc, TV, cafetera, hipoalergénico, plancha
											// secador, usb A, usb C, desayuno
											
											Boolean.valueOf(info[8]), Boolean.valueOf(info[9]), Boolean.valueOf(info[10]), Boolean.valueOf(info[11]), Boolean.valueOf(info[12]), Boolean.valueOf(info[13]), 
											Boolean.valueOf(info[14]), Boolean.valueOf(info[15]), Boolean.valueOf(info[16]), Boolean.valueOf(info[17]), 
											
											info[18],
											Boolean.valueOf(info[19]), Boolean.valueOf(info[20]),
											Integer.parseInt(info[21])));
								}
							}

							else {
								String[] info = lineSuite.split(";");
								suite.add(new HabitacionSuite(Integer.parseInt(info[0]), info[1],
										Integer.parseInt(info[2]), 
										
										// nuevo (tamaño y voltaje)
										
										Integer.parseInt(info[3]), Integer.parseInt(info[4]),
										
										Boolean.valueOf(info[5]),
										Boolean.valueOf(info[6]), Boolean.valueOf(info[7]), 
										
										// nuevo (calefacción, aireAc, TV, cafetera, hipoalergénico, plancha
										// secador, usb A, usb C, desayuno
										
										Boolean.valueOf(info[8]), Boolean.valueOf(info[9]), Boolean.valueOf(info[10]), Boolean.valueOf(info[11]), Boolean.valueOf(info[12]), Boolean.valueOf(info[13]), 
										Boolean.valueOf(info[14]), Boolean.valueOf(info[15]), Boolean.valueOf(info[16]), Boolean.valueOf(info[17]), 
										
										info[18],
										Boolean.valueOf(info[19]), Boolean.valueOf(info[20]),
										Integer.parseInt(info[21])));
							}
						}
					}

					else {
						String[] info = lineEstandar.split(";");
						estandar.add(new HabitacionEstandar(Integer.parseInt(info[0]), info[1],
								Integer.parseInt(info[2]), 
								
								// nuevo (tamaño y voltaje)
								
								Integer.parseInt(info[3]), Integer.parseInt(info[4]),
								
								Boolean.valueOf(info[5]),
								Boolean.valueOf(info[6]), Boolean.valueOf(info[7]), 
								
								// nuevo (calefacción, aireAc, TV, cafetera, hipoalergénico, plancha
								// secador, usb A, usb C, desayuno
								
								Boolean.valueOf(info[8]), Boolean.valueOf(info[9]), Boolean.valueOf(info[10]), Boolean.valueOf(info[11]), Boolean.valueOf(info[12]), Boolean.valueOf(info[13]), 
								Boolean.valueOf(info[14]), Boolean.valueOf(info[15]), Boolean.valueOf(info[16]), Boolean.valueOf(info[17]), 
								
								info[18],
								Boolean.valueOf(info[19]), Boolean.valueOf(info[20]),
								Integer.parseInt(info[21])));
					}
				}
			}
		}
		Reader.close();
		inventario.put("Estandar", estandar);
		inventario.put("Suite", suite);
		inventario.put("Suite doble", suiteDoble);
		return inventario;
	}

	public void escribir(File archivo, Inventario inventario) throws IOException {

		archivo.createNewFile();
		FileWriter writer = new FileWriter(archivo, false);
		ArrayList<Habitacion> estandar = inventario.estandar;
		ArrayList<Habitacion> suite = inventario.suite;
		ArrayList<Habitacion> suiteDoble = inventario.suiteDoble;
		String text = "";

		text += "Estandar\n";

		for (Habitacion habitacion : estandar) {

			text += habitacion.getIdentificador();
			text += ";";
			text += habitacion.getUbicacion();
			text += ";";
			text += habitacion.getCapacidad();
			text += ";";
			//nuevo
			text += habitacion.getTamanio();
			text += ";";
			text += habitacion.getVoltaje();
			text += ";";
			
			text += habitacion.haveBalcon();
			text += ";";
			text += habitacion.haveVista();
			text += ";";
			text += habitacion.haveCocinaIntegrada();
			text += ";";
			// nuevo
			text += habitacion.isCalefaccion();
			text += ";";
			text += habitacion.isAireAc();
			text += ";";
			text += habitacion.isTV();
			text += ";";
			text += habitacion.isCafetera();
			text += ";";
			text += habitacion.isHipoalergenico();
			text += ";";
			text += habitacion.isPlancha();
			text += ";";
			text += habitacion.isSecador();
			text += ";";
			text += habitacion.isUsb_a();
			text += ";";
			text += habitacion.isUsb_c();
			text += ";";
			text += habitacion.isDesayuno();
			text += ";";
			
			text += "Estandar";
			text += ";";
			text += habitacion.haveDisponibilidad();
			text += ";";
			text += habitacion.getNinos();
			text += ";";
			text += habitacion.getCapacidadNinos();
			text += "\n";
		}
		;
		text += "Suite\n";
		for (Habitacion habitacion : suite) {
			text += habitacion.getIdentificador();
			text += ";";
			text += habitacion.getUbicacion();
			text += ";";
			text += habitacion.getCapacidad();
			text += ";";
			//nuevo
			text += habitacion.getTamanio();
			text += ";";
			text += habitacion.getVoltaje();
			text += ";";
			
			text += habitacion.haveBalcon();
			text += ";";
			text += habitacion.haveVista();
			text += ";";
			text += habitacion.haveCocinaIntegrada();
			text += ";";
			// nuevo
			text += habitacion.isCalefaccion();
			text += ";";
			text += habitacion.isAireAc();
			text += ";";
			text += habitacion.isTV();
			text += ";";
			text += habitacion.isCafetera();
			text += ";";
			text += habitacion.isHipoalergenico();
			text += ";";
			text += habitacion.isPlancha();
			text += ";";
			text += habitacion.isSecador();
			text += ";";
			text += habitacion.isUsb_a();
			text += ";";
			text += habitacion.isUsb_c();
			text += ";";
			text += habitacion.isDesayuno();
			text += ";";
			
			text += "Suite";
			text += ";";
			text += habitacion.haveDisponibilidad();
			text += ";";
			text += habitacion.getNinos();
			text += ";";
			text += habitacion.getCapacidadNinos();
			text += "\n";
		}
		;
		text += "Suite doble\n";
		for (Habitacion habitacion : suiteDoble) {
			text += habitacion.getIdentificador();
			text += ";";
			text += habitacion.getUbicacion();
			text += ";";
			text += habitacion.getCapacidad();
			text += ";";
			//nuevo
			text += habitacion.getTamanio();
			text += ";";
			text += habitacion.getVoltaje();
			text += ";";
			
			text += habitacion.haveBalcon();
			text += ";";
			text += habitacion.haveVista();
			text += ";";
			text += habitacion.haveCocinaIntegrada();
			text += ";";
			// nuevo
			text += habitacion.isCalefaccion();
			text += ";";
			text += habitacion.isAireAc();
			text += ";";
			text += habitacion.isTV();
			text += ";";
			text += habitacion.isCafetera();
			text += ";";
			text += habitacion.isHipoalergenico();
			text += ";";
			text += habitacion.isPlancha();
			text += ";";
			text += habitacion.isSecador();
			text += ";";
			text += habitacion.isUsb_a();
			text += ";";
			text += habitacion.isUsb_c();
			text += ";";
			text += habitacion.isDesayuno();
			text += ";";
						
			text += "Suite doble";
			text += ";";
			text += habitacion.haveDisponibilidad();
			text += ";";
			text += habitacion.getNinos();
			text += ";";
			text += habitacion.getCapacidadNinos();
			text += "\n";
		}
		;
		writer.write(text);
		writer.close();
	}

}
