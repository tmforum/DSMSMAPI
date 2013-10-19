/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import org.tmforum.xml.tip.smi.ManagementReport;

/**
 *
 * @author pierregauthier
 */

@XmlRootElement
public class HubEvent implements Serializable { 
    
    private ManagementReport event;
    
    private String eventType;

    public ManagementReport getEvent() {
        return event;
    }

    public void setEvent(ManagementReport event) {
        this.event = event;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    
    
}
