/**
 * Application development in Java, 5dv135-apjava-ht17,
 * Assignment 1: MyUnitTester,
 * 
 * 
 * Last Edited: 2017-11-13
 * @author Moa Hermansson, id14mhn
 */

package myUnitTester;

import java.lang.reflect.*;
import java.util.*;

public class ModelT {

	private List<Method> testMethodsList;
	private List<String> testResultList ;
	private Method setUp;
	private Method tearDown;
	private Class<?> testClass;
	private String result = "";
	Class<?> c = null;
	String error = "";

	private int numOfRunTest=0;
	private int numOfSuccess=0;
	private int numOfFail=0;
	private int numOfExceptions = 0;

	private String methodName = "";
	private boolean hasSetup = false;
	private boolean hasTearDown = false;
	private boolean incorrectClass = false;

	public ModelT(){}

	/**
	 * Tries to find the class given by input string.
	 * */
	public void startTests(String testClass){

		try {
			c = Class.forName("myUnitTester."+testClass);
		} catch (ClassNotFoundException e) {
			incorrectClass = true;
		}
		if(incorrectClass==false){

			sortMethodsInClass(c);
			runTestMethods();
		}

	}

	/**
	 * This method checks if a given class implements the TestClass.
	 */
	public boolean checkIfImplementsInterface(Class<?> testClass, String interfaceName) {
		/* A try catch in case the given testClass does not have interfaces. In which case
		 * an exception will be thrown. */
		try{

			if(testClass.getInterfaces()[0].toString().contains(interfaceName)){
				return true; 
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

	/**
	 * This method checks if a given class has a constructor that takes the given number of parameters.
	 *
	 */
	public boolean checkConstructor(Class<?> testClass, int nrOfParameters) {
		/* A try catch in case the testClass does not have constructors. */
		try{
			if(testClass.getConstructors()[0].getParameterCount()==nrOfParameters){
				return true; 
			}
		}catch(Exception e){
			return false;
		}
		return false;

	}
	/**
	 * runTestMethods invokes methods in the TestClass. First
	 * it invokes the SetUp class of the class has one. After that it tries 
	 * to invoke the methods in testMethodsList. It will sort the result depending
	 * if the test succeeded, failed or failed with exception.
	 * After that it will run the tearDown method if the class has one.
	 *  
	 */

	public void runTestMethods(){

		Object ob = null;
		try {
			ob = testClass.newInstance();
		} catch (IllegalAccessException|InstantiationException e) {
			incorrectClass = true;
			error = e.getCause().toString();
		}
		/*Checks if in the previous method "sortMethodsInClass" it changed it to an incorrect class. Also checks
		 * if the testMethodsList is empty. Then it wont run. */
		if(incorrectClass==false && testMethodsList.isEmpty()==false){

			for(Method method: testMethodsList){
				if(hasSetup==true){
					try {
						setUp.invoke(ob);
					} catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException  e) {
						error = e.getCause().toString();
					}
				}



				Boolean returnVal = false;
				error = "";

				/* Tries to invoke the method. The returning boolean
				 * will be added to the string result depending if
				 * true/false */
				try{
					methodName = method.getName();
					returnVal = (Boolean)method.invoke(ob);
					numOfRunTest++;
					
					if(returnVal == true){
						numOfSuccess++;
						result+=(" "+methodName+": SUCCESS"+"\n");
					}
				}catch(IllegalAccessException|IllegalArgumentException|NullPointerException | InvocationTargetException e){
					error = e.getCause().toString();
					
				}

				if(returnVal == false){

					if(!error.isEmpty())
					{
					result+=" "+methodName+" : FAIL Generated a "+error+"\n";
					numOfFail++;}
					else{result+=(" "+methodName+": FAIL"+"\n");
					numOfExceptions++;}
				}
			}

			if(hasTearDown==true){
				try {
					tearDown.invoke(ob);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					error = e.getCause().toString();
				}
			}
		}
	}
	/**
	 * Takes in a testClass. It 
	 * will check if the testClass fills the conditions of
	 * if it is implemented correctly. After that it calls for
	 * the declared methods. Sorts the methods depending what 
	 * the method's name contains. If it contains setUp, tearDown,
	 * or test it will be saved. The test methods will be saved 
	 * in the list testMethodsList.
	 * 
	 * If the testClass does not pass the check of requirements
	 * an error will be added to the result string and the boolean
	 * incorrectClass will be changed to true.
	 * @param testClass
	 */
	public void sortMethodsInClass(Class<?> testClass){
		this.testClass = testClass;

		testMethodsList = new ArrayList<>();


		if((checkIfImplementsInterface(testClass,"TestClass") == true && checkConstructor(testClass, 0)) == true){

			Method[] methods = testClass.getDeclaredMethods();

			for(Method m : methods){
				String meth = m.toString();

				if(meth.contains("setUp")){
					hasSetup = true;
					setUp = m;
				}
				if(meth.contains("tearDown")){
					hasTearDown = true;
					tearDown = m;
				}
				if(meth.contains("test")){
					testMethodsList.add(m);
				}

			}

		}
		else{

			result += "Incorrect class";
			incorrectClass = true;

		}
	}
	public List<Method> getMethodList(){return testMethodsList;}

	public List<String> getTestResults(){return testResultList;}

	public Integer getNumberOfSuccessTest(){return numOfSuccess;}

	public Integer getNumberOfFailedTest(){return numOfFail;}

	public Integer getNumberOfExceptionTest(){return numOfExceptions;}

	public Integer getNumberOfTest(){return numOfRunTest;}

	public String getResult(){return result;}
	public String getError(){ return error;}

	public boolean getIncorrectClass(){return incorrectClass;}


}

