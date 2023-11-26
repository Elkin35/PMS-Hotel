package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurante extends Servicio {

    PMS hotel;
    private static Pedido pedidoActual;
    private static Restaurante restauranteActual;

    public Restaurante(int tarifa, String nombreServicio, String descripcion, String idEmpleado) {
        super(tarifa, nombreServicio, descripcion, idEmpleado, LocalDate.now());
        nombreServicio = "restaurante";
        this.hotel = new PMS();
        hotel.cargarMenus();
    }

public ArrayList<Object[][]> mostrarMenu(int tipoMenu){ // 1 = habitacion, 2 = comedor
    	
    	ArrayList<Object[][]> retorno = new ArrayList<>();
    	
    	//Menu de habitacion
        if(tipoMenu == 1){
        HashMap<String, Plato> mapaPlatosMenuHabitacion = hotel.getPlatosMenuHabitacion();
        HashMap<String, Bebida> mapaBebidasMenuHabitacion = hotel.getBebidasMenuHabitacion();
        
        //Se agrega la informacion a la lista de platos menu habitacion
        
       Object[][] listaPlatosMenuHabitacion = new Object[mapaPlatosMenuHabitacion.size()][];

        int numPlato = 1;
        
        int platoAgregar = 0;

        for(String key: mapaPlatosMenuHabitacion.keySet()){
        	
        	ArrayList<String> platoInfo = new ArrayList<>();
        	
            Plato plato = mapaPlatosMenuHabitacion.get(key);
            String precio = String.valueOf(plato.getPrecio());
            // pasar el LocalTime hora inicio a String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaInicio = plato.getHoraInicio().format(formatter);
            // pasar el LocalTime hora fin a String
            String horaFin = plato.getHoraFin().format(formatter);
            String impuesto = String.valueOf(plato.getImpuesto());
            
            platoInfo.add(numPlato+""); platoInfo.add(plato.getNombre()); platoInfo.add(precio); platoInfo.add(horaInicio);
            platoInfo.add(horaFin); platoInfo.add(impuesto);
            
            listaPlatosMenuHabitacion[platoAgregar] = platoInfo.toArray();

            numPlato++;
            platoAgregar++;

                }
        
        retorno.add(listaPlatosMenuHabitacion);
        
      //Se agrega la informacion a la lista de bebidas del menu habitacion
        
        Object[][] listaBebidasMenuHabitacion = new Object[mapaBebidasMenuHabitacion.size()][];
        
        int bebidaAgregar = 0;

        for(String key: mapaBebidasMenuHabitacion.keySet()){
        	
        	ArrayList<String> bebidaInfo = new ArrayList<>();
        	
            Bebida bebida = mapaBebidasMenuHabitacion.get(key);
            String precio = String.valueOf(bebida.getPrecio());
            // pasar el LocalTime hora inicio a String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaInicio = bebida.getHoraInicio().format(formatter);
            // pasar el LocalTime hora fin a String
            String horaFin = bebida.getHoraFin().format(formatter);

            String impuesto = String.valueOf(bebida.getImpuesto());
            
            bebidaInfo.add(numPlato+""); bebidaInfo.add(bebida.getNombre()); bebidaInfo.add(precio); bebidaInfo.add(horaInicio);
            bebidaInfo.add(horaFin); bebidaInfo.add(impuesto);
            
            listaBebidasMenuHabitacion[bebidaAgregar] = bebidaInfo.toArray();

            numPlato++;
            bebidaAgregar++;
        }
        
        retorno.add(listaBebidasMenuHabitacion);
        
            }
        
        //Menu de comedor
        else if(tipoMenu == 2){

        HashMap<String, Bebida> mapaBebidasMenuComedor = hotel.getBebidasMenuComedor();
        HashMap<String, Plato> mapaPlatosMenuComedor = hotel.getPlatosMenuComedor();
        
        //Se agrega la informacion a la lista de platos menu comedor
        
        Object[][] listaPlatosMenuComedor = new Object[mapaPlatosMenuComedor.size()][];
        
        int numPlato = 1;
        int platoAgregar = 0;
        
        for(String key: mapaPlatosMenuComedor.keySet()){
        	
        	ArrayList<String> platoInfo = new ArrayList<>();
        	
            Plato plato = mapaPlatosMenuComedor.get(key);
            String precio = String.valueOf(plato.getPrecio());
            // pasar el LocalTime hora inicio a String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaInicio = plato.getHoraInicio().format(formatter);
            // pasar el LocalTime hora fin a String
            String horaFin = plato.getHoraFin().format(formatter);

            String impuesto = String.valueOf(plato.getImpuesto());
            
            platoInfo.add(numPlato+""); platoInfo.add(plato.getNombre()); platoInfo.add(precio); platoInfo.add(horaInicio);
            platoInfo.add(horaFin); platoInfo.add(impuesto);
            
            listaPlatosMenuComedor[platoAgregar] = platoInfo.toArray();

            numPlato++;
            platoAgregar++;
                }
        
        	retorno.add(listaPlatosMenuComedor);
        	
        //Se agrega la informacion a la lista de bebidas del menu comedor
            
        Object[][] listaBebidasMenuComedor = new Object[mapaBebidasMenuComedor.size()][];

        int bebidaAgregar = 0;

        for(String key: mapaBebidasMenuComedor.keySet()){
        	
        	ArrayList<String> bebidaInfo = new ArrayList<>();
        	
            Bebida bebida = mapaBebidasMenuComedor.get(key);
            String precio = String.valueOf(bebida.getPrecio());
            // pasar el LocalTime hora inicio a String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaInicio = bebida.getHoraInicio().format(formatter);
            // pasar el LocalTime hora fin a String
            String horaFin = bebida.getHoraFin().format(formatter);
            String impuesto = String.valueOf(bebida.getImpuesto());
            
            bebidaInfo.add(numPlato+""); bebidaInfo.add(bebida.getNombre()); bebidaInfo.add(precio); bebidaInfo.add(horaInicio);
            bebidaInfo.add(horaFin); bebidaInfo.add(impuesto);
            
            listaBebidasMenuComedor[bebidaAgregar] = bebidaInfo.toArray();

            numPlato++;
            bebidaAgregar++;
            }
        
        retorno.add(listaBebidasMenuComedor);
        
        }
        
        return retorno;

    }


    public void iniciarPedido(boolean esHabitacion){
        Pedido pedido = new Pedido(esHabitacion);
        pedidoActual = pedido;
    }
    
    public Pedido getPedidoActual() {
    	return pedidoActual;
    }

    public boolean agregarProductoAPedido(String nombreProducto){

        if(pedidoActual.esHabitacion()){
            HashMap<String, Plato> mapaPlatosMenuHabitacion = hotel.getPlatosMenuHabitacion();
        HashMap<String, Bebida> mapaBebidasMenuHabitacion = hotel.getBebidasMenuHabitacion();
        
            if(mapaPlatosMenuHabitacion.containsKey(nombreProducto)){
                Plato plato = mapaPlatosMenuHabitacion.get(nombreProducto);
                pedidoActual.agregarItem(plato);
                return true;
            }
            else if(mapaBebidasMenuHabitacion.containsKey(nombreProducto)){
                Bebida bebida = mapaBebidasMenuHabitacion.get(nombreProducto);
                pedidoActual.agregarItem(bebida);
                return true;
            }
        }
        else{
            HashMap<String, Plato> mapaPlatosMenuComedor = hotel.getPlatosMenuComedor();
            HashMap<String, Bebida> mapaBebidasMenuComedor = hotel.getBebidasMenuComedor();

            if(mapaPlatosMenuComedor.containsKey(nombreProducto)){
                Plato plato = mapaPlatosMenuComedor.get(nombreProducto);
                pedidoActual.agregarItem(plato);
                return true;
            }
            else if(mapaBebidasMenuComedor.containsKey(nombreProducto)){
                Bebida bebida = mapaBebidasMenuComedor.get(nombreProducto);
                pedidoActual.agregarItem(bebida);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProductoAPedido(String nombreProducto){
    	
    	ArrayList<Producto> itemsPedido = pedidoActual.getItemsPedido();

        if(pedidoActual.esHabitacion()){
            HashMap<String, Plato> mapaPlatosMenuHabitacion = hotel.getPlatosMenuHabitacion();
        HashMap<String, Bebida> mapaBebidasMenuHabitacion = hotel.getBebidasMenuHabitacion();
        
            if(mapaPlatosMenuHabitacion.containsKey(nombreProducto)){
                Plato plato = mapaPlatosMenuHabitacion.get(nombreProducto);
                if(itemsPedido.contains(plato)) {
                pedidoActual.eliminarItem(plato);
                return true;
                } else {
                	return false;
                }
                
            }
            else if(mapaBebidasMenuHabitacion.containsKey(nombreProducto)){
                Bebida bebida = mapaBebidasMenuHabitacion.get(nombreProducto);
                if(itemsPedido.contains(bebida)) {
                pedidoActual.eliminarItem(bebida);
                return true;
                } else {
                	return false;
                }
            }
        }
        else{
            HashMap<String, Plato> mapaPlatosMenuComedor = hotel.getPlatosMenuComedor();
            HashMap<String, Bebida> mapaBebidasMenuComedor = hotel.getBebidasMenuComedor();

            if(mapaPlatosMenuComedor.containsKey(nombreProducto)){
                Plato plato = mapaPlatosMenuComedor.get(nombreProducto);
                if(itemsPedido.contains(plato)) {
                pedidoActual.eliminarItem(plato);
                return true;
                } else {
                	return false;
                }
            }
            else if(mapaBebidasMenuComedor.containsKey(nombreProducto)){
                Bebida bebida = mapaBebidasMenuComedor.get(nombreProducto);
                if(itemsPedido.contains(bebida)) {
                pedidoActual.eliminarItem(bebida);
                return true;
                } else {
                	return false;
                }
            }
        }
        return false;
    }

    public float calcularTotalPedido(){

        float total = 0;
        for(Producto producto: pedidoActual.getItemsPedido()){
            int impuesto = producto.getImpuesto();
            float precioProducto = (producto.getPrecio()*((impuesto/100)+1));
            total += precioProducto;
        }
        return total;
    }

    public void setDescripcionServicio(){
        String descripcion = "";

        for(Producto producto: pedidoActual.getItemsPedido()){
            int impuesto = producto.getImpuesto();
            float precioProducto = (producto.getPrecio()*((impuesto/100)+1));
            descripcion += producto.getNombre() + ":" + "\n";
            descripcion += "Precio: " + producto.getPrecio() + "\n";
            descripcion += "Impuesto: " + producto.getImpuesto() + "\n";
            descripcion += "Precio con impuesto: " + precioProducto + "\n\n";
        }
        descripcion += "Total: " + calcularTotalPedido();

        this.descripcion = descripcion;
    }

    public Restaurante getRestaurante(){
        restauranteActual = this;
        return this;
    }
    
}
