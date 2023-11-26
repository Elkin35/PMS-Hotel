package presentacion.AppRestauranteInterfaz;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import logica.PMS;
import logica.Restaurante;
import logica.Ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppModificarPedido extends JFrame{
	
	static PMS hotel = new PMS();
    
	public AppModificarPedido(int tipoMenu, Restaurante restaurante) {
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
        
        // Crear los componentes para pedir el número de fila
        JLabel labelFooter = new JLabel("Ingrese el número del producto que desea agregar/eliminar al pedido: ");
        labelFooter.setForeground(new Color(242, 242, 242));
        labelFooter.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
        
        JTextField textField = new JTextField(5);
        textField.setBackground(new Color(242, 242, 242));
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                if(textField.getText().isEmpty()) {
            		JOptionPane.showInternalMessageDialog(null, "Debe ingresar un numero del menu para agregarlo al pedido", getTitle(), 0);
            	} else {
            		int row = Integer.parseInt(textField.getText())-1;
                    int platosLastRow = tablePlatos.getRowCount()-1;
                    int bebidasLastRow = tableBebidas.getRowCount()-1;
                    boolean agregado = false;
                    
                 if(row<0 || row>(platosLastRow+bebidasLastRow+1)) {
                	 JOptionPane.showInternalMessageDialog(null, "Debe ingresar un numero entre " + (1) + " y " + (bebidasLastRow+platosLastRow+2) + ".", getTitle(), 0);
                 } else {
                    
                if(row<=platosLastRow) {
                	
                	// Imprimir la fila de la tabla
                Object[] rowData = new Object[tablePlatos.getColumnCount()];
                for (int col = 0; col < tablePlatos.getColumnCount(); col++) {
                    rowData[col] = tablePlatos.getValueAt(row, col);
                }
                
                agregado = restaurante.agregarProductoAPedido((String) rowData[1]);
                } else if((row)>platosLastRow && (row)<=(bebidasLastRow+platosLastRow+1)) {
                	// Imprimir la fila de la tabla
                    Object[] rowData = new Object[tableBebidas.getColumnCount()];
                    row = row-platosLastRow-1;
                    for (int col = 0; col < tableBebidas.getColumnCount(); col++) {
                        rowData[col] = tableBebidas.getValueAt(row, col);
                    }
                    agregado = restaurante.agregarProductoAPedido((String) rowData[1]);
                }
                
                if(agregado) {
                	//nuevo
                	guardarVentas();
                	JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
                } else {
                	JOptionPane.showMessageDialog(null, "Ocurrio un error. Intentelo de nuevo");
                }
                
            	}
            }
                
            }
        });
        btnAgregar.setBackground(new Color(242 ,242, 242));
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                if(textField.getText().isEmpty()) {
            		JOptionPane.showInternalMessageDialog(null, "Debe ingresar un numero del menu para eliminarlo del pedido", getTitle(), 0);
            	} else {
            		int row = Integer.parseInt(textField.getText())-1;
                    int platosLastRow = tablePlatos.getRowCount()-1;
                    int bebidasLastRow = tableBebidas.getRowCount()-1;
                    boolean eliminado = false;
                    
                 if(row<0 || row>(platosLastRow+bebidasLastRow+1)) {
                	 JOptionPane.showInternalMessageDialog(null, "Debe ingresar un numero entre " + (1) + " y " + (bebidasLastRow+platosLastRow+2) + ".", getTitle(), 0);
                 } else {
                    
                if(row<=platosLastRow) {
                	
                	// Imprimir la fila de la tabla
                Object[] rowData = new Object[tablePlatos.getColumnCount()];
                for (int col = 0; col < tablePlatos.getColumnCount(); col++) {
                    rowData[col] = tablePlatos.getValueAt(row, col);
                }
                eliminado = restaurante.eliminarProductoAPedido((String) rowData[1]);
                } else if((row)>platosLastRow && (row)<=(bebidasLastRow+platosLastRow+1)) {
                	// Imprimir la fila de la tabla
                    Object[] rowData = new Object[tableBebidas.getColumnCount()];
                    row = row-platosLastRow-1;
                    for (int col = 0; col < tableBebidas.getColumnCount(); col++) {
                        rowData[col] = tableBebidas.getValueAt(row, col);
                    }
                    eliminado = restaurante.eliminarProductoAPedido((String) rowData[1]);
                }
                
                if(eliminado) {
                	guardarVentas();
                	JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
                } else {
                	JOptionPane.showMessageDialog(null, "Debe agregar un producto antes de eliminarlo. Intentelo de nuevo.");
                }
                
            	}
            }
                
            }
        });
        btnEliminar.setBackground(new Color(242 ,242, 242));
        
        // Mostrar la tabla y los componentes para pedir el número de fila en la misma ventana
        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.Y_AXIS));
        
        JScrollPane jspPlatos = new JScrollPane(tablePlatos);
        JScrollPane jspBebidas = new JScrollPane(tableBebidas);
        
        jspBebidas.setBorder(BorderFactory.createEmptyBorder());
        jspPlatos.setBorder(BorderFactory.createEmptyBorder());
        
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
        
        JPanel footer = new JPanel(new FlowLayout());
        footer.setBackground(new Color(16, 32, 57));
        footer.add(labelFooter);
        footer.add(textField);
        footer.add(btnAgregar);
        footer.add(btnEliminar);
        
        this.add(footer, BorderLayout.SOUTH);
        this.setLocation((this.getWidth()/2), (this.getHeight()/2));
        Dimension tamano = new Dimension(700, 700);
        this.setSize(tamano);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void guardarVentas() {
		// nuevo
		try {
			hotel.guardarVentas();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
    	new AppModificarPedido(1, new Restaurante(100, "restaurante", "...", "m-1"));

    }
}
