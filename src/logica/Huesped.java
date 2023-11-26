package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Huesped {
    private String nombre;
    private Long documento;
    private String correo;
    private Long celular;
    private int numAcompañantes;


    public Huesped(String nombre, Long documento, String correo, Long celular, int numAcompañantes) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.celular = celular;
        this.numAcompañantes = numAcompañantes;
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

    public int getNumAcompañantes() {
        return numAcompañantes;
    }

}
