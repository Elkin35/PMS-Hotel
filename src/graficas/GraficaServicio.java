package graficas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraficaServicio extends JPanel {
    private HashMap<String, Integer> frecuenciaServicios;
    private HashMap<String, Color> coloresServicios;
    private Image backgroundImage;

    public GraficaServicio(HashMap<String, Integer> frecuenciaServicios) {
        this.frecuenciaServicios = frecuenciaServicios;
        coloresServicios = new HashMap<>();

        coloresServicios.put("restaurante", new Color(70, 130, 180));
        coloresServicios.put("tour", new Color(3, 24, 53));
        coloresServicios.put("spa", new Color(144, 198, 244));

        try {
            backgroundImage = ImageIO.read(new File("entrega 3/src/imagenes/rectbg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

     
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

    
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        int radius = Math.min(width, height) / 2 - 10;
        double total = 0;

        for (int value : frecuenciaServicios.values()) {
            total += value;
        }

        double startAngle = 0;
        int i = 0;
        for (Map.Entry<String, Integer> entry : frecuenciaServicios.entrySet()) {
            String nombreServicio = entry.getKey();
            int value = entry.getValue();

            double angle = (value / total) * 360;
            Color servicioColor = coloresServicios.get(nombreServicio);
            g.setColor(servicioColor);
            g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, (int) startAngle, (int) angle);

            int rectX = width - 150;
            int rectY = i * 30;
            int rectWidth = 140;
            int rectHeight = 25;
            g.setColor(servicioColor); 
            g.fillRect(rectX, rectY, rectWidth, rectHeight);
            g.setColor(Color.BLACK);
            g.drawRect(rectX, rectY, rectWidth, rectHeight);

            if (nombreServicio.equals("spa")) {
                g.setColor(Color.BLACK); // Utilizar color negro para el servicio "Spa"
            } else {
                g.setColor(Color.WHITE);
            }
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(nombreServicio, rectX + 5, rectY + 15);

         
            double percentage = (value / total) * 100;
            String percentageString = String.format("%.2f%%", percentage);
            g.drawString(percentageString, rectX + 80, rectY + 15);

            startAngle += angle;
            i++;
        }

        String titulo = "Diagrama de Pastel por Frecuencia de Cada Servicio";
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 18));
        int tituloWidth = g.getFontMetrics().stringWidth(titulo);
        int tituloX = (width - tituloWidth) / 2;
        int tituloY = 30;
        g.drawString(titulo, tituloX, tituloY);
    }

}
