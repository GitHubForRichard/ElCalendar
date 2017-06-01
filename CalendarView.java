package hw2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class CalendarView 
{
	public static void main(String[] args) {
	JFrame eventFrame = new JFrame("Event Box");
	eventFrame.setSize(550, 300);
	
	Font labelFont = new Font("SanSerif", Font.BOLD, 20);
	Font contentFont = new Font("SanSerif", Font.BOLD, 16);
	JPanel descPanel = new JPanel();
	JPanel datePanel = new JPanel();
	JPanel endDatePanel = new JPanel();
	JPanel colorPanel = new JPanel();
	
	//create save button and modified its look
	JButton saveButton = new JButton("Save");
	saveButton.setBackground(new Color(50,205,50));
	saveButton.setForeground(Color.WHITE);
	saveButton.setFont(contentFont);
	
	//create cancel button and modified its look
	JButton cancelButton = new JButton("Cancel");
	cancelButton.setBackground(new Color(255,60,60));
	cancelButton.setForeground(Color.WHITE);
	cancelButton.setFont(contentFont);
	cancelButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent event) 
		{
			eventFrame.dispose();
		}
	});
	
	//implement box layout
	Box box = Box.createVerticalBox();
	JLabel descLabel = new JLabel("Description: ");
	descLabel.setFont(labelFont);
	
	//add labels and set its fonts.
	JLabel dateLabel = new JLabel("Date: ");
	dateLabel.setFont(labelFont);
	JTextField descText = new JTextField(20);
	descText.setFont(new Font("SansSerif", Font.PLAIN, 16));
	JLabel colorLabel = new JLabel("Color: ");
	colorLabel.setFont(labelFont);
	
	
	// add stuff in description area and put it into the top of the window
	descPanel.add(descLabel);
	descPanel.add(descText);
	box.add(descPanel);
	box.createVerticalStrut(100);
	
	// add components in start Date area and put it below description section
	datePanel.add(dateLabel);
	datePanel.add(createYearBox());
	datePanel.add(createMonthBox());
	datePanel.add(createDayBox());
	datePanel.add(createTimeBox());
	JRadioButton amButton = new JRadioButton("A.M.");
	amButton.setFont(contentFont);
	JRadioButton pmButton = new JRadioButton("P.M.");
	pmButton.setFont(contentFont);
	
	//adding radio button in a group
	ButtonGroup g = new ButtonGroup();
	g.add(amButton);
	g.add(pmButton);
	
	
	datePanel.add(amButton);
	datePanel.add(pmButton);
	box.add(datePanel);
	box.createVerticalStrut(70);
	
//  add components in color area and put it below end date section
	colorPanel.add(colorLabel);
	colorPanel.add(createColorBox());
	box.add(colorPanel);
	box.createVerticalStrut(10);
	
	// add buttons in the buttom of the window
	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new FlowLayout());
	buttonPanel.add(saveButton);
	buttonPanel.add(cancelButton);
	box.add(buttonPanel);
	
	eventFrame.add(box);
	eventFrame.setVisible(true);
	eventFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * create drop down menu(combo box) for year
	 */
	public static JComboBox createYearBox()
	{
		JComboBox yearBox = new JComboBox();
		yearBox.setFont(new Font("Sanserif", Font.BOLD, 16));
		int yearToAdd = 2017;
		for(int i = 0; i < 20; i++)
		{
			yearBox.addItem(yearToAdd);
			yearToAdd++;
		}		
		return yearBox;
	}
/*
* create drop down menu(combo box) for month
*/
public static JComboBox createMonthBox()
{
	JComboBox monthBox = new JComboBox();
	monthBox.setFont(new Font("Sanserif", Font.BOLD, 16));
	String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	for(int i = 0; i < 12; i++)
	{
		monthBox.addItem(months[i]);
	}
	return monthBox;
}

/*
 * create drop down menu(combo box) for day
 */
public static JComboBox createDayBox()
{
	JComboBox dayBox = new JComboBox();
	dayBox.setFont(new Font("Sanserif", Font.BOLD, 16));
	for(int i = 1; i < 32; i++)
	{
		dayBox.addItem(i);
	}
	return dayBox;
}

public static JComboBox createColorBox()
{
	JComboBox colorBox = new JComboBox();
	colorBox.setFont(new Font("Sanserif", Font.BOLD, 16));
	colorBox.addItem("RED");
	colorBox.addItem("BLUE");
	colorBox.addItem("GREY");
	colorBox.addItem("PINK");
	colorBox.addItem("ORANGE");
	return colorBox;
}

public static JComboBox createTimeBox()
{
	JComboBox timeBox = new JComboBox();
	String[] minutesList = {"00","15","30","45"};
	timeBox.setFont(new Font("Sanserif", Font.BOLD, 16));
	for(int i = 1; i <= 12; i++)
	{
		for(int j = 0; j < 4; j++)
		{
			timeBox.addItem(i + ":" + minutesList[j]);
		}
	}
	return timeBox;
}
}