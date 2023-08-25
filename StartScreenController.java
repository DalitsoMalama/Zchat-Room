import javafx.fxml.FXML;

/**
 * This class serves as the controller for the start screen.
 * It implements ControlledScreen interface for interaction with the ScreensController.
 */
public class StartScreenController implements ControlledScreen {

    // Reference to the ScreensController and the Main class
    ScreensController myScreenController;
    Main myLogicalParent;

    // Implementation of the setParents method from the ControlledScreen interface
    @Override
    public void setParents(ScreensController screenPage, Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }

    /**
     * Handler for the "Initialize Server" button.
     * Initiates the process of setting up a server.
     */
    @FXML
    protected void initServer() {
        myLogicalParent.initServer();
    }

    /**
     * Handler for the "Initialize Client" button.
     * Initiates the process of setting up a client.
     */
    @FXML
    protected void initClient() {
        myLogicalParent.initClient();
    }
}
