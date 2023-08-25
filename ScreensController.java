import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.util.HashMap;

public class ScreensController extends StackPane {

    // HashMaps to store loaded screens and their respective controllers
    private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, Object> controllers = new HashMap<>();

    Main logicalParent; // Reference to the Main class

    public ScreensController(Main logical) {
        logicalParent = logical;
    }

    // Add a screen to the HashMap
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    // Load a screen from a FXML resource
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            controllers.put(name, myLoader.getController());
            myScreenController.setParents(this, logicalParent);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Unload a screen from the HashMap
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    // Set the active screen based on the given name
    public boolean setScreen(final String name) {
        if (screens.get(name) != null) { // Check if the screen is loaded
            final DoubleProperty opacity = opacityProperty();

            // If there's already a displayed screen, fade it out and replace with the new screen
            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(1000),
                        (ActionEvent e) -> {
                            // Remove displayed screen
                            getChildren().remove(0);
                            // Add new screen
                            getChildren().add(0, screens.get(name));
                            Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        },
                        new KeyValue(opacity, 0.0)
                    )
                );
                fade.play();
            } else {
                // If no screen was displayed before, simply show the new screen
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("Screen hasn't been loaded!\n");
            return false;
        }
    }

    // Get the controller of a specific screen
    public Object getScreenController(final String name) {
        return controllers.get(name);
    }
}
