package org.dav.learn.corejavabook;

public class TagAttributeValue
{
    private String tagName;
    private String attrName;
    private String attrValue;

    public TagAttributeValue(String tagName, String attrName, String attrValue)
    {
        this.tagName = tagName;
        this.attrName = attrName;
        this.attrValue = attrValue;
    }

    public String getTagName()
    {
        return tagName;
    }

    public String getAttrName()
    {
        return attrName;
    }

    public String getAttrValue()
    {
        return attrValue;
    }
}
