package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.PMS;
import presentacion.AppRestauranteInterfaz.AppRestaurante;

import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class App extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static String path;
	private JTextField tfNombre;
	private JPasswordField tfContrasena;
	
    public static void main(String[] args) {;

        PMS hotel = new PMS();
        hotel.cargarUsuarios();
        hotel.cargarServiciosPrecios();
        
        try {
			hotel.cargarPasarelas();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar las pasarelas de pago. Vuelva a iniciar la aplicacion para solucionar el error.", "Error", ABORT);
		}

        HashMap<String, ArrayList<String>> mapaUsuarios = hotel.obtenerUsuarios();
        HashMap<String, String> mapaContrasenas = hotel.obtenerContrasenas();

        App app = new App(mapaUsuarios, mapaContrasenas);
        app.setVisible(true);
		app.setResizable(false);
        
    }
    
    public App(HashMap<String, ArrayList<String>> mapaUsuarios, HashMap<String, String> mapaContrasenas) {
    	
    	getContentPane().setBackground(new Color(3, 24, 53));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((int)(screenWidth/4), (int)(screenHeight/4.5), screenHeight, screenHeight);

		this.setTitle("PMS");
		this.setSize (1000 ,560);
		this.setForeground(new Color(0, 0, 153) );
		JPanel inicio = new JPanel (){
			 
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				
				ImageIcon bg = new ImageIcon(getClass().getResource("/imagenes/login.jpg"));
				Image imagenP = bg.getImage();
				g.drawImage(imagenP, 0, 0, 1000, 560, this);
				setOpaque(false);
				 
				super.paint(g);
				 
			}
				 
				 
		};
		inicio.setBackground(new Color(3, 24, 53));
		inicio.repaint();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(inicio);

		
		JPanel panel_peq = new JPanel();
		panel_peq.setBackground(new Color(242, 242, 242));
		panel_peq.setSize(300, 400);
		panel_peq.setPreferredSize(new Dimension (400,400));
		panel_peq.setMinimumSize(new Dimension (400,400));
		panel_peq.setMaximumSize(new Dimension (400,400));
		inicio.add(panel_peq, Component.CENTER_ALIGNMENT);
		
		GridBagLayout gbl_panel_peq = new GridBagLayout();
		gbl_panel_peq.columnWidths = new int[]{0, 0};
		gbl_panel_peq.rowHeights = new int[]{170, 0, 30, 40, 30, 40, 30, 40, 20, 20, 20};
		gbl_panel_peq.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_peq.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_peq.setLayout(gbl_panel_peq);
		
		JPanel panel_deg = new JPanel();
		panel_deg.setBackground(new Color(39, 64, 105));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_peq.add(panel_deg, gbc_panel);
		panel_deg.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblBanner = new JLabel("PMS");
		lblBanner.setForeground(new Color(242, 242, 242));
		lblBanner.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBanner.setFont(new Font("MS Gothic", Font.BOLD, 50));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		panel_deg.add(lblBanner);
		
		JLabel lblBienvenido = new JLabel("Bienvenido!");
		lblBienvenido.setForeground(new Color(242, 242, 242));
		lblBienvenido.setFont(new Font("MS PGothic", Font.BOLD, 19));
		lblBienvenido.setVerticalAlignment(SwingConstants.TOP);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		panel_deg.add(lblBienvenido);
		
		JLabel lblTipoDeUsuario = new JLabel("          Tipo de usuario");
		lblTipoDeUsuario.setFont(new Font("MS Gothic", Font.BOLD, 13));
		GridBagConstraints gbc_lblTipoDeUsuario = new GridBagConstraints();
		gbc_lblTipoDeUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblTipoDeUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_lblTipoDeUsuario.gridx = 0;
		gbc_lblTipoDeUsuario.gridy = 2;
		panel_peq.add(lblTipoDeUsuario, gbc_lblTipoDeUsuario);
		
		String[] nombres = {"administrador", "recepcionista", "guia", "mesero", "spa"};
		JComboBox<String> boxTipo = new JComboBox<String>(nombres);
		boxTipo.setBackground(new Color(242,242,242));
		boxTipo.setPreferredSize(new Dimension (265,20));
		boxTipo.setMinimumSize(new Dimension (265,20));
		boxTipo.setMaximumSize(new Dimension (265,20));
		GridBagConstraints gbc_boxTipo = new GridBagConstraints();
		gbc_boxTipo.insets = new Insets(0, 0, 5, 0);
		gbc_boxTipo.gridx = 0;
		gbc_boxTipo.gridy = 3;
		panel_peq.add(boxTipo, gbc_boxTipo);
		
		JLabel lblNombre = new JLabel("          Nombre de usuario");
		lblNombre.setFont(new Font("MS Gothic", Font.BOLD, 13));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 4;
		panel_peq.add(lblNombre, gbc_lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setPreferredSize(new Dimension (265,20));
		tfNombre.setMinimumSize(new Dimension (265,20));
		tfNombre.setMaximumSize(new Dimension (265,20));
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tfNombre.gridx = 0;
		gbc_tfNombre.gridy = 5;
		panel_peq.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblPassword = new JLabel("          Contraseña");
		lblPassword.setFont(new Font("MS Gothic", Font.BOLD, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 6;
		panel_peq.add(lblPassword, gbc_lblPassword);
		
		tfContrasena = new JPasswordField();
		tfContrasena.setPreferredSize(new Dimension (265,20));
		tfContrasena.setMinimumSize(new Dimension (265,20));
		tfContrasena.setMaximumSize(new Dimension (265,20));
		GridBagConstraints gbc_tfContrasena = new GridBagConstraints();
		gbc_tfContrasena.insets = new Insets(0, 0, 5, 0);
		gbc_tfContrasena.gridx = 0;
		gbc_tfContrasena.gridy = 7;
		panel_peq.add(tfContrasena, gbc_tfContrasena);
		
		JButton btnIniciar = new JButton("Iniciar sesión");
		btnIniciar.setForeground(new Color(242, 242, 242));
		btnIniciar.setBackground(new Color(39, 64, 105));
		btnIniciar.setFont(new Font("MS PGothic", Font.BOLD, 18));
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		panel_peq.add(btnIniciar, gbc_btnNewButton);
		
		btnIniciar.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				Window ventana = FocusManager.getCurrentManager().getActiveWindow();
				String tipo = (String)boxTipo.getSelectedItem();
				String usuario = (String)tfNombre.getText();
				String contrasena = new String(tfContrasena.getPassword());
				boolean correct;
				try {
					correct = validarUsuario(tipo, usuario, contrasena, mapaUsuarios, mapaContrasenas, ventana);
					if (!correct) {
						JOptionPane.showMessageDialog(null, "Nombre o contraseña incorrectos",null,JOptionPane.ERROR_MESSAGE);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				
			};
		});
	}

    public static boolean validarUsuario(String tipoUsuario, String usuario, String contrasena, HashMap<String, ArrayList<String>> mapaUsuarios, HashMap<String, String> mapaContrasenas, Window ventana) throws FileNotFoundException, IOException{
        if(mapaContrasenas.containsKey(usuario) && mapaContrasenas.get(usuario).equals(contrasena)){

            if (mapaUsuarios.get(tipoUsuario).contains(usuario)){
                if ((mapaContrasenas.get(usuario)).equals(contrasena)){
            if(tipoUsuario.equals("administrador")){

                    AppAdministrador app = new AppAdministrador();
                    app.setVisible(true);
                    app.setResizable(false);
                    ventana.dispose();

            } else if(tipoUsuario.equals("spa")){

                    ventana.dispose();
					AppSpa appS = new AppSpa();
					appS.setVisible(true);

            } else if(tipoUsuario.equals("mesero")){

                ventana.dispose();
				AppRestaurante appR = new AppRestaurante();
				appR.setVisible(true);
            } else if(tipoUsuario.equals("recepcionista")){
				FAppRecep app = new FAppRecep();
				app.setVisible(true);
				app.setResizable(false);
				ventana.dispose();
            } else if(tipoUsuario.equals("guia")){
            	ventana.dispose();
            	AppTour appT = new AppTour();
				appT.setVisible(true);
            }
        }
                return true;
    }else{
        System.out.println("\nInformacion no valida\n");
        return false;
    }
    }else{
        System.out.println("\nInformacion no valida\n");
        return false;
    }
    }

}
