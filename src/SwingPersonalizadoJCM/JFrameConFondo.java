package SwingPersonalizadoJCM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class JFrameConFondo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private SpecialJPanel centeredPanel;
	
	// Constructor sin size
	
	/*
	 * Generates a JFrame object with an background image with default size
	 * 
	 * @param Title the title of the JFrame
	 * @param ImagePath the path of the background image into the project
	 * 
	 * */
	public JFrameConFondo(String Title, String ImagePath) {

        setTitle(Title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crea el JPanel con la imagen de fondo
        
        panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(ImagePath);
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(panel);
        
        // Window resize
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                panel.setSize(getWidth(), getHeight()); 
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                panel.setLocation(0, 0); 
            }
        });

        // Establece el tamaño y la posición del JFrame
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }
	
	// Constructor with size
	
	/*
	 * Generates a JFrame object with an background image with defined size
	 * 
	 * @param Title the title of the JFrame
	 * @param ImagePath the path of the background image into the project
	 * @param width of the new window
	 * @param length of the new window
	 * 
	 * */
	public JFrameConFondo(String Title, String ImagePath, int width, int height ) {
		
		setTitle(Title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Create the JPanel with a background image
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(ImagePath);
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		getContentPane().add(panel);
		
		// Window resize
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				panel.setSize(getWidth(), getHeight()); 
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				super.componentMoved(e);
				panel.setLocation(0, 0); 
			}
		});
		
		// Establish width and height of the JFrame
		setSize(width, height);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla
	}
	
	/*
	 * 
	 * Set the size to the screen size
	 * 
	 * */
	public void fullWindow () {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/*
	 * 
	 * Set the size to the screen size
	 * 
	 * @param permanent if true setResizable value turns false and it kept the full size of the window 
	 * 
	 * */
	public void fullWindow (boolean permanent) {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		if (permanent) {
			setResizable(false);
		};
	}
	
	/*
	 * 
	 * Create a centered JPanel in the middle of the JFrame
	 * 
	 * @param opaque if true it makes the panel with solid colors, else it makes it transparent
	 * @color the color of the JPanel in the JFrame
	 * 
	 * */
	public SpecialJPanel addCenteredPanel (boolean opaque, Color color) {
		this.panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.set(50, 50, 50, 50);
        
        if (opaque) {
        	centeredPanel = new SpecialJPanel();
        	centeredPanel.setBackground(color);
        	centeredPanel.setOpaque(true);
        }
        else {
        	centeredPanel = new SpecialJPanel() {
        		
        		private static final long serialVersionUID = 1L;
        		
        		@Override
        		protected void paintComponent(Graphics g) {
        			Graphics2D g2d = (Graphics2D) g.create();
        			g2d.setColor(color);
        			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
        			g2d.fillRect(0, 0, getWidth(), getHeight());
        			g2d.dispose();
        		}
        	};
        	centeredPanel.setOpaque(false);
        }
        
        this.panel.add(centeredPanel, gbc);
        return this.centeredPanel;
		
	}
	
	/*
	 * 
	 * Create a centered JPanel in the middle of the JFrame
	 * 
	 * @param opaque if true it makes the panel with solid colors, else it makes it transparent
	 * 
	 * */
	public SpecialJPanel addCenteredPanel (boolean opaque) {
		this.panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets.set(50, 50, 50, 50);
		
		if (opaque) {
			centeredPanel = new SpecialJPanel();
			centeredPanel.setBackground(new Color(242, 242, 242));
			centeredPanel.setOpaque(true);
		}
		else {
			centeredPanel = new SpecialJPanel() {
				
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setColor(new Color(242, 242, 242));
					g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
					g2d.fillRect(0, 0, getWidth(), getHeight());
					g2d.dispose();
				}
			};
			centeredPanel.setOpaque(false);
		}
		
		this.panel.add(centeredPanel, gbc);
		return this.centeredPanel;
		
	}
	
	/*
	 * 
	 * Create a centered JPanel in the middle of the JFrame
	 * 
	 * @param the color of the JPanel in the JFrame
	 * 
	 * */
	public SpecialJPanel addCenteredPanel (Color color) {
		this.panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets.set(50, 50, 50, 50);
		
		centeredPanel = new SpecialJPanel();
		centeredPanel.setBackground(color);
		centeredPanel.setOpaque(true);	
		this.panel.add(centeredPanel, gbc);
		return this.centeredPanel;
		
	}
	
	/*
	 * 
	 * Create a centered JPanel in the middle of the JFrame
	 * 
	 * */
	public SpecialJPanel addCenteredPanel () {
		this.panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets.set(50, 50, 50, 50);
		
		centeredPanel = new SpecialJPanel();
		centeredPanel.setBackground(new Color(242, 242, 242));
		centeredPanel.setOpaque(true);	
		this.panel.add(centeredPanel, gbc);
		return this.centeredPanel;
		
	}
	
}
