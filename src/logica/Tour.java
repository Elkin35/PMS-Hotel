package logica;

import java.time.LocalDate;

public class Tour extends Servicio {

    public Tour(int tarifa, String nombreServicio, String descripcion, String idEmpleado, LocalDate fecha) {
        super(tarifa, nombreServicio, descripcion, idEmpleado, fecha);
    }
    
}
