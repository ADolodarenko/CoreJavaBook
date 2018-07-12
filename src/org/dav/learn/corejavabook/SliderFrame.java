package org.dav.learn.corejavabook;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class SliderFrame extends JFrame
{
    private JSlider slider;
    private JLabel label;
    private ChangeListener listener;

    public SliderFrame()
    {
        initiateListener();

        initiateSlider();
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(slider);
        getContentPane().add(sliderPanel, BorderLayout.SOUTH);

        initiateLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);
        labelPanel.setPreferredSize(new Dimension(60, 30));
        getContentPane().add(labelPanel, BorderLayout.CENTER);

        pack();
    }

    private void initiateListener()
    {
        listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                Object source = e.getSource();

                if (source != null && (source instanceof JSlider))
                {
                    JSlider sourceSlider = (JSlider)source;

                    if (!sourceSlider.getValueIsAdjusting())
                    {
                        int key = sourceSlider.getValue();
                        Object value = sourceSlider.getLabelTable().get(key);

                        if (value != null && (value instanceof JLabel))
                            updateLabel(((JLabel) value).getText());
                    }
                }
            }
        };
    }

    private void initiateSlider()
    {
        Dictionary<Integer, JComponent> labels = new Hashtable<>();
        labels.put(new Integer(1), new JLabel("I"));
        labels.put(new Integer(2), new JLabel("II"));
        labels.put(new Integer(3), new JLabel("III"));
        labels.put(new Integer(4), new JLabel("IV"));
        labels.put(new Integer(5), new JLabel("V"));

        slider = new JSlider(SwingConstants.HORIZONTAL, 1, 5, 1);
        slider.setLabelTable(labels);
        slider.setMajorTickSpacing(1);
        slider.addChangeListener(listener);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }

    private void initiateLabel()
    {
        Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 28);

        label = new JLabel();
        label.setFont(font);
    }

    private void updateLabel(String text)
    {
        label.setText(text + " ");
    }
}
