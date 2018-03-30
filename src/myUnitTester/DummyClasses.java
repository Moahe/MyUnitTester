package myUnitTester;

public class DummyClasses implements TestClass{

	public DummyClasses(){}
    
    public void setUpDummy() throws InterruptedException{
    	Thread.sleep(200);
    }

	public boolean testSleep() throws InterruptedException{
		Thread.sleep(2000);
		return false;
		}
}

