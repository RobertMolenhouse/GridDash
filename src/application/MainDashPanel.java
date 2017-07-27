package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


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
	
	private JPanel controlPanel;

	private CommandControl control;
	private CarData data;

	public MainDashPanel() throws IOException, InterruptedException {
		data = new CarData();
		control = new CommandControl(data);		
		

		
		add(createLabelPanel());
		
		start();
		
	}
	
	private JPanel createLabelPanel(){
		//main panel that all the others will be placed into
				controlPanel = new JPanel();
				controlPanel.setLayout(new GridLayout(8,2));

				tach = new JLabel(data.getRpm() + "");
				speedo = new JLabel(data.getMph() + "");
				coolTmp = new JLabel(data.getCoolandTemp() + "");
				oilTmp = new JLabel(data.getOilTemp() + "");
				fuelLvl = new JLabel(data.getFuelLevel() + "");
				throttlePos = new JLabel(data.getThrottlePos() + "");
				gear = new JLabel(data.getGear());
				gear.setBackground(Color.BLACK);
				
				controlPanel.add(new JLabel("RPM"));
				controlPanel.add(tach);
				controlPanel.add(new JLabel("MPH"));
				controlPanel.add(speedo);
				controlPanel.add(new JLabel("ct"));
				controlPanel.add(coolTmp);
				controlPanel.add(new JLabel("ot"));
				controlPanel.add(oilTmp);
				controlPanel.add(new JLabel("fuel"));
				controlPanel.add(fuelLvl);
				controlPanel.add(new JLabel("throttle"));
				controlPanel.add(throttlePos);
				controlPanel.add(new JLabel("gear"));
				controlPanel.add(gear);
				
				controlPanel.setBackground(Color.CYAN);
				controlPanel.setPreferredSize(new Dimension(790,400));
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
			//update labelPanel
			tach.setText(data.getRpm() + "");
			speedo.setText(data.getMph() + "");
			coolTmp.setText(data.getCoolandTemp() + "");
			oilTmp.setText(data.getOilTemp() + "");
			fuelLvl.setText(data.getFuelLevel() + "");
			throttlePos.setText(data.getThrottlePos() + "");
			gear.setText(data.getGear());
		}
	};
	Timer timer = new Timer(10, action);
	timer.start();
}

	
}
