package persistencia;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.io.*;
import logica.Huesped;
import logica.Reserva;
import presentacion.App;

public class PersistenciaReserva {

	public void escribir(String archivo, HashMap<String, Reserva> lista) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(App.path + "/entrega 3/reservas/"+archivo)));
			String cadena = "";
			for(String k : lista.keySet()) {
				Reserva reserva = lista.get(k); 
				cadena += reserva.getFechaInicio() + ";";
				cadena += reserva.getFechaFin() + ";";
                cadena += reserva.getTarifaTotal() + ";";
                cadena += reserva.getidReserva() + ";";
                cadena += reserva.getInfoCliente().getNombre() + ";";
                cadena += reserva.getInfoCliente().getDocumento() + ";";
                cadena += reserva.getInfoCliente().getCorreo() + ";";
                cadena += reserva.getInfoCliente().getCelular() + ";";
                cadena += reserva.getInfoCliente().getNumAcompañantes() + ";";
                cadena += reserva.getHabitacion() + ";";
                cadena += reserva.getFechareserva() + "\n";	
            }	
			bw.write(cadena);
			bw.close();
		} catch (Exception e) {
		}
		
	}

    

	public HashMap<String, Reserva> leer(String archivo) {
		HashMap<String, Reserva> lista = new HashMap<String, Reserva>();
      
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
			BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/reservas/"+archivo));
			String linea;
			while(!(linea=br.readLine()).equals("")) {
				String datos[] = linea.split(";");
                
                Reserva r = new Reserva(
                    LocalDateTime.parse(datos[0], formatter),
                    LocalDateTime.parse(datos[1], formatter), 
                    Float.parseFloat(datos[2]),
                    datos[3], 
                    null, 
                    LocalDateTime.parse(datos[10], formatter), 
                    datos[9]);
                r.cliente(datos[4], Long.parseLong(datos[5]), datos[6], Long.parseLong(datos[7]), Integer.parseInt(datos[8]));
                    String x =datos[3];
				lista.put(x, r);				
			}
			br.close();			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
    }



	public boolean eliminar(String idReservaAEliminar){
		File archivo = new File(App.path + "/entrega 3/reservas/reservass.txt");
		Boolean x = true;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			StringBuilder sb = new StringBuilder();
			String linea;
			long horasDesdeCreacion = 0; // declarar la variable fuera del bucle
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if (!datos[3].equals(idReservaAEliminar)) {
					sb.append(linea);
					sb.append(System.lineSeparator());
				} else {
					LocalDateTime fechaCreacion = LocalDateTime.parse(datos[10], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
					horasDesdeCreacion = ChronoUnit.HOURS.between(fechaCreacion, LocalDateTime.now());
				}
			}
	
			if (horasDesdeCreacion <= 48) { // comprobar si han pasado más de 48 horas
				br.close();
				FileWriter fw = new FileWriter(archivo);
				fw.write(sb.toString());
				fw.close(); // eliminar la reserva
			
			}
			else{
				x=false;
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x;

	}
}
