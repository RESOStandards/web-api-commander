package org.reso.models;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parameters {
    private final Map<String, String> parameters;

    public Parameters() {
        parameters = new LinkedHashMap<>();
    }


    public static Parameters loadFromRESOScript(File file) {
        final String PARAMETERS_KEY = "Parameters";
        final String NAME_FIELD = "Name";
        final String VALUE_FIELD = "Value";


        Map<String, String> settings = new LinkedHashMap<>();

        try {
            FileInputStream fileIS = new FileInputStream(file);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(fileIS);
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/OutputScript/" + PARAMETERS_KEY + "/node()";
            NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            Node node;
            String name, value;

            for (int i = 0; i < nodes.getLength(); i++) {
                node = nodes.item(i);

                if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                    name = node.getAttributes().getNamedItem(NAME_FIELD).getNodeValue();
                    value = node.getAttributes().getNamedItem(VALUE_FIELD).getNodeValue();

                    settings.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parameters parameters = new Parameters();
        parameters.getParameters().putAll(settings);

        return parameters;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public String getValue(String name) {
        return parameters.get(name);
    }

    public void putParameter(String name, String value) {
        this.parameters.put(name, value);
    }
}