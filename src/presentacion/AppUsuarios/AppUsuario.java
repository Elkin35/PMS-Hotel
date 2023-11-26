package presentacion.AppUsuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.*;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import logica.Habitacion;
import logica.PMS;
import logica.Tarifa;

import java.awt.GridLayout;
import java.awt.Font;
import logica.Tour;
import logica.UsuarioHuesped;

public class AppUsuario extends JFrame {

    private UsuarioHuesped x = new UsuarioHuesped();
        
    public AppUsuario() throws FileNotFoundException {
        
        UIManager.put("OptionPane.background", new Color(70, 130, 180));
        UIManager.put("Panel.background", new Color(70, 130, 180));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.buttonFont", new Font("Verdana", Font.PLAIN, 16));
        UIManager.put("OptionPane.minimumSize",new Dimension(500, 350));


    	getContentPane().setBackground(new Color(3, 24, 53));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((int)(screenWidth/4), (int)(screenHeight/4.5), screenHeight, screenHeight);

		this.setTitle("Usuarios");
		this.setSize (800 ,570);
		this.setForeground(new Color(0, 0, 153) );
		JPanel inicio = new JPanel (){
			 
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				
				ImageIcon bg = new ImageIcon(getClass().getResource("/imagenes/rectbg.jpg"));
				Image imagenP = bg.getImage();
				g.drawImage(imagenP, 0, 0, 800 ,560, this);
				setOpaque(false);
				 
				super.paint(g);
				 
			}
				 
				 
		};
		inicio.repaint();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(inicio);

		
		JPanel panel_peq = new JPanel();
		panel_peq.setBackground(new Color(39, 64, 105));
		panel_peq.setPreferredSize(new Dimension (650,430));
		panel_peq.setMinimumSize(new Dimension (650,430));
		panel_peq.setMaximumSize(new Dimension (650,430));
		inicio.add(panel_peq, Component.CENTER_ALIGNMENT);
		panel_peq.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_interno = new JPanel();
		panel_interno.setBackground(new Color(39, 64, 105));
		panel_peq.add(panel_interno, BorderLayout.CENTER);
		panel_interno.setLayout(new GridLayout(12, 1, 0, 10));
		
		JLabel spc = new JLabel("");
		panel_interno.add(spc);
		
		JLabel title = new JLabel("PMS-Usuario Huesped");
		title.setForeground(new Color(242, 242, 242));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 33));
		panel_interno.add(title);
		
		JLabel subtitle = new JLabel("Huesped");
		subtitle.setVerticalAlignment(SwingConstants.TOP);
		subtitle.setForeground(new Color(144, 198, 244));
		subtitle.setFont(new Font("Verdana", Font.BOLD, 14));
		subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_interno.add(subtitle);
		
		JButton Opt1 = new JButton("1. Realizar una reserva");
		Opt1.setForeground(new Color(39, 64, 105));
		Opt1.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt1.setHorizontalAlignment(SwingConstants.LEFT);
		Opt1.setBackground(Color.WHITE);
		panel_interno.add(Opt1);

		JButton Opt2 = new JButton("2. Pagar tarifa");
		Opt2.setForeground(new Color(39, 64, 105));
		Opt2.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt2.setHorizontalAlignment(SwingConstants.LEFT);
		Opt2.setBackground(Color.WHITE);
		panel_interno.add(Opt2);

        JButton Opt3 = new JButton("3. Consultar tarifa");
		Opt3.setForeground(new Color(39, 64, 105));
		Opt3.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt3.setHorizontalAlignment(SwingConstants.LEFT);
		Opt3.setBackground(Color.WHITE);
		panel_interno.add(Opt3);	
		
		JLabel blank = new JLabel("    ");
		panel_peq.add(blank, BorderLayout.SOUTH);
		
		JLabel blank1 = new JLabel("                                   ");
		panel_peq.add(blank1, BorderLayout.WEST);
		
		JLabel blank2 = new JLabel("                                   ");
		panel_peq.add(blank2, BorderLayout.EAST);
		


		Opt3.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				Opt3();
				
			}

            private void Opt3() {

                String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva");
                float y;
                try {
                    y = x.consultar(id);
                    JOptionPane.showMessageDialog(null, "El valor a pagar es: "+ y);
        
                } catch (Exception e) {
                    
                    JOptionPane.showMessageDialog(null, e);
                }
            
        

                
            };
		});

		Opt1.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				Opt1();
				
			}

            private void Opt1() {

                ReservaHabitacionesUI x = new ReservaHabitacionesUI();
                x.setVisible(true);

                
            };
		});
        Opt2.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				Opt2();
				
			}

            private void Opt2() {



                String idReserva = "";
                String metodoPago = "";
                String nombreTitular = "";
                String numeroTarjeta = "";
                String precioTotal = "";
                String caducidad = "";
                String codigoSeguridad = "";
        
                JTextField reservaField = new JTextField();
                String[] opcionesPago = {"PayPal", "Payu", "Sire"};
                JComboBox<String> metodoPagoComboBox = new JComboBox<>(opcionesPago);
                JTextField titularField = new JTextField();
                JTextField tarjetaField = new JTextField();
                JTextField precioField = new JTextField();
                JTextField caducidadField = new JTextField();
                JPasswordField codigoSeguridadField = new JPasswordField();
        
                Object[] message = {
                        "ID de reserva:", reservaField,
                        "Método de pago:", metodoPagoComboBox,
                        "Nombre del titular:", titularField,
                        "Número de tarjeta:", tarjetaField,
                        "Fecha de caducidad (MM/AA):", caducidadField,
                        "Código de seguridad:", codigoSeguridadField
                };
        
                int option = JOptionPane.showOptionDialog(null, message, "Información de Pago",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        
                if (option == JOptionPane.OK_OPTION) {
                    idReserva = reservaField.getText();
                    metodoPago = (String) metodoPagoComboBox.getSelectedItem();
                    nombreTitular = titularField.getText();
                    numeroTarjeta = tarjetaField.getText();
                    precioTotal = precioField.getText();
                    caducidad = caducidadField.getText();
                    codigoSeguridad = new String(codigoSeguridadField.getPassword());
        
                    if (idReserva.isEmpty() || nombreTitular.isEmpty() ||
                            numeroTarjeta.isEmpty()  || caducidad.isEmpty() ||
                            codigoSeguridad.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {

                       try {
                        x.realizarPago(idReserva, metodoPago, nombreTitular, numeroTarjeta, caducidad, codigoSeguridad);
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(null,ee);
                    }

                    }
                }

                Opt3.addActionListener(new AbstractAction() {

                    private static final long serialVersionUID = 1L;
        
                    public void actionPerformed(ActionEvent e) {
        
                        Opt3();
                        
                    }
        
                    private void Opt3() {
        
                        try {
                            String id = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva");
                            float y = x.consultar(id);
                            JOptionPane.showMessageDialog(null, "El valor a pagar es: "+ y);
        
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
           
                        }
        
                        
                    };
                });

               


          
            };
		});
    
    };
}


