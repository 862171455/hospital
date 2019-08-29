package org.java.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GD on 2019/7/16 0016 22:52
 */

@Component
public class DateUtils {
	public static String getFormatDateTime(String pattern, int dateTime){
		Date d = new Date(dateTime);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}
	public static String getFormatDateTime(String pattern, java.sql.Date dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(dateTime);
	}
	
}
