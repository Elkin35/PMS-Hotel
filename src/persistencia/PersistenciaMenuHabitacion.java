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

public class PersistenciaMenuHabitacion {
    public void leerPlatos(HashMap<String, Plato> mapaProductosMenuHabitacion){

        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/platosHabitacion.txt"));
            String linea;
            
            int i = 0;
            
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0].trim();
                float precio = Float.parseFloat(datos[1]);
                String fecha1 = datos[2].trim();
                LocalTime fechaInicio = LocalTime.parse(fecha1, DateTimeFormatter.ofPattern("HH:mm"));
                String fecha2 = datos[3].trim();
                LocalTime fechaFin = LocalTime.parse(fecha2, DateTimeFormatter.ofPattern("HH:mm"));
                int impuesto = Integer.parseInt(datos[4].trim());

                Plato plato = new Plato(nombre, precio, fechaInicio, fechaFin, impuesto);

                mapaProductosMenuHabitacion.put(nombre, plato);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("\nError al leer los productos del menu de habitacion: " + e);
        }

    }


    public void leerBebidas(HashMap<String, Bebida> mapaBebidasMenuHabitacion){

        try{
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/bebidasHabitacion.txt"));
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

                mapaBebidasMenuHabitacion.put(nombre, bebida);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("\nError al leer los productos del menu de habitacion");
        }
    }


    public void escribirPlato(String nombre, float precio, LocalTime fechaInicio, LocalTime fechaFin, int impuesto, HashMap<String, Plato> mapaProductosMenuHabitacion){

        try {
            FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/platosHabitacion.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            String fecha1 = fechaInicio.toString();
            String fecha2 = fechaFin.toString();

            if(!mapaProductosMenuHabitacion.containsKey(nombre)){

                bw.write(nombre + "," + precio + "," + fecha1 + "," +  fecha2 + "," + impuesto);
                bw.newLine();
                bw.close();
                fw.close();
    
                Plato plato = new Plato(nombre, precio, fechaInicio, fechaFin, impuesto);
                mapaProductosMenuHabitacion.put(nombre, plato);

            } else{
                System.out.println("\nEl plato ya existe");
            }


        } catch (Exception e) {
            System.out.println("\nError al crear el plato");
        }

    }

    public void escribirBebida(String nombre, float precio, LocalTime fechaInicio, LocalTime fechaFin, int impuesto, HashMap<String, Bebida> mapaBebidasMenuHabitacion ){
            
            try {
                FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/bebidasHabitacion.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
    
                String fecha1 = fechaInicio.toString();
                String fecha2 = fechaFin.toString();

                if(!mapaBebidasMenuHabitacion.containsKey(nombre)){

                    bw.write(nombre + "," + precio + "," + fecha1 + "," +  fecha2 + "," + impuesto);
                    bw.newLine();
                    bw.close();
                    fw.close();
        
                    Bebida bebida = new Bebida(nombre, precio, fechaInicio, fechaFin, impuesto);
                    mapaBebidasMenuHabitacion.put(nombre, bebida);
                } else{
                    System.out.println("\nLa bebida ya existe");
                }

            } catch (Exception e) {
                System.out.println("\nError al crear el usuario");
            }
    }

    public void eliminarPlato(String nombrePlato, HashMap<String, Plato> mapaProductosMenuHabitacion){
        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/platosHabitacion.txt"));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0];
                if(!nombre.equals(nombrePlato)){
                    sb.append(linea);
                    sb.append(System.lineSeparator());
                } else{
                    mapaProductosMenuHabitacion.remove(nombre);
                }
            }
            br.close();
            try (FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/platosHabitacion.txt", false)) {
                fw.write(sb.toString());
                fw.close();
            }

        } catch (Exception e) {
        }
    }

    public void eliminarBebida(String nombreBebida, HashMap<String, Bebida> mapaBebidasMenuHabitacion){
        try {
            BufferedReader br = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/bebidasHabitacion.txt"));
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(",");
                String nombre = datos[0];
                if(!nombre.equals(nombreBebida)){
                    sb.append(linea);
                    sb.append(System.lineSeparator());
                } else{
                    mapaBebidasMenuHabitacion.remove(nombre);
                    //System.out.println("El plato se ha eliminado correctamente.");
                }
            }
            br.close();
            try (FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/bebidasHabitacion.txt", false)) {
                fw.write(sb.toString());
                fw.close();
            }

        } catch (Exception e) {
        }
    }

}
