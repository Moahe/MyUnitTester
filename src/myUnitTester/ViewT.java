/**
 * Application development in Java, 5dv135-apjava-ht17,
 * Assignment 1: MyUnitTester,
 * 
 * 
 * Last Edited: 2017-12-06
 * @author Moa Hermansson, id14mhn
 */
package myUnitTester;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Creates a GUI with java.swing and adds a textField,
 * textArea and two buttons. One for clearing the textArea
 * and one for testing the input in the textField.
 * 
 */
@SuppressWarnings("serial")
public class ViewT extends JPanel{
	protected JTextField textField;
	protected JTextArea textArea;
	protected JButton testButton;
	protected JButton clearButton;

	public ViewT() {
		
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(500,500));
		
		//Creates components
		textField = new JTextField(20);
		textArea = new JTextArea(30, 40);
		testButton = new JButton("Test");
		clearButton = new JButton("Clear");
		
		/*Adds the ActionListeners to the buttons and fields.*/
		/*
		textField.addActionListener(new TextListener(textArea, textField));
		clearButton.addActionListener(new ButtonListener(textArea));*/
		//testButton.addActionListener(new ActionListener(){
		
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		frame.add(testButton);

		GridBagConstraints c = new GridBagConstraints();
	
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(textField, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		frame.add(scrollPane, c);
		frame.add(clearButton);
		frame.pack();
		frame.setVisible(true);

	}


public void setClearButtonListener(ActionListener actionListener){
	clearButton.addActionListener(actionListener);
}	

public String getInput(){
	return textField.getText();
}

public void writeOutput(String output){
	textArea.append(output);
}

public void clearText(){
    textArea.setText(null);
    textField.setText(null);
}

public void setRunButtonListener(ActionListener actionListener){
    testButton.addActionListener(actionListener);
    textField.addActionListener(actionListener);
}

}