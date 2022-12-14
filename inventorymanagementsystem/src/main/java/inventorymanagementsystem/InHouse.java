package inventorymanagementsystem;

/**
 * In-House part. Includes a Machine ID.
 * 
 * @author Nick Fuller
 */
public class InHouse extends Part
{
    /**
     * This In House part's machine ID.
     */
    public int machineId;
    
    /**
     * Creates an In House part. This is the only available constructor for this object.
     * 
     * @param id id to set.
     * @param name name to set.
     * @param price price to set.
     * @param stock stock to set.
     * @param min min to set.
     * @param max max to set.
     * @param machineId machine ID to set.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) 
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /**
     * @param machineId the machine ID to set.
     */
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }
    
    /**
     * @return the machine ID.
     */
    public int getMachineId()
    {
        return this.machineId;
    }
}
