package inventorymanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;



/**
 * Add Part FXML Controller class
 *
 * @author Nick Fuller
 */
public class AddPartFormController implements Initializable {

    /**
     * Radio button for selecting In-House Part.
     */
    @FXML
    public RadioButton inHouseRadio;
    
    /**
     * In-House Part machine ID label display in scene.
     */
    @FXML
    public Label machineLabel;
    
    /**
     * Part name text box.
     */
    @FXML
    public TextField nameTextField;
    
    /**
     * Part inventory text box.
     */
    @FXML
    public TextField invTextField;
    
    /**
     * Part price text box.
     */
    @FXML
    public TextField priceTextField;
    
    /**
     * Part max text box.
     */
    @FXML
    public TextField maxTextField;
    
    /**
     * In-House Part machine ID text box.
     */
    @FXML
    public TextField machineTextField;
    
    /**
     * Outsourced Part company name text box.
     */
    @FXML
    public TextField companyTextField;
    
    /**
     * Outsourced Part company name label display in scene.
     */
    @FXML
    public Label companyLabel;
    
    /**
     * Part min text box.
     */
    @FXML
    public TextField minTextField;
    
    /**
     * The Add Part Form's error label for displaying errors.
     */
    @FXML
    public Label errorLabel;
    
    
    /**
     * Integer value for incrementing and assigning unique part IDs.
     */
    public static int autoGen;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        errorLabel.setVisible(false);
    }    
    
    /**
     * Changes form to display all necessary In-House Part data collection elements.
     * 
     * @param event 
     */
    @FXML
    public void inHouseRadioAction(ActionEvent event) 
    {
        companyLabel.setVisible(false);
        companyTextField.setVisible(false);
        companyTextField.clear();
        
        machineLabel.setVisible(true);
        machineTextField.setVisible(true);
        machineTextField.clear();
    }

    /**
     * Changes form to display all necessary Outsourced Part data collection elements.
     * 
     * @param event 
     */
    @FXML
    public void outsourcedRadioAction(ActionEvent event) 
    {
        machineLabel.setVisible(false);
        machineTextField.setVisible(false);
        machineTextField.clear();
        
        companyLabel.setVisible(true);
        companyTextField.setVisible(true);
        companyTextField.clear();
        
    }

    /**
     * Saves part and returns to Main Form if all information is entered correctly.
     * 
     * @param event 
     */
    @FXML
    public void saveButtonAction(ActionEvent event)
    {
        String name = "";
        int inv;
        double price;
        int max;
        int min;
        int machineId;
        String companyName = "";
        
        if(!nameTextField.getText().isBlank())
        {
            name = nameTextField.getText();
        }
        else
        {
            displayError("A part requires a name");
            errorLabel.setVisible(true);
            return;
        }
        
        if(invTextField.getText().isBlank())
        {
            displayError("Inv cannot be empty");
            errorLabel.setVisible(true);
            return;
        }
        
        if(MainFormController.isNumber(invTextField.getText()))
        {
            inv = Integer.parseInt(invTextField.getText());
        }
        else
        {
            displayError("Inv must be a whole number");
            errorLabel.setVisible(true);
            return;
        }
        
        if(priceTextField.getText().isBlank())
        {
            displayError("Price cannot be empty");
            errorLabel.setVisible(true);
            return;
        }
        
        if(MainFormController.isNumber(priceTextField.getText()))
        {
            price = Double.parseDouble(priceTextField.getText());
        }
        else
        {
            displayError("Price must be a dollar and cents amount");
            errorLabel.setVisible(true);
            return;
        }
        
        if(maxTextField.getText().isBlank())
        {
            displayError("Max cannot be empty");
            errorLabel.setVisible(true);
            return;
        }
        
        if(MainFormController.isNumber(maxTextField.getText()))
        {
            max = Integer.parseInt(maxTextField.getText());
        }
        else
        {
            displayError("Max must be a whole number");
            errorLabel.setVisible(true);
            return;
        }
        
        if(minTextField.getText().isBlank())
        {
            displayError("Min cannot be empty");
            errorLabel.setVisible(true);
            return;
        }
        
        if(MainFormController.isNumber(minTextField.getText()))
        {
            min = Integer.parseInt(minTextField.getText());
        }
        else
        {
            displayError("Min must be a whole number");
            errorLabel.setVisible(true);
            return;
        }
        
        if(min > max)
        {
            displayError("Min cannot be greater than Max");
            errorLabel.setVisible(true);
            return;
        }
        
        if(!(inv <= max && inv >= min))
        {
            displayError("Inv must be in the range Min through Max inclusive");
            errorLabel.setVisible(true);
            return;
        }
        
        if(inHouseRadio.isSelected())
        {
            if(machineTextField.getText().isBlank())
            {
            displayError("Machine ID cannot be empty");
            errorLabel.setVisible(true);
            return;
            }
            
            if(MainFormController.isNumber(machineTextField.getText()))
            {
                machineId = Integer.parseInt(machineTextField.getText());
            }
            else
            {
                displayError("Machine ID must be a whole number");
                errorLabel.setVisible(true);
                return;
            } 
            
            //Assigns the automatically generated number, then increments
            //the generator.
            int id = autoGen++;
            
            InHouse newInHouse = new InHouse(id, name, price, inv, min, max, machineId);
            
            Inventory.addPart(newInHouse);
        }
        else
        {
            if(!companyTextField.getText().isBlank())
            {
                companyName = companyTextField.getText();
            }
            else
            {
            displayError("Company Name cannot be empty");
            errorLabel.setVisible(true);
            return;
            }
            
            //Assigns the automatically generated number, then increments
            //the generator.
            int id = autoGen++;
            
            Outsourced newOutsourced = new Outsourced(id, name, price, inv, min, max, companyName);
            
            Inventory.addPart(newOutsourced);
        }
        
        try
        {
            App.changeScene("MainForm");
        }
        catch(Exception e)
        {
            displayError("!!!Critical Error!!! Please close program");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Returns to Main Form and disposes of data. Does not save part or data.
     * 
     * @param event 
     */
    @FXML
    public void cancelButtonAction(ActionEvent event)
    {
        try
        {
            App.changeScene("MainForm");
        }
         catch(Exception e)
        {
            displayError("!!!Critical Error!!! Please close program");
            errorLabel.setVisible(true);
        }
    }

    
    /**
     * Displays error text in the scene.
     * 
     * @param errorText error text to be displayed.
     */
    public void displayError(String errorText)
    {
        errorLabel.setText(errorText);
    }
}
