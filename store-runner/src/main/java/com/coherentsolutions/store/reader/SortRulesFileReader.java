package com.coherentsolutions.store.reader;

import com.coherentsolutions.store.exceptions.ParsingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortRulesFileReader {

    private static final String SORT_RULES = "sort-rules";
    private static final Integer INDEX = 0;

    public Map<String, String> readXmlTagNames(String sortRulesFile){
        Map<String, String> sortRulesFileData = new LinkedHashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(sortRulesFile).getFile());

        try (InputStream inputStream = new FileInputStream(file)) {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();
            NodeList nodeList = (NodeList) document.getElementsByTagName(SORT_RULES).item(INDEX);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String sortingName = eElement.getNodeName();
                    String sortingCriteria = eElement.getTextContent();
                    sortRulesFileData.put(sortingName, sortingCriteria);
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParsingException(e);
        }
        return sortRulesFileData;
    }
}
