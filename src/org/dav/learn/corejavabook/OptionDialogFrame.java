package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OptionDialogFrame extends JFrame
{
    private ButtonPanel typePanel;
    private ButtonPanel messagePanel;
    private ButtonPanel messageTypePanel;
    private ButtonPanel optionTypePanel;
    private ButtonPanel optionsPanel;
    private ButtonPanel inputPanel;

    private JComboBox<String> themesCombo;

    private String messageString = "Message";
    private Icon messageIcon = new ImageIcon("blue-ball.gif");
    private Object messageObject = new Date();
    private Component messageComponent = new SampleComponent();

    private Map<String, String> installedThemes;

    public OptionDialogFrame()
    {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 3));

        typePanel = new ButtonPanel("Type", "Message", "Confirm", "Option", "Input");
        messageTypePanel = new ButtonPanel("Message type", "ERROR_MESSAGE",
                "INFORMATION_MESSAGE", "WARNING_MESSAGE", "QUESTION_MESSAGE", "PLAIN_MESSAGE");
        messagePanel = new ButtonPanel("Message", "String", "Icon", "Component",
                "Other", "Object[]");
        optionTypePanel = new ButtonPanel("Confirm", "DEFAULT_OPTION", "YES_NO_OPTION",
                "YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION");
        optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
        inputPanel = new ButtonPanel("Input", "Text field", "Combo box");

        gridPanel.add(typePanel);
        gridPanel.add(messageTypePanel);
        gridPanel.add(messagePanel);
        gridPanel.add(optionTypePanel);
        gridPanel.add(optionsPanel);
        gridPanel.add(inputPanel);

        JPanel showPanel = new JPanel();

        themesCombo = new JComboBox<>();
        loadInstalledThemes();
        showPanel.add(themesCombo);

        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ShowAction());

        showPanel.add(showButton);

        add(gridPanel, BorderLayout.CENTER);
        add(showPanel, BorderLayout.SOUTH);

        pack();
    }

    private void setCurrentTheme(String themeName)
    {
        String themeClass = installedThemes.get(themeName);

        if (themeClass != null)
            try
            {
                UIManager.setLookAndFeel(themeClass);
                SwingUtilities.updateComponentTreeUI(this);

                pack();
            }
            catch (Exception e)
            {

            }
    }

    private void loadInstalledThemes()
    {
        installedThemes = new HashMap<>();

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        {
            installedThemes.put(info.getName(), info.getClassName());

            themesCombo.addItem(info.getName());
        }

        themesCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JComboBox<String> combo = (JComboBox<String>)e.getSource();

                setCurrentTheme(combo.getItemAt(combo.getSelectedIndex()));
            }
        });
    }

    public Object getMessage()
    {
        String s = messagePanel.getSelection();

        if ("String".equals(s))
            return messageString;
        else if ("Icon".equals(s))
            return messageIcon;
        else if ("Component".equals(s))
            return messageComponent;
        else if ("Object[]".equals(s))
            return new Object[]{messageString, messageIcon, messageComponent, messageObject};
        else if ("Other".equals(s))
            return messageObject;
        else
            return null;
    }

    public Object[] getOptions()
    {
        String s = optionsPanel.getSelection();

        if ("String[]".equals(s))
            return new String[]{"Yellow", "Blue", "Red"};
        else if ("Icon[]".equals(s))
            return new Icon[]{new ImageIcon("yellow-ball.gif"),
                                new ImageIcon("blue-ball.gif"),
                                new ImageIcon("red-ball.gif")};
        else if ("Object[]".equals(s))
            return new Object[]{messageString, messageIcon, messageComponent, messageObject};
        else
            return null;
    }

    public int getType(ButtonPanel panel)
    {
        String s = panel.getSelection();
        try
        {
            return JOptionPane.class.getField(s).getInt(null);
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    private class ShowAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if ("Confirm".equals(typePanel.getSelection()))
                JOptionPane.showConfirmDialog(OptionDialogFrame.this,
                        getMessage(), "Title", getType(optionTypePanel),
                        getType(messageTypePanel));
            else if ("Input".equals(typePanel.getSelection()))
                if ("Text field".equals(inputPanel.getSelection()))
                    JOptionPane.showInputDialog(OptionDialogFrame.this,
                            getMessage(), "Title",
                            getType(messageTypePanel));
                else
                    JOptionPane.showInputDialog(OptionDialogFrame.this,
                            getMessage(), "Title",
                            getType(messageTypePanel),
                            null,
                            new String[]{"Yellow", "Blue", "Red"},
                            "Blue");
            else if ("Message".equals(typePanel.getSelection()))
                JOptionPane.showMessageDialog(OptionDialogFrame.this,
                        getMessage(), "Title",
                        getType(messageTypePanel));
            else if ("Option".equals(typePanel.getSelection()))
                JOptionPane.showOptionDialog(OptionDialogFrame.this,
                        getMessage(), "Title", getType(optionTypePanel),
                        getType(messageTypePanel), null, getOptions(), getOptions()[0]);
        }
    }
}

class SampleComponent extends JComponent
{
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);

        g2.setPaint(Color.YELLOW);
        g2.fill(rect);

        g2.setPaint(Color.BLUE);
        g2.draw(rect);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(10, 10);
    }
}
