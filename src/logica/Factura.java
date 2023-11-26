package logica;

import java.util.ArrayList;


import java.util.ArrayList;

public class Factura {
    private ArrayList<Grupo> historial;
    private int numeroFactura;
    private int valorImpuestos;
    private int valorTotal;
    
    private PMS hotel = new PMS();

    static String generarFactura(String idReserva){

        ArrayList<Servicio> servicios= PMS.getMapaServicios().get(idReserva);

        if (servicios!=null){
            String x = "\n\n---------------------FACTURA---------------------\n\n";
            x += "ID de la reserva:  " + idReserva;
            x += "\n";
            x += "Nombre del huesped:  " + PMS.getReservas().get(idReserva).getInfoCliente().getNombre() + "\n";
            x += "\n";
       
            x += "Precio de la tarifa:  " + PMS.getReservas().get(idReserva).getTarifaTotal() + "\n";
            x += "\n";
            x += String.format("%-20s%-20s%-15s%-20s\n",
            "Nombre del servicio", "Tarifa", "Empleado", "Pago");
            
            float total = 0;
            total += PMS.getReservas().get(idReserva).getTarifaTotal();

            for (Servicio servicio: servicios){
                x += String.format("%-20s%-20s%-15s%-20s\n",
                        servicio.getNombreServicio(), servicio.gettarifaServicio(), servicio.getIdEmpleado(), servicio.isPagado());
                        if (servicio.isPagado()==false){
                            total+=servicio.gettarifaServicio();
                        }
           
            }
            x += "\n";
            x += "Precio neto  .......................  $" + total + "\n";
            x += "Precio IVA  ........................  $" + total*0.19 + "\n";
            x += "Precio total  ......................  $" + (total+(total*0.19));

            x += "\n";
            
            return x; 
        }else{
            return "\nNo hay factura asociada a este ID\n";
        }
    
}
    
    // nuevo
    public float getPrecioNeto(String idReserva) {
    	
    	ArrayList<Servicio> servicios= PMS.getMapaServicios().get(idReserva);
    	    	
    	Reserva reserva = hotel.buscarReservas(idReserva);
    	
    	float precioNeto = reserva.getTarifaTotal();
    	
    	if (servicios!=null){
    		for (Servicio servicio: servicios){
                if (servicio.isPagado()==false){
                	precioNeto+=servicio.gettarifaServicio();
                }
           
            }
    		
    	}
    	
    	return precioNeto;
    }
    
    // nuevo
    public float getIva(String idReserva) {
    	float precioNeto = getPrecioNeto(idReserva);
    	
    	return (float) (precioNeto*0.19);
    }

    
}