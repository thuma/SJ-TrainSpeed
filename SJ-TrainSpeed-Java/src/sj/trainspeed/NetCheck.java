/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sj.trainspeed;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author thuma
 */
public class NetCheck {
    private String netname;
    private boolean sj = true;
    private boolean veolia = false;
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private URL veoliaurl;
    private URLConnection conn;
    private DocumentBuilder builder;
    private Document doc;
    private GetSpeed datainfo;
    
    NetCheck(){

        try {
            if(InetAddress.getByName("www.ombord.info").getHostAddress().equals("213.50.114.147"))
            {
               sj = false;
            }
        } catch (UnknownHostException ex) {
                sj = false;
        }

        
        datainfo =  new GetSpeed("http://veolia.internet--access.net:8105/oxmap_ajax","SpeedOverGround",1.852);
                                          
        if (datainfo.getRaw().equals("-99")){
            veolia = false;
             //System.out.println("Fel");
        }
        if (datainfo.getSpeed() > -1){
            veolia = true;
        }
        

        if(sj) {
          datainfo = new GetSpeed("http://www.ombord.info/api/xml/position/", "speed", 3.6);
          if (datainfo.getSpeed()> -1){
          sj = true;
          }
          else{
          sj = false;
          }
        } 
    }
    
    public boolean isVeolia(){
    return veolia;
    }
    
    public boolean isSJ(){
    return sj;
    }
}
