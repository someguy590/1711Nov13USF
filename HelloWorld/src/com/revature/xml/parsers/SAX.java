package com.revature.xml.parsers;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
public class SAX 
{
/*SAX XML PARSER in java 
 * sax stands for simple api for xml parsing
 * this is an event bbased xml parser and it parses the xml file
 * step by step. it is much more suitable for larger xml files.
 * sax xml parser fires an event when it encounters an opengin 
 * tag, element, or attribute, and parses accordingly
 *  
 */String filename = "com/revature/xml/parsers";
	public static void main(String[] args)throws SAXException,ParserConfigurationException
	{
		
		try
		{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sax = spf.newSAXParser();
			DefaultHandler handler = new DefaultHandler()
			{
					boolean fn = false;
					boolean ln = false;
					boolean age = false;
					public void startElement(String uri,String localName,String name,Attributes attributes)
					{
						System.out.println("Start Element: "+name);
						if(name.equalsIgnoreCase("FIRSTNAME")) fn =true;
						if(name.equalsIgnoreCase("LASTNAME")) ln = true;
						if(name.equalsIgnoreCase("age")) age = true;
						
					}
			};
			
			public void endElement(String uri, String localName, String qname)throws SAXException
			{
				System.out.println("end element"+qname);
			}
			
			public void characters(char ch[],int start,int length) throws SAXException
			{
				if(fn)
				{
					System.out.println("Firstname"+new String(ch,start,length));
				}
				if(ln)
				{
					System.out.println("Last name "+new String(ch,start,length));
				}
				if(age)
				{
					System.out.println("Age: "+new String(ch,Start,length));
					age =false;
				}
			};
		}
		//inner class - a class within another class
		catch(ParserConfigurationException | SAXException e)
		{
			
		}
	}
}