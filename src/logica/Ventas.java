package logica;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Ventas {
	
	public static Map<String, Integer> mapaVentas = new HashMap<>();
	
	public Ventas(String archivo) throws Exception {
		this.cargar(archivo);
	}
	
	// guardar y cargar ventas
	
	public void guardar(String archivo) throws Exception {
		PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
		for (Map.Entry<String, Integer> entry : mapaVentas.entrySet()) {
			escritor.write(entry.getKey() + ";" + entry.getValue() + "\n");
		}
		escritor.close();
	}
	
	public Map<String, Integer> cargar(String archivo) throws Exception {
		
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        		String linea;
        while ((linea = lector.readLine()) != null) {
        	String[] partes = linea.split(";");
        	if (partes.length == 2) {
        		String llave = partes[0];
        		int valor = Integer.parseInt(partes[1]);
        		mapaVentas.put(llave, valor);
        	}
        }
        lector.close();
        
        
        return mapaVentas;
    }
	
	public static HashMap<String, Integer> hacerMapaPrecios() {
		HashMap<String, Integer> mapaPrecios = new HashMap<>();
		mapaPrecios.putAll(Ventas.mapaVentas);
		PMS temporal = new PMS();
		temporal.cargarMenus();
		HashMap<String, Plato> MenuComedor = temporal.getPlatosMenuComedor();
		HashMap<String, Bebida> MenuComedorBebidas = temporal.getBebidasMenuComedor();
		HashMap<String, Plato> MenuHabitacion = temporal.getPlatosMenuHabitacion();
		HashMap<String, Bebida> MenuHabitacionBebidas = temporal.getBebidasMenuHabitacion();
		
		for (String llave : mapaPrecios.keySet()) {
			int cantidad = mapaVentas.get(llave);
			float precio = 1;
			if (MenuComedor.containsKey(llave)) {
				precio = MenuComedor.get(llave).getPrecio();
			}
			else if (MenuComedorBebidas.containsKey(llave)) {
				precio = MenuComedorBebidas.get(llave).getPrecio();
			}
			else if (MenuHabitacion.containsKey(llave)) {
				precio = MenuHabitacion.get(llave).getPrecio();
			}
			else if (MenuHabitacionBebidas.containsKey(llave)) {
				precio = MenuHabitacionBebidas.get(llave).getPrecio();
			}
			mapaPrecios.put(llave, (int) (cantidad * precio));
		}
		
		return mapaPrecios;
		
	}
	
}
