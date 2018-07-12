package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog
{
    public AboutDialog(JFrame owner)
    {
        super(owner, "About", true);

        add(new JLabel("<html><h1><i>Java Core</i></h1><hr>By me!</html>"), BorderLayout.CENTER);

        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });

        JPanel panelCommand = new JPanel();
        panelCommand.add(buttonOK);
        add(panelCommand, BorderLayout.SOUTH);

        pack();
    }
}
