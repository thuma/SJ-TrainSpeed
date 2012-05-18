/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sj.trainspeed;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author thuma
 */
public class GetSpeed{

    private String urlString;
    private URL url;
    private URLConnection conn;
    private DocumentBuilder builder;
    private Document doc;
    private int speed;
 
    GetSpeed(String inurl){
    this.urlString = inurl; 
    }
    
    public int getSpeed() throws IOException{
        
    try{
    url = new URL(urlString);
    } catch (MalformedURLException e) {
    System.out.println("Error: "+e.getMessage());
    }
      try{
    conn = url.openConnection();
     } catch (MalformedURLException e) {
    System.out.println("Error: "+e.getMessage());
    }
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error: "+e.getMessage());
        }
        try {
            doc = builder.parse(conn.getInputStream());
        } catch (SAXException e) {
            System.out.println("Error: "+e.getMessage());
        }
        
        NodeList speeds = doc.getElementsByTagName("SpeedOverGround"); 
        Double speedms = Double.parseDouble(speeds.item(0).getTextContent())*1.852;
        speed = speedms.intValue();
        
        return speed;
        }
    }



