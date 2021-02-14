package test;
import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import main.java.BearWorkshop;

import static org.junit.Assert.*;

/***
 * This class provides a framework to implement black box tests for various
 * implementations of the BearWorkshop Class. The BearWorkshop is having a
 * blowout sale and is offering the following savings.
 *
 * Bears are Buy 2 bears, get 1 free. It is 10% off the cost of a bear when a
 * single bear has 10 or more accessories (Note that embroidery, stuffing, and
 * the material used for the bear casing is not considered an accessory).
 * Additionally, clothes are buy 2, get one free on each bear. Only non free clothes count 
 * towards the 10 accessories or more savings part. 
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {BearWorkshop1.class},
                {BearWorkshop2.class},
                {BearWorkshop3.class},
                {BearWorkshop4.class},
                {BearWorkshop5.class}

        };
        return Arrays.asList(classes);
    }

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        Constructor<BearWorkshop> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    @Before
    public void setUp() throws Exception {

        // One default Bear (casing base = $30, no saving expected)
        oneBear = createBearWorkshop("AZ");
        oneBear.addBear(new Bear());
        oneBearExpected = 0.00; // no savings
        
        // Three Bears expected to not pay for cheapest one
        threeBears = createBearWorkshop("AZ");
        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
        threeBearsExpected = 30.00;
        
        
    }

    @After
    public void tearDown() throws Exception {
    }
 
/////////////////////////////////////  NO BEAR CASE  /////////////////////////////////////////
    /**
     * Test examines a BearWorkshop with no bears in it. With no bears added
     * there should be no savings.
     */
    @Test
    public void noBearsNoSavings() {
    	System.out.println("Running - noBearsNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Double ans = bears.calculateSavings();
    	assertEquals( 0.0, ans, 0);
    }
    
 ////////////////////////////////////  ONE BEAR CASES  ///////////////////////////////////////

    /**
     * Test examines a BearFactory with 1 default bear in it. The default bear 
     * with base stuffing costs $30 and there should be no savings.
     */
    @Test
    public void oneBaseBearNoSavings() {
    	System.out.println("Running - oneBaseBearNoSavings");
        Double ans = oneBear.calculateSavings();
        assertEquals(oneBearExpected, ans);
    }
    
    /**
     * Test examines a BearFactory with 1 foam stuffing bear in it. The foam
     * stuffing bear costs $50 and there should be no savings.
     */
    @Test
    public void oneFoamNoSavings() {
    	System.out.println("Running - oneFoamNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	bears.addBear(new Bear(Stuffing.stuffing.FOAM));
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with a 5 letter embroidery. 
     * The base stuffing bear costs $30, the one five letter embroidery cost $5 and
     * there should be no savings.
     */
    @Test
    public void oneBaseBearEmbroideryNoSavings() {
    	System.out.println("Running - oneBaseBearEmbroideryNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Bear baseInkBear = new Bear();
    	baseInkBear.ink = new Embroidery("12345");
    	bears.addBear(baseInkBear);
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with a default noise maker. 
     * The base stuffing bear costs $30, the default noise maker cost $10 and
     * there should be no savings.
     */
    @Test 
    public void oneBaseBearDefaultNoiseMakerNoSavings(){
    	System.out.println("Running - oneBaseBearDefaultNoiseMakerNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Bear baseDefNoiseMakerBear = new Bear();
    	baseDefNoiseMakerBear.addNoise(new NoiseMaker());
    	bears.addBear(baseDefNoiseMakerBear);
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with a custom noise maker. 
     * The base stuffing bear costs $30, the default noise maker cost $5 and
     * there should be no savings.
     */
    @Test
    public void oneBaseBearCustomNoiseMakerNoSavings() {
    	System.out.println("Running - oneBaseBearCustomNoiseMakerNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Bear baseNoiseMakerBear = new Bear();
    	baseNoiseMakerBear.addNoise(new NoiseMaker("Noise Maker Tester", "Testing 123", 
    												NoiseMaker.Location.RIGHT_HAND));
    	bears.addBear(baseNoiseMakerBear);
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with one piece of default clothing. 
     * The base stuffing bear costs $30, the one default piece of clothing cost $4 and
     * there should be no savings.
     */
    @Test
    public void oneBaseBearOnePieceDefaultClothingNoSavings() {
    	System.out.println("Running - oneBaseBearOnePieceDefaultClothinNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Bear baseDefClothingBear = new Bear();
    	baseDefClothingBear.clothing.add(new Clothing());
    	bears.addBear(baseDefClothingBear);
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with one piece of custom clothing. 
     * The base stuffing bear costs $30, the one custom piece of clothing cost $10 and
     * there should be no savings.
     */
    @Test
    public void oneBaseBearOnePieceCustomClothingNoSavings() {
    	System.out.println("Running - oneBaseBearOnePieceCustomClothingNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Bear baseCustomClothingBear = new Bear();
    	baseCustomClothingBear.clothing.add(new Clothing(10.0, "Hoodie"));
    	bears.addBear(baseCustomClothingBear);
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with two pieces of clothing. 
     * The base stuffing bear costs $30, the two pieces of clothing cost $14 and
     * there should be no savings.
     */
    @Test
    public void oneBaseBear2PiecesClothingNoSavings() {
    	System.out.println("Running - oneBaseBear2PiecesClothingNoSavings");
    	BearWorkshop bears = null;
    	try { bears = createBearWorkshop("AZ"); } catch (Exception e){}
    	Bear baseTwoClothingBear = new Bear();
    	baseTwoClothingBear.clothing.add(new Clothing());
    	baseTwoClothingBear.clothing.add(new Clothing(10.0, "Hoodie"));
    	bears.addBear(baseTwoClothingBear);
    	Double ans = bears.calculateSavings();
    	assertEquals( Double.valueOf(0.0), ans);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with three pieces of clothing all the same price $4. 
     * The base stuffing bear costs $30, the three pieces of clothing cost $8 
     * with $4 in savings 
     */
    @Test
    public void oneBaseBear3ClothingsExpectSaving() {
    	System.out.println("Running - oneBaseBear3ClothingsExpectSaving");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with three pieces of clothing all different prices. 
     * The base stuffing bear costs $30, the three pieces of clothing cost $10 
     * with savings of $4 the price of the cheapest piece of clothes
     */
    @Test
    public void oneBaseBear3DifferentClothingsExpectSaving() {
    	System.out.println("Running - oneBaseBear3DifferentClothingsExpectSaving");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(5, "Sunglasses"));
	    customBear.clothing.add(new Clothing(6, "Shoes"));
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with six pieces of clothing all different prices. 
     * The base stuffing bear costs $30, the six pieces of clothing cost $18 for a total of $48
     * with savings of $3 the price of the cheapest piece of clothes
     */
    @Test
    public void oneBaseBear6DifferentClothingsExpectSaving() {
    	System.out.println("Running - oneBaseBear6DifferentClothingsExpectSaving");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
	    customBear.clothing.add(new Clothing(1, "Hat"));
	    customBear.clothing.add(new Clothing(2, "Sunglasses"));
	    customBear.clothing.add(new Clothing(3, "Shoes"));
	    customBear.clothing.add(new Clothing(4, "Shirt"));
	    customBear.clothing.add(new Clothing(5, "Pants"));
	    customBear.clothing.add(new Clothing(6, "Gloves"));
        Double bearsExpected = 3.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with ten accessories all $10 each. 
     * The base stuffing bear costs $30, the ten accessories cost $100 for a total of $117
     * with savings of $13 for 10% off with ten accessories
     */
    @Test
    public void oneBaseBear10AccessoriesNoClothingExpectSavings() {
    	System.out.println("Running - oneBaseBear10AccessoriesNoClothingExpectSavings");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        customBear.noisemakers.add(new NoiseMaker());
        Double bearsExpected = 13.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with 14 accessories all clothing costing $2 each.
	 * The bear the cost base stuffing cost $30 plus 14 pieces of clothing with four of them free at $2 
	 * The bear is $50 with ten accessories
     * the bear is 10% off for a total of $45 with a savings of $2 for the four pieces of clothing and then 10% off $50 = $5 for 
     * a total savings of $13
     */
    @Test
    public void oneBaseBear10AccessorieAllClothingExpectSavings() {
    	System.out.println("Running - oneBaseBear10AccessorieAllClothingExpectSavings");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
	    customBear.clothing.add(new Clothing(2, "1"));
	    customBear.clothing.add(new Clothing(2, "2"));
	    customBear.clothing.add(new Clothing(2, "3"));//FREE
	    customBear.clothing.add(new Clothing(2, "4"));
	    customBear.clothing.add(new Clothing(2, "5"));
	    customBear.clothing.add(new Clothing(2, "6"));//FREE
	    customBear.clothing.add(new Clothing(2, "7"));
	    customBear.clothing.add(new Clothing(2, "8"));
	    customBear.clothing.add(new Clothing(2, "9"));//FREE
	    customBear.clothing.add(new Clothing(2, "10"));
	    customBear.clothing.add(new Clothing(2, "11"));
	    customBear.clothing.add(new Clothing(2, "12"));//FREE
	    customBear.clothing.add(new Clothing(2, "13"));
	    customBear.clothing.add(new Clothing(2, "14"));
        Double bearsExpected = 13.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Test examines a BearFactory with 1 base bear with ten accessories all.
     * 1 default noise maker for $10 and 4 more for $5 each for $30 together
     * Then 7 pieces of clothes each for $2 and each third one free together for $10
     * The bears total price is $30+$30+$10 = $70
     * With ten accessories not including the free piece of clothing
     * the bear is 10% off for a total of $63 with a savings of $7
     * Plus the $4 saved on the two pieces of clothing
     * For a total savings of $11
     */
    @Test
    public void oneBaseBear10AccessorieExpectSavings() {
    	System.out.println("Running - oneBaseBear10AccessoriesExpectSavings");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        customBear.addNoise(new NoiseMaker());
        customBear.addNoise(new NoiseMaker("","", NoiseMaker.Location.LEFT_FOOT));
        customBear.addNoise(new NoiseMaker("","", NoiseMaker.Location.LEFT_HAND));
        customBear.addNoise(new NoiseMaker("","", NoiseMaker.Location.RIGHT_FOOT));
        customBear.addNoise(new NoiseMaker("","", NoiseMaker.Location.RIGHT_HAND));
	    customBear.clothing.add(new Clothing(2, "Hat"));
	    customBear.clothing.add(new Clothing(2, "Sunglasses"));
	    customBear.clothing.add(new Clothing(2, "Shoes"));//FREE
	    customBear.clothing.add(new Clothing(2, "Shirt"));
	    customBear.clothing.add(new Clothing(2, "Pants"));
	    customBear.clothing.add(new Clothing(2, "Gloves"));//FREE
	    customBear.clothing.add(new Clothing(2, "Bandana"));
        Double bearsExpected = 11.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
////////////////////////////////////// THREE BEARS CASES /////////////////////////////////////

    /**
     * Create a workshop with three base bears each $30
     * Bears are buy 2 get one free so the total is $60 with a $30 savings
     */
    @Test
    public void threeBaseBearsExpectSavings() {
    	System.out.println("Running - threeBaseBearsExpectSavings");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        Bear customBear1 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear2 = new Bear(Stuffing.stuffing.BASE);
        Bear customBear3 = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear1);
        bears.addBear(customBear2);
        bears.addBear(customBear3);
        Double bearsExpected = 30.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    /**
     * Creates three bears with no accessories. 
     * One bear is base for $30
     * One bear is Down for $40
     * One bear is foam for $50
     * Bears are buy two get one free the cheapest bear is dropped
     * SO total is $90 with a savings of $30
     * 
     */
    @Test
    public void threeDifferentBearsSaveOnCheapest() {
    	System.out.println("Running - threeDifferentBearsSaveOnCheapest");
        Double ans = threeBears.calculateSavings();
        assertEquals(threeBearsExpected, ans);
    }
    
//////////////////////////////////////  MULTIPLE BEARS CASES /////////////////////////////////////

    /**
     * This test creates a workshop and addes 100 base bears each for $30 to the shop.
     * The when the calculate savings method it called it should return $300 because bears are
     * buy 2 get the third (cheapest one) free
     */
    @Test
    public void thrityBaseBears() {
    	System.out.println("Running - oneHundredBaseBears");
        BearWorkshop bears = null;
        try { bears = createBearWorkshop("AZ");} catch (Exception e){}
        for(int i = 0; i < 30; i++) {
            Bear customBear = new Bear(Stuffing.stuffing.BASE);
            bears.addBear(customBear);
        }
        Double bearsExpected = 300.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans);
    }
    

///////////////////////////////////////  FINAL OUTPUT CASE  //////////////////////////////////
    @Test
    public void testMethod(){
    	System.out.println("Running - End\n");
        assertEquals(1,1);
    }

}
