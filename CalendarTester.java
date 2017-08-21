
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import java.util.ArrayList;

public class CalendarTester {
public static void main(String[] args) 
{
	CalendarView cv = new CalendarView();
	Model m = cv.getModel();
	cv.themeFrame();
	
	ArrayList<Event> all = new ArrayList(DB.loadAll());
	
	for(Event current : all)
	{
		System.out.println(current.toString());
		m.addEvent(current.getYear(), current.getMonth(), current.getDay(), current.getStartTime(), current.getEndTime(), current.getDescription(), current.getStartTod(), current.getendTod(), current.getColor());
	}
	
//	cv.paintDayView();
//	cv.paintAgendaView();
//	cv.paintMonthView();
//	cv.createEventBox();
}
}
