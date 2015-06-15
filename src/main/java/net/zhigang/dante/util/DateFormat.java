package net.zhigang.dante.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static String format(Date date) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
	return simpleDateFormat.format(date);
    }
}
