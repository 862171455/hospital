package org.java.util;

import java.util.UUID;

/**
 * Created by GD on 2019/8/22 0022 9:56
 */
public class UuidUtils {
	public static String uuid() {
			String a = UUID.randomUUID().toString();
			System.out.println(a);
			return a;
	}
 
}
