package test;
import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;



public class GivenWhiteBox {
    BearWorkshop oneBear;

    @Before
    public void setUp() throws Exception {
    	oneBear = new BearWorkshop();
    }



    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(5.35, ans, 0.005);
    }
}
