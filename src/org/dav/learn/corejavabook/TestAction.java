package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TestAction extends AbstractAction
{
    public TestAction(String name)
    {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(null, getValue(Action.NAME) + " selected.",
                "Action", JOptionPane.INFORMATION_MESSAGE);
    }
}
