package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.PMS;
import logica.Spa;


public class AppSpa extends JFrame{
	
	private JPanel contentPane;
	
	static Spa spa = new Spa(0, "spa", null, null, null);
	static PMS hotel = new PMS();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		float precioSpa = hotel.getServiciosPrecios().get("spa");
        spa.settarifaServicio((int) precioSpa);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppSpa frame = new AppSpa();
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
	public AppSpa() {
		float precioSpa = hotel.getServiciosPrecios().get("spa");
        spa.settarifaServicio((int) precioSpa);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(551, 541);
		setLocationRelativeTo(null);
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
		
		//-------------------AGREGAR SERVICIO A LA FACTURA----------------------
		
		JButton LogIn = new JButton("Agregar servicio a factura");
		
		LogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AppAgregarServicio(spa);
			}
			
		});
		
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
		
		JLabel lblNewLabel_2 = new JLabel("Spa");
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

}
