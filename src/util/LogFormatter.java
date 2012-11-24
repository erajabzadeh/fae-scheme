package util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

	@Override
	public String format(LogRecord lr) {
		return String.format(
				"[%1$tH:%1$tM:%1$tS] %2$s: %3$s%n",
				lr.getMillis(),
				lr.getLevel(),
				lr.getMessage()
				);
	}

}
