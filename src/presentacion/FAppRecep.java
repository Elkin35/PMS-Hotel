package presentacion;
import javax.swing.JOptionPane;
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
import java.time.format.DateTimeParseException;
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

public class FAppRecep extends JFrame {

	private static final long serialVersionUID = 1L;
	static PMS hotel;
    private static String idactual;
    
    public FAppRecep() throws FileNotFoundException {
        idactual="";
        this.hotel = new PMS();
        hotel.cargar();
        hotel.cargarRegistro();
        hotel.cargarDatos("pTar.txt", "pInv.txt");
        hotel.cargarHabitaciones();
        hotel.cargarFacturas();
        
    	getContentPane().setBackground(new Color(3, 24, 53));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((int)(screenWidth/4), (int)(screenHeight/4.5), screenHeight, screenHeight);

		this.setTitle("PMS");
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
		
		JLabel title = new JLabel("PMS-Recepcionista");
		title.setForeground(new Color(242, 242, 242));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 33));
		panel_interno.add(title);
		
		JLabel subtitle = new JLabel("Recepcionista");
		subtitle.setVerticalAlignment(SwingConstants.TOP);
		subtitle.setForeground(new Color(144, 198, 244));
		subtitle.setFont(new Font("Verdana", Font.BOLD, 14));
		subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_interno.add(subtitle);
		
		JButton Opt1 = new JButton("1. Consultar reservas");
		Opt1.setForeground(new Color(39, 64, 105));
		Opt1.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt1.setHorizontalAlignment(SwingConstants.LEFT);
		Opt1.setBackground(Color.WHITE);
		panel_interno.add(Opt1);


		
		JButton Opt2 = new JButton("2. Registrar reserva");
		Opt2.setForeground(new Color(39, 64, 105));
		Opt2.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt2.setHorizontalAlignment(SwingConstants.LEFT);
		Opt2.setBackground(Color.WHITE);
		panel_interno.add(Opt2);
		
		JButton Opt3 = new JButton("3. Cancelar reserva");
		Opt3.setForeground(new Color(39, 64, 105));
		Opt3.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt3.setHorizontalAlignment(SwingConstants.LEFT);
		Opt3.setBackground(Color.WHITE);
		panel_interno.add(Opt3);
		
		JButton Opt4 = new JButton("4. Registrar Check-In");
		Opt4.setForeground(new Color(39, 64, 105));
		Opt4.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt4.setHorizontalAlignment(SwingConstants.LEFT);
		Opt4.setBackground(Color.WHITE);
		panel_interno.add(Opt4);
		
		JButton Opt5 = new JButton("5. Consultar Huespedes");
		Opt5.setForeground(new Color(39, 64, 105));
		Opt5.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt5.setHorizontalAlignment(SwingConstants.LEFT);
		Opt5.setBackground(Color.WHITE);
		panel_interno.add(Opt5);
		
		JButton Opt6 = new JButton("6. Registrar Check-Out");
		Opt6.setForeground(new Color(39, 64, 105));
		Opt6.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt6.setHorizontalAlignment(SwingConstants.LEFT);
		Opt6.setBackground(Color.WHITE);
		panel_interno.add(Opt6);
        JButton Opt10 = new JButton("7. Ocupación del hotel");
		Opt10.setForeground(new Color(39, 64, 105));
		Opt10.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt10.setHorizontalAlignment(SwingConstants.LEFT);
		Opt10.setBackground(Color.WHITE);
		panel_interno.add(Opt10);
        
		
		JLabel blank = new JLabel("    ");
		panel_peq.add(blank, BorderLayout.SOUTH);
		
		JLabel blank1 = new JLabel("                                   ");
		panel_peq.add(blank1, BorderLayout.WEST);
		
		JLabel blank2 = new JLabel("                                   ");
		panel_peq.add(blank2, BorderLayout.EAST);
		
		Opt1.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				Opt1();
				
			}

            private void Opt1() {

                List<List<Object>> x = hotel.consultarReservas();
                PReservas reservas = new PReservas(x);
                reservas.setVisible(true);



                
            };
		});
		
		Opt2.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				Opt2();
				
			}

            private void Opt2() {

               
                JTextField fechainicio = new JTextField();
                JTextField fechafin = new JTextField();
                JTextField nombre = new JTextField();
                JTextField correo = new JTextField();
                JTextField numero = new JTextField();
                JTextField documento = new JTextField();
                
                Object[] inputs = {"Fecha de inicio: ", fechainicio, "Fecha final: ", fechafin, "Nombre: ", nombre, "Correo electrónico", correo, "Documento de identidad", documento, "Número de teléfono", numero};

                UIManager.put("OptionPane.background", new Color(70, 130, 180));
                UIManager.put("Panel.background", new Color(70, 130, 180));
                UIManager.put("OptionPane.messageForeground", Color.white);
                UIManager.put("OptionPane.buttonFont", new Font("Verdana", Font.PLAIN, 16));
                UIManager.put("OptionPane.minimumSize",new Dimension(500, 350));

                int option = JOptionPane.showOptionDialog(null, inputs, "Reservar habitaciones", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "Reservar habitaciones");
                
                if (option == JOptionPane.OK_OPTION) {
                    
                    if (fechainicio.getText().isEmpty() || fechafin.getText().isEmpty() || nombre.getText().isEmpty() || correo.getText().isEmpty() || numero.getText().isEmpty() || documento.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String fecha1 = fechainicio.getText();
                        LocalDateTime fechaInicio = LocalDateTime.parse(fecha1, formatter);
                        String fecha2 = fechafin.getText();
                        LocalDateTime fechaFin = LocalDateTime.parse(fecha2, formatter);
                    
                
                    String nombreUsuario = nombre.getText();
                    String correoElectronico = correo.getText();
                    String numeroTelefono = numero.getText();
                    String documentoIdentidadd = documento.getText();
                
                    
                    JRadioButton es = new JRadioButton("Estandar");
                    JRadioButton su = new JRadioButton("Suite");
                    JRadioButton dob = new JRadioButton("Suite Doble");
                    es.setBackground(new Color(70, 130, 180));
                    su.setBackground(new Color(70, 130, 180));
                    dob.setBackground(new Color(70, 130, 180));

                    
                    ButtonGroup habitaciones = new ButtonGroup();
                    habitaciones.add(es);
                    habitaciones.add(su);
                    habitaciones.add(dob);
                                        
                    JTextField adul = new JTextField();
                    JTextField nin = new JTextField();
                    JTextField men = new JTextField();
                    JTextField cam = new JTextField();
                    
                    JCheckBox balcon = new JCheckBox("Balcón");
                    JCheckBox vista = new JCheckBox("Vista");
                    JCheckBox cocina = new JCheckBox("Cocina");
                    balcon.setBackground(new Color(70, 130, 180));
                    vista.setBackground(new Color(70, 130, 180));
                    cocina.setBackground(new Color(70, 130, 180));

                    balcon.setForeground(Color.WHITE);
                    vista.setForeground(Color.WHITE);
                    cocina.setForeground(Color.WHITE);
                    
                    es.setForeground(Color.WHITE);
                    su.setForeground(Color.WHITE);
                    dob.setForeground(Color.WHITE);
                    
                    JComboBox<String> habitacionesComboBox = new JComboBox<>();

                    JButton buscarButton = new JButton("Buscar");
                    buscarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                    
                            if (adul.getText().isEmpty() || nin.getText().isEmpty() || men.getText().isEmpty() || cam.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de buscar habitaciones disponibles.");
                            } else {
                               
                                String tipoHabitacion = "";
                                if (es.isSelected()) {
                                    tipoHabitacion = "Estandar";
                                } else if (su.isSelected()) {
                                    tipoHabitacion = "Suite";
                                } else if (dob.isSelected()) {
                                    tipoHabitacion = "Suite doble";
                                }
                                int cantidadAdultos = Integer.parseInt(adul.getText());
                                int cantidadNinios = Integer.parseInt(nin.getText());
                                int cantidadCamasNinios = Integer.parseInt(cam.getText());
                                int ninos = cantidadNinios + cantidadCamasNinios;
                                boolean tieneBalcon = balcon.isSelected();
                                boolean tieneVista = vista.isSelected();
                                boolean tieneCocina = cocina.isSelected();

                                ArrayList<String> habitacionesList = hotel.disponibilidadHabitaciones(tipoHabitacion, cantidadAdultos, ninos, tieneBalcon, tieneVista, tieneCocina, fechaInicio, fechaFin);

                                habitacionesComboBox.removeAllItems();

                                for (String habitacion : habitacionesList) {
                                    habitacionesComboBox.addItem(habitacion);
                                }
                            }
                        }
                    });

                    ArrayList<String> habitacionesSeleccionadas = new ArrayList<>(); // crear una lista vacía
                    ArrayList<String> tipoHabitacion = new ArrayList<>(); // crear una lista vacía
                    AtomicInteger cantidadTotal = new AtomicInteger(0);

                    JButton aceptarButton = new JButton("Aceptar");
                    aceptarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (habitacionesComboBox.getSelectedItem() != null) {
                                habitacionesSeleccionadas.add(habitacionesComboBox.getSelectedItem().toString());
                            }
                    
                            if (es.isSelected()) {
                                tipoHabitacion.add("Estandar");
                            } else if (su.isSelected()) {
                                tipoHabitacion.add("Suite");
                            } else if (dob.isSelected()) {
                                tipoHabitacion.add("Suite doble");
                            }
                            int cantidadAdultos = Integer.parseInt(adul.getText());
                            int cantidadNinios = Integer.parseInt(nin.getText());
                            int cantidadmen = Integer.parseInt(men.getText());
                    
                            cantidadTotal.addAndGet(cantidadAdultos+cantidadNinios+cantidadmen);

                            es.setSelected(true);
                            adul.setText("");
                            nin.setText("");
                            men.setText("");
                            cam.setText("");
                            balcon.setSelected(false);
                            vista.setSelected(false);
                            cocina.setSelected(false);
                            habitacionesComboBox.removeAllItems();
                        }
                    });
                    
                    
                    Object[] inputs2 = {"Tipo de habitacion: ", es, su, dob, "Cantidad de adultos: ", adul, "Niños mayores de 2 años: ", nin, "Niños menores de 2 años", men, "Cantidad de camas para niños menores de 2 años", cam, "Características de la habitación:", balcon, vista, cocina, "Habitaciones disponibles: ", habitacionesComboBox, buscarButton, aceptarButton};
                    
                    int option2 = JOptionPane.showOptionDialog(null, inputs2, "Reservar habitaciones", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "Reservar habitaciones");
                     
                    if (option2 == JOptionPane.OK_OPTION) {
                        if (adul.getText().isEmpty() || nin.getText().isEmpty() || men.getText().isEmpty() || cam.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe completar todos los campos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                          
                        }
                        
                        if (habitacionesSeleccionadas != null){

                            StringBuilder habitacionesConcatenadas = new StringBuilder();
                            for (String habitacion : habitacionesSeleccionadas) {
                                habitacionesConcatenadas.append(habitacion).append(", ");
                            }
                            String habitacion = habitacionesConcatenadas.toString().trim();
                            
                            habitacion = habitacion.substring(0, habitacion.length() - (",").length());

                       
                            hotel.reservar(habitacion, fechaInicio, fechaFin);
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                            String formattedDateTime = now.format(formattertime);
                            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                
                            LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime, formatters);
                           
                            Long documentoo = Long.parseLong(documentoIdentidadd);       
                            Long celular = Long.parseLong(numeroTelefono);    
                            int intValue = cantidadTotal.get();
                            String id = hotel.crearRecerva(fechaInicio, fechaFin, 0, null, null, dateTime, habitacion, nombreUsuario, documentoo, correoElectronico, celular, intValue, tipoHabitacion);

                            JOptionPane.showMessageDialog(men, "El id de la reserva es "+id, "Información", JOptionPane.INFORMATION_MESSAGE);


                        }else{
                            JOptionPane.showMessageDialog(men, "No se reservo ninguna habitacion", null, screenHeight, null);
                        }

                    }

                {
            }} catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Error en el formato de fecha. Debe ser 'yyyy-MM-dd HH:mm'", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al procesar las fechas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            };
		}});
        Opt10.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt10();
				
			};
		});
		Opt3.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				try {
                    Opt3();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				
			}

            private void Opt3() throws FileNotFoundException {
                String idReserva = JOptionPane.showInputDialog(null, "Ingrese el ID de la reserva a cancelar:");
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar la reserva con ID " + idReserva + "?");

            if (confirm == JOptionPane.YES_OPTION) {

                    String x = hotel.cancelar(idReserva);

                    if (x.equals("si_id")) {
                        JOptionPane.showMessageDialog(null, "Se eliminó la reserva correctamente.");
                    } else if (x.equals("no_id")) {
                        JOptionPane.showMessageDialog(null, "No se encontró el id de la reserva.");
                    } else if (x.equals("hora")) {
                        JOptionPane.showMessageDialog(null, "Ya ha pasado el tiempo límite para cancelar la reserva.");
                    }

            } else {
             
            }
            };
		});
		
		Opt4.addActionListener(new AbstractAction() {
			
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt4();
				
			}

            private void Opt4() {

                String id = JOptionPane.showInputDialog(null, "Ingrese el id de la reserva:");
                idactual=id;
                if (hotel.buscarReservas(id) != null){
                    hotel.registro(id);
                    ArrayList<String> info = hotel.consultarReserva(id);
                    
                    PRegistro x = new PRegistro(info);
                    x.setVisible(true);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "No se encontro ninguna reserva asociada al id "+id, "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            };
		});
		
		Opt5.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt5();
				
			}

            private void Opt5() {

                List<List<Object>> x = hotel.consultarHuespedes();
                PHuespedes reservas = new PHuespedes(x);
                reservas.setVisible(true);
                 
            };
		});
		
		Opt6.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				try {
                    Opt6();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				
			}

            private void Opt6() throws FileNotFoundException {

                String ccc = JOptionPane.showInputDialog(null, "Ingrese el id de la reserva:");
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea registrar el check-out de la reserva con ID " + ccc + "?");
    
                if (confirm == JOptionPane.YES_OPTION) {

                    String x = hotel.factura(ccc);
//                    hotel.eliminarHuespedes(ccc);
                    JTextArea facturaText = new JTextArea(x);
                    facturaText.setFont(new Font("Verdana", Font.PLAIN, 18));
                    facturaText.setBackground(new Color(39, 64, 105));
                    facturaText.setForeground(new Color(242, 242, 242));
                    Object[] options = {"Pagar en efectivo", "Pagar con tarjeta"}; //*
                    int choice = JOptionPane.showOptionDialog(null, facturaText, "Pago",
                    	    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); //*
                    if(choice == 0) {
                    	hotel.eliminarHuespedes(ccc);
                    	JOptionPane.showMessageDialog(null, "Se ha registrado el check-out exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    } else if (choice == 1) {
                    	new AppPasarelas(ccc);
                    	// hotel.eliminarHuespedes(ccc); {Esto se ebe hacer en la ventana de pasarela de pago}
                    	//JOptionPane.showMessageDialog(null, "Se ha registrado el check-out exitosamente", "", JOptionPane.INFORMATION_MESSAGE); // Temporal
                    }
//                    JOptionPane.showMessageDialog(null, facturaText);
//                    JOptionPane.showMessageDialog(null, "Se ha registrado el check-out exitosamente", "", JOptionPane.INFORMATION_MESSAGE);

                } else {
                 
                }

            };
  
		});
		
    }
    
    
    static boolean agregarAcompanantes(ArrayList<String> lista) {
        try {
            String nombre = lista.get(0);
            validarNombre(nombre);
    
            Long documento = Long.parseLong(lista.get(1));
            validarDocumento(documento);
    
            String correo = lista.get(2);
            validarCorreo(correo);
    
            Long celular = Long.parseLong(lista.get(3));
            validarCelular(celular);
    
            String fechaStr = lista.get(4);
            validarFecha(fechaStr);
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);
    
            hotel.acompanantes(idactual, nombre, documento, correo, celular, fecha);
            return true;
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
        
    }
    
    static void validarNombre(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("El nombre no puede estar vacío");
        }
    }
    
    static void validarDocumento(Long documento) throws Exception {
       
        if (documento <= 0) {
            throw new Exception("El documento debe ser un valor válido");
        }
    }
    
    static void validarCorreo(String correo) throws Exception {
       
        if (!correo.contains("@")) {
            throw new Exception("El correo debe ser válido");
        }
    }
    
    static void validarCelular(Long celular) throws Exception {
        
        if (celular.toString().length() != 10) {
            throw new Exception("El número de celular debe tener 10 dígitos");
        }
    }
    
    static void validarFecha(String fechaStr) throws Exception {
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            throw new Exception("La fecha debe tener el formato 'yyyy-MM-dd'");
        }
    }
    
    static void agregarAcompanantesfin(){
        hotel.guardarRegistro();
    }

    static boolean verificarid(String idReserva){
        if (hotel.buscarReservas(idReserva) != null){
            return true;
        }
        else{
            System.out.println("\nNo se encontro el id de la reserva");
            return false;
        }

    }

    public static void cargar(ArrayList<ArrayList<String>> listaa) {
    
        for(ArrayList<String> lista: listaa){

            String nombre = lista.get(0);
            Long documento = Long.parseLong((lista.get(1)));
            String correo = lista.get(2);
            Long celular = Long.parseLong(lista.get(3));
            String fechaStr = lista.get(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);
    
            hotel.acompanantes(idactual, nombre, documento, correo, celular, fecha);
        }
    }
    public static void Opt10() {
     	
        int yesno8 = JOptionPane.showConfirmDialog(null, "Desea ver la ocupación del hoteol dado un año?",null,JOptionPane.YES_NO_OPTION);
        
        if (yesno8 == JOptionPane.YES_OPTION) {
            FOcupacion optOcp = new FOcupacion();
            optOcp.setVisible(true);
            optOcp.setResizable(false);
            
        }

    }

	}