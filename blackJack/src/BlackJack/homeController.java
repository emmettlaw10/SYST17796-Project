
/**
 * the controller for the home screen of the application
 *
 * @author Emmett Law Aug 2021
 */
package BlackJack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class homeController {
    @FXML
    Pane pane;


    public Stage stage;
    public Scene scene;
    private Parent root;


    /**
     * switches from home screen to the main game screen when start is pressed
     * @param event
     * @throws IOException
     */
    public void switchScenes(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}


