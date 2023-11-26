package graficas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GraficaRelacion {
    private HashMap<String, Double> datos;
    private JComboBox<String> habitacionesComboBox;

    public GraficaRelacion(HashMap<String, Double> datoss) {
        datos = datoss;
        JFrame frame = new JFrame("Gráfico de Barras - Relación Tarifa y Restaurante");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        JLabel habitacionLabel = new JLabel("Habitación:");
        String[] habitaciones = datos.keySet().stream()
        .map(key -> key.substring(0, key.indexOf("_")))
        .toArray(String[]::new);
        habitacionesComboBox = new JComboBox<>(habitaciones);

        JButton generarGraficaButton = new JButton("Generar Gráfica");
        generarGraficaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarGrafica();
            }
        });

        panelSuperior.add(habitacionLabel);
        panelSuperior.add(habitacionesComboBox);
        panelSuperior.add(generarGraficaButton);

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void generarGrafica() {
        String habitacionSeleccionada = (String) habitacionesComboBox.getSelectedItem();
        double tarifaRestaurante = datos.get(habitacionSeleccionada + "_restaurante");
        double tarifaTotal = datos.get(habitacionSeleccionada + "_tarifa");

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(tarifaRestaurante, "Restaurante", "");
        dataset.addValue(tarifaTotal, "Tarifa", "");

        JFreeChart chart = ChartFactory.createBarChart(
                "Comparación de Tarifa y Restaurante - Habitación " + habitacionSeleccionada,
                "Categoría",
                "Valor",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(3, 24, 53));
        plot.getRenderer().setSeriesPaint(1, new Color(242, 242, 242));

        ChartFrame frame = new ChartFrame("Gráfico de Barras - " + habitacionSeleccionada, chart);
        frame.pack();
        frame.setVisible(true);
    }

  
  
}
