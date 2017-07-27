package application;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javafx.animation.*;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/*
 * switching to swing.  Things to remember to put in. gauges, exit button, annunciator lights, depending on how far
 * i get, need to be able to switch between different dashes.  Annunciator lights programmable.
 */
public class MainDashPanel extends JPanel implements Initializable {
	
	private JLabel tach; //tachometer, rpm for dummies
	private JLabel speedo; //speedometer, mph for dummies.
	private JLabel coolTmp;
	private JLabel oilTmp;
	private JLabel fuelLvl;
	private JLabel throttlePos;
	private JLabel gear;

	private CommandControl control;
	private CarData data;

	public MainDashPanel() throws IOException, InterruptedException {
		data = new CarData();
		control = new CommandControl(data);		
		//main panel that all the others will be placed into
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());

		controlPanel.add(createLabelPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createLabelPanel(){
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout());
		
		tach = new JLabel();
		speedo = new JLabel();
		coolTmp = new JLabel();
		oilTmp = new JLabel();
		fuelLvl = new JLabel();
		throttlePos = new JLabel();
		gear = new JLabel();
		
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
		
		return labelPanel;
		
	}

	//run the CommandControl in its own thread.
	private Service<Void> obdControl;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		bindToTime();

		obdControl = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						try {
							control = new CommandControl(data);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							control.run();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;

					}
				};
			}
		};
		obdControl.start();
	}

	@FXML
	private void bindToTime() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int rpm = data.getRpm();
				tach.setText(rpm + "");
			
				//TODO re-implement annunciator lights when ready.
//				//check for annunciator lights.
//				if (rpm > 2000) {
//					g1.setFill(Color.web("0x3FFF2F"));
//					g1.setOpacity(1);
//					g2.setFill(Color.web("0x3FFF2F"));
//					g2.setOpacity(1);
//					if (rpm > 3000) {
//						y1.setFill(Color.web("0xFBFF00"));
//						y1.setOpacity(1);
//						y2.setFill(Color.web("0xFBFF00"));
//						y2.setOpacity(1);
//						if (rpm > 4000) {
//							r1.setFill(Color.web("0xFF0000"));
//							r1.setOpacity(1);
//							r2.setFill(Color.web("0xFF0000"));
//							r2.setOpacity(1);
//						}else{
//							r1.setFill(Color.web("0x700d0b"));
//							r1.setOpacity(0.25);
//							r2.setFill(Color.web("0x700d0b"));
//							r2.setOpacity(0.25);
//						}
//					}else{
//						y1.setFill(Color.web("0x82860b"));
//						y1.setOpacity(0.25);
//						y2.setFill(Color.web("0x82860b"));
//						y2.setOpacity(0.25);
//					}
//				}else{
//					g1.setFill(Color.web("0x0b7215"));
//					g1.setOpacity(0.25);
//					g2.setFill(Color.web("0x0b7215"));
//					g2.setOpacity(0.25);
//				}
				speedo.setText(data.getMph() + "");
				coolTmp.setText(data.getCoolandTemp() + "");
				throttlePos.setText(data.getThrottlePos() + "");
				fuelLvl.setText(data.getFuelLevel() + "");
				oilTmp.setText(data.getOilTemp() + "");
				gear.setText(data.getGear());
			}
		}), new KeyFrame(Duration.seconds(0.03)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	@FXML
	private void ALHandler(ActionEvent event){
		
	}

}