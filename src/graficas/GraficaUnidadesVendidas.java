package graficas;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class GraficaUnidadesVendidas extends JFrame {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> unidadesVendidas;

    public GraficaUnidadesVendidas(HashMap<String, Integer> unidadesVendidas, int numGraph) {
        this.unidadesVendidas = unidadesVendidas;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		if (numGraph == 1) {
			setTitle("Unidades Vendidas por Producto");
			setBounds((int)(screenWidth/5-300), (int)(screenHeight/4), 800, 500);
			DefaultCategoryDataset dataset = crearDataset();
			JFreeChart chart = ChartFactory.createBarChart(
					"Unidades Vendidas por Producto",
					"Producto",
					"Unidades Vendidas",
					dataset,
					PlotOrientation.VERTICAL,
					true,
					true,
					false
					);
			ChartPanel chartPanel = new ChartPanel(chart);
			add(chartPanel, BorderLayout.CENTER);
		}
		else {
			setTitle("Valor total ganado por Producto");
			setBounds((int)(screenWidth/5+550), (int)(screenHeight/4), 800, 500);	
			DefaultCategoryDataset dataset = crearDataset();
			JFreeChart chart = ChartFactory.createBarChart(
					"Valor total ganado por Producto",
					"Producto",
					"Valor total ganado",
					dataset,
					PlotOrientation.VERTICAL,
					true,
					true,
					false
					);
			ChartPanel chartPanel = new ChartPanel(chart);
			add(chartPanel, BorderLayout.CENTER);
		}



        setVisible(true);
    }

    private DefaultCategoryDataset crearDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : unidadesVendidas.entrySet()) {
            dataset.addValue(entry.getValue(), "Unidades", entry.getKey());
        }
        return dataset;
    }

}