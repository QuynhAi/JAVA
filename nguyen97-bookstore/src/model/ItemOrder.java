/*
* Ai Quynh Nguyen
* TCSS 305 - Winter 2019
* Assignment 2 - Shopping Cart
*/

package model;

import java.util.Objects;

/**
 * This store information about a purchase for an item.
 *      namely, a reference to the item itself
 *      the quantity desired
 * Condition: the quantity is greater than 0
 * @author ainguyen
 * @version January 23
 *
 */
public final class ItemOrder {
    
    /**
     * Reference to the Item.
     */
    private final Item myItem;
    
    /**
     * The quantity for the Item.
     */
    private final int myQuantity;
    
    /**
     * Constructor that creates an item order for given quantity of given Item.
     * @param theItem the assigned Item
     * @param theQuantity   the assigned quantity of Item
     * @throw IllegalArgumentException if theQuantity if less than 0
     * @throws NullPointerException if the theItem is null 
     *                                  or if theQuantity is null
     * Conditions: theItem cannot be null 
     *              theQuantity cannot be null or less than 0
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        this.myItem = Objects.requireNonNull(theItem);
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        this.myQuantity = Objects.requireNonNull(theQuantity);
    }

    /**
     * Returns a reference to the Item that customer ordered.
     * @return a reference to the Item
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * Return the quantity of the Item that customer ordered.
     * @return the quantity for ItemOrder
     */
    public int getQuantity() {
        return myQuantity;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append("Item=");
        builder.append(myItem);
        builder.append(" Quantity=");
        builder.append(myQuantity);
        return builder.toString();
    }
}
