/**
 * Application development in Java, 5dv135-apjava-ht17,
 * Assignment 1: MyUnitTester,
 * 
 * 
 * Last Edited: 2017-12-06
 * @author Moa Hermansson, id14mhn
 */

package myUnitTester;

import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 * 
 * The Controller is given the view. It sets listeners to the viewButtons.
 * Controller starts a swingworker if the testbutton is being clicked or if the textFields
 * get hit with enter. It creates a model and checks the given input from the interface.
 * 
 *
 */
public class ControllerT {

	private ViewT view;

	public ControllerT(ViewT view){
		this.view = view;

		setRunListener();
		setClearListener();
	}
	
	/**
	 * A listener that starts a swingworker when activated.
	 * Connected to the model and prints result to view.
	 */

	private void setRunListener(){

		JTextArea textArea = view.textArea;
		JTextField textField = view.textField;

		ActionListener clearListener2 = click ->{
			String testToTest = view.getInput();

			view.writeOutput("Testing: "+testToTest+"\n");
			

			class Worker extends SwingWorker<Object, Object>{
				String result = "";
				String error = "";
				@Override
				protected Object doInBackground()
				{
					ModelT classChecker = new ModelT();
					classChecker.startTests(testToTest);
					error=classChecker.getError();
					
					if(!error.isEmpty()){
						result+="Threw Exception: "+error+"\n";
					}

					if(classChecker.getIncorrectClass()==false){

							result = classChecker.getResult()+"\n";
							result += " "+classChecker.getNumberOfSuccessTest()+" tests succeded\n "+classChecker.getNumberOfFailedTest()+" tests failed\n "
									+classChecker.getNumberOfExceptionTest()+" tests failed because of an exception\n\n";

					}
					else{result+=" Incorrect class, Needs to implement TestClass and \nhave a constructor with no parameters.\n\n";}

					return result;

				}
				@Override
				protected void done(){
						/* When the method doInBackground() is finished it will try to append the result
						 * to the textArea in the interface. */

						textArea.append(result);
						textField.selectAll();
						textArea.setCaretPosition(textArea.getDocument().getLength());

				}
			}
			new Worker().execute();
			
		};

		view.setRunButtonListener(clearListener2);
	}

	private void setClearListener(){

		ActionListener clearListener = click -> view.clearText();
		view.setClearButtonListener(clearListener);
	}


}

