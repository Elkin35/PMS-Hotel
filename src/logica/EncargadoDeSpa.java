package logica;

import java.time.LocalDate;

public class EncargadoDeSpa extends Empleado {

    private PMS hotel;

    public EncargadoDeSpa(String login, String contrasena) {
        super(login, contrasena);
        
        this.hotel = new PMS();
    }

    @Override
    protected void agregarServicioFactura(String idReserva, int saldoAPagar, String servicio, String idEmpleado, boolean pagar) {
        Servicio servicioSpa = new Tour(saldoAPagar, servicio, idEmpleado, idEmpleado, LocalDate.now());
        if(pagar == true){
            servicioSpa.setPagado(true);
        } else {
            servicioSpa.setPagado(false);
        }
        hotel.actualizarMapaServicios(idReserva, servicioSpa);
        hotel.actualizarMapaEmpleadosServicios(idEmpleado, servicioSpa);
    }

}
    

