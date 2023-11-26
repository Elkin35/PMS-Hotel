package graficas;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficaConsumoServicios extends JFrame{
	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> serviciosConsumidos;

    public GraficaConsumoServicios() throws FileNotFoundException, IOException {
        this.serviciosConsumidos = (HashMap<String, Integer>) hacerMapaServiciosConsumidos();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
		setTitle("Gráfica de barras - consumo de servicios");
		DefaultCategoryDataset dataset = crearDataset();
		JFreeChart chart = ChartFactory.createBarChart(
				"Consumo de servicios",
				"Servicios",
				"Cantidad consumida",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);
		ChartPanel chartPanel = new ChartPanel(chart);
		add(chartPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private DefaultCategoryDataset crearDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : serviciosConsumidos.entrySet()) {
            dataset.addValue(entry.getValue(), "Unidades", entry.getKey());
        }
        return dataset;
    }
    
    private Map<String, Integer> hacerMapaServiciosConsumidos() throws FileNotFoundException, IOException {
    	
   	 Map<String, Integer> map = new HashMap<>();
        String path = System.getProperty("user.dir");
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "/entrega 3/reservas/Facturas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");

                String servicio = columns[1].trim(); // servicio
                
                int currentValue = map.getOrDefault(servicio, 0);
                int newValue = currentValue + 1;
                
                map.put(servicio, newValue); 
            }
        }
        return map;
   }
}
