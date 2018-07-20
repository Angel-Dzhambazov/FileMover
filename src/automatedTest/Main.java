package automatedTest;

import java.awt.Point;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.CopyManager;
import common.CopyProcessor;


public class Main {

	private static Map<Integer, Boolean> tests = new LinkedHashMap<Integer, Boolean>();
	private static final String sourceFolderForTest = "C:\\Users\\a.dzhambazov\\Desktop\\filesTestFolder\\resource folder for test ";
	private static final String destination = "C:\\Users\\a.dzhambazov\\Desktop\\filesTestFolder\\folder1";
	private static final String finalDestination = "C:\\Users\\a.dzhambazov\\Desktop\\filesTestFolder\\folder2";

	public static void main(String[] args) {





		//files into directory 1 and checking if these X
		// files are present in directory 2
		createTest(1);

		// Start of test 2 - loading X files and Y directories into directory 1 and
		// checking if these X files are present in directory 2
		createTest(2);

		// Start of test 3 - loading Y directories into directory 1 and checking if
		// these X files are present in directory 2
		createTest(3);

		// printing each test with its result - true if test is successful false if not
		for (Map.Entry<Integer, Boolean> entry : tests.entrySet()) {
			System.out.println("Test N:" + entry.getKey() + " finished as " + entry.getValue());
		}
	}

	private static void createTest(Integer i) {
		String sourceFolder = sourceFolderForTest + i;

		// moving the files from the resource folder to the testing initial folder
		CopyManager copyManager = new CopyManager(sourceFolder, destination);
		CopyProcessor copyThread = new CopyProcessor(copyManager);

		// creating a list with checksums in order to compare them later
		List<String> sourceFilesChecksum = new ArrayList<>();
		fillListWithChecksum(sourceFilesChecksum, sourceFolder);

		// starting the thread moving the files from the test resource folder to the
		// destination folder
		copyThread.start();

		// sleeping the thread in order to give some time to OS to move the files
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		copyThread.shutdown();

		// filling a list with all the files' checkSums in the finalFolder
		List<String> destinationFilesChecksum = new ArrayList<>();
		fillListWithChecksum(destinationFilesChecksum, finalDestination);

		// filling our resultMap with the number of the test and its true or false
		// result
		boolean result = checkForSuccessfulComplete(sourceFilesChecksum, destinationFilesChecksum);
		tests.put(i, result);
	}

	private static boolean checkForSuccessfulComplete(List<String> listSource, List<String> listDestination) {

		int mapCounter = listSource.size();
		int tempCounter = 0;
		for (String string : listSource) {
			if (listDestination.contains(string)) {
				tempCounter++;
			} else {
				return false;
			}
		}

		if (mapCounter == tempCounter) {
			return true;
		}
		return false;
	}

	// filling a list with all the checksum files
	private static void fillListWithChecksum(List<String> list, String destination) {
		File source = new File(destination);
		for (File file : source.listFiles()) {
			// checking if it is file
			// TODO implement if we can write/ read from the file. If we cannot, we shall
			// skip moving the file.
			if (!file.isDirectory()) {
				try {
					list.add(getCheckSumOfFile(file));
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// getting checkSum of a file
	private static String getCheckSumOfFile(File file) throws NoSuchAlgorithmException, IOException {

		String filePath = file.getAbsolutePath();

		MessageDigest md = MessageDigest.getInstance("SHA1");
		FileInputStream fis = new FileInputStream(filePath);
		byte[] dataBytes = new byte[1024];

		int nread = 0;

		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}

		byte[] mdbytes = md.digest();

		// convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		fis.close();

		return sb.toString();
	}
}
