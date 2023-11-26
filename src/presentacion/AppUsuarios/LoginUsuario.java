package presentacion.AppUsuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import logica.PMS;
import presentacion.AppAdministrador;


public class LoginUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfNombre;
	private JPasswordField tfContrasena;
	private static PMS hotel = new PMS();
	
	public static void main(String[] args) {

        hotel.cargarUsuarios();
        hotel.cargarServiciosPrecios();

        HashMap<String, ArrayList<String>> mapaUsuarios = hotel.obtenerUsuarios();
        HashMap<String, String> mapaContrasenas = hotel.obtenerContrasenas();
        
		LoginUsuario frame = new LoginUsuario(mapaUsuarios, mapaContrasenas);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public LoginUsuario(HashMap<String, ArrayList<String>> mapaUsuarios, HashMap<String, String> mapaContrasenas) {

		getContentPane().setBackground(new Color(3, 24, 53));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((int)(screenWidth/4), (int)(screenHeight/4.5), screenHeight, screenHeight);

		this.setTitle("PMS client");
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
		
		JLabel lblBanner = new JLabel("App PMS");
		lblBanner.setForeground(new Color(242, 242, 242));
		lblBanner.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBanner.setFont(new Font("MS Gothic", Font.BOLD, 50));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		panel_deg.add(lblBanner);
		
		JLabel lblBienvenido = new JLabel("Bienvenido cliente!");
		lblBienvenido.setForeground(new Color(242, 242, 242));
		lblBienvenido.setFont(new Font("MS PGothic", Font.BOLD, 19));
		lblBienvenido.setVerticalAlignment(SwingConstants.TOP);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		panel_deg.add(lblBienvenido);
        
		
		JLabel lblNombre = new JLabel("          Nombre de usuario");
		lblNombre.setFont(new Font("MS Gothic", Font.BOLD, 13));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		panel_peq.add(lblNombre, gbc_lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setPreferredSize(new Dimension (265,20));
		tfNombre.setMinimumSize(new Dimension (265,20));
		tfNombre.setMaximumSize(new Dimension (265,20));
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tfNombre.gridx = 0;
		gbc_tfNombre.gridy = 4;
		panel_peq.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblPassword = new JLabel("          Contraseña");
		lblPassword.setFont(new Font("MS Gothic", Font.BOLD, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 5;
		panel_peq.add(lblPassword, gbc_lblPassword);
		
		tfContrasena = new JPasswordField();
		tfContrasena.setPreferredSize(new Dimension (265,20));
		tfContrasena.setMinimumSize(new Dimension (265,20));
		tfContrasena.setMaximumSize(new Dimension (265,20));
		GridBagConstraints gbc_tfContrasena = new GridBagConstraints();
		gbc_tfContrasena.insets = new Insets(0, 0, 5, 0);
		gbc_tfContrasena.gridx = 0;
		gbc_tfContrasena.gridy = 6;
		panel_peq.add(tfContrasena, gbc_tfContrasena);
		
		JButton btnIniciar = new JButton("Iniciar sesión");
		btnIniciar.setForeground(new Color(242, 242, 242));
		btnIniciar.setBackground(new Color(39, 64, 105));
		btnIniciar.setFont(new Font("MS PGothic", Font.BOLD, 18));
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 8;
		panel_peq.add(btnIniciar, gbc_btnNewButton);
		
		JLabel SignIn = new JLabel("No tienes usuario? Click acá!");
        SignIn.setOpaque(false);
        SignIn.setBorder(BorderFactory.createEmptyBorder());
        SignIn.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblSignIn = new GridBagConstraints();
        gbc_lblSignIn.anchor = GridBagConstraints.WEST;
		gbc_lblSignIn.insets = new Insets(0, 0, 5, 0);
		gbc_lblSignIn.gridx = 0;
		gbc_lblSignIn.gridy = 10;
		panel_peq.add(SignIn, gbc_lblSignIn);
		
		SignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	JTextField username = new JTextField();
            	JPasswordField password = new JPasswordField();
            	Object[] inputs = {"Ingrese su nuevo nombre de usuario:", username, "Ingrese su nueva contraseña", password};
            	int okcancel = JOptionPane.showOptionDialog(null, inputs, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            	if (okcancel == JOptionPane.OK_OPTION) {
            		try {
            			hotel.guardarUsuario("cliente", username.getText(), new String(password.getPassword()), "cl");
            			JOptionPane.showMessageDialog(null, "El usuario se ha registrado", "", JOptionPane.INFORMATION_MESSAGE);	
            		} catch (Exception e1) {
            			JOptionPane.showMessageDialog(null, "La información proporcionada no es válida", "(!)", JOptionPane.WARNING_MESSAGE);	
            		}
            		
            	}
            }
        });
		
		btnIniciar.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				Window ventana = FocusManager.getCurrentManager().getActiveWindow();
				String usuario = (String)tfNombre.getText();
				String contrasena = new String(tfContrasena.getPassword());
				boolean correct;
				try {
					correct = validarUsuario(usuario, contrasena, mapaUsuarios, mapaContrasenas, ventana);
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
	
	public static boolean validarUsuario(String usuario, String contrasena, HashMap<String, ArrayList<String>> mapaUsuarios, HashMap<String, String> mapaContrasenas, Window ventana) throws FileNotFoundException, IOException{
        if(mapaContrasenas.containsKey(usuario) && mapaContrasenas.get(usuario).equals(contrasena)){

            if (mapaUsuarios.get("cliente").contains(usuario)){
                if ((mapaContrasenas.get(usuario)).equals(contrasena)){
                	AppUsuario app = new AppUsuario();
                    app.setVisible(true);
                    app.setResizable(false);
                    ventana.dispose();

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
