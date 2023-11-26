package presentacion.AppRestauranteInterfaz;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import logica.PMS;
import logica.Restaurante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class AppMenu extends JFrame{
	
	static PMS hotel = new PMS();
    
    static Restaurante restaurante = new Restaurante(0, null, null, null);
	
	public AppMenu(int tipoMenu) {
		super();
		
		ArrayList<Object[][]> data = restaurante.mostrarMenu(tipoMenu);
		
		Object[][] platos = data.get(0);
		Object[][] bebidas = data.get(1);
        
        String[] columnNames = {"#", "Nombre", "Precio", "Hora inicio", "Hora fin", "Impuesto"};
        
        DefaultTableModel modelPlatos = new DefaultTableModel(platos, columnNames);
        DefaultTableModel modelBebidas = new DefaultTableModel(bebidas, columnNames);
        
        JTable tablePlatos = new JTable(modelPlatos);
        JTable tableBebidas = new JTable(modelBebidas);
        TableColumn platosCol0 = tablePlatos.getColumnModel().getColumn(0);
        TableColumn bebidasCol0 = tableBebidas.getColumnModel().getColumn(0);
        platosCol0.setMaxWidth(30);
        bebidasCol0.setMaxWidth(30);
        
        tableBebidas.setBackground(new Color(242, 242, 242));
        tableBebidas.setFillsViewportHeight(true);
        tableBebidas.setEnabled(false);
        
        tablePlatos.setFillsViewportHeight(true);
        tablePlatos.setBackground(new Color(242, 242, 242));
        tablePlatos.setEnabled(false);
        
        this.setLayout(new BorderLayout());
        
        JPanel panelGen = new JPanel() {
        	public void paint(Graphics g) {
				
				ImageIcon bg = new ImageIcon(getClass().getResource("/imagenes/rectbg.jpg"));
				Image imagenP = bg.getImage();
				g.drawImage(imagenP, 0, 0, this.getWidth(), this.getHeight(), this);
				setOpaque(false);
				 
				super.paint(g);
				 
			}
        };
        panelGen.repaint();
        panelGen.setLayout(new BorderLayout());
        
        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.Y_AXIS));
        
        JScrollPane jspPlatos = new JScrollPane(tablePlatos);
        JScrollPane jspBebidas = new JScrollPane(tableBebidas);
        
        jspBebidas.setBorder(BorderFactory.createEmptyBorder());
        jspPlatos.setBorder(BorderFactory.createEmptyBorder());
        
//        jspBebidas.setViewportBorder(null);
        
        JLabel labelTitulo = new JLabel("MENÚ");
        labelTitulo.setFont(new Font("Segoe UI Historic", Font.BOLD, 30));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(new Color(242, 242, 242));
        
        JLabel labelPlatos = new JLabel("Platos del menú");
        labelPlatos.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        labelPlatos.setForeground(new Color(242, 242, 242));
        
        JLabel labelBebidas = new JLabel("Bebidas del menú");
        labelBebidas.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        labelBebidas.setForeground(new Color(242, 242, 242));
        
        //Bloque para mostrar las tablas
        panelTablas.add(labelTitulo);
        panelTablas.add(Box.createRigidArea(new Dimension(0, 20)));
        panelTablas.add(labelPlatos);
        panelTablas.add(Box.createRigidArea(new Dimension(0, 10)));
        panelTablas.add(jspPlatos);
        panelTablas.add(Box.createRigidArea(new Dimension(0, 30)));
        panelTablas.add(labelBebidas);
        panelTablas.add(Box.createRigidArea(new Dimension(0, 10)));
        panelTablas.add(jspBebidas);
        
        panelTablas.setBackground(new Color(39, 64, 105));
		
        panelGen.add(panelTablas, BorderLayout.CENTER);
        
        panelGen.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelTablas.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.add(panelGen, BorderLayout.CENTER);
        
        this.setLocation((this.getWidth()/2), (this.getHeight()/2));
        Dimension tamano = new Dimension(700, 750);
        this.setSize(tamano);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
    public static void main(String[] args) {
    	new AppMenu(1);
    }
}
