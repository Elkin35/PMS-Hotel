package SwingPersonalizadoJCM;

import java.awt.*;
import javax.swing.*;

public class SpecialJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	
	public SpecialJPanel() {
		this.setLayout(new BorderLayout(10, 10));
		contentPanel = new TransparentJPanel();
		this.add(contentPanel, BorderLayout.CENTER);
		
		JLabel blank = new JLabel("          ");
		JLabel blank2 = new JLabel("          ");
		JLabel blank3 = new JLabel("          ");
		this.add(blank, BorderLayout.EAST);
		this.add(blank2, BorderLayout.WEST);
		this.add(blank3, BorderLayout.SOUTH);
	}
	
	/*
	 * 
	 * Add a title to the panel
	 * 
	 * @param title the String of text 
	 * @param color the color of the title
	 * @param font the font of the title
	 * 
	 * */
	public void addTitle (String title, Color color, Font font) {
		JLabel lblTitle = new JLabel(title);
		lblTitle.setForeground(color);
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setFont(font);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle, BorderLayout.NORTH);
	}
	
	/*
	 * 
	 * Add a title to the panel
	 * 
	 * @param title the String of text 
	 * @param color the color of the title
	 * 
	 * */
	public void addTitle (String title, Color color) {
		JLabel lblTitle = new JLabel(title);
		lblTitle.setForeground(color);
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle, BorderLayout.NORTH);
	}
	
	/*
	 * 
	 * Add a title to the panel
	 * 
	 * @param title the String of text 
	 * 
	 * */
	public void addTitle (String title) {
		JLabel lblTitle = new JLabel(title);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle, BorderLayout.NORTH);
	}
	
	/*
	 * 
	 * Add a title to the panel
	 * 
	 * @param path the path of the icon image
	 * 
	 * */
	public void addIconTitle (String path) {
		ImageIcon icon = new ImageIcon(path);
		JLabel lblTitle = new JLabel(icon);
		this.add(lblTitle, BorderLayout.NORTH);
	}
	
	/*
	 * 
	 * Add a title to the panel
	 * 
	 * @param path the path of the icon image
	 * @param logoWidth the width of the logo
	 * @param logoHeight the height of the logo
	 * 
	 * */
	public void addIconTitle (String path, int logoWidth, int logoHeight) {
		ImageIcon icon = new ImageIcon(path);
		Image original = icon.getImage();
        Image scaled = original.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaled);
        JLabel lblTitle = new JLabel(scaledIcon);
        this.add(lblTitle, BorderLayout.NORTH);
	}
	
	
	
	@Override
	public Component add(Component comp) {
		return contentPanel.add(comp);
	}
	
	/*
	 * 
	 * Add a layout to the panel after the logo
	 * 
	 * @param mrg the layout to add to the panel
	 * 
	 * */
	public void setContentLayout(LayoutManager mgr) {
		contentPanel.setLayout(mgr);
	}
	
}
