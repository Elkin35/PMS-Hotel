package presentacion;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComboBoxUI;

import logica.Factura;
import logica.PMS;
import logica.Reserva;
import logica.Servicio;
import logica.Tour;
import logica.PasarelaGenerica;
import persistencia.PersistenciaPasarela;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AppPasarelas extends JFrame{
	
	static PMS hotel = new PMS();
	static Factura factura = new Factura();
	static PersistenciaPasarela pPasarela = new PersistenciaPasarela();
	static PasarelaGenerica pasarelaGenerica = new PasarelaGenerica("");
    	
	public AppPasarelas(String idReserva) {
		super();
		
		hotel.cargar();
		hotel.cargarServiciosPrecios();
		hotel.cargarServicios();
        
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
        
        
        JLabel labelTitulo = new JLabel("Pasarelas de pago");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(new Color(242, 242, 242));
        
        JLabel labelServicio = new JLabel();
        labelServicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelServicio.setFont(new Font("Segoe UI Historic", Font.BOLD, 16));
        labelServicio.setHorizontalAlignment(SwingConstants.CENTER);
        labelServicio.setForeground(new Color(242, 242, 242));
        
      //-----------------------------FORMULARIO DE LA TARJETA----------------------------------
        
        JLabel lblPagarAhora = new JLabel("Seleccione la pasarela de pago que desea utilizar: ");
        ArrayList<String> elementosJComboList = hotel.getpasarelasDePagoStr();
        elementosJComboList.add("");
        Object[] elementosJCombo = (elementosJComboList).toArray();
        JComboBox<Object> opcionesPasarelas = new JComboBox<>(elementosJCombo);
        opcionesPasarelas.setSelectedIndex(elementosJComboList.size()-1);
        Checkbox checkPagarTrue = new Checkbox("Sí", false);
        Checkbox checkPagarFalse = new Checkbox("No", true);

        lblPagarAhora.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblPagarAhora.setForeground(new Color(242, 242, 242));
        
        JPanel panelPagar = new JPanel();
        panelPagar.add(lblPagarAhora);
        panelPagar.add(opcionesPasarelas);
        panelPagar.setBackground(new Color(39, 64, 105));
        
        JLabel lblNombreTitular = new JLabel("Ingrese el nombre completo del titular de la tarjeta: ");
        lblNombreTitular.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblNombreTitular.setForeground(new Color(242, 242, 242));
        JLabel emptyLabel = new JLabel(); emptyLabel.setPreferredSize(new Dimension(0, 0)); emptyLabel.setMinimumSize(new Dimension(0, 0));
        JTextField tfNombreTitular = new JTextField(15);
        
        JPanel panNombreTitular = new JPanel();
        panNombreTitular.add(lblNombreTitular);
        panNombreTitular.add(tfNombreTitular);
        panNombreTitular.setBackground(new Color(39, 64, 105));
        
        JLabel lblNumTarjeta = new JLabel("Ingrese el numero de la tarjeta: ");
        lblNumTarjeta.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblNumTarjeta.setForeground(new Color(242, 242, 242));
        JTextField tfNumTarjeta = new JTextField(20);
        
        JPanel panNumTarjeta = new JPanel();
        panNumTarjeta.add(lblNumTarjeta);
        panNumTarjeta.add(tfNumTarjeta);
        panNumTarjeta.setBackground(new Color(39, 64, 105));
        
        JLabel lblCaducidad = new JLabel("Ingrese la fecha de caducidad de la tarjeta (mes/año): ");
        lblCaducidad.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblCaducidad.setForeground(new Color(242, 242, 242));
        JTextField tfCaducidad = new JTextField(10);
        
        JPanel panCaducidad = new JPanel();
        panCaducidad.add(lblCaducidad);
        panCaducidad.add(tfCaducidad);
        panCaducidad.setBackground(new Color(39, 64, 105));
        
        
        JLabel lblSeguridad = new JLabel("Ingrese El codigo de seguridad de la tarjeta (CVC): ");
        lblSeguridad.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblSeguridad.setForeground(new Color(242, 242, 242));
        JTextField tfSeguridad = new JTextField(5);
        
        JPanel panSeguridad = new JPanel();
        panSeguridad.add(lblSeguridad);
        panSeguridad.add(tfSeguridad);
        panSeguridad.setBackground(new Color(39, 64, 105));
        
        //-------------------------------------------------------------------
        
        JLabel lblSaldo = new JLabel("Ingrese el saldo a pagar. (La tarifa es: )");
        lblSaldo.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblSaldo.setForeground(new Color(242, 242, 242));
        JTextField tfSaldo = new JTextField(20);
        tfSaldo.setEnabled(false);
        tfSaldo.setBackground(new Color(161, 159, 158));
        
        JPanel panSaldo = new JPanel();
        panSaldo.add(lblSaldo);
        panSaldo.add(tfSaldo);
        panSaldo.setBackground(new Color(39, 64, 105));
        
        //---------------------Valor a pagar----------------------------------
        
        float precioNeto = factura.getPrecioNeto(idReserva);
        float iva = factura.getIva(idReserva);
        float total = precioNeto + iva;
        
        JLabel lblPrecioNeto = new JLabel("El valor neto a pagar es: " + precioNeto);
        lblPrecioNeto.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblPrecioNeto.setForeground(new Color(242, 242, 242));
        
        JLabel lblIva = new JLabel("El valor del IVA es: " + iva);
        lblIva.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblIva.setForeground(new Color(242, 242, 242));

        JLabel lblPrecioTotal = new JLabel("El valor total a pagar es (neto + IVA): " + total);
        lblPrecioTotal.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblPrecioTotal.setForeground(new Color(242, 242, 242));
        
        //---------------------------------------------------------------------
        
        
        JButton btnPagar = new JButton("Pagar");
        btnPagar.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        btnPagar.setBackground(new Color(242, 242, 242));
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        btnCancelar.setBackground(new Color(242, 242, 242));
        
        JPanel panButton = new JPanel();
        panButton.add(btnPagar);
        panButton.add(btnCancelar);
        panButton.setBackground(new Color(39, 64, 105));
        
        
        btnPagar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String namePasarela = (String) opcionesPasarelas.getSelectedItem();
				String nameTitular = tfNombreTitular.getText();
				String numTarjeta = tfNumTarjeta.getText();
				String caducidad = tfCaducidad.getText();
				String codSeguridad = tfSeguridad.getText();
				
				pasarelaGenerica.setNombre(namePasarela);
				
				String resultadoPagar = pasarelaGenerica.realizarPago(idReserva, namePasarela, nameTitular, numTarjeta, precioNeto+"", iva+"", total+"", caducidad, codSeguridad);
				
				JOptionPane.showMessageDialog(null, resultadoPagar, "", JOptionPane.INFORMATION_MESSAGE);
				
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
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 35)));
        panelFormulario.add(panelPagar);
        panelFormulario.add(panNombreTitular);
        panelFormulario.add(panNumTarjeta);
        panelFormulario.add(panCaducidad);
        panelFormulario.add(panSeguridad);
        panelFormulario.add(lblPrecioNeto);
        panelFormulario.add(lblIva);
        panelFormulario.add(lblPrecioTotal);
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        panelFormulario.add(panButton);
        
        panelFormulario.add(panelGrid, BorderLayout.CENTER);
        
        panelGrid.setBackground(new Color(39, 64, 105));
        panelFormulario.setBackground(new Color(39, 64, 105));
		
        panelGen.add(panelFormulario, BorderLayout.CENTER);
        
        panelGen.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.add(panelGen, BorderLayout.CENTER);
        
        this.setLocation((this.getWidth()/2), (this.getHeight()/2));
        Dimension tamano = new Dimension(700, 750);
        this.setSize(tamano);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
    public static void main(String[] args) {
    	new AppPasarelas("101");
    }
}
