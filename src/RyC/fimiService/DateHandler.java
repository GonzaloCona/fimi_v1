package RyC.fimiService;

import java.util.Date;

public class DateHandler {
	private Date date;

    public DateHandler(){
		
	}
	public DateHandler(Date date){
		this.date=date;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date NewDate(){
		java.util.Date date = new Date();
		return date;
	}
	
}
