package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;

//File obsolete? deprecated

public class CopyManager {
	//gui copy manager
	private File source;
	private File destination;
	private ArrayDeque<File> files = new ArrayDeque<>();

	public CopyManager(String source, String destination) {
		this.source = new File(source);
		this.destination = new File(destination);
	}

	public boolean checkEmptyDirectory() {
		return source.listFiles().length <= 0;
	}

	public void copyFiles() throws IOException {
		for (File file : source.listFiles()) {
			if(!file.isDirectory()) {
				files.add(file);
			}
		}

		File currentFile = null;
		Path sourcePath;
		Path destinationPath;
		while (!files.isEmpty()) {
			currentFile = files.pop();
			long fileSize = currentFile.length();
			fileSize /= (1024 * 1024);
			// currentFile.length()
			// currentFile.canWrite()

			sourcePath = Paths.get(currentFile.getPath());
			destinationPath = Paths.get(destination.getPath() + File.separator + currentFile.getName());
			// Files.move(sourcePath, destinationPath , StandardCopyOption.ATOMIC_MOVE);

			if (fileSize < 200) {
				try {
					// System.out.println("Starting moving of file: " + currentFile.getName());
					Files.move(sourcePath, destinationPath);
					// System.out.println("Finishing moving of file: " + currentFile.getName());
				} catch (FileAlreadyExistsException aee) {
					// Result of File.delete() is ignored => Zashto?
					// delete
					currentFile.delete();
				}
			} else {
				LargeFileThread moveBigFile = new LargeFileThread(currentFile, sourcePath, destinationPath);
				// System.out.println("prehvarlaneto ne trqbva da e zap4nalo!");

				moveBigFile.start();

				try {
					moveBigFile.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// System.out.println("Finishing of .start() command");
			}
		}
	}
}
