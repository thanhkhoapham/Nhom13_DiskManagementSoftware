package com.xdpm.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatString {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("dd/MM/yyyy");
	public static String formatTienVN(double d) {
		return String.format("%,.0f", d);
	}
	public static String formatLocalDate(LocalDate date) {
		return date.format(formatter);
	}
	public static String formatDate(Date date) {
		return simpleDateFormat.format(date);
	}
}
