package inventorymanagementsystem;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Model/data representation of the Inventory Management System.
 * 
 * @author Nick Fuller
 */
public class Inventory 
{
    /**
     * Contains all parts in the Inventory Management System.
     */
    public static ObservableList<Part> allParts = 
            FXCollections.<Part>observableArrayList();
    
    /**
     * Contains all products in the Inventory Management System.
     */
    public static ObservableList<Product> allProducts = 
            FXCollections.<Product>observableArrayList();
    
    /**
     * @param newPart the part to add to the list of all parts.
     */
    public static void addPart(Part newPart)
    {
        if(newPart == null)
        {
            return;
        }
        
        allParts.add(newPart);
    }
    
    /**
     * @param newProduct the product to add to the list of all products.
     */
    public static void addProduct(Product newProduct)
    {
        if(newProduct == null)
        {
            return;
        }
        
        allProducts.add(newProduct);
    }
    
    /**
     * Searches for and returns specified part based on part ID.
     * 
     * @param partId the part ID of the part to search for.
     * @return the looked up part. null if the specified part is not found.
     */
    public static Part lookupPart(int partId)
    {   
        for(Part nextPart : allParts)
        {
           if(nextPart.getId() == partId)
           {
               return nextPart;
           }
        }
        
        return null;
    }
    
    /**
     * Searches for and returns specified product based on product ID.
     * 
     * @param productId the product ID of the product to search for.
     * @return the looked up product. null if the specified product is not found.
     */
    public static Product lookupProduct(int productId)
    {      
        for(Product nextProduct : allProducts)
        {
           if(nextProduct.getId() == productId)
           {
               return nextProduct;
           }
        }
        
        return null;
    }
    
    /**
     * Searches for and returns a list of parts containing the specified part name as part of its name.
     * 
     * @param partName the name or partial name of the part to search for.
     * @return a list of parts that contain the specified part name as part of their name.
     */
    public static ObservableList<Part> lookupPart(String partName)
    {
        
        ObservableList<Part> toReturn = FXCollections.<Part>observableArrayList();
        
        if(partName == null)
        {
            return toReturn;
        }
        
        for(Part nextPart : allParts)
        {
            if(nextPart.getName().contains(partName))
            {
                toReturn.add(nextPart);
            }
        }
        
        return toReturn;
    }
    
    /**
     * Searches for and returns a list of products containing the specified product name as part of its name.
     * 
     * @param productName the name or partial name of the product to search for.
     * @return a list of products that contain the specified product name as part of their name.
     */
    public static ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> toReturn = FXCollections.<Product>observableArrayList();
        
        if(productName == null)
        {
            return toReturn;
        }
        
        for(Product nextProduct : allProducts)
        {
            if(nextProduct.getName().contains(productName))
            {
                toReturn.add(nextProduct);
            }
        }
        
        return toReturn;
    }
    
    /**
     * Replaces the part at the specified index with the new specified part.
     * 
     * @param index index of part to replace.
     * @param selectedPart part that replaces part at specified index.
     */
    public static void updatePart(int index, Part selectedPart)
    {  
        if(index >= allParts.size())
        {
            return;
        }
        
        allParts.set(index, selectedPart);
    }
    
    /**
     * Replaces the product at the specified index with the new specified product.
     * 
     * @param index index of product to replace.
     * @param newProduct product that replaces product at specified index.
     */
    public static void updateProduct(int index, Product newProduct)
    {   
        if(index >= allProducts.size())
        {
            return;
        }
        
        allProducts.set(index, newProduct);
    }
    
    /**
     * Deletes specified part.
     * 
     * @param selectedPart part to be deleted.
     * @return true if part deleted. false if part not deleted.
     */
    public static boolean deletePart(Part selectedPart)
    {  
        for(int i = 0; i < allParts.size(); i++)
        {
            if(selectedPart.getId() == allParts.get(i).getId())
            {
                allParts.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Deletes specified product.
     *
     * @param selectedProduct product to be deleted.
     * @return true if part deleted. false if part not deleted.
     */
    public static boolean deleteProduct(Product selectedProduct)
    {   
        for(int i = 0; i < allProducts.size(); i++)
        {
            if(selectedProduct.getId() == allProducts.get(i).getId())
            {
                allProducts.remove(i);
                return true;
            }
        }
        
        return false;
        
    }
    
    /**
     * @return all parts in the Inventory Management System.
     */
    public static ObservableList<Part> getAllParts()
    { 
        return allParts;
    }
    
    /**
     * @return all parts in the Inventory Management System.
     */
    public static ObservableList<Product> getAllProducts()
    { 
        return allProducts;
    }
    
}
