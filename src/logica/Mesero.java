package logica;

public class Mesero extends Empleado {

    private PMS hotel;

    public Mesero(String login, String contrasena) {
        super(login, contrasena);
        
        this.hotel = new PMS();
    }

    @Override
    protected void agregarServicioFactura(String idReserva, int saldoAPagar, String servicio, String idEmpleado, boolean pagar) {
        Servicio servicioMesero = new Restaurante(saldoAPagar, servicio, idEmpleado, idEmpleado);
        if(pagar == true){
            servicioMesero.setPagado(true);
        } else {
            servicioMesero.setPagado(false);
        }
        hotel.actualizarMapaServicios(idReserva, servicioMesero);
        hotel.actualizarMapaEmpleadosServicios(idEmpleado, servicioMesero);
    }
    
}
    
