package sj.trainspeed;

import java.io.IOException;

/**
 *
 * @author Martin Thruesson
 */
public class SJTrainSpeed {

    /**
     * @param none void.
     */
    public static void main(String[] args) throws IOException, InterruptedException{
     InfoWindow userwindow = new InfoWindow();
    
     GetSpeed getter =  new GetSpeed("http://veolia.internet--access.net:8105/oxmap_ajax","SpeedOverGround",1.852);
     //GetSpeed getter =  new GetSpeed("http://www.ombord.info/api/xml/position/", "speed", 3.6);
     userwindow.setVisible(true);
     while(true){
     userwindow.setSpeed(getter.getSpeed());
     Thread.sleep(2000);
     }
    }
}
