package inventorymanagementsystem;

/**
 * Outsourced part. Includes a company name. 
 * 
 * @author Nick Fuller
 */
public class Outsourced extends Part
{
    /**
     * This Outsourced part's company name.
     */
    public String companyName;

    /**
     * Creates an Outsourced part. Only available constructor for this object.
     *
     * @param id id to set.
     * @param name name to set.
     * @param price price to set.
     * @param stock stock to set.
     * @param min min to set.
     * @param max max to set.
     * @param companyName company name to set.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) 
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /**
     * @param companyName the company name to set.
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    /**
     * @return the company name.
     */
    public String getCompanyName()
    {
        return this.companyName;
    }
}
