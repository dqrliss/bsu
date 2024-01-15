package com.example.sem_project_javafx;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class for_xml {
    public List<String> parse(String fileName) {
        Root root = new Root();

        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("open parsing error" + e);
            return null;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChilds = rootNode.getChildNodes();

        String mainName = null;
        Node expressionsNode = null;
        for(int i = 0; i < rootChilds.getLength(); i++) {
            if(rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChilds.item(i).getNodeName()) {
                case "name": {
                    mainName = rootChilds.item(i).getTextContent();
                    break;
                }
                case "expressions": {
                    expressionsNode = rootChilds.item(i);
                    break;
                }
            }
        }

        if(expressionsNode == null) {
            return null;
        }

        List<Expressions> expressionsList = new ArrayList<>();
        NodeList peopleChilds = expressionsNode.getChildNodes();
        List<String> expressions = new ArrayList<>();
        for(int i = 0; i < peopleChilds.getLength(); i++) {
            if(peopleChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if(!peopleChilds.item(i).getNodeName().equals("element")) {
                continue;
            }

            String exp = "";
            NodeList elementChilds = peopleChilds.item(i).getChildNodes();
            for(int j = 0; j < elementChilds.getLength(); j++) {
                if(elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (elementChilds.item(j).getNodeName()) {
                    case "expression": {
                        exp = elementChilds.item(j).getTextContent();
                        expressions.add(exp);
                        break;
                    }
                }
            }
            Expressions people = new Expressions(exp);
            expressionsList.add(people);
        }

        root.setName(mainName);
        root.setExpressions(expressionsList);

        return expressions;
    }

    public void writeInXml(String filename, Vector<Double> results) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("Results");
            document.appendChild(rootElement);

            for (Double result : results) {
                Element resultElement = document.createElement("Result");
                resultElement.appendChild(document.createTextNode(result.toString()));
                rootElement.appendChild(resultElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(filename + ".xml");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        }
    }
}
