/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbro.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author Yumarx Polanco <yumarx_polanco@jce.do>
 */
public class NavegadorController implements Initializable {
    @FXML
    private TextField txtURL;
    @FXML
    private Button btnBuscar;
    @FXML
    private WebView navegadorWeb;
    private WebEngine webEngine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = navegadorWeb.getEngine();
        
         // process page loading
        webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == State.SUCCEEDED) {
                            JSObject win = 
                                (JSObject) webEngine.executeScript("window");
                            win.setMember("jbro", new JavaScriptController());
                        }
            }
                }
        );
        
        webEngine.load("http://localhost:8383/servicio-escolar/");
    }    

    @FXML
    private void buscarURL(ActionEvent event) {
        webEngine.load(txtURL.getText());
    }
    
    
    
}
