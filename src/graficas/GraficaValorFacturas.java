package graficas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.*;

public class GraficaValorFacturas {
	
	
	public GraficaValorFacturas() throws FileNotFoundException, IOException {

		DefaultCategoryDataset dataset = crearDataset();
		
		JFreeChart chart = ChartFactory.createLineChart(
                "Valor de las facturas a lo largo del tiempo", 
                "Fecha",                       
                "Valor",                   
                dataset                     
        );
		ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Valor de las facturas a lo largo del tiempo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().add(chartPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
		
	}
	
    private DefaultCategoryDataset crearDataset() throws FileNotFoundException, IOException {
    	Map<LocalDate, Integer> ValorPorDia =  FacturasPorFecha();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Entry<LocalDate, Integer> entry : ValorPorDia.entrySet()) {
            dataset.addValue(entry.getValue(), "Unidades", entry.getKey().toString());
        }
        return dataset;
    }
    
    private Map<LocalDate, Integer> FacturasPorFecha() throws FileNotFoundException, IOException {
    	
    	 Map<LocalDate, Integer> map = new HashMap<>();
         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         String path = System.getProperty("user.dir");
         try (BufferedReader reader = new BufferedReader(new FileReader(path + "/entrega 3/reservas/Facturas.txt"))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] columns = line.split(";");

                 String dateString = columns[6].trim(); // fecha
                 String valueString = columns[2].trim(); // valor
                 String servicio = columns[1].trim(); // servicio
                 
                 LocalDate date = LocalDate.parse(dateString, dateFormatter);
                 
                 int currentValue = map.getOrDefault(date, 0);
                 int newValue = currentValue + Integer.parseInt(valueString);
                 
                 if (servicio.equals("restaurante")) {
                	 map.put(date, newValue); 
                 }
             }
         }
         SortedMap<LocalDate, Integer> mapOrdenado = new TreeMap<>(map);
         return mapOrdenado;
    }
    
}
