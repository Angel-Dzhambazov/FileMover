package common;

import java.nio.file.Files;
import java.nio.file.Paths;

import gui.Frame1;

public class FileMoverRun {


	private static CopyProcessor copyThread;

	public static void startTheProgram() {
		String sourceFolder = Frame1.getSourcePath();
		String destination = Frame1.getDestinationPath();

		CopyManager copyManager = new CopyManager(sourceFolder, destination);

		copyThread = new CopyProcessor(copyManager);
		copyThread.start();
	}

	public static void shutDownTheProgram() {
		copyThread.shutdown();
	}

	public static boolean checkIfDirectoryExists(String path) {
		if (path.length() > 0 && Files.isDirectory(Paths.get(path))) {
			return true;
		}
		return false;
	}

}
