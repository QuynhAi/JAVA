/*
* Ai Quynh Nguyen
* TCSS 305 - Winter 2019
* Assignment 2 - Shopping Cart
*/

package model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents an Item.
 *  myName cannot be empty or null
 *  myPrice must be greater than 0
 *  myBulkQuantity must be greater than 0
 *  myBulkPrice must be greater than 0
 * @author ainguyen
 * @version January 21, 2019
 *
 */
public final class Item {
    
    /**
     * The name of the Item.
     */
    private String myName;
    
    /**
     * The price of the Item.
     */
    private BigDecimal myPrice;
    
    /**
     * The bulk quantity of the Item.
     */
    private int myBulkQuantity;
    
    /**
     * The bulk price of the Item.
     */
    private BigDecimal myBulkPrice;
    
    /**
     * The bulk option availability for the Item.
     */
    private final boolean myBulkOptionAvailable;
    
  /**
    * Constructor a Item with specific name and price.
    * Preconditions: 
    *               theName cannot be empty and not null
    *               thePrice cannot be null and greater than 0
    * @param theName the assigned name for the item
    * @param thePrice the assigned price for the item
    * @throws NullPointerException if name is null or price is null
    * @throws IllegalArgumentException if price is less than 0
    */
    public Item(final String theName, final BigDecimal thePrice) {
        constructorHelper(theName, thePrice);
        this.myBulkOptionAvailable = false;
    }
    
    /**
     * Constructor that takes name, single-item, a bulk quantity and bulk price.
     * preconditions:
     *              theName cannot be empty or null
     *              thePrice cannot be null or less than 0
     *              theBulkQuantity cannot be null or less than 0
     *              theBulkPrice cannot be null or less than   
     * @param theName the assigned name for this Item
     * @param thePrice the assigned price for this Item
     * @param theBulkQuantity the assigned bulk quantity for this ITem
     * @param theBulkPrice the assigned bulk price for this Item
     */
    public Item(final String theName, final BigDecimal thePrice, 
                final int theBulkQuantity, final BigDecimal theBulkPrice) {
        
        constructorHelper(theName, thePrice);
        
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException();
        }
        this.myBulkQuantity = theBulkQuantity;
        
        this.myBulkPrice = Objects.requireNonNull(theBulkPrice);
        if (myBulkPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        this.myBulkOptionAvailable = true;
    }
    
    /**
     * Check the conditions for name and price of the item.
     * Conditions:  theName cannot be empty and not null
     *              thePrice cannot be null and greater than 0
     * @param theName the assigned name for the item
     * @param thePrice the assigned price for the item
     * @throws NullPointerException if name is null or price is null
     * @throws IllegalArgumentException if price is less than 0
     */
    private void constructorHelper(final String theName, final BigDecimal thePrice) {
        this.myName = Objects.requireNonNull(theName);
        this.myPrice = Objects.requireNonNull(thePrice);
        if (myPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Returns the price of the Item.
     * 
     * @return the price of this Item
     */
    public BigDecimal getPrice() {
        return myPrice;
    }
    
    /**
     * Returns the bulk quantity for the Item.
     * @return the bulk quantity for this Item.
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }
    
    /**
     * Return the bulk price for the item.
     * @return return the bulk price this Item
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }
    
    /**
     * return whether if the Item has bulk pricing.
     * @return True if Item has bulk pricing
     * else, return false
     */
    public boolean isBulk() {
        return myBulkOptionAvailable;
    }

    /**
     * the String representation of this Circle will be formatted.
     * if bulk option is not available:
     *      Name of item, $ price of Item
     * If bulk option is available:
     *      Name of Item, $ price of Item (bulk quantity for bulk price)
     */
    @Override
    public String toString() { // X, $19.99 (5 for $89.99)
        final StringBuilder builder = new StringBuilder();
        builder.append(myName);
        builder.append(", $");
        builder.append(myPrice);
        if (myBulkOptionAvailable) {
            builder.append(" (");
            builder.append(myBulkQuantity);
            builder.append(" for $");
            builder.append(myBulkPrice);
            builder.append(')');
        }
        return builder.toString();
    }

    /**
     * This method compares the name, the prices, the bulk quantities and bulk price
     * to determine the equality of the Item ojects. All four fields must be equal for
     * two Items object to be consider equal.
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean returnValue = false;
        if (theOther == null || this.getClass() != theOther.getClass()) {
            returnValue = false;
        } else {
            final Item otherItem = (Item) theOther;
            // always look at all  4
            returnValue = Objects.equals(myPrice, otherItem.myPrice)
                            && Objects.equals(myName, otherItem.myName)
                            && (myBulkQuantity == otherItem.myBulkQuantity)
                            && Objects.equals(myBulkPrice, otherItem.myBulkPrice);
        }
        return returnValue;
    }
     
    @Override
    public int hashCode() {
        final int result;
        if (myBulkOptionAvailable) {
            result = Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
        } else {
            result = Objects.hash(myName, myPrice);
        }
        return result;
    }
}