package logica;

import java.time.LocalDate;
import java.util.*;

public class Spa extends Servicio {

    public Spa(int tarifa, String nombreServicio, String descripcion, String idEmpleado, LocalDate fecha) {
        super(tarifa, nombreServicio, descripcion, idEmpleado, fecha);
    }
    
}
