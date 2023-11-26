package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import logica.Reserva;
import presentacion.App;

public class PersistenciaHabitacionReservada {

    public void escribir(String archivo, HashMap<String, ArrayList<LocalDate>> lista) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(App.path + "/entrega 3/reservas/"+archivo)));
			String cadena = "";
			for(String k : lista.keySet()) {
				ArrayList<LocalDate> fechas = lista.get(k); 
				if (fechas.size()!=0){
					cadena += k +";";
					String sub ="";
					for (LocalDate fecha:fechas){
						sub += fecha+",";
					}
					cadena += sub.substring(0, sub.length() - ("-").length());
					cadena+= "\n";
				}
            }	
            
			bw.write(cadena);
			bw.close();
		} catch (Exception e) {
		}
		
	}

    

	public HashMap<String, ArrayList<LocalDate>> leer(String archivo) {
		HashMap<String, ArrayList<LocalDate>> lista = new HashMap<String, ArrayList<LocalDate>>();
      
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
			BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/reservas/"+archivo));
			String linea;
			while(!(linea=br.readLine()).equals("")) {

				String datos[] = linea.split(";");
                String f[] = datos[1].split(",");

                ArrayList<LocalDate> hab = new ArrayList();

                for (String fecha:f){

    
                    LocalDate fechaCreacion = LocalDate.parse(fecha, formatter);
                    hab.add(fechaCreacion);
                  
                }


                lista.put(datos[0], hab);
                
			}
			br.close();			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
    }
}
