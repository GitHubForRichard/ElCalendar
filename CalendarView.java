import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.Border;

public class CalendarView 
{
	public static void main(String[] args) {
	Hashtable<String, Integer> monthList = makeMonthList();
	JFrame eventFrame = new JFrame("Event Box");
	eventFrame.setSize(600, 450);
	
	Font labelFont = new Font("SanSerif", Font.BOLD, 20);
	Font contentFont = new Font("SanSerif", Font.BOLD, 16);
	JPanel descPanel = new JPanel();
	JPanel datePanel = new JPanel();
	JPanel startTimePanel = new JPanel();
	JPanel endTimePanel = new JPanel();
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
	JLabel startTimeLabel = new JLabel("Start Time:");
	JLabel endTimeLabel = new JLabel("End Time: ");
	dateLabel.setFont(labelFont);
	startTimeLabel.setFont(labelFont);
	endTimeLabel.setFont(labelFont);
	JTextField descText = new JTextField(20);
	descText.setFont(new Font("SansSerif", Font.PLAIN, 16));
	JLabel colorLabel = new JLabel("Color: ");
	colorLabel.setFont(labelFont);
	
	
	// add components in description section and put it into the top of the window
	descPanel.add(descLabel);
	descPanel.add(descText);
	box.add(descPanel);
	
	//add components in date section
	datePanel.add(dateLabel);
	datePanel.add(createYearBox());
	JComboBox monthBox = createMonthBox();
	datePanel.add(monthBox);
	JComboBox dayBox = createDayBox();
	datePanel.add(dayBox);
	box.createVerticalStrut(100);
	
	// add components in start Date area and put it below description section
//	startDatePanel.add(startDateLabel);
//	startDatePanel.add(createYearBox());
//	JComboBox startMonthBox = createMonthBox();
//	startDatePanel.add(startMonthBox);
//	JComboBox startDayBox = createDayBox();
//	startDatePanel.add(startDayBox);
	
//	 add components in end Date area and put it below start date section
//	endDatePanel.add(endDateLabel);
//	endDatePanel.add(createYearBox());
//	JComboBox endMonthBox = createMonthBox();
//	endDatePanel.add(endMonthBox);	
//	JComboBox endDayBox = createDayBox();
//	endDatePanel.add(endDayBox);
	
	// In start day section, adjust the maximum of the days according to the input from month section
	monthBox.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			String selectedMonth = (String) monthBox.getSelectedItem(); // read input from the month section
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH, monthList.get(selectedMonth) - 1);
			int maxDayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			dayBox.removeAllItems();
			for(int i = 1; i <= maxDayOfMonth; i++)
			{
				dayBox.addItem(i);
			}
			datePanel.repaint();
		}		
	});
	
	// In end day section, adjust the maximum of the days according to the input from month section
//	endMonthBox.addActionListener(new ActionListener()
//	{
//		public void actionPerformed(ActionEvent e) 
//		{
//			String selectedMonth = (String) endMonthBox.getSelectedItem(); // read input from the month section
//			Calendar c = Calendar.getInstance();
//			c.set(Calendar.MONTH, monthList.get(selectedMonth) - 1);
//			int maxDayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//			endDayBox.removeAllItems();
//			for(int i = 1; i <= maxDayOfMonth; i++)
//			{
//				endDayBox.addItem(i);
//			}
//			endDatePanel.repaint();
//		}		
//	});
	
	startTimePanel.add(startTimeLabel);
	JComboBox startTime = createTimeBox();
	startTimePanel.add(startTime);
	endTimePanel.add(endTimeLabel);
	JComboBox endTime = createTimeBox();
	endTimePanel.add(endTime);
	
	//create radio button for AM and PM options
	JRadioButton startTimeAM = new JRadioButton("A.M.");
	startTimeAM.setFont(contentFont);
	JRadioButton startTimePM = new JRadioButton("P.M.");
	startTimePM.setFont(contentFont);
	JRadioButton endTimeAM = new JRadioButton("A.M.");
	endTimeAM.setFont(contentFont);
	JRadioButton endTimePM = new JRadioButton("P.M.");
	endTimePM.setFont(contentFont);
	
	//adding radio button in a group
	ButtonGroup startGroup = new ButtonGroup();
	startGroup.add(startTimeAM);
	startGroup.add(startTimePM);
	
	ButtonGroup endGroup = new ButtonGroup();
	endGroup.add(endTimeAM);
	endGroup.add(endTimePM);
	
	startTimePanel.add(startTimeAM);
	startTimePanel.add(startTimePM);
	endTimePanel.add(endTimeAM);
	endTimePanel.add(endTimePM);
	
	//Make a checkbox to check if an event lasts all day
	JCheckBox allDayCheck = new JCheckBox("All Day");
	allDayCheck.setFont(contentFont);
	datePanel.add(allDayCheck);
	allDayCheck.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent arg0) 
		{
			startTime.setEnabled(!allDayCheck.isSelected());
			endTime.setEnabled(!allDayCheck.isSelected());
			startTimeAM.setEnabled(!allDayCheck.isSelected());
			startTimePM.setEnabled(!allDayCheck.isSelected());
			endTimeAM.setEnabled(!allDayCheck.isSelected());
			endTimePM.setEnabled(!allDayCheck.isSelected());
		}		
	});
	
	box.add(datePanel);
	
	box.add(startTimePanel);
	box.createVerticalStrut(70);
	
	box.add(endTimePanel);
	box.createVerticalStrut(50);

	
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
	
	public static Hashtable<String,Integer> makeMonthList()
	{
		Hashtable<String,Integer> ht = new Hashtable<>();
		ht.put("January", 1);
		ht.put("February", 2);
		ht.put("March", 3);
		ht.put("April", 4);
		ht.put("May", 5);
		ht.put("June", 6);
		ht.put("July", 7);
		ht.put("August", 8);
		ht.put("September", 9);
		ht.put("October", 10);
		ht.put("November", 11);
		ht.put("December", 12);
		return ht;
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
	for(int i = 0; i <= 11; i++)
	{
		for(int j = 0; j < 4; j++)
		{
			if(i == 0)
			{
				timeBox.addItem("12:" + minutesList[j]);
			}
			else
			{
				timeBox.addItem(i + ":" + minutesList[j]);
			}
		}
	}
	return timeBox;
}
}

