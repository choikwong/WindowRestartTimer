package com.shutdown;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.shutdown.listener.CheckBoxActionListener;
import com.shutdown.listener.RadioButtonSwapListener;
import com.shutdown.listener.ShowUpFrameActionListener;
import com.shutdown.listener.SpinnerChangeListener;

public class ShutdownUserInterface extends JFrame {
    private JPanel mainpanel = new JPanel();
    private JPanel displaypanel = new JPanel();
    private JPanel radiopanel = new JPanel();
    private JPanel daypanel = new JPanel();
    private JPanel timepanel = new JPanel();
    private ButtonGroup bg = new ButtonGroup();

    /**
     * Default Constructor
     */
    public ShutdownUserInterface() {
	setUserInterface();

	BufferedImage img;
	try {
	    img = ImageIO.read(getClass().getResource("image/32.png"));
	    this.setIconImage(img);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
	
    }

    /**
     * @throws IOException
     * 
     */
    public boolean setTotray() throws IOException {

	if (!SystemTray.isSupported()) {
	    System.out.println("SystemTray is not supported");
	    return false;
	}
	final PopupMenu popup = new PopupMenu();
	BufferedImage img = ImageIO
		.read(getClass().getResource("image/16.png"));
	final TrayIcon trayIcon = new TrayIcon(img, "Shut In Time");
	final SystemTray tray = SystemTray.getSystemTray();
	trayIcon.addActionListener(new ShowUpFrameActionListener(this));

	// Create a pop-up menu components
	MenuItem openItem = new MenuItem("Open");
	MenuItem aboutItem = new MenuItem("About");
	aboutItem.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new AboutInterface();
		
	    }
	    
	});
	MenuItem exitItem = new MenuItem("Exit");
	openItem.addActionListener(new ShowUpFrameActionListener(this));
	exitItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	    }

	});
	// Add components to pop-up menu
	popup.add(openItem);
	popup.add(aboutItem);
	popup.addSeparator();
	popup.add(exitItem);

	trayIcon.setPopupMenu(popup);

	try {
	    tray.add(trayIcon);
	} catch (AWTException e) {
	    System.out.println("TrayIcon could not be added.");
	    return false;
	}
	return true;
    }

    /**
     * 
     * 
     */
    private void setUserInterface() {
	JPanel borderedPanel = new JPanel();
	borderedPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
	this.setContentPane(borderedPanel);
	Component[] a = daypanel.getComponents();
	mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
	daypanel.setLayout(new FlowLayout(FlowLayout.LEADING));

	// ****************************//
	// * Monday *//
	// **************************//
	// radio
	JRadioButton rdioMon = new JRadioButton("Mon");
	rdioMon.addChangeListener(new RadioButtonSwapListener());

	radiopanel.add(rdioMon);
	bg.add(rdioMon);
	// tick
	JCheckBox chkMon = new JCheckBox("");
	chkMon.setName("Mon");
	chkMon.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkMon);
	daypanel.add(Box.createRigidArea(new Dimension(24, 0)));

	// ****************************//
	// * Tuesday *//
	// **************************//
	// radio
	JRadioButton rdioTue = new JRadioButton("Tue");
	rdioTue.addChangeListener(new RadioButtonSwapListener());
	radiopanel.add(rdioTue);
	bg.add(rdioTue);
	// tick
	JCheckBox chkTue = new JCheckBox("");
	chkTue.setName("Tue");
	chkTue.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkTue);
	daypanel.add(Box.createRigidArea(new Dimension(20, 0)));

	// ****************************//
	// * Wednesday *//
	// **************************//
	// radio
	JRadioButton rdioWed = new JRadioButton("Wed");
	rdioWed.addChangeListener(new RadioButtonSwapListener());
	radiopanel.add(rdioWed);
	bg.add(rdioWed);
	// tick
	JCheckBox chkWed = new JCheckBox("");
	chkWed.setName("Wed");
	chkWed.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkWed);
	daypanel.add(Box.createRigidArea(new Dimension(25, 0)));

	// ****************************//
	// * Thusday *//
	// **************************//
	// radio
	JRadioButton rdioThu = new JRadioButton("Thu");
	rdioThu.addChangeListener(new RadioButtonSwapListener());
	radiopanel.add(rdioThu);
	bg.add(rdioThu);
	// tick
	JCheckBox chkThu = new JCheckBox("");
	chkThu.setName("Thu");
	chkThu.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkThu);
	daypanel.add(Box.createRigidArea(new Dimension(19, 0)));

	// ****************************//
	// * Friday *//
	// **************************//
	// radio
	JRadioButton rdioFri = new JRadioButton("Fri");
	rdioFri.addChangeListener(new RadioButtonSwapListener());
	radiopanel.add(rdioFri);
	bg.add(rdioFri);
	// tick
	JCheckBox chkFri = new JCheckBox("");
	chkFri.setName("Fri");
	chkFri.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkFri);
	daypanel.add(Box.createRigidArea(new Dimension(14, 0)));

	// ****************************//
	// * Saturday *//
	// **************************//
	// radio
	JRadioButton rdioSat = new JRadioButton("Sat");
	rdioSat.addChangeListener(new RadioButtonSwapListener());
	radiopanel.add(rdioSat);
	bg.add(rdioSat);
	// tick
	JCheckBox chkSat = new JCheckBox("");
	chkSat.setName("Sat");
	chkSat.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkSat);
	daypanel.add(Box.createRigidArea(new Dimension(18, 0)));

	// ****************************//
	// * Sunday *//
	// **************************//
	// radio
	JRadioButton rdioSun = new JRadioButton("Sun");
	rdioSun.addChangeListener(new RadioButtonSwapListener());
	radiopanel.add(rdioSun);
	bg.add(rdioSun);
	// tick
	JCheckBox chkSun = new JCheckBox("");
	chkSun.setName("Sun");
	chkSun.addActionListener(new CheckBoxActionListener());
	daypanel.add(chkSun);

	JPanel timelabelpanel = new JPanel();
	timelabelpanel.add(new JLabel("Hour"));
	timelabelpanel.add(new JLabel(":"));
	timelabelpanel.add(new JLabel("Minute"));
	timelabelpanel.add(new JLabel(":"));
	timelabelpanel.add(new JLabel("Second"));
	// Time
	// Hours
	SpinnerModel hh = new SpinnerNumberModel(0, // initial value
		0, // min
		23, // max
		1);

	JSpinner sp1 = new JSpinner(hh);
	sp1.addChangeListener(new SpinnerChangeListener());

	// Minute
	SpinnerModel mm = new SpinnerNumberModel(0, // initial value
		0, // min
		59, // max
		1);
	JSpinner sp2 = new JSpinner(mm);
	sp2.addChangeListener(new SpinnerChangeListener());
	// second
	SpinnerModel ss = new SpinnerNumberModel(0, // initial value
		0, // min
		59, // max
		1);
	JSpinner sp3 = new JSpinner(ss);
	sp3.addChangeListener(new SpinnerChangeListener());
	// Building a Spinner Panel.
	timepanel.add(sp1);
	timepanel.add(new JLabel(":"));
	timepanel.add(sp2);
	timepanel.add(new JLabel(":"));
	timepanel.add(sp3);

	//display panel
	displaypanel.add(new JLabel("Next Shutdown Time:"));
	JLabel l = new JLabel(ShutdownProgram.SHUTDOWNTASK().getShutTimeOnLabel());
	l.setForeground (Color.red);
	displaypanel.add(l);
	displaypanel.add(new JLabel("Escape Time:"));
	l = new JLabel("00:00:00");
	l.setForeground (Color.red);
	displaypanel.add(l);





	
	// setup a main panel's Components
	mainpanel.add(displaypanel);
	mainpanel.add(radiopanel);
	mainpanel.add(daypanel);
	mainpanel.add(timelabelpanel);
	mainpanel.add(timepanel);
	this.add(mainpanel);

	// initial the start up selection
	rdioMon.setSelected(true);
	Component[] setofJCheckBox = daypanel.getComponents();

	for (Component oo : setofJCheckBox) {
	    if (oo instanceof JCheckBox && !(oo.getName().equals("Mon")))
		oo.setEnabled(false);
	}

	// Let Jframe invisible when it lose its focus.
	this.addWindowFocusListener(new WindowFocusListener() {

	    @Override
	    public void windowGainedFocus(WindowEvent arg0) {

	    }

	    @Override
	    public void windowLostFocus(WindowEvent arg0) {
		((ShutdownUserInterface) arg0.getSource()).setVisible(false);

	    }

	});

	// Let Jframe able to move under Undecorated is true
	this.addMouseMotionListener(new MouseMotionListener() {
	    Point lastlocation;

	    @Override
	    public void mouseDragged(MouseEvent arg0) {
		JFrame jf = (JFrame) arg0.getSource();
		// calculating the offset of dragged to move.
		int mousemoveoffsetx = lastlocation.x
			- arg0.getLocationOnScreen().x;
		int mousemoveoffsety = lastlocation.y
			- arg0.getLocationOnScreen().y;
		// apply the mouse's movement with the jframe
		jf.setLocation(jf.getLocationOnScreen().x - mousemoveoffsetx,
			jf.getLocationOnScreen().y - mousemoveoffsety);
		lastlocation = arg0.getLocationOnScreen();
	    }

	    @Override
	    public void mouseMoved(MouseEvent arg0) {
		// recoding the last position of the mouse moved on the jframe
		lastlocation = arg0.getLocationOnScreen();
	    }

	});
	this.setResizable(false);
	try {
	    if (setTotray()) {
		this.setUndecorated(true);
		this.setOpacity(0.85f);
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
	// set the jframe starting on the right bottom corner of screen
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
		.getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	this.setLocation(width - this.getWidth() - 5, height - this.getHeight()
		- 50);
	initialDay();
    }

    /**
     * initial Day Active
     */
   private void initialDay() {
	for (Object o : ShutdownProgram.GETDATASET().getAllShutdown_Days()
		.keySet()) {
	    Component[] tpc = daypanel.getComponents();
	    for (Component c : tpc) {
		if (c instanceof JCheckBox && c.getName().equals(o.toString())) {
		    ((JCheckBox) c).setSelected(ShutdownProgram.GETDATASET()
			    .isShutdownDay(o.toString()));
		}
	    }

	}
    }
   
   /**
    * Update the Escape Time
    */
   public void updateEscapeTime(String s) {
       ((JLabel)displaypanel.getComponents()[3]).setText(s);
   }
   

    /**
     * Update the Data on screen according to Data Set ShutdownTask .
     */
    public void updateData() {
	((JLabel)displaypanel.getComponents()[1]).setText(ShutdownProgram.SHUTDOWNTASK().getShutTimeOnLabel());
	
	for (Enumeration<AbstractButton> buttons = (Enumeration<AbstractButton>) bg
		.getElements(); buttons.hasMoreElements();) {
	    AbstractButton jrb = buttons.nextElement();

	    if (jrb.isSelected()) {
		Component[] tpc = daypanel.getComponents();
		// if selected RadioButton's text is same as JCheckBox's name
		// Enable the JCheckBox
		for (Component c : tpc) {
		    if ((c instanceof JCheckBox && !(c.getName().equals(jrb
			    .getText())))) {
			c.setEnabled(false);
		    } else if (c instanceof JCheckBox
			    && c.getName().equals(jrb.getText())) {
			c.setEnabled(true);
			// set a display data.
			// set JCheckBox isSelected.
			((JCheckBox) c).setSelected(ShutdownProgram
				.GETDATASET().isShutdownDay(jrb.getText()));

			// do a timepanel update when day isSelected.
			if (((JCheckBox) c).isSelected()) {
			    // set spinner data.
			    // split the fromat of string HH:MM:SS into a array
			    // of string.
			    String[] time = ShutdownProgram.GETDATASET()
				    .getShutdownTime(jrb.getText()).split(":");
			    int timeforspinnercounter = 0;
			    // loop a Component in timePanel, and put the
			    // corresponding
			    // data for display.
			    for (Component spc : timepanel.getComponents()) {
				if (spc instanceof JSpinner) {
				    ((JSpinner) spc)
					    .setValue(Integer
						    .parseInt(time[timeforspinnercounter]));
				    timeforspinnercounter++;
				}
			    }
			}
		    }
		}
	    }
	}
    }
}
