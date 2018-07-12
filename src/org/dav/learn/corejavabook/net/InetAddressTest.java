package org.dav.learn.corejavabook.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest
{
    private static final String STR_PROMPT = "address: ";
    private static final String STR_EXIT = "exit";
    private static final String STR_LOCALHOST = "localhost";
    private static final String STR_FULLINFO = "fi";
    private static final String STR_SHORTINFO = "si";

    private BufferedReader reader;
    private String input;
    private boolean fullInfo;

    public static void main(String[] args)
    {
        new InetAddressTest().process();
    }

    public void process()
    {
        init();

        while (true)
        {
            System.out.print(STR_PROMPT);
            readInput();

            if (input == null || STR_EXIT.equalsIgnoreCase(input))
                break;

            if (!setParams())
                printAddresses();
        }
    }

    private boolean setParams()
    {
        if (STR_FULLINFO.equalsIgnoreCase(input))
        {
            fullInfo = true;
            return true;
        }

        if (STR_SHORTINFO.equalsIgnoreCase(input))
        {
            fullInfo = false;
            return true;
        }

        return false;
    }

    private void init()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fullInfo = false;
    }

    private void readInput()
    {
        input = null;

        try
        {
            input = reader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void printAddresses()
    {
        try
        {
            if (STR_LOCALHOST.equalsIgnoreCase(input))
                System.out.println(getAddressInfo(InetAddress.getLocalHost()));
            else
            {
                InetAddress[] addresses = InetAddress.getAllByName(input);

                for (InetAddress address : addresses)
                    System.out.println(getAddressInfo(address));
            }
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }

    private String getAddressInfo(InetAddress address)
    {
        if (fullInfo)
            return address.toString();
        else
            return address.getHostAddress();
    }
}
