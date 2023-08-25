import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatScreenController implements ControlledScreen {

    // Reference to the ScreensController and Main class
    ScreensController myScreenController;
    Main myLogicalParent;

    // This method sets the parent screens and logical parent
    @Override
    public void setParents(ScreensController screenPage, Main logical) {
        myLogicalParent = logical;
        myScreenController = screenPage;
    }

    // Reference to the TextFlow and TextArea defined in the FXML
    @FXML
    private TextFlow chatLog;

    @FXML
    private TextArea chatText;

    // This method is triggered when the send button is clicked
    @FXML
    protected void sendMassage() {
        // Get the message from the chatText TextArea
        String msg = chatText.getText();

        // Create a Text element for the sender's name and style it
        Text blue = new Text(myLogicalParent.myName + " : ");
        blue.setStyle("-fx-fill: #0098d8");

        // Create a Text element for the message
        Text m = new Text(msg + "\n");

        // Add the sender's name and message Text elements to the TextFlow
        chatLog.getChildren().add(blue);
        chatLog.getChildren().add(m);

        // Clear the chatText TextArea
        chatText.setText("");

        // Send the message using the parent's sendMsg method
        myLogicalParent.sendMsg(msg);
    }

    // This method is called when a new message is received
    public void recivedMassage(String msg) {
        // Create a Text element for the partner's name and style it
        Text red = new Text(myLogicalParent.partnerName + " : ");
        red.setStyle("-fx-fill: orangered");

        // Create a Text element for the received message
        Text m = new Text(msg + "\n");

        // Add the partner's name and received message Text elements to the TextFlow
        chatLog.getChildren().add(red);
        chatLog.getChildren().add(m);
    }
    public void activeStatus(){

    }
    
    public void activeButton(){}
}
