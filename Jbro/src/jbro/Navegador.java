/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbro;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jbro.control.JavaScriptController;
import modules.canon.api.CanonCamera;
import netscape.javascript.JSObject;

/**
 *
 * @author Yumarx Polanco <yumarx_polanco@jce.do>
 */
public class Navegador extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Navegador() {
        //apply the styles
        getStyleClass().add("navegador");
        //prepare custom javascript object
        webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                            JSObject win = 
                                (JSObject) webEngine.executeScript("window");
                            win.setMember("jbro", new JavaScriptController());
                        }
            }
                }
        );
        // load the web page
        webEngine.load("http://10.12.2.93/jce-registro-escolar/");
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }

    @Override
    protected void finalize() throws Throwable {
        CanonCamera.close();
        super.finalize();
    }
    
    
}
