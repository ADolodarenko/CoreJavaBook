package org.dav.learn.corejavabook;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SysProperties
{
    public static void main(String[] args)
    {
        System.getProperties().list(System.out);

        InputStreamReader isr;
        BufferedReader reader = new BufferedReader(isr = new InputStreamReader(System.in));
        

    }
}
