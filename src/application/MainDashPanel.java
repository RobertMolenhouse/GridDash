package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;



/*
 * switching to swing.  Things to remember to put in. gauges, exit button, annunciator lights, depending on how far
 * i get, need to be able to switch between different dashes.  Annunciator lights programmable.
 */
public class MainDashPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel tach; //tachometer, rpm for dummies
	private JLabel speedo; //speedometer, mph for dummies.
	private JLabel coolTmp;
	private JLabel oilTmp;
	private JLabel fuelLvl;
	private JLabel throttlePos;
	private JLabel gear;
	
	private JButton exitButton;
	private JButton[] rpmArray;
	
	private ButtonListener listen = new ButtonListener();
	
	private JPanel controlPanel;

	private CommandControl control;
	private CarData data;
	private JButton rb4;

	public MainDashPanel() throws IOException, InterruptedException {
		data = new CarData();
		control = new CommandControl(data);
		setLayout(new BorderLayout(0, 0));
		
		add(createLabelPanel());
		
		start();
		
	}
	
	private JPanel createLabelPanel(){
		//main panel that all the others will be placed into
				controlPanel = new JPanel();
				controlPanel.setForeground(new Color(240, 255, 240));

				tach = new JLabel("3586");
				tach.setFont(new Font("Tahoma", Font.PLAIN, 36));
				tach.setBounds(330, 36, 113, 58);
				speedo = new JLabel("68.5");
				speedo.setFont(new Font("Tahoma", Font.PLAIN, 36));
				speedo.setBounds(30, 139, 78, 40);
				coolTmp = new JLabel(data.getCoolandTemp() + "");
				coolTmp.setFont(new Font("Tahoma", Font.PLAIN, 18));
				coolTmp.setBounds(164, 237, 54, 40);
				oilTmp = new JLabel(data.getOilTemp() + "");
				oilTmp.setFont(new Font("Tahoma", Font.PLAIN, 18));
				oilTmp.setBounds(76, 237, 60, 41);
				fuelLvl = new JLabel(data.getFuelLevel() + "");
				fuelLvl.setBounds(351, 242, 40, 30);
				fuelLvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
				fuelLvl.setBackground(SystemColor.textHighlight);
				throttlePos = new JLabel(data.getThrottlePos() + "");
				throttlePos.setFont(new Font("Tahoma", Font.PLAIN, 18));
				throttlePos.setBounds(252, 236, 46, 43);
				gear = new JLabel("3");
				gear.setFont(new Font("Tahoma", Font.PLAIN, 64));
				gear.setBounds(192, 86, 44, 62);
				gear.setBackground(Color.BLACK);
				controlPanel.setLayout(null);
				
				JLabel label = new JLabel("RPM");
				label.setFont(new Font("Tahoma", Font.PLAIN, 18));
				label.setBounds(351, 11, 44, 20);
				controlPanel.add(label);
				controlPanel.add(tach);
				JLabel label_1 = new JLabel("MPH");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				label_1.setBounds(45, 109, 72, 40);
				controlPanel.add(label_1);
				controlPanel.add(speedo);
				JLabel lblHo = new JLabel("H2O");
				lblHo.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblHo.setBounds(154, 211, 64, 20);
				controlPanel.add(lblHo);
				controlPanel.add(coolTmp);
				JLabel lblOiltmp = new JLabel("OilTmp");
				lblOiltmp.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblOiltmp.setBounds(66, 201, 78, 41);
				controlPanel.add(lblOiltmp);
				controlPanel.add(oilTmp);
				JLabel label_4 = new JLabel("fuel");
				label_4.setBounds(351, 211, 44, 20);
				label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
				controlPanel.add(label_4);
				controlPanel.add(fuelLvl);
				JLabel label_5 = new JLabel("throttle");
				label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
				label_5.setBounds(242, 200, 84, 43);
				controlPanel.add(label_5);
				controlPanel.add(throttlePos);
				controlPanel.add(gear);
				
				JLabel tick1 = new JLabel("l");
				tick1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick1.setBounds(42, 80, 46, 14);
				controlPanel.add(tick1);
				
				JLabel tick2 = new JLabel("l");
				tick2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick2.setBounds(82, 58, 46, 14);
				controlPanel.add(tick2);
				
				JLabel tick3 = new JLabel("l");
				tick3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick3.setBounds(122, 48, 46, 14);
				controlPanel.add(tick3);
				
				JLabel tick4 = new JLabel("l");
				tick4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick4.setBounds(162, 43, 46, 14);
				controlPanel.add(tick4);
				
				JLabel tick5 = new JLabel("l");
				tick5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick5.setBounds(202, 43, 46, 14);
				controlPanel.add(tick5);
				
				JLabel tick6 = new JLabel("l");
				tick6.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick6.setBounds(242, 43, 46, 14);
				controlPanel.add(tick6);
				
				JLabel tick7 = new JLabel("l");
				tick7.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick7.setBounds(280, 43, 46, 14);
				controlPanel.add(tick7);
				
				JLabel rpm1000 = new JLabel("1000");
				rpm1000.setBounds(30, 92, 46, 14);
				controlPanel.add(rpm1000);
				
				JLabel rpm2000 = new JLabel("2000");
				rpm2000.setBounds(70, 72, 46, 14);
				controlPanel.add(rpm2000);
				
				JLabel rpm3000 = new JLabel("3000");
				rpm3000.setBounds(110, 60, 46, 14);
				controlPanel.add(rpm3000);
				
				JLabel rpm4000 = new JLabel("4000");
				rpm4000.setBounds(150, 55, 46, 14);
				controlPanel.add(rpm4000);
				
				JLabel rpm5000 = new JLabel("5000");
				rpm5000.setBounds(190, 55, 46, 14);
				controlPanel.add(rpm5000);
				
				JLabel rpm6000 = new JLabel("6000");
				rpm6000.setBounds(230, 55, 46, 14);
				controlPanel.add(rpm6000);
				
				JLabel rpm7000 = new JLabel("7000");
				rpm7000.setBounds(270, 55, 46, 14);
				controlPanel.add(rpm7000);
				
				exitButton = new JButton("");
				exitButton.addActionListener(listen);
				exitButton.setBounds(422, 11, 20, 20);
				exitButton.setBackground(new Color(0, 191, 255));
				exitButton.setIcon(new ImageIcon("x.png"));
				controlPanel.add(exitButton);
				
				createRPMDisplay();
				
				controlPanel.setBackground(new Color(0, 191, 255));
				//controlPanel.setPreferredSize(new Dimension(790,400));
				controlPanel.revalidate();
		
		return controlPanel;
		
	}
	
	

	//run the CommandControl in its own thread.

private void start(){
	Thread controlThread = new Thread(control);
	controlThread.start();
	
	
	//new action listener for the timer
	ActionListener action = new ActionListener()
	{   
		@Override
		public void actionPerformed(ActionEvent event)
		{
			DecimalFormat df = new DecimalFormat("#.#");
			//update labelPanel
			tach.setText(data.getRpm() + "");
			speedo.setText(df.format(data.getMph()));
			coolTmp.setText(df.format(data.getCoolandTemp()));
			oilTmp.setText(df.format(data.getOilTemp()));
			fuelLvl.setText(df.format(data.getFuelLevel()));
			throttlePos.setText(df.format(data.getThrottlePos()));
			gear.setText(data.getGear());
			
			//update rpm display
			int i = 25;
			for(JButton rpm : rpmArray){
				if(data.getRpm() >= i){
					rpm.setVisible(true);
				}else{
					rpm.setVisible(false);
				}
				i += 25;
			}
		}
	};
	Timer timer = new Timer(10, action);
	timer.start();
}
private class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton){
			System.exit(0);
		}
		
	}
	
}
private void createRPMDisplay(){
	JButton rb1 = new JButton("");
	rb1.setBackground(new Color(0, 0, 0));
	rb1.setBounds(10, 11, 7, 91);
	rb1.setOpaque(true);
	rb1.setBorderPainted(false);
	controlPanel.add(rb1);
	
	JButton rb2 = new JButton("");
	rb2.setOpaque(true);
	rb2.setBorderPainted(false);
	rb2.setBackground(Color.BLACK);
	rb2.setBounds(20, 11, 7, 82);
	controlPanel.add(rb2);
	
	JButton rb3 = new JButton("");
	rb3.setOpaque(true);
	rb3.setBorderPainted(false);
	rb3.setBackground(Color.BLACK);
	rb3.setBounds(30, 11, 7, 75);
	controlPanel.add(rb3);
	
	rb4 = new JButton("");
	rb4.setOpaque(true);
	rb4.setBorderPainted(false);
	rb4.setBackground(Color.BLACK);
	rb4.setBounds(40, 11, 7, 69);
	controlPanel.add(rb4);
	
	JButton rb5 = new JButton("");
	rb5.setOpaque(true);
	rb5.setBorderPainted(false);
	rb5.setBackground(Color.BLACK);
	rb5.setBounds(50, 11, 7, 62);
	controlPanel.add(rb5);
	
	JButton rb6 = new JButton("");
	rb6.setOpaque(true);
	rb6.setBorderPainted(false);
	rb6.setBackground(Color.BLACK);
	rb6.setBounds(60, 11, 7, 55);
	controlPanel.add(rb6);
	
	JButton rb7 = new JButton("");
	rb7.setOpaque(true);
	rb7.setBorderPainted(false);
	rb7.setBackground(Color.BLACK);
	rb7.setBounds(70, 11, 7, 50);
	controlPanel.add(rb7);
	
	JButton rb8 = new JButton("");
	rb8.setOpaque(true);
	rb8.setBorderPainted(false);
	rb8.setBackground(Color.BLACK);
	rb8.setBounds(80, 11, 7, 45);
	controlPanel.add(rb8);
	
	JButton rb9 = new JButton("");
	rb9.setOpaque(true);
	rb9.setBorderPainted(false);
	rb9.setBackground(Color.BLACK);
	rb9.setBounds(90, 11, 7, 42);
	controlPanel.add(rb9);
	
	JButton rb10 = new JButton("");
	rb10.setOpaque(true);
	rb10.setBorderPainted(false);
	rb10.setBackground(Color.BLACK);
	rb10.setBounds(110, 11, 7, 38);
	controlPanel.add(rb10);
	
	JButton rb11 = new JButton("");
	rb11.setOpaque(true);
	rb11.setBorderPainted(false);
	rb11.setBackground(Color.BLACK);
	rb11.setBounds(100, 11, 7, 40);
	controlPanel.add(rb11);
	
	JButton rb12 = new JButton("");
	rb12.setOpaque(true);
	rb12.setBorderPainted(false);
	rb12.setBackground(Color.BLACK);
	rb12.setBounds(120, 11, 7, 36);
	controlPanel.add(rb12);
	
	JButton rb13 = new JButton("");
	rb13.setOpaque(true);
	rb13.setBorderPainted(false);
	rb13.setBackground(Color.BLACK);
	rb13.setBounds(130, 11, 7, 34);
	controlPanel.add(rb13);
	
	JButton rb14 = new JButton("");
	rb14.setOpaque(true);
	rb14.setBorderPainted(false);
	rb14.setBackground(Color.BLACK);
	rb14.setBounds(140, 11, 7, 33);
	controlPanel.add(rb14);
	
	JButton rb15 = new JButton("");
	rb15.setOpaque(true);
	rb15.setBorderPainted(false);
	rb15.setBackground(Color.BLACK);
	rb15.setBounds(150, 11, 7, 32);
	controlPanel.add(rb15);
	
	JButton rb16 = new JButton("");
	rb16.setOpaque(true);
	rb16.setBorderPainted(false);
	rb16.setBackground(Color.BLACK);
	rb16.setBounds(160, 11, 7, 31);
	controlPanel.add(rb16);
	
	JButton rb17 = new JButton("");
	rb17.setOpaque(true);
	rb17.setBorderPainted(false);
	rb17.setBackground(Color.BLACK);
	rb17.setBounds(170, 11, 7, 30);
	controlPanel.add(rb17);
	
	JButton rb18 = new JButton("");
	rb18.setOpaque(true);
	rb18.setBorderPainted(false);
	rb18.setBackground(Color.BLACK);
	rb18.setBounds(170, 11, 7, 30);
	controlPanel.add(rb18);
	
	JButton rb19 = new JButton("");
	rb19.setOpaque(true);
	rb19.setBorderPainted(false);
	rb19.setBackground(Color.BLACK);
	rb19.setBounds(180, 11, 7, 30);
	controlPanel.add(rb19);
	
	JButton rb20 = new JButton("");
	rb20.setOpaque(true);
	rb20.setBorderPainted(false);
	rb20.setBackground(Color.BLACK);
	rb20.setBounds(190, 11, 7, 30);
	controlPanel.add(rb20);
	
	JButton rb21 = new JButton("");
	rb21.setOpaque(true);
	rb21.setBorderPainted(false);
	rb21.setBackground(Color.BLACK);
	rb21.setBounds(200, 11, 7, 30);
	controlPanel.add(rb21);
	
	JButton rb22 = new JButton("");
	rb22.setOpaque(true);
	rb22.setBorderPainted(false);
	rb22.setBackground(Color.BLACK);
	rb22.setBounds(210, 11, 7, 30);
	controlPanel.add(rb22);
	
	JButton rb23 = new JButton("");
	rb23.setOpaque(true);
	rb23.setBorderPainted(false);
	rb23.setBackground(Color.BLACK);
	rb23.setBounds(220, 11, 7, 30);
	controlPanel.add(rb23);
	
	JButton rb24 = new JButton("");
	rb24.setOpaque(true);
	rb24.setBorderPainted(false);
	rb24.setBackground(Color.BLACK);
	rb24.setBounds(230, 11, 7, 30);
	controlPanel.add(rb24);
	
	JButton rb25 = new JButton("");
	rb25.setOpaque(true);
	rb25.setBorderPainted(false);
	rb25.setBackground(Color.BLACK);
	rb25.setBounds(240, 11, 7, 30);
	controlPanel.add(rb25);
	
	JButton rb26 = new JButton("");
	rb26.setOpaque(true);
	rb26.setBorderPainted(false);
	rb26.setBackground(Color.BLACK);
	rb26.setBounds(250, 11, 7, 30);
	controlPanel.add(rb26);
	
	JButton rb27 = new JButton("");
	rb27.setOpaque(true);
	rb27.setBorderPainted(false);
	rb27.setBackground(Color.BLACK);
	rb27.setBounds(260, 11, 7, 30);
	controlPanel.add(rb27);
	
	JButton rb28 = new JButton("");
	rb28.setOpaque(true);
	rb28.setBorderPainted(false);
	rb28.setBackground(Color.BLACK);
	rb28.setBounds(270, 11, 7, 30);
	controlPanel.add(rb28);
	
	JButton rb29 = new JButton("");
	rb29.setOpaque(true);
	rb29.setBorderPainted(false);
	rb29.setBackground(Color.BLACK);
	rb29.setBounds(280, 11, 7, 30);
	controlPanel.add(rb29);
	
	JButton rb30 = new JButton("");
	rb30.setOpaque(true);
	rb30.setBorderPainted(false);
	rb30.setBackground(Color.BLACK);
	rb30.setBounds(290, 11, 7, 30);
	controlPanel.add(rb30);
	
	JButton rb31 = new JButton("");
	rb31.setOpaque(true);
	rb31.setBorderPainted(false);
	rb31.setBackground(Color.BLACK);
	rb31.setBounds(300, 11, 7, 30);
	controlPanel.add(rb31);
	
	JButton rb32 = new JButton("");
	rb32.setOpaque(true);
	rb32.setBorderPainted(false);
	rb32.setBackground(Color.BLACK);
	rb32.setBounds(310, 11, 7, 30);
	controlPanel.add(rb32);
	
	rpmArray = new JButton[]{rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10,
			rb11,rb12,rb13,rb14,rb15,rb16,rb17,rb18,rb19,rb20,rb21,rb22,
			rb23,rb24,rb25,rb26,rb27,rb28,rb29,rb30,rb31,rb32};
}
}
