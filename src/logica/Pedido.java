package logica;

import java.util.ArrayList;

public class Pedido {

    private ArrayList<Producto> itemsPedido;
    private boolean esHabitacion;

    public Pedido(boolean esHabitacion) {
        this.itemsPedido = new ArrayList<>();
        this.esHabitacion = esHabitacion;
    }

    public ArrayList<Producto> getItemsPedido() {
        return itemsPedido;
    }

    public void agregarItem(Producto item) {
        this.itemsPedido.add(item);
        
        if (!Ventas.mapaVentas.containsKey(item.nombre)) {
        	Ventas.mapaVentas.put(item.nombre, 1);
        }
        else {
        	int pedidos = Ventas.mapaVentas.get(item.nombre);
        	Ventas.mapaVentas.put(item.nombre, pedidos + 1);
        }
    }

    public void eliminarItem(Producto item) {
        this.itemsPedido.remove(item);
        if (Ventas.mapaVentas.containsKey(item.nombre)) {
        	int pedidos = Ventas.mapaVentas.get(item.nombre);
        	Ventas.mapaVentas.put(item.nombre, pedidos - 1);
        }
    }

    public boolean esHabitacion() {
        return this.esHabitacion;
    }
    
}
