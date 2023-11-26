package logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.lang.model.type.ArrayType;

public class Acompanate {
    private String nombre;
    private Long documento;
    private String correo;
    private Long celular;
    private LocalDate fechaDeNacimiento;
    
    public Acompanate(String nombre, Long documento, String correo, Long celular, LocalDate fechaDeNacimiento) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.celular = celular;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Long edad(){
        LocalDate fechaActual = LocalDate.now();
        long anios = ChronoUnit.YEARS.between(this.fechaDeNacimiento, fechaActual);
        return anios;

    }

    public String getNombre() {
        return nombre;
    }

    public Long getDocumento() {
        return documento;
    }

    public String getCorreo() {
        return correo;
    }

    public Long getCelular() {
        return celular;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    
}
