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

    private String tagname;
    private String urlString;
    private Double speedfactor;
    private URL url;
    private URLConnection conn;
    private DocumentBuilder builder;
    private Document doc;
    private int speed;
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
    GetSpeed(String inurl, String tagname, Double speedfactor){
        this.tagname = tagname;
        this.speedfactor = speedfactor;
        try {
            this.url = new URL(inurl);
        } catch (MalformedURLException urlerror) {
            System.out.println("URL malformed: "+urlerror.getMessage());
        }
    }
    
    public int getSpeed(){
  
    try{
    conn = url.openConnection();
    builder = factory.newDocumentBuilder();
    doc = builder.parse(conn.getInputStream());
    
     } catch (IOException | SAXException | ParserConfigurationException all) {
    System.out.println("Error: "+all.getMessage());
    return -9999;
    }   
        // Look for tagname:
        NodeList speeds = doc.getElementsByTagName(tagname); 
        // The speed data is a float, and it is in knots.
        // To make the speed to km/h mulitiplyby the knots ratio (1,852km/h/knot)
        Double speedms = Double.parseDouble(speeds.item(0).getTextContent())*speedfactor;
        // change to int. No round.
        speed = speedms.intValue();
        // return speed
        return speed;
        }
    }



