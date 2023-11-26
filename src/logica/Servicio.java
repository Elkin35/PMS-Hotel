package logica;

import java.time.LocalDate;

public abstract class Servicio {
    
    private int tarifaServicio;
    private String nombreServicio;
    protected String descripcion;
    private String idEmpleado;
    private LocalDate fecha;
    public String getIdEmpleado() {
        return idEmpleado;
    }

    protected boolean pagado; //Si el servicio ya fue pagado o no
    
    public boolean isPagado() {
        return pagado;
    }


    public Servicio(int tarifaServicio, String nombreServicio, String descripcion, String idEmpleado, LocalDate fecha) {
        this.tarifaServicio = tarifaServicio;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.idEmpleado= idEmpleado;
        this.fecha = fecha;
    }
    
    
    public int gettarifaServicio(){
        return tarifaServicio;
    }
    
    public LocalDate getFecha(){
    	return fecha;
    }

    public void settarifaServicio(int tarifaServicio){
        this.tarifaServicio = tarifaServicio;
    }
    
    public String getNombreServicio(){
        return nombreServicio;
    }
    
    public String getCaracteristicas(){
        return descripcion;
    }

    public void setPagado(boolean pagado){
        this.pagado = pagado;
    }
}