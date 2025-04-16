package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class CustomLogFormatter extends SimpleFormatter {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String format(LogRecord logRecord) {
		final StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append("["+this.dateFormat.format(new Date(logRecord.getMillis()))+"]");
		stringBuffer.append(" ");
		
		stringBuffer.append(logRecord.getMessage());
		stringBuffer.append("\n");
		
		return stringBuffer.toString();
	}
}
