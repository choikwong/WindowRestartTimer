package com.shutdown;

import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutInterface extends JDialog {

    public AboutInterface() {
	JPanel mainpanel = new JPanel();
	JPanel titlepanel = new JPanel();
	JPanel versionpanel = new JPanel();
	JPanel developerpanel = new JPanel();
	JPanel contactpanel = new JPanel();
	JPanel copyrightpanel = new JPanel();
	JLabel jl;
	mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
	
	
	BufferedImage img;
	try {
	    img = ImageIO.read(getClass().getResource("image/32.png"));
	    ImageIcon image = new ImageIcon(img);
	    jl = new JLabel(image);
	    titlepanel.add(jl);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	jl = new JLabel("Shut In Time");
	jl.setFont(new Font("Verdana", Font.ITALIC, 28));
	titlepanel.add(jl);
	jl = new JLabel("version: 0.1");
	versionpanel.add(jl);
	jl = new JLabel("Created By: Raymond Choi.");
	developerpanel.add(jl);
	jl = new JLabel("Email: ");
	contactpanel.add(jl);
	jl = new JLabel("Copyright \u00a9 2014 Raymond. All rights Reserved");
	copyrightpanel.add(jl);
	mainpanel.add(titlepanel);
	mainpanel.add(versionpanel);
	mainpanel.add(developerpanel);
	mainpanel.add(contactpanel);
	mainpanel.add(copyrightpanel);
	/*mainpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	titlepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	versionpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	developerpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	contactpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	copyrightpanel.setAlignmentX(Component.LEFT_ALIGNMENT);*/
	this.add(mainpanel);
	this.setResizable(false);
	this.pack();
	this.setVisible(true);
    }

}
