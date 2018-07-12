package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.*;

public class FrameTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                JFrame frame = new OptionDialogFrame();
                frame.setTitle("Testing...");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
