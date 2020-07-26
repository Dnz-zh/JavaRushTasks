package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws TransformerException, IOException, SAXException, JAXBException, ParserConfigurationException {
        //сериализуем объект в XML
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
        String xml = writer.toString();
        //сериализация

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));//DOM представление XML в виде множества узлов
        //альтернативный вариант: сразу сериализовать объект в doc
        //Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        //marshaller.marshal(obj, doc);

        NodeList nodes = doc.getElementsByTagName("*"); //это коллекция, которая содержит все узлы представления XML
        //метод Document.getElementsByTagName() позволяет проходить XML не построчно, а по узлам

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            replaceTextWithCDATA(node, doc);
            if (node.getNodeName().equals(tagName)) {
                Comment com = doc.createComment(comment); //+ System.getProperty("line.separator")
                node.getParentNode().insertBefore(com, node);
            }

        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }

    private static void replaceTextWithCDATA(Node node, Document doc) {
        if ((node.getNodeType() == 3) && (Pattern.compile(".*[<>&'\"].*").matcher(node.getTextContent()).find())) {
            //Это позволяет экранировать символы разметки XML в чистом тексте при помощи CDATA
            Node cnode = doc.createCDATASection(node.getNodeValue());//создаём новый узел типа CDATA
            node.getParentNode().replaceChild(cnode, node);//замена узла типа "текст" на узел типа "CDATA"
        }

        NodeList list = node.getChildNodes();

        for (int i = 0; i < list.getLength(); i++) {
            replaceTextWithCDATA(list.item(i), doc);
        }
    }


    public static void main(String[] args) throws Exception {
        SomeObject someObject = new SomeObject();
        someObject.name = "daedra";
        someObject.x = 10;
        System.out.println(toXmlWithComment(someObject, "name", "it's a comment"));
    }
}
