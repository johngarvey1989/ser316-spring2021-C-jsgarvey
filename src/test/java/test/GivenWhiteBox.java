package test;
import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import main.java.BearWorkshop;


public class GivenWhiteBox {
    BearWorkshop oneBear;
    BearWorkshop testShop;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(33.17, ans, 0.005);
    }
    
    @Test 
    public void testNodeCoverageofGetCost() {
    	testShop = new BearWorkshop();
    	Bear bearOne = new Bear();
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.noisemakers.add(new NoiseMaker());
    	bearOne.ink = new Embroidery("X");
    	testShop.addBear(bearOne);
    	Double ans = testShop.getCost(bearOne);
    	assertEquals(73.8, ans, 0.005);
    }
    
    @Test
    public void testEdgeCoverageOfGetCost() {
    	testShop = new BearWorkshop();
    	Bear bearOne = new Bear();
    	bearOne.clothing.add(new Clothing());
    	testShop.addBear(bearOne);
    	Double ans = testShop.getCost(bearOne);
    	assertEquals(35, ans, 0.005);
    }
    
    @Test 
    public void testNodeCoverageofGetRawCost() {
    	testShop = new BearWorkshop();
    	Bear bearOne = new Bear();
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.clothing.add(new Clothing());
    	bearOne.noisemakers.add(new NoiseMaker());
    	bearOne.ink = new Embroidery("X");
    	testShop.addBear(bearOne);
    	Double ans = testShop.getRawCost(bearOne);
    	assertEquals(102.0, ans, 0.005);
    }
    
    @Test
    public void testEdgeCoverageOfGetRawCost() {
    	testShop = new BearWorkshop();
    	Bear bearOne = new Bear();
    	bearOne.clothing.add(new Clothing());
    	testShop.addBear(bearOne);
    	Double ans = testShop.getRawCost(bearOne);
    	assertEquals(35, ans, 0.005);
    }
    
    @Test
    public void testCalculateTax() {
    	BearWorkshop testShop1 = new BearWorkshop();
    	BearWorkshop testShop2 = new BearWorkshop("NY");
    	BearWorkshop testShop3 = new BearWorkshop("VA");
    	BearWorkshop testShop4 = new BearWorkshop("DC");
    	BearWorkshop testShop5 = new BearWorkshop("CA");
    	BearWorkshop testShop6 = new BearWorkshop("XX");
    	Double ans1 = testShop1.calculateTax();
    	Double ans2 = testShop2.calculateTax();
    	Double ans3 = testShop3.calculateTax();
    	Double ans4 = testShop4.calculateTax();
    	Double ans5 = testShop5.calculateTax();
    	Double ans6 = testShop6.calculateTax();
    	assertEquals(1.07, ans1, 0.005);
    	assertEquals(1.09, ans2, 0.005);
    	assertEquals(1.05, ans3, 0.005);
    	assertEquals(1.105, ans4, 0.005);
    	assertEquals(1.1, ans5, 0.005);
    	assertEquals(1.05, ans6, 0.005);
    	
    }


    @Test
    public void testAddBear() {
    	testShop = new BearWorkshop();
    	Bear bearOne = new Bear();
    	bearOne.clothing.add(new Clothing());
    	boolean f = testShop.addBear(bearOne);
    	assertTrue(f);
    }

    @Test
    public void testRemoveBear() {
    	testShop = new BearWorkshop();
    	Bear bearOne = new Bear();
    	testShop.addBear(bearOne);
    	bearOne.clothing.add(new Clothing());
    	boolean f1 = testShop.removeBear(bearOne);
    	boolean f2 = testShop.removeBear(bearOne);
    	assertTrue(!f2);
    }
    
    
    
    
}
