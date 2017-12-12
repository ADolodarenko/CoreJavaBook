package org.dav.learn.corejavabook;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RobotFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static JFileChooser fileChooser = new JFileChooser();
	
	private JButton screenShotButton;
	private JComboBox<GraphicsDevice> screensCombo;
	
	public RobotFrame()
	{
		setTitle("Screen shots");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		screensCombo = new JComboBox<>(environment.getScreenDevices());
		add(screensCombo, BorderLayout.NORTH);
		
		screenShotButton = new JButton("Take screenshot");
		screenShotButton.addActionListener(new ScreenShotProducer());
		add(screenShotButton, BorderLayout.SOUTH);
	}
	
	private class ScreenShotProducer implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
				try
				{
					GraphicsDevice currentScreen = screensCombo.getItemAt(screensCombo.getSelectedIndex());
					int width = currentScreen.getDisplayMode().getWidth();
					int height = currentScreen.getDisplayMode().getHeight();
					
					Robot robot new Robot(currentScreen);
					//robot.waitForIdle();
					BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, width - 1, height - 1));
					
					ImageIO.write(image, "png", fileChooser.getSelectedFile());
				}
				catch (AWTException ex)
				{
					ex.printStackTrace();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
		}
	}
}
