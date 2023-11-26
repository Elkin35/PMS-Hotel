package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import presentacion.App;


public class PersistenciaServicio {
    
    public void leerServicios(HashMap<String, Float> mapaServiciosPersist){
        try {
            //System.out.println("Leyendo productosMenuComedor");
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/servicios.txt"));
            //System.out.println("Archivo leido");
            String linea;
            
            int i = 1;
            
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(":");
                String nombre = datos[0].trim();
                float precio = Float.parseFloat(datos[1].trim());
                
                mapaServiciosPersist.put(nombre, precio);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer los precios de los servicios " + e);
        }
    }

    public void actualizarServicios(String nombre, float precio, HashMap<String, Float> mapaServiciosPersist){
        try{
            if(mapaServiciosPersist.containsKey(nombre)){

                mapaServiciosPersist.put(nombre, precio);

                FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/servicios.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
   
                for(String llave: mapaServiciosPersist.keySet()){
                    bw.write(llave + ":" + mapaServiciosPersist.get(llave));
                    bw.newLine();
                }

                bw.close();
                fw.close();

            } else {
                System.out.println("El servicio no existe");
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar los servicios");
        }

    }

}
