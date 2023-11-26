package presentacion.AppRestauranteInterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.EncargadoDeSpa;
import logica.Mesero;
import logica.PMS;
import logica.Restaurante;
import logica.Ventas;
import presentacion.App;
import presentacion.AppAgregarServicio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AppRestaurante extends JFrame {
	
	static PMS hotel = new PMS();
    
    static Restaurante restaurante = new Restaurante(0, "restaurante", null, null);
    
    private boolean existePedido = false;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Quitar
		hotel.cargarUsuarios();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppRestaurante frame = new AppRestaurante();
//					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppRestaurante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 541);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 130, 180));
		
		JLabel lblNewLabel = new JLabel("  ");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel() {
			
			public void paint(Graphics g) {
				
				ImageIcon bg = new ImageIcon(getClass().getResource("/imagenes/rectbg.jpg"));
				Image imagenP = bg.getImage();
				g.drawImage(imagenP, 0, 0, this.getWidth(), this.getHeight(), this);
				setOpaque(false);
				 
				super.paint(g);
				 
			}
		};
		panel_1.repaint();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(30, 144, 255));
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel Center_cont = new JPanel();
		Center_cont.setBackground(new Color(39, 64, 105));
		panel_2.add(Center_cont, BorderLayout.CENTER);
		Center_cont.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton LogIn = new JButton("1. Mostrar menu");
		LogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tipoMenu = {"Menú habitación", "Menú del comedor"};
				int optMenu = JOptionPane.showOptionDialog(null, 
						"Indique el menú para mostrar", 
						"", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, 
						tipoMenu, tipoMenu[0]);
				
				if(tipoMenu[optMenu].equals("Menú habitación")) {
					mostrarMenu(1);
				}
				else if(tipoMenu[optMenu].equals("Menú del comedor")) {
					mostrarMenu(2);
				}
			}
		});
		LogIn.setHorizontalAlignment(SwingConstants.LEFT);
		LogIn.setForeground(new Color(16, 32, 57));
		LogIn.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		LogIn.setBackground(new Color(242, 242, 242));
		Center_cont.add(LogIn);
		
		//-----------------------------------SALIR--------------------------------
		
		JButton Salir = new JButton("Exit");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//------------------------------INICIAR NUEVO PEDIDO-----------------------------
		
		JButton btnActualizarArchivo = new JButton("2. Empezar un nuevo pedido");
		btnActualizarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tipoMenu = {"Menú habitación", "Menú del comedor"};
				int optMenu = JOptionPane.showOptionDialog(null, 
						"Indique el menú donde se agregará", 
						"", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, 
						tipoMenu, tipoMenu[0]);
				
				if(tipoMenu[optMenu].equals("Menú habitación")) {
					restaurante.iniciarPedido(true);
					JOptionPane.showMessageDialog(null, "Se comezó un pedido en habitaión", "", 1);
					existePedido = true;
				}
				else if(tipoMenu[optMenu].equals("Menú del comedor")) {
					restaurante.iniciarPedido(false);
					JOptionPane.showMessageDialog(null, "Se comezó un pedido en el comedor", "", 1);
					existePedido = true;
				}
			}
		});
		btnActualizarArchivo.setHorizontalAlignment(SwingConstants.LEFT);
		btnActualizarArchivo.setForeground(new Color(16, 32, 57));
		btnActualizarArchivo.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		btnActualizarArchivo.setBackground(new Color(242, 242, 242));
		Center_cont.add(btnActualizarArchivo);
		
		//-----------------------------AGREGAR/ELIMINAR PRODUCTO AL PEDIDO----------------------------------
		
		JButton btnCrearNueva = new JButton("3. Agregar/eliminar producto al pedido");
		btnCrearNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(existePedido) {
				if(restaurante.getPedidoActual().esHabitacion()) {
					new AppModificarPedido(1, restaurante);
				} else {
					new AppModificarPedido(2, restaurante);
				}
				
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe crear un nuevo pedido para agregar o eliminar productos");
				}

			}
		});
		btnCrearNueva.setHorizontalAlignment(SwingConstants.LEFT);
		btnCrearNueva.setForeground(new Color(16, 32, 57));
		btnCrearNueva.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		btnCrearNueva.setBackground(new Color(242, 242, 242));
		Center_cont.add(btnCrearNueva);
		
		//------------------------AGREGAR PRODUCTO A LA FACTURA----------------------------------
		
		JButton btnVerificarSi = new JButton("4. Agregar servicio a factura");
		btnVerificarSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existePedido) {
					
					new AppAgregarServicioRestaurante(restaurante);
				
			} 
				else {
				JOptionPane.showMessageDialog(null, "Debe crear un nuevo pedido para agregarlo a la factura.");
			}
			}
		});
		btnVerificarSi.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerificarSi.setForeground(new Color(16, 32, 57));
		btnVerificarSi.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		btnVerificarSi.setBackground(new Color(242, 242, 242));
		Center_cont.add(btnVerificarSi);
		
		Salir.setForeground(new Color(16, 32, 57));
		Salir.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		Salir.setBackground(new Color(242, 242, 242));
		Center_cont.add(Salir);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(39, 64, 105));
		panel_2.add(panel_4, BorderLayout.WEST);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(39, 64, 105));
		panel_2.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBienvenido = new JLabel("            PMS            ");
		lblBienvenido.setForeground(new Color(242, 242, 242));
		lblBienvenido.setVerticalAlignment(SwingConstants.TOP);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Segoe UI Historic", Font.BOLD, 30));
		panel_5.add(lblBienvenido, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Restaurante");
		lblNewLabel_2.setForeground(new Color(242, 242, 242));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		panel_5.add(lblNewLabel_3, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(39, 64, 105));
		panel_2.add(panel_6, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(39, 64, 105));
		panel_2.add(panel_7, BorderLayout.EAST);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8, BorderLayout.SOUTH);
		
	}
	
	public void mostrarMenu(int tipoMenu) {
		new AppMenu(tipoMenu);
	}

}




