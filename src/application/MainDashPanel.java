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
import java.awt.FlowLayout;



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
		setPreferredSize(new Dimension(800,400));
		data = new CarData();
		control = new CommandControl(data);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		add(createLabelPanel());
		
		start();
		
	}
	
	private JPanel createLabelPanel(){
		//main panel that all the others will be placed into
				controlPanel = new JPanel();
				controlPanel.setForeground(new Color(240, 255, 240));

				tach = new JLabel("3586");
				tach.setFont(new Font("Tahoma", Font.PLAIN, 64));
				tach.setBounds(540, 131, 218, 91);
				speedo = new JLabel("68.5");
				speedo.setFont(new Font("Tahoma", Font.PLAIN, 64));
				speedo.setBounds(80, 142, 168, 69);
				coolTmp = new JLabel(data.getCoolandTemp() + "");
				coolTmp.setFont(new Font("Tahoma", Font.PLAIN, 18));
				coolTmp.setBounds(164, 322, 54, 40);
				oilTmp = new JLabel(data.getOilTemp() + "");
				oilTmp.setFont(new Font("Tahoma", Font.PLAIN, 18));
				oilTmp.setBounds(76, 322, 60, 41);
				fuelLvl = new JLabel(data.getFuelLevel() + "");
				fuelLvl.setBounds(337, 327, 40, 30);
				fuelLvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
				fuelLvl.setBackground(SystemColor.textHighlight);
				throttlePos = new JLabel(data.getThrottlePos() + "");
				throttlePos.setFont(new Font("Tahoma", Font.PLAIN, 18));
				throttlePos.setBounds(240, 321, 46, 43);
				gear = new JLabel("3");
				gear.setFont(new Font("Tahoma", Font.PLAIN, 84));
				gear.setBounds(382, 106, 85, 125);
				gear.setBackground(Color.BLACK);
				controlPanel.setLayout(null);
				
				JLabel label = new JLabel("RPM");
				label.setFont(new Font("Tahoma", Font.PLAIN, 28));
				label.setBounds(570, 106, 66, 40);
				controlPanel.add(label);
				controlPanel.add(tach);
				JLabel label_1 = new JLabel("MPH");
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
				label_1.setBounds(102, 106, 72, 40);
				controlPanel.add(label_1);
				controlPanel.add(speedo);
				JLabel lblHo = new JLabel("H2O");
				lblHo.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblHo.setBounds(154, 298, 64, 20);
				controlPanel.add(lblHo);
				controlPanel.add(coolTmp);
				JLabel lblOiltmp = new JLabel("OilTmp");
				lblOiltmp.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblOiltmp.setBounds(76, 288, 78, 41);
				controlPanel.add(lblOiltmp);
				controlPanel.add(oilTmp);
				JLabel label_4 = new JLabel("fuel");
				label_4.setBounds(333, 298, 44, 20);
				label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
				controlPanel.add(label_4);
				controlPanel.add(fuelLvl);
				JLabel label_5 = new JLabel("throttle");
				label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
				label_5.setBounds(233, 287, 84, 43);
				controlPanel.add(label_5);
				controlPanel.add(throttlePos);
				controlPanel.add(gear);
				
				JLabel tick1 = new JLabel("l");
				tick1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick1.setBounds(102, 50, 46, 14);
				controlPanel.add(tick1);
				
				JLabel tick2 = new JLabel("l");
				tick2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick2.setBounds(202, 40, 46, 14);
				controlPanel.add(tick2);
				
				JLabel tick3 = new JLabel("l");
				tick3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick3.setBounds(302, 40, 46, 14);
				controlPanel.add(tick3);
				
				JLabel tick4 = new JLabel("l");
				tick4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick4.setBounds(402, 40, 46, 14);
				controlPanel.add(tick4);
				
				JLabel tick5 = new JLabel("l");
				tick5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick5.setBounds(502, 40, 46, 14);
				controlPanel.add(tick5);
				
				JLabel tick6 = new JLabel("l");
				tick6.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick6.setBounds(602, 40, 46, 14);
				controlPanel.add(tick6);
				
				JLabel tick7 = new JLabel("l");
				tick7.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tick7.setBounds(702, 50, 46, 14);
				controlPanel.add(tick7);
				
				JLabel rpm1000 = new JLabel("1000");
				rpm1000.setBounds(90, 65, 46, 14);
				controlPanel.add(rpm1000);
				
				JLabel rpm2000 = new JLabel("2000");
				rpm2000.setBounds(190, 55, 46, 14);
				controlPanel.add(rpm2000);
				
				JLabel rpm3000 = new JLabel("3000");
				rpm3000.setBounds(290, 55, 46, 14);
				controlPanel.add(rpm3000);
				
				JLabel rpm4000 = new JLabel("4000");
				rpm4000.setBounds(390, 55, 46, 14);
				controlPanel.add(rpm4000);
				
				JLabel rpm5000 = new JLabel("5000");
				rpm5000.setBounds(490, 55, 46, 14);
				controlPanel.add(rpm5000);
				
				JLabel rpm6000 = new JLabel("6000");
				rpm6000.setBounds(590, 55, 46, 14);
				controlPanel.add(rpm6000);
				
				JLabel rpm7000 = new JLabel("7000");
				rpm7000.setBounds(690, 65, 46, 14);
				controlPanel.add(rpm7000);
				
				exitButton = new JButton("");
				exitButton.addActionListener(listen);
				exitButton.setBounds(780, 0, 20, 20);
				exitButton.setBackground(new Color(0, 191, 255));
				exitButton.setIcon(new ImageIcon("x.png"));
				controlPanel.add(exitButton);
				
				createRPMDisplay();
				
				controlPanel.setBackground(new Color(0, 191, 255));
				controlPanel.setPreferredSize(new Dimension(800,400));
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
			int i = 100;
			for(JButton rpm : rpmArray){
				if(data.getRpm() >= i){
					rpm.setVisible(true);
				}else{
					rpm.setVisible(false);
				}
				i += 100;
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

/*
 * creates all the tick marks for the tach.
 * TODO the numbers are messed up, there is no 18, 43, or 44.
 * and it goes up to 82 when there are only 79 marks.  fix the numbering maybe
 * when you're bored.
 */
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
	rb10.setBounds(100, 11, 7, 40);
	controlPanel.add(rb10);
	
	JButton rb11 = new JButton("");
	rb11.setOpaque(true);
	rb11.setBorderPainted(false);
	rb11.setBackground(Color.BLACK);
	rb11.setBounds(110, 11, 7, 38);
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
	
	JButton rb33 = new JButton("");
	rb33.setOpaque(true);
	rb33.setBorderPainted(false);
	rb33.setBackground(Color.BLACK);
	rb33.setBounds(320, 11, 7, 30);
	controlPanel.add(rb33);
	
	JButton rb34 = new JButton("");
	rb34.setOpaque(true);
	rb34.setBorderPainted(false);
	rb34.setBackground(Color.BLACK);
	rb34.setBounds(330, 11, 7, 30);
	controlPanel.add(rb34);
	
	JButton rb35 = new JButton("");
	rb35.setOpaque(true);
	rb35.setBorderPainted(false);
	rb35.setBackground(Color.BLACK);
	rb35.setBounds(340, 11, 7, 30);
	controlPanel.add(rb35);
	
	JButton rb36 = new JButton("");
	rb36.setOpaque(true);
	rb36.setBorderPainted(false);
	rb36.setBackground(Color.BLACK);
	rb36.setBounds(350, 11, 7, 30);
	controlPanel.add(rb36);
	
	JButton rb37 = new JButton("");
	rb37.setOpaque(true);
	rb37.setBorderPainted(false);
	rb37.setBackground(Color.BLACK);
	rb37.setBounds(360, 11, 7, 30);
	controlPanel.add(rb37);
	
	JButton rb38 = new JButton("");
	rb38.setOpaque(true);
	rb38.setBorderPainted(false);
	rb38.setBackground(Color.BLACK);
	rb38.setBounds(370, 11, 7, 30);
	controlPanel.add(rb38);
	
	JButton rb39 = new JButton("");
	rb39.setOpaque(true);
	rb39.setBorderPainted(false);
	rb39.setBackground(Color.BLACK);
	rb39.setBounds(380, 11, 7, 30);
	controlPanel.add(rb39);
	
	JButton rb40 = new JButton("");
	rb40.setOpaque(true);
	rb40.setBorderPainted(false);
	rb40.setBackground(Color.BLACK);
	rb40.setBounds(390, 11, 7, 30);
	controlPanel.add(rb40);
	
	JButton rb41 = new JButton("");
	rb41.setOpaque(true);
	rb41.setBorderPainted(false);
	rb41.setBackground(Color.BLACK);
	rb41.setBounds(400, 11, 7, 30);
	controlPanel.add(rb41);
	
	JButton rb42 = new JButton("");
	rb42.setOpaque(true);
	rb42.setBorderPainted(false);
	rb42.setBackground(Color.BLACK);
	rb42.setBounds(410, 11, 7, 30);
	controlPanel.add(rb42);
	
	JButton rb45 = new JButton("");
	rb45.setOpaque(true);
	rb45.setBorderPainted(false);
	rb45.setBackground(Color.BLACK);
	rb45.setBounds(420, 11, 7, 30);
	controlPanel.add(rb45);
	
	JButton rb46 = new JButton("");
	rb46.setOpaque(true);
	rb46.setBorderPainted(false);
	rb46.setBackground(Color.BLACK);
	rb46.setBounds(430, 11, 7, 30);
	controlPanel.add(rb46);
	
	JButton rb47 = new JButton("");
	rb47.setOpaque(true);
	rb47.setBorderPainted(false);
	rb47.setBackground(Color.BLACK);
	rb47.setBounds(440, 11, 7, 30);
	controlPanel.add(rb47);
	
	JButton rb48 = new JButton("");
	rb48.setOpaque(true);
	rb48.setBorderPainted(false);
	rb48.setBackground(Color.BLACK);
	rb48.setBounds(450, 11, 7, 30);
	controlPanel.add(rb48);
	
	JButton rb49 = new JButton("");
	rb49.setOpaque(true);
	rb49.setBorderPainted(false);
	rb49.setBackground(Color.BLACK);
	rb49.setBounds(460, 11, 7, 30);
	controlPanel.add(rb49);
	
	JButton rb50 = new JButton("");
	rb50.setOpaque(true);
	rb50.setBorderPainted(false);
	rb50.setBackground(Color.BLACK);
	rb50.setBounds(470, 11, 7, 30);
	controlPanel.add(rb50);
	
	JButton rb51 = new JButton("");
	rb51.setOpaque(true);
	rb51.setBorderPainted(false);
	rb51.setBackground(Color.BLACK);
	rb51.setBounds(480, 11, 7, 30);
	controlPanel.add(rb51);
	
	JButton rb52 = new JButton("");
	rb52.setOpaque(true);
	rb52.setBorderPainted(false);
	rb52.setBackground(Color.BLACK);
	rb52.setBounds(490, 11, 7, 30);
	controlPanel.add(rb52);
	
	JButton rb53 = new JButton("");
	rb53.setOpaque(true);
	rb53.setBorderPainted(false);
	rb53.setBackground(Color.BLACK);
	rb53.setBounds(500, 11, 7, 30);
	controlPanel.add(rb53);
	
	JButton rb54 = new JButton("");
	rb54.setOpaque(true);
	rb54.setBorderPainted(false);
	rb54.setBackground(Color.BLACK);
	rb54.setBounds(510, 11, 7, 30);
	controlPanel.add(rb54);
	
	JButton rb55 = new JButton("");
	rb55.setOpaque(true);
	rb55.setBorderPainted(false);
	rb55.setBackground(Color.BLACK);
	rb55.setBounds(520, 11, 7, 30);
	controlPanel.add(rb55);
	
	JButton rb56 = new JButton("");
	rb56.setOpaque(true);
	rb56.setBorderPainted(false);
	rb56.setBackground(Color.BLACK);
	rb56.setBounds(530, 11, 7, 30);
	controlPanel.add(rb56);
	
	JButton rb57 = new JButton("");
	rb57.setOpaque(true);
	rb57.setBorderPainted(false);
	rb57.setBackground(Color.BLACK);
	rb57.setBounds(540, 11, 7, 30);
	controlPanel.add(rb57);
	
	JButton rb58 = new JButton("");
	rb58.setOpaque(true);
	rb58.setBorderPainted(false);
	rb58.setBackground(Color.BLACK);
	rb58.setBounds(550, 11, 7, 30);
	controlPanel.add(rb58);
	
	JButton rb59 = new JButton("");
	rb59.setOpaque(true);
	rb59.setBorderPainted(false);
	rb59.setBackground(Color.BLACK);
	rb59.setBounds(560, 11, 7, 30);
	controlPanel.add(rb59);
	
	JButton rb60 = new JButton("");
	rb60.setOpaque(true);
	rb60.setBorderPainted(false);
	rb60.setBackground(Color.BLACK);
	rb60.setBounds(570, 11, 7, 30);
	controlPanel.add(rb60);
	
	JButton rb61 = new JButton("");
	rb61.setOpaque(true);
	rb61.setBorderPainted(false);
	rb61.setBackground(Color.BLACK);
	rb61.setBounds(580, 11, 7, 30);
	controlPanel.add(rb61);
	
	JButton rb62 = new JButton("");
	rb62.setOpaque(true);
	rb62.setBorderPainted(false);
	rb62.setBackground(Color.BLACK);
	rb62.setBounds(590, 11, 7, 30);
	controlPanel.add(rb62);
	
	JButton rb63 = new JButton("");
	rb63.setOpaque(true);
	rb63.setBorderPainted(false);
	rb63.setBackground(Color.BLACK);
	rb63.setBounds(600, 11, 7, 30);
	controlPanel.add(rb63);
	
	JButton rb64 = new JButton("");
	rb64.setOpaque(true);
	rb64.setBorderPainted(false);
	rb64.setBackground(Color.BLACK);
	rb64.setBounds(610, 11, 7, 30);
	controlPanel.add(rb64);
	
	JButton rb65 = new JButton("");
	rb65.setOpaque(true);
	rb65.setBorderPainted(false);
	rb65.setBackground(Color.BLACK);
	rb65.setBounds(620, 11, 7, 30);
	controlPanel.add(rb65);
	
	JButton rb66 = new JButton("");
	rb66.setOpaque(true);
	rb66.setBorderPainted(false);
	rb66.setBackground(Color.BLACK);
	rb66.setBounds(630, 11, 7, 30);
	controlPanel.add(rb66);
	
	JButton rb67 = new JButton("");
	rb67.setOpaque(true);
	rb67.setBorderPainted(false);
	rb67.setBackground(Color.BLACK);
	rb67.setBounds(640, 11, 7, 31);
	controlPanel.add(rb67);
	
	JButton rb68 = new JButton("");
	rb68.setOpaque(true);
	rb68.setBorderPainted(false);
	rb68.setBackground(Color.BLACK);
	rb68.setBounds(650, 11, 7, 32);
	controlPanel.add(rb68);
	
	JButton rb69 = new JButton("");
	rb69.setOpaque(true);
	rb69.setBorderPainted(false);
	rb69.setBackground(Color.BLACK);
	rb69.setBounds(660, 11, 7, 33);
	controlPanel.add(rb69);
	
	JButton rb70 = new JButton("");
	rb70.setOpaque(true);
	rb70.setBorderPainted(false);
	rb70.setBackground(Color.BLACK);
	rb70.setBounds(670, 11, 7, 34);
	controlPanel.add(rb70);
	
	JButton rb71 = new JButton("");
	rb71.setOpaque(true);
	rb71.setBorderPainted(false);
	rb71.setBackground(Color.BLACK);
	rb71.setBounds(680, 11, 7, 36);
	controlPanel.add(rb71);
	
	JButton rb72 = new JButton("");
	rb72.setOpaque(true);
	rb72.setBorderPainted(false);
	rb72.setBackground(Color.BLACK);
	rb72.setBounds(690, 11, 7, 38);
	controlPanel.add(rb72);
	
	JButton rb73 = new JButton("");
	rb73.setOpaque(true);
	rb73.setBorderPainted(false);
	rb73.setBackground(Color.BLACK);
	rb73.setBounds(700, 11, 7, 40);
	controlPanel.add(rb73);
	
	JButton rb74 = new JButton("");
	rb74.setOpaque(true);
	rb74.setBorderPainted(false);
	rb74.setBackground(Color.BLACK);
	rb74.setBounds(710, 11, 7, 42);
	controlPanel.add(rb74);
	
	JButton rb75 = new JButton("");
	rb75.setOpaque(true);
	rb75.setBorderPainted(false);
	rb75.setBackground(Color.BLACK);
	rb75.setBounds(720, 11, 7, 45);
	controlPanel.add(rb75);
	
	JButton rb76 = new JButton("");
	rb76.setOpaque(true);
	rb76.setBorderPainted(false);
	rb76.setBackground(Color.BLACK);
	rb76.setBounds(730, 11, 7, 50);
	controlPanel.add(rb76);
	
	JButton rb77 = new JButton("");
	rb77.setOpaque(true);
	rb77.setBorderPainted(false);
	rb77.setBackground(Color.BLACK);
	rb77.setBounds(740, 11, 7, 55);
	controlPanel.add(rb77);
	
	JButton rb78 = new JButton("");
	rb78.setOpaque(true);
	rb78.setBorderPainted(false);
	rb78.setBackground(Color.BLACK);
	rb78.setBounds(750, 11, 7, 62);
	controlPanel.add(rb78);
	
	JButton rb79 = new JButton("");
	rb79.setOpaque(true);
	rb79.setBorderPainted(false);
	rb79.setBackground(Color.BLACK);
	rb79.setBounds(760, 11, 7, 69);
	controlPanel.add(rb79);
	
	JButton rb80 = new JButton("");
	rb80.setOpaque(true);
	rb80.setBorderPainted(false);
	rb80.setBackground(Color.BLACK);
	rb80.setBounds(770, 11, 7, 75);
	controlPanel.add(rb80);
	
	JButton rb81 = new JButton("");
	rb81.setOpaque(true);
	rb81.setBorderPainted(false);
	rb81.setBackground(Color.BLACK);
	rb81.setBounds(780, 11, 7, 82);
	controlPanel.add(rb81);
	
	JButton rb82 = new JButton("");
	rb82.setOpaque(true);
	rb82.setBorderPainted(false);
	rb82.setBackground(Color.BLACK);
	rb82.setBounds(790, 11, 7, 91);
	controlPanel.add(rb82);
	
	
	rpmArray = new JButton[]{rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb11,
			rb10,rb12,rb13,rb14,rb15,rb16,rb17,rb19,rb20,rb21,rb22,
			rb23,rb24,rb25,rb26,rb27,rb28,rb29,rb30,rb31,rb32,rb33,rb34,
			rb35,rb36,rb37,rb38,rb39,rb40,rb41,rb42,rb45,rb46,rb47,rb48,
			rb49,rb50,rb51,rb52,rb53,rb54,rb55,rb56,rb57,rb58,rb59,rb60,
			rb61,rb62,rb63,rb64,rb65,rb66,rb67,rb68,rb69,rb70,rb71,rb72,
			rb73,rb74,rb75,rb76,rb77,rb78,rb79,rb80,rb81,rb82};
}
}
