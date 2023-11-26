package SwingPersonalizadoJCM;

import java.awt.*;
import javax.swing.*;

public class SpecialJButton extends JButton {
	
	private static final long serialVersionUID = 1L;

	public SpecialJButton(String text) {
		this.setPreferredSize(new Dimension(this.getWidth(),50));
		this.setMaximumSize(new Dimension(this.getWidth(),10));
        this.setBackground(new Color(246,248,255));
        this.setForeground(new Color(26,28,37));
        this.setFont(new Font("Verdana", Font.BOLD, 14));
        this.setText("		"+text);
        this.setHorizontalAlignment(LEFT);
	}

}
