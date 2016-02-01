package br.com.fiap.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorData {
	
	public static Date formatarDate(String data, String oldFormat, String newFormat){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
			date = sdf.parse(data);
			sdf.applyPattern(newFormat);
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
