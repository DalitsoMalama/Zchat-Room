// Importing the class for creating simple string properties in JavaFX
import javafx.beans.property.SimpleStringProperty;

// Definition of the RemoteDeviceInfo class
public class RemoteDeviceInfo {

    // Creating a simple string property for the device name
    public final SimpleStringProperty deviceName = new SimpleStringProperty("");

    // Creating a simple string property for the device address
    public final SimpleStringProperty deviceAddress = new SimpleStringProperty("");

    // Getter method for retrieving the device address
    public String getDeviceAddress() {
        return deviceAddress.get();
    }

    // Setter method for setting the device address
    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress.set(deviceAddress);
    }

    // Getter method for retrieving the device name
    public String getDeviceName() {
        return deviceName.get();
    }

    // Setter method for setting the device name
    public void setDeviceName(String deviceName) {
        this.deviceName.set(deviceName);
    }

    // Constructor for initializing the device name and address
    public RemoteDeviceInfo(String name, String address) {
        setDeviceName(name);
        setDeviceAddress(address);
    }

    // Default constructor with empty values for device name and address
    public RemoteDeviceInfo() {
        this("", "");
    }
}
