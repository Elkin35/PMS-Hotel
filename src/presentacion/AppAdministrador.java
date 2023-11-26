package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.AbstractAction;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import logica.PMS;
import logica.Tarifa;
import logica.Ventas;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import graficas.*;

public class AppAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	static PMS hotel = new PMS();
	
    
    public AppAdministrador() {
    	getContentPane().setBackground(new Color(3, 24, 53));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((int)(screenWidth/4), (int)(screenHeight/7), screenHeight, screenHeight);

		this.setTitle("PMS");
		this.setSize (800 ,700);
		this.setForeground(new Color(0, 0, 153) );
		JPanel inicio = new JPanel (){
			 
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				
				ImageIcon bg = new ImageIcon(getClass().getResource("/imagenes/rectbg.jpg"));
				Image imagenP = bg.getImage();
				g.drawImage(imagenP, 0, 0, 800 ,700, this);
				setOpaque(false);
				 
				super.paint(g);
				 
			}
				 
				 
		};
		inicio.repaint();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(inicio);

		
		JPanel panel_peq = new JPanel();
		panel_peq.setBackground(new Color(39, 64, 105));
		panel_peq.setPreferredSize(new Dimension (650,600));
		panel_peq.setMinimumSize(new Dimension (650,600));
		panel_peq.setMaximumSize(new Dimension (650,600));
		inicio.add(panel_peq, Component.CENTER_ALIGNMENT);
		panel_peq.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_interno = new JPanel();
		panel_interno.setBackground(new Color(39, 64, 105));
		panel_peq.add(panel_interno, BorderLayout.CENTER);
		panel_interno.setLayout(new GridLayout(13+5, 1, 0, 10));
		
		JLabel spc = new JLabel("");
		panel_interno.add(spc);
		
		JLabel title = new JLabel("PMS-admin");
		title.setForeground(new Color(242, 242, 242));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 33));
		panel_interno.add(title);
		
		JLabel subtitle = new JLabel("Admin");
		subtitle.setVerticalAlignment(SwingConstants.TOP);
		subtitle.setForeground(new Color(144, 198, 244));
		subtitle.setFont(new Font("Verdana", Font.BOLD, 14));
		subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_interno.add(subtitle);
		
		JButton Opt1 = new JButton("1. Cargar archivos de tarifas e inventario");
		Opt1.setForeground(new Color(39, 64, 105));
		Opt1.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt1.setHorizontalAlignment(SwingConstants.LEFT);
		Opt1.setBackground(Color.WHITE);
		panel_interno.add(Opt1);
		
		JButton Opt2 = new JButton("2. Actualizar tarifa");
		Opt2.setForeground(new Color(39, 64, 105));
		Opt2.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt2.setHorizontalAlignment(SwingConstants.LEFT);
		Opt2.setBackground(Color.WHITE);
		panel_interno.add(Opt2);
		
		JButton Opt3 = new JButton("3. Crear nueva tarifa");
		Opt3.setForeground(new Color(39, 64, 105));
		Opt3.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt3.setHorizontalAlignment(SwingConstants.LEFT);
		Opt3.setBackground(Color.WHITE);
		panel_interno.add(Opt3);
		
		JButton Opt4 = new JButton("4. Crear nueva habitación");
		Opt4.setForeground(new Color(39, 64, 105));
		Opt4.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt4.setHorizontalAlignment(SwingConstants.LEFT);
		Opt4.setBackground(Color.WHITE);
		panel_interno.add(Opt4);
		
		JButton Opt5 = new JButton("5. Verificar si hay tarifas para cada día de un año");
		Opt5.setForeground(new Color(39, 64, 105));
		Opt5.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt5.setHorizontalAlignment(SwingConstants.LEFT);
		Opt5.setBackground(Color.WHITE);
		panel_interno.add(Opt5);
		
		JButton Opt6 = new JButton("6. Crear nuevo usuario");
		Opt6.setForeground(new Color(39, 64, 105));
		Opt6.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt6.setHorizontalAlignment(SwingConstants.LEFT);
		Opt6.setBackground(Color.WHITE);
		panel_interno.add(Opt6);
		
		JButton Opt7 = new JButton("7. Cambiar tarifas de servicios");
		Opt7.setForeground(new Color(39, 64, 105));
		Opt7.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt7.setHorizontalAlignment(SwingConstants.LEFT);
		Opt7.setBackground(Color.WHITE);
		panel_interno.add(Opt7);
		
		JButton Opt8 = new JButton("8. Cargar menus de restaurante");
		Opt8.setForeground(new Color(39, 64, 105));
		Opt8.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt8.setHorizontalAlignment(SwingConstants.LEFT);
		Opt8.setBackground(Color.WHITE);
		panel_interno.add(Opt8);
		
		JButton Opt9 = new JButton("9. Configurar platos");
		Opt9.setForeground(new Color(39, 64, 105));
		Opt9.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt9.setHorizontalAlignment(SwingConstants.LEFT);
		Opt9.setBackground(Color.WHITE);
		panel_interno.add(Opt9);
		
		JButton Opt10 = new JButton("10. Ocupación del hotel");
		Opt10.setForeground(new Color(39, 64, 105));
		Opt10.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt10.setHorizontalAlignment(SwingConstants.LEFT);
		Opt10.setBackground(Color.WHITE);
		panel_interno.add(Opt10);
		
		JButton Opt11 = new JButton("11. Ventas por cada producto");
		Opt11.setForeground(new Color(39, 64, 105));
		Opt11.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt11.setHorizontalAlignment(SwingConstants.LEFT);
		Opt11.setBackground(Color.WHITE);
		panel_interno.add(Opt11);
		
		JButton Opt12 = new JButton("12. Valor de las facturas a lo largo del tiempo");
		Opt12.setForeground(new Color(39, 64, 105));
		Opt12.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt12.setHorizontalAlignment(SwingConstants.LEFT);
		Opt12.setBackground(Color.WHITE);
		panel_interno.add(Opt12);
		
		JButton Opt13 = new JButton("13. Relación restaurante / valor por noche");
		Opt13.setForeground(new Color(39, 64, 105));
		Opt13.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt13.setHorizontalAlignment(SwingConstants.LEFT);
		Opt13.setBackground(Color.WHITE);
		panel_interno.add(Opt13);
		
		JButton Opt14 = new JButton("14. Consumos de todos los servicios ");
		Opt14.setForeground(new Color(39, 64, 105));
		Opt14.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt14.setHorizontalAlignment(SwingConstants.LEFT);
		Opt14.setBackground(Color.WHITE);
		panel_interno.add(Opt14);
		
		JButton Opt15 = new JButton("15. Comparación del uso de servicios ");
		Opt15.setForeground(new Color(39, 64, 105));
		Opt15.setFont(new Font("Verdana", Font.BOLD, 12));
		Opt15.setHorizontalAlignment(SwingConstants.LEFT);
		Opt15.setBackground(Color.WHITE);
		panel_interno.add(Opt15);
		
		JLabel blank = new JLabel("    ");
		panel_peq.add(blank, BorderLayout.SOUTH);
		
		JLabel blank1 = new JLabel("                                   ");
		panel_peq.add(blank1, BorderLayout.WEST);
		
		JLabel blank2 = new JLabel("                                   ");
		panel_peq.add(blank2, BorderLayout.EAST);
		
		Opt1.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				try {
					Opt1();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Algo salió mal",null,JOptionPane.ERROR_MESSAGE);
				}
				
			};
		});
		
		Opt2.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				try {
					Opt2();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			};
		});
		
		Opt3.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Opt3();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Algo salió mal",null,JOptionPane.ERROR_MESSAGE);
				}
				
			};
		});
		
		Opt4.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Opt4();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Algo salió mal",null,JOptionPane.ERROR_MESSAGE);
				}
				
			};
		});
		
		Opt5.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt5();
				
			};
		});
		
		Opt6.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt6();
				
			};
		});
		
		Opt7.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt7();
				
			};
		});
		
		Opt8.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt8();
				
			};
		});
		
		Opt9.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt9();
				
			};
		});
		
		Opt10.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt10();
				
			};
		});
		
		Opt11.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt11();
				
			};
		});
		
		Opt12.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Opt12();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			};
		});
		
		Opt13.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt13();
				
			};
		});
		
		Opt14.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Opt14();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			};
		});
		
		Opt15.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				
				Opt15();
				
			};
		});
		
	}
    
    public static void Opt1() throws FileNotFoundException {

        hotel.cargarDatos("pTar.txt", "pInv.txt");
        JOptionPane.showMessageDialog(null, "Los archivos se cargaron correctamente",null,JOptionPane.INFORMATION_MESSAGE);
    }

    public static void Opt2() throws IOException {
    	
    	JTextField idia = new JTextField();
        JTextField imes = new JTextField();
        JTextField ianio = new JTextField();
        
        String initfin = "inicio";
        Object[] inputs_fecha = {"Ingrese la información de la fecha de "+initfin, "Día:", idia, "Mes:", imes, "Año:", ianio};
        JOptionPane.showOptionDialog(null, inputs_fecha, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
    	
        int D1 = Integer.parseInt(idia.getText());
        int M1 = Integer.parseInt(imes.getText());
        int Y1 = Integer.parseInt(ianio.getText());
        LocalDate inicio = LocalDate.of(Y1, M1, D1);
        
        initfin = "fin";
        Object[] inputs_fecha2 = {"Ingrese la información de la fecha de "+initfin, "Día:", idia, "Mes:", imes, "Año:", ianio};
        JOptionPane.showOptionDialog(null, inputs_fecha2, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
        
        int D2 = Integer.parseInt(idia.getText());
        int M2 = Integer.parseInt(imes.getText());
        int Y2 = Integer.parseInt(ianio.getText());
        LocalDate fin = LocalDate.of(Y2, M2, D2).plusDays(1);
        
        
        int yesno = JOptionPane.showOptionDialog(null, "La tarifa va a aplicar para todos los días de la semana?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        
        ArrayList<LocalDate> diasSemana = new ArrayList<LocalDate>();
        if (yesno == JOptionPane.NO_OPTION) {
        	
            JCheckBox lunes = new JCheckBox("Lunes");
            JCheckBox martes = new JCheckBox("Martes");
            JCheckBox miercoles = new JCheckBox("Miércoles");
            JCheckBox jueves = new JCheckBox("Jueves");
            JCheckBox viernes = new JCheckBox("Viernes");
            JCheckBox sabado = new JCheckBox("Sábado");
            JCheckBox domingo = new JCheckBox("Domingo");
            Object[] options = {"Seleccione los días de la semana donde aplicará la tarifa:", lunes, martes, miercoles, jueves, viernes, sabado, domingo};
            JOptionPane.showOptionDialog(null, options, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
            
            String diasStr = "";
            if (lunes.isSelected()) {
            	diasStr += "1";
            }
            if (martes.isSelected()) {
            	diasStr += "2";
            }
            if (miercoles.isSelected()) {
            	diasStr += "3";
            }
            if (jueves.isSelected()) {
            	diasStr += "4";
            }
            if (viernes.isSelected()) {
            	diasStr += "5";
            }
            if (sabado.isSelected()) {
            	diasStr += "6";
            }
            if (domingo.isSelected()) {
            	diasStr += "7";
            }
            
            String[] diasList = diasStr.split("");
            int[] valorDias = new int[diasList.length];
            DateTimeFormatter formatterDiaSemana = DateTimeFormatter.ofPattern("e");
            int diaSemanaInicio = Integer.valueOf(inicio.format(formatterDiaSemana));
            for (int i = 0; i < diasList.length; i++) {
            	valorDias[i] = Integer.valueOf(diasList[i]) - diaSemanaInicio;
            }
            ;
            for (int d : valorDias) {
            	LocalDate control = LocalDate.of(Y1, M1, D1).plusDays(d);
            	do {
            		diasSemana.add(control);
            		control = control.plusWeeks(1);
            	} while (control.compareTo(fin) < 0);
            }
        }
        else {
            LocalDate control = LocalDate.of(Y1, M1, D1);
            do {
                diasSemana.add(control);
                control = control.plusDays(1);
            } while (control.compareTo(fin) < 0);
        }
        
        
        JRadioButton suite = new JRadioButton("Suite");
        JRadioButton suiteDoble = new JRadioButton("Suite Doble");
        JRadioButton estandar = new JRadioButton("Estándar");
        
        ButtonGroup habitacionesTipo = new ButtonGroup();
        habitacionesTipo.add(suite);
        habitacionesTipo.add(suiteDoble);
        habitacionesTipo.add(estandar);
        Object[] tipos = {"Selecciona el tipo de habitación:", suite, suiteDoble, estandar};
        
        JOptionPane.showOptionDialog(null, tipos, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
        
        String tipo = "";
        if (suite.isSelected()) {
            tipo = "Suite";
        }
        else if (suiteDoble.isSelected()) {
            tipo = "Suite doble";
        }
        else if (estandar.isSelected()) {
            tipo = "Estandar";
        }

        float valor = Float.valueOf(JOptionPane.showInputDialog(null, "Ingrese el valor de la tarifa que aplica en las fechas señaladas:"));

        hotel.actualizarTarifas(new Tarifa(inicio, fin, diasSemana, valor, tipo), inicio, fin, valor);

        for (LocalDate fecha : diasSemana) {

            if (tipo.equals("Estandar")) {
                if (!hotel.pTarifas.estandar.containsKey(fecha)) {
                	JOptionPane.showMessageDialog(null, "La tarifa para " + fecha + ", No existía, se creó la tarifa para esta fecha",null,JOptionPane.WARNING_MESSAGE);
                }
                hotel.pTarifas.estandar.put(fecha, (double) valor);
            } else if (tipo.equals("Suite")) {
                if (!hotel.pTarifas.suite.containsKey(fecha)) {
                	JOptionPane.showMessageDialog(null, "La tarifa para " + fecha + ", No existía, se creó la tarifa para esta fecha",null,JOptionPane.WARNING_MESSAGE);
                }
                hotel.pTarifas.suite.put(fecha, (double) valor);
            } else if (tipo.equals("Suite doble")) {
                if (!hotel.pTarifas.suiteDoble.containsKey(fecha)) {
                	JOptionPane.showMessageDialog(null, "La tarifa para " + fecha + ", No existía, se creó la tarifa para esta fecha",null,JOptionPane.WARNING_MESSAGE);
                }
                hotel.pTarifas.suiteDoble.put(fecha, (double) valor);
            }
        }
        JOptionPane.showMessageDialog(null, "Se actualizaron las tarifas correctamente",null,JOptionPane.INFORMATION_MESSAGE);
    }

    public static void Opt3() throws IOException {

    	JTextField idia = new JTextField();
        JTextField imes = new JTextField();
        JTextField ianio = new JTextField();
        
        String initfin = "inicio";
        Object[] inputs_fecha = {"Ingrese la información de la fecha de "+initfin, "Día:", idia, "Mes:", imes, "Año:", ianio};
        JOptionPane.showOptionDialog(null, inputs_fecha, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
    	
        int D1 = Integer.parseInt(idia.getText());
        int M1 = Integer.parseInt(imes.getText());
        int Y1 = Integer.parseInt(ianio.getText());
        LocalDate inicio = LocalDate.of(Y1, M1, D1);
        
        initfin = "fin";
        Object[] inputs_fecha2 = {"Ingrese la información de la fecha de "+initfin, "Día:", idia, "Mes:", imes, "Año:", ianio};
        JOptionPane.showOptionDialog(null, inputs_fecha2, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
        
        int D2 = Integer.parseInt(idia.getText());
        int M2 = Integer.parseInt(imes.getText());
        int Y2 = Integer.parseInt(ianio.getText());
        LocalDate fin = LocalDate.of(Y2, M2, D2).plusDays(1);
        
        
        int yesno = JOptionPane.showOptionDialog(null, "La tarifa va a aplicar para todos los días de la semana?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        
        ArrayList<LocalDate> diasSemana = new ArrayList<LocalDate>();
        if (yesno == JOptionPane.NO_OPTION) {
        	
            JCheckBox lunes = new JCheckBox("Lunes");
            JCheckBox martes = new JCheckBox("Martes");
            JCheckBox miercoles = new JCheckBox("Miércoles");
            JCheckBox jueves = new JCheckBox("Jueves");
            JCheckBox viernes = new JCheckBox("Viernes");
            JCheckBox sabado = new JCheckBox("Sábado");
            JCheckBox domingo = new JCheckBox("Domingo");
            Object[] options = {"Seleccione los días de la semana donde aplicará la tarifa:", lunes, martes, miercoles, jueves, viernes, sabado, domingo};
            JOptionPane.showOptionDialog(null, options, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
            
            String diasStr = "";
            if (lunes.isSelected()) {
            	diasStr += "1";
            }
            if (martes.isSelected()) {
            	diasStr += "2";
            }
            if (miercoles.isSelected()) {
            	diasStr += "3";
            }
            if (jueves.isSelected()) {
            	diasStr += "4";
            }
            if (viernes.isSelected()) {
            	diasStr += "5";
            }
            if (sabado.isSelected()) {
            	diasStr += "6";
            }
            if (domingo.isSelected()) {
            	diasStr += "7";
            }
            
            String[] diasList = diasStr.split("");
            int[] valorDias = new int[diasList.length];
            DateTimeFormatter formatterDiaSemana = DateTimeFormatter.ofPattern("e");
            int diaSemanaInicio = Integer.valueOf(inicio.format(formatterDiaSemana));
            for (int i = 0; i < diasList.length; i++) {
            	valorDias[i] = Integer.valueOf(diasList[i]) - diaSemanaInicio;
            }
            ;
            for (int d : valorDias) {
            	LocalDate control = LocalDate.of(Y1, M1, D1).plusDays(d);
            	do {
            		diasSemana.add(control);
            		control = control.plusWeeks(1);
            	} while (control.compareTo(fin) < 0);
            }
        }
        else {
            LocalDate control = LocalDate.of(Y1, M1, D1);
            do {
                diasSemana.add(control);
                control = control.plusDays(1);
            } while (control.compareTo(fin) < 0);
        }
        
        
        JRadioButton suite = new JRadioButton("Suite");
        JRadioButton suiteDoble = new JRadioButton("Suite Doble");
        JRadioButton estandar = new JRadioButton("Estándar");
        
        ButtonGroup habitacionesTipo = new ButtonGroup();
        habitacionesTipo.add(suite);
        habitacionesTipo.add(suiteDoble);
        habitacionesTipo.add(estandar);
        Object[] tipos = {"Selecciona el tipo de habitación:", suite, suiteDoble, estandar};
        
        JOptionPane.showOptionDialog(null, tipos, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
        
        String tipo = "";
        if (suite.isSelected()) {
            tipo = "Suite";
        }
        else if (suiteDoble.isSelected()) {
            tipo = "Suite doble";
        }
        else if (estandar.isSelected()) {
            tipo = "Estandar";
        }

        float valor = Float.valueOf(JOptionPane.showInputDialog(null, "Ingrese el valor de la tarifa que aplica en las fechas señaladas:"));

        for (LocalDate fecha : diasSemana) {

            if (tipo.equals("Estandar")) {
                if (hotel.pTarifas.estandar.containsKey(fecha)) {
                	JOptionPane.showMessageDialog(null, "La tarifa para " + fecha + " ya existe, se dejará el menor valor",null,JOptionPane.WARNING_MESSAGE);
                    if ((double) valor < hotel.pTarifas.estandar.get(fecha)) {
                        hotel.pTarifas.estandar.put(fecha, (double) valor);
                        hotel.actualizarTarifas(new Tarifa(inicio, fin, diasSemana, valor, tipo), inicio, fin, valor);
                    }
                } 
            }

            else if (tipo.equals("Suite")) {
                if (hotel.pTarifas.suite.containsKey(fecha)) {
                	JOptionPane.showMessageDialog(null, "La tarifa para " + fecha + " ya existe, se dejará el menor valor",null,JOptionPane.WARNING_MESSAGE);
                    if ((double) valor < hotel.pTarifas.suite.get(fecha)) {
                        hotel.pTarifas.suite.put(fecha, (double) valor);
                        hotel.actualizarTarifas(new Tarifa(inicio, fin, diasSemana, valor, tipo), inicio, fin, valor);
                    }
                } 
            }

            else if (tipo.equals("Suite doble")) {
                if (hotel.pTarifas.suiteDoble.containsKey(fecha)) {
                	JOptionPane.showMessageDialog(null, "La tarifa para " + fecha + " ya existe, se dejará el menor valor",null,JOptionPane.WARNING_MESSAGE);
                    if ((double) valor < hotel.pTarifas.suiteDoble.get(fecha)) {
                        hotel.pTarifas.suiteDoble.put(fecha, (double) valor);
                        hotel.actualizarTarifas(new Tarifa(inicio, fin, diasSemana, valor, tipo), inicio, fin, valor);
                    }
                } 
            }

        }
        JOptionPane.showMessageDialog(null, "Se creó la tarifa correctamente",null,JOptionPane.INFORMATION_MESSAGE);
    }

    public static void Opt4() throws IOException {
    	
    	
    	JTextField numHabitacion = new JTextField();
    	JRadioButton torreA = new JRadioButton("Torre A");
    	JRadioButton torreB = new JRadioButton("Torre B");
    	JRadioButton torreC = new JRadioButton("Torre C");
        
        ButtonGroup usuariosTipo = new ButtonGroup();
        usuariosTipo.add(torreA);
        usuariosTipo.add(torreB);
        usuariosTipo.add(torreC);
        
        Object[] inputs4 = {"Ingrese el número de habitación:", numHabitacion, "Indique en qué torre se ubica la habitación", torreA, torreB, torreC};
        
        int okcancel4 = JOptionPane.showOptionDialog(null, inputs4, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        
        if (okcancel4 == JOptionPane.OK_OPTION) {
        	int id = Integer.valueOf(numHabitacion.getText());
        	String ubicacion = "";
        	if (torreA.isSelected()) {
        		ubicacion = "Torre A";
        	} else if (torreB.isSelected()) {
        		ubicacion = "Torre B";
        	} else if (torreC.isSelected()) {
        		ubicacion = "Torre C";
        	}
        	
        	JTextField camasDobles = new JTextField();
        	JTextField camasSencillas = new JTextField();
        	JCheckBox balcon = new JCheckBox("Balcón");
        	JCheckBox vista = new JCheckBox("Vista especial");
        	JCheckBox cocina = new JCheckBox("Cocina integrada");
        	
        	//nuevo
        	
        	JCheckBox calef = new JCheckBox("Calefacción");
        	JCheckBox AC = new JCheckBox("Aire acondicionado");
        	JCheckBox tele = new JCheckBox("TV");
        	JCheckBox caf = new JCheckBox("Cafetera");
        	JCheckBox hipoa = new JCheckBox("Ropa de cama y tapetes hipoalergénicos");
        	JCheckBox planch = new JCheckBox("Plancha");
        	JCheckBox sec = new JCheckBox("Secador");
        	JCheckBox TipoA = new JCheckBox("Tomas USB-A");
        	JCheckBox TipoC = new JCheckBox("Tomas USB-C");
        	JCheckBox desa = new JCheckBox("Incluye desayuno");
        	JTextField taman = new JTextField(); 
        	JTextField voltaj = new JTextField(); 
        	
        	Object[] inputs4_2 = {"Ingrese la cantidad de camas dobles de la habitación:", camasDobles, "Ingrese la cantidad de camas sencillas de la habitación:", camasSencillas, "Ingrese las características de la habitación", 
        			balcon, vista, cocina, calef, AC, tele, caf, hipoa, planch, sec, TipoA, TipoC, desa,
        			"Ingrese el tamaño en metros cuadrados", taman, "Ingrese el voltaje AC", voltaj};
        	int okcancel4_2 = JOptionPane.showOptionDialog(null, inputs4_2, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        	
        	if (okcancel4_2 == JOptionPane.OK_OPTION) {
        		int dobles = Integer.valueOf(camasDobles.getText());
        		int sencilla = Integer.valueOf(camasSencillas.getText());
        		int capacidad = (dobles * 2) + sencilla;
        		boolean balc = false;
        		boolean vis = false;
        		boolean coc = false;
        		
        		// NUEVO
        		
        		boolean calefaccion = false;
        		boolean aireAC = false;
        		boolean TV = false;
        		boolean cafetera = false;
        		boolean hipoalergenico = false;
        		boolean plancha = false;
        		boolean secador = false;
        		boolean usb_a = false;
        		boolean usb_c = false;
        		boolean desayuno = false;
        				
        		if (balcon.isSelected()) {
        			balc = true;
        		} 
        		if (vista.isSelected()) {
        			vis = true;
        		} 
        		if (cocina.isSelected()) {
        			coc = true;
        		}
        		
        		//nuevo 
        		
        		if (calef.isSelected()) {
        			calefaccion = true;
        		} 
        		if (AC.isSelected()) {
        			aireAC = true;
        		} 
        		if (tele.isSelected()) {
        			TV = true;
        		}
        		if (caf.isSelected()) {
        			cafetera = true;
        		} 
        		if (hipoa.isSelected()) {
        			hipoalergenico = true;
        		} 
        		if (planch.isSelected()) {
        			plancha = true;
        		}
        		if (sec.isSelected()) {
        			secador = true;
        		} 
        		if (TipoA.isSelected()) {
        			usb_a = true;
        		} 
        		if (TipoC.isSelected()) {
        			usb_c = true;
        		}
        		if (desa.isSelected()) {
        			desayuno = true;
        		}
        		
        		int tam = Integer.valueOf(taman.getText());
        		int volt = Integer.valueOf(voltaj.getText());
        		
        		
        		JRadioButton suite = new JRadioButton("Suite");
                JRadioButton suiteDoble = new JRadioButton("Suite Doble");
                JRadioButton estandar = new JRadioButton("Estándar");
        		ButtonGroup habitacionesTipo = new ButtonGroup();
                habitacionesTipo.add(suite);
                habitacionesTipo.add(suiteDoble);
                habitacionesTipo.add(estandar);
                Object[] inputs4_3 = {"Selecciona el tipo de habitación:", suite, suiteDoble, estandar};
                int okcancel4_3 = JOptionPane.showOptionDialog(null, inputs4_3, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                
                String tipo = "";
                if (estandar.isSelected()) {
                	tipo = "Estandar";
                } else if (suite.isSelected()) {
                	tipo = "Suite";
                } else if (suiteDoble.isSelected()) {
                	tipo = "Suite doble";
                }
        		
        		if (okcancel4_3 == JOptionPane.OK_OPTION) {
        			Boolean nin = false;
        			int yesnoNinos = JOptionPane.showOptionDialog(null, "La habitación es para niños?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if (yesnoNinos == JOptionPane.YES_OPTION) {
        				nin = true;
        			}
        			int cNin = 0;
        			if (nin) {
        				cNin = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese la cantidad de niños en la habitación"));
        			}
        			
        			boolean creado = false;
        			if (cNin < capacidad) {
        				creado = hotel.crearHabitacion(id, ubicacion, capacidad, tam, volt, balc, vis, coc, 
        						calefaccion, aireAC, TV, cafetera, hipoalergenico, plancha,
        						secador, usb_a, usb_c, desayuno, tipo, nin, cNin ,hotel.inventario);
        			}
        			if (creado) {
        				JOptionPane.showMessageDialog(null, "La habitación se ha creado correctamente",null,JOptionPane.INFORMATION_MESSAGE);
        			} else {
        				JOptionPane.showMessageDialog(null, "La habitación no se ha creado",null,JOptionPane.WARNING_MESSAGE);
        			}
        		}
        		
        	}
        	
        }
    }

    public static void Opt5() {
    	
    	
    	JTextField consultaY = new JTextField();
    	JRadioButton suite = new JRadioButton("Suite");
        JRadioButton suiteDoble = new JRadioButton("Suite Doble");
        JRadioButton estandar = new JRadioButton("Estándar");
        
        ButtonGroup habitacionesTipo = new ButtonGroup();
        habitacionesTipo.add(suite);
        habitacionesTipo.add(suiteDoble);
        habitacionesTipo.add(estandar);
        Object[] tipos = {"Ingrese el año para el cuál desea verificar si todos los días tienen tarifas:",consultaY ,"Selecciona el tipo de habitación:", suite, suiteDoble, estandar};
        
        int si5 = JOptionPane.showOptionDialog(null, tipos, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
        
        if (si5 == JOptionPane.YES_OPTION) {
        
	        String tipo = "";
	        if (suite.isSelected()) {
	            tipo = "Suite";
	        }
	        else if (suiteDoble.isSelected()) {
	            tipo = "Suite doble";
	        }
	        else if (estandar.isSelected()) {
	            tipo = "Estandar";
	        }
	
	        int Y = Integer.valueOf(consultaY.getText());
	
	        ArrayList<LocalDate> sinTarifa = hotel.verificarTarifaPorTipoyAnio(Y, tipo);
	        if (hotel.verificarTarifaPorAnio(Y)) {
	        	JOptionPane.showMessageDialog(null, "Todos los días del año tienen tarifas asignadas", null, JOptionPane.INFORMATION_MESSAGE);
	        } else if (sinTarifa.size() == 0) {
	        	JOptionPane.showMessageDialog(null, "Todos los días del año tienen tarifa para la habitación "+tipo, null, JOptionPane.INFORMATION_MESSAGE);
	        } else {
	
	            int yesno5 = JOptionPane.showConfirmDialog(null, "No todos los días tienen asignada una tarifa. \nDesea ver los días que no tienen tarifa?", null, JOptionPane.YES_NO_OPTION);
	
	            if (yesno5 == JOptionPane.YES_OPTION) {
	            	JList<LocalDate> lista = new JList<>(sinTarifa.toArray(new LocalDate[sinTarifa.size()]));
	            	JScrollPane scrollLista = new JScrollPane(lista);
	            	JOptionPane.showMessageDialog(null, scrollLista, "Días sin tarifa de habitación "+tipo, JOptionPane.PLAIN_MESSAGE);
	            }
	        }
        }

    }

    public static void Opt6() {
    	
    	JRadioButton mesero = new JRadioButton("Mesero");
        JRadioButton recepcionista = new JRadioButton("Recepcionista");
        JRadioButton guia = new JRadioButton("Guía turístico");
        JRadioButton spa = new JRadioButton("Encargado de spa");
        
        ButtonGroup usuariosTipo = new ButtonGroup();
        usuariosTipo.add(mesero);
        usuariosTipo.add(recepcionista);
        usuariosTipo.add(guia);
        usuariosTipo.add(spa);
        
        JTextField newUsuario = new JTextField();
        JPasswordField newContrasena = new JPasswordField();
        JTextField newID = new JTextField();
        
        Object[] inputs6 = {"Selecciona el tipo de usuario a crear:", mesero, recepcionista, guia, spa, "Ingrese el nuevo nombre de usuario", newUsuario, "Ingrese la nueva contraseña", newContrasena, "Ingrese el nuevo ID de usuario", newID};
        
        int okcancel6 = JOptionPane.showOptionDialog(null, inputs6, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        String tipo = "";
        if (mesero.isSelected()) {
            tipo = "mesero";
        } else if (recepcionista.isSelected()) {
            tipo = "recepcionista";
        } else if (guia.isSelected()) {
            tipo = "guia";
        } else if (spa.isSelected()) {
            tipo = "spa";
        }
        String nombre = newUsuario.getText();
        String contrasena = new String(newContrasena.getPassword());
        String id = newID.getText();

        if (okcancel6 == JOptionPane.OK_OPTION) {
        	hotel.guardarUsuario(tipo, nombre, contrasena, id);
        	JOptionPane.showMessageDialog(null, "Se generó correctamente el usuario " + nombre, null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void Opt7() {
    	
    	JRadioButton servSpa = new JRadioButton("Spa");
        JRadioButton servTour = new JRadioButton("Tour");
        
        ButtonGroup servicioTipo = new ButtonGroup();
        servicioTipo.add(servSpa);
        servicioTipo.add(servTour);

        JTextField newPrecio = new JTextField();
        
        Object[] inputs7 = {"Elija el servicio para cambiar su tarifa:", servSpa, servTour, "Ingrese el nuevo precio del servicio", newPrecio};
        
        int okcancel7 = JOptionPane.showOptionDialog(null, inputs7, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    	
        if (okcancel7 == JOptionPane.OK_OPTION) {
	        float precio = Float.valueOf(newPrecio.getText());
	        if (servSpa.isSelected()) {
	            hotel.actualizarservicio("spa",precio);  } 
	        else if (servTour.isSelected()) {
	            hotel.actualizarservicio("tour",precio);  }
	        JOptionPane.showMessageDialog(null, "Se actualizó correctamente la tarifa", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void Opt8() {
    	
    	int yesno8 = JOptionPane.showConfirmDialog(null, "Desea cargar los menús?",null,JOptionPane.YES_NO_OPTION);
    	
    	if (yesno8 == JOptionPane.YES_OPTION) {
    		hotel.cargarMenus();
    		JOptionPane.showMessageDialog(null, "Se cargaron correctamente los menús", null, JOptionPane.INFORMATION_MESSAGE);
    	}

    }

     public static void Opt9() {
    	 
    	 Object[] ok = {"OK"};
    	 Object[] opciones = {"actualizar menú", "agregar producto", "eliminar producto"};
         int seleccion = JOptionPane.showOptionDialog(null, "Elija una opción", null,JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, null);
         // Actualizar
         if (seleccion == 0) {
        	 
        	 JRadioButton platos0 = new JRadioButton("Platos");
        	 JRadioButton bebidas0 = new JRadioButton("Bebidas");
        	 ButtonGroup tipoAlim0 = new ButtonGroup();
        	 tipoAlim0.add(platos0);
        	 tipoAlim0.add(bebidas0);
        	 JRadioButton hab0 = new JRadioButton("Menú habitación");
        	 JRadioButton res0 = new JRadioButton("Menú restaurante principal");
        	 ButtonGroup menu0 = new ButtonGroup();
        	 menu0.add(hab0);
        	 menu0.add(res0);
        	 Object[] group0 = {"Ingrese la opción a modificar",platos0, bebidas0,"Ingrese el menú al que pertenece esta opción", hab0, res0}; 
        	 int seleccion0 = JOptionPane.showOptionDialog(null, group0, null,JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        	 JTextField Horainit0 = new JTextField();
        	 JTextField Horafin0 = new JTextField();
        	 Object[] horas0 = {"Ingrese la hora de inicio en formato HH:HH :",Horainit0,"Ingrese la hora de finalización en formato HH:HH :",Horafin0};
        	 
        	 if (platos0.isSelected() && hab0.isSelected()) {
        		 
        		 
        		 Set<String> set = hotel.mapaPlatosMenuHabitacion.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        	     set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione el plato a modificar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 String nombre = lista.getSelectedValue();
        		 
        		 float precio = Float.valueOf(JOptionPane.showInputDialog(null,"Ingrese el nuevo precio"));
        		 
        		 JOptionPane.showOptionDialog(null, horas0, null, JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        		 
        		 LocalTime horainit = LocalTime.parse(Horainit0.getText());
        		 LocalTime horafin = LocalTime.parse(Horafin0.getText());
        		 hotel.actualizarMenus(nombre, precio, horainit, horafin, (int) (0.2*precio), true, true);
        	 } 
        	 else if (platos0.isSelected() && res0.isSelected()) {
        		 
        		 Set<String> set = hotel.mapaPlatosMenuComedor.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        	     set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione el plato a modificar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 String nombre = lista.getSelectedValue();
        		 
        		 float precio = Float.valueOf(JOptionPane.showInputDialog(null,"Ingrese el nuevo precio"));
        		 
        		 JOptionPane.showOptionDialog(null, horas0, null, JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        		 
        		 LocalTime horainit = LocalTime.parse(Horainit0.getText());
        		 LocalTime horafin = LocalTime.parse(Horafin0.getText());
        		 hotel.actualizarMenus(nombre, precio, horainit, horafin, (int) (0.2*precio), true, false);
        	 } 
        	 else if (bebidas0.isSelected() && hab0.isSelected()) {
        		 Set<String> set = hotel.mapaBebidasMenuHabitacion.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        		 set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione la bebida a modificar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 String nombre = lista.getSelectedValue();
        		 
        		 float precio = Float.valueOf(JOptionPane.showInputDialog(null,"Ingrese el nuevo precio"));
        		 
        		 JOptionPane.showOptionDialog(null, horas0, null, JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        		 
        		 LocalTime horainit = LocalTime.parse(Horainit0.getText());
        		 LocalTime horafin = LocalTime.parse(Horafin0.getText());
        		 hotel.actualizarMenus(nombre, precio, horainit, horafin, (int) (0.2*precio), false, true);
        	 } 
        	 else if (bebidas0.isSelected() && res0.isSelected()) {
        		 Set<String> set = hotel.mapaBebidasMenuComedor.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        		 set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione la bebida a modificar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 String nombre = lista.getSelectedValue();
        		 
        		 float precio = Float.valueOf(JOptionPane.showInputDialog(null,"Ingrese el nuevo precio"));
        		 
        		 JOptionPane.showOptionDialog(null, horas0, null, JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        		 
        		 LocalTime horainit = LocalTime.parse(Horainit0.getText());
        		 LocalTime horafin = LocalTime.parse(Horafin0.getText());
        		 hotel.actualizarMenus(nombre, precio, horainit, horafin, (int) (0.2*precio), false, false);
        	 } 
         }
         // Agregar
         else if (seleccion == 1) {
        	 JRadioButton platos1 = new JRadioButton("Platos");
        	 JRadioButton bebidas1 = new JRadioButton("Bebidas");
        	 ButtonGroup tipoAlim1 = new ButtonGroup();
        	 tipoAlim1.add(platos1);
        	 tipoAlim1.add(bebidas1);
        	 JRadioButton hab1 = new JRadioButton("Menú habitación");
        	 JRadioButton res1 = new JRadioButton("Menú restaurante principal");
        	 ButtonGroup menu1 = new ButtonGroup();
        	 menu1.add(hab1);
        	 menu1.add(res1);
        	 Object[] group1 = {"Ingrese el tipo de producto a crear:",platos1, bebidas1,"Ingrese el menú donde se ofrecerá el producto:", hab1, res1}; 
        	 JOptionPane.showOptionDialog(null, group1, null,JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        	 
        	 boolean esPlato = false;
        	 boolean esHabitacion = false;
        	 
        	 if (platos1.isSelected()) {esPlato = true;}
        	 else if (bebidas1.isSelected()) {esPlato = false;}
        	 if (hab1.isSelected()) {esHabitacion = true;}
        	 else if (res1.isSelected()) {esHabitacion = false;}
        	 
        	 JTextField nombre1 = new JTextField();
        	 JTextField precio1 = new JTextField();
        	 JTextField Horainit1 = new JTextField();
        	 JTextField Horafin1 = new JTextField();
        	 Object[] horas1 = {"Ingrese el nombre:",nombre1,"Ingrese el precio",precio1,"Ingrese la hora de inicio en formato HH:HH :",Horainit1,"Ingrese la hora de finalización en formato HH:HH :",Horafin1};
        	 JOptionPane.showOptionDialog(null, horas1, null,JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        	 
        	 String nomAgg = nombre1.getText();
        	 float priceAgg = Float.valueOf(precio1.getText());
        	 LocalTime itAgg = LocalTime.parse(Horainit1.getText());
        	 LocalTime etAgg = LocalTime.parse(Horafin1.getText());

        	 hotel.escribirProducto(nomAgg, priceAgg, itAgg, etAgg, (int) (0.2*priceAgg), esPlato, esHabitacion); 
         } 
         // Eliminar
         else if (seleccion == 2) {
        	 JRadioButton platos2 = new JRadioButton("Platos");
        	 JRadioButton bebidas2 = new JRadioButton("Bebidas");
        	 ButtonGroup tipoAlim2 = new ButtonGroup();
        	 tipoAlim2.add(platos2);
        	 tipoAlim2.add(bebidas2);
        	 JRadioButton hab2 = new JRadioButton("Menú habitación");
        	 JRadioButton res2 = new JRadioButton("Menú restaurante principal");
        	 ButtonGroup menu2 = new ButtonGroup();
        	 menu2.add(hab2);
        	 menu2.add(res2);
        	 Object[] group2 = {"Ingrese la opción a la que pertenece lo que se eliminará",platos2, bebidas2,"Ingrese el menú al que pertenece esta opción", hab2, res2}; 
        	 int seleccion2 = JOptionPane.showOptionDialog(null, group2, null,JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, ok, null);
        	 String nombre = "";
        	 if (platos2.isSelected() && hab2.isSelected()) {
        		 
        		 
        		 Set<String> set = hotel.mapaPlatosMenuHabitacion.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        	     set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione el plato a eliminar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 nombre = lista.getSelectedValue();
        		 
        		 hotel.eliminarProducto(nombre, true, true);
        	 } 
        	 else if (platos2.isSelected() && res2.isSelected()) {
        		 
        		 Set<String> set = hotel.mapaPlatosMenuComedor.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        	     set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione el plato a eliminar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 nombre = lista.getSelectedValue();
        		 
        		 hotel.eliminarProducto(nombre, true, false);
        	 } 
        	 else if (bebidas2.isSelected() && hab2.isSelected()) {
        		 Set<String> set = hotel.mapaBebidasMenuHabitacion.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        	     set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione la bebida a eliminar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 nombre = lista.getSelectedValue();
        		 
        		 hotel.eliminarProducto(nombre, false, true);
        	 } 
        	 else if (bebidas2.isSelected() && res2.isSelected()) {
        		 Set<String> set = hotel.mapaBebidasMenuComedor.keySet();
        		 DefaultListModel<String> listModel = new DefaultListModel<>();
        	     set.forEach(listModel::addElement);
        		 
        		 JList<String> lista = new JList<>(listModel);
        		 lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        		 JScrollPane scrollLista = new JScrollPane(lista);
        		 Object[] platosHab = {"Seleccione la bebida a eliminar y presione OK", scrollLista};
        		 JOptionPane.showMessageDialog(null, platosHab, null, JOptionPane.PLAIN_MESSAGE);
        		 nombre = lista.getSelectedValue();
        		 
        		 hotel.eliminarProducto(nombre, false, false);
        	 }
        	 
			JOptionPane.showMessageDialog(null, "Se eliminó "+nombre, null, JOptionPane.WARNING_MESSAGE);
         }
     }
     

     public static void Opt10() {
     	
     	int yesno8 = JOptionPane.showConfirmDialog(null, "Desea ver la ocupación del hotel dado un año?",null,JOptionPane.YES_NO_OPTION);
     	
     	if (yesno8 == JOptionPane.YES_OPTION) {
     		FOcupacion optOcp = new FOcupacion();
     		optOcp.setVisible(true);
     		optOcp.setResizable(false);
     		
     	}

     }
     
     // Nuevo
     
     public static void Opt11() {
    	 //  Ventas por cada producto (en cantidades y en valor total)
    	 new GraficaUnidadesVendidas((HashMap<String, Integer>) Ventas.mapaVentas, 1);
    	 new GraficaUnidadesVendidas((HashMap<String, Integer>) Ventas.hacerMapaPrecios(), 2); 
     }
     
     public static void Opt12() throws Exception {
    	 //  Valor de las facturas a lo largo del tiempo
    	 new GraficaValorFacturas();
     }
     
     public static void Opt13() {
    	 
		HashMap<String, Double> relacionConsumo = hotel.graficaRelacion();
      
         GraficaRelacion grafica = new GraficaRelacion(relacionConsumo);
        
    	 
     }
     
     public static void Opt14() throws FileNotFoundException, IOException {
    	 //  Graf de barras servicios
    	 new GraficaConsumoServicios();
     }
     
     public static void Opt15() {
		HashMap<String, Integer> frecuencia= hotel.graficaServicios();
		JFrame frame = new JFrame("Diagrama de Pastel");
		GraficaServicio grafica = new GraficaServicio(frecuencia);
		grafica.setPreferredSize(new Dimension(950, 600)); 
		frame.getContentPane().add(grafica);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
    	 
     }
     
     
     
     
}
