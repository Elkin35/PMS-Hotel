package logica;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Producto {
    
    protected String nombre;
    protected float precio;
    protected LocalTime horaInicio;
    protected LocalTime horaFin;
    protected int impuesto;

    public Producto(String nombre, float precio, LocalTime horaInicio, LocalTime horaFin, int impuesto) {
        this.nombre = nombre;
        this.precio = precio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.impuesto = impuesto;
    }

    public abstract float getPrecio();

    public abstract String getNombre();

    public abstract void setNombre(String nuevoNombre);

    public abstract void setPrecio(float precio);

    public abstract void setHoraInicio(LocalTime horaInicio);

    public abstract LocalTime getHoraInicio();

    public abstract void setHoraFin(LocalTime horaFin);

    public abstract void setImpuesto(int impuesto);

    public abstract LocalTime getHoraFin();

    public abstract int getImpuesto();

}
