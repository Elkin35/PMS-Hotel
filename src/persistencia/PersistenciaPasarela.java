package persistencia;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalTime;
import java.util.HashMap;

import logica.Plato;
import presentacion.App;

public class PersistenciaPasarela {

	public void escribirPlato(String idReserva, String nombrePasarela, String nombreDueno, String numTarjeta, String precioNeto, 
			String iva, String total, boolean resultadoTransaccion){

        try {
            FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/Pasarelas/"+ nombrePasarela + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

                bw.write(idReserva + "," + nombreDueno + "," + numTarjeta + "," + precioNeto + "," +  iva + "," + total + "," + resultadoTransaccion);
                bw.newLine();
                bw.close();
                fw.close();
                
        } catch (Exception e) {
            System.out.println("\nError al crear el plato");
        }

    }

}
