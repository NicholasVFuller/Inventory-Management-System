package inventorymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Inventory Management System JavaFX App
 * 
 * FUTURE ENHANCEMENT - This application could be better optimized by utilizing
 * hash maps (or perhaps other data structures) for faster/more efficient manipulation and searching.
 */
public class App extends Application {

    /**
     * This application's stage.
     */
    public static Stage stage;
    
    /**
     * The stages scene.
     */
    public static Scene scene;

    /**
     * Initializes the applications stage and scene and shows them.
     *
     * @param setStage the stage to be set as this application's stage.
     * @throws IOException
     */
    @Override
    public void start(Stage setStage) throws IOException {
        stage = setStage;
        scene = new Scene(loadFXML("MainForm"));
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**
     * Changes scenes by setting a new scene to the application's stage.
     * 
     * @param fxml the name of the fxml document to be loaded into the scene.
     * @throws IOException 
     */
    public static void changeScene(String fxml) throws IOException {
        scene = new Scene(loadFXML(fxml));
        stage.setScene(scene);
    }

    /**
     * Loads FXML file for scene.
     * 
     * @param fxml the name of the fxml document to be loaded into the scene.
     * @throws IOException 
     * @return parent node for scene.
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * This applications main method.
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

}