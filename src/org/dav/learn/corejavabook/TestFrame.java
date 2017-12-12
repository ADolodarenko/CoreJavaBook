package org.dav.learn.corejavabook;

import javax.swing.*;

public class TestFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public TestFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Зайки в черном");
		
		RepaintManager.currentManager(getRootPane()).setDoubleBufferingEnabled(false);
		((JComponent)getContentPane()).setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
	}
	
}
