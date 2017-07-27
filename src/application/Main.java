package application;

import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		JFrame frame = new JFrame();
		try {
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainDashPanel panel = new MainDashPanel();
		frame.getContentPane().add(panel); //TODO
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
