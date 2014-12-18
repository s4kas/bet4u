package com.bmp.bet4u.common.parser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	private final static String DD_MM_YYYY = "dd/MM/yyyy";
	
	public static Calendar getDate(String format, String date, String anoInicio,
			String anoFim) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Date dateObj = new Date();
		if (format.equals("dd/MMM")) {
			String[] newDate = date.split("/");
			newDate[1] = getMesNumerico(newDate[1]);
			
			String year = Integer.parseInt(newDate[1]) >= 8 ? anoInicio : anoFim;
			
			date = newDate[0] + "/" + newDate[1] + "/" + year;
			SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
			dateObj = sdf.parse(date);
			
		} else if (format.equals("dd-MMM-yy")) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateObj = sdf.parse(date);
		}
		
	    cal.setTime(dateObj);
	    return cal;
	}
	
	public static Calendar getDate(String format, String date) throws ParseException {
		return getDate(format, date, null, null);
	}
	
	private static String getMesNumerico(String mesTexto) {
		if (mesTexto.equalsIgnoreCase("ago")) {
			return "8";
		} else if (mesTexto.equalsIgnoreCase("set")) {
			return "9";
		} else if (mesTexto.equalsIgnoreCase("out")) {
			return "10";
		} else if (mesTexto.equalsIgnoreCase("nov")) {
			return "11";
		} else if (mesTexto.equalsIgnoreCase("dez")) {
			return "12";
		} else if (mesTexto.equalsIgnoreCase("jan")) {
			return "1";
		} else if (mesTexto.equalsIgnoreCase("fev")) {
			return "2";
		} else if (mesTexto.equalsIgnoreCase("mar")) {
			return "3";
		} else if (mesTexto.equalsIgnoreCase("abr")) {
			return "4";
		} else if (mesTexto.equalsIgnoreCase("mai")) {
			return "5";
		} else if (mesTexto.equalsIgnoreCase("jun")) {
			return "6";
		} else if (mesTexto.equalsIgnoreCase("jul")) {
			return "7";
		} else {
			return "";
		}
	}
	
}
