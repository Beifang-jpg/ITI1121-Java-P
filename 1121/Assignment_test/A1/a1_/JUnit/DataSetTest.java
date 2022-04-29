
/** Runs all the tests using a textual runner.  This is the
 *  alternative to running all the tests within your favorite
 *  development environment (DrJava, Eclipse...).  This may require
 *  downloading JUnit from <a href="www.junit.org">www.junit.org</a>.
 *
 * <pre>
 * > javac -cp junit-4.12.jar:hamcrest-all-1.3.jar:. DataSetTest.java
 * > java -cp junit-4.12.jar:hamcrest-all-1.3.jar:. DataSetTest
 * </pre>
 *
 * Requires JUnit 4.12 or higher.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 * @author GV Jourdan (gjourdan@uottawa.ca)
 * @author M. Sabetzadeh (msabetza@uottawa.ca)
 */


import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.junit.rules.Timeout;
import org.junit.Rule;

import static org.junit.Assert.*;

public class DataSetTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    @Test
    public void testWeatherConstructor() {
 	   DataSet ds;
 	   try {
        	ds = new DataSet("weather-nominal.csv");
        } catch (Exception e) {
        	fail("Should not have thrown any exception!");
        }
    }

    @Test
    public void testCreditWithoutCommasConstructor() {
    	DataSet ds;    
        try {
        	ds = new DataSet("credit-info.csv");
        } catch (Exception e) {
        	fail("Should not have thrown an exception!");
        }
     }

    @Test
    public void testCreditWithCommasConstructor() {
        DataSet ds;
        try {
        	ds = new DataSet("credit-info-with-commas.csv");
        } catch (Exception e) {
        	fail("Should not have thrown an exception!");
        }
     }

    @Test
    public void testWeatherDimensions() throws Exception {
        DataSet ds = new DataSet("weather-nominal.csv");
    	assertEquals(5, ds.getNumberOfAttributes());
    	assertEquals(14, ds.getNumberOfDatapoints());
    
     }

     
    @Test
    public void testCreditWithoutCommasDimensions() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");
    	assertEquals(21, ds.getNumberOfAttributes());
    	assertEquals(1000, ds.getNumberOfDatapoints());
    
     }
     
    @Test
    public void testCreditWithCommasDimensions() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
    	assertEquals(21, ds.getNumberOfAttributes());
    	assertEquals(1000, ds.getNumberOfDatapoints());    
     }

    @Test
    public void testWeatherSpotChecks() throws Exception {
        DataSet ds = new DataSet("weather-nominal.csv");

        String attr1 = ds.getAttributeName(0);
        String attr2 = ds.getAttributeName(3);
        String attr3 = ds.getAttributeName(5);
        
    	assertEquals("outlook", attr1);
    	assertEquals("windy", attr2);
    	assertEquals(null, attr3);
    	
    	String val1 = ds.getAttributeValue(4, 1);
    	String val2 = ds.getAttributeValue(13, 2);
    	String val3 = ds.getAttributeValue(14, 1);

    	assertEquals("cool", val1);
    	assertEquals("high", val2);
    	assertEquals(null, val3);

     }
     
    @Test
    public void testCreditWithoutCommasSpotChecks() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");

        String attr1 = ds.getAttributeName(8);
        String attr2 = ds.getAttributeName(15);
        String attr3 = ds.getAttributeName(21);
        
    	assertEquals("personal_status", attr1);
    	assertEquals("existing_credits", attr2);
    	assertEquals(null, attr3);
    	
    	String val1 = ds.getAttributeValue(400, 12);
    	String val2 = ds.getAttributeValue(500, 4);
    	String val3 = ds.getAttributeValue(100, 21);

    	assertEquals("39", val1);
    	assertEquals("3123", val2);
    	assertEquals(null, val3);        

     }
     
    @Test
    public void testCreditWithCommasSpotChecks() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
        
        String attr1 = ds.getAttributeName(8);
        String attr2 = ds.getAttributeName(15);
        String attr3 = ds.getAttributeName(21);
        
    	assertEquals("gender, status", attr1);
    	assertEquals("existing_credits", attr2);
    	assertEquals(null, attr3);
    	
    	String val1 = ds.getAttributeValue(400, 12);
    	String val2 = ds.getAttributeValue(500, 4);
    	String val3 = ds.getAttributeValue(100, 21);

    	assertEquals("39", val1);
    	assertEquals("3123", val2);
    	assertEquals(null, val3);        
     }

    @Test
    public void testWeatherUniqueValues1() throws Exception {
        DataSet ds = new DataSet("weather-nominal.csv");
        String[] array = ds.getUniqueAttributeValues("outlook");
        assertEquals(3, array.length);    
     }

    @Test
    public void testWeatherUniqueValues2() throws Exception {
        DataSet ds = new DataSet("weather-nominal.csv");
        String[] array = ds.getUniqueAttributeValues("humidity");
        assertEquals(2, array.length);    
     }

    @Test
    public void testWeatherUniqueValues3() throws Exception {
        DataSet ds = new DataSet("weather-nominal.csv");
        String[] array = ds.getUniqueAttributeValues("temperature");
        assertEquals(3, array.length);    
     }

     
    @Test
    public void testCreditWithoutCommasUniqueValues1() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");
        String[] array = ds.getUniqueAttributeValues("personal_status");
        assertEquals(4, array.length);    
     }

    @Test
    public void testCreditWithoutCommasUniqueValues2() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");
        String[] array = ds.getUniqueAttributeValues("credit_history");
        assertEquals(5, array.length);    
     }

    @Test
    public void testCreditWithoutCommasUniqueValues3() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");
        String[] array = ds.getUniqueAttributeValues("job");
        assertEquals(4, array.length);    
     }

    @Test
    public void testCreditWithoutCommasUniqueValues4() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");
        String[] array = ds.getUniqueAttributeValues("age");
        assertEquals(53, array.length);    
     }

    @Test
    public void testCreditWithoutCommasUniqueValues5() throws Exception {
        DataSet ds = new DataSet("credit-info.csv");
        String[] array = ds.getUniqueAttributeValues("credit_amount");
        assertEquals(921, array.length);    
     }
     
    @Test
    public void testCreditWithCommasUniqueValues1() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
        String[] array = ds.getUniqueAttributeValues("gender, status");
        assertEquals(4, array.length);    
     }

    @Test
    public void testCreditWithCommasUniqueValues2() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
        String[] array = ds.getUniqueAttributeValues("credit_history");
        assertEquals(5, array.length);    
     }

    @Test
    public void testCreditWithCommasUniqueValues3() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
        String[] array = ds.getUniqueAttributeValues("job");
        assertEquals(4, array.length);    
     }

    @Test
    public void testCreditWithCommasUniqueValues4() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
        String[] array = ds.getUniqueAttributeValues("age");
        assertEquals(53, array.length);    
     }
     
    @Test
    public void testCreditWithCommasUniqueValues5() throws Exception {
        DataSet ds = new DataSet("credit-info-with-commas.csv");
        String[] array = ds.getUniqueAttributeValues("credit_amount");
        assertEquals(921, array.length);    
     }

    @Test
    public void testLarge() throws Exception {
        DataSet ds = new DataSet("large.csv");
        String[] array = ds.getUniqueAttributeValues("credit_amount");
        assertEquals(921, array.length);

        array = ds.getUniqueAttributeValues("job");
        assertEquals(4, array.length); 

        array = ds.getUniqueAttributeValues("credit_history");
        assertEquals(5, array.length);     
     }

    @Test
    public void testMissingValues() throws Exception {
        DataSet ds = new DataSet("missing-values.csv");
        String[] array = ds.getUniqueAttributeValues("outlook");
        assertEquals(4, array.length);
        array = ds.getUniqueAttributeValues("temperature");
        assertEquals(12, array.length);
        array = ds.getUniqueAttributeValues("humidity");
        assertEquals(10, array.length);
        array = ds.getUniqueAttributeValues("windy");
        assertEquals(3, array.length);
        array = ds.getUniqueAttributeValues("play");
        assertEquals(3, array.length);
     }
     
    @Test
    public void testwithSpaces() throws Exception {
        DataSet ds = new DataSet("weather-with-spaces.csv");
        String[] array = ds.getUniqueAttributeValues("outlook");
        assertEquals(3, array.length);
        array = ds.getUniqueAttributeValues("temperature");
        assertEquals(12, array.length);
        array = ds.getUniqueAttributeValues("humidity");
        assertEquals(10, array.length);
        array = ds.getUniqueAttributeValues("windy");
        assertEquals(2, array.length);
        array = ds.getUniqueAttributeValues("play");
        assertEquals(2, array.length);
    }

         
    /**
     * Runs the test suite using the textual runner.
     *
     * @param args reference to the array of values passed on the command lines
     */

    public static void main( String[] args ) {

        TestUtils.runClass(DataSetTest.class);
        
    }
}
