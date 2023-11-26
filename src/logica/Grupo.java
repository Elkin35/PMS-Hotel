package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Grupo {
    private Reserva reserva;
    private ArrayList<Acompanate> acompanantes;
    public ArrayList<Acompanate> getAcompanantes() {
        return acompanantes;
    }

  
    public Grupo(Reserva reserva) {
        this.acompanantes = new ArrayList<>();
        this.reserva = reserva;
        acompanantes.add(new Acompanate(reserva.getInfoCliente().getNombre(), reserva.getInfoCliente().getDocumento(), reserva.getInfoCliente().getCorreo(), reserva.getInfoCliente().getCelular(), null));
    }

    public void agregarAcompañante(String nombre, Long documento, String correo, Long celular, LocalDate fechaDeNacimiento){
        if (reserva.getInfoCliente().getNumAcompañantes() > acompanantes.size()){
            Acompanate nuevo = new Acompanate(nombre, documento, correo, celular, fechaDeNacimiento);
            acompanantes.add(nuevo);
          
        }else{
            System.out.println("\nYa se registraron todos los acompañantes asociados a esa reserva");
        }
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setAcompanantes(ArrayList<Acompanate> acompanantes) {
        this.acompanantes = acompanantes;
    }


}
