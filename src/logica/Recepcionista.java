package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import persistencia.PersistenciaReserva;

public class Recepcionista {

    public List<List<Object>> consultarReservas() {
        List<List<Object>> reservas = new ArrayList<>();
        String fileName = "./entrega 3/reservas/reservass.txt";
        String line;
    
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
    
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                List<Object> reserva = new ArrayList<>();
                reserva.add(data[0]);
                reserva.add(data[1]);
                reserva.add(data[2]);
                reserva.add(data[3]);
                reserva.add(data[4]);
                reserva.add(data[5]);
                reserva.add(data[6]);
                reserva.add(data[7]);
                reserva.add(data[8]);
                reserva.add(data[9]);
                reserva.add(data[10]);
                reservas.add(reserva);
            }
    
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return reservas;
    }
    



    public String consultarFactura(String idreserva){
        return Factura.generarFactura(idreserva);
    }

    public Reserva crearRecerva(LocalDateTime fechaInicio, LocalDateTime fechaFin, float tarifaTotal, String idReserva,
                             Huesped infoCliente,  LocalDateTime fechareserva, String habitacion, String nombre, Long documento, String correo, Long celular, int numAcompanantes, ArrayList<String> tipoHabitacion){
        
        Reserva reserva = new Reserva(fechaInicio, fechaFin, tarifaTotal, idReserva, infoCliente,fechareserva, habitacion);
        reserva.cliente(nombre, documento, correo, celular, numAcompanantes);
  
        
        return reserva;
    }
    public void registro(String nombre, Long documento, String correo, Long celular, LocalDate fechaDeNacimiento, Grupo grupo){
        grupo.agregarAcompañante(nombre, documento, correo, celular, fechaDeNacimiento);
        
    }
    public ArrayList<String> consultarReserva(String reserva){
        HashMap<String, Reserva> reservas = PMS.getReservas();
        Reserva reservaBuscada = reservas.get(reserva);
        ArrayList<String> resultado = new ArrayList<String>();
        
        if (reservaBuscada != null){
            resultado.add(reservaBuscada.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            resultado.add(reservaBuscada.getFechaFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            resultado.add(reservaBuscada.getInfoCliente().getNombre());
            resultado.add(Integer.toString(reservaBuscada.getInfoCliente().getNumAcompañantes()));
            resultado.add(reservaBuscada.getHabitacion());
         
        }
        
        return resultado;
    }
    

    public boolean cancelar(String idreserva) throws FileNotFoundException{
        boolean x = PMS.getArchivoReservas().eliminar(idreserva);
        return x;
    }
    public void eliminarHuespedes(String idreserva) throws FileNotFoundException{
        PMS.getArchivoGrupos().eliminar(idreserva);
    }

    

    
        public void liberar(String habitacionesApartadas){

            String[] ArrayHabitaciones = habitacionesApartadas.split(", ");

            for (String habitaciones : ArrayHabitaciones) {
                String[] ArrayInfoHabitacion = habitaciones.split("-");
             
                Habitacion seleccionada = Inventario.getHabitacion(Integer.parseInt(ArrayInfoHabitacion[0]),
                        ArrayInfoHabitacion[1]);
                seleccionada.changeDisponibilidad(true);
            }
        }

        public void reservar(String habitacionesApartadas, LocalDateTime fechaInico, LocalDateTime fechaFinal){
            LocalDate fecha1 = LocalDate.parse(fechaInico.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate fecha2 = LocalDate.parse(fechaFinal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    String[] ArrayHabitaciones = habitacionesApartadas.split(", ");

    ArrayList<LocalDate> listaFechas = new ArrayList<>();
    for (LocalDate fecha = fecha1; !fecha.isAfter(fecha2); fecha = fecha.plusDays(1)) {
        listaFechas.add(fecha);
    }

    for (String habitaciones : ArrayHabitaciones) {
        for (LocalDate fechas : listaFechas) {
            if (PMS.getReservaHabitaciones().get(habitaciones) == null) {
                PMS.getReservaHabitaciones().put(habitaciones, new ArrayList<LocalDate>());
                PMS.getReservaHabitaciones().get(habitaciones).add(fechas);
            } else {
                PMS.getReservaHabitaciones().get(habitaciones).add(fechas);
            }

        }
    }
        }
            

        
      
        public List<List<Object>> consultarHuespedes(){
            String fileName = "./entrega 3/reservas/Registro.txt";
            String line;
            List<List<Object>> datos = new ArrayList<>();
            
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                
                // Agregar encabezado a la lista

            
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    
                    if (data[5].equals("null")){
                        data[5] = "Huesped";
                    }
        
                    // Agregar fila de datos a la lista
                    List<Object> fila = new ArrayList<>();
                    fila.add(data[0]);
                    fila.add(data[1]);
                    fila.add(data[2]);
                    fila.add(data[3]);
                    fila.add(data[4]);
                    fila.add(data[5]);
                    datos.add(fila);
                }
            
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
           
            }
            return datos;
        }

        
    }



