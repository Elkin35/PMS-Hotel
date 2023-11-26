package persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import logica.Bebida;
import logica.Plato;
import logica.Producto;
import presentacion.App;


public class PersistenciaMenuComedor {

    public void leerPlatos(HashMap<String, Plato> productosMenuComedor){
        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/platosComedor.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0].trim();
                float precio = Float.parseFloat(datos[1].trim());
                String fecha1 = datos[2].trim();
                LocalTime fechaInicio = LocalTime.parse(fecha1, DateTimeFormatter.ofPattern("HH:mm"));
                String fecha2 = datos[3].trim();
                LocalTime fechaFin = LocalTime.parse(fecha2, DateTimeFormatter.ofPattern("HH:mm"));
                int impuesto = Integer.parseInt(datos[4].trim());

                Plato plato = new Plato(nombre, precio, fechaInicio, fechaFin, impuesto);

                productosMenuComedor.put(nombre, plato);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer los productos del menu de comedor");
        }

    }


    public void leerBebidas(HashMap<String, Bebida> bebidasMenuComedor){
        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/bebidasComedor.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0].trim();
                float precio = Float.parseFloat(datos[1].trim());
                String fecha1 = datos[2].trim();
                LocalTime fechaInicio = LocalTime.parse(fecha1, DateTimeFormatter.ofPattern("HH:mm"));
                String fecha2 = datos[3].trim();
                LocalTime fechaFin = LocalTime.parse(fecha2, DateTimeFormatter.ofPattern("HH:mm"));
                int impuesto = Integer.parseInt(datos[4].trim());

                Bebida bebida = new Bebida(nombre, precio, fechaInicio, fechaFin, impuesto);

                bebidasMenuComedor.put(nombre, bebida);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer las bebidas del menu de comedor");
        }

    }

    public void escribirPlato(String nombre, float precio, LocalTime fechaInicio, LocalTime fechaFin, int impuesto, HashMap<String, Plato> mapaBebidasMenuComedor){

        try {
            FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/platosComedor.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            String fecha1 = fechaInicio.toString();
            String fecha2 = fechaFin.toString();

            if(!mapaBebidasMenuComedor.containsKey(nombre)){

                bw.write(nombre + "," + precio + "," + fecha1 + "," +  fecha2 + "," + impuesto);
                bw.newLine();
                bw.close();
                fw.close();
    
                Plato plato = new Plato(nombre, precio, fechaInicio, fechaFin, impuesto);
                mapaBebidasMenuComedor.put(nombre, plato);

            } else{
            }


        } catch (Exception e) {
            System.out.println("\nError al crear el plato");
        }

    }

    public void escribirBebida(String nombre, float precio, LocalTime fechaInicio, LocalTime fechaFin, int impuesto, HashMap<String, Bebida> mapaBebidasMenuComedor){
            
            try {
                FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/bebidasComedor.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
    
                String fecha1 = fechaInicio.toString();
                String fecha2 = fechaFin.toString();

                if(!mapaBebidasMenuComedor.containsKey(nombre)){

                    bw.write(nombre + "," + precio + "," + fecha1 + "," +  fecha2 + "," + impuesto);
                    bw.newLine();
                    bw.close();
                    fw.close();
        
                    Bebida bebida = new Bebida(nombre, precio, fechaInicio, fechaFin, impuesto);
                    mapaBebidasMenuComedor.put(nombre, bebida);
                } else{
                }

            } catch (Exception e) {
                System.out.println("\nError al crear la bebida");
            }
    }

    public void eliminarPlato(String nombrePlato, HashMap<String, Plato> mapaProductosMenuComedor){
        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/platosComedor.txt"));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0];
                if(!nombre.equals(nombrePlato)){
                    sb.append(linea);
                    sb.append(System.lineSeparator());
                } else{
                    mapaProductosMenuComedor.remove(nombre);
                }
            }
            br.close();
            try (FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/platosComedor.txt", false)) {
                fw.write(sb.toString());
                fw.close();
            }

        } catch (Exception e) {
        }
    }

    public void eliminarBebida(String nombreBebida, HashMap<String, Bebida> mapaBebidasMenuComedor){
        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/bebidasComedor.txt"));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0];
                if(!nombre.equals(nombreBebida)){
                    sb.append(linea);
                    sb.append(System.lineSeparator());
                } else{
                    mapaBebidasMenuComedor.remove(nombre);
                }
            }
            br.close();
            try (FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/bebidasComedor.txt", false)) {
                fw.write(sb.toString());
                fw.close();
            }

        } catch (Exception e) {
        }
    }

}
