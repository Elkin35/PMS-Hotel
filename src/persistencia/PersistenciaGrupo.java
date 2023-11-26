package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import logica.Acompanate;
import logica.Grupo;
import logica.PMS;
import logica.Reserva;
import presentacion.App;

public class PersistenciaGrupo {


	public void escribir(String archivo, HashMap<String, Grupo> lista) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(App.path + "/entrega 3/reservas/"+archivo)));
			String cadena = "";
			for(String k : lista.keySet()) {
				Grupo reserva = lista.get(k); 
                ArrayList<Acompanate> persona = reserva.getAcompanantes();
                
                for (Acompanate a : persona){
                    cadena += k + ";";
                    cadena += a.getNombre() + ";";
                    cadena += a.getDocumento() + ";";
                    cadena += a.getCorreo() + ";";
                    cadena += a.getCelular() + ";";
                    cadena += a.getFechaDeNacimiento() + "\n";
                    
                }
            }
			bw.write(cadena);
			bw.close();
		} catch (Exception e) {
		}
		
	}

    
	public HashMap<String, Grupo> leer(String archivo, HashMap<String, Reserva> lista) {
		HashMap<String, Grupo> lista2 = new HashMap<String, Grupo>();
        HashMap<String, ArrayList<Acompanate>> temp = new HashMap<>();
        HashMap<String, ArrayList<Acompanate>> tempNinos = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      

        try {
			BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/reservas/"+archivo));
			String linea;
			while ((linea = br.readLine()) != null) {
				String datos[] = linea.split(";");
                if (datos[5]==null){
                    datos[5]="null";
                }
                if (temp.get(datos[0])!=null){
                    ArrayList<Acompanate> x = temp.get(datos[0]);
                  
                    if (datos[5].equals("null")){
                        Acompanate n = new Acompanate(
                            datos[1], 
                            Long.parseLong(datos[2]),
                            datos[3], 
                            Long.parseLong(datos[4]),
                            null);
                            x.add(n);
                    }else{
                        Acompanate r = new Acompanate(
                            datos[1], 
                            Long.parseLong(datos[2]),
                            datos[3], 
                            Long.parseLong(datos[4]),
                            LocalDate.parse(datos[5], formatter));
                            x.add(r);
                    }
                   

                
                    }else{
                        ArrayList <Acompanate> nuevo = new ArrayList<>();
                        temp.put(datos[0], nuevo);
                        if (datos[5].equals("null")){
                            Acompanate n = new Acompanate(
                                datos[1], 
                                Long.parseLong(datos[2]),
                                datos[3], 
                                Long.parseLong(datos[4]),
                                null);
                            
                        nuevo.add(n);

                        }else{
                            Acompanate r = new Acompanate(
                                datos[1], 
                                Long.parseLong(datos[2]),
                                datos[3], 
                                Long.parseLong(datos[4]),
                                LocalDate.parse(datos[5], formatter));
                                nuevo.add(r);
                        }
                   
                }			
			

            
		}
        br.close();
        } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
		}
        for (String key : temp.keySet()) {

            Grupo grupo = new Grupo(lista.get(key));
            grupo.setAcompanantes(temp.get(key));
            lista2.put(key, grupo);

        }
    
        
        		
		return lista2;
    }

	public void eliminar(String idReservaAEliminar){
		File archivo = new File(App.path + "/entrega 3/reservas/Registro.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			StringBuilder sb = new StringBuilder();
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if (!datos[0].equals(idReservaAEliminar)) {
					sb.append(linea);
					sb.append(System.lineSeparator());
				} 
			}
	
			// comprobar si han pasado más de 48 horas
				br.close();
				FileWriter fw = new FileWriter(archivo);
				fw.write(sb.toString());
				fw.close(); // eliminar la reserva
				System.out.println("\nSe ha eliminado la reserva exitosamente");
			
			
	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



    
}
