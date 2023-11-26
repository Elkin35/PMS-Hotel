package logica;

import java.time.LocalDate;

public class GuiaTuristico extends Empleado {

    private PMS hotel;

    public GuiaTuristico(String login, String contrasena) {
        super(login, contrasena);
        
        this.hotel = new PMS();
    }

    @Override
    protected void agregarServicioFactura(String idReserva, int saldoAPagar, String servicio, String idEmpleado, boolean pagar) {
        Servicio servicioTour = new Tour(saldoAPagar, servicio, idEmpleado, idEmpleado, LocalDate.now());
        if(pagar == true){
            servicioTour.setPagado(true);
        } else {
            servicioTour.setPagado(false);
        }
        hotel.actualizarMapaServicios(idReserva, servicioTour);
        hotel.actualizarMapaEmpleadosServicios(idEmpleado, servicioTour);
    }
    
}
