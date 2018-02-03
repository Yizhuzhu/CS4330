/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Yi Zhu
 */
public class CheckerboardUIController implements Initializable {
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private StackPane stackPane;
    private Stage stage;
    private Scene scene;
    private int numRows;
    private int numCols; 
    private Color darkColor;
    private Color lightColor;
    private Checkerboard checkerBoard;
    private double boardWidth;
    private double boardHeight;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDefaultColor();
        setDefaultRowsCols();
    }  
    
    private void renderBoard(){
        boardWidth = scene.getWidth();
        boardHeight = scene.getHeight()- menuBar.getHeight();
        stackPane.getChildren().clear();
        checkerBoard = new Checkerboard(numRows, numCols, boardWidth, boardHeight, lightColor, darkColor);
        stackPane.getChildren().add(checkerBoard.build());
    }
    
    public void ready(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
        ChangeListener<Number> listener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            renderBoard();
        };
        scene.widthProperty().addListener(listener);
        scene.heightProperty().addListener(listener);
        renderBoard();
    }
    
    
   @FXML void Selectsize(ActionEvent event){
       MenuItem menuItem = (MenuItem)(event.getSource());
        switch(menuItem.getId()) {
            case "16 x 16":
                numRows = 16;
                numCols = 16;
                break;
                
            case "10 x 10":
                numRows = 10;
                numCols = 10;
                break;
                
            case "8 x 8":
                numRows = 8;
                numCols = 8;
                break;
                
            case "3 x 3":
                numRows = 3;
                numCols = 3;
                break;
                
            default:
                setDefaultRowsCols();
        }
        renderBoard();
   }
    
    @FXML
    public void selectColor(ActionEvent event){
        MenuItem menuItem = (MenuItem)(event.getSource());
        switch(menuItem.getId()){
            case "Blue":
                setBlue();
                break;
            default:
                setDefaultColor();
        }
        renderBoard();
    }
    
    
     private void setDefaultColor()
     {
        darkColor = Color.BLACK;
        lightColor = Color.RED;
    }
    
     
     private void setDefaultRowsCols() 
     {
        numRows = 8;
        numCols = 8;
    }
     
    private void setBlue() 
    {
        darkColor = Color.DARKBLUE;
        lightColor = Color.SKYBLUE;
    }
    
}
