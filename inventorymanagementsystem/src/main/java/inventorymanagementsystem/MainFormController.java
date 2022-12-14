package inventorymanagementsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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



/**
 * Main Form FXML Controller class
 *
 * @author Nick Fuller
 */
public class MainFormController implements Initializable {


    /**
     * The Main Form's products table.
     */
    @FXML
    public TableView<Product> productsTable;
    
    /**
     * The products table's product ID column.
     */
    @FXML
    public TableColumn<Product, Integer> productIdColumn;
    
    /**
     * The products table's product name column.
     */
    @FXML
    public TableColumn<Product, String> productNameColumn;
    
    /**
     * The products table's product inventory column.
     */
    @FXML
    public TableColumn<Product, Integer> productInventoryColumn;
    
    /**
     * The products table's product price column.
     */
    @FXML
    public TableColumn<Product, Double> productPriceColumn;
    
    /**
     * The Main Form's product search box.
     */
    @FXML
    public TextField searchProducts;
    
    /**
     * The Main Form's parts table.
     */
    @FXML
    public TableView<Part> partsTable;
    
    /**
     * The parts table's part ID column.
     */
    @FXML
    public TableColumn<Part, Integer> partIdColumn;
    
    /**
     * The parts table's part name column.
     */
    @FXML
    public TableColumn<Part, String> partNameColumn;
    
    /**
     * The parts table's part inventory column.
     */
    @FXML
    public TableColumn<Part, Integer> partInventoryColumn;
    
    /**
     * The parts table's part price column.
     */
    @FXML
    public TableColumn<Part, Double> partPriceColumn;
    
    /**
     * The Main Form's part search box.
     */
    @FXML
    public TextField searchParts;
    
    /**
     * The Main Form's error label for displaying errors.
     */
    @FXML
    public Label errorLabel;

    /**
     * Index used for table selection. Helps pass data to other controllers
     * from this controller's table views.
     */
    public static int selectedIndex;
  
    
    
    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   
        // Initialize Products table
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(Inventory.getAllProducts());
        
        // Initialize Parts table
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());
    }    
    
    /**
     * Closes the application.
     * 
     * @param event 
     */
    @FXML
    public void exitButtonAction(ActionEvent event) 
    {
        Platform.exit();
    }

    
    /**
     * Searches for and displays products in product table view.
     * 
     * @param event 
     */
    @FXML
    public void searchProductsAction(KeyEvent event) 
    {
        errorLabel.setVisible(false);
        
         if(searchProducts.getText().isBlank())
        {   
            productsTable.setItems(Inventory.getAllProducts());
            return;
        }
        else if(isNumber(searchProducts.getText()))
        {
            ObservableList<Product> showProducts = FXCollections.<Product>observableArrayList();
            showProducts.add(Inventory.lookupProduct(Integer.parseInt(searchProducts.getText())));
            productsTable.setItems(showProducts);
            productsTable.getItems().addAll(Inventory.lookupProduct(
                    searchProducts.getText()));
        }
        else
        {
            productsTable.setItems(Inventory.lookupProduct(
                    searchProducts.getText()));
        }
         
        if(productsTable.getItems().isEmpty())
        {
            displayError("No products found");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Deletes product selected in product table view.
     * 
     * @param event 
     */
    @FXML
    public void deleteProductButtonAction(ActionEvent event) 
    {
        errorLabel.setVisible(false);
        
        if(productsTable.getItems().isEmpty() || productsTable.getSelectionModel().isEmpty())
        {
            displayError("Please select a product to delete");
            errorLabel.setVisible(true);
            return;
        }
        
        if(!productsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty())
        {
            displayError("Cannot delete a product that has a part associated with it");
            errorLabel.setVisible(true);
            return;
        }
        
        if(Inventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem()))
        {
             productsTable.setItems(Inventory.getAllProducts());
        }
        else
        {
            displayError("Error - product not deleted");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Opens up selected product in product table view for modifying.
     * 
     * @param event 
     */
    @FXML
    public void modifyProductButtonAction(ActionEvent event)
    {
        if(productsTable.selectionModelProperty().isBound() || productsTable.getItems().isEmpty())
        {
            displayError("Please select a product to modify");
            errorLabel.setVisible(true);
            return;
        }
        selectedIndex = productsTable.getSelectionModel().getFocusedIndex();
        try
        {
            App.changeScene("ModifyProductForm");
        }
        catch(Exception e)
        {
            displayError("!!!Critical Error!!! Please close program");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Opens Add Product Form to add a product to this table view.
     * 
     * @param event 
     */
    @FXML
    public void addProductButtonAction(ActionEvent event)
    {
        try
        {
            App.changeScene("AddProductForm");
        }
        catch(Exception e)
        {
            displayError("!!!Critical Error!!! Please close program");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Searches for and displays parts in part table view.
     * 
     * @param event 
     */
    @FXML
    public void searchPartsAction(KeyEvent event) 
    {
        errorLabel.setVisible(false);
        
        if(searchParts.getText().isBlank())
        {   
            partsTable.setItems(Inventory.getAllParts());
            return;
        }
        else if(isNumber(searchParts.getText()))
        {
            ObservableList<Part> showParts = FXCollections.<Part>observableArrayList();
            showParts.add(Inventory.lookupPart(Integer.parseInt(searchParts.getText())));
            partsTable.setItems(showParts);
            partsTable.getItems().addAll(Inventory.lookupPart(
                    searchParts.getText()));
        }
        else
        {
            partsTable.setItems(Inventory.lookupPart(
                    searchParts.getText()));
        }
        
        if(partsTable.getItems().isEmpty())
        {
            displayError("No parts found");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Deletes part selected in part table view.
     * 
     * @param event 
     */
    @FXML
    public void deletePartButtonAction(ActionEvent event) 
    {
        errorLabel.setVisible(false);
        
        if(partsTable.getItems().isEmpty() || partsTable.getSelectionModel().isEmpty())
        {
            displayError("Please select a part to delete");
            errorLabel.setVisible(true);
            return;
        }
        
         if(Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem()))
        {
            partsTable.setItems(Inventory.getAllParts());
        }
        else
        {
            displayError("Error - part not deleted");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Opens up selected part in part table view for modifying.
     * 
     * @param event 
     */
    @FXML
    public void modifyPartButtonAction(ActionEvent event)
    {
        if(partsTable.selectionModelProperty().isBound() || partsTable.getItems().isEmpty())
        {
            displayError("Please select a part to modify");
            errorLabel.setVisible(true);
            return;
        }
        selectedIndex = partsTable.getSelectionModel().getFocusedIndex();
        try
        {
            App.changeScene("ModifyPartForm");
        }
        catch(Exception e)
        {
            displayError("!!!Critical Error!!! Please close program");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Opens Add Part Form to add a part to this table view.
     * 
     * @param event 
     */
    @FXML
    public void addPartButtonAction(ActionEvent event)
    {
        try
        {
            App.changeScene("AddPartForm");
        }
        catch(Exception e)
        {
            displayError("!!!Critical Error!!! Please close program");
            errorLabel.setVisible(true);
        }
    }
    
   
    
    /**
     * Checks whether passed in string is a number(integer or double).
     * 
     * @param check string to check if is number.
     * @return true if string is a number. false if string is not a number.
     */
    public static boolean isNumber(String check)
    {
        boolean toReturn = false;
        
        if(check == null)
        {
            return toReturn;
        }
        
        boolean containsDecimalPoint = false;
        toReturn = true;
        
        for(int i = 0; i < check.length(); i++)
        {
            if(check.charAt(i) == '.')
            {
                if(containsDecimalPoint == true)
                {
                    return false;
                }
                containsDecimalPoint = true;
                continue;
            }
            
            if(!Character.isDigit(check.charAt(i)))
            {
                toReturn = false;
                break;
            }
        }
            
        return toReturn;
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
