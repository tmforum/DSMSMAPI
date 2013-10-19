/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest2;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pierregauthier
 */

@XmlRootElement
public class ExecutionState implements Serializable {
   
  
    private String executionState;

 
  @XmlElement
    public String getExecutionState() {
        return executionState;
    }

    public void setExecutionState(String executionState) {
        this.executionState = executionState;
    }
    
}

    

