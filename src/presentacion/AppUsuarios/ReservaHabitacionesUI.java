package presentacion.AppUsuarios;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logica.UsuarioHuesped;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

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
public class ReservaHabitacionesUI extends JFrame {
    private JLabel lblFechaInicio;
    private JLabel lblFechaFinal;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFinal;
    private JButton btnConsultar;
    private LocalDateTime fechainicial;
    private LocalDateTime fechaFinall;
    private JList<String> lstHabitacionesDisponibles;
    private String tipor;
    private String hab;
    private LocalDate fechaInicio2;
    private LocalDate fechaFin2;
    private HashMap<String, ArrayList<LocalDate>> habitacionesOcupadas;
    private UsuarioHuesped usuario = new UsuarioHuesped();
 private JButton btnReservar;

    public ReservaHabitacionesUI() {
        HashMap<String, ArrayList<LocalDate>> habitacionesOcupadass = new HashMap<>();
       
        habitacionesOcupadas = habitacionesOcupadass;
         
        
        UIManager.put("OptionPane.background", new Color(70, 130, 180));
        UIManager.put("Panel.background", new Color(70, 130, 180));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.buttonFont", new Font("Verdana", Font.PLAIN, 18));
        UIManager.put("OptionPane.minimumSize",new Dimension(300, 250));
   
        setTitle("Reserva de Habitaciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        lblFechaInicio = new JLabel("Fecha Inicio:");
        txtFechaInicio = new JTextField(10);
        lblFechaFinal = new JLabel("Fecha Final:");
        txtFechaFinal = new JTextField(10);
        btnConsultar = new JButton("Consultar");
        lstHabitacionesDisponibles = new JList<>();


        add(lblFechaInicio);
        add(txtFechaInicio);
        add(lblFechaFinal);
        add(txtFechaFinal);
        add(btnConsultar);
        add(new JScrollPane(lstHabitacionesDisponibles));

      
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    
                    String fecha1 = txtFechaInicio.getText();
                    LocalDateTime fechaInicior = LocalDateTime.parse(fecha1, formatter);
                    String fecha2 = txtFechaFinal.getText();
                    LocalDateTime fechaFinr = LocalDateTime.parse(fecha2, formatter);
                
                    LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText().substring(0, 10));
                    LocalDate fechaFinal = LocalDate.parse(txtFechaFinal.getText().substring(0, 10));
                
                    fechainicial = fechaInicior;
                    fechaFinall = fechaFinr;
                
                    fechaInicio2 = fechaInicio;
                    fechaFin2 = fechaFinal; 
                
                    ArrayList<String> habitacionesDisponibles = usuario.consultar(fechaInicio, fechaFinal);
                
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    for (String habitacion : habitacionesDisponibles) {
                        listModel.addElement(habitacion);
                    }
                    lstHabitacionesDisponibles.setModel(listModel);
                } catch (DateTimeParseException e5) {
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha. Asegúrate de ingresar una fecha y hora válidas en el formato 'yyyy-MM-dd HH:mm'.");
                } catch (Exception e6) {
                    JOptionPane.showMessageDialog(null, "Error al procesar la consulta de habitaciones disponibles: " + e6.getMessage());
                }
                
            }
        });

        
        lstHabitacionesDisponibles.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedHabitacion = lstHabitacionesDisponibles.getSelectedValue();
                     hab = selectedHabitacion;
                    if (selectedHabitacion != null) {
                        ArrayList<String> infoHabitacion = usuario.consultarEspecif(fechaInicio2, fechaFin2, selectedHabitacion);
                        mostrarInformacionHabitacion(infoHabitacion);
                    }
                }
            }
        });

          
                btnReservar = new JButton("Reservar habitación");

   
                add(btnReservar);
        
                
                btnReservar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String selectedHabitacion = lstHabitacionesDisponibles.getSelectedValue();
                        if (selectedHabitacion != null) {
                            ArrayList<String> infoHabitacion = usuario.consultarEspecif(fechaInicio2, fechaFin2, selectedHabitacion);
                            System.out.println(selectedHabitacion);
               
                            JTextField nombre = new JTextField();
                            JTextField correo = new JTextField();
                            JTextField numero = new JTextField();
                            JTextField documento = new JTextField();
                            JTextField cantidadAdultos = new JTextField();
                            JTextField cantidadNinos = new JTextField();

                            Object[] inputs = {"Nombre: ", nombre, "Correo electrónico", correo, "Documento de identidad", documento, "Número de teléfono", numero, "Cantidad de adultos", cantidadAdultos, "Cantidad niños", cantidadNinos};

                            int option = JOptionPane.showOptionDialog(null, inputs, "Reservar habitaciones", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "Reservar habitaciones");
                            if (option == JOptionPane.OK_OPTION) {
                                try {
                                    String nombreUsuario = nombre.getText();
                                    String correoElectronico = correo.getText();
                                    String numeroTelefono = numero.getText();
                                    String documentoIdentidadd = documento.getText();
                                    String adultos = cantidadAdultos.getText();
                                    String ninos = cantidadNinos.getText();
                                    
                                    LocalDateTime now = LocalDateTime.now();
                                    DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                                    String formattedDateTime = now.format(formattertime);
                                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                                    
                                    LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime, formatters);
                                    
                                    ArrayList<String> x = new ArrayList<>();
                                    x.add(tipor);
                                    String id = usuario.crearRecerva(fechainicial, fechaFinall, (float) 0, null, null, dateTime, hab, nombreUsuario, Long.parseLong(documentoIdentidadd), correoElectronico, Long.parseLong(numeroTelefono), Integer.parseInt(adultos + ninos), x);
                                    
                                    JOptionPane.showMessageDialog(null, "El id de la reserva es: " + id);
                                } catch (NumberFormatException e3) {
                                    JOptionPane.showMessageDialog(null, "Error al procesar la reserva. Asegúrate de ingresar valores numéricos válidos para el documento de identidad, número de teléfono, cantidad de adultos y cantidad de niños.");
                                } catch (DateTimeParseException e1) {
                                    JOptionPane.showMessageDialog(null, "Error al procesar la reserva. El formato de fecha y hora es inválido.");
                                } catch (Exception e2) {
                                    JOptionPane.showMessageDialog(null, "Error al procesar la reserva. Se produjo un error inesperado: " + e2.getMessage());
                                }
                            }
                            
                    }
                }});
    }
    

    private void mostrarInformacionHabitacion(ArrayList<String> infoHabitacion) {
        StringBuilder sb = new StringBuilder();
    
        for (String info : infoHabitacion) {
            sb.append(info).append("\n");
    
            if (info.startsWith("Tipo             :")) {
                String[] palabras = info.split(":");
                if (palabras.length > 1) {
                    tipor = palabras[1].trim();
                }
            }
        }
    
        JButton consultarButton = new JButton("Consultar Características del Hotel");
        consultarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Características del Hotel:\n\n- Parqueadero gratuito en el hotel\n- Piscina climatisada\n- Zonas húmedas\n- Wifi gratis\n- Recepción 24 horas\n- Admite mascotas", "Características del Hotel", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    
        Object[] message = { sb.toString(), consultarButton };
    
        JOptionPane.showMessageDialog(this, message, "Información de la Habitación", JOptionPane.INFORMATION_MESSAGE);
    }
    


}
