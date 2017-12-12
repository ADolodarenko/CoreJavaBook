package org.dav.learn.corejavabook;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwoLoggersTest {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
            try
            {
                final int LOG_ROTATION_COUNT = 10;

                Logger logger = Logger.getLogger("");
                logger.setLevel(Level.ALL);

                Handler handler = new FileHandler("%h/" + TwoLoggersTest.class.getSimpleName() + ".log",
                        0, LOG_ROTATION_COUNT);

                logger.addHandler(handler);
            }
            catch (IOException e)
            {}
    }
}
