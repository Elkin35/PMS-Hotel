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

public class UsuarioHuesped {
    private PMS pms=new PMS();
    
    public UsuarioHuesped(){
             
        try {
            pms.cargarDatos("pTar.txt", "pInv.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> consultar(LocalDate fechainicio, LocalDate fechafinal) {
   
        return pms.consultar(fechainicio, fechafinal);

    }

    public String crearRecerva(LocalDateTime fechaInicio, LocalDateTime fechaFin, float tarifaTotal, String idReserva,
                             Huesped infoCliente,  LocalDateTime fechareserva, String habitacion, String nombre, Long documento, String correo, Long celular, int numAcompanantes, ArrayList<String> tipoHabitacion){
        
        pms.cargar();
        LocalDate fecha1 = LocalDate.parse(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fecha2 = LocalDate.parse(fechaFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        float tarifasTotalC = pms.getTarifas(fecha1, fecha2, tipoHabitacion);

        Reserva reserva = new Reserva(fechaInicio, fechaFin, tarifasTotalC, idReserva, infoCliente,fechareserva, habitacion);
        reserva.cliente(nombre, documento, correo, celular, numAcompanantes);
   
        pms.guardarReseva(reserva);
        pms.guardar();

        
        return reserva.getidReserva();

        
    }



    public ArrayList<String> consultarEspecif(LocalDate fechaInicio2,LocalDate fechaFin2, String id) {
   
        return pms.ConsultarIdentificador(fechaInicio2, fechaFin2, id);

    }

    public void realizarPago(String idReserva, String namePasarela, String nameTitular, String numTarjeta, 
     String caducidad, String codSeguridad) throws Exception{
        pms.realizarPago(idReserva, namePasarela, nameTitular, numTarjeta, caducidad, codSeguridad);
        
    }

    public float consultar(String id) throws Exception{
        return pms.consultarPago(id);
    }
 


}
