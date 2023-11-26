package presentacion;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.PMS;
import logica.Servicio;
import logica.Tour;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class AppAgregarServicio extends JFrame{
	
	static PMS hotel = new PMS();
    	
	public AppAgregarServicio(Servicio servicioAPagar) {
		super();
        
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
        
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        
        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(3,3));
        
        
        JLabel labelTitulo = new JLabel("AGREGAR SERVICIO A LA FACTURA");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(new Color(242, 242, 242));
        
        JLabel labelServicio = new JLabel(servicioAPagar.getNombreServicio().toUpperCase());
        labelServicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelServicio.setFont(new Font("Segoe UI Historic", Font.BOLD, 16));
        labelServicio.setHorizontalAlignment(SwingConstants.CENTER);
        labelServicio.setForeground(new Color(242, 242, 242));
        
        JLabel lblPagarAhora = new JLabel("¿Desea pagar ahora? ");
        Checkbox checkPagarTrue = new Checkbox("Sí", false);
        Checkbox checkPagarFalse = new Checkbox("No", true);
        
        
        lblPagarAhora.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblPagarAhora.setForeground(new Color(242, 242, 242));
        
        checkPagarTrue.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        checkPagarTrue.setForeground(new Color(242, 242, 242));
        
        checkPagarFalse.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        checkPagarFalse.setForeground(new Color(242, 242, 242));
        
        JPanel panelPagar = new JPanel();
        panelPagar.add(lblPagarAhora);
        panelPagar.add(checkPagarTrue);
        panelPagar.add(checkPagarFalse);
        panelPagar.setBackground(new Color(39, 64, 105));
        
        JLabel lblIdReserva = new JLabel("Ingrese el ID de la reserva");
        lblIdReserva.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblIdReserva.setForeground(new Color(242, 242, 242));
        JLabel emptyLabel = new JLabel(); emptyLabel.setPreferredSize(new Dimension(0, 0)); emptyLabel.setMinimumSize(new Dimension(0, 0));
        JTextField tfIdReserva = new JTextField(20);
        
        JPanel panIdReserva = new JPanel();
        panIdReserva.add(lblIdReserva);
        panIdReserva.add(tfIdReserva);
        panIdReserva.setBackground(new Color(39, 64, 105));
        
        JLabel lblIdEmpleado = new JLabel("Ingrese el ID del empleado");
        lblIdEmpleado.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblIdEmpleado.setForeground(new Color(242, 242, 242));
        JTextField tfIdEmpleado = new JTextField(20);
        
        JPanel panIdEmpleado = new JPanel();
        panIdEmpleado.add(lblIdEmpleado);
        panIdEmpleado.add(tfIdEmpleado);
        panIdEmpleado.setBackground(new Color(39, 64, 105));
        
        JLabel lblSaldo = new JLabel("Ingrese el saldo a pagar. (La tarifa es: " + servicioAPagar.gettarifaServicio() + ")");
        lblSaldo.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblSaldo.setForeground(new Color(242, 242, 242));
        JTextField tfSaldo = new JTextField(20);
        tfSaldo.setEnabled(false);
        tfSaldo.setBackground(new Color(161, 159, 158));
        
        JPanel panSaldo = new JPanel();
        panSaldo.add(lblSaldo);
        panSaldo.add(tfSaldo);
        panSaldo.setBackground(new Color(39, 64, 105));
        
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        btnEnviar.setBackground(new Color(242, 242, 242));
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        btnCancelar.setBackground(new Color(242, 242, 242));
        
        JPanel panButton = new JPanel();
        panButton.add(btnEnviar);
        panButton.add(btnCancelar);
        panButton.setBackground(new Color(39, 64, 105));
        
        checkPagarTrue.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					checkPagarFalse.setState(false);

					tfSaldo.setBackground(new Color(242, 242, 242));
					tfSaldo.setEnabled(true);

		        } else {
		        	if(checkPagarFalse.getState()==false) {
		        		checkPagarFalse.setState(true);
		        		tfSaldo.setText(null);
		        		tfSaldo.setBackground(new Color(161, 159, 158));
		        		tfSaldo.setEnabled(false);
		        	}
		        }
			}
        	
        });
        
        checkPagarFalse.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					checkPagarTrue.setState(false);
					tfSaldo.setText(null);
					tfSaldo.setBackground(new Color(161, 159, 158));
					tfSaldo.setEnabled(false);
		        } else {
		        	tfSaldo.setEnabled(true);
		        	tfSaldo.setBackground(new Color(242, 242, 242));
		        	
		        	if(checkPagarTrue.getState()==false) {
		        		checkPagarTrue.setState(true);
		        		tfSaldo.setText(null);
		        		tfSaldo.setBackground(new Color(242, 242, 242));
		        		tfSaldo.setEnabled(true);
		        	}
		        }
			}
        	
        });
        
        btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean pagar = false;
				int precio = servicioAPagar.gettarifaServicio();
				String servicio = servicioAPagar.getNombreServicio();
				
				if(checkPagarTrue.getState() == true) {
					pagar = true;

				}
				
				String IDReserva = tfIdReserva.getText();
				String IDEmpleado = tfIdEmpleado.getText();
				
				
				try {
					
					
					if(pagar) {
						String saldo = tfSaldo.getText();
						int intSaldo = Integer.parseInt(saldo);
						
						if(intSaldo>=precio) {
							if(hotel.existeEmpleado(IDEmpleado)) {
								hotel.agregarServicioFactura(IDReserva, precio, servicio, IDEmpleado, pagar);
								JOptionPane.showMessageDialog(null, "Se ha agregado el servicio a la factura");
								
								tfIdEmpleado.setText(null);
								tfIdReserva.setText(null);
								tfSaldo.setText(null);
								tfSaldo.setBackground(new Color(161, 159, 158));;
								tfSaldo.setEnabled(false);
								checkPagarTrue.setState(false);
								checkPagarFalse.setState(true);
							} else {
								JOptionPane.showInternalMessageDialog(null, "El elmpleado no existe, intentelo e nuevo.", "Error en el id de empleado", 0);
							}
						} else {
							JOptionPane.showInternalMessageDialog(null, "La tarifa de el spa es de " + servicioAPagar.gettarifaServicio() + ". Debe ingresar un valor mas alto.", "Error en el saldo", 0);
						}
					} else {
						if(hotel.existeEmpleado(IDEmpleado)) {
							hotel.agregarServicioFactura(IDReserva, precio, servicio, IDEmpleado, pagar);
							JOptionPane.showMessageDialog(null, "Se ha agregado el servicio a la factura");
							
							tfIdEmpleado.setText(null);
							tfIdReserva.setText(null);

						} else {
							JOptionPane.showInternalMessageDialog(null, "El elmpleado no existe, intentelo e nuevo.", "Error en el id de empleado", 0);
						}
					}
					
				} catch (NumberFormatException e2) {
					JOptionPane.showInternalMessageDialog(null, "El saldo a pagar debe ser un numero. Intentelo de nuevo", "Error en el saldo", 0);
				}
				
			}
        	
        });
        
        btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
        	
        });
        
        panelFormulario.add(labelTitulo);
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        panelFormulario.add(labelServicio);
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 35)));
        panelFormulario.add(panelPagar);
        panelFormulario.add(panIdReserva);
        panelFormulario.add(panIdEmpleado);
        panelFormulario.add(panSaldo);
        panelFormulario.add(panButton);
        
        panelFormulario.add(panelGrid, BorderLayout.CENTER);
        
        panelGrid.setBackground(new Color(39, 64, 105));
        panelFormulario.setBackground(new Color(39, 64, 105));
		
        panelGen.add(panelFormulario, BorderLayout.CENTER);
        
        panelGen.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.add(panelGen, BorderLayout.CENTER);
        
        this.setLocation((this.getWidth()/2), (this.getHeight()/2));
        Dimension tamano = new Dimension(520, 600);
        this.setSize(tamano);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
    public static void main(String[] args) {
    	new AppAgregarServicio(new Tour(0, "tour", null, null, null));
    }
}
