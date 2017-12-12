package org.dav.learn.corejavabook;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private static Logger logger = Logger.getLogger("org.dav.learn.corejavabook");

    public ImageViewerFrame()
    {
        logger.entering("ImageViewerFrame", "<init>");

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logger.fine("Exiting.");

                System.exit(0);
            }
        });

        label = new JLabel();
        add(label);

        logger.exiting("ImageViewerFrame", "<init>");

    }

    private class FileOpenListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            logger.entering("ImageViewerFrame.FileOpenListener",
                    "actionPerformed", e);

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            chooser.setFileFilter(new FileFilter()
            {
                @Override
                public boolean accept(File f)
                {
                    return f.getName().toLowerCase().endsWith(".gif") ||
                            f.isDirectory();
                }

                @Override
                public String getDescription()
                {
                    return "GIF Images";
                }
            });

            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            if (r == JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();

                logger.log(Level.FINE, "Reading file {0}", name);

                label.setIcon(new ImageIcon(name));
            }
            else
            {
                logger.fine("File open dialog canceled.");

                logger.exiting("ImageViewerFrame.FileOpenListener",
                        "actionPerformed");
            }
        }
    }
}
