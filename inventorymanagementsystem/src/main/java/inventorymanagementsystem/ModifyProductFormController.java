package inventorymanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import static inventorymanagementsystem.MainFormController.isNumber;



/**
 * Modify Product FXML Controller class
 *
 * @author Nick Fuller
 */
public class ModifyProductFormController implements Initializable {

    /**
     * Product ID text box.
     */
    @FXML
    public TextField idTextField;

    /**
     * Product max text box.
     */
    @FXML
    public TextField maxTextField;

    /**
     * Product price text box.
     */
    @FXML
    public TextField priceTextField;

    /**
     * Product inventory text box.
     */
    @FXML
    public TextField invTextField;

    /**
     * Product name text box.
     */
    @FXML
    public TextField nameTextField;

    /**
     * Product min text box.
     */
    @FXML
    public TextField minTextField;

    /**
     * Search bar for searching all parts table.
     */
    @FXML
    public TextField searchParts;

    /**
     * Table populated with all parts data.
     */
    @FXML
    public TableView<Part> allPartsTable;

    /**
     * allPartsTable part ID column.
     */
    @FXML
    public TableColumn<Part, Integer> partIdColumn;

    /**
     * allPartsTable part name column.
     */
    @FXML
    public TableColumn<Part, String> partNameColumn;

    /**
     * allPartsTable part inventory column.
     */
    @FXML
    public TableColumn<Part, Integer> partInventoryColumn;

    /** 
     * allPartsTable part price column.
     */
    @FXML
    public TableColumn<Part, Double> partPriceColumn;

    /**
     * Table populated with associated parts data.
     */
    @FXML
    public TableView<Part> associatedPartsTable;

    /**
     * associatedPartsTable part ID column.
     */
    @FXML
    public TableColumn<Part, Integer> associatedPartIdColumn;

    /**
     * associatedPartsTable part name column.
     */
    @FXML
    public TableColumn<Part, String> associatedPartNameColumn;

    /**
     * associatedPartsTable part inventory column.
     */
    @FXML
    public TableColumn<Part, Integer> associatedPartInventoryColumn;

    /**
     * associatedPartsTable part price column.
     */
    @FXML
    public TableColumn<Part, Double> associatedPartPriceColumn;

    /**
     * The Add Product Form's error label for displaying errors.
     */
    @FXML
    public Label errorLabel;
    
    /**
     * The product to be added.
     */
    public Product product;
    
    
    
    /**
     * Initializes the controller class and product.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Product getProduct = Inventory.getAllProducts().get(MainFormController.selectedIndex);
        
        product = new Product(getProduct.getId(), getProduct.getName(),
                getProduct.getPrice(), getProduct.getStock(), getProduct.getMin(), 
                getProduct.getMax());
        
        for(Part part : getProduct.getAllAssociatedParts())
        {
            product.addAssociatedPart(part);
        }
        
        
        idTextField.setText(Integer.toString(product.getId()));
        nameTextField.setText(product.getName());
        invTextField.setText(Integer.toString(product.getStock()));
        priceTextField.setText(Double.toString(product.getPrice()));
        maxTextField.setText(Integer.toString(product.getMax()));
        minTextField.setText(Integer.toString(product.getMin()));
        

        // Initialize Parts table
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTable.setItems(Inventory.getAllParts());
        
        //Initialize Associated Part table
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        associatedPartsTable.setItems(product.getAllAssociatedParts());
    }    
    
    /**
     * Returns to Main Form and disposes of data. Does not save product or data.
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
     * Saves product and returns to Main Form if all information is entered correctly.
     * 
     * @param event 
     */
    @FXML
    public void saveButtonAction(ActionEvent event)
    {
        if(!nameTextField.getText().isBlank())
        {
            product.setName(nameTextField.getText());
        }
        else
        {
            displayError("A product requires a name");
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
            product.setStock(Integer.parseInt(invTextField.getText()));
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
            product.setPrice(Double.parseDouble(priceTextField.getText()));
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
            product.setMax(Integer.parseInt(maxTextField.getText()));
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
            product.setMin(Integer.parseInt(minTextField.getText()));
        }
        else
        {
            displayError("Min must be a whole number");
            errorLabel.setVisible(true);
            return;
        }
        
        if(product.getMin() > product.getMax())
        {
            displayError("Min cannot be greater than Max");
            errorLabel.setVisible(true);
            return;
        }
        
        if(!(product.getStock() <= product.getMax() && 
                product.getStock() >= product.getMin()))
        {
            displayError("Inv must be in the range Min through Max inclusive");
            errorLabel.setVisible(true);
            return;
        }
            
        Inventory.updateProduct(MainFormController.selectedIndex, product);
        
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
     * Searches for and displays parts in all parts table view.
     * 
     * @param event 
     */
    @FXML
    public void searchPartsAction(KeyEvent event) 
    {
        errorLabel.setVisible(false);
        
        if(searchParts.getText().isBlank())
        {   
            allPartsTable.setItems(Inventory.getAllParts());
        }
        else if(isNumber(searchParts.getText()))
        {
            //Try catch?? Maybe???
            ObservableList<Part> showParts = FXCollections.<Part>observableArrayList();
            showParts.add(Inventory.lookupPart(Integer.parseInt(searchParts.getText())));
            allPartsTable.setItems(showParts);
            allPartsTable.getItems().addAll(Inventory.lookupPart(
                    searchParts.getText()));
        }
        else
        {
            allPartsTable.setItems(Inventory.lookupPart(
                    searchParts.getText()));
        }
    }

    /**
     * Removes selected associated part from the associated parts table view.
     * RUNTIME ERROR - was throwing a null pointer exception when no part to
     * remove was selected. Added code specified in method to fix.
     * 
     * @param event 
     */
    @FXML
    public void removeAssociatedPartButtonAction(ActionEvent event) 
    {
        errorLabel.setVisible(false);
        
        //added to fix runtime error.
        if(associatedPartsTable.getSelectionModel().isEmpty())
        {
            displayError("Please select an associated part to remove");
            errorLabel.setVisible(true);
            return;
        }
        
        if(!product.deleteAssociatedPart(associatedPartsTable.
                getSelectionModel().getSelectedItem()))
        {
            displayError("Error - associated part not removed");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Adds selected part from all parts table to associated parts table.
     * 
     * @param event 
     */
    @FXML
    public void addButtonAction(ActionEvent event) 
    {
        errorLabel.setVisible(false);
        
        if(allPartsTable.getSelectionModel().isEmpty())
        {
            displayError("Plese select a part to associate with product");
            errorLabel.setVisible(true);
            return;
        }
        
        product.addAssociatedPart(allPartsTable.
                getSelectionModel().getSelectedItem());
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
