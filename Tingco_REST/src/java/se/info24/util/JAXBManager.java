/*
 * Copyright (c) 2008 Fareoffice CRS AB. All Rights Reserved.
 */

package se.info24.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Singleton for managing JAXB related tasks inside the Fareoffice system.
 * TODO: Can this be static or should it maybe be changed into a factory that
 * generates message specific managers instead?
 *
 * The singleton pattern used here is based on the recommended method
 * by Bill Pugh. Read more at http://en.wikipedia.org/wiki/Singleton_pattern
 * 
 * @author Danish Shaukat | danish@indpro.se
 */
public class JAXBManager
{
  
  /**
   * Private visibility so no instances 
   * may be created outside this class.
   */
  private JAXBManager()
  {
  }  
  /**
	 * Creates a Singleton static instance
	 */
	private static class SingletonHolder {
		private final static JAXBManager instance = new JAXBManager();
	}

	/**
	 * Returns a Singleton static instance
       * 
       * @return JAXBManager
       */
	public static JAXBManager getInstance() {
		return SingletonHolder.instance;
	}
  /**
   * This Fucntion Marshalls the Object data into the XML Format with the JAXB Marshaller and then append the result into the Writer
   *
   * @param marshallData 
   * 
   * @return - generatedXMLRequest as String
   * 
   * @throws javax.xml.bind.JAXBException 
   */
  public String marshall(Object marshallData) throws JAXBException
  {
    String generatedXMLRequest = null;
    try
    {
      JAXBContext jaxbCtx = JAXBContext.newInstance(marshallData.getClass().getPackage().getName());
      Marshaller marshaller = jaxbCtx.createMarshaller();
      Writer writer = new StringWriter();
      marshaller.marshal(marshallData, writer);
      generatedXMLRequest = writer.toString();
      writer = null;
    }
    catch (JAXBException ex)
    {
        TCMUtil.exceptionLog(getClass().getName(), ex);

    }
    return generatedXMLRequest;
  }

  /**
   * This method Unmarshalls the XML data with the JAXB UnMarshaller 
   * and then Returns the Unmarshall JAXB Object
   *
   * @param xmlUnmarshallData 
   *
   * @param contextClass 
   * 
   * @return - unMarshallData as Object
   */
  public Object unMarshall(String xmlUnmarshallData, Class contextClass)
  {
    Object unMarshallData = null;

    try
    {
      JAXBContext jaxbCtx = JAXBContext.newInstance(contextClass.getPackage().getName());
      Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
      unMarshallData = unmarshaller.unmarshal(new StringReader(xmlUnmarshallData));
    }
    catch (JAXBException ex)
    {
      TCMUtil.exceptionLog(getClass().getName(), ex);
    }

    return unMarshallData;
  }

  /**
   * This method Unmarshalls the XML data with the JAXB UnMarshaller
   * and then Returns the Unmarshall JAXB Object
   *
   * @param xmlUnmarshallData
   *
   * @param contextClass
   *
   * @return - unMarshallData as Object
   */
  public Object unMarshall(File xmlUnmarshallData, Class contextClass)
  {
    Object unMarshallData = null;

    try
    {
      JAXBContext jaxbCtx = JAXBContext.newInstance(contextClass.getPackage().getName());
      Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
      unMarshallData = unmarshaller.unmarshal(xmlUnmarshallData);
    }
    catch (JAXBException ex)
    {
      TCMUtil.exceptionLog(getClass().getName(), ex);
    }

    return unMarshallData;
  }

  /**
   * Overloads unMarshall with the possibility to make
   * the unmarshalling with the unMarshall type.
   * 
   * TODO: However, this should be discussed if it really is correct.
   * 
   * @param xmlUnmarshallData
   * @param unMarshallType
   * @return
   */
  public Object unMarshall(String xmlUnmarshallData, Object unMarshallType)
  {
    return this.unMarshall(xmlUnmarshallData, unMarshallType.getClass());
  }
}
