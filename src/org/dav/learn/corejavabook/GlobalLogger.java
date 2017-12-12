package org.dav.learn.corejavabook;

import java.util.logging.Logger;

public class GlobalLogger {
    public static void main(String[] args) {
        Logger.getGlobal().info("File->Something happened.");
    }
}
