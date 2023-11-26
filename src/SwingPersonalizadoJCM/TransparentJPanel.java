package SwingPersonalizadoJCM;

import java.awt.Color;

import javax.swing.*;

public class TransparentJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TransparentJPanel() {
		this.setOpaque(false);
		this.setBackground(new Color(0, 0, 0, 0));
	}
	
}
