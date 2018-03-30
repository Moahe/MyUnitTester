package myUnitTester;
import org.junit.Test;
import org.junit.Assert;
/**
 * Unit test for simple App.
 */
public class ModelTest
{
	ModelT classChecker = new ModelT();
    @Test
    public void shouldDetectMissingInterface() throws Exception {
        Assert.assertFalse(classChecker.checkIfImplementsInterface(DummyClasses.class, "null"));
    }
    @Test
    public void shouldDetectMissingConstructorWithOneParameter() throws Exception {
        Assert.assertFalse(classChecker.checkConstructor(DummyClasses.class, 1));
    }
    
    @Test 
    public void shouldDetectClassHasStructwithzeroarguments() throws Exception{
    	
    	Assert.assertTrue(classChecker.checkConstructor(DummyClasses.class, 0));
    }
    
    @Test
    public void shouldDetectIfClassImpCorrectInterface() throws Exception{

    	Assert.assertTrue(classChecker.checkIfImplementsInterface(DummyClasses.class, "TestClass"));
    }
    
    @Test
    public void shouldDetectMissingpara() throws Exception{
    	DummyClasses test = new DummyClasses();
    	 int nrOfParameters = -1;
    	Assert.assertFalse(classChecker.checkConstructor(test.getClass(), nrOfParameters));
    	
    }
    
    @Test
    public void shouldDetectIncorrectClass() throws Exception {
    	classChecker.sortMethodsInClass(DummyInterface.class);
    	Assert.assertTrue(classChecker.getIncorrectClass());
    }
    
    @Test
    public void shouldDetectCorrectClass() throws Exception {
    	classChecker.sortMethodsInClass(DummyClasses.class);
    	Assert.assertTrue(0 < classChecker.getMethodList().size());
    }

}

