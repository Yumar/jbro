/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Yumarx Polanco <yumarx_polanco@jce.do>
 */
public class Jbro extends Application {
    
    @Override
    public void start(Stage stage) {        
        StackPane root = new StackPane();        
        Scene scene = new Scene(new Navegador(),750,500, Color.web("#666970"));
        stage.setTitle("Navegador JCE");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
