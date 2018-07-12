package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogFrame extends JFrame
{
    private static final int DEAFULT_WIDTH = 300;
    private static final int DEAFULT_HEIGHT = 200;
    private JDialog dialog;

    public DialogFrame()
    {
        setSize(DEAFULT_WIDTH, DEAFULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (dialog == null)
                    dialog = new AboutDialog(DialogFrame.this);

                dialog.setVisible(true);
            }
        });
        fileMenu.add(aboutItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
    }

}
