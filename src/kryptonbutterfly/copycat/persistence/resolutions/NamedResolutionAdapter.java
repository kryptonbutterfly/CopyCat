package kryptonbutterfly.copycat.persistence.resolutions;

import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import kryptonbutterfly.xmlConfig4J.TypeAdapter;
import kryptonbutterfly.xmlConfig4J.XmlReader;
import kryptonbutterfly.xmlConfig4J.XmlWriter;
import kryptonbutterfly.xmlConfig4J.exceptions.AttributeNotFoundException;
import kryptonbutterfly.xmlConfig4J.exceptions.BrokenReferenceException;

public final class NamedResolutionAdapter implements TypeAdapter<NamedResolution>
{
	@Override
	public Class<NamedResolution> getType()
	{
		return NamedResolution.class;
	}
	
	@Override
	public boolean isValueType()
	{
		return true;
	}
	
	@Override
	public void write(XmlWriter writer, Element elem, NamedResolution value) throws IllegalAccessException
	{
		if (value == null)
			writer.writeNull(elem);
		else
		{
			elem.setAttribute(writer.getTags().valueTag(), value.resolutionName());
		}
	}
	
	@Override
	public NamedResolution read(XmlReader reader, Node node, Class<?> classOfT)
		throws ClassNotFoundException,
		AttributeNotFoundException,
		NoSuchFieldException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException,
		NoSuchMethodException,
		BrokenReferenceException
	{
		if (reader.isNull(node))
			return null;
		final var attrValue = XmlReader.getAttribute(node, reader.getTags().valueTag());
		return NamedResolution.getByDescription(attrValue.getValue());
	}
}
