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
	
	
	public  static String getnow(){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String hehe = dateFormat.format( now );
		System.out.println(hehe);
		return hehe;
	}
	
}
