package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.PMS;

public class FOcupacion extends JFrame {

	static PMS hotel = new PMS();
	private int totalReservas;
	private HashMap<Integer, HashMap<Integer, Integer>> ocupacion;

	@SuppressWarnings("unchecked")
	public FOcupacion() {
		
		Object[] infoOcupacion = hotel.formatOcupacion();
		totalReservas = (int) infoOcupacion[0];
		ocupacion = (HashMap<Integer, HashMap<Integer, Integer>>) infoOcupacion[1];
		Set<Integer> anios = ocupacion.keySet();
		
		getContentPane().setBackground(new Color(3, 24, 53));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds((int)(screenWidth/4), (int)(screenHeight/12), screenHeight, screenHeight);

		this.setTitle("PMS");
		this.setSize (800 ,900);
		this.setForeground(new Color(0, 0, 153) );
		JPanel inicio = new JPanel (){
			 
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				
				ImageIcon bg = new ImageIcon(getClass().getResource("/imagenes/rectbg.jpg"));
				Image imagenP = bg.getImage();
				g.drawImage(imagenP, 0, 0, 1000 ,900, this);
				setOpaque(false);
				 
				super.paint(g);
				 
			}
				 
				 
		};
		inicio.repaint();
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(inicio);

		
		JPanel panel_peq = new JPanel();
		panel_peq.setBackground(new Color(39, 64, 105));
		panel_peq.setPreferredSize(new Dimension (650,830));
		panel_peq.setMinimumSize(new Dimension (650,830));
		panel_peq.setMaximumSize(new Dimension (650,830));
		inicio.add(panel_peq, Component.CENTER_ALIGNMENT);
		panel_peq.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_interno = new JPanel();
		panel_interno.setBackground(new Color(39, 64, 105));
		panel_peq.add(panel_interno, BorderLayout.CENTER);
		panel_interno.setLayout(new GridLayout(29, 1, 0, 10));
		
		JLabel spc = new JLabel("");
		panel_interno.add(spc);
		
		JLabel title = new JLabel("PMS");
		title.setForeground(new Color(242, 242, 242));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 24));
		panel_interno.add(title);
		
		JLabel lblSeleccion = new JLabel("Indique un año para consultar");
		lblSeleccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeleccion.setForeground(new Color(144, 198, 244));
		lblSeleccion.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblSeleccion);
		
		Integer[] arrayAnios = anios.stream().toArray(Integer[]::new);
		Arrays.sort(arrayAnios);
		JComboBox<Integer> aniosCombo = new JComboBox<Integer>(arrayAnios);
		aniosCombo.setFont(new Font("Verdana", Font.BOLD, 12));
		aniosCombo.setBackground(Color.WHITE);
		aniosCombo.setPreferredSize(new Dimension (350,430));
		aniosCombo.setMinimumSize(new Dimension (350,430));
		aniosCombo.setMaximumSize(new Dimension (350,430));
		panel_interno.add(aniosCombo);
		
		JLabel blank = new JLabel("    ");
		panel_peq.add(blank, BorderLayout.SOUTH);
		
		JLabel blank1 = new JLabel("                                   ");
		panel_peq.add(blank1, BorderLayout.WEST);
		
		JLabel blank2 = new JLabel("                                   ");
		panel_peq.add(blank2, BorderLayout.EAST);
		
		int anioSelected = (int) aniosCombo.getSelectedItem();
		HashMap<Integer, Integer> mapaMeses = ocupacion.get(anioSelected);
		
		JLabel lblEnero = new JLabel("Enero");
		lblEnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnero.setForeground(Color.WHITE);
		lblEnero.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblEnero);
		
		JProgressBar pbEnero = new JProgressBar();
		float ocpEnero;
		try {ocpEnero = mapaMeses.get(1);}
		catch (Exception e) {ocpEnero = 0;}
		pbEnero.setValue((int) (ocpEnero/totalReservas * 100));
		pbEnero.setStringPainted(true);
		panel_interno.add(pbEnero);
		
		JLabel lblFebrero = new JLabel("Febrero");
		lblFebrero.setHorizontalAlignment(SwingConstants.CENTER);
		lblFebrero.setForeground(Color.WHITE);
		lblFebrero.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblFebrero);
		
		JProgressBar pbFebrero = new JProgressBar();
		float ocpFebrero;
		try {ocpFebrero = mapaMeses.get(2);}
		catch (Exception e) {ocpFebrero = 0;}
		pbFebrero.setValue((int) (ocpFebrero/totalReservas * 100));
		pbFebrero.setStringPainted(true);
		panel_interno.add(pbFebrero);
		
		JLabel lblMarzo = new JLabel("Marzo");
		lblMarzo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarzo.setForeground(Color.WHITE);
		lblMarzo.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblMarzo);
		
		JProgressBar pbMarzo = new JProgressBar();
		float ocpMarzo;
		try {ocpMarzo = mapaMeses.get(3);}
		catch (Exception e) {ocpMarzo = 0;}
		pbMarzo.setValue((int) (ocpMarzo/totalReservas * 100));
		pbMarzo.setStringPainted(true);
		panel_interno.add(pbMarzo);
		
		JLabel lblAbril = new JLabel("Abril");
		lblAbril.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbril.setForeground(Color.WHITE);
		lblAbril.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblAbril);
		
		JProgressBar pbAbril = new JProgressBar();
		float ocpAbril;
		try {ocpAbril = mapaMeses.get(4);}
		catch (Exception e) {ocpAbril = 0;}
		pbAbril.setValue((int) (ocpAbril/totalReservas * 100));
		pbAbril.setStringPainted(true);
		panel_interno.add(pbAbril);
		
		JLabel lblMayo = new JLabel("Mayo");
		lblMayo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMayo.setForeground(Color.WHITE);
		lblMayo.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblMayo);
		
		JProgressBar pbMayo = new JProgressBar();
		float ocpMayo;
		try {ocpMayo = mapaMeses.get(5);}
		catch (Exception e) {ocpMayo = 0;}
		pbMayo.setValue((int) (ocpMayo/totalReservas * 100));
		pbMayo.setStringPainted(true);
		panel_interno.add(pbMayo);
		
		JLabel lblJunio = new JLabel("Junio");
		lblJunio.setHorizontalAlignment(SwingConstants.CENTER);
		lblJunio.setForeground(Color.WHITE);
		lblJunio.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblJunio);
		
		JProgressBar pbJunio = new JProgressBar();
		float ocpJunio;
		try {ocpJunio = mapaMeses.get(6);}
		catch (Exception e) {ocpJunio = 0;}
		pbJunio.setValue((int) (ocpJunio/totalReservas * 100));
		pbJunio.setStringPainted(true);
		panel_interno.add(pbJunio);
		
		JLabel lblJulio = new JLabel("Julio");
		lblJulio.setHorizontalAlignment(SwingConstants.CENTER);
		lblJulio.setForeground(Color.WHITE);
		lblJulio.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblJulio);
		
		JProgressBar pbJulio = new JProgressBar();
		float ocpJulio;
		try {ocpJulio = mapaMeses.get(7);}
		catch (Exception e) {ocpJulio = 0;}
		pbJulio.setValue((int) (ocpJulio/totalReservas * 100));
		pbJulio.setStringPainted(true);
		panel_interno.add(pbJulio);
		
		JLabel lblAgosto = new JLabel("Agosto");
		lblAgosto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgosto.setForeground(Color.WHITE);
		lblAgosto.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblAgosto);
		
		JProgressBar pbAgosto = new JProgressBar();
		float ocpAgosto;
		try {ocpAgosto = mapaMeses.get(8);}
		catch (Exception e) {ocpAgosto = 0;}
		pbAgosto.setValue((int) (ocpAgosto/totalReservas * 100));
		pbAgosto.setStringPainted(true);
		panel_interno.add(pbAgosto);
		
		JLabel lblSeptiembre = new JLabel("Septiembre");
		lblSeptiembre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeptiembre.setForeground(Color.WHITE);
		lblSeptiembre.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblSeptiembre);
		
		JProgressBar pbSeptiembre = new JProgressBar();
		float ocpSeptiembre;
		try {ocpSeptiembre = mapaMeses.get(9);}
		catch (Exception e) {ocpSeptiembre = 0;}
		pbSeptiembre.setValue((int) (ocpSeptiembre/totalReservas * 100));
		pbSeptiembre.setStringPainted(true);
		panel_interno.add(pbSeptiembre);
		
		JLabel lblOctubre = new JLabel("Octubre");
		lblOctubre.setHorizontalAlignment(SwingConstants.CENTER);
		lblOctubre.setForeground(Color.WHITE);
		lblOctubre.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblOctubre);
		
		JProgressBar pbOctubre = new JProgressBar();
		float ocpOctubre;
		try {ocpOctubre = mapaMeses.get(10);}
		catch (Exception e) {ocpOctubre = 0;}
		pbOctubre.setValue((int) (ocpOctubre/totalReservas * 100));
		pbOctubre.setStringPainted(true);
		panel_interno.add(pbOctubre);
		
		JLabel lblNoviembre = new JLabel("Noviembre");
		lblNoviembre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoviembre.setForeground(Color.WHITE);
		lblNoviembre.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblNoviembre);
		
		JProgressBar pbNoviembre = new JProgressBar();
		float ocpNoviembre;
		try {ocpNoviembre = mapaMeses.get(11);}
		catch (Exception e) {ocpNoviembre = 0;}
		pbNoviembre.setValue((int) (ocpNoviembre/totalReservas * 100));
		pbNoviembre.setStringPainted(true);
		panel_interno.add(pbNoviembre);
		
		JLabel lblDiciembre = new JLabel("Diciembre");
		lblDiciembre.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiciembre.setForeground(Color.WHITE);
		lblDiciembre.setFont(new Font("Verdana", Font.BOLD, 12));
		panel_interno.add(lblDiciembre);
		
		JProgressBar pbDiciembre = new JProgressBar();
		float ocpDiciembre;
		try {ocpDiciembre = mapaMeses.get(12);}
		catch (Exception e) {ocpDiciembre = 0;}
		pbDiciembre.setValue((int) (ocpDiciembre/totalReservas * 100));
		pbDiciembre.setStringPainted(true);
		panel_interno.add(pbDiciembre);
		
		aniosCombo.addActionListener(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				int anioSelected = (int) aniosCombo.getSelectedItem();
				HashMap<Integer, Integer> mapaMeses = ocupacion.get(anioSelected);
				
				float ocpEnero;
				try {ocpEnero = mapaMeses.get(1);}
				catch (Exception e2) {ocpEnero = 0;}
				pbEnero.setValue((int) (ocpEnero/totalReservas * 100));
				pbEnero.setStringPainted(true);
				
				float ocpFebrero;
				try {ocpFebrero = mapaMeses.get(2);}
				catch (Exception e2) {ocpFebrero = 0;}
				pbFebrero.setValue((int) (ocpFebrero/totalReservas * 100));
				pbFebrero.setStringPainted(true);
				
				float ocpMarzo;
				try {ocpMarzo = mapaMeses.get(3);}
				catch (Exception e2) {ocpMarzo = 0;}
				pbMarzo.setValue((int) (ocpMarzo/totalReservas * 100));
				pbMarzo.setStringPainted(true);
				
				float ocpAbril;
				try {ocpAbril = mapaMeses.get(4);}
				catch (Exception e2) {ocpAbril = 0;}
				pbAbril.setValue((int) (ocpAbril/totalReservas * 100));
				pbAbril.setStringPainted(true);
				
				float ocpMayo;
				try {ocpMayo = mapaMeses.get(5);}
				catch (Exception e2) {ocpMayo = 0;}
				pbMayo.setValue((int) (ocpMayo/totalReservas * 100));
				pbMayo.setStringPainted(true);
				
				float ocpJunio;
				try {ocpJunio = mapaMeses.get(6);}
				catch (Exception e2) {ocpJunio = 0;}
				pbJunio.setValue((int) (ocpJunio/totalReservas * 100));
				pbJunio.setStringPainted(true);
				
				float ocpJulio;
				try {ocpJulio = mapaMeses.get(7);}
				catch (Exception e2) {ocpJulio = 0;}
				pbJulio.setValue((int) (ocpJulio/totalReservas * 100));
				pbJulio.setStringPainted(true);
				
				float ocpAgosto;
				try {ocpAgosto = mapaMeses.get(8);}
				catch (Exception e2) {ocpAgosto = 0;}
				pbAgosto.setValue((int) (ocpAgosto/totalReservas * 100));
				pbAgosto.setStringPainted(true);
				
				float ocpSeptiembre;
				try {ocpSeptiembre = mapaMeses.get(9);}
				catch (Exception e2) {ocpSeptiembre = 0;}
				pbSeptiembre.setValue((int) (ocpSeptiembre/totalReservas * 100));
				pbSeptiembre.setStringPainted(true);
				
				float ocpOctubre;
				try {ocpOctubre = mapaMeses.get(10);}
				catch (Exception e2) {ocpOctubre = 0;}
				pbOctubre.setValue((int) (ocpOctubre/totalReservas * 100));
				pbOctubre.setStringPainted(true);
				
				float ocpNoviembre;
				try {ocpNoviembre = mapaMeses.get(11);}
				catch (Exception e2) {ocpNoviembre = 0;}
				pbNoviembre.setValue((int) (ocpNoviembre/totalReservas * 100));
				pbNoviembre.setStringPainted(true);
				
				float ocpDiciembre;
				try {ocpDiciembre = mapaMeses.get(12);}
				catch (Exception e2) {ocpDiciembre = 0;}
				pbDiciembre.setValue((int) (ocpDiciembre/totalReservas * 100));
				pbDiciembre.setStringPainted(true);
				
			}

		});

	}
	
}
