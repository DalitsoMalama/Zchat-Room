import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

/**
 * This class serves as the controller for the server list screen.
 * It implements ControlledScreen interface for interaction with the ScreensController.
 */
public class ServerListScreenController implements ControlledScreen {

    // Reference to the ScreensController and the Main class
    ScreensController myScreenController;
    Main myLogicalParent;

    // Implementation of the setParents method from the ControlledScreen interface
    @Override
    public void setParents(ScreensController screenPage, Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }

    @FXML
    private TableView serverTable; // TableView element for displaying server list

    /**
     * Set data in the TableView with the provided ObservableList.
     * @param data The list of RemoteDeviceInfo objects to be displayed.
     */
    public void setTableData(ObservableList<RemoteDeviceInfo> data) {
        serverTable.setItems(data);
    }

    /**
     * Handler for the "Connect" button.
     * Initiates a connection to the selected device using its index in the table.
     */
    @FXML
    protected void connectToDevice() {
        myLogicalParent.connectToDevice(serverTable.getSelectionModel().getSelectedIndex());
    }

    /**
     * Handler for the "Refresh" button.
     * Triggers a refresh of the displayed data.
     */
    @FXML
    protected void refresh() {
        myLogicalParent.refresh();
    }
}
