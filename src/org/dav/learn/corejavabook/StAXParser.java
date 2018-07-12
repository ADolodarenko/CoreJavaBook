package org.dav.learn.corejavabook;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class StAXParser
{
    private URL resource;
    private List<TagAttributeValue> searchParams;

    public StAXParser(String filePath, List<TagAttributeValue> searchParams) throws MalformedURLException
    {
        this.resource = new URL(filePath);

        if (searchParams == null)
            throw new IllegalArgumentException("Search parameters can't be empty.");
        else
            this.searchParams = searchParams;
    }

    public List<TagAttributeValue> parse()
    {
        List<TagAttributeValue> result = null;

        try
        {
            InputStream in = resource.openStream();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(in);

            result = new LinkedList<>();

            while (reader.hasNext())
            {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT)
                {
                    for (TagAttributeValue param : searchParams)
                    {
                        String tagName = param.getTagName();
                        if (tagName.equalsIgnoreCase(reader.getLocalName()))
                        {
                            String attrName = param.getAttrName();
                            String attrValue = reader.getAttributeValue(null, attrName);

                            if (attrValue != null)
                                result.add(new TagAttributeValue(tagName, attrName, attrValue));
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XMLStreamException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
