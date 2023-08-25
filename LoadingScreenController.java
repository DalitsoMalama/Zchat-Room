import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

// This class implements the ControlledScreen interface
public class LoadingScreenController implements ControlledScreen {

    // Reference to the ScreensController and Main class
    ScreensController myScreenController;
    Main myLogicalParent;

    // This method sets the parent screens and logical parent
    @Override
    public void setParents(ScreensController screenPage, Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }

    // Reference to the Label defined in the FXML
    @FXML
    private Label loadLbl;

    // This method updates the text of the Label
    public void setText(String str) {
        loadLbl.setText(str);
    }
}
