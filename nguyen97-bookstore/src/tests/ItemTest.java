// Ai Quynh Nguyen
// TCSS 305, Winter 2019
// Assignment 2 - Shopping Cart

package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Cart;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ainguyen
 * @version January 23, 2019
 *
 */
public class ItemTest {
    /**
     * Testing bulk price.
     */
    public static final BigDecimal MY_BULK_PRICE = new BigDecimal("89.99");
    
    /**
     * Testing price.
     */
    public static final BigDecimal MY_PRICE = new BigDecimal("19.99");
    
    /**
     * Testing bulk quantity.
     */
    public static final int MY_BULK_QUANTITY = 5;
    
    /**
     * Quantity of the ordered item.
     */
    public static final int ORDER_QUANTITY = 2;
    
    /**
     * Testing name.
     */
    public static final String MY_NAME = "X";
    
   
    /**
     * Item order for testing purpose.
     */
    private ItemOrder myItemOrder;
    
    /**
     * Cart for testing purpose.
     */
    private Cart myOrderCart;
    
    /**
     * item for testing purpose.
     */
    private Item myItem;
    
    /**
     * another item for testing purpose.
     */
    private Item myOtherItem;
    
    /**
     * Initial variables.
     */
    @Before
    public void setUp() {
        this.myItem = new Item(MY_NAME, MY_PRICE);
        this.myOtherItem = new Item(MY_NAME, MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        this.myItemOrder = new ItemOrder(myItem, ORDER_QUANTITY);
        this.myOrderCart = new Cart();
    }
    
    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testOtherHashCode() {
        assertEquals("Test HashCode: ", myOtherItem.hashCode(), myOtherItem.hashCode());
    }
    
    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testOtherExtraHashCode() {
        final Item testItem = new Item(MY_NAME, MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        assertEquals("Test HashCode: ", myOtherItem.hashCode(), testItem.hashCode());
    }
    
    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        final Item testItem = new Item(MY_NAME, MY_PRICE);
        assertEquals("Test HashCode: ", myItem.hashCode(), testItem.hashCode());
    }
    
    //===================================================================
    
    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimal() {
        assertEquals("Test price: ", MY_PRICE, myItem.getPrice());
    }

    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal, 
     * int, java.math.BigDecimal)}.
     */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        assertEquals("Test price: ", MY_PRICE, myOtherItem.getPrice());
        assertEquals("Test quantity: ", MY_BULK_QUANTITY, myOtherItem.getBulkQuantity());
        assertEquals("Test quantity price: ", MY_BULK_PRICE, myOtherItem.getBulkPrice());
    }
    
    /**
     * Test method for {@link model.Item#Item(java.lang.String, java.math.BigDecimal)}.
     * Test when price is less than 0
     * throw IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testItemStringBigDecimalNegative() {
        new Item(MY_NAME, new BigDecimal("-1.0"));
    } 
    
    /**
     * Test when bulk price is less than 0.
     * throw IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBulkPriceNegative() {
        new Item(MY_NAME, MY_PRICE, MY_BULK_QUANTITY, new BigDecimal("-1.0"));
    } 
    
    /**
     * Test when the bulk quantity is less than 0.
     * Throw IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBulkQuantityNegative() {
        new Item(MY_NAME, MY_PRICE, -1, MY_BULK_PRICE);
    }

    //===================================================================
    
    /**
     * Test method for {@link model.Item#getPrice()}.
     */
    @Test
    public void testGetPrice() {
        assertEquals("Testing price: ", MY_PRICE, myItem.getPrice());
    }

    /**
     * Test method for {@link model.Item#getBulkQuantity()}.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals("Test bulk quantity: ", MY_BULK_QUANTITY, myOtherItem.getBulkQuantity());
    }

    /**
     * Test method for {@link model.Item#getBulkPrice()}.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals("Test bulk price: ", MY_BULK_PRICE, myOtherItem.getBulkPrice());
    }

    //===================================================================
    
    /**
     * Test method for {@link model.Item#isBulk()}.
     * Test if buying in bulk is available
     */
    @Test
    public void testYesIsBulk() {
        assertTrue("Bulk is available:", myOtherItem.isBulk());
    }
    
    /**
     * Test method for {@link model.Item#isBulk()}.
     * Test if buying in bulk is not available
     */
    @Test
    public void testNoIsBulk() {
        assertFalse("Bulk is not available:", myItem.isBulk());
    }

    //===================================================================
    
    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("testing toString:", "X, $19.99", myItem.toString());
    }
    
    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testOtherToString() {
        assertEquals("testing toString:", "X, $19.99 (5 for $89.99)", myOtherItem.toString());
    }
    
    //===================================================================
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     * ignore PMD alert - doing it on purpose.
     */
    @Test
    public void testNullEquals() {
        assertFalse("Testing equals method: ", myItem.equals(null));
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     * ignore PMD alert - doing it on purpose.
     */
    @Test
    public void testNullOtherEquals() {
        assertFalse("Testing equals method: ", myOtherItem.equals(null));
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testClass() {
        final Item testingItem = new Item(MY_NAME, MY_PRICE);
        assertTrue("Testing equals method: ", 
                   myItem.getClass().equals(testingItem.getClass())); 
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsMethod() {
        final String test = "null";
        assertFalse("Testing equals method: ", myItem.equals(test)); 
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testOtherClass() {
        final Item testingItem = new Item(MY_NAME, MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        assertTrue("Testing equals method: ", 
                   myOtherItem.getClass().equals(testingItem.getClass())); 
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEquals() {
        final Item testingItem = new Item(MY_NAME, MY_PRICE);
        assertEquals("Testing equals method: ", myItem, testingItem);  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testOtherEquals() {
        final Item testItem = new Item(MY_NAME, MY_PRICE, MY_BULK_QUANTITY, 
                                       MY_BULK_PRICE);
        assertEquals("Testing equals method: ", myOtherItem, testItem);  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testNameEquals() {
        final Item testItem = new Item("XX", MY_PRICE);
        assertFalse("Testing equals method: ", myItem.equals(testItem));  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testOtherNameEquals() {
        final Item testItem = new Item("XX", MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        assertFalse("Testing equals method: ", myItem.equals(testItem));  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testPriceEquals() {
        final Item testItem = new Item(MY_NAME, new BigDecimal("20.00"));
        assertFalse("Testing equals method: ", myItem.equals(testItem));  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testOtherPriceEquals() {
        final Item testItem = new Item(MY_NAME, 
                                       new BigDecimal("1.00"), 
                                       MY_BULK_QUANTITY, MY_BULK_PRICE);
        assertFalse("Testing equals method: ", myOtherItem.equals(testItem));  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testBulkQuanityEquals() {
        final Item testItem = new Item(MY_NAME, MY_PRICE, 10, MY_BULK_PRICE);
        assertFalse("Testing equals method - bulk quantity: ", myOtherItem.equals(testItem));  
    }
    
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testBulkPriceEquals() {
        final Item testItem = new Item(MY_NAME, MY_PRICE, MY_BULK_QUANTITY, 
                                       new BigDecimal("100.00"));
        assertFalse("Testing equals method - bulk price: ", myOtherItem.equals(testItem));  
    }
 
    //===================================================================
   
    /**
     * Test method for {@link model.ItemOrder#getQuantity()}.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("testing order quantity: ", ORDER_QUANTITY, myItemOrder.getQuantity());
    }
    
    /**
     * Test method for {@link model.ItemOrder#getQuantity()}.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testGetQuantityNegative() {
        new ItemOrder(myItem, -1);
    }
    
    /**
     * Test method for {@link model.ItemOrder#getItem()}.
     */
    @Test
    public void testGetItem() {
        assertEquals("testing getItem: ", myItem, myItemOrder.getItem());
    }
    
    /**
     * Test method for {@link model.ItemOrder#getItem()}.
     */
    @Test
    public void testOtherGetItem() {
        myItemOrder = new ItemOrder(myOtherItem, ORDER_QUANTITY);
        assertEquals("testing getItem: ", myOtherItem, myItemOrder.getItem());
    }
    
    /**
     * Test method for {@link model.ItemOrder#toString()}.
     */
    @Test
    public void testOrderToString() {
        assertEquals("testing toString for order item:", 
                     "Item=" + myItem + " Quantity=" + ORDER_QUANTITY, myItemOrder.toString());
    }
    //================================================================
    /**
     * Test method for {@link model.Cart#toString()}.
     */
    @Test
    public void testCartToString() {
        myOrderCart.add(myItemOrder);
        final String shopping = "Shopping Cart=[Item=X, $19.99 Quantity=2]";
        assertEquals("testing toString cart: ", shopping, myOrderCart.toString());
    }
    
    /**
     * Test method for {@link model.Cart#calculateTotal()}.
     */
    @Test
    public void testCalculateTotal() {
        myOrderCart.add(myItemOrder);
        final BigDecimal total = MY_PRICE.multiply(new BigDecimal(ORDER_QUANTITY));
        assertEquals("Testing calculation total: ", total, myOrderCart.calculateTotal());
    }
    
    /**
     * Test method for {@link model.Cart#calculateTotal()}.
     */
    @Test
    public void testOtherCalculateTotal() {
        final BigDecimal total = MY_PRICE.multiply(new BigDecimal(ORDER_QUANTITY));
        myItemOrder = new ItemOrder(myOtherItem, ORDER_QUANTITY);
        myOrderCart.add(myItemOrder);
        assertEquals("Testing calculation total: ", total, myOrderCart.calculateTotal());
    }
}
