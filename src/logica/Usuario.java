package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Usuario {
    private String login;
    private String contrasena;
    private PMS pms=new PMS();

    public Usuario(String login, String contrasena) {
        this.login = login;
        this.contrasena = contrasena;
    }


}