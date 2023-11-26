package presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class PRegistro extends JPanel {

    private JButton confirmButton;
    private JButton resetButton;
    private JButton addPersonButton;
    private JPanel personasPanel;
    private JScrollPane scrollPane;
    private String id;
    

    public PRegistro(ArrayList<String> info) {
    
      
        JFrame frame = new JFrame("Reservación");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(70, 130, 180));
     
        personasPanel = new JPanel();
        personasPanel.setLayout(new BoxLayout(personasPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(personasPanel);
        add(scrollPane, BorderLayout.CENTER);

       // Crear panel para mostrar información de la reserva
       JPanel infoPanel = new JPanel(new GridLayout(5, 2));
       infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       JLabel fechaInicioLabel = new JLabel("Fecha de inicio:");
       JLabel fechaFinLabel = new JLabel("Fecha de fin:");
       JLabel huespedLabel = new JLabel("Nombre del huésped:");
       JLabel infoClienteLabel = new JLabel("Información del cliente:");
       JLabel habitacionLabel = new JLabel("Habitación:");
       JLabel fechaInicioValue = new JLabel(info.get(0));
       JLabel fechaFinValue = new JLabel(info.get(1));
       JLabel huespedValue = new JLabel(info.get(2));
       JLabel infoClienteValue = new JLabel(info.get(3));
       JLabel habitacionValue = new JLabel(info.get(4));
       infoPanel.add(fechaInicioLabel);
       infoPanel.add(fechaInicioValue);
       infoPanel.add(fechaFinLabel);
       infoPanel.add(fechaFinValue);
       infoPanel.add(huespedLabel);
       infoPanel.add(huespedValue);
       infoPanel.add(infoClienteLabel);
       infoPanel.add(infoClienteValue);
       infoPanel.add(habitacionLabel);
       infoPanel.add(habitacionValue);
       add(infoPanel, BorderLayout.PAGE_START);
       JPanel topPanel = new JPanel(new BorderLayout());
       add(topPanel, BorderLayout.PAGE_START);
       
       JPanel infoAcompanantesPanel = new JPanel(new GridLayout(1, 2, 20, 0));
       JLabel infoLabel = new JLabel("Acompañantes registrados: "+ info.get(3));
       infoLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
       infoAcompanantesPanel.add(infoLabel);
       topPanel.add(infoAcompanantesPanel, BorderLayout.LINE_START);
       
       addPersonButton = new JButton("Agregar persona");
    
       addPersonButton.setFont(new Font("Verdana", Font.BOLD, 15));
       addPersonButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               PersonaPanel personaPanel = new PersonaPanel();
               personasPanel.add(personaPanel);
               personasPanel.revalidate();
               personasPanel.repaint();
           }
       });
       topPanel.add(addPersonButton, BorderLayout.LINE_END);
       


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        confirmButton = new JButton("Confirmar");
        confirmButton.setBackground(new Color(51, 153, 204));
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        confirmButton.setForeground(Color.WHITE);
        buttonPanel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que los campos estén llenos
                boolean camposLlenos = true;
                for (Component c : personasPanel.getComponents()) {
                    if (c instanceof PersonaPanel) {
                        PersonaPanel p = (PersonaPanel) c;
                        if (p.nombreField.getText().isEmpty() || p.documentoField.getText().isEmpty() || p.correoField.getText().isEmpty()
                                || p.celularField.getText().isEmpty() || p.fechaNacField.getText().isEmpty()) {
                            camposLlenos = false;
                            break;
                        }
                    }
                }
                if (!camposLlenos) {
                    JOptionPane.showMessageDialog(PRegistro.this, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
         
                // Crear objeto acompañante para cada persona y agregar su información a la lista acompanantes
                boolean x = false;
                for (Component c : personasPanel.getComponents()) {
                    if (c instanceof PersonaPanel) {
                        PersonaPanel p = (PersonaPanel) c;
                        String nombre = p.nombreField.getText();
                        String documento = p.documentoField.getText();
                        String correo = p.correoField.getText();
                        String celular = p.celularField.getText();
                        String fecha = p.fechaNacField.getText();
                        ArrayList<String> acompanante = new ArrayList<>();
                
                        acompanante.add(nombre);
                        acompanante.add(documento);
                        acompanante.add(correo);
                        acompanante.add(celular);
                        acompanante.add(fecha);
                        x = FAppRecep.agregarAcompanantes(acompanante);
                        
                    }
                }
                if (x==true){
                JOptionPane.showMessageDialog(PRegistro.this, "Se ha registrado correctamente el acompañante.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                FAppRecep.agregarAcompanantesfin();
                frame.dispose();
                }
            }
        });
        
        resetButton = new JButton("Cancelar");

        resetButton.setBackground(new Color(51, 153, 204));
        resetButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        resetButton.setForeground(Color.WHITE);
        buttonPanel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PRegistro.this);
                frame.dispose();
            }
        });

        add(buttonPanel, BorderLayout.PAGE_END);

        frame.getContentPane().add(this);
        frame.setSize(1200, 600);
        frame.setVisible(true);

    }

    private class PersonaPanel extends JPanel {
        private JTextField nombreField;
        private JTextField documentoField;
        private JTextField correoField;
        private JTextField celularField;
        private JTextField fechaNacField;

        public PersonaPanel() {
            setLayout(new GridLayout(5, 2));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            setBackground(new Color(240, 240, 240));

            nombreField = new JTextField();
            add(new JLabel("Nombre:"));
            add(nombreField);

            documentoField = new JTextField();
            add(new JLabel("Documento:"));
            add(documentoField);

            correoField = new JTextField();
            add(new JLabel("Correo:"));
            add(correoField);

            celularField = new JTextField();
            add(new JLabel("Celular:"));
            add(celularField);

            fechaNacField = new JTextField();
            add(new JLabel("Fecha de nacimiento:"));
            add(fechaNacField);
        }
        
    }


}