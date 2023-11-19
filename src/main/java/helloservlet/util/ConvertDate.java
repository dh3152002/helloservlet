package helloservlet.util;

import java.sql.Date;

public class ConvertDate {
	public static Date convertStringToDateSql(String date_string) {
		int year=Integer.parseInt(date_string.split("-")[0]);
		int month=Integer.parseInt(date_string.split("-")[1])-1;
		int day=Integer.parseInt(date_string.split("-")[2]);
		Date date=new Date(year,month,day);
		return date;
	}
}
