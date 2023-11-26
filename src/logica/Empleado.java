package logica;

public abstract class Empleado extends Usuario {

    protected String idEmpleado;

    public Empleado(String login, String contrasena) {
        super(login, contrasena);
    }

    protected abstract void agregarServicioFactura(String idFactura, int saldoAPagar, String servicio, String idEmpleado, boolean pagar);

    protected boolean registrarPago(int dinero, Servicio servicio){
        if(dinero >= servicio.gettarifaServicio()){
            servicio.setPagado(true);
            return true;
        }else{
            System.out.println("No se pudo realizar el pago. El dinero no es suficiente");
            return false;
        }
    }
    }