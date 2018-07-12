package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class LogFrame extends JFrame
{
    private JFrame frame;

    public LogFrame()
    {
        initialize();
    }

    private void initialize()
    {
        setFrame();
        setMenus();
        setMainPanel();

        pack();
    }

    private void setMainPanel()
    {
        JPanel panel = new JPanel();
        panel.add(getTextPane());
        //panel.setPreferredSize(new Dimension(400, 300));
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private JScrollPane getTextPane()
    {
        JTextArea textArea = new JTextArea(40, 80);
        textArea.setEditable(false);

        return new JScrollPane(textArea);
    }

    private void setFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = this;
    }

    private void setMenus()
    {
        JMenu menu;
        Action action;
        JMenuItem menuItem;

        JMenuBar menuBar = new JMenuBar();

        menu = new JMenu("File");

        action = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        };
        action.putValue(Action.MNEMONIC_KEY, new Integer('A'));

        menuItem = new JMenuItem(action);

        menu.add(menuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);
    }
}
