package org.dav.learn.corejavabook;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingImageViewer
{
    public static void main(String[] args)
    {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            Logger logger = Logger.getLogger("org.dav.learn.corejavabook");
            logger.setLevel(Level.ALL);

            try
            {
                final int LOG_ROTATION_COUNT = 10;

                Handler handler = new FileHandler("%h/LoggingImageViewer.log",
                        0, LOG_ROTATION_COUNT);

                logger.addHandler(handler);
            }
            catch (IOException e)
            {
                logger.log(Level.SEVERE, "Can't create log file handler", e);
            }
        }

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Handler windowHandler = new WindowHandler();
                windowHandler.setLevel(Level.ALL);
                Logger.getLogger("org.dav.learn.corejavabook").addHandler(windowHandler);

                JFrame frame = new ImageViewerFrame();
                frame.setTitle("LoggingImageViewer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Logger.getLogger("org.dav.learn.corejavabook").fine("Showing frame");

                frame.setVisible(true);
            }
        });
    }
}
