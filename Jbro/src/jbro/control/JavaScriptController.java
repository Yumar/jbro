/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbro.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebView;
import modules.canon.api.CanonCamera;
import modules.canon.utils.CanonConstants;

/**
 *
 * @author Yumarx Polanco <yumarx_polanco@jce.do>
 */
public class JavaScriptController {
    private CanonCamera cam;  

    public JavaScriptController() {
    }
    
    public void exit() {
        CanonCamera.close();
        Platform.exit();        
    }  
    
    
    public CanonCamera getOpenedCamera(){
        if(cam == null){
            cam = new CanonCamera();
        }
        return cam;
    }    

    public void print(String target, String html) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            WebView printView = new WebView();
            printView.getEngine().loadContent(html);
            printView.getEngine().print(job);
            job.endJob();
        }
    }
    
    public void print(String html) {
        WebView printView = new WebView();
        
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / printView.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / printView.getBoundsInParent().getHeight();
        printView.getTransforms().add(new Scale(scaleX, scaleY));
        
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            printView.getEngine().loadContent(html);
            printView.getEngine().print(job);
            job.endJob();
        }
    }
    
    public String takePhoto(){
        System.out.println("se ejecuta el escript");
        StringBuilder fotoBuilder = new StringBuilder("data:image/jpg;base64,");
        CanonCamera camera = getOpenedCamera();

        if ( camera.openSession() ) {
            System.out.println("la session esta abierta");
            final File[] photos = camera.shoot( CanonConstants.EdsSaveTo.kEdsSaveTo_Host );

            if ( photos != null ) {
                for ( final File photo : photos ) {
                    if ( photo != null ) {
                        try {
                            System.out.println(photo.getAbsolutePath());
                            byte[] bytes = loadFile(photo.getAbsoluteFile());
                            fotoBuilder.append(Base64.getEncoder().encodeToString(bytes));
                        } catch (IOException ex) {
                            Logger.getLogger(JavaScriptController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

            camera.closeSession();
            System.out.println("se cerro la sesion");
        }
        return fotoBuilder.toString();
    }
    
    private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
 
	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
 
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
 
	    is.close();
	    return bytes;
	}

}
