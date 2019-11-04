/*
* Ai Quynh Nguyen
* TCSS 305 - Winter 2019
* Assignment 2 - Shopping Cart
*/
package model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Store information about customer's overall purchase.
 * @author ainguyen
 * @version January 24
 */
public class Cart {
    
    /**
     * Check if customer has membership.
     */
    private boolean myMembership;
    
    /**
     * List to store all items that customer order.
     */
    private List<ItemOrder> myOrderCart;
    
    /**
     * Constructor to initial the Cart.
     */
    public Cart() {
        this.myOrderCart = new ArrayList<ItemOrder>();   
    }

    /**
     * This method add the ordered item into the shopping cart.
     * If customer changed their mind about the quantity of the order
     * Replace the old order with the new order
     * @param theOrder the assigned item that customer ordered
     */
    public void add(final ItemOrder theOrder) {
        for (int i = 0; i < myOrderCart.size(); i++) {
            final ItemOrder temp = myOrderCart.get(i);
            if ((temp.getItem()).equals(theOrder.getItem())) {
                myOrderCart.remove(i);
            }
        }
        myOrderCart.add(theOrder);
        //System.out.println(myOrderCart);
    }
    
    /**
     * Return whether the customer has a membership.
     * @param theMembership an assigned true or false of membership
     */
    public void setMembership(final boolean theMembership) {
        this.myMembership = theMembership;     
    } 

    /**
     * This method calculate the total cost of the shopping cart.
     * @return total cost of this shopping cart as BigDecimal
     * Used Scale set of 2 and round half even rule
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        final Iterator<ItemOrder> itr = myOrderCart.iterator();
        
        while (itr.hasNext()) {
            final ItemOrder curOrder = itr.next();
            final BigDecimal currentQuantity = new BigDecimal(curOrder.getQuantity());
            final Item curItem = curOrder.getItem();
            if (myMembership && curItem.isBulk()) {
                total = isBulkCal(curOrder, curItem, total);
            } else {
                total = total.add(currentQuantity.multiply(curItem.getPrice()));
            }
        }
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * This method calculate the total cost of the shopping cart.
     * When the customer has a membership(meaning customer allows to have special
     * price when buying item in bulk)
     * @param theCurOrder   the current ItemOrder
     * @param theCurItem    the current Item
     * @param theResult     the total 
     * @return the total cost with membership
     */
    private BigDecimal isBulkCal(final ItemOrder theCurOrder, final Item theCurItem, 
                                 final BigDecimal theResult) {
        BigDecimal curResult = theResult;
        final int bulkRemainder = theCurOrder.getQuantity() % theCurItem.getBulkQuantity();
        if ((theCurOrder.getQuantity() - bulkRemainder)
                        % theCurItem.getBulkQuantity() == 0) {
            final int bulkQuantity = (theCurOrder.getQuantity() - bulkRemainder)
                                        / theCurItem.getBulkQuantity();
            final BigDecimal newBulkQuantity = new BigDecimal(bulkQuantity);
            curResult = curResult.add(newBulkQuantity.multiply(theCurItem.getBulkPrice()));   
        }
        final BigDecimal newBulkRemainder = new BigDecimal(bulkRemainder);
        curResult = curResult.add(newBulkRemainder.multiply(theCurItem.getPrice()));
        return curResult;
    }

    /**
     * This method clear everything in the cart.
     */
    public void clear() {
        myOrderCart = new ArrayList<ItemOrder>(); 
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append("Shopping Cart=");
        builder.append(myOrderCart);
        return builder.toString();
    }
}
