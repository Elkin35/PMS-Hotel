package logica;

import java.time.LocalTime;

public class Plato extends Producto {

    public Plato(String nombre, float precio, LocalTime horaInicio, LocalTime horaFin, int impuesto) {
        super(nombre, precio, horaInicio, horaFin, impuesto);
    }
    
    public float getPrecio() {
        return this.precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public LocalTime getHoraFin() {
        return this.horaFin;
    }

    public int getImpuesto() {
        return this.impuesto;
    }

}
