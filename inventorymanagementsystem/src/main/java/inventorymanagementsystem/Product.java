package inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a product in the Inventory Management System
 * 
 * @author Nick Fuller
 */
public class Product 
{
    /**
     * An ObservableList of parts associated with this product.
     */
    public ObservableList<Part> associatedParts;
    
    /**
     * This product's ID.
     */
    public int id;
    
    /**
     * This product's name.
     */
    public String name;
    
    /**
     * This product's price.
     */
    public double price;
    
    /**
     * This product's inventory/stock.
     */
    public int stock;
    
    /**
     * The minimum stock of this product.
     */
    public int min;
    
    /**
     * The maximum stock of this product.
     */
    public int max;

    /**
     * Creates a product. This is the only available constructor for this object.
     * 
     * @param id id to set.
     * @param name name to set.
     * @param price price to set.
     * @param stock stock to set.
     * @param min min to set.
     * @param max max to set.
     */
    public Product(int id, String name, double price, int stock, 
            int min, int max) 
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.<Part>observableArrayList();
    }
    
    /**
     * @param id the ID to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * @param name the name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @param price the price to set.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    /**
     * @param stock the stock to set.
     */
    public void setStock(int stock)
    {
        this.stock = stock;
    }
    
    /**
     * @param min the min to set.
     */
    public void setMin(int min)
    {
        this.min = min;
    }
    
    /**
     * @param max the max to set.
     */
    public void setMax(int max)
    {
        this.max = max;
    }
    
    /**
     * @return the ID.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * @return the name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the price.
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * @return the stock.
     */
    public int getStock()
    {
        return stock;
    }
    
    /**
     * @return the min.
     */
    public int getMin()
    {
        return min;
    }
    
    /**
     * @return the max.
     */
    public int getMax()
    {
        return max;
    }
    
    /**
     * Adds the specified part to this product's associated parts list.
     * 
     * @param part the part to add to the associated parts list.
     */
    public void addAssociatedPart(Part part)
    {
        associatedParts.add(part);
    }
    
    /**
     * Deletes the specified part from the associated parts list.
     * RUNTIMEERROR - nullpointerexception was being thrown.
     * Added a null check to the method to prevent this error.
     * 
     * @param selectedAssociatedPart part to remove from associated parts list.
     * @return true if the associated part was deleted.
     * False if it was not deleted.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        if(selectedAssociatedPart == null)
        {
            return false;
        }
        
        for(int i = 0; i < associatedParts.size(); i++)
        {
            if(selectedAssociatedPart.getId() == associatedParts.get(i).getId())
            {
                associatedParts.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * @return an ObservableList of all of this product's associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
    }
    
}
