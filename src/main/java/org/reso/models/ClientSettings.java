package org.reso.models;

import org.reso.commander.Commander;
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

public class ClientSettings {

  public static final String
      SERVER_ID = "ServerId",
      SERVER_NAME = "ServerName",
      VENDOR_NAME = "VendorName",
      LAST_MODIFIED_TIMESTAMP = "LastModifiedTimestamp",
      WEB_API_URI = "WebAPIURI",
      AUTHORIZATION_URI = "AuthorizationURI",
      TOKEN_URI = "TokenURI",
      REDIRECT_URI = "RedirectURI",
      AUTHENTICATION_TYPE = "AuthenticationType",
      BEARER_TOKEN = "BearerToken",
      CLIENT_IDENTIFICATION = "ClientIdentification",
      CLIENT_SECRET = "ClientSecret",
      USERNAME = "UserName",
      PASSWORD = "Password",
      CLIENT_SCOPE = "ClientScope",
      VERSION = "Version",
      PRE_AUTHENTICATE = "Preauthenticate",
      RESULTS_DIRECTORY = "ResultsDirectory",
      SCRIPT_FILE = "ScriptFile";

  public static final String[] supportedClientSettings = {
      SERVER_ID, SERVER_NAME, VENDOR_NAME, LAST_MODIFIED_TIMESTAMP,
      WEB_API_URI, AUTHORIZATION_URI, TOKEN_URI, REDIRECT_URI,
      AUTHENTICATION_TYPE, BEARER_TOKEN, CLIENT_IDENTIFICATION,
      CLIENT_SECRET, USERNAME, PASSWORD, CLIENT_SCOPE,
      VERSION, PRE_AUTHENTICATE, SERVER_NAME, RESULTS_DIRECTORY, SCRIPT_FILE};

  private LinkedHashMap<String, String> settings;

  public ClientSettings() {
    this.settings = new LinkedHashMap<>();
  }

  /***
   * XML de/serialization methods
   */
  public static ClientSettings loadFromRESOScript(File file) {
    //should match the XML property name
    final String CLIENT_SETTINGS_KEY = "ClientSettings";
    Map<String, String> settings = new LinkedHashMap<>();

    try {
      FileInputStream fileIS = new FileInputStream(file);
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = builderFactory.newDocumentBuilder();
      Document xmlDocument = builder.parse(fileIS);
      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = "/OutputScript/" + CLIENT_SETTINGS_KEY + "/node()";
      NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
      Node node;

      for (int i = 0; i < nodes.getLength(); i++) {
        node = nodes.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          settings.put(node.getNodeName(), node.getTextContent());
        }
      }
    } catch (Exception e) {
      //this only needs to be shown once
      //showMessageDialog(appFrame, "Cannot read configuration file.");
      e.printStackTrace();
      System.exit(Commander.NOT_OK);
    }

    ClientSettings clientSettings = new ClientSettings();
    clientSettings.settings = new LinkedHashMap<>(settings);

    return clientSettings;
  }

  private Map<String, String> getSettings() {
    return this.settings;
  }

  public String get(String name) {
    return getSettings().get(name);
  }

  public void put(String name, String value) {
    this.getSettings().put(name, value);
  }

}