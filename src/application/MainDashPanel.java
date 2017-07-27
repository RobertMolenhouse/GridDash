package application;

import java.awt.BorderLayout;
import java.awt.Color;
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
	
	private JLabel tach; //tachometer, rpm for dummies
	private JLabel speedo; //speedometer, mph for dummies.
	private JLabel coolTmp;
	private JLabel oilTmp;
	private JLabel fuelLvl;
	private JLabel throttlePos;
	private JLabel gear;
	
	private JPanel labelPanel;

	private CommandControl control;
	private CarData data;

	public MainDashPanel() throws IOException, InterruptedException {
		data = new CarData();
		control = new CommandControl(data);		
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		//main panel that all the others will be placed into
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(createLabelPanel(), BorderLayout.NORTH);
		
		add(controlPanel);
		
		start();
	}
	
	private JPanel createLabelPanel(){
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout());
		
		tach = new JLabel(data.getRpm() + "");
		speedo = new JLabel(data.getMph() + "");
		coolTmp = new JLabel(data.getCoolandTemp() + "");
		oilTmp = new JLabel(data.getOilTemp() + "");
		fuelLvl = new JLabel(data.getFuelLevel() + "");
		throttlePos = new JLabel(data.getThrottlePos() + "");
		gear = new JLabel(data.getGear());
		
		labelPanel.add(new JLabel("RPM"));
		labelPanel.add(tach);
		labelPanel.add(new JLabel("MPH"));
		labelPanel.add(speedo);
		labelPanel.add(new JLabel("ct"));
		labelPanel.add(coolTmp);
		labelPanel.add(new JLabel("ot"));
		labelPanel.add(oilTmp);
		labelPanel.add(new JLabel("fuel"));
		labelPanel.add(fuelLvl);
		labelPanel.add(new JLabel("throttle"));
		labelPanel.add(throttlePos);
		labelPanel.add(new JLabel("gear"));
		labelPanel.add(gear);
		
		labelPanel.setBackground(Color.CYAN);
		
		return labelPanel;
		
	}

	//run the CommandControl in its own thread.

private void start(){
	try {
		control.run();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//new action listener for the timer
	ActionListener action = new ActionListener()
	{   
		@Override
		public void actionPerformed(ActionEvent event)
		{
			//update labelPanel
			labelPanel.removeAll();
			createLabelPanel();
			labelPanel.revalidate();
			labelPanel.repaint();
		}
	};
	Timer timer = new Timer(10, action);
	timer.start();
}
}