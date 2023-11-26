package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logica.Habitacion;
import logica.Inventario;
import persistencia.PersistenciaReserva;

public class Reserva {
    private String habitacion;
    private LocalDateTime fechareserva;
    private LocalDateTime fechaInicio;
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    private LocalDateTime fechaFin;
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    private float tarifaTotal;
    public void setTarifaTotal(float tarifaTotal) {
        this.tarifaTotal = tarifaTotal;
    }

    private String idReserva;
    public String getidReserva() {
        return idReserva;
    }

    private Huesped infoCliente;


    public Reserva(LocalDateTime fechaInicio, LocalDateTime fechaFin, float tarifaTotal, String idReserva,
            Huesped infoCliente,  LocalDateTime fechareserva, String habitacion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tarifaTotal = tarifaTotal;
        if (idReserva == null){
            this.idReserva = calcularidReserva();
        }else{
            this.idReserva=idReserva;
        }
        this.infoCliente =  null;
        this.habitacion = habitacion;
        this.fechareserva = fechareserva;
        
        ;
    }

    private String calcularidReserva() {
        return Integer.toString(PMS.getReservas().size()+100);
        
    }

    public float getTarifaTotal() {


        return tarifaTotal;
    }

    public void cliente(String nombre, Long documento, String correo, Long celular, int numAcompañantes){
        infoCliente = new Huesped(nombre, documento, correo, celular, numAcompañantes);
    }


    public ArrayList<Habitacion> listaHabitaciones(){

        String[] ArrayHabitaciones = this.habitacion.split(", ");
        ArrayList<Habitacion> habitacionesLista = new ArrayList<>();
        for (String habitaciones:ArrayHabitaciones){
            String[] ArrayInfoHabitacion = habitaciones.split("-");
            Habitacion seleccionada = Inventario.getHabitacion(Integer.parseInt(ArrayInfoHabitacion[0]), ArrayInfoHabitacion[1]);
            habitacionesLista.add(seleccionada);
        }
        return habitacionesLista;

    }


    public String getHabitacion() {
        return habitacion;
    }

    public LocalDateTime getFechareserva() {
        return fechareserva;
    }




    public Huesped getInfoCliente() {
        return infoCliente;
    }

    public ArrayList<LocalDate> ListaDias(){
                LocalDate fecha1 = LocalDate.parse(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fecha2 = LocalDate.parse(fechaFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        ArrayList<LocalDate> listaFechas = new ArrayList<>();
        for (LocalDate fecha = fecha1; !fecha.isAfter(fecha2); fecha = fecha.plusDays(1)) {
            listaFechas.add(fecha);
}
        return listaFechas;
    }


}
