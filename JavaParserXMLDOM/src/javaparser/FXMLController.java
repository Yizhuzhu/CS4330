/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparser;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;



/**
 * FXML Controller class
 *
 * @author apple
 */
public class FXMLController implements Initializable {
   
    @FXML
    private TextArea textArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       //TODO
    } 
    
    @FXML
    private void handleOpen(ActionEvent event) {
      
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
                if (file != null){
                    try{
                        this.textArea.clear();
                        UseNode root=Loader.load(file);
                        showParsedXML(root);
               
                }catch (Exception ex) {
                   displayExceptionAlert("Exceptional Error.", ex);
                }}
    }

    private void showParsedXML(UseNode node){
        textArea.appendText(node.name + ": " + node.content + "\n");
        
        
        if (!node.attributes.isEmpty()){
            HashMap<String, String> attributes = node.attributes;
            for(Object entry : attributes.entrySet()){
                HashMap.Entry e = (HashMap.Entry) entry;
                Object key = e.getKey();
                Object value = e.getValue();

                textArea.appendText(key + ": " + value + "\n");
            }
        }
        if (!node.properties.isEmpty()){

            ArrayList<UseNode> list = node.properties.get("");
            int n = list.size();
            for (int i = 0; i < n; i++){
                showParsedXML(list.get(i));
            }
        }
    }
        
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exceptional Error Dialog");
        alert.setHeaderText("Exceptional Error");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
}
