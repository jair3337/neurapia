/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerVarios")
@ConversationScoped
public class ControllerApp implements Serializable {

    public ControllerApp() {
        
    }
    
    @Inject
    private Conversation conversacion;
    
    public void iniciarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.begin();
        }
    }
    
    public void finalizarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.end();
        }
    }
    
    public String cancelar(){
        finalizarConversacion();
        return "";
    }

    public Conversation getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversation conversacion) {
        this.conversacion = conversacion;
    }
    
    public void redireccionar(String url) throws IOException {
        
        url = this.hostName()+ url + ".xhtml";
        
        if(!"".equals(url)) {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            
        }
         
    }
    
    public String hostName() {
        
        String serverName = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        
        String name = "http://";
        
        if("localhost".equals(serverName)) {
            
            name += serverName  + ":8080/Proyecto/";
        
        } else {
            
            name += serverName;
           
        }
        return name; 
    }
    
}