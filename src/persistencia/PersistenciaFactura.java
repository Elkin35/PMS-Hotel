package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import logica.Acompanate;
import logica.Grupo;
import logica.Restaurante;
import logica.Servicio;
import logica.Spa;
import logica.Tour;
import presentacion.App;

public class PersistenciaFactura {


    public void escribir(String archivo, HashMap<String, ArrayList<Servicio>> lista) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(App.path + "/entrega 3/reservas/"+archivo)));
			String cadena = "";
			for(String k : lista.keySet()) {
				ArrayList<Servicio> servicios = lista.get(k); 
  
                for (Servicio a : servicios){
                    cadena += k + ";";
                    cadena += a.getNombreServicio() + ";";
                    cadena += a.gettarifaServicio() + ";";
                    cadena += a.getCaracteristicas() + ";";
                    cadena += a.getIdEmpleado() + ";";
                    cadena += a.isPagado() + ";";  
                    cadena += a.getFecha().toString() + "\n";
                }
            }
			bw.write(cadena);
			bw.close();
		} catch (Exception e) {
		}
		
	}


    
	public HashMap<String, ArrayList<Servicio>> leer(String archivo) {
		HashMap<String, ArrayList<Servicio>> lista2 = new HashMap<String, ArrayList<Servicio>>();


        try {
			BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/reservas/"+archivo));
			String linea;

			while(!(linea = br.readLine()).equals("")) {

				String datos[] = linea.split(";");

                    Servicio r = null;

                    if (datos[1].equals("tour")) {

                        r = new Tour(Integer.parseInt(datos[2]), datos[1], datos[4], datos[4], LocalDate.parse(datos[6]));
                        
                        r.setPagado(Boolean.parseBoolean(datos[5]));

                    } else if (datos[1].equals("spa")) {
                       
                        r = new Spa(Integer.parseInt(datos[2]), datos[1], datos[4], datos[4], LocalDate.parse(datos[6]));
                        r.setPagado(Boolean.parseBoolean(datos[5]));

                    } else if (datos[1].equals("restaurante")) {

                        r = new Spa(Integer.parseInt(datos[2]), datos[1], datos[4], datos[4], LocalDate.parse(datos[6]));
                        r.setPagado(Boolean.parseBoolean(datos[5]));
                    }               


                if (lista2.get(datos[0])!=null){
                    ArrayList<Servicio> x = lista2.get(datos[0]);
                    x.add(r);
      
                    }else{
                        ArrayList<Servicio> nueva = new ArrayList<>();
                        nueva.add(r);
                        lista2.put(datos[0], nueva);
                    }
                }			
		
            br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
   		
		return lista2;
    }   
}
