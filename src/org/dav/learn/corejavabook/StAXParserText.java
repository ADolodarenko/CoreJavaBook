package org.dav.learn.corejavabook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StAXParserText
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        List<TagAttributeValue> searchParams = new ArrayList<>();
        searchParams.add(new TagAttributeValue("a", "href", null));

        List<TagAttributeValue> result = null;

        while ( !"exit".equalsIgnoreCase(input = reader.readLine()))
        {
            StAXParser parser = new StAXParser(input, searchParams);
            result = parser.parse();

            if (result != null)
                for (TagAttributeValue item : result)
                    System.out.println("tag: " + item.getTagName() +
                            "; attr: " + item.getAttrName() +
                            "; value: " + item.getAttrValue());
        }

        reader.close();
    }
}
