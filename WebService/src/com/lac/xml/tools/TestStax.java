package com.lac.xml.tools;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.EventFilter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestStax {

	@Test
	public void test01() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("book.xml");
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					System.out.println(reader.getName());
				} else if (type == XMLStreamConstants.CHARACTERS) {
					System.out.println(reader.getText().trim());
				} else if (type == XMLStreamConstants.END_ELEMENT) {
					System.out.println(reader.getName());
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void test02() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("book.xml");
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while(reader.hasNext()){
				int type = reader.next();
				if(type == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getName().toString();
					if(name.equals("book")) {
						System.out.println(reader.getAttributeName(0)+":"+reader.getAttributeValue(0));  
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			if (is!=null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
		}
	}
	
	@Test  
    public void test03(){  
        XMLInputFactory factory = XMLInputFactory.newInstance();  
        InputStream is = null;  
        try {  
            is = TestStax.class.getResourceAsStream("book.xml");  
            XMLStreamReader reader = factory.createXMLStreamReader(is);  
            while (reader.hasNext()) {  
                int type = reader.next();  
                if (type == XMLStreamConstants.START_ELEMENT) {  
                    String name = reader.getName().toString();  
                    if (name.equals("title")) {  
                        System.out.println(reader.getAttributeName(0)+":"+reader.getAttributeValue(0));  
                        System.out.print(reader.getElementText()+":");  
                    }  
                    if (name.equals("price")) {  
                        System.out.println(reader.getElementText()+":");  
                    }  
                }  
            }  
        } catch (XMLStreamException e) {  
            e.printStackTrace();  
        }finally{  
            if (is!=null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
	
	@Test  
    public void test04(){  
        XMLInputFactory factory = XMLInputFactory.newInstance();  
        InputStream is = null;  
        try {  
            is = TestStax.class.getResourceAsStream("book.xml");  
            //基于迭代模型的操作方式  
            XMLEventReader reader = factory.createXMLEventReader(is);  
            int num = 0;  
            while (reader.hasNext()) {  
                //通过XMLEvent来获取是否是某种节点  
                XMLEvent event = reader.nextEvent();  
                reader.next();
                if (event.getEventType()==XMLStreamConstants.START_ELEMENT) {  
                    //通过event.asXxxxx来转换节点  
                    String name = event.asStartElement().getName().toString();  
                    if (name.equals("title")) {  
                        System.out.print(reader.getElementText());  
                    }  
                    if (name.equals("year")) {  
                        //System.out.println(reader.getElementText()+":");  
                    }  
                }  
                num++;  
            }  
            System.out.println("遍历次数："+num);  
        } catch (XMLStreamException e) {  
            e.printStackTrace();  
        }finally{  
            if (is!=null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
	
	@Test  
    public void test05(){  
        XMLInputFactory factory = XMLInputFactory.newInstance();  
        InputStream is = null;  
        try {  
            is = TestStax.class.getResourceAsStream("book.xml");  
            //基于迭代模型的操作方式  
            XMLEventReader reader = factory.createFilteredReader(factory.createXMLEventReader(is), 
            		new EventFilter(){

						@Override
						public boolean accept(XMLEvent event) {
							if(event.isStartElement()) {
								String name = event.asStartElement().getName().toString();  
                                if ("title".equals(name)||"price".equals(name)) {  
                                    return true;  
                                }
							}
							return false;
						}
            	
            });  
            int num = 0;  
            while (reader.hasNext()) {  
                //通过XMLEvent来获取是否是某种节点  
                XMLEvent event = reader.nextEvent();  
                if (event.isStartElement()) {  
                    //通过event.asXxxxx来转换节点  
                    String name = event.asStartElement().getName().toString();  
                    if (name.equals("title")) {  
                        System.out.print(reader.getElementText()+":");  
                    }  
                    if (name.equals("price")) {  
                        System.out.println(reader.getElementText()+":");  
                    }  
                }  
                num++;  
            }  
            System.out.println("遍历次数："+num);  
        } catch (XMLStreamException e) {  
            e.printStackTrace();  
        }finally{  
            if (is!=null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
	
	@Test
	public void test06(){
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("book.xml");
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(is);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xpath.evaluate("//book[@category='WEB']", doc,XPathConstants.NODESET);
			int length = nodeList.getLength();  
            for (int i=0;i<length;i++) {  
                Element e = (Element)nodeList.item(i);  
                System.out.println(e.getElementsByTagName("title").item(0).getTextContent());  
            }  
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test07() {
		 try {
			XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
			xsw.writeStartDocument("UTF-8","1.0");
			xsw.writeEndDocument(); 
			String ns = "http://aa:dd";  
            xsw.writeStartElement("test", "person", ns);  
            xsw.writeStartElement(ns,"id");  
            xsw.writeCharacters("1");  
            xsw.writeEndElement();  
            xsw.writeEndElement();  
            xsw.flush();  
            xsw.close();
		 } catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test08(){
		InputStream is = null;
		try {
			is = TestStax.class.getResourceAsStream("book.xml");
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(is);
			XPath xpath = XPathFactory.newInstance().newXPath();
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			NodeList list = (NodeList)xpath.evaluate("//book[title='Harry Potter']", doc,XPathConstants.NODESET);
			Element book = (Element) list.item(0);
			Element price = (Element) (book.getElementsByTagName("price").item(0));
			price.setTextContent("55555"); 
			Result result = new StreamResult(System.out);
			trans.transform(new DOMSource(doc), result);  
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
