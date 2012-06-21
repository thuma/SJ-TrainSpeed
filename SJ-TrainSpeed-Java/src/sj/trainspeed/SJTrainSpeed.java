package sj.trainspeed;

import java.io.IOException;


/**
 *
 * @author Martin Thruesson
 */
public class SJTrainSpeed {
    
    
    private GetSpeed getter;
     /**
     * @param none void.
     */
    public static void main(String[] args){
             
     InfoWindow userwindow = new InfoWindow();
     userwindow.setVisible(true);
     
     NetCheck netCheck = new NetCheck();

     GetSpeed getter = new GetSpeed("","",1.0);
          
     if(netCheck.isVeolia()){
        getter =  new GetSpeed("http://veolia.internet--access.net:8105/oxmap_ajax","SpeedOverGround",1.852);
     }
     else if(netCheck.isSJ()){
        getter =  new GetSpeed("http://www.ombord.info/api/xml/position/", "speed", 3.6);
     }
     
     
     while(true){
     
     userwindow.setSpeed(getter.getSpeed());
     try{Thread.sleep(2000);} catch(InterruptedException e){};
     
     }
    }
}
