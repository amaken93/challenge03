package model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlLogger {
	public void sqlLogger(String sql) throws SecurityException, IOException {
		String path = dir();

		LocalDate now = LocalDate.now();
		Logger logger = Logger.getLogger("SQLlogs");

		FileHandler fHandler = new FileHandler(path + now.toString() + ".log", 0, 1, true);
		fHandler.setFormatter(new CustomLogFormatter());
		logger.addHandler(fHandler);

		logger.setLevel(Level.INFO);

		logger.info(sql);

		fHandler.close();
	}

	public String dir() {
		String path = "C:\\pleiades\\2024-12\\workspace\\Challenge03\\log\\";
		File dir = new File(path);
		if (dir.exists()) {
			System.out.println("y");
		} else {
			System.out.println("n");
			dir.mkdir();
		}
		return path;
	}
}
